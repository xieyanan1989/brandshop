package com.platform.shiro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.platform.cache.J2CacheUtils;
import com.platform.dao.BrandSysMenuDao;
import com.platform.dao.BrandUserDao;
import com.platform.entity.BrandUserEntity;
import com.platform.entity.LoginTypeEntity;
import com.platform.entity.BrandSysMenuEntity;
import com.platform.utils.Constant;

public class BrandAdminRealm extends AuthorizingRealm {
    @Autowired
    private BrandUserDao brandUserDao;
    @Autowired
    private BrandSysMenuDao brandSysMenuDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	//获取用户
    	BrandUserEntity user = (BrandUserEntity) principals.getPrimaryPrincipal();
        Long userId = user.getUserId();

        List<String> permsList = (List<String>) J2CacheUtils.get(Constant.BRAND_PERMS_LIST + userId);

        //用户权限列表
        Set<String> permsSet = new HashSet<String>();
        if (permsList != null && permsList.size() != 0) {
            for (String perms : permsList) {
                if (StringUtils.isBlank(perms)) {
                    continue;
                }
                permsSet.addAll(Arrays.asList(perms.trim().split(",")));
            }
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    	BrandUserEntity user = null;
    	 String username = (String) token.getPrincipal();
         String password = new String((char[]) token.getCredentials());
        user = brandUserDao.queryObjectByUsername(username);
        if (user == null){
        	throw new UnknownAccountException("用户不存在！");
        }
        //密码错误
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }

        //账号锁定
        if (user.getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        List<String> permsList;
      //系统管理员，拥有最高权限
        if (Constant.SUPER_ADMIN == user.getUserType()) {
            List<BrandSysMenuEntity> menuList = brandSysMenuDao.queryList(new HashMap<String, Object>());
            permsList = new ArrayList<>(menuList.size());
            for (BrandSysMenuEntity menu : menuList) {
                permsList.add(menu.getPerms());
            }
        } else {
            permsList = brandUserDao.queryAllPerms(user.getUserId());
        }
        J2CacheUtils.put(Constant.BRAND_PERMS_LIST + user.getUserId(), permsList);
     // 把当前用户放入到session中
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(true);
        session.setAttribute(LoginTypeEntity.BRANDADMIN.toString(), user);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        //更新用户时间
        user.setLastLoginTime(new Date());
        brandUserDao.update(user);
        return info;
    }

}

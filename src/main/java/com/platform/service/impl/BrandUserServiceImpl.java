package com.platform.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.platform.dao.BrandUserDao;
import com.platform.entity.BrandSysDeptEntity;
import com.platform.entity.BrandUserEntity;
import com.platform.entity.SysUserEntity;
import com.platform.entity.UserWindowDto;
import com.platform.page.Page;
import com.platform.page.PageHelper;
import com.platform.service.BrandSysDeptService;
import com.platform.service.BrandSysRoleService;
import com.platform.service.BrandSysUserRoleService;
import com.platform.service.BrandUserService;
import com.platform.service.SysRoleService;
import com.platform.service.SysUserRoleService;
import com.platform.utils.Constant;
import com.platform.utils.RRException;
import com.platform.utils.WechatInterfaceUtil;

import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.J2Cache;

/**
 * 品牌管理表Service实现类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2019-06-11 10:02:54
 */
@Service("brandUserService")
public class BrandUserServiceImpl implements BrandUserService {
	 private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BrandUserDao brandUserDao;
    @Autowired
    private BrandSysUserRoleService brandSysUserRoleService;
    @Autowired
    private BrandSysRoleService brandSysRoleService;
    @Autowired
    private BrandSysDeptService brandSysDeptService;
    @Override
    public BrandUserEntity queryObject(Long id) {
        return brandUserDao.queryObject(id);
    }

    @Override
    public List<BrandUserEntity> queryList(Map<String, Object> map) {
        return brandUserDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return brandUserDao.queryTotal(map);
    }

    @Override
    public void save(BrandUserEntity brandUser) {
//    	brandUser.setMobile(brandUser.getUsername());
        //sha256加密
//    	brandUser.setPassword(new Sha256Hash(Constant.DEFAULT_PASS_WORD).toHex());
    	//小程序生成商家二维码
//    	JSONObject obj =  WechatInterfaceUtil.codeUnlimited(""+brandUser.getBrandId());
//    	if(obj.get("errcode") != null){
//    		logger.info("小程序生成商家二维码失败");
//        }else{
//        	logger.info("小程序生成商家二维码成功");
//        }
    	
    	brandUser.setRegisterTime(new Date());
        //sha256加密
    	brandUser.setPassword(new Sha256Hash(Constant.DEFAULT_PASS_WORD).toHex());
        brandUserDao.save(brandUser);

        //检查角色是否越权
        checkRole(brandUser);

        //保存用户与角色关系
        brandSysUserRoleService.saveOrUpdate(brandUser.getUserId(), brandUser.getRoleIdList());
    }

    @Override
    public void update(BrandUserEntity brandUser) {
//        brandUserDao.update(brandUser);
    	 if (StringUtils.isBlank(brandUser.getPassword())) {
    		 brandUser.setPassword(new Sha256Hash(Constant.DEFAULT_PASS_WORD).toHex());
         } else {
        	 brandUser.setPassword(new Sha256Hash(brandUser.getPassword()).toHex());
         }
         brandUserDao.update(brandUser);

         //检查角色是否越权
         checkRole(brandUser);

         //保存用户与角色关系
         brandSysUserRoleService.saveOrUpdate(brandUser.getUserId(), brandUser.getRoleIdList());
    }

    @Override
    public int delete(Long id) {
        return brandUserDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[] ids) {
        return brandUserDao.deleteBatch(ids);
    }

	@Override
	public BrandUserEntity queryObjectByUsername(String username) {
		// TODO Auto-generated method stub
		return brandUserDao.queryObjectByUsername(username);
	}

	@Override
	public int updatePassword(Long userId, String password, String newPassword) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("password", password);
		map.put("newPassword", newPassword);
		return brandUserDao.updatePassword(map);
	}

	@Override
	public List<Long> queryAllMenuId(Long userId) {
		return brandUserDao.queryAllMenuId(userId);
	}
	
	 /**
     * 检查角色是否越权
     */
    private void checkRole(BrandUserEntity user) {
        //如果不是超级管理员，则需要判断用户的角色是否自己创建
        if (user.getUserType() == Constant.SUPER_ADMIN) {
            return;
        }

        //查询用户创建的角色列表
        List<Long> roleIdList = brandSysRoleService.queryRoleIdList(user.getCreateUserId());

        //判断是否越权
        if (!roleIdList.containsAll(user.getRoleIdList())) {
            throw new RRException("新增用户所选角色，不是本人创建");
        }
    }
    /**
     * 新增一个品牌商
     */
	@Override
	public void brandSave(BrandUserEntity brandUser) {
		// TODO Auto-generated method stub
//    	brandUser.setMobile(brandUser.getUsername());
        //sha256加密
//    	brandUser.setPassword(new Sha256Hash(Constant.DEFAULT_PASS_WORD).toHex());
    	//小程序生成商家二维码
//    	JSONObject obj =  WechatInterfaceUtil.codeUnlimited(""+brandUser.getBrandId());
//    	if(obj.get("errcode") != null){
//    		logger.info("小程序生成商家二维码失败");
//        }else{
//        	logger.info("小程序生成商家二维码成功");
//        }
    	
    	brandUser.setRegisterTime(new Date());
        //sha256加密
    	brandUser.setPassword(new Sha256Hash(Constant.DEFAULT_PASS_WORD).toHex());
        brandUserDao.save(brandUser);
        //检查角色是否越权
//        checkRole(brandUser);
        //保存用户与角色关系
//        brandSysUserRoleService.saveOrUpdate(brandUser.getUserId(), brandUser.getRoleIdList());
        //新建一个商品的总部
//        BrandSysDeptEntity sysDept = new BrandSysDeptEntity();
//        sysDept.setBrandId(brandUser.getBrandId());
//        sysDept.setName("总部");
//        sysDept.setParentId(0l);
//        sysDept.setOrderNum(0);
//        sysDept.setDeptId(0l);
//        brandSysDeptService.save(sysDept);
	}
	/**
     * 修改品牌商
     */
	@Override
	public void brandUpdate(BrandUserEntity brandUser) {
		// TODO Auto-generated method stub
//  	 if (StringUtils.isBlank(brandUser.getPassword())) {
//  		 brandUser.setPassword(new Sha256Hash(Constant.DEFAULT_PASS_WORD).toHex());
//       } else {
//      	 brandUser.setPassword(new Sha256Hash(brandUser.getPassword()).toHex());
//       }
       brandUserDao.update(brandUser);
       //检查角色是否越权
//       checkRole(brandUser);
       //保存用户与角色关系
//       brandSysUserRoleService.saveOrUpdate(brandUser.getUserId(), brandUser.getRoleIdList());
	}

}

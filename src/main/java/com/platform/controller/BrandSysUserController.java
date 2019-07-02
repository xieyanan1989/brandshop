package com.platform.controller;

import com.platform.annotation.SysLog;
import com.platform.entity.BrandUserEntity;
import com.platform.service.BrandSysUserRoleService;
import com.platform.service.BrandUserService;
import com.platform.service.SysUserRoleService;
import com.platform.utils.*;
import com.platform.validator.Assert;
import com.platform.validator.ValidatorUtils;
import com.platform.validator.group.AddGroup;
import com.platform.validator.group.UpdateGroup;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年10月31日 上午10:40:10
 */
@RestController
@RequestMapping("/brand/sys/user")
public class BrandSysUserController extends BrandAbstractController {
    @Autowired
    private BrandUserService brandUserService;
    @Autowired
    private BrandSysUserRoleService sysUserRoleService;

    /**
     * 所有用户列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:user:list")
    public R list(@RequestParam Map<String, Object> params) {
        //只有超级管理员，才能查看所有管理员列表
        if (getUserType() != Constant.SUPER_ADMIN) {
            params.put("createUserId", getUserId());
        }
        params.put("brandId", getBrandId());
        //查询列表数据
        Query query = new Query(params);
        List<BrandUserEntity> userList = brandUserService.queryList(query);
        int total = brandUserService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 获取登录的用户信息
     */
    @RequestMapping("/info")
    public R info() {
        return R.ok().put("user", ShiroUtils.getBrandUserEntity());
    }

    /**
     * 修改登录用户密码
     */
    @SysLog("修改密码")
    @RequestMapping("/password")
    public R password(String password, String newPassword) {
        if(ResourceUtil.getConfigByName("sys.demo").equals("1")){
            throw new RRException("演示环境无法修改密码！");
        }
        Assert.isBlank(newPassword, "新密码不为能空");

        //sha256加密
        password = new Sha256Hash(password).toHex();
        //sha256加密
        newPassword = new Sha256Hash(newPassword).toHex();

        //更新密码
        int count = brandUserService.updatePassword(ShiroUtils.getBrandUserId(), password, newPassword);
        if (count == 0) {
            return R.error("原密码不正确");
        }

        //退出
        ShiroUtils.logout();

        return R.ok();
    }

    /**
     * 用户信息
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("sys:user:info")
    public R info(@PathVariable("userId") Long userId) {
        BrandUserEntity user = brandUserService.queryObject(userId);

        //获取用户所属的角色列表
        List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);

        return R.ok().put("user", user);
    }

    /**
     * 保存用户
     */
    @SysLog("保存用户")
    @RequestMapping("/save")
    @RequiresPermissions("sys:user:save")
    public R save(@RequestBody BrandUserEntity user) {
        ValidatorUtils.validateEntity(user, AddGroup.class);
        if(user.getBrandId() == null){
        	user.setBrandId(getBrandId());
        }
        user.setCreateUserId(getUserId());
        user.setUserType(Constant.NORMA_ADMIN);
        brandUserService.save(user);

        return R.ok();
    }

    /**
     * 修改用户
     */
    @SysLog("修改用户")
    @RequestMapping("/update")
    @RequiresPermissions("sys:user:update")
    public R update(@RequestBody BrandUserEntity user) {
        ValidatorUtils.validateEntity(user, UpdateGroup.class);

        user.setCreateUserId(getUserId());
        brandUserService.update(user);

        return R.ok();
    }

    /**
     * 删除用户
     */
    @SysLog("删除用户")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    public R delete(@RequestBody Long[] userIds) {
        if (ArrayUtils.contains(userIds, 1L)) {
            return R.error("系统管理员不能删除");
        }

        if (ArrayUtils.contains(userIds, getUserId())) {
            return R.error("当前用户不能删除");
        }

        brandUserService.deleteBatch(userIds);

        return R.ok();
    }
}

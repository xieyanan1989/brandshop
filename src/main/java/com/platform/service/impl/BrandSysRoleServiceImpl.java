package com.platform.service.impl;

import com.platform.controller.BrandAbstractController;
import com.platform.dao.BrandSysRoleDao;
import com.platform.dao.SysRoleDao;
import com.platform.entity.BrandSysRoleEntity;
import com.platform.entity.UserWindowDto;
import com.platform.page.Page;
import com.platform.page.PageHelper;
import com.platform.service.BrandSysRoleDeptService;
import com.platform.service.BrandSysRoleMenuService;
import com.platform.service.BrandSysRoleService;
import com.platform.service.BrandUserService;
import com.platform.service.SysRoleDeptService;
import com.platform.service.SysRoleMenuService;
import com.platform.service.SysRoleService;
import com.platform.service.SysUserService;
import com.platform.utils.Constant;
import com.platform.utils.RRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 角色
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年9月18日 上午9:45:12
 */
@Service("brandSysRoleService")
public class BrandSysRoleServiceImpl extends BrandAbstractController implements BrandSysRoleService {
    @Autowired
    private BrandSysRoleDao sysRoleDao;
    @Autowired
    private BrandSysRoleMenuService sysRoleMenuService;
    @Autowired
    private BrandUserService sysUserService;
    @Autowired
    private BrandSysRoleDeptService sysRoleDeptService;

    @Override
    public BrandSysRoleEntity queryObject(Long roleId) {
        return sysRoleDao.queryObject(roleId);
    }

    @Override
    public List<BrandSysRoleEntity> queryList(Map<String, Object> map) {
        return sysRoleDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysRoleDao.queryTotal(map);
    }

    @Override
    @Transactional
    public void save(BrandSysRoleEntity role) {
        role.setCreateTime(new Date());
        sysRoleDao.save(role);

        //检查权限是否越权
        checkPrems(role);

        //保存角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());

        //保存角色与部门关系
        sysRoleDeptService.saveOrUpdate(role.getRoleId(), role.getDeptIdList());
    }

    @Override
    @Transactional
    public void update(BrandSysRoleEntity role) {
        sysRoleDao.update(role);

        //检查权限是否越权
        checkPrems(role);

        //更新角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
        //保存角色与部门关系
        sysRoleDeptService.saveOrUpdate(role.getRoleId(), role.getDeptIdList());
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] roleIds) {
        sysRoleDao.deleteBatch(roleIds);
    }

    @Override
    public List<Long> queryRoleIdList(Long createUserId) {
        return sysRoleDao.queryRoleIdList(createUserId);
    }

    /**
     * 检查权限是否越权
     */
    private void checkPrems(BrandSysRoleEntity role) {
        //如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
        if (getUserType() == Constant.SUPER_ADMIN) {
            return;
        }

        //查询用户所拥有的菜单列表
        List<Long> menuIdList = sysUserService.queryAllMenuId(role.getCreateUserId());

        //判断是否越权
        if (!menuIdList.containsAll(role.getMenuIdList())) {
            throw new RRException("新增角色的权限，已超出你的权限范围");
        }
    }

    @Override
    public Page<UserWindowDto> queryPageByDto(UserWindowDto userWindowDto, int pageNum) {
        PageHelper.startPage(pageNum, Constant.pageSize);
        sysRoleDao.queryPageByDto(userWindowDto);
        return PageHelper.endPage();
    }
}

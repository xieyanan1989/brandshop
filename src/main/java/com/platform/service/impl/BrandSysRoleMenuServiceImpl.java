package com.platform.service.impl;

import com.platform.dao.BrandSysRoleMenuDao;
import com.platform.service.BrandSysRoleMenuService;
import com.platform.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 角色与菜单对应关系
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年9月18日 上午9:44:35
 */
@Service("brandSysRoleMenuService")
public class BrandSysRoleMenuServiceImpl implements BrandSysRoleMenuService {
    @Autowired
    private BrandSysRoleMenuDao brandSysRoleMenuDao;

    @Override
    @Transactional
    public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
        if (menuIdList.size() == 0) {
            return;
        }
        //先删除角色与菜单关系
        brandSysRoleMenuDao.delete(roleId);

        //保存角色与菜单关系
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", roleId);
        map.put("menuIdList", menuIdList);
        brandSysRoleMenuDao.save(map);
    }

    @Override
    public List<Long> queryMenuIdList(Long roleId) {
        return brandSysRoleMenuDao.queryMenuIdList(roleId);
    }

}

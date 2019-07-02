package com.platform.service;
import com.platform.entity.BrandSysMenuEntity;
import com.platform.entity.BrandUserEntity;

import java.util.List;
import java.util.Map;


/**
 * 菜单管理
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年9月18日 上午9:42:16
 */
public interface BrandSysMenuService {

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId   父菜单ID
     * @param menuIdList 用户菜单ID
     */
    List<BrandSysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<BrandSysMenuEntity> queryNotButtonList();

    /**
     * 获取用户菜单列表
     */
    List<BrandSysMenuEntity> getUserMenuList(BrandUserEntity brandUserEntity);


    /**
     * 查询菜单
     */
    BrandSysMenuEntity queryObject(Long menuId);

    /**
     * 查询菜单列表
     */
    List<BrandSysMenuEntity> queryList(Map<String, Object> map);

    /**
     * 查询总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存菜单
     */
    void save(BrandSysMenuEntity menu);

    /**
     * 修改
     */
    void update(BrandSysMenuEntity menu);

    /**
     * 删除
     */
    void deleteBatch(Long[] menuIds);

    /**
     * 查询用户的权限列表
     */
    List<BrandSysMenuEntity> queryUserList(Long userId);
}

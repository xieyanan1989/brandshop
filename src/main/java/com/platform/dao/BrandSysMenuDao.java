package com.platform.dao;
import com.platform.entity.BrandSysMenuEntity;

import java.util.List;

/**
 * 菜单管理
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年9月18日 上午9:33:01
 */
public interface BrandSysMenuDao extends BaseDao<BrandSysMenuEntity> {

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId 父菜单ID
     */
    List<BrandSysMenuEntity> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<BrandSysMenuEntity> queryNotButtonList();

    /**
     * 查询用户的权限列表
     */
    List<BrandSysMenuEntity> queryUserList(Long userId);
}

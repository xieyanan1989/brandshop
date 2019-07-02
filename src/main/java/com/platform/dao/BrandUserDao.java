package com.platform.dao;

import java.util.List;
import java.util.Map;

import com.platform.entity.BrandUserEntity;

/**
 * 品牌管理表Dao
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2019-06-11 10:02:54
 */
public interface BrandUserDao extends BaseDao<BrandUserEntity> {
	 /**
     * 根据用户名，查询系统用户
     */
	BrandUserEntity queryObjectByUsername(String username);
	/**
     * 更新密码
     */
	int updatePassword(Map<String, Object> map);
	/**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);
    /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     */
    List<String> queryAllPerms(Long userId);
}

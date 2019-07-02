package com.platform.service;

import com.platform.entity.BrandUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 品牌管理表Service接口
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2019-06-19 16:12:47
 */
public interface BrandUserService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    BrandUserEntity queryObject(Long userId);
    /**
     * 根据username查询实体
     *
     * @param username 用户名
     * @return 实体
     */
    BrandUserEntity queryObjectByUsername(String username);
    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<BrandUserEntity> queryList(Map<String, Object> map);

    /**
     * 分页统计总数
     *
     * @param map 参数
     * @return 总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存实体
     *
     * @param brandUser 实体
     * @return 保存条数
     */
    void save(BrandUserEntity brandUser);

    /**
     * 根据主键更新实体
     *
     * @param brandUser 实体
     * @return 更新条数
     */
    void update(BrandUserEntity brandUser);

    /**
     * 根据主键删除
     *
     * @param userId
     * @return 删除条数
     */
    int delete(Long userId);

    /**
     * 根据主键批量删除
     *
     * @param userIds
     * @return 删除条数
     */
    int deleteBatch(Long[] userIds);
    /**
     * 修改密码
     *
     * @param userId      用户ID
     * @param password    原密码
     * @param newPassword 新密码
     */
    int updatePassword(Long userId, String password, String newPassword);
    /**
     * 查询用户的所有菜单ID
     */
	List<Long> queryAllMenuId(Long userId);
	/**
     * 新增一个品牌商
     */
	void brandSave(BrandUserEntity brandUser);
	/**
     * 修改品牌商
     */
	void brandUpdate(BrandUserEntity brandUser);
}

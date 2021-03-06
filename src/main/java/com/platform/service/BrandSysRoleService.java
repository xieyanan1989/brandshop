package com.platform.service;

import com.platform.entity.BrandSysRoleEntity;
import com.platform.entity.UserWindowDto;
import com.platform.page.Page;

import java.util.List;
import java.util.Map;


/**
 * 角色
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年9月18日 上午9:42:52
 */
public interface BrandSysRoleService {

    BrandSysRoleEntity queryObject(Long roleId);

    List<BrandSysRoleEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(BrandSysRoleEntity role);

    void update(BrandSysRoleEntity role);

    void deleteBatch(Long[] roleIds);

    /**
     * 查询用户创建的角色ID列表
     */
    List<Long> queryRoleIdList(Long createUserId);

    /**
     * 分页查询角色审批选择范围
     * @return
     */
    Page<UserWindowDto> queryPageByDto(UserWindowDto userWindowDto, int pageNmu);
}

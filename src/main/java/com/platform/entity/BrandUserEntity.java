package com.platform.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.platform.validator.group.AddGroup;
import com.platform.validator.group.UpdateGroup;

/**
 * 品牌管理表实体
 * 表名 nideshop_brand_user
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2019-06-19 16:12:47
 */
public class BrandUserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long userId;
    //品牌id
    private Integer brandId;
    //用户名
    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String username;
    //密码
    private String password;
    //注册时间
    private Date registerTime;
    //最后登录时间
    private Date lastLoginTime;
    //最后登录ip
    private String lastLoginIp;
    //手机号
    private String mobile;
    //状态  0：禁用   1：正常
    private Integer status;
    //创建者ID
    private Long createUserId;
    //部门ID
    private Long deptId;
    //小程序二维码数据
    private String qrData;
    //用户类型 1:管理员 2:普通用户
    private Integer userType;
    /**
     * 角色ID列表
     */
    private List<Long> roleIdList;
    
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 设置：
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取：
     */
    public Long getUserId() {
        return userId;
    }
    /**
     * 设置：品牌id
     */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    /**
     * 获取：品牌id
     */
    public Integer getBrandId() {
        return brandId;
    }
    /**
     * 设置：用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取：用户名
     */
    public String getUsername() {
        return username;
    }
    /**
     * 设置：密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取：密码
     */
    public String getPassword() {
        return password;
    }
    /**
     * 设置：注册时间
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 获取：注册时间
     */
    public Date getRegisterTime() {
        return registerTime;
    }
    /**
     * 设置：最后登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取：最后登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }
    /**
     * 设置：最后登录ip
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    /**
     * 获取：最后登录ip
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }
    /**
     * 设置：手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：手机号
     */
    public String getMobile() {
        return mobile;
    }
    /**
     * 设置：状态  0：禁用   1：正常
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：状态  0：禁用   1：正常
     */
    public Integer getStatus() {
        return status;
    }
    /**
     * 设置：创建者ID
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取：创建者ID
     */
    public Long getCreateUserId() {
        return createUserId;
    }
    /**
     * 设置：部门ID
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取：部门ID
     */
    public Long getDeptId() {
        return deptId;
    }
    /**
     * 设置：小程序二维码数据
     */
    public void setQrData(String qrData) {
        this.qrData = qrData;
    }

    /**
     * 获取：小程序二维码数据
     */
    public String getQrData() {
        return qrData;
    }
    
    /**
     * 设置：用户类型 1:管理员 2:普通用户
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * 获取：用户类型 1:管理员 2:普通用户
     */
    public Integer getUserType() {
        return userType;
    }
    
    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}

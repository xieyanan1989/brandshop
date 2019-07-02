package com.platform.controller;

import com.platform.annotation.SysLog;
import com.platform.entity.BrandSysDeptEntity;
import com.platform.service.BrandSysDeptService;
import com.platform.utils.Constant;
import com.platform.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门管理Controller
 *
 * @author liepngjun
 * @email 939961241@qq.com
 * @date 2017-09-17 23:58:47
 */
@RestController
@RequestMapping("/brand/sys/dept")
public class BrandSysDeptController extends BrandAbstractController {
    @Autowired
    private BrandSysDeptService brandSysDeptService;

    /**
     * 部门列表
     *
     * @return R
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:dept:list")
    public R list() {
        Map<String, Object> map = new HashMap<>();
        //如果不是超级管理员，则只能查询本部门及子部门数据
        if (getUserType() != Constant.SUPER_ADMIN) {
            map.put("deptFilter", brandSysDeptService.getSubDeptIdList(getDeptId()));
        }
        map.put("brandId", getBrandId());
        List<BrandSysDeptEntity> deptList = brandSysDeptService.queryList(map);
        return R.ok().put("list", deptList);
    }

    /**
     * 选择部门(添加、修改菜单)
     *
     * @return R
     */
    @RequestMapping("/select")
    @RequiresPermissions("sys:dept:select")
    public R select() {
        Map<String, Object> map = new HashMap<>();
        //如果不是超级管理员，则只能查询本部门及子部门数据
        if (getUserType() != Constant.SUPER_ADMIN) {
            map.put("deptFilter", brandSysDeptService.getSubDeptIdList(getDeptId()));
        }
        map.put("brandId", getBrandId());
        List<BrandSysDeptEntity> deptList = brandSysDeptService.queryList(map);

        //添加一级部门
        if (getUserType() == Constant.SUPER_ADMIN) {
            BrandSysDeptEntity root = new BrandSysDeptEntity();
            root.setDeptId(0L);
            root.setName("一级部门");
            root.setParentId(-1L);
            root.setOpen(true);
            deptList.add(root);
        }

        return R.ok().put("deptList", deptList);
    }

    /**
     * 获取用户部门Id(管理员则为0)
     *
     * @return
     */
    @RequestMapping("/info")
    @RequiresPermissions("sys:dept:list")
    public R info() {
        long deptId = 0;
        if (getUserType() != Constant.SUPER_ADMIN) {
            BrandSysDeptEntity dept = brandSysDeptService.queryObject(getDeptId());
            deptId = dept.getParentId();
        }

        return R.ok().put("deptId", deptId);
    }

    /**
     * 根据主键获取部门信息
     *
     * @param deptId 主键
     * @return R
     */
    @RequestMapping("/info/{deptId}")
    @RequiresPermissions("sys:dept:info")
    public R info(@PathVariable("deptId") Long deptId) {
        BrandSysDeptEntity dept = brandSysDeptService.queryObject(deptId);

        return R.ok().put("dept", dept);
    }

    /**
     * 新增部门
     *
     * @param dept 部门
     * @return R
     */
    @SysLog("新增部门")
    @RequestMapping("/save")
    @RequiresPermissions("sys:dept:save")
    public R save(@RequestBody BrandSysDeptEntity dept) {
    	dept.setBrandId(getBrandId());
        brandSysDeptService.save(dept);

        return R.ok();
    }

    /**
     * 修改部门
     *
     * @param dept 部门
     * @return R
     */
    @SysLog("修改部门")
    @RequestMapping("/update")
    @RequiresPermissions("sys:dept:update")
    public R update(@RequestBody BrandSysDeptEntity dept) {
    	dept.setBrandId(getBrandId());
        brandSysDeptService.update(dept);

        return R.ok();
    }

    /**
     * 删除部门
     *
     * @param deptId 主键
     * @return R
     */
    @SysLog("删除部门")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:dept:delete")
    public R delete(long deptId) {
        //判断是否有子部门
        List<Long> deptList = brandSysDeptService.queryDetpIdList(deptId);
        if (deptList.size() > 0) {
            return R.error("请先删除子部门");
        }

        brandSysDeptService.delete(deptId);

        return R.ok();
    }

}

package com.platform.service.impl;

import com.platform.dao.BrandSysDeptDao;
import com.platform.entity.BrandSysDeptEntity;
import com.platform.entity.UserWindowDto;
import com.platform.page.Page;
import com.platform.page.PageHelper;
import com.platform.service.BrandSysDeptService;
import com.platform.service.SysDeptService;
import com.platform.utils.Constant;
import com.qiniu.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("BrandSysDeptService")
public class BrandSysDeptServiceImpl implements BrandSysDeptService {
    @Autowired
    private BrandSysDeptDao brandSysDeptDao;

    @Override
    public BrandSysDeptEntity queryObject(Long deptId) {
        return brandSysDeptDao.queryObject(deptId);
    }

    @Override
    public List<BrandSysDeptEntity> queryList(Map<String, Object> map) {
        return brandSysDeptDao.queryList(map);
    }

    @Override
    public void save(BrandSysDeptEntity sysDept) {
        brandSysDeptDao.save(sysDept);
    }

    @Override
    public void update(BrandSysDeptEntity sysDept) {
        brandSysDeptDao.update(sysDept);
    }

    @Override
    public void delete(Long deptId) {
        brandSysDeptDao.delete(deptId);
    }

    @Override
    public List<Long> queryDetpIdList(Long parentId) {
        return brandSysDeptDao.queryDetpIdList(parentId);
    }

    @Override
    public String getSubDeptIdList(Long deptId) {
        //部门及子部门ID列表
        List<Long> deptIdList = new ArrayList<>();

        //获取子部门ID
        List<Long> subIdList = queryDetpIdList(deptId);
        getDeptTreeList(subIdList, deptIdList);

        //添加本部门
        deptIdList.add(deptId);

        String deptFilter = StringUtils.join(deptIdList, ",");
        return deptFilter;
    }

    /**
     * 递归
     */
    public void getDeptTreeList(List<Long> subIdList, List<Long> deptIdList) {
        for (Long deptId : subIdList) {
            List<Long> list = queryDetpIdList(deptId);
            if (list.size() > 0) {
                getDeptTreeList(list, deptIdList);
            }

            deptIdList.add(deptId);
        }
    }

    @Override
    public Page<UserWindowDto> queryPageByDto(UserWindowDto userWindowDto, int pageNum) {
        PageHelper.startPage(pageNum, Constant.pageSize);
        brandSysDeptDao.queryPageByDto(userWindowDto);
        return PageHelper.endPage();
    }
}

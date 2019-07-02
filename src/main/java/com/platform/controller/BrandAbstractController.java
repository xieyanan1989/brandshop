package com.platform.controller;

import com.platform.entity.BrandUserEntity;
import com.platform.utils.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller公共组件
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年11月9日 下午9:42:26
 */
public abstract class BrandAbstractController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected BrandUserEntity getUser() {
        return ShiroUtils.getBrandUserEntity();
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }

    protected Long getDeptId() {
        return getUser().getDeptId();
    }
    protected Integer getBrandId() {
        return getUser().getBrandId();
    }
    protected Integer getUserType() {
        return getUser().getUserType();
    }
}

package com.platform.service;

import com.platform.entity.BrandFootprintEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-13 10:41:08
 */
public interface FootprintService {
	
	BrandFootprintEntity queryObject(Integer id);
	
	List<BrandFootprintEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BrandFootprintEntity footprint);
	
	void update(BrandFootprintEntity footprint);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}

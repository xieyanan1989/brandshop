package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.BrandFootprintDao;
import com.platform.entity.FootprintEntity;
import com.platform.service.BrandFootprintService;


@Service("brandFootprintService")
public class BrandFootprintServiceImpl implements BrandFootprintService {
	@Autowired
	private BrandFootprintDao footprintDao;
	
	@Override
	public FootprintEntity queryObject(Integer id){
		return footprintDao.queryObject(id);
	}
	
	@Override
	public List<FootprintEntity> queryList(Map<String, Object> map){
		return footprintDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return footprintDao.queryTotal(map);
	}
	
	@Override
	public void save(FootprintEntity footprint){
		footprintDao.save(footprint);
	}
	
	@Override
	public void update(FootprintEntity footprint){
		footprintDao.update(footprint);
	}
	
	@Override
	public void delete(Integer id){
		footprintDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		footprintDao.deleteBatch(ids);
	}
	
}

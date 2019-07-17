package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.FootprintDao;
import com.platform.entity.BrandFootprintEntity;
import com.platform.service.FootprintService;


@Service("footprintService")
public class FootprintServiceImpl implements FootprintService {
	@Autowired
	private FootprintDao footprintDao;
	
	@Override
	public BrandFootprintEntity queryObject(Integer id){
		return footprintDao.queryObject(id);
	}
	
	@Override
	public List<BrandFootprintEntity> queryList(Map<String, Object> map){
		return footprintDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return footprintDao.queryTotal(map);
	}
	
	@Override
	public void save(BrandFootprintEntity footprint){
		footprintDao.save(footprint);
	}
	
	@Override
	public void update(BrandFootprintEntity footprint){
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

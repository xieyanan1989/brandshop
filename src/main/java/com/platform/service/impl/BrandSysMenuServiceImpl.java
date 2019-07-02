package com.platform.service.impl;

import com.platform.dao.BrandSysMenuDao;
import com.platform.dao.SysMenuDao;
import com.platform.entity.BrandSysMenuEntity;
import com.platform.entity.BrandUserEntity;
import com.platform.service.BrandSysMenuService;
import com.platform.service.BrandUserService;
import com.platform.service.SysMenuService;
import com.platform.service.SysRoleMenuService;
import com.platform.service.SysUserService;
import com.platform.utils.Constant.MenuType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("brandSysMenuService")
public class BrandSysMenuServiceImpl implements BrandSysMenuService {
	@Autowired
	private BrandSysMenuDao brandSysMenuDao;
	@Autowired
	private BrandUserService brandUserService;
	
	@Override
	public List<BrandSysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
		List<BrandSysMenuEntity> menuList = brandSysMenuDao.queryListParentId(parentId);
		if(menuIdList == null){
			return menuList;
		}
		
		List<BrandSysMenuEntity> userMenuList = new ArrayList<>();
		for(BrandSysMenuEntity menu : menuList){
			if(menuIdList.contains(menu.getMenuId())){
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}

	@Override
	public List<BrandSysMenuEntity> queryNotButtonList() {
		return brandSysMenuDao.queryNotButtonList();
	}

	@Override
	public List<BrandSysMenuEntity> getUserMenuList(BrandUserEntity brandUserEntity) {
		//系统管理员，拥有最高权限
		if(brandUserEntity.getUserType() == 1){
			return getAllMenuList(null);
		}
		
		//用户菜单列表
		List<Long> menuIdList = brandUserService.queryAllMenuId(brandUserEntity.getUserId());
		return getAllMenuList(menuIdList);
	}
	
	@Override
	public BrandSysMenuEntity queryObject(Long menuId) {
		return brandSysMenuDao.queryObject(menuId);
	}

	@Override
	public List<BrandSysMenuEntity> queryList(Map<String, Object> map) {
		return brandSysMenuDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return brandSysMenuDao.queryTotal(map);
	}

	@Override
	public void save(BrandSysMenuEntity menu) {
		brandSysMenuDao.save(menu);
	}

	@Override
	public void update(BrandSysMenuEntity menu) {
		brandSysMenuDao.update(menu);
	}

	@Override
	@Transactional
	public void deleteBatch(Long[] menuIds) {
		brandSysMenuDao.deleteBatch(menuIds);
	}
	
	@Override
	public List<BrandSysMenuEntity> queryUserList(Long userId) {
		return brandSysMenuDao.queryUserList(userId);
	}

	/**
	 * 获取所有菜单列表
	 */
	private List<BrandSysMenuEntity> getAllMenuList(List<Long> menuIdList){
		//查询根菜单列表
		List<BrandSysMenuEntity> menuList = queryListParentId(0L, menuIdList);
		//递归获取子菜单
		getMenuTreeList(menuList, menuIdList);
		
		return menuList;
	}

	/**
	 * 递归
	 */
	private List<BrandSysMenuEntity> getMenuTreeList(List<BrandSysMenuEntity> menuList, List<Long> menuIdList){
		List<BrandSysMenuEntity> subMenuList = new ArrayList<BrandSysMenuEntity>();
		
		for(BrandSysMenuEntity entity : menuList){
			if(entity.getType() == MenuType.CATALOG.getValue()){//目录
				entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
			}
			subMenuList.add(entity);
		}
		
		return subMenuList;
	}
}

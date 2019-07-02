package com.platform.service.impl;

import com.platform.annotation.DataFilter;
import com.platform.dao.GoodsAttributeDao;
import com.platform.dao.BrandGoodsDao;
import com.platform.dao.BrandGoodsGalleryDao;
import com.platform.dao.BrandProductDao;
import com.platform.entity.GoodsGalleryEntity;
import com.platform.entity.BrandUserEntity;
import com.platform.entity.GoodsAttributeEntity;
import com.platform.entity.GoodsEntity;
import com.platform.entity.ProductEntity;
import com.platform.entity.SysUserEntity;
import com.platform.service.BrandGoodsService;
import com.platform.utils.RRException;
import com.platform.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-21 21:19:49
 */
@Service("brandGoodsService")
public class BrandGoodsServiceImpl implements BrandGoodsService {
    @Autowired
    private BrandGoodsDao goodsDao;
    @Autowired
    private GoodsAttributeDao goodsAttributeDao;
    @Autowired
    private BrandProductDao productDao;
    @Autowired
    private BrandGoodsGalleryDao goodsGalleryDao;

    @Override
    public GoodsEntity queryObject(Integer id) {
        return goodsDao.queryObject(id);
    }

    @Override
    @DataFilter(userAlias = "nideshop_goods.create_user_id", deptAlias = "nideshop_goods.create_user_dept_id")
    public List<GoodsEntity> queryList(Map<String, Object> map) {
        return goodsDao.queryList(map);
    }

    @Override
    @DataFilter(userAlias = "nideshop_goods.create_user_id", deptAlias = "nideshop_goods.create_user_dept_id")
    public int queryTotal(Map<String, Object> map) {
        return goodsDao.queryTotal(map);
    }

    @Override
    @Transactional
    public int save(GoodsEntity goods) {
//        BrandUserEntity user = ShiroUtils.getBrandUserEntity();
    	BrandUserEntity user = ShiroUtils.getBrandUserEntity();
    	System.out.println("goods.getSortOrder:"+goods.getSortOrder());
        Map<String, Object> map = new HashMap<>();
        map.put("name", goods.getName());
        List<GoodsEntity> list = queryList(map);
        if (null != list && list.size() != 0) {
            throw new RRException("商品名称已存在！");
        }
        Integer id = goodsDao.queryMaxId() + 1;
        goods.setId(id);

        //保存产品信息
        ProductEntity productEntity = new ProductEntity();
        productEntity.setGoodsId(id);
        productEntity.setGoodsSn(goods.getGoodsSn());
        productEntity.setGoodsNumber(goods.getGoodsNumber());
        productEntity.setRetailPrice(goods.getRetailPrice());
        productEntity.setMarketPrice(goods.getMarketPrice());
        productEntity.setGoodsSpecificationIds("");
        productDao.save(productEntity);

        goods.setAddTime(new Date());
        goods.setPrimaryProductId(productEntity.getId());

        //保存商品详情页面显示的属性
        List<GoodsAttributeEntity> attributeEntityList = goods.getAttributeEntityList();
        if (null != attributeEntityList && attributeEntityList.size() > 0) {
            for (GoodsAttributeEntity item : attributeEntityList) {
                item.setGoodsId(id);
                goodsAttributeDao.save(item);
            }
        }

        //商品轮播图
        List<GoodsGalleryEntity> galleryEntityList = goods.getGoodsImgList();
        if (null != galleryEntityList && galleryEntityList.size() > 0) {
            for (GoodsGalleryEntity galleryEntity : galleryEntityList) {
                galleryEntity.setGoodsId(id);
                goodsGalleryDao.save(galleryEntity);
            }
        }
        goods.setGoodsSn(""+id);
        goods.setBrandId(user.getBrandId());
        goods.setIsDelete(0);
        goods.setCreateUserDeptId(user.getDeptId());
        goods.setCreateUserId(user.getUserId());
        goods.setUpdateUserId(user.getUserId());
        goods.setUpdateTime(new Date());
        return goodsDao.save(goods);
    }

    @Override
    @Transactional
    public int update(GoodsEntity goods) {
    	BrandUserEntity user = ShiroUtils.getBrandUserEntity();
        List<GoodsAttributeEntity> attributeEntityList = goods.getAttributeEntityList();
        if (null != attributeEntityList && attributeEntityList.size() > 0) {
            for (GoodsAttributeEntity goodsAttributeEntity : attributeEntityList) {
                int result = goodsAttributeDao.updateByGoodsIdAttributeId(goodsAttributeEntity);
                if (result == 0) {
                    goodsAttributeDao.save(goodsAttributeEntity);
                }
            }
        }
        goods.setUpdateUserId(user.getUserId());
        goods.setUpdateTime(new Date());
        //商品轮播图
        //修改时全删全插
        List<GoodsGalleryEntity> galleryEntityList = goods.getGoodsImgList();
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("goodsId", goods.getId());
        goodsGalleryDao.deleteByGoodsId(map);

        if (null != galleryEntityList && galleryEntityList.size() > 0) {
            for (GoodsGalleryEntity galleryEntity : galleryEntityList) {
                galleryEntity.setGoodsId(goods.getId());
                goodsGalleryDao.save(galleryEntity);
            }
        }
        return goodsDao.update(goods);
    }

    @Override
    public int delete(Integer id) {
        BrandUserEntity user = ShiroUtils.getBrandUserEntity();
        GoodsEntity goodsEntity = queryObject(id);
        goodsEntity.setIsDelete(1);
        goodsEntity.setIsOnSale(0);
        goodsEntity.setUpdateUserId(user.getUserId());
        goodsEntity.setUpdateTime(new Date());
        return goodsDao.update(goodsEntity);
    }

    @Override
    @Transactional
    public int deleteBatch(Integer[] ids) {
        int result = 0;
        for (Integer id : ids) {
            result += delete(id);
        }
        return result;
    }

    @Override
    @Transactional
    public int back(Integer[] ids) {
        BrandUserEntity user = ShiroUtils.getBrandUserEntity();
        int result = 0;
        for (Integer id : ids) {
            GoodsEntity goodsEntity = queryObject(id);
            goodsEntity.setIsDelete(0);
            goodsEntity.setIsOnSale(1);
            goodsEntity.setUpdateUserId(user.getUserId());
            goodsEntity.setUpdateTime(new Date());
            result += goodsDao.update(goodsEntity);
        }
        return result;
    }

    @Override
    public int enSale(Integer id) {
        BrandUserEntity user = ShiroUtils.getBrandUserEntity();
        GoodsEntity goodsEntity = queryObject(id);
        if (1 == goodsEntity.getIsOnSale()) {
            throw new RRException("此商品已处于上架状态！");
        }
        goodsEntity.setIsOnSale(1);
        goodsEntity.setUpdateUserId(user.getUserId());
        goodsEntity.setUpdateTime(new Date());
        return goodsDao.update(goodsEntity);
    }

    @Override
    public int unSale(Integer id) {
        BrandUserEntity user = ShiroUtils.getBrandUserEntity();
        GoodsEntity goodsEntity = queryObject(id);
        if (0 == goodsEntity.getIsOnSale()) {
            throw new RRException("此商品已处于下架状态！");
        }
        goodsEntity.setIsOnSale(0);
        goodsEntity.setUpdateUserId(user.getUserId());
        goodsEntity.setUpdateTime(new Date());
        return goodsDao.update(goodsEntity);
    }
}

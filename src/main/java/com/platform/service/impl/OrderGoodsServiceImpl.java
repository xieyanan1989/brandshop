package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.OrderGoodsDao;
import com.platform.dao.ShippingDao;
import com.platform.entity.OrderEntity;
import com.platform.entity.OrderGoodsEntity;
import com.platform.entity.ShippingEntity;
import com.platform.service.OrderGoodsService;
import com.platform.utils.RRException;

/**
 * 订单商品表Service实现类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2019-07-03 09:36:51
 */
@Service("orderGoodsService")
public class OrderGoodsServiceImpl implements OrderGoodsService {
    @Autowired
    private OrderGoodsDao orderGoodsDao;
    @Autowired
    private ShippingDao shippingDao;

    @Override
    public OrderGoodsEntity queryObject(Integer id) {
        return orderGoodsDao.queryObject(id);
    }

    @Override
    public List<OrderGoodsEntity> queryList(Map<String, Object> map) {
        return orderGoodsDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return orderGoodsDao.queryTotal(map);
    }

    @Override
    public int save(OrderGoodsEntity orderGoods) {
        return orderGoodsDao.save(orderGoods);
    }

    @Override
    public int update(OrderGoodsEntity orderGoods) {
        return orderGoodsDao.update(orderGoods);
    }

    @Override
    public int delete(Integer id) {
        return orderGoodsDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return orderGoodsDao.deleteBatch(ids);
    }

	@Override
	public int confirm(Integer id) {
		// TODO Auto-generated method stub
		OrderGoodsEntity orderGoodsEntity = queryObject(id);
		Integer shippingStatus = orderGoodsEntity.getShippingStatus();//发货状态
		Integer payStatus = orderGoodsEntity.getPayStatus();//付款状态
		if (2 != payStatus) {
			throw new RRException("此订单未付款，不能确认收货！");
		}
		if (4 == shippingStatus) {
			throw new RRException("此订单处于退货状态，不能确认收货！");
		}
		if (0 == shippingStatus) {
			throw new RRException("此订单未发货，不能确认收货！");
		}
		orderGoodsEntity.setShippingStatus(2);
		orderGoodsEntity.setOrderStatus(301);
		return orderGoodsDao.update(orderGoodsEntity);
	}

	@Override
	public int sendGoods(OrderGoodsEntity orderGoodsEntity) {
		Integer payStatus = orderGoodsEntity.getPayStatus();//付款状态
        if (2 != payStatus) {
            throw new RRException("此订单未付款！");
        }

        ShippingEntity shippingEntity = shippingDao.queryObject(orderGoodsEntity.getShippingId());
        if (null != shippingEntity) {
        	orderGoodsEntity.setShippingName(shippingEntity.getName());
        }
        orderGoodsEntity.setOrderStatus(300);//订单已发货
        orderGoodsEntity.setShippingStatus(1);//已发货
        return orderGoodsDao.update(orderGoodsEntity);
	}
}

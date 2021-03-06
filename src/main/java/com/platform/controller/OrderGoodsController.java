package com.platform.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.entity.OrderEntity;
import com.platform.entity.OrderGoodsEntity;
import com.platform.service.OrderGoodsService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 订单商品表Controller
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2019-07-05 10:26:26
 */
@RestController
@RequestMapping("ordergoods")
public class OrderGoodsController extends BrandAbstractController{
    @Autowired
    private OrderGoodsService orderGoodsService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ordergoods:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
    	params.put("brandId", getBrandId());
        Query query = new Query(params);

        List<OrderGoodsEntity> orderGoodsList = orderGoodsService.queryList(query);
        int total = orderGoodsService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(orderGoodsList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ordergoods:info")
    public R info(@PathVariable("id") Integer id) {
        OrderGoodsEntity orderGood = orderGoodsService.queryObject(id);

        return R.ok().put("orderGood", orderGood);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ordergoods:save")
    public R save(@RequestBody OrderGoodsEntity orderGoods) {
        orderGoodsService.save(orderGoods);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ordergoods:update")
    public R update(@RequestBody OrderGoodsEntity orderGoods) {
        orderGoodsService.update(orderGoods);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ordergoods:delete")
    public R delete(@RequestBody Integer[] ids) {
        orderGoodsService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
    	params.put("brandId", getBrandId());
        List<OrderGoodsEntity> list = orderGoodsService.queryList(params);

        return R.ok().put("list", list);
    }
    
    /**
     * 确定收货
     *
     * @param id
     * @return
     */
    @RequestMapping("/confirm")
    @RequiresPermissions("ordergoods:confirm")
    public R confirm(@RequestBody Integer id) {
    	orderGoodsService.confirm(id);

        return R.ok();
    }

    /**
     * 发货
     *
     * @param order
     * @return
     */
    @RequestMapping("/sendGoods")
    @RequiresPermissions("ordergoods:sendGoods")
    public R sendGoods(@RequestBody OrderGoodsEntity order) {
    	orderGoodsService.sendGoods(order);

        return R.ok();
    }
}

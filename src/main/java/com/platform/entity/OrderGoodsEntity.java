package com.platform.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单商品表实体
 * 表名 nideshop_order_goods
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2019-07-05 10:26:26
 */
public class OrderGoodsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //订单Id
    private Integer orderId;
    //商品id
    private Integer goodsId;
    //商品名称
    private String goodsName;
    //商品序列号
    private String goodsSn;
    //产品Id
    private Integer productId;
    //商品数量
    private Integer number;
    //市场价
    private BigDecimal marketPrice;
    //零售价格
    private BigDecimal retailPrice;
    //商品规格详情
    private String goodsSpecifitionNameValue;
    //虚拟商品
    private Integer isReal;
    //商品规格Ids
    private String goodsSpecifitionIds;
    //图片链接
    private String listPicUrl;
    //品牌ID
    private Integer brandId;
    //0未发货,1已发货,2已收货,4退货
    private Integer shippingStatus;
    //快递公司Id
    private Integer shippingId;
    //快递公司名称
    private String shippingName;
    //快递费用
    private BigDecimal shippingFee;
    //实际需要支付的金额
    private BigDecimal actualPrice;
    //使用的优惠券id
    private Integer couponId;
    //优惠价格
    private BigDecimal couponPrice;
    //快递号
    private String shippingNo;
    //0未付款;1付款中;2已付款
    private Integer payStatus;
    //新增时间
    private Date addTime;
    //1xx 表示订单取消和删除等状态 0订单创建成功等待付款，　101订单已取消，　102订单已删除。
   // 2xx 表示订单支付状态　201订单已付款，等待发货。
    //3xx 表示订单物流相关状态　300订单已发货， 301用户确认收货。
    //4xx 表示订单退换货相关的状态　401 没有发货，退款　402 已收货，退款退货。
    private Integer orderStatus;
    //商品总价
    private BigDecimal goodsPrice;
  //会员Id
    private Integer userId;
    private String userName;
  //确认时间
    private Date confirmTime;
  //支付时间
    private Date payTime;
    //收货人
    private String consignee;
    //国家
    private String country;
    //省
    private String province;
    //地市
    private String city;
    //区县
    private String district;
    //收货地址
    private String address;
    //联系电话
    private String mobile;
    //补充说明
    private String postscript;
    //品牌名称
    private String brandName;
    //订单编号
    private String orderSn;
    /**
     * 设置：主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：主键
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置：订单Id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取：订单Id
     */
    public Integer getOrderId() {
        return orderId;
    }
    /**
     * 设置：商品id
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取：商品id
     */
    public Integer getGoodsId() {
        return goodsId;
    }
    /**
     * 设置：商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取：商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }
    /**
     * 设置：商品序列号
     */
    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    /**
     * 获取：商品序列号
     */
    public String getGoodsSn() {
        return goodsSn;
    }
    /**
     * 设置：产品Id
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取：产品Id
     */
    public Integer getProductId() {
        return productId;
    }
    /**
     * 设置：商品数量
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取：商品数量
     */
    public Integer getNumber() {
        return number;
    }
    /**
     * 设置：市场价
     */
    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * 获取：市场价
     */
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }
    /**
     * 设置：零售价格
     */
    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    /**
     * 获取：零售价格
     */
    public BigDecimal getRetailPrice() {
        return retailPrice;
    }
    /**
     * 设置：商品规格详情
     */
    public void setGoodsSpecifitionNameValue(String goodsSpecifitionNameValue) {
        this.goodsSpecifitionNameValue = goodsSpecifitionNameValue;
    }

    /**
     * 获取：商品规格详情
     */
    public String getGoodsSpecifitionNameValue() {
        return goodsSpecifitionNameValue;
    }
    /**
     * 设置：虚拟商品
     */
    public void setIsReal(Integer isReal) {
        this.isReal = isReal;
    }

    /**
     * 获取：虚拟商品
     */
    public Integer getIsReal() {
        return isReal;
    }
    /**
     * 设置：商品规格Ids
     */
    public void setGoodsSpecifitionIds(String goodsSpecifitionIds) {
        this.goodsSpecifitionIds = goodsSpecifitionIds;
    }

    /**
     * 获取：商品规格Ids
     */
    public String getGoodsSpecifitionIds() {
        return goodsSpecifitionIds;
    }
    /**
     * 设置：图片链接
     */
    public void setListPicUrl(String listPicUrl) {
        this.listPicUrl = listPicUrl;
    }

    /**
     * 获取：图片链接
     */
    public String getListPicUrl() {
        return listPicUrl;
    }
    /**
     * 设置：品牌ID
     */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    /**
     * 获取：品牌ID
     */
    public Integer getBrandId() {
        return brandId;
    }
    /**
     * 设置：0未发货,1已发货,2已收货,4退货
     */
    public void setShippingStatus(Integer shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    /**
     * 获取：0未发货,1已发货,2已收货,4退货
     */
    public Integer getShippingStatus() {
        return shippingStatus;
    }
    /**
     * 设置：快递公司Id
     */
    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }

    /**
     * 获取：快递公司Id
     */
    public Integer getShippingId() {
        return shippingId;
    }
    /**
     * 设置：快递公司名称
     */
    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    /**
     * 获取：快递公司名称
     */
    public String getShippingName() {
        return shippingName;
    }
    /**
     * 设置：快递费用
     */
    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    /**
     * 获取：快递费用
     */
    public BigDecimal getShippingFee() {
        return shippingFee;
    }
    /**
     * 设置：实际需要支付的金额
     */
    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    /**
     * 获取：实际需要支付的金额
     */
    public BigDecimal getActualPrice() {
        return actualPrice;
    }
    /**
     * 设置：使用的优惠券id
     */
    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    /**
     * 获取：使用的优惠券id
     */
    public Integer getCouponId() {
        return couponId;
    }
    /**
     * 设置：优惠价格
     */
    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    /**
     * 获取：优惠价格
     */
    public BigDecimal getCouponPrice() {
        return couponPrice;
    }
    /**
     * 设置：快递号
     */
    public void setShippingNo(String shippingNo) {
        this.shippingNo = shippingNo;
    }

    /**
     * 获取：快递号
     */
    public String getShippingNo() {
        return shippingNo;
    }
    /**
     * 设置：0未付款;1付款中;2已付款
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 获取：0未付款;1付款中;2已付款
     */
    public Integer getPayStatus() {
        return payStatus;
    }
    /**
     * 设置：新增时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取：新增时间
     */
    public Date getAddTime() {
        return addTime;
    }
    /**
     * 设置：1xx 表示订单取消和删除等状态 0订单创建成功等待付款，　101订单已取消，　102订单已删除。
    2xx 表示订单支付状态　201订单已付款，等待发货。
    3xx 表示订单物流相关状态　300订单已发货， 301用户确认收货。
    4xx 表示订单退换货相关的状态　401 没有发货，退款　402 已收货，退款退货。
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取：1xx 表示订单取消和删除等状态 0订单创建成功等待付款，　101订单已取消，　102订单已删除。
    2xx 表示订单支付状态　201订单已付款，等待发货。
    3xx 表示订单物流相关状态　300订单已发货， 301用户确认收货。
    4xx 表示订单退换货相关的状态　401 没有发货，退款　402 已收货，退款退货。
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }
    /**
     * 设置：商品总价
     */
    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    /**
     * 获取：商品总价
     */
    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPostscript() {
		return postscript;
	}

	public void setPostscript(String postscript) {
		this.postscript = postscript;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
    
}

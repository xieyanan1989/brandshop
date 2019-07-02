package com.platform.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.platform.cache.J2CacheUtils;
import com.platform.utils.ResourceUtil;

import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.J2Cache;

/**
 * 微信相关接口
 * 作者: @author yananxie <br>
 * 时间: 2019-06-12 08:58<br>
 * 描述: WechatInterfaceUtil <br>
 */
public class WechatInterfaceUtil {
	 private static Logger logger = LoggerFactory.getLogger(WechatInterfaceUtil.class);

	/**
	 * 接口调用凭证
	 * @return
	 */
    public static JSONObject getAccessToken() {
    	String url = String.format(ResourceUtil.getConfigByName("wx.access_token") ,
                ResourceUtil.getConfigByName("wx.appId"),
                ResourceUtil.getConfigByName("wx.secret"));
        String returndata = HttpUtil.URLGet(url, null, HttpUtil.URL_PARAM_DECODECHARSET_UTF8);
        JSONObject obj =  JSONObject.parseObject(returndata);
        return obj;
    }
    
    /**
	 * 小程序-商家二维码生成
	 * @return
	 */
    public static JSONObject codeUnlimited(String brandId) {
    	Map<String,Object> params = new HashMap<String, Object>();
    	logger.info("access_token:"+J2CacheUtils.get("access_token"));
    	params.put("scene", brandId);
    	logger.info("scene:"+brandId);
    	params.put("page", ResourceUtil.getConfigByName("wx.sp_page"));
    	logger.info("page:"+ResourceUtil.getConfigByName("wx.sp_page"));
    	String url = String.format(ResourceUtil.getConfigByName("wx.getwxacodeunlimit"),J2CacheUtils.get("access_token"));
    	String returndata = HttpUtil.URLPost(url, params, HttpUtil.URL_PARAM_DECODECHARSET_UTF8);
        JSONObject obj =  JSONObject.parseObject(returndata);
        logger.info("小程序-商家二维码生成,返回数据:"+obj.toString());
        return obj;
    }
}
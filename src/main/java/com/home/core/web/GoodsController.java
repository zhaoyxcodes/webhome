/**
 * Project Name:home
 * File Name:GoodsController.java
 * Package Name:com.home.core.web
 * Date:2018-8-21上午10:01:51
 * Copyright (c) 2018, 神州数码 All Rights Reserved.
 *
*/

package com.home.core.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.home.core.service.GoodsService;
import com.system.core.util.HmacUtil;
import com.system.core.util.ResponseValue;

/**
 * ClassName:com.home.core.web.GoodsController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-8-21 上午10:01:51 <br/>
 * @author   yuanxu.zhao
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Controller
@RequestMapping("/home")
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping(value = "/saveGoods", method=RequestMethod.POST)
	@ResponseBody
	public String saveGoods(String title,String describe,String price,String paytype,String distype,
			String time,String submitSKU,String img0,String img1) throws IOException {
		JSONArray sku_attr_list=JSONArray.parseArray(submitSKU);
		if(sku_attr_list.size()<=0||HmacUtil.getStringNull(submitSKU)||HmacUtil.getStringNull(title)||HmacUtil.getStringNull(describe)
				||HmacUtil.getStringNull(paytype)||HmacUtil.getStringNull(distype)||HmacUtil.getStringNull(time)||HmacUtil.getStringNull(img0)
				||HmacUtil.getStringNull(img1)){
			System.out.println("add goods attr is null..");
		}
		 int num=goodsService.saveGoods( title, describe, price, paytype, distype, time, img0, img1, sku_attr_list);
		 if(num>0){
			 return  ResponseValue.IS_SUCCESS;
		 }
		return ResponseValue.IS_ERROR;
	}
	
}


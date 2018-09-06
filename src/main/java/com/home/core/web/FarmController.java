/**
 * Project Name:home
 * File Name:FarmController.java
 * Package Name:com.home.core.web
 * Date:2018-9-4上午11:00:12
 * Copyright (c) 2018, 神州数码 All Rights Reserved.
 *
*/

package com.home.core.web;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.home.core.service.FarmService;
import com.system.core.util.HmacUtil;
import com.system.core.util.ResponseValue;

/**
 * ClassName:com.home.core.web.FarmController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-9-4 上午11:00:12 <br/>
 * @author   yuanxu.zhao
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Controller
@RequestMapping("/home/fram")
public class FarmController {
	@Autowired
	private FarmService farmService;
	
	@RequestMapping(value = "/querybyuser", method=RequestMethod.POST)
	@ResponseBody
	public String queryByUser(String user) throws IOException {
		JSONObject obj=JSONObject.parseObject(user);
		if(HmacUtil.getStringNull(obj.getString(ResponseValue.USER_ID))){
			System.out.println("add goods attr is null..");
		}
		Map<String, Object>  map=farmService.queryByUserId( obj.getString(ResponseValue.USER_ID));
		if(map!=null){
			return JSON.toJSONString(map);
		}
		return ResponseValue.IS_ERROR;
	}
	@RequestMapping(value = "/insert", method=RequestMethod.POST)
	@ResponseBody
	public String insert(String user,String title,String phone,String address,String geom,String img,String disscope) throws IOException {
		JSONObject obj=JSONObject.parseObject(user);
		if(HmacUtil.getStringNull(obj.getString(ResponseValue.USER_ID))){
			System.out.println("add goods attr is null..");
		}
		int num=farmService.insert( obj.getString(ResponseValue.USER_ID), title, phone, address, geom, img, disscope);
		if(num>0){
			return ResponseValue.IS_SUCCESS;
		}
		return ResponseValue.IS_ERROR;
	}
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(String id,String title,String phone,String address,String geom,String img,String disscope) throws IOException {
		if(HmacUtil.getStringNull(id)){
			System.out.println("add goods attr is null..");
		}
		//img 把以前图片删掉
		int num=farmService.update( id, title, phone, address, geom, img, disscope);
		if(num>0){
			return ResponseValue.IS_SUCCESS;
		}
		return ResponseValue.IS_ERROR;
	}
}


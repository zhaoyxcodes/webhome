/**
 * Project Name:home
 * File Name:GoodsController.java
 * Package Name:com.home.core.web
 * Date:2018-8-21上午10:01:51
 * Copyright (c) 2018, 神州数码 All Rights Reserved.
 *
*/

package com.home.core.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.home.core.entity.Goods;
import com.home.core.service.GoodsService;
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
	
	@RequestMapping("/saveGoods")
	@ResponseBody
	public String saveGoods(Goods goods) {
		if(goods!=null){
			 int num=goodsService.saveGoods(goods);
			 if(num>0){
				 return  ResponseValue.IS_SUCCESS;
			 }
		}
		return ResponseValue.IS_ERROR;
	}
	
}


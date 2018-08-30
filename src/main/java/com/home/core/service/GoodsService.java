/**
 * Project Name:home
 * File Name:GoodsService.java
 * Package Name:com.home.core.service
 * Date:2018-8-21上午10:20:35
 * Copyright (c) 2018, 神州数码 All Rights Reserved.
 *
*/

package com.home.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.home.core.dao.JdbcDao;
import com.system.core.util.HmacUtil;

/**
 * ClassName:com.home.core.service.GoodsService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-8-21 上午10:20:35 <br/>
 * @author   yuanxu.zhao
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Component
@Transactional(readOnly=true)
public class GoodsService {

	@Autowired
	private JdbcDao jdbcDao;
	
	@Transactional
	public int saveGoods(String title,String describe,String price,String paytype,String distype,String time,String img0,String img1,JSONArray sku_attr_list){
//		String isql="insert into goods(id,title,type,price,saleprice,number,paytype,distributiontype,describe,imgs,geom,time)values('"+HmacUtil.getUUID()+"',"+
//				"'"+title+"','"+goods.getType()+"','"+goods.getPrice()+"','"+goods.getSaleprice()+"','"+
//				goods.getNumber()+"','"+goods.getPaytype()+"','"+goods.getDistributiontype()+"','"+goods.getDescribe()+
//				"','"+goods.getImgs()+"',"+goods.getGeom()+",'"+goods.getTime()+"')";
//		return jdbcDao.update(isql);
		System.out.println(sku_attr_list.getJSONObject(0).getJSONArray("attrlist").size());
		return 0;
	}
}


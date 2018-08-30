/**
 * Project Name:home
 * File Name:Goods.java
 * Package Name:com.home.core.entity
 * Date:2018-8-21上午10:06:45
 * Copyright (c) 2018, 神州数码 All Rights Reserved.
 *
*/

package com.home.core.entity;

import com.system.core.util.HmacUtil;

/**
 * ClassName:com.home.core.entity.Goods <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-8-21 上午10:06:45 <br/>
 * @author   yuanxu.zhao
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class Goods {

	private String id;
	private String title;//标题
	private String type;//类型
	private String price;//原价
	private String saleprice;//折扣价
	private String number;//库存
	private String paytype;//支付方式
	private String distributiontype;//配送方式
	private String describe;//商品详情
	private String imgs;//图片详情
	private String geom;//位置
	private String time;//折扣天数
	private String imgs2;//图片展示
	
	
	public String getImgs2() {
		return imgs2;
	}
	public void setImgs2(String imgs2) {
		this.imgs2 = imgs2;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(String saleprice) {
		this.saleprice = saleprice;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public String getDistributiontype() {
		return distributiontype;
	}
	public void setDistributiontype(String distributiontype) {
		this.distributiontype = distributiontype;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	public String getGeom() {
		return geom;
	}
	public void setGeom(String geom) {
		String geom2="";
		if(geom!=null&&geom.length()>0){
			geom2=HmacUtil.stringPoitGEO(geom);
		}
		this.geom = geom2;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

}


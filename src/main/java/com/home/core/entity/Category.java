/**
 * Project Name:home
 * File Name:category.java
 * Package Name:com.home.core.entity
 * Date:2018-8-31下午5:24:40
 * Copyright (c) 2018, 神州数码 All Rights Reserved.
 *
*/

package com.home.core.entity;
/**
 * ClassName:com.home.core.entity.category <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-8-31 下午5:24:40 <br/>
 * @author   yuanxu.zhao
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class Category {

	private String  id;
	private String  lmname;
	private String  pare_id;
	private String  ispare;
	private String  sorts;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLmname() {
		return lmname;
	}
	public void setLmname(String lmname) {
		this.lmname = lmname;
	}
	public String getPare_id() {
		return pare_id;
	}
	public void setPare_id(String pare_id) {
		this.pare_id = pare_id;
	}
	public String getIspare() {
		return ispare;
	}
	public void setIspare(String ispare) {
		this.ispare = ispare;
	}
	public String getSorts() {
		return sorts;
	}
	public void setSorts(String sorts) {
		this.sorts = sorts;
	}
	

}


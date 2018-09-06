/**
 * Project Name:home
 * File Name:FarmService.java
 * Package Name:com.home.core.service
 * Date:2018-9-4上午11:01:26
 * Copyright (c) 2018, 神州数码 All Rights Reserved.
 *
*/

package com.home.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.home.core.dao.JdbcDao;
import com.system.core.util.HmacUtil;

/**
 * ClassName:com.home.core.service.FarmService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-9-4 上午11:01:26 <br/>
 * @author   yuanxu.zhao
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Component
@Transactional(readOnly = true)
public class FarmService {

	@Autowired
	private JdbcDao jdbcDao;
	
	public Map<String, Object> queryByUserId(String userid){
		 List<Map<String, Object>> list=jdbcDao.queryForList("select * from Farm where user_id=?", new String[]{userid});
		 if(list!=null&&list.size()>0){
			 return list.get(0);
		 }
		 return null;
	}
	public int insert(String userid,String title,String phone,String address,String geom,String img,String disscope){
		return jdbcDao.update("insert into Farm(id,user_id,title,phone,address,geom,img,disscope)values('"+HmacUtil.getUUID()+"','"+userid+"','"+title+"','"+phone+"','"+address+"',"+HmacUtil.stringPoitGEO(geom)+",'"+img+"','"+disscope+"')");
	}
	public int update(String id,String title,String phone,String address,String geom,String img,String disscope){
		return jdbcDao.update("update Farm set title='"+title+"',disscope='"+disscope+"',phone='"+phone+"',address='"+address+"',geom="+HmacUtil.stringPoitGEO(geom)+",img='"+img+"' where id='"+id+"' ");
	}
}


/**
 * Project Name:home
 * File Name:GoodsDao.java
 * Package Name:com.home.core.dao
 * Date:2018-8-21上午10:21:09
 * Copyright (c) 2018, 神州数码 All Rights Reserved.
 *
*/

package com.home.core.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassName:com.home.core.dao.GoodsDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-8-21 上午10:21:09 <br/>
 * @author   yuanxu.zhao
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Component
public class GoodsDao {

	private static final Logger logger = LoggerFactory.getLogger(GoodsDao.class);
	@Autowired
	private JdbcDao jdbcDao;
}


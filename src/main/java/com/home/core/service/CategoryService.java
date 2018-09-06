/**
 * Project Name:home
 * File Name:CategoryService.java
 * Package Name:com.home.core.service
 * Date:2018-9-4上午11:39:00
 * Copyright (c) 2018, 神州数码 All Rights Reserved.
 *
 */

package com.home.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.home.core.dao.JdbcDao;
import com.home.core.entity.Category;
import com.system.core.util.ResponseValue;

/**
 * ClassName:com.home.core.service.CategoryService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018-9-4 上午11:39:00 <br/>
 * 
 * @author yuanxu.zhao
 * @version
 * @since JDK 1.7
 * @see
 */
@Component
@Transactional(readOnly = true)
public class CategoryService {
	@Autowired
	private JdbcDao jdbcDao;

	// 获取分类
	public String queryCategory() {
		try {
			//加入缓存
			String sql = "select * from category order by sorts";
			List plist = jdbcDao.queryForObjectList(sql, new String[] {},
					Category.class);
			if (plist != null && plist.size() > 0) {
				String cateli = "";
				for (Object object : plist) {
					Category cate = (Category) object;
					cateli = "{id:'" + cate.getId() + "',name:'"
							+ cate.getLmname() + "',sort:'" + cate.getSorts()
							+ "',pareid:'" + cate.getPare_id() + "'},";
				}
				if (cateli.length() > 0) {
					cateli = cateli.substring(0, cateli.length() - 1);
				}
				return "{data:[" + cateli + "]}";
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ResponseValue.IS_ERROR;
	}
}

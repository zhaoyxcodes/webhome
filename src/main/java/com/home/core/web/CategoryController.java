/**
 * Project Name:home
 * File Name:CategoryController.java
 * Package Name:com.home.core.web
 * Date:2018-9-4上午11:38:48
 * Copyright (c) 2018, 神州数码 All Rights Reserved.
 *
*/

package com.home.core.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.home.core.service.CategoryService;

/**
 * ClassName:com.home.core.web.CategoryController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-9-4 上午11:38:48 <br/>
 * @author   yuanxu.zhao
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/query", method=RequestMethod.POST)
	@ResponseBody
	public String query() throws IOException {
		return categoryService.queryCategory();
	}

}


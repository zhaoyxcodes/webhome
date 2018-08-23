/**
 * Project Name:home
 * File Name:FileUploadController.java
 * Package Name:com.home.core.web
 * Date:2018-8-21下午5:36:34
 * Copyright (c) 2018, 神州数码 All Rights Reserved.
 *
 */

package com.home.core.web;

import java.io.File;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.home.core.service.FileService;
import com.system.core.util.HmacUtil;
import com.system.core.util.ResponseValue;

/**
 * ClassName:com.home.core.web.FileUploadController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018-8-21 下午5:36:34 <br/>
 * 
 * @author yuanxu.zhao
 * @version
 * @since JDK 1.7
 * @see
 */
@Controller
@RequestMapping("/file")
public class FileController {

	@Autowired
	private FileService fileService;
	@RequestMapping(value = "/upload", method=RequestMethod.POST)
	@ResponseBody
	public String upload(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file)
			throws Exception { 
		String dir=request.getParameter("dir");
		if(dir!=null&&dir.length()>0){
			try{
				String dirfile=dir+"/"+HmacUtil.getUUID()+".jpg";
				File targetFile = new File(fileService+dirfile);
				if (!targetFile.exists()) {
					boolean succ = targetFile.mkdirs();
				}
				file.transferTo(targetFile);
				return dirfile;
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return ResponseValue.IS_ERROR;
	}
	@RequestMapping(value = "/upload2", method=RequestMethod.GET)
	@ResponseBody
	public String upload2(MultipartHttpServletRequest request)
			throws Exception {
		Iterator<String> itr = request.getFileNames();
		MultipartFile file = request.getFile(itr.next());
		String dir=request.getParameter("dir");
		if(dir!=null&&dir.length()>0){
			try{
				String dirfile=dir+"/"+HmacUtil.getUUID()+".jpg";
				File targetFile = new File(fileService+dirfile);
				if (!targetFile.exists()) {
					boolean succ = targetFile.mkdirs();
				}
				file.transferTo(targetFile);
				return dirfile;
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return ResponseValue.IS_ERROR;
	}
	
	@RequestMapping(value = "/upload3", method=RequestMethod.GET)
	@ResponseBody
	public String upload2(HttpServletRequest request)
			throws Exception {
		
		return ResponseValue.IS_ERROR;
	}
}

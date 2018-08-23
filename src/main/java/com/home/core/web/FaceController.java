/**
 * Project Name:myHome Maven Webapp
 * File Name:Face.java
 * Package Name:com.myhome.core.web
 * Date:2018-2-2上午11:13:24
 * Copyright (c) 2018, 神州数码 All Rights Reserved.
 *
*/

package com.home.core.web;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.system.core.util.FaceValidate;

/**
 * ClassName:com.myhome.core.web.Face <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-2-2 上午11:13:24 <br/>
 * @author   yuanxu.zhao
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Controller
@RequestMapping("/face/info")
public class FaceController {

	@Autowired(required=false)
	private FaceValidate faceValidate;
	@RequestMapping("/list")
	public ModelAndView findList(Integer moudleId,String type, HttpServletRequest request) throws Exception {
	
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/process/processFlowleft");
		
		return mav;
	}
	//上传我的照片
	@RequestMapping("/facefile")
	@ResponseBody
	public   String faceFileUpload(MultipartHttpServletRequest request)  throws Exception{
		String userid=String.valueOf(new Random().nextInt(100) +1);
		Iterator<String> itr =  request.getFileNames();
		MultipartFile file = request.getFile(itr.next());
		File targetFile = new File(faceValidate.getImg_dirpath()+"/"+faceValidate.originalImg,userid+"_face.jpg");  
	    if(!targetFile.exists()){ 
	        boolean succ=targetFile.mkdirs(); 
	    }
	    file.transferTo(targetFile);
	    //保存检测脸部照片
	    String dqimg=faceValidate.setOtherPeopleFace(faceValidate.getImg_dirpath()+"/"+faceValidate.originalImg+"/"+userid+"_face.jpg"
	    		,faceValidate.getImg_dirpath()+"/"+faceValidate.faceDir+"/"+userid+"_face.jpg");
	    return dqimg;
	}
	/**
	 * validateFaceFileUpload:(拍照验证). <br/>
	 * @author yuanxu.zhao
	 * @param request
	 * @return 校验正确的原始图片地址
	 * @throws Exception
	 * @since JDK 1.7
	 */
	@RequestMapping("/validatefacefile")
	@ResponseBody
	public   List<String> validateFaceFileUpload(MultipartHttpServletRequest request)  throws Exception{
		Iterator<String> itr =  request.getFileNames();
		MultipartFile file = request.getFile(itr.next());
		File targetFile = new File(faceValidate.getImg_dirpath()+"/"+faceValidate.validateDir, "validate.jpg");  
		file.transferTo(targetFile);
	    //保存检测脸部照片
	    String dqimg=faceValidate.setOtherPeopleFace(faceValidate.getImg_dirpath()+"/"+faceValidate.validateDir+"/"+"validate.jpg",faceValidate.getImg_dirpath()+"/"+faceValidate.validateDir+"/"+"validate_face.jpg");
	    return  faceValidate.getIsReadFace(dqimg);
//	    if("true".equals(str.trim())){
//	    	FileInputStream inputStream = new FileInputStream(new File(FaceValidate.img_dirpath,userid+"_face.jpg") );
//			 byte[] data = new byte[inputStream.available()];
//	    	 inputStream.read(data);
//	         inputStream.close();
//	         BASE64Encoder encoder = new BASE64Encoder();
//	         return encoder.encode(data);
//	    }
//	    return str;
	}
	@RequestMapping("/getImage")
	@ResponseBody
	public   void getImage(HttpServletRequest request,HttpServletResponse response)  throws Exception{
		String url=request.getParameter("url");
		System.out.println(url);
		FileInputStream inputStream = new FileInputStream(new File(url) );
		
        RenderedImage image = ImageIO.read(inputStream);
        response.setContentType("image/jpeg");//设置后网页中才能显示出图片的后缀
        ImageIO.write(image, "jpg", response.getOutputStream());
        inputStream.close();
	}
	@RequestMapping("/a")
	@ResponseBody
	public   void a(HttpServletRequest request,HttpServletResponse response)  throws Exception{
		request.getSession().invalidate();
	}
}


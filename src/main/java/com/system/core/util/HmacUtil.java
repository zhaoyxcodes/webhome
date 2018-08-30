/**
 * Project Name:myHome
 * File Name:HmacUtil.java
 * Package Name:com.system.core.util
 * Date:2018-8-8下午1:21:02
 * Copyright (c) 2018, 神州数码 All Rights Reserved.
 *
*/

package com.system.core.util;
/**
 * ClassName:com.system.core.util.HmacUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-8-8 下午1:21:02 <br/>
 * @author   yuanxu.zhao
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.servlet.ServletRequest;

import com.alibaba.fastjson.JSONObject;
 
public class HmacUtil {
 
	
    public static String SHA1(String str){  
        try {  
            //指定sha1算法  
            MessageDigest digest = MessageDigest.getInstance("SHA-1");  
            digest.update(str.getBytes());  
            //获取字节数组  
            byte messageDigest[] = digest.digest();  
            // Create Hex String  
            StringBuffer hexString = new StringBuffer();  
            // 字节数组转换为 十六进制 数  
            for (int i = 0; i < messageDigest.length; i++) {  
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);  
                if (shaHex.length() < 2) {  
                    hexString.append(0);  
                }  
                hexString.append(shaHex);  
            }  
            return hexString.toString().toLowerCase();  
  
        } catch (NoSuchAlgorithmException e) {  
           return "";
        }  
    }  
    
    public static String stringPoitGEO(String strpoit){
    	String geom="";
    	if(strpoit!=null&&strpoit.length()>0&&strpoit.indexOf(",")!=-1){
    		String[] laglog=strpoit.split(",");
    		geom="geomfromtext('point("+laglog[1]+" "+laglog[0]+")')";
    	}
    	return geom;
    }
    public static String getUUID(){
    	return UUID.randomUUID().toString().replace("-", "");
    }
    public static boolean getStringNull(Object obj){
    	if(obj==null||obj.toString().length()<=0||obj.toString().isEmpty()){
    		return true;
    	}
    	return false;
    }
    public static void main(String[] args) {
		System.out.println("".isEmpty());
	}
}


/**
 * Project Name:home
 * File Name:GoodsService.java
 * Package Name:com.home.core.service
 * Date:2018-8-21上午10:20:35
 * Copyright (c) 2018, 神州数码 All Rights Reserved.
 *
 */

package com.home.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.home.core.dao.JdbcDao;
import com.home.core.entity.Category;
import com.home.core.entity.Product;
import com.system.core.util.HmacUtil;
import com.system.core.util.ResponseValue;

/**
 * ClassName:com.home.core.service.GoodsService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018-8-21 上午10:20:35 <br/>
 * 
 * @author yuanxu.zhao
 * @version
 * @since JDK 1.7
 * @see
 */
@Component
@Transactional(readOnly = true)
public class ProductService {

	@Autowired
	private JdbcDao jdbcDao;
	
	//获取分类
	public String queryCategory(){
		try{
			String sql="select * from category order by sorts";
			List plist = jdbcDao.queryForObjectList(sql,
					new String[] {}, Category.class);
			if(plist!=null&&plist.size()>0){
				String cateli="";
				for (Object object : plist) {
					Category cate=(Category)object;
					cateli="{id:'"+cate.getId()+"',name:'"+cate.getLmname()+"',sort:'"+cate.getSorts()+"',pareid:'"+cate.getPare_id()+"'},";
				}
				if(cateli.length()>0){
					cateli=cateli.substring(0,cateli.length()-1);
				}
				return "{data:["+cateli+"]}";
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return ResponseValue.IS_ERROR;
	}
	//新增商品
	public int saveProduct(String cid, String title, String describe,
			String price, String paytype, String distype, String time,
			String img0, String img1, JSONArray sku_attr_list,
			JSONArray other_attr_list, String geom) {
		String[] sql = {};
		String pro_id = HmacUtil.getUUID();
		sql[sql.length] = "insert into product(id,title,category_id,price,saleprice,paytype,distributiontype,describe,geom,time)values('"
				+ pro_id
				+ "',"
				+ "'"
				+ title
				+ "','"
				+ cid
				+ "','"
				+ price
				+ "','9-12','"
				+ paytype
				+ "','"
				+ distype
				+ "','"
				+ describe
				+ "'," + HmacUtil.stringPoitGEO(geom) + ",'" + time + "')";
		if (img0.indexOf(",") != -1) {
			String[] img0li = img0.split(",");
			for (String string : img0li) {
				sql[sql.length] = "insert into product_imgs(id,imgurl,product_id,istype)values('"
						+ HmacUtil.getUUID()
						+ "','"
						+ string
						+ "','"
						+ pro_id
						+ "','1')";
			}
		}
		if (img1.indexOf(",") != -1) {
			String[] img1li = img1.split(",");
			for (String string : img1li) {
				sql[sql.length] = "insert into product_imgs(id,imgurl,product_id,istype)values('"
						+ HmacUtil.getUUID()
						+ "','"
						+ string
						+ "','"
						+ pro_id
						+ "','0')";
			}
		}
		// 其他属性
		for (int i = 0; i < other_attr_list.size(); i++) {
			JSONObject attr = other_attr_list.getJSONObject(i);
			String attrid = attr.getString("attrid");
			String attrname = attr.getString("attrname");
			String valname = attr.getString("valname");
			String valid = attr.getString("valid");
			sql[sql.length] = "insert into product_pros(id,product_id,proname_id,provalue_id)values('"
					+ HmacUtil.getUUID()
					+ "','"
					+ pro_id
					+ "','"
					+ attrid
					+ "','" + valid + "')";
		}
		// sku属性
		for (int i = 0; i < sku_attr_list.size(); i++) {
			JSONObject jsobj = sku_attr_list.getJSONObject(i);
			JSONArray attrlist = jsobj.getJSONArray("attrlist");
			int count = jsobj.getInteger("count");
			String saleprice = jsobj.getString("prive");
			String sku_id = HmacUtil.getUUID();
			sql[sql.length] = "insert into sku(id,nums,price,product_id)values('"
					+ sku_id
					+ "',"
					+ count
					+ ",'"
					+ saleprice
					+ "','"
					+ pro_id
					+ "')";
			for (int j = 0; j < attrlist.size(); j++) {
				JSONObject attr = attrlist.getJSONObject(j);
				String attrid = attr.getString("attrid");
				String attrname = attr.getString("attrname");
				String valname = attr.getString("valname");
				String valid = attr.getString("valid");
				sql[sql.length] = "insert into product_pros(id,product_id,proname_id,provalue_id,issku,sku_id)values('"
						+ HmacUtil.getUUID()
						+ "','"
						+ pro_id
						+ "','"
						+ attrid
						+ "','" + valid + "',1,'" + sku_id + "')";
			}
		}

		try {
			jdbcDao.batchUpdate(sql);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	//修改商品信息
	public int updateProduct(String pro_id, String cid, String title,
			String describe, String price, String paytype, String distype,
			String time, JSONArray img, JSONArray sku_attr_list,
			JSONArray other_attr_list, String geom) {
		String[] sql = {};
		sql[sql.length] = "update product set title'" + title
				+ "',category_id='" + cid + "',price='" + price
				+ "',saleprice='9-12',paytype='" + paytype
				+ "',distributiontype='" + distype + "',describe='" + describe
				+ "',geom=" + HmacUtil.stringPoitGEO(geom) + ",time='" + time
				+ "' where id='" + pro_id + "'";

		for (int i = 0; i < img.size(); i++) {// [{img:"",id:"",type:"0",delete:"1"},{}]
			JSONObject imgli = other_attr_list.getJSONObject(i);
			String imgstr = imgli.getString("img");
			String imgid = imgli.getString("id");
			String imgdelete = imgli.getString("delete");
			String imgtype = imgli.getString("type");
			if (imgid == null || imgid.length() <= 0 || "0".equals(imgid)) {
				sql[sql.length] = "insert into product_imgs(id,imgurl,product_id,istype)values('"
						+ HmacUtil.getUUID()
						+ "','"
						+ imgstr
						+ "','"
						+ pro_id
						+ "','" + imgtype + "')";
			} else if ("1".equals(imgdelete)) {
				sql[sql.length] = "delete from  product_imgs where id='"
						+ imgid + "'";
			}

		}
		// 其他属性
		sql[sql.length] = "delete from product_pros where product_id='"
				+ pro_id + "'";
		sql[sql.length] = "delete from sku where product_id='" + pro_id + "'";
		for (int i = 0; i < other_attr_list.size(); i++) {
			JSONObject attr = other_attr_list.getJSONObject(i);
			String attrid = attr.getString("attrid");
			String attrname = attr.getString("attrname");
			String valname = attr.getString("valname");
			String valid = attr.getString("valid");
			sql[sql.length] = "insert into product_pros(id,product_id,proname_id,provalue_id)values('"
					+ HmacUtil.getUUID()
					+ "','"
					+ pro_id
					+ "','"
					+ attrid
					+ "','" + valid + "')";
		}

		// sku属性
		for (int i = 0; i < sku_attr_list.size(); i++) {
			JSONObject jsobj = sku_attr_list.getJSONObject(i);
			JSONArray attrlist = jsobj.getJSONArray("attrlist");
			int count = jsobj.getInteger("count");
			String saleprice = jsobj.getString("prive");
			String sku_id = HmacUtil.getUUID();
			sql[sql.length] = "insert into sku(id,nums,price,product_id)values('"
					+ sku_id
					+ "',"
					+ count
					+ ",'"
					+ saleprice
					+ "','"
					+ pro_id
					+ "')";
			for (int j = 0; j < attrlist.size(); j++) {
				JSONObject attr = attrlist.getJSONObject(j);
				String attrid = attr.getString("attrid");
				String attrname = attr.getString("attrname");
				String valname = attr.getString("valname");
				String valid = attr.getString("valid");
				sql[sql.length] = "insert into product_pros(id,product_id,proname_id,provalue_id,issku,sku_id)values('"
						+ HmacUtil.getUUID()
						+ "','"
						+ pro_id
						+ "','"
						+ attrid
						+ "','" + valid + "',1,'" + sku_id + "')";
			}
		}

		try {
			jdbcDao.batchUpdate(sql);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	//根据商品id查询商品信息
	public String queryProduct(String pro_id) {
		try {
			StringBuffer json = new StringBuffer();
			String sql = "select * from product where id=?";
			List plist = jdbcDao.queryForObjectList(sql,
					new String[] { pro_id }, Product.class);
			if (plist != null && plist.size() > 0) {
				Product p = (Product) plist.get(0);
				json.append("title:'" + p.getTitle() + "',");
				json.append("describe:'" + p.getDescribe() + "',");
				json.append("price:'" + p.getPrice() + "',");
				json.append("paytype:'" + p.getPaytype() + "',");
				json.append("distype:'" + p.getDistributiontype() + "',");
				json.append("time:'" + p.getTime() + "',");
				sql = "select * from product_imgs where product_id='" + pro_id
						+ "' order by createdate";
				List<Map<String, Object>> imglist = jdbcDao.queryForList(sql);
				if (imglist != null && imglist.size() > 0) {
					String imglistjson = "";// [{img:"",id:"",type:"0",delete:"1"},{}]
					for (Map<String, Object> map2 : imglist) {
						String id = map2.get("id".toUpperCase()).toString();
						String imgurl = map2.get("imgurl".toUpperCase())
								.toString();
						String istype = map2.get("istype".toUpperCase())
								.toString();
						imglistjson += "{img:'" + imgurl + "',id:'" + id
								+ "',type:'" + istype + "',delete:''},";
					}
					if (imglistjson.length() > 0) {
						imglistjson = imglistjson.substring(0,
								imglistjson.length() - 1);
					}
					json.append("imglist:[" + imglistjson + "],");
				}

				// sku属性
				sql = "select * from sku where product_id='" + pro_id
						+ "' order by createdate";
				List<Map<String, Object>> skulist = jdbcDao.queryForList(sql);
				String skujosn = "";
				if (skulist != null && skulist.size() > 0) {
					for (Map<String, Object> map2 : skulist) {
						String id = map2.get("id".toUpperCase()).toString();
						String nums = map2.get("nums".toUpperCase()).toString();
						String price = map2.get("price".toUpperCase())
								.toString();
						sql = "select b.id as attrid,b.attrname,c.id as vid,c.value from product_pros a,proname b,provalue c  where a.proname_id=b.id and a.provalue_id=c.id and a.product_id='"
								+ pro_id
								+ "' and a.sku_id='"
								+ id
								+ "' order by a.createdate";
						List<Map<String, Object>> prolist = jdbcDao
								.queryForList(sql);
						String skuattrjson = "";
						for (Map<String, Object> map3 : prolist) {
							String attrid = map3.get("attrid".toUpperCase())
									.toString();
							String attrname = map3
									.get("attrname".toUpperCase()).toString();
							String vid = map3.get("vid".toUpperCase())
									.toString();
							String value = map3.get("value".toUpperCase())
									.toString();
							skuattrjson += "{attrid:'" + attrid
									+ "',attrname:'" + attrname + "',valname:'"
									+ value + "',valid:'" + vid + "'},";
						}
						if (skuattrjson.length() > 0) {
							skuattrjson = skuattrjson.substring(0,
									skuattrjson.length() - 1);
						}
						skujosn += "{prive:'" + price + "',count:'" + nums
								+ "',attrlist:[" + skuattrjson + "]},";
					}
				}
				if (skujosn.length() > 0) {
					skujosn = skujosn.substring(0, skujosn.length() - 1);
				}
				json.append("submitSKU:[" + skujosn + "]");
			}
			return "{" + json.toString() + "}";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseValue.IS_ERROR;
	}

}

package com.bypay.service.impl.util;



import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


public class TmallYfDataPackage {
	
	
	public TmallYfDataPackage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	private static JsonConfig jsonConfig = new JsonConfig();
	
	protected static void setUp() {
		// 使Date型转成JsonString时,格式为"yyyy-MM-dd"
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		// 使Timestamp型转成JsonString时,格式为"yyyy-MM-dd HH:mm:ss"
	    jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor());
	}

	/**
	 * 
	 * 功能: 转换成JSON数据格式
	 * author: Manson
	 * 2012-9-11
	 * @param dataMap
	 * @return
	 * @throws JSONException String
	 */
	public static String convertObjectToJson(Map<String, String>  dataMap)  {

		if (dataMap == null) return "";
		setUp();
        JSONObject jsonObj = JSONObject.fromObject(dataMap, jsonConfig);
        String ret = jsonObj.toString();
		return ret;
	}
	
	/**
	 * 
	 * 功能: 根据有序的map返回mac
	 * author: Manson
	 * 2012-9-11
	 * @param dataMap
	 * @return mac
	 * @throws Exception String
	 */
	
	/**
	 * 
	 * 功能: JSON字符串转换为MAP
	 * author: Manson
	 * 2012-9-11
	 * @param s
	 * @return Map
	 */
	public static void  convertJsonToMap(String s, Map mapData){
		
		try {
			JSONObject json=JSONObject.fromObject(s);
			Iterator keys=json.keys();
			String key = null;
			String value = null;
			while(keys.hasNext()){
				key=(String) keys.next();
				value=json.get(key).toString();
//				System.out.println("return  key:"+key+"-value:"+value);
				mapData.put(key, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 功能: JSON字符串转换为MAP
	 * author: Manson
	 * 2012-9-11
	 * @param s
	 * @return Map
	 */
	
	
	/** List->Json */
	public static String convertListToJson(List<?> list)  {

		if (list == null) return "";
		setUp();
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		String ret = jsonArray.toString();
		return ret;
	}
	
	
	
	public static void test (Map<String, String> mmmm){
		
		mmmm.put("typea", null);						//交易类型
		mmmm.put("merchantNo", "2");		//商户号
		mmmm.put("terminalNo", "3");		//终端号
		mmmm.put("tmOrderNo", "4");	//终端流水号
		mmmm.put("trdOrderNo", "5");			//订单号
	}
	
	public static void main(String[] args) {
		Map<String, String> mmmm = new HashMap<String, String>();
		mmmm.put("typea", null);						//交易类型
		mmmm.put("merchantNo", "2");		//商户号
		mmmm.put("terminalNo", "3");		//终端号
		mmmm.put("tmOrderNo", "4");	//终端流水号
		mmmm.put("trdOrderNo", "5");
		TmallYfDataPackage.convertObjectToJson(mmmm);
		
		System.out.println(TmallYfDataPackage.convertObjectToJson(mmmm));
		List<String> strList = new ArrayList<String>();
		strList.add("000000000001");
		strList.add("一号订单");
		strList.add("7000");
		strList.add("");
		strList.add("");
		System.out.println();
		String str = convertListToJson(strList);
		JSONArray json=  JSONArray.fromObject(mmmm);
		
		System.out.println(json.get(0));
//		Map<String, String> dataMapIn = new LinkedHashMap<String, String>();
//		Map<String, String> dataMapOut = new HashMap<String, String>();
//		dataMapIn.put("typea", null);						//交易类型
//		dataMapIn.put("merchantNo", "2");		//商户号
//		dataMapIn.put("terminalNo", "3");		//终端号
//		dataMapIn.put("tmOrderNo", "4");	//终端流水号
//		dataMapIn.put("trdOrderNo", "5");			//订单号
//		
//		dataMapIn.remove("tyddpe");
//		System.out.println(dataMapIn.size());
//		
//		Iterator it = dataMapIn.entrySet().iterator();   
//        while (it.hasNext()) {   
//            Map.Entry e = (Map.Entry) it.next();   
//            System.out.println("Key: " + e.getKey() + "; Value: " + e.getValue());   
//        } 
//		byte[] inputByte = TmallYfDataPackage.convertObjectToJson(dataMapIn).getBytes();
//		System.out.println(TmallYfDataPackage.convertObjectToJson(dataMapIn));
//		for (int i = 0; i < inputByte.length; i++) {
//			System.out.println(inputByte[i]);
//		}
		
	}
}

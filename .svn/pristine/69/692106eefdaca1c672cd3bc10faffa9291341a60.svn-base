package com.bypay.service.impl.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import net.sf.json.JSONObject;

import com.bypay.util.RemoteAccessor;

/**
 * @author zoulin
 * @version 创建时间：Oct 17, 2013 2:52:32 PM
 */

public class HttpTestMain {

	public static final String CODE_SUCCESS = "0";
	
	public static String test(String url,String encode,String [] params){
		try {
			RemoteAccessor ra = new RemoteAccessor();
			return ra.getResponseByPost(url, encode, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean merchantTest(MerchantInfoBean info){
		//同步商户接口测试
		String merchantResponse = test("http://211.155.226.82:8070/channel/565602.tra", "utf-8", getMerchantParams(info));
		System.out.println("同步商户接口测试-result:"+merchantResponse);
		JSONObject merObj = JSONObject.fromObject(merchantResponse);
		HashMap merMap = (HashMap)JSONObject.toBean(merObj,HashMap.class);
		
		if(merObj!=null&&CODE_SUCCESS.equals(merMap.get("CODE"))){
			return true;
		}else{
			return false;
		}
		
	}
	
	public static String formatString(Object obj){
		if(obj==null){
			return "";
		}else{
			return obj.toString().trim();
		}
		
	}
	
	public static String [] getMerchantParams(MerchantInfoBean info){
		String [] param = new String [26];
		param[0] = "merType";//商户类型(2):01个人02公司
		param[1] = formatString(info.getMerType());//"01";
		
		param[2] = "merName";//商户名称(50)
		
		try {
			param[3] = URLEncoder.encode(info.getMerName(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		param[4] = "idNo";//身份证(30):当merType=01时必填
		param[5] = formatString(info.getIdNo());//"411081199405107313";
		
		param[6] = "busLic";//营业执照(50):当merType=02时必填
		param[7] = formatString(info.getBusLic());//"";//"zjfdska0988";
		
		param[8] = "taxCer";//税务登记证(50):当merType=02时必填
		param[9] = formatString(info.getTaxCer());//"";//"654321";
		
		param[10] = "orgCodeCer";//组织机构代码证(50):当merType=02时必填
		param[11] = formatString(info.getOrgCodeCer());//"";//"456321";
		
		param[12] = "bankPermit";//银行开户许可证(50):当merType=02时必填
		param[13] = formatString(info.getBankPermit());//"";//"321456";
		
		param[14] = "bankAcc";//银行结算账号(20)
		param[15] = formatString(info.getBankAcc());//"6225881234564123";
		
		param[16] = "tel";//联系电话(14)
		param[17] = formatString(info.getTel());//"13915565485";
		
		param[18] = "fee";//费率(6)
		param[19] = formatString(info.getFee());//"5";
		
		param[20] = "agentId";//代理商编号(20)
		param[21] = formatString(info.getAgentId());//"12";
		
		param[22] = "agentFee";//代理商费率(6)
		param[23] = formatString(info.getAgentFee());//"3";
		
		param[24] = "merSysId";//商户号(12)
		param[25] = formatString(info.getMerSysId());//"123321456660";
		
		System.out.println("商户参数显示start!!!!!");
		for(int i=0;i<param.length;i++){
			if(i%2==0){
				System.out.print(param[i]+":");
			}else{
				System.out.print(param[i]);
			}
		}
		System.out.println("商户参数显示end!!!!!");
		
		return param;
		
	}

}

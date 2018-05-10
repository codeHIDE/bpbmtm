package com.bypay.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import com.bypay.service.impl.util.TmallYfDataPackage;

/**
 * 捷安数据文件
* @ClassName: JAUtil 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author Eason Jiang 
* @date 2016-9-26 下午2:54:15 
*
 */
public class JAUtil {
	private static final String custId="6000000503";
//	private static final String macKey="fa7ab654a29ce7143a6754e8f615f788";			//测试
	private static final String macKey="8d84b432986c38b6fb26474567b269f8";     //生产
	//信用卡垫资
	private static final String sid="2017020001";     //生产
	private static final String token="xingyifu2017";     //生产
	
	public static void main(String[] args) {
	    advance("201702171451");
//		test2();
	}
	
	public static JSONObject card4(Map<String, String> param){
		String userName = param.get("userName");
		String idNum = param.get("idNum");
		String cardNo = param.get("cardNo");
		String cellNo = param.get("cellNo");
		
		DefaultHttpClient httpClient = new DefaultHttpClient();  
		HttpPost request = new HttpPost("http://api.jieandata.com/vpre/ccmn/verify");  
		try {
				Map<String,String> map = new LinkedHashMap<String, String>();  
				map.put("CARD_ID", cardNo);
				map.put("CERT_ID", idNum);
				map.put("CERT_NAME", userName);
				map.put("MP", cellNo);
				map.put("PROD_ID", "CARD4");
				StringBuffer sb = new StringBuffer();
				
		        String jsonString = TmallYfDataPackage.convertObjectToJson(map);
		        
		        System.out.println("jsonString="+jsonString);
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		        String orderId =sdf.format(new Date());
		        
		        //配置参数
		        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();  
		        nvps.add(new BasicNameValuePair("versionId", "01")); // 参数
		        nvps.add(new BasicNameValuePair("chrSet", "UTF-8")); // 参数
		        nvps.add(new BasicNameValuePair("custId", custId)); // 参数
		        nvps.add(new BasicNameValuePair("ordId", orderId)); // 参数
		        nvps.add(new BasicNameValuePair("transType", "STD_VERI")); // 参数
		        nvps.add(new BasicNameValuePair("busiType", "")); // 参数
		        nvps.add(new BasicNameValuePair("merPriv", "")); // 参数
		        nvps.add(new BasicNameValuePair("retUrl", "")); // 参数
		        nvps.add(new BasicNameValuePair("jsonStr", jsonString)); // 参数
		        
		      //制作签名
		        String checkSignMsg=Md5Util.generateMD5String("01UTF-8"+custId+orderId+"STD_VERI"+jsonString+macKey);
		        System.out.println("key="+checkSignMsg);
		        
		        nvps.add(new BasicNameValuePair("macStr", checkSignMsg)); // 参数
		        UrlEncodedFormEntity params =new UrlEncodedFormEntity(nvps, HTTP.UTF_8);
		        request.setEntity(params);
		        //发送请求
		        HttpResponse response = httpClient.execute(request);  
		        HttpEntity entity = response.getEntity();  
		        InputStream instream = entity.getContent();  
		        BufferedReader in = new BufferedReader(new InputStreamReader(instream,"UTF-8"));  
		        sb = new StringBuffer();  
		        String data = null;  
		        while((data = in.readLine())!=null){  
		            sb.append(data);  
		        }  
		        if(in != null)  
		            in.close(); 
		        System.out.println("return="+sb);
				JSONObject verify = JSONObject.fromObject(Xml2JsonUtil.xml2JSON(sb.toString()));
				JSONObject json = verify.getJSONObject("verify");
				System.out.println(json.optString("respCode").replaceAll("\\[\"|\"\\]", ""));
				System.out.println(json.optString("respDesc").replaceAll("\\[\"|\"\\]", ""));
				JSONObject returnJson = new JSONObject();
				returnJson.put("respCode", json.optString("respCode").replaceAll("\\[\"|\"\\]", ""));
				returnJson.put("respDesc", json.optString("respDesc").replaceAll("\\[\"|\"\\]", ""));
				return returnJson;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @Title:        card2 
	 * @Description:  
	 * @param:        @param param
	 * @param:        @return    
	 * @return:       JSONObject    
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2017-2-7 下午4:43:04
	 */
	public static JSONObject card2(Map<String, String> param){
        String userName = param.get("userName");
//        String idNum = param.get("idNum");
        String cardNo = param.get("cardNo");
//        String cellNo = param.get("cellNo");
        
        DefaultHttpClient httpClient = new DefaultHttpClient();  
        HttpPost request = new HttpPost("http://api.jieandata.com/vpre/ccmn/verify");  
        try {
                Map<String,String> map = new LinkedHashMap<String, String>();  
                map.put("CARD_ID", cardNo);
//                map.put("CERT_ID", idNum);
                map.put("CERT_NAME", userName);
//                map.put("MP", cellNo);
                map.put("PROD_ID", "CARD2N");
                StringBuffer sb = new StringBuffer();
                
                String jsonString = TmallYfDataPackage.convertObjectToJson(map);
                
                System.out.println("jsonString="+jsonString);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                String orderId =sdf.format(new Date());
                
                //配置参数
                List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();  
                nvps.add(new BasicNameValuePair("versionId", "01")); // 参数
                nvps.add(new BasicNameValuePair("chrSet", "UTF-8")); // 参数
                nvps.add(new BasicNameValuePair("custId", custId)); // 参数
                nvps.add(new BasicNameValuePair("ordId", orderId)); // 参数
                nvps.add(new BasicNameValuePair("transType", "STD_VERI")); // 参数
                nvps.add(new BasicNameValuePair("busiType", "")); // 参数
                nvps.add(new BasicNameValuePair("merPriv", "")); // 参数
                nvps.add(new BasicNameValuePair("retUrl", "")); // 参数
                nvps.add(new BasicNameValuePair("jsonStr", jsonString)); // 参数
                
              //制作签名
                String checkSignMsg=Md5Util.generateMD5String("01UTF-8"+custId+orderId+"STD_VERI"+jsonString+macKey);
                System.out.println("key="+checkSignMsg);
                
                nvps.add(new BasicNameValuePair("macStr", checkSignMsg)); // 参数
                UrlEncodedFormEntity params =new UrlEncodedFormEntity(nvps, HTTP.UTF_8);
                request.setEntity(params);
                //发送请求
                HttpResponse response = httpClient.execute(request);  
                HttpEntity entity = response.getEntity();  
                InputStream instream = entity.getContent();  
                BufferedReader in = new BufferedReader(new InputStreamReader(instream,"UTF-8"));  
                sb = new StringBuffer();  
                String data = null;  
                while((data = in.readLine())!=null){  
                    sb.append(data);  
                }  
                if(in != null)  
                    in.close(); 
                System.out.println("return="+sb);
                JSONObject verify = JSONObject.fromObject(Xml2JsonUtil.xml2JSON(sb.toString()));
                JSONObject json = verify.getJSONObject("verify");
                System.out.println(json.optString("respCode").replaceAll("\\[\"|\"\\]", ""));
                System.out.println(json.optString("respDesc").replaceAll("\\[\"|\"\\]", ""));
                JSONObject returnJson = new JSONObject();
                returnJson.put("respCode", json.optString("respCode").replaceAll("\\[\"|\"\\]", ""));
                returnJson.put("respDesc", json.optString("respDesc").replaceAll("\\[\"|\"\\]", ""));
                return returnJson;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    
    
    }
	
	public static JSONObject checkId(Map<String, String> param){
	    String userName = param.get("userName");
        String idNum = param.get("idNum");
        DefaultHttpClient httpClient = new DefaultHttpClient();  
        HttpPost request = new HttpPost("http://api.jieandata.com/vpre/ccmn/verify");  
        try {
                Map<String,String> map = new LinkedHashMap<String, String>();  
                map.put("CERT_ID", idNum);
                map.put("CERT_NAME", userName);
                map.put("PROD_ID", "CERTPHOTO");
                StringBuffer sb = new StringBuffer();
                
                String jsonString = TmallYfDataPackage.convertObjectToJson(map);
                
                System.out.println("jsonString="+jsonString);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                String orderId =sdf.format(new Date());
                
                
                //配置参数
                List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();  
                nvps.add(new BasicNameValuePair("versionId", "01")); // 参数
                nvps.add(new BasicNameValuePair("chrSet", "UTF-8")); // 参数
                nvps.add(new BasicNameValuePair("custId", custId)); // 参数
                nvps.add(new BasicNameValuePair("ordId", orderId)); // 参数
                nvps.add(new BasicNameValuePair("transType", "REPORT")); // 参数
                nvps.add(new BasicNameValuePair("busiType", "")); // 参数
                nvps.add(new BasicNameValuePair("merPriv", "")); // 参数
                nvps.add(new BasicNameValuePair("retUrl", "")); // 参数
                nvps.add(new BasicNameValuePair("jsonStr", jsonString)); // 参数
                
              //制作签名
                String checkSignMsg=Md5Util.generateMD5String("01UTF-8"+custId+orderId+"REPORT"+jsonString+macKey);
                System.out.println("key="+checkSignMsg);
                
                nvps.add(new BasicNameValuePair("macStr", checkSignMsg)); // 参数
                UrlEncodedFormEntity params =new UrlEncodedFormEntity(nvps, HTTP.UTF_8);
                request.setEntity(params);
                //发送请求
                HttpResponse response = httpClient.execute(request);  
                HttpEntity entity = response.getEntity();  
                InputStream instream = entity.getContent();  
                BufferedReader in = new BufferedReader(new InputStreamReader(instream,"UTF-8"));  
                sb = new StringBuffer();  
                String data = null;  
                while((data = in.readLine())!=null){  
                    sb.append(data);  
                }  
                if(in != null)  
                    in.close(); 
                System.out.println("return="+sb);
                JSONObject verify = JSONObject.fromObject(Xml2JsonUtil.xml2JSON(sb.toString()));
                System.out.println(verify.toString());
                JSONObject json = verify.getJSONObject("verify");
                System.out.println(json.optString("respCode").replaceAll("\\[\"|\"\\]", ""));
                System.out.println(json.optString("respDesc").replaceAll("\\[\"|\"\\]", ""));
                return json;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
	}
	
	public static void test(){
		DefaultHttpClient httpClient = new DefaultHttpClient();  
		HttpPost request = new HttpPost("http://api.jieandata.com/vpre/ccmn/verify");  
		try {
				Map<String,String> map = new LinkedHashMap<String, String>();  
				map.put("CARD_ID", "6228480425774050875");
//				map.put("CERT_ID", "320623198805035832");
				map.put("CERT_NAME", "吴海泉");
//				map.put("MP", "18626269633");
				map.put("PROD_ID", "CARD2N");
				StringBuffer sb = new StringBuffer();
				
		        String jsonString = TmallYfDataPackage.convertObjectToJson(map);
		        
		        System.out.println("jsonString="+jsonString);
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		        String orderId =sdf.format(new Date());
		        
		        
		        //配置参数
		        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();  
		        nvps.add(new BasicNameValuePair("versionId", "01")); // 参数
		        nvps.add(new BasicNameValuePair("chrSet", "UTF-8")); // 参数
		        nvps.add(new BasicNameValuePair("custId", custId)); // 参数
		        nvps.add(new BasicNameValuePair("ordId", orderId)); // 参数
		        nvps.add(new BasicNameValuePair("transType", "STD_VERI")); // 参数
		        nvps.add(new BasicNameValuePair("busiType", "")); // 参数
		        nvps.add(new BasicNameValuePair("merPriv", "")); // 参数
		        nvps.add(new BasicNameValuePair("retUrl", "")); // 参数
		        nvps.add(new BasicNameValuePair("jsonStr", jsonString)); // 参数
		        
		      //制作签名
		        String checkSignMsg=Md5Util.generateMD5String("01UTF-8"+custId+orderId+"STD_VERI"+jsonString+macKey);
		        System.out.println("key="+checkSignMsg);
		        
		        nvps.add(new BasicNameValuePair("macStr", checkSignMsg)); // 参数
		        UrlEncodedFormEntity params =new UrlEncodedFormEntity(nvps, HTTP.UTF_8);
		        request.setEntity(params);
		        //发送请求
		        HttpResponse response = httpClient.execute(request);  
		        HttpEntity entity = response.getEntity();  
		        InputStream instream = entity.getContent();  
		        BufferedReader in = new BufferedReader(new InputStreamReader(instream,"UTF-8"));  
		        sb = new StringBuffer();  
		        String data = null;  
		        while((data = in.readLine())!=null){  
		            sb.append(data);  
		        }  
		        if(in != null)  
		            in.close(); 
		        System.out.println("return="+sb);
				JSONObject verify = JSONObject.fromObject(Xml2JsonUtil.xml2JSON(sb.toString()));
				System.out.println(verify.toString());
				JSONObject json = verify.getJSONObject("verify");
				System.out.println(json.optString("respCode").replaceAll("\\[\"|\"\\]", ""));
				System.out.println(json.optString("respDesc").replaceAll("\\[\"|\"\\]", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public static void test2(){

		DefaultHttpClient httpClient = new DefaultHttpClient();  
		HttpPost request = new HttpPost("http://api.jieandata.com/vpre/ccmn/verify");  
		try {
				Map<String,String> map = new LinkedHashMap<String, String>();  
				map.put("CERT_ID", "320623198805035832");
				map.put("CERT_NAME", "吴海泉");
				map.put("PROD_ID", "CERT");
				StringBuffer sb = new StringBuffer();
				
		        String jsonString = TmallYfDataPackage.convertObjectToJson(map);
		        
		        System.out.println("jsonString="+jsonString);
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		        String orderId =sdf.format(new Date());
		        
		        
		        //配置参数
		        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();  
		        nvps.add(new BasicNameValuePair("versionId", "01")); // 参数
		        nvps.add(new BasicNameValuePair("chrSet", "UTF-8")); // 参数
		        nvps.add(new BasicNameValuePair("custId", custId)); // 参数
		        nvps.add(new BasicNameValuePair("ordId", orderId)); // 参数
		        nvps.add(new BasicNameValuePair("transType", "STD_VERI")); // 参数
		        nvps.add(new BasicNameValuePair("busiType", "")); // 参数
		        nvps.add(new BasicNameValuePair("merPriv", "")); // 参数
		        nvps.add(new BasicNameValuePair("retUrl", "")); // 参数
		        nvps.add(new BasicNameValuePair("jsonStr", jsonString)); // 参数
		        
		      //制作签名
		        String checkSignMsg=Md5Util.generateMD5String("01UTF-8"+custId+orderId+"STD_VERI"+jsonString+macKey);
		        System.out.println("key="+checkSignMsg);
		        
		        nvps.add(new BasicNameValuePair("macStr", checkSignMsg)); // 参数
		        UrlEncodedFormEntity params =new UrlEncodedFormEntity(nvps, HTTP.UTF_8);
		        request.setEntity(params);
		        //发送请求
		        HttpResponse response = httpClient.execute(request);  
		        HttpEntity entity = response.getEntity();  
		        InputStream instream = entity.getContent();  
		        BufferedReader in = new BufferedReader(new InputStreamReader(instream,"UTF-8"));  
		        sb = new StringBuffer();  
		        String data = null;  
		        while((data = in.readLine())!=null){  
		            sb.append(data);  
		        }  
		        if(in != null)  
		            in.close(); 
		        System.out.println("return="+sb);
				JSONObject verify = JSONObject.fromObject(Xml2JsonUtil.xml2JSON(sb.toString()));
				System.out.println(verify.toString());
				JSONObject json = verify.getJSONObject("verify");
				System.out.println(json.optString("respCode").replaceAll("\\[\"|\"\\]", ""));
				System.out.println(json.optString("respDesc").replaceAll("\\[\"|\"\\]", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	    /**
	     * 垫资接口
	     * @Title:        test3 
	     * @Description:  
	     * @param:        @param suid    
	     * @return:       void    
	     * @throws 
	     * @author        Eason Jiang
	     * @Date          2017-2-20 下午12:43:50
	     */
    	public static void advance(String suid){

            DefaultHttpClient httpClient = new DefaultHttpClient();  
            StringBuffer sb = new StringBuffer();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String stimestamp = sdf.format(new Date());
            String stoken = Md5Util.generateMD5StringLow(sid+stimestamp+token);
            System.out.println(sid);
            System.out.println(stimestamp);
            System.out.println(stoken);
            String url="http://jieandata.cn/api/go/V1?sid="+sid+"&suid="+suid+"&stimestamp="+stimestamp+"&stoken="+stoken;
            System.out.println(url);
            HttpGet request = new HttpGet(url);  
            try {
                  HttpResponse response = httpClient.execute(request);  
                    HttpEntity entity = response.getEntity();  
                    InputStream instream = entity.getContent();  
                    BufferedReader in = new BufferedReader(new InputStreamReader(instream,"GBK"));  
                    sb = new StringBuffer();  
                    String data = null;  
                    while((data = in.readLine())!=null){  
                        sb.append(data);  
                    }  
                    if(in != null)  
                        in.close(); 
                    System.out.println(sb.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        
    	}
}

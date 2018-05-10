package com.bypay.util;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
/**
 * 亿美接口类
* @ClassName: YMInterfaceUtil 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author Eason Jiang 
* @date 2016-6-12 下午2:04:16 
*
 */
public class YMInterfaceUtil {

	private static final String serialNumber = "9SDK-EMY-0999-JERSK";
	private static final String key = "829427";
	
	public static  void doInterface() {
//	String url ="http://opensdk.emay.cn:9080/HD_GetAccess_Token.asmx"; // 在浏览器中打开url，可以找到
	String url ="http://opensdk.emay.cn:9080/HD_ZX/HD_OperatorService.asmx"; // 身份证
	String namespace = "http://tempuri.org/"; // Action路径 
//	String op = "GetACCESS_TOKEN"; // 要调用的方法名
	String op = "GetCardPhoto"; // 要调用的方法名
	Service service = new Service();
	try {
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(new java.net.URL(url)); 
		call.setUseSOAPAction(true);
		// action uri
		call.setSOAPActionURI(namespace + op);
		// 设置要调用哪个方法
		call.setOperationName(new QName(namespace, op));
		// 设置参数名称，具体参照从浏览器中看到的
//		call.addParameter(new QName(namespace, "AppID"),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
//		call.addParameter(new QName(namespace, "AppSecret"),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
//		call.addParameter(new QName(namespace, "Key"),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
		call.addParameter(new QName(namespace, "name"),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
		call.addParameter(new QName(namespace, "idcode "),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
		call.addParameter(new QName(namespace, "ACCESS_TOKEN"),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
//		call.setReturnType(new QName(namespace, op), Vector.class); // 入参：对应theIpAddress 
		call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_STRING);
//		Object[] params = new Object[] {"AF0CEF68WE3E7W4564W99D5W53A3960C57B4","C9D5FC45LA492L424CL8CD6LC07231466BBE","459EEE5BH50A5H4862H8B8BHC7AA9AC3AD29"}; // 调用方法并传递参数
		Object[] params = new Object[] {"吴海泉","320623198805035832","D9B53AB87DD342C78E163C21974C746C69F32CC8FFCCE50C2B3850A29BF28D75E3EB8F53F6DC4F439F27D5AD87431ABA"}; // 调用方法并传递参数
		System.out.println("参数发送完毕!");
		String v = (String) call.invoke(params); 
		System.out.println("获取信息");
		System.out.println(v.toString());
		JSONObject json = JSONObject.fromObject(Xml2JsonUtil.xml2JSON(v));
		System.out.println(json.toString());
		JSONArray jj = json.getJSONObject("response").getJSONArray("result");
		String info = jj.getString(0);
		System.out.println(info);
		JSONObject infoJson = JSONObject.fromObject(info);
		System.out.println(infoJson);
		System.out.println(infoJson.size());
		String infos = infoJson.getString("info");
		System.out.println(infos);
		infos = infos.replaceAll("\\[", "").replaceAll("\\]", "");
		JSONObject obj = JSONObject.fromObject(infos);
		System.out.println(obj.get("message"));
		System.out.println(obj.get("result"));
		System.out.println(obj.get("idcardphoto2"));
//		System.out.println(jj.getJSONArray("info"));
//		json.getJSONObject("response").getJSONObject("result");
		
//		for (int i = 0; i < v.size(); i++) 
//		{ 
//		System.out.println(v.get(i)); 
//		} 
	} 
	catch (Exception ex) 
	{ 
	ex.printStackTrace(); 
	} 
	}
	
	public static void makePic(String picBuffer,String filePath){
		ByteArrayInputStream bais = null;
		BufferedImage bi1  =  null;
		try {
			byte[] bytes1 = Base64.decodeBase64(picBuffer);
			bais = new ByteArrayInputStream(bytes1);
			bi1 = ImageIO.read(bais);
			File pic = new File(filePath);
			ImageIO.write(bi1, "jpg", pic);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * 获取TOKEN方法
	 * @Title:        getToken 
	 * @Description:  
	 * @param:        @return    
	 * @return:       Map<String,String>    
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2016-6-12 下午4:54:09
	 */
	public static JSONObject getToken(){
		String url ="http://opensdk.emay.cn:9080/HD_GetAccess_Token.asmx"; // 在浏览器中打开url，可以找到
		String namespace = "http://tempuri.org/"; // Action路径 
		String op = "GetACCESS_TOKEN"; // 要调用的方法名
		Service service = new Service();
		try {
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(url)); 
			call.setUseSOAPAction(true);
			// action uri
			call.setSOAPActionURI(namespace + op);
			// 设置要调用哪个方法
			call.setOperationName(new QName(namespace, op));
			// 设置参数名称，具体参照从浏览器中看到的
			call.addParameter(new QName(namespace, "AppID"),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
			call.addParameter(new QName(namespace, "AppSecret"),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
			call.addParameter(new QName(namespace, "Key"),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
			call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_STRING);
			Object[] params = new Object[] {"AF0CEF68WE3E7W4564W99D5W53A3960C57B4","C9D5FC45LA492L424CL8CD6LC07231466BBE","459EEE5BH50A5H4862H8B8BHC7AA9AC3AD29"}; // 调用方法并传递参数
			System.out.println("参数发送完毕!");
			String v = (String) call.invoke(params); 
			System.out.println("获取信息");
			System.out.println(v.toString());
			JSONObject json = JSONObject.fromObject(v);
			
			return json;
		} 
		catch (Exception ex) 
		{ 
			ex.printStackTrace(); 
			return null;
		} 
	}
	
	public static JSONObject getToken2(){
		String url ="http://opensdk.emay.cn:9080/HD_GetAccess_Token.asmx"; // 在浏览器中打开url，可以找到
		String namespace = "http://tempuri.org/"; // Action路径 
		String op = "GetACCESS_TOKEN"; // 要调用的方法名
		Service service = new Service();
		try {
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(url)); 
			call.setUseSOAPAction(true);
			// action uri
			call.setSOAPActionURI(namespace + op);
			// 设置要调用哪个方法
			call.setOperationName(new QName(namespace, op));
			// 设置参数名称，具体参照从浏览器中看到的
			call.addParameter(new QName(namespace, "AppID"),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
			call.addParameter(new QName(namespace, "AppSecret"),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
			call.addParameter(new QName(namespace, "Key"),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
			call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_STRING);
			Object[] params = new Object[] {"CB3AC210WE3A5W4C75WAA0BWAAF148FB5142","62C586C8L3691L4499L9EC5L0725BF4486F2","A9AD8378H4523H40FFH9E05HE08B0274F349"}; // 调用方法并传递参数
			System.out.println("参数发送完毕!");
			String v = (String) call.invoke(params); 
			System.out.println("获取信息");
			System.out.println(v.toString());
			JSONObject json = JSONObject.fromObject(v);
			
			return json;
		} 
		catch (Exception ex) 
		{ 
			ex.printStackTrace(); 
			return null;
		} 
	}
	
	public static JSONObject getIdPic(String name,String idNum,String token){

//		String url ="http://opensdk.emay.cn:9080/HD_ZX/HD_OperatorService.asmx"; // 身份证
		String url ="http://opensdk.emay.cn:9080/SF_YZ_API/SFService.asmx"; // 身份证
		String namespace = "http://tempuri.org/"; // Action路径 
//		String op = "GetCardPhoto"; // 要调用的方法名
		String op = "Get_EMW_GetCardPhoto_RZ"; // 要调用的方法名
		Service service = new Service();
		try {
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(url)); 
			call.setUseSOAPAction(true);
			// action uri
			call.setSOAPActionURI(namespace + op);
			// 设置要调用哪个方法
			call.setOperationName(new QName(namespace, op));
			// 设置参数名称，具体参照从浏览器中看到的
			call.addParameter(new QName(namespace, "name"),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
			call.addParameter(new QName(namespace, "idcard "),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
			call.addParameter(new QName(namespace, "ACCESS_TOKEN"),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
			call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_STRING);
			Object[] params = new Object[] {name,idNum,token}; // 调用方法并传递参数
			System.out.println("发送参数为:"+name+"|"+idNum+"|"+token);
			System.out.println("参数发送完毕!");
			String v = (String) call.invoke(params); 
			System.out.println("获取信息");
			System.out.println(v.toString());
			JSONObject json = JSONObject.fromObject(v);
//			JSONObject json = JSONObject.fromObject(Xml2JsonUtil.xml2JSON(v));
//			JSONArray jj = json.getJSONObject("response").getJSONArray("result");
//			String info = jj.getString(0);
//			JSONObject infoJson = JSONObject.fromObject(info);
//			String infos = infoJson.getString("info");
//			infos = infos.replaceAll("\\[", "").replaceAll("\\]", "");
//			JSONObject obj = JSONObject.fromObject(infos);
//			System.out.println(obj.toString());
//			return obj;
			return json;
		} 
		catch (Exception ex) 
		{ 
			ex.printStackTrace(); 
			return null;
		} 
		
	}
	
	public static JSONObject checkBankCard(String name,String idNum,String cellphone,String cardNo,String token){
		
		String url ="http://opensdk.emay.cn:9080/HD_ZX/HD_OperatorService.asmx"; // 身份证
		String namespace = "http://tempuri.org/"; // Action路径 
		String op = "GetUpsmartUnionpayFactQueryAttribute"; // 要调用的方法名
		Service service = new Service();
		try {
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(url)); 
			call.setUseSOAPAction(true);
			// action uri
			call.setSOAPActionURI(namespace + op);
			// 设置要调用哪个方法
			call.setOperationName(new QName(namespace, op));
			// 设置参数名称，具体参照从浏览器中看到的
			call.addParameter(new QName(namespace, "name"),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
			call.addParameter(new QName(namespace, "cid"),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
			call.addParameter(new QName(namespace, "mobile"),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
			call.addParameter(new QName(namespace, "card"),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
			call.addParameter(new QName(namespace, "type"),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
			call.addParameter(new QName(namespace, "ACCESS_TOKEN"),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
			call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_STRING);
			Object[] params = new Object[] {name,idNum,cellphone,cardNo,"4",token}; // 调用方法并传递参数
			System.out.println("发送参数为:"+name+"|"+idNum+"|"+cellphone+"|"+cardNo+"|"+token);
			System.out.println("参数发送完毕!");
			String v = (String) call.invoke(params); 
			System.out.println("获取信息");
			System.out.println(v.toString());
			JSONObject json = JSONObject.fromObject(Xml2JsonUtil.xml2JSON(v));
			System.out.println(json.toString());
			JSONArray jj = json.getJSONObject("response").getJSONArray("result");
			String info = jj.getString(0);
			JSONObject infoJson = JSONObject.fromObject(info);
			String infos = infoJson.getString("info");
			infos = infos.replaceAll("\\[", "").replaceAll("\\]", "");
			JSONObject obj = JSONObject.fromObject(infos);
			JSONObject resmsg = obj.getJSONObject("bankCardValidate");
			System.out.println(resmsg.toString());
			return resmsg;
		} 
		catch (Exception ex) 
		{ 
			ex.printStackTrace(); 
			return null;
		} 
		
	}
	
public static JSONObject checkBankCard2(String name,String cardNo,String token){
		
		String url ="http://opensdk.emay.cn:9080/HD_ZX/HD_OperatorService.asmx"; // 身份证
		String namespace = "http://tempuri.org/"; // Action路径 
		String op = "GetUpsmartUnionpayFactQueryAttribute"; // 要调用的方法名
		Service service = new Service();
		try {
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(url)); 
			call.setUseSOAPAction(true);
			// action uri
			call.setSOAPActionURI(namespace + op);
			// 设置要调用哪个方法
			call.setOperationName(new QName(namespace, op));
			// 设置参数名称，具体参照从浏览器中看到的
			call.addParameter(new QName(namespace, "name"),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
			call.addParameter(new QName(namespace, "cid"),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
			call.addParameter(new QName(namespace, "mobile"),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
			call.addParameter(new QName(namespace, "card"),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
			call.addParameter(new QName(namespace, "type"),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
			call.addParameter(new QName(namespace, "ACCESS_TOKEN"),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
			call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_STRING);
			Object[] params = new Object[] {name,"","",cardNo,"1",token}; // 调用方法并传递参数
			System.out.println("发送参数为:"+name+"|"+"|"+cardNo+"|"+token);
			System.out.println("参数发送完毕!");
			String v = (String) call.invoke(params); 
			System.out.println("获取信息");
			System.out.println(v.toString());
			JSONObject json = JSONObject.fromObject(Xml2JsonUtil.xml2JSON(v));
			System.out.println(json.toString());
			JSONArray jj = json.getJSONObject("response").getJSONArray("result");
			String info = jj.getString(0);
			JSONObject infoJson = JSONObject.fromObject(info);
			String infos = infoJson.getString("info");
			infos = infos.replaceAll("\\[", "").replaceAll("\\]", "");
			JSONObject obj = JSONObject.fromObject(infos);
			JSONObject resmsg = obj.getJSONObject("bankCardValidate");
			System.out.println(resmsg.toString());
			return resmsg;
		} 
		catch (Exception ex) 
		{ 
			ex.printStackTrace(); 
			return null;
		} 
		
	}

	public static void sendSMS(String mobileNo,String message,int companyCode) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();  
		HttpPost request = new HttpPost("http://sdk999ws.eucp.b2m.cn:8080/sdkproxy/sendsms.action");  
		try {
		        
		        //配置参数
		        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();  
		        nvps.add(new BasicNameValuePair("cdkey", serialNumber)); // 参数
		        nvps.add(new BasicNameValuePair("password", key)); // 参数
		        nvps.add(new BasicNameValuePair("phone", mobileNo)); // 参数
		        nvps.add(new BasicNameValuePair("message", getCompany(companyCode)+message)); // 参数
		        nvps.add(new BasicNameValuePair("addserial", "")); // 参数
		        nvps.add(new BasicNameValuePair("seqid", "")); // 参数
		        nvps.add(new BasicNameValuePair("smspriority", "5")); // 参数
		        UrlEncodedFormEntity params =new UrlEncodedFormEntity(nvps, HTTP.UTF_8);
		        request.setEntity(params);
		        //发送请求
		        HttpResponse response = httpClient.execute(request);  
		        HttpEntity entity = response.getEntity();  
		        InputStream instream = entity.getContent();  
		        BufferedReader in = new BufferedReader(new InputStreamReader(instream,"UTF-8"));  
		        StringBuffer sb = new StringBuffer();  
		        String data = null;  
		        while((data = in.readLine())!=null){  
		            sb.append(data);  
		        }  
		        if(in != null)  
		            in.close(); 
		        System.out.println("return="+sb);
//		        JSONObject json=JSONObject.fromObject(sb.toString());
//		        JSONObject respJson=JSONObject.fromObject(respString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getCompany(int code){
		switch (code) {
		case 1:
			return "【至尊宝】";
		case 2:
			return "【轻松支付】";
		case 3:
			return "【尊享生活】";
		case 4:
			return "【壹商圈】";
		case 5:
			return "【通富天下】";
		case 6:
			return "【速宝】";
		case 7:
			return "【酷支付】";
		case 8:
			return "【卡伴支付】";
		case 9:
			return "【巨禾支付】";
		case 10:
			return "【卡贝支付】";
		case 11:
			return "【淘付宝】";
		case 12:
			return "【手机支付】";
		case 13:
			return "【盛云支付】";
		case 14:
			return "【民典快付】";
		default:
			return "";
		}
	}
	
	public static void main(String args[]) 
	{ 
//		System.out.println(Md5Util.getMD5("111111"));
		
//		try {
//            sendSMS("13816398916","恭喜你获得价值8888元大礼包",1);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//		regist();
//		getToken();
//		checkBankCard("吴海泉","320623198805035832","18626269633","6222523108286568","CE4A84C40F29445F82BF853EBFF5AF671A8D0D17C33085B83831C19C273285D3F9022946B4B743599AEC1DA9C8203A2A");
//		checkBankCard2("吴海泉","6222523108286568","CE4A84C40F29445F82BF853EBFF5AF671A8D0D17C33085B83831C19C273285D3F9022946B4B743599AEC1DA9C8203A2A");
//		test();
//		doInterface();
		makePic("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","d:\\123.jpg");
	}
	
	}
	

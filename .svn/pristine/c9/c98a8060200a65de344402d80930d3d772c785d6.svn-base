/**
 * @ClassName:     BankCardCheckServiceImpl.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 * @author         Eason Jiang
 * @version        V1.0  
 * @Date           2015-7-6 下午3:12:58 
 */
package com.bypay.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bypay.domain.Attr;
import com.bypay.domain.PacketReq;
import com.bypay.domain.PacketRes;
import com.bypay.service.BankCardCheckService;
import com.thoughtworks.xstream.XStream;

/** 
 * @ClassName: BankCardCheckServiceImpl 
 * @Description: TODO(银行卡鉴权接口实现类) 
 * @author Eason Jiang 
 * @date 2015-7-6 下午3:12:58 
 *  
 */
@Service("bankCardCheckService")
public class BankCardCheckServiceImpl implements BankCardCheckService {
	 private final static Logger logger = LoggerFactory.getLogger(BankCardCheckServiceImpl.class);   
	 private String url="http://222.73.85.147:3911";
	 
	public PacketReq makeReq(Map<String, String > map ){
		String userName = map.get("userName");
		String idNum = map.get("idNum");
		String cardNo = map.get("cardNo");
		String cellNo = map.get("cellNo");
		logger.info("userName="+userName);
		logger.info("idNum="+idNum);
		logger.info("cardNo="+cardNo);
		logger.info("cellNo="+cellNo);
		
		PacketReq packetReq = new PacketReq();
		packetReq.setServiceCode(10003);		//服务代码 
		List<Attr> AttrList = new ArrayList<Attr>();		//属性列表
		Attr customerName = new Attr();		//客户名
		customerName.setId(5000);
		customerName.setValueType(1);
		customerName.setStrValue("ruiyin");
		AttrList.add(customerName);
		
		Attr customerCode = new Attr();		//客户代码
		customerCode.setId(5001);
		customerCode.setValueType(1);
		customerCode.setStrValue("ruiyin");			
		AttrList.add(customerCode);
		
		Attr customerPwd = new Attr();		//客户密码
		customerPwd.setId(5002);
		customerPwd.setValueType(1);
		customerPwd.setStrValue("ruiyin@2015");			
		AttrList.add(customerPwd);
		
		Attr name = new Attr();		//姓名
		name.setId(10000);
		name.setValueType(1);
		name.setStrValue(userName);			
		AttrList.add(name);
		
		Attr idNumAttr = new Attr();		//身份证
		idNumAttr.setId(10001);
		idNumAttr.setValueType(1);
		idNumAttr.setStrValue(idNum);			
		AttrList.add(idNumAttr);
		
		Attr cardNoAttr = new Attr();		//银行卡号
		cardNoAttr.setId(10006);
		cardNoAttr.setValueType(1);
		cardNoAttr.setStrValue(cardNo);			
		AttrList.add(cardNoAttr);
		
		Attr cellNoAttr = new Attr();		//手机号
		cellNoAttr.setId(10007);
		cellNoAttr.setValueType(1);
		cellNoAttr.setStrValue(cellNo);			
		AttrList.add(cellNoAttr);
		packetReq.setAttrList(AttrList);
		return packetReq;
	}
	
	public void checkCardPost() {
		DefaultHttpClient httpClient = new DefaultHttpClient();  
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000); //设置超时
		try {
//			String url="http://222.73.85.147:3911";
			HttpPost request = new HttpPost(url);  //localhost:8080 pay.sanwing.com
			//3	银行卡鉴权接口
			PacketReq packetReq = new PacketReq();
			packetReq.setServiceCode(10003);		//服务代码 
			List<Attr> AttrList = new ArrayList<Attr>();		//属性列表
			Attr customerName = new Attr();		//客户名
			customerName.setId(5000);
			customerName.setValueType(1);
			customerName.setStrValue("ruiyin");
			AttrList.add(customerName);
			
			Attr customerCode = new Attr();		//客户代码
			customerCode.setId(5001);
			customerCode.setValueType(1);
			customerCode.setStrValue("ruiyin");			
			AttrList.add(customerCode);
			
			Attr customerPwd = new Attr();		//客户密码
			customerPwd.setId(5002);
			customerPwd.setValueType(1);
			customerPwd.setStrValue("ruiyin@2015");			
			AttrList.add(customerPwd);
			
			Attr name = new Attr();		//姓名
			name.setId(10000);
			name.setValueType(1);
			name.setStrValue("蒋毅承");			
			AttrList.add(name);
			
			Attr idNum = new Attr();		//身份证
			idNum.setId(10001);
			idNum.setValueType(1);
			idNum.setStrValue("310110198712075172");			
			AttrList.add(idNum);
			
			Attr cardNo = new Attr();		//银行卡号
			cardNo.setId(10006);
			cardNo.setValueType(1);
//			cardNo.setStrValue("5527420126062895");			
//			cardNo.setStrValue("6217580800001691919");			
			AttrList.add(cardNo);
			
			Attr cellNo = new Attr();		//手机号
			cellNo.setId(10007);
			cellNo.setValueType(1);
			cellNo.setStrValue("13816398916");			
			AttrList.add(cellNo);
			//封装对象
			packetReq.setAttrList(AttrList);
			XStream xStream = new XStream();  
			//设置节点
//			xStream.alias("ns1:PacketReq", PacketReq.class);
			xStream.alias("AttrList", Attr.class);
			//设置节点的别名
//			xStream.aliasField("AttrList", Attr.class,"");
		    xStream.autodetectAnnotations(true);
		    String xml = xStream.toXML(packetReq);
	        String soapRequestData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
	        "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:ns1=\"http://www.CreditServer.com\"><SOAP-ENV:Body>"+
	   		xml+
	   		"</SOAP-ENV:Body></SOAP-ENV:Envelope>";
	        
	        System.out.println("soapRequestData="+soapRequestData);
	        StringEntity params =new StringEntity(soapRequestData,"UTF-8");  
	        request.addHeader("content-type", "application/soap+xml");  
	        request.setEntity(params);
	        HttpResponse response = httpClient.execute(request);
	        HttpEntity entity = response.getEntity();  
	        InputStream instream = entity.getContent();  
	        BufferedReader in = new BufferedReader(new InputStreamReader(instream,"UTF-8"));  
	        StringBuffer sb = new StringBuffer();  
	        String data = null;  
	        while((data = in.readLine())!=null){  
	            sb.append(data);  
	        }  
	        sb.replace(0, sb.indexOf("<ns6:PacketRes>"), "");
	        sb.replace(sb.indexOf("</ns6:PacketRes>")+"</ns6:PacketRes>".length(), sb.length(), "");
	        xStream.alias("ns6:PacketRes", PacketRes.class);
	        
	        System.out.println("result="+sb);
	        
	        PacketRes packetRes = (PacketRes) xStream.fromXML(sb.toString()); 
	        System.out.println(packetRes.getServiceCode());
	        List<Attr> resList = packetRes.getAttrList();
	        for(Attr attr:resList){
	        	if(attr.getId()==9000){
	        		System.out.println("错误码:"+attr.getIntValue());
	        	}
	        	if(attr.getId()==9001){
	        		System.out.println("错误结果:"+attr.getStrValue());
	        	}
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		BankCardCheckServiceImpl sl = new BankCardCheckServiceImpl();
		sl.checkCardPost();
	}

	@Override
	public Map<String, String> checkBankCard(Map<String, String> param) {
		PacketReq  packetReq = makeReq(param);
		Map<String,String> returnMap = new HashMap<String,String>();
		DefaultHttpClient httpClient = new DefaultHttpClient();  
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000); //设置超时
		try {
			HttpPost request = new HttpPost(url);  
			XStream xStream = new XStream();  
			//设置节点
//			xStream.alias("ns1:PacketReq", PacketReq.class);
			xStream.alias("AttrList", Attr.class);
			//设置节点的别名
//			xStream.aliasField("AttrList", Attr.class,"");
		    xStream.autodetectAnnotations(true);
		    String xml = xStream.toXML(packetReq);
		    String soapRequestData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
			        "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:ns1=\"http://www.CreditServer.com\"><SOAP-ENV:Body>"+
			   		xml+
			   		"</SOAP-ENV:Body></SOAP-ENV:Envelope>";
		    logger.info("soapRequestData="+soapRequestData);
	        StringEntity params =new StringEntity(soapRequestData,"UTF-8");  
	        request.addHeader("content-type", "application/soap+xml");  
	        request.setEntity(params);
	        HttpResponse response = httpClient.execute(request);
	        HttpEntity entity = response.getEntity();  
	        InputStream instream = entity.getContent();  
	        BufferedReader in = new BufferedReader(new InputStreamReader(instream,"UTF-8"));  
	        StringBuffer sb = new StringBuffer();  
	        String data = null;  
	        while((data = in.readLine())!=null){  
	            sb.append(data);  
	        }  
	        logger.info("result="+sb);
	        sb.replace(0, sb.indexOf("<ns6:PacketRes>"), "");
	        sb.replace(sb.indexOf("</ns6:PacketRes>")+"</ns6:PacketRes>".length(), sb.length(), "");
	        xStream.alias("ns6:PacketRes", PacketRes.class);
	        
	        PacketRes packetRes = (PacketRes) xStream.fromXML(sb.toString()); 
	        System.out.println(packetRes.getServiceCode());
	        List<Attr> resList = packetRes.getAttrList();
	        
	        for(Attr attr:resList){
	        	if(attr.getId()==9000){
	        		returnMap.put("respCode", attr.getIntValue()+"");
	        	}
	        	if(attr.getId()==9001){
	        		returnMap.put("respMsg", attr.getStrValue());
	        	}
	        }
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			returnMap.put("respCode", "-1");
			returnMap.put("respMsg", "系统异常");
		}
		return returnMap;
	}

}

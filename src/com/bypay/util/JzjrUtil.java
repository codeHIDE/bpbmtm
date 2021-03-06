package com.bypay.util;
/**
 * 矩阵金融代付类
* @ClassName: JzjrUtil 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author Eason Jiang 
* @date 2016-6-24 上午10:00:16 
*
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.ju.utils.EncryptUtils;

public class JzjrUtil {
	
	//公钥地址
	private static String pubKeyUrl = "/usr/local/New_ProjectFiles/898000000020015_public.key";
//	private static String pubKeyUrl = "D://898000000020015_public.key";
	private static String merId="898000000020015";		//代付商户号
	
	public static JSONObject payJz(String orderId,String transTime,String cardNo,String userName,String bankName,String payMoney){
		String signature;
		JSONObject json = null;
		JzjrPost post = new JzjrPost();
		Map<String, String> map = new HashMap<String, String>();
		String tradeCode = "500302";
		String zeros="";
		for(int i =0;i<(15-payMoney.length());i++){
			zeros+="0";
		}
		String msgInfo=transTime+"|02|"+cardNo+"|"+userName+"||||"+bankName+"|"+zeros+payMoney+"|瑞银金融";
		System.out.println("send msg="+msgInfo);
		try {
			msgInfo = EncryptUtils.encrypt(msgInfo, pubKeyUrl);
			signature = EncryptUtils.juSignature(merId+orderId+tradeCode+msgInfo, pubKeyUrl);
		} catch (Exception e1) {
			System.out.println("报文拼接失败");
			e1.printStackTrace();
			return json;
		}
		
		map.put("merId", merId);
		map.put("tradeCode", tradeCode);
		map.put("orderId",orderId);	
		map.put("msg", msgInfo);
		map.put("signature", signature);
		
		String responts = "";
		try {
			long a = System.currentTimeMillis();
			responts = post.http("http://www.eidpay.com/pcsp-client-xl/pcsp/transEntryInterface!transEntry.do",map);		//生产
			 String msgs[] = responts.split("\"signature\":\"", -1);
		        String signature1 = msgs[1].split("\"}", -1)[0];
		        String responts1 = responts.replace(signature1, "@eidpay");
		        String signatureStr = EncryptUtils.juSignature(responts1, pubKeyUrl);
		        System.out.println("signatureStr:"+signatureStr);
		if(EncryptUtils.juValidateSignature(responts, pubKeyUrl)){
				System.out.println("返回结果:"+responts);
				json = JSONObject.fromObject(responts);
				System.out.println("耗时"+(System.currentTimeMillis()- a));
			}else{
				System.out.println("校验失败");
				System.out.println("返回结果:"+responts);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
//		System.exit(0);
		return json;
	}
	
	
	
	public static void main(String[] args) {
		
		String signature;
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		JzjrPost post = new JzjrPost();
		Map<String, String> map = new HashMap<String, String>();
        String pubKeyUrl = "D:\\898000000020015_public.key";
        String merId="898000000020015";		//代付商户号
        String orderId = sdf1.format(new Date())+"000000";
		
		//验卡
//		String tradeCode = "500101";
//		String msgInfo=sdf1.format(new Date())+"|xx|6212xxxxx10441522|00|61010xxxxxx070456|1|||13097xxx16";
		//代收
        String tradeCode = "500302";
        String msgInfo=sdf1.format(new Date())+"|02|4367421273610498563|吴海泉||||建设银行|000000000000001|钜真测试";

//		String msgInfo=sdf1.format(new Date())+"|02|6215581111004500898|李奕|01|320623199209076050|102306082007|南通分行业务处理中心|000000000000100|单笔代付";
		//代付 
//		String tradeCode = "500301";
//		String msgInfo=sdf1.format(new Date())+"|000000000000|000000000000200|单笔代付";
		//余额
//		String tradeCode = "500401";
//		String msgInfo=sdf1.format(new Date())+"|01";
		//查询结果
//		String tradeCode = "500501";
//		String msgInfo=sdf1.format(new Date())+"|40150528015802000000|500301";
       
		
		try {
			
			msgInfo = EncryptUtils.encrypt(msgInfo, pubKeyUrl);
			signature = EncryptUtils.juSignature(merId+orderId+tradeCode+msgInfo, pubKeyUrl);
			
		} catch (Exception e1) {
			System.out.println("报文拼接失败");
			e1.printStackTrace();
			return;
		}
		
		map.put("merId", merId);
		map.put("tradeCode", tradeCode);
		map.put("orderId",orderId);	
		map.put("msg", msgInfo);
		map.put("signature", signature);
		
		String responts = "";
		try {
			long a = System.currentTimeMillis();
//			responts = post.http("http://localhost:9901/ju-pcs-client-proxy/pcsp/transEntryInterface!transEntry.do",map);
//			responts = post.http("http://www.eidpay.com/pcsp-client/pcsp/transEntryInterface!transEntry.do",map);
			responts = post.http("http://www.eidpay.com/pcsp-client-xl/pcsp/transEntryInterface!transEntry.do",map);		//生产
//			responts = post.http("http://112.64.159.162:8014/ju-pcs-proxy/pcsp/transEntryInterface!transEntry.do",map);		//测试
//			responts = post.http("http://112.64.159.162:8002/pcsp-client/pcsp/transEntryInterface!transEntry.do",map);
			//获取signature 并通过调用EncryptUtils.juValidateSignature,判断signature是否正确
			 String msgs[] = responts.split("\"signature\":\"", -1);
		        String signature1 = msgs[1].split("\"}", -1)[0];
		        String responts1 = responts.replace(signature1, "@eidpay");
		        String signatureStr = EncryptUtils.juSignature(responts1, pubKeyUrl);
		        System.out.println("signatureStr:"+signatureStr);
		if(EncryptUtils.juValidateSignature(responts, pubKeyUrl)){
				System.out.println(responts);
				System.out.println("耗时"+(System.currentTimeMillis()- a));
			}else{
				System.out.println("校验失败");
				System.out.println(responts);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		System.exit(0);
	
	}
	
	
}

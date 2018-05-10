package com.bypay.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import com.Ostermiller.util.MD5;


public class CommonTool {
	
	/**
	 * 转换卡类型,1:借记卡2:信用卡
	 * @param cardNum
	 * @return
	 */
	public static String transformCardType(String cardType){
		if(cardType.equals("1")){
			return "借记卡";
		}else if(cardType.equals("2")){
			return "信用卡";
		}else{
			return "未知";
		}
	}
	/**
	 * 转换卡号,前3后4中间*
	 * @param cardNum
	 * @return
	 */
	public static String transformCardNumber(String cardNum){
		return cardNum.substring(0,3)+"****"+cardNum.substring(cardNum.length()-4);
	}
	/**
	 * 转换手机号,前3后4中间*
	 * @param cardNum
	 * @return
	 */
	public static String transformMobile(String mobile){
		return mobile.substring(0,3)+"****"+mobile.substring(mobile.length()-4);
	}
	
	/**
	 * 转换身份证号,前3后4中间*
	 * @param idnum
	 * @return
	 */
	public static String transforIdnum(String idnum){
		return idnum.substring(0,3)+"****"+idnum.substring(idnum.length()-4);
	}
	
	
	public static byte[] encode(byte[] data) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md.digest(data);
	}
	/**
	 * 组成元素：设备类型+厂家代码+保留位+设备序号+校验位
		格式：    HEX     HEX      HEX   HEX      HEX  
		值：    
		设备类型（1）：1-刷卡器，2-刷卡手机，3-POS
		厂家代码（3）：01--FF
		设备序号（8）：00000000-FFFFFFFF
		校验位（1）：设备序号和8位1做异或，取后2位，转bit,统计二进制1的个数
	 * @return
	 */
	public static String generatorTsn(String type,String code,String no,int length){
		int index = length;
		index = index - 1 - 2 - 2;
		if(index>=0)length = index;
		code = Integer.toHexString(Integer.parseInt(code));
		if(code.length()<2)code = "0"+code;
		String transformNo = Long.toHexString(Long.parseLong(no));
		if(transformNo.length()<8){
			int num = transformNo.length();
			for (int i = 0; i < 8-num; i++) {
				transformNo = "0"+transformNo;
			}
		}
		String add = "";
		if(no.length()<length){
			for (int i = 0; i < length-no.length(); i++) {
				add += "0";
			}
		}
		transformNo = add + transformNo;
		String basic = "11111111";
		char[] noChar = no.toCharArray();
		char[] basicChar = basic.toCharArray();
		int temp = 0;
		String binaryStr = "";
		for (int i = 0; i < noChar.length; i++) {
			temp = noChar[i]^basicChar[i];
			BigInteger tempBig = new BigInteger(temp+"");
			binaryStr += tempBig.toString(2);
		}
		int verifyIndex = 0;
		char[] binaryChar = binaryStr.toCharArray();
		for (int i = 0; i < binaryChar.length; i++) {
			if(binaryChar[i] == '1')
				verifyIndex ++ ;
		}
		String verify = Integer.toHexString(verifyIndex);
		if(verify.length()<2)verify = "0" + verify;
		
		return (type + code + transformNo + verify).toUpperCase();
	}
	
	public static String generatorUnionTsn(String type,String code,String no){
		if(code.length()<3)code = "9"+code;
		return type + code + no;
	}
	
	public static void main(String[] args) {
		System.out.println(CommonTool.generatorTsn("1", "06", "10100571",20));
	}
	
	
	 public static String getIpAddrByRequest(HttpServletRequest request) { 

	        String ip = request.getHeader("x-forwarded-for"); 

	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 

	            ip = request.getHeader("Proxy-Client-IP"); 

	        } 

	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 

	            ip = request.getHeader("WL-Proxy-Client-IP"); 

	        } 

	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 

	            ip = request.getRemoteAddr(); 

	        } 

	        return ip; 

	    } 

}

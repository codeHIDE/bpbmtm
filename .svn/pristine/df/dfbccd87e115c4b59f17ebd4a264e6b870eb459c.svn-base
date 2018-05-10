package com.bypay.util;

import java.io.IOException;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class TransUtil {

	BASE64Encoder encoder = new BASE64Encoder();
	BASE64Decoder decoder = new BASE64Decoder();
	
	private String desKey = "011111111111111111111110";
	
	public String getDesKey() {
		return this.desKey;
	}
	
	//加密
	public String encrypt(String data) {
		String encryptXml = "";
		try {
			String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCT1aeuD6Mq/xZI3p7KubYbll3ifChlR9OPlLku70m83ODnMzH/O9CoeMY8ID8HwHMph3FAMOfMqZ4nPKSy+3fPjmaa3YSPNgu5a5ReqcvQm4oQKjVeQGdgpe47ZsSM1IEOF6kyoKfDWf0ZFtAAUoEvHuWRS+M4sE5WvlJS8YsVvQIDAQAB";
			String encryptDesKey = RSACoder.encryptByPublicKey(this.desKey, publicKey);
			byte[] xml = DesUtil.encrypt(data.getBytes("utf-8"), this.desKey.getBytes("utf-8"));
			encryptXml = encoder.encode("173665683923351".getBytes("utf-8"))+"|"+encryptDesKey+"|"+encoder.encodeBuffer(xml);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return encryptXml;
	}
	
	//解密
	public String decrypt(String data) {
		String respXml = "";
		try {
			respXml = new String(DesUtil.decrypt(decoder.decodeBuffer(data), this.desKey.getBytes()),"utf-8");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
	
	/**
	 * 通知支付前置
	 * 
	 * @param url
	 *            支付前置的通知地址
	 * @param outResultMsg
	 *            通知参数
	 * @throws ServiceException
	 */
	public static void notice(OutputStream out, String outResultMsg)
	{
		try
		{
			out.write(outResultMsg.getBytes("utf-8"));
			out.flush();
			out.close();
		} catch (IOException e){
			e.printStackTrace();
		}

	}
}

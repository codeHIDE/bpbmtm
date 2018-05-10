package com.bypay.util;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.Ostermiller.util.MD5;

public class AuthenticationUtil {
	BASE64Encoder encoder = new BASE64Encoder();
	BASE64Decoder decoder = new BASE64Decoder();
	
	//加密
	public String encrypt(String first, String Second, String Third) {
		String encryptXml = "";
		try {
			byte[] xml = DesUtil.encrypt(Second.getBytes("utf-8"), ValueTool.AUTH_MER_TRANS_KEY.getBytes("utf-8"));
			
			encryptXml = encoder.encode(first.getBytes("utf-8"))+"|"+encoder.encodeBuffer(xml)+"|"+MD5.getHashString(Third);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return encryptXml;
	}
	
	//解密
	public String decrypt(String data) {
		String respXml = "";
		try {
			respXml = new String(DesUtil.decrypt(decoder.decodeBuffer(data), ValueTool.AUTH_MER_TRANS_KEY.getBytes()),"utf-8");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}

	public String getRespDesc(String xml) {
		String respDesc="";
		try{
			Document document = DocumentHelper.parseText(xml);
			Element bpcore = document.getRootElement();
			respDesc = bpcore.elementText("respDesc");
		}catch (Exception e) {
			//checkCode = "3006";
			e.printStackTrace();
		}
		return respDesc;
	}
}

package com.bypay.util;

import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class ClientRequestUtil {
	private BASE64Decoder decoder = new BASE64Decoder();
	private BASE64Encoder encoder = new BASE64Encoder();
	private String desKey = "";
	private String terminalId = "";
	private String encryptDesKey = "";
	private String encryptXml = "";
	private String application = "";

	public String getDesKey() {
		return this.desKey;
	}

	public String getTerminalId() {
		return this.terminalId;
	}

	public String getApplication() {
		return this.application;
	}

	/**
	 * �?查请求报文格式是否正�?
	 * 
	 * @param encryptReqXml
	 * @return
	 */
	public String checkEncryptReqXml(String encryptReqXml) {
		// �?查报文基本格�?
		String[] xmlArr = new String[3];
		if (null == encryptReqXml || encryptReqXml.length() < 1) {
			try {
				return "0|9998|" + encoder.encodeBuffer("报文格式错误".getBytes("utf-8"));
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			return null;
		}
		try {
			xmlArr = encryptReqXml.split("\\|");
			if (xmlArr.length != 3) {
				try {
					return "0|9998|" + encoder.encodeBuffer("报文格式错误".getBytes("utf-8"));
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			this.encryptDesKey = xmlArr[1];
			this.encryptXml = xmlArr[2];
			this.terminalId = new String(decoder.decodeBuffer(xmlArr[0]), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			try {
				return "0|9999|" + new BASE64Encoder().encodeBuffer("未知错误".getBytes("utf-8"));
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 解密报文�?
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getDecryptXml() throws Exception {
		String privateKey = EncDecUtil.getPublicCertKey("123456","e://700000000000001-qjj.cer");;
		System.out.println(" this.encryptDesKey:  "   +  this.encryptDesKey);
		this.desKey = RSACoder.decryptByPublicKey(this.encryptDesKey, privateKey);
		byte[] xmlBody = DesUtil.decrypt(decoder.decodeBuffer(this.encryptXml), desKey.getBytes());
		String decryptXml = new String(xmlBody, "utf-8");
//		System.out.println("解密出的XML明文:    " + decryptXml);
		Document document = DocumentHelper.parseText(decryptXml);
		Element root = document.getRootElement();
		this.application = root.attributeValue("application");
		return decryptXml;
	}

}

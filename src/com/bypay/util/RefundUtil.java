package com.bypay.util;

import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.Ostermiller.util.MD5;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class RefundUtil {
	BASE64Encoder encoder = new BASE64Encoder();
	BASE64Decoder decoder = new BASE64Decoder();
	
	private String desKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKEPfNisvXwbSy92NWI2hL46589LRygHyBDuVQXHE1rlxVOZdVx9uddd54+JwvCVuWaL39Ns9LB2g5qpmVZ+8iLL/93mHC99KVPaJ9QI66srren2HnRKmfB5HLdGmFoJetlzdiovD9uQRvamS0iECx8kwxQ4aj8kwZO2syeLnrPxAgMBAAECgYEAhBTAbKyUpB959A070DQnfh2ulsgELabcAk6BeUB99fAyd9GEdnpAmobO7F6seEJBDgCtaKSUsdYvLPni3xUyGdtvQFoH/xzv2XMQyvpYTexIrDiJr3MEpDJd8Iid5ZubpMWqSOmutJtLfV3gC0MGfQm6+nZdGfiXV7ohFRMmwUECQQDY5NNtQcsf+DKv4xJdEZHU6tIK/A0wcswI5QMd/v8l9FGjyX14ApYSCdnPhQZtqWzwuQlUxWPgqSAi1BE8KqEJAkEAvhmPgMqv3co1YXRJ47zVF+ACNOhlaaG8x+OXHBfv2NN6jvYAfHdELa/tYFlWLazOpy0Q0CU26+5rT6h3V1F9qQJAU+6RBrmsMi3o53mWtJ9E8MECETAipnn2DQcaYrQ35mcaZKhnPla53jcjq5ONvkgPGURxoPVVxi2Mew3XsZHJiQJADFbgZ73AWKcte9vuh+fT9S7HNeP34TlsZZUyU9KB8RMZG3qAYZPkSwrmX6Cs5V4YM+XK95fSztG1CYCn7nUNsQJAWanfSF18w5GilDnygQg62rP59X2XNqSLxjLaD15QiodhiiiFrWVjMoFgukkebNCO9ZLGz1jTnMx/9sQJgFxtfQ==";
	
	public String getDesKey() {
		return this.desKey;
	}
	
	//加密
	public String encrypt(String data, String terminalId) {
		String encryptXml = "";
		try {
			String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQChD3zYrL18G0svdjViNoS+OufPS0coB8gQ7lUFxxNa5cVTmXVcfbnXXeePicLwlblmi9/TbPSwdoOaqZlWfvIiy//d5hwvfSlT2ifUCOurK63p9h50SpnweRy3RphaCXrZc3YqLw/bkEb2pktIhAsfJMMUOGo/JMGTtrMni56z8QIDAQAB";
//			String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDavTXnRYKwowo6UyjtXmN+29mwjBDwLxwy4McGUJYqba+g88ThlznhDB9rBesZjTka0evRSmRM3Cfbxqmr50jghr/NNBWp+9GKokixheUsbVptklXQhZP7tMhAj+6ZoIh7PR4RZafdzYcgxezYog1ouW5zhcbAHIbwPqkFXzTWpwIDAQAB";
//			String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC11dhz/urKmlEU7hlzd+Fa+yYpLpJ7Fq9byk5/SjaVvlkfbU+f3tBCklP1M2r2avAAG5fh680xwdVpGuuaJBp7JNj1kIAJOPZ8EQLR+wzLqzaEDx0MCnHGfyqtOKN6eJWm6H5wUxBrAzPXqjqjJVVT4vZIAYwFlOaHRKfFucNP0QIDAQAB";
			String encryptDesKey = RSACoder.encryptByPublicKey(MD5.getHashString(this.desKey), publicKey);
			byte[] xml = DesUtil.encrypt(data.getBytes("utf-8"), MD5.getHashString(this.desKey).getBytes("utf-8"));
			encryptXml = encoder.encode(terminalId.getBytes("utf-8"))+"|"+encryptDesKey+"|"+encoder.encodeBuffer(xml);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return encryptXml;
	}
	
	//解密
	public String decrypt(String data) {
		String respXml = "";
		String result = "";
		try {
			respXml = new String(DesUtil.decrypt(decoder.decodeBuffer(data), MD5.getHashString(this.desKey).getBytes()),"utf-8");
			System.out.println(respXml);
			result = getRespDesc(respXml);
			System.out.println("result>>:" + result);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public static void main(String[] args) {
		String desKey = "zcxzjojq21312d242Kwdq24244";
		BASE64Decoder decoder = new BASE64Decoder();
			try {
				System.out.println(new String(decoder.decodeBuffer("czldYnjpQk+lY9os0m+pVg=="), "utf-8"));
			} catch (Exception e) {
				e.printStackTrace();
			}
//		String str = "QzHOE0tRePMeHr3jcpSH5dzW5Vz6b8UZgkVDXWIVoJaTJawDx7kIt7589rBDb6VHIGHLbjGwnQdQVFvaoF9A+IS5Qtz21mKOjBig+3Fluu3Dxio3Dy0HqF5rU9esH1dWlWuV0OdhW9Z3KJuHgW8wQx6Hxs8XXJ0yRb5u21DUKen0cK3NnR1onzcNkc3b2E6lPS/lkiTl4EgrvXwp0NGmg5FLP4YeGzWb0vsljcCm3qX2Uu5ZAGv16guqdyEC+aqkjBjtW5FxI0nPgzi8/TiipD8PzQW9hTB6z2LjPrJDxpnCsOMBYtFqWd5B9ASiaPCQgJLG6v6xL2Ct/SLQya9y0rvVBsIYTas6gYT+gAMjyXbxUx2dw0KmOBReEQ/dmAeGbx6B5is8JNkrUZTP2MQYMI+Fkev9gjoA2J7LEzUfGKr3q77h8NrEs9ieyxM1HxiqlIcEU+XVeGkB06O7BrYmafNAio6Sbz9L1UVxMWDxLCF43w9FTIUZrDUqMom0WXFbhpjYoKBoFS3T1aRRyepgyR8PIjf0dw110UeILB8bzav6qwGOgyyAuvNwHvkE1oFM";
//		new RefundUtil().decrypt(str);
	}

	/**
	 * 获取返回描述
	 * @param xml
	 * @return
	 */
	public String getRespDesc(String xml) {
		String respCode = "";
		String respDesc="";
		try{
			Document document = DocumentHelper.parseText(xml);
			Element bpcore = document.getRootElement();
			respCode = bpcore.elementText("respCode");
			respDesc = bpcore.elementText("respDesc");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "1|"+respCode+"|"+respDesc;
	}
}

package com.bypay.service.impl.util;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Security;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;

import com.bypay.util.Des3;
import com.bypay.util.JSONStringBuilder;
import com.bypay.util.RSAObjectC;
import com.bypay.util.TimeUtils;

public class RuiyinCredit {
	//私钥是假的
	public static String privateKey="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMBhd23siGvEXoZ5\rFqm97/vnfKas4Rk7ATzPfXX1L0Fo9nQ9N7FIw9ASkTIGyS2E6yG0amwU9o9TOdnX\rW6gPEf7rI4tvotbTBnd3ENXzlvWqKdwCdtPvggtgswSwoXUzWbX+js2wh+tsaFL7\r5SSnu4+XRHjFPAxRpt4eOSMdxGS5AgMBAAECgYA4adxtvZjbkT/uyv9cUzhWdDFX\rZwazMVxRir7NolJzKeZre7AjKqVRFMDDHL5NvNZ+kbtLanJK4SrruH1GMPUjewq/\r+YGoCIauAYo4i6zpRT79yW/wResIWgGepO6y1Rez3Gw9sKaCJ29W1LX5V6zNlFp6\rS3K1utBw5i5wcQYIAQJBAPHCEYGDpYhxmb06t5BK3VXxLkI8YaxVeytsrNklc516\rJjTEmsFE5I1DKsnWrLptCsm7fz70EeKfDfl9ZXKQYTkCQQDLtr4n90NFpK8nQDRs\rBC0v9WUKPhmgnaCGjxfJqoaVYvjw+njiQg6ZN6tg6JVRQ69+fQfVBUdGUdqonljI\rqZ+BAkBfuQSvcE2xqbFC0KXv9wSOS9GSHlBaKPp85inGTOLrPns0N4hC8CFCoY6m\rnl6D7jrwR1IJvh/6yBmDaJgrdWlZAkAoO1P8sqGPjIGm/9AakQyXjyBAUJlhFiBQ\r0svA4TOpJx3BAcaD8MXPTqZDAkwS+4oaMfktAdjcs2e6q2MfZhiBAkAzb4pwjFRE\rE/B7XFZSqKkzDTKU2AYYX9B22GMmgv70e/jFgE9Y1lHjbM5YVnJ0/jnw4DiicBKP\r6u+891v27V8b\r";

//	public static String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDEt4xl7lP9v5R4Kd3Hd9n+5jgd\rbtQApkPiM6OrCPH1bGZ6aTr1qTR/VCfiiP38HVlVU9IVuJss2U1Zt6Kxaf6CV7Ge\renX8bvagWdE9BElw6iAKlfxYBIAlTVsIrjqtfExzSN4Z1C4iHI+2+ELkIry63n2J\rlJj/rhOq/c4BHB8DpQIDAQAB\r";
	public static String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDEt4xl7lP9v5R4Kd3Hd9n+5jgdbtQApkPiM6OrCPH1bGZ6aTr1qTR/VCfiiP38HVlVU9IVuJss2U1Zt6Kxaf6CV7GeenX8bvagWdE9BElw6iAKlfxYBIAlTVsIrjqtfExzSN4Z1C4iHI+2+ELkIry63n2JlJj/rhOq/c4BHB8DpQIDAQAB";
	public static String pinKey="B1E4349321C46C694CAA50055E06101E";//明文
//	public static String pinKey="9E69BFEEC777C27C9E69BFEEC777C27C";//ruiyin明文
	public static String trackKey="140D1D12A153B649547609157647539A";//明文
//	public static String mcode = "137458150713099";
//	public static String tcode = "60110";
	public static String mcode = "137458150713099";
	public static String tcode = "60110";
	
	static{//安全性
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	}
	
	//测试接口 是否可以用
	public static void main(String args[]){
		try {
			Map<String,Object> result = new LinkedHashMap<String,Object>();
			result.put("mcode", mcode);
			result.put("tcode", tcode);
			result.put("version", "1.0");
			
//			String accountNumber = "6215581111004500898";
			String accountNumber = "6228480412309089719";
//			String pin = "361000";
			String pin = "196576";
//			String pin = "A647C206BBE1BBA0";
//			String pin = "B6AFD8DA604D3B8E";
//			String track2Data = "6215581111004500898d25032200299991917";
			String track2Data = "6228480412309089719d49121205007530000";
			Date date = new Date();

			
			//消费
			result.put("action","purchase_receivablesToCard");
			result.put("merchantOrderId", TimeUtils.dateString(date, "yyyyMMddHHmmssSSS"));
			result.put("merchantOrderTime", TimeUtils.dateString(date, "yyyyMMddHHmmss"));			
			result.put("transAmt", "1000");//金额分
			result.put("accountNumber", accountNumber);//卡号 中国银行
			result.put("mpin", Des3.encryptPin(accountNumber, pin, pinKey));//
			result.put("mtrack2Data", Des3.encryptStanderTranck(trackKey, track2Data));//
			result.put("track3Data", "");
			result.put("signaturePath", "");//调用上传图片接口返回的  
			result.put("bit32", "00012213");
			JSONObject icCardYuJson = JSONObject.fromObject("{\"bit55\":\"9F2608D237B18C45484D9D9F2701809F101307020103A02002010A0100000000001F1A05A59F370419A89AB89F3602005F9505000004E0009A031511119C01009F02060000000265005F2A02015682027C009F1A0201569F03060000000000009F3303E0F9C89F34034203009F3501229F1E0838333230494343008408A0000003330101029F090200209F4104000001729F631030313035303030300000000000000000\",\"5f24\":\"230930\",\"5f34\":\"1\"}");
			//信用卡
//			result.put("icCardYu",icCardYuJson.toString());
			
			//四要素认证
//			String accountName = "吴海泉";
//			String idCard = "320623198805035832";
//			String mobile = "18626269633";
//			result.put("action","add_bankCheck_userBankCardCheck");
//			result.put("merchantOrderId", TimeUtils.dateString(date, "yyyyMMddHHmmssSSS"));//商户订单号  可以自定义
//			result.put("merchantOrderTime", TimeUtils.dateString(date, "yyyyMMddHHmmss"));			
//			result.put("accountNumber", accountNumber);//卡号
//			result.put("accountName", accountName);
//			result.put("idCard", idCard);
//			result.put("mobile", mobile);
			
			JSONObject json = diaoyong(JSONStringBuilder.getAjaxString(result));
//			diaoyong(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static JSONObject diaoyong(String json)throws Exception{
		String terminalTypeId = new String(Base64.encodeBase64(mcode.getBytes("UTF-8")),"UTF-8");
		RSAObjectC rsaEncrypt= RSAObjectC.getInstance(publicKey,privateKey);
		byte[] desKeyB = rsaEncrypt.encrypt(rsaEncrypt.getPublicKey(), "8C23022DD39F2AC3B4A2377813CF9AAE".getBytes("Utf-8"));
		//byte[] desKeyB = RSAUtils.encryptByPublicKey("8C23022DD39F2AC3B4A2377813CF9AAE".getBytes("Utf-8"), publicKey);
		String desKey = new String(Base64.encodeBase64(desKeyB),"UTF-8");
		System.out.println("#"+desKey+"#");
		System.out.println("#################json#"+json+"#");
		/*byte[] dataB = DesUtil.encrypt(json.getBytes("Utf-8"), "8C23022DD39F2AC3B4A2377813CF9AAE".getBytes("Utf-8"));
		String data = new String(Base64.encode(dataB),"UTF-8");*/
		String data = Des3.encode(json,"8C23022DD39F2AC3B4A2377813CF9AAE");
		
		System.out.println("#"+data+"#");
		String str = terminalTypeId+"|"+desKey+"|"+data;//"1|"+desKey+"|"+data;
		System.out.println("参数："+str);
		//str = Base64.encode(str);
		
		return httpClientUtils(str);
	}
	
	@SuppressWarnings("unused")
	private static String sessionId = "JSESSIONID=983A81651D1CCFE7122462A1B2FE87F1";
	public static JSONObject httpClientUtils(String XML) throws Exception{
		String Method="POST";
		String xmlString=XML;
		byte[] xmlData=xmlString.getBytes();
		try {
			xmlData = xmlString.getBytes("UTF-8");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
//		String urlStr = "http://selenthill001.xicp.net:8601/zyPayInfo/forward/fcn.action"; 
//		String urlStr = "http://localhost:8080/zyPayInfo/forward/fcn.action"; 
		String urlStr = "http://www.zytlpay.com:8600/zyPayInfo/forward/fcn.action";
		BufferedReader rd =null;
		String msg="";
		System.out.println("请求URL:" + urlStr);
		System.out.println("请求参数:" + XML);
		JSONObject json = new  JSONObject();
		try {
			URL url = new URL(urlStr);
			HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
			urlCon.setDoOutput(true);
			urlCon.setDoInput(true);
			urlCon.setUseCaches(false);
			urlCon.setRequestMethod(Method);
			urlCon.setConnectTimeout(120000);
			urlCon.setReadTimeout(120000);
			//System.setProperty("sun.net.client.defaultConnectTimeout", "120000");
			//System.setProperty("sun.net.client.defaultReadTimeout", "120000");
			urlCon.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
			urlCon.setRequestProperty("Content-length", String.valueOf(xmlData.length));
			urlCon.setRequestMethod("POST");
//			if(null != sessionId)
				urlCon.setRequestProperty("Cookie", "SESSION_COOKIE=s103;JSESSIONID=983A81651D1CCFE7122462A1B2FE87F1");
			//urlCon.setRequestProperty("Cookie", "JSESSIONID=BBEBB4F78F7490EC657B6EA74989DE9C");
			DataOutputStream printout = new DataOutputStream(urlCon.getOutputStream());
			printout.write(xmlData);
			printout.flush();
			printout.close();
			System.out.println("数据发送完成,等待响应...");
			rd = new BufferedReader(new InputStreamReader(urlCon.getInputStream(),"utf-8"));  
			String inputLine;
			while ((inputLine = rd.readLine()) != null)
				msg+=inputLine;
			System.out.println("解密前数据:"+msg);
			//解密
			String[] strArray = msg.split("\\|");
			if(strArray.length == 3){
				if("1".equals(strArray[0])){//成功信息
					/*byte[] dataB = Base64.decode(strArray[1].getBytes("UTF-8"));
					String data = new String(DesUtil.decrypt(dataB, "8C23022DD39F2AC3B4A2377813CF9AAE".getBytes("UTF-8")),"UTF-8");*/
					String data = Des3.decode(strArray[1],"8C23022DD39F2AC3B4A2377813CF9AAE");
					strArray[1] = data;
					System.out.println("响应数据为:"+data);
					json=JSONObject.fromObject(data);
				}else{
					String code = strArray[1];
					System.out.println("错误代码:"+code);
					String eMsg = new String(Base64.decodeBase64(strArray[2].getBytes("UTF-8")),"UTF-8");
					System.out.println("错误描述:"+eMsg);
					json.put("reqCode", code);
					json.put("reqMsg", eMsg);
				}
			}
			System.out.println(json.getString("reqMsg"));
			System.out.println(json.getString("reqCode"));
//			String cookieValue=urlCon.getHeaderField("Set-Cookie");
//            System.out.println("cookie value:"+cookieValue);
//            sessionId=cookieValue.substring(0, cookieValue.indexOf(";")); 
//            System.out.println("sessionId:"+sessionId);
			//System.out.println("响应数据为:"+msg);
			rd.close();				
		}catch (Exception e) {
			//e.printStackTrace();
			throw e;
		}
		return json;
	}
	
	@SuppressWarnings("unused")
	private static void upload(String[] uploadFiles, String actionUrl) {
        String end = "/r/n";
        String twoHyphens = "--";
        String boundary = "*****";
        try {
            URL url = new URL(actionUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
             // 发送POST请求必须设置如下两行  
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setRequestMethod("POST");
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "UTF-8");
            con.setRequestProperty("Content-Type",
                    "multipart/form-data;boundary=" + boundary);
            DataOutputStream ds =
                    new DataOutputStream(con.getOutputStream());
            
            ds.writeBytes(twoHyphens + boundary + end);
            ds.writeBytes("Content-Disposition: form-data; " +
                    "name=\"action\"" + end);
            ds.writeBytes(end);
            ds.writeBytes("");
            for (int i = 0; i < uploadFiles.length; i++) {
                String uploadFile = uploadFiles[i];
                String filename = uploadFile.substring(uploadFile.lastIndexOf("//") + 1);
                ds.writeBytes(twoHyphens + boundary + end);
                ds.writeBytes("Content-Disposition: form-data; " +
                        "name=\"file" + i + "\";filename=\"" +
                        filename + "\"" + end);
                ds.writeBytes(end);
                FileInputStream fStream = new FileInputStream(uploadFile);
                int bufferSize = 1024;
                byte[] buffer = new byte[bufferSize];
                int length = -1;
                while ((length = fStream.read(buffer)) != -1) {
                    ds.write(buffer, 0, length);
                }
                ds.writeBytes(end);
                /* close streams */
                fStream.close();
            }
            ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
            ds.flush();
            // 定义BufferedReader输入流来读取URL的响应  
            InputStream is = con.getInputStream();
            int ch;
            StringBuffer b = new StringBuffer();
            while ((ch = is.read()) != -1) {
                b.append((char) ch);
            }
            String s = b.toString();
            if (s.contains("successfully")) {
                // for (int i = 1; i < 5; i++) {
                int beginIndex = s.indexOf("url =") + 5;
                int endIndex = s.indexOf("/n", beginIndex);
                String urlStr = s.substring(beginIndex, endIndex).trim();
                System.out.println(urlStr);
                // }
            }
            ds.close();
        } catch (Exception e) {
        }
    }
}

package com.bypay.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 * 
 * @version 1.0
 * @author brent xu
 * 
 */
public class Md5Util {
    
    /**
     * MD5加密
     * @param plainText
     * @return 
     */
    public static String generateMD5String(String plainText){
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            
            int i;
            StringBuffer sb=new StringBuffer();
            for(int offset=0;offset<b.length;offset++){
                i=b[offset];
                if(i<0){
                    i+=256;
                }
                if(i<16){
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            //log.error...
            System.out.println("generate MD5 String error! "+e.getMessage());
            //throw e;
        }
        return null;
    }
    
    public static String getMD5(String source) {
		String s = null;
		char hexDigits[] = { // 用来将字节转换成 16 进制表示的字�?
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
				'e', 'f' };
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");

			md.update(source.getBytes("utf-8"));
			byte tmp[] = md.digest(); // MD5 的计算结果是�?�? 128 位的长整数，
			// 用字节表示就�? 16 个字�?
			char str[] = new char[16 * 2]; // 每个字节�? 16 进制表示的话，使用两个字符，
			// �?以表示成 16 进制�?�? 32 个字�?
			int k = 0; // 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) { // 从第�?个字节开始，�? MD5 的每�?个字�?
				// 转换�? 16 进制字符的转�?
				byte byte0 = tmp[i]; // 取第 i 个字�?
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中�? 4 位的数字转换,
				// >>> 为�?�辑右移，将符号位一起右�?
				str[k++] = hexDigits[byte0 & 0xf]; // 取字节中�? 4 位的数字转换
			}
			s = new String(str); // 换后的结果转换为字符�?

		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
    
    public static String getPayMD5ChaSet(String source) {
		String s = null;
		char hexDigits[] = { // 用来将字节转换成 16 进制表示的字�?
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
				'e', 'f' };
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");

			md.update(source.getBytes("utf-8"));
			byte tmp[] = md.digest(); // MD5 的计算结果是�?�? 128 位的长整数，
			// 用字节表示就�? 16 个字�?
			char str[] = new char[16 * 2]; // 每个字节�? 16 进制表示的话，使用两个字符，
			// �?以表示成 16 进制�?�? 32 个字�?
			int k = 0; // 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) { // 从第�?个字节开始，�? MD5 的每�?个字�?
				// 转换�? 16 进制字符的转�?
				byte byte0 = tmp[i]; // 取第 i 个字�?
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中�? 4 位的数字转换,
				// >>> 为�?�辑右移，将符号位一起右�?
				str[k++] = hexDigits[byte0 & 0xf]; // 取字节中�? 4 位的数字转换
			}
			s = new String(str); // 换后的结果转换为字符�?

		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
    
    
    //19pay提供的MD5加密方式-仅限手机充值接口使用--begin
    
	public static String getKeyedDigest(String strSrc, String key) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(strSrc.getBytes("UTF8"));
            
            String result="";
            byte[] temp;
            temp=md5.digest(key.getBytes("UTF8"));
    		for (int i=0; i<temp.length; i++){
    			result+=Integer.toHexString((0x000000ff & temp[i]) | 0xffffff00).substring(6);
    		}
    		
    		return result;
    		
        } catch (NoSuchAlgorithmException e) {
        	
        	e.printStackTrace();
        	
        }catch(Exception e)
        {
          e.printStackTrace();
        }
        return null;
    }
	
	

    /**
     * MD5加密 ivr报文体加签名
     * @param plainText
     * @return 
     */
	public static String getSignMd5(String strSrc) {
        //生成sign签名
        String dataSign = "<Sign>"+Md5Util.getMD5(strSrc.substring(strSrc.indexOf("<MessageType>"),strSrc.indexOf("</vpcpay>")))+"</Sign></vpcpay>";
        strSrc = strSrc.substring(0,strSrc.indexOf("</vpcpay>"));
        strSrc = strSrc+dataSign;
        return strSrc;
    }
	
	/**
     * MD5加密 ivr验证签名
     * @param plainText
     * @return 
     */
	public static String getVerificationMd5(String strSrc) {
        //生成sign签名
        strSrc = strSrc.substring(strSrc.indexOf("<MessageType>"),strSrc.indexOf("<Sign>"));
        System.out.println(strSrc);
        strSrc = Md5Util.getMD5(strSrc);
        
        return strSrc;
    }
	
	public static String generateMD5StringLow(String plainText){
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            
            int i;
            StringBuffer sb=new StringBuffer();
            for(int offset=0;offset<b.length;offset++){
                i=b[offset];
                if(i<0){
                    i+=256;
                }
                if(i<16){
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString().toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            //log.error...
            System.out.println("generate MD5 String error! "+e.getMessage());
            //throw e;
        }
        return null;
    }
	
	 //19pay提供的MD5加密方式-仅限手机充值接口使用--end

	public static void main(String[] args) {
		
		String data  ="201702000120170217112905c4798e7b1bca71d4c85f198429ab3a30";
		System.out.println(generateMD5String(data));
		
	}

	
}

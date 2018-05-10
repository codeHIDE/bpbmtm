package com.bypay.util;

/** 
 * Project Name:gaoex-gshield 
 * File Name:AESCoder.java 
 * Package Name:com.gaoex.shield.security 
 * Date:2016年1月20日上午11:06:03 
 * mailto:lqh@gaoex.com
 * Copyright (c) 2016, www.gaoex.com All Rights Reserved. 
 * 
 */


import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * 
 *
 * @ClassName: AESCoder 
 * 
 * @author qinghe 
 *
 * @date 2016年1月20日 上午11:06:47 
 *
 * @version V1.0
 *
 */
public abstract class AESCoder {

	/**
	 * 密钥算法
	 */
	public static final String KEY_ALGORITHM = "AES";
	
	/**
	 * 加密解密算法 / 工作模式 / 填充方式
	 * 
	 */
	public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

	/**
	 * 
	 * 转换密钥
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 *
	 */
	private static Key toKey(byte[] key) throws Exception {
		// 实例化密钥
		SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
		// 返回函數
		return secretKey;
	}

	/**
	 * 
	 * 加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 *
	 */
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		// 还原密钥
		Key k = toKey(key);
		// 实例化
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		// 初始化,设置加密模式
		cipher.init(Cipher.ENCRYPT_MODE, k);
		// 加密
		return cipher.doFinal(data);
	}

	/**
	 * 
	 * 解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 *
	 */
	public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		// 还原密钥
		Key k = toKey(key);
		// 实例化
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		// 初始化,设置解密模式
		cipher.init(Cipher.DECRYPT_MODE, k);
		
		// 解密
		return cipher.doFinal(data);
	}

	/**将二进制转换成16进制 
	 * @param buf 
	 * @return 
	 */  
	public  static String parseByte2HexStr(byte buf[]) {  
	        StringBuffer sb = new StringBuffer();  
	        for (int i = 0; i < buf.length; i++) {  
	                String hex = Integer.toHexString(buf[i] & 0xFF);  
	                if (hex.length() == 1) {  
	                        hex = '0' + hex;  
	                }  
	                sb.append(hex.toUpperCase());  
	        }  
	        return sb.toString();  
	}  
	 
	/**将16进制转换为二进制 
	 * @param hexStr 
	 * @return 
	 */  
	public static byte[] parseHexStr2Byte(String hexStr) {  
	        if (hexStr.length() < 1)  
	                return null;  
	        byte[] result = new byte[hexStr.length()/2];  
	        for (int i = 0;i< hexStr.length()/2; i++) {  
	                int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
	                int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
	                result[i] = (byte) (high * 16 + low);  
	        }  
	        return result;  
	}  

	  private static final String CIPHER_ALGORITHNM = "AES";

	  public static String getAutoCreateAESKey()
	    throws Exception
	  {
	    KeyGenerator kg = KeyGenerator.getInstance("AES");
	    kg.init(128);
	    SecretKey sk = kg.generateKey();
	    return Base64.encodeBase64String(sk.getEncoded());
	  }

	  public static byte[] encrypt(byte[] data, String aesKey)
	    throws Exception
	  {
	    byte[] encryptBytes = Base64.decodeBase64(aesKey);
	    SecretKeySpec key = new SecretKeySpec(encryptBytes, "AES");
	    Cipher cipher = Cipher.getInstance("AES");
	    cipher.init(1, key);
	    return cipher.doFinal(data);
	  }

	  public static byte[] decrypt(byte[] data, String aesKey)
	    throws Exception
	  {
	    byte[] encryptBytes = Base64.decodeBase64(aesKey);
	    SecretKeySpec key = new SecretKeySpec(encryptBytes, "AES");
	    Cipher cipher = Cipher.getInstance("AES");
	    cipher.init(2, key);
	    return cipher.doFinal(data);
	  }
	  
	public static String encryptDataByAES(String src, String aesKey, String charset)
	        throws Exception
	      {
	        try
	        {
	          byte[] bytes = encrypt(src.getBytes("UTF-8"), aesKey);
	          return Base64.encodeBase64String(bytes);
	        } catch (Exception e) {
	            e.printStackTrace();
	          throw e;
	        }
	      }
	
	  public static String decryptDataByAES(String src, String aesKey, String charset)
	          throws Exception
	        {
	          try
	          {
	            byte[] srcByte = Base64.decodeBase64(src);
	            byte[] bytes = decrypt(srcByte, aesKey);
	            return new String(bytes, charset);
	          } catch (Exception e) {
	              e.printStackTrace();
	            throw e;
	          }
	        }
	 
	  
	public static void main(String[] args) {
        String data="WVR304GURVlkVWmf5pxdHkQ6gnGz8TjyQudS+f3X/KxxZ4kaSsrMgi1V+md+4CXy3/SPEC7Gi9nEzd4ilo6xLYxtnt1e1GoUrHNgdEuzszQzfoiLZ6X0C8lCDz3KUFlqXkjVcGpOXXDR3Pju/Qe3osGMC7zM0FSW6xqDfTE5LzRsWd+mkPFxYbTiIr8JCcOAkP3cR1CHTVxJDgB5ltfhim5fhrCyBBkxOTTzVm8NJCLhj91D3qcr9hr8B7fd8jvVuLpj+xhcVVfZ0Qnrd4PJd++TPMziueLVexHrVVbS1Ea4qLRvAWYKxPgbydpXz3vP+zlcAJutLusmjRHaj0MprOUJHLrkzqzWcAhA3wzmzYgWwIUlLCxYo/YCNw1IFOp9wnJh71fygp/8ECufUT22/kPdwaHSfhFxqEvayIJssckzjao2TkQPpQ2+kizU5BXVynkZ8OIx2zXSbkOWYJmZ7A==";
	    String key="xbDy7BIi4rgG+Bp0m4JpQA==";
	    try {
	        String result = decryptDataByAES(data,key,"UTF-8");
	        System.out.println(result);
//            byte[] result = decrypt(data.getBytes(), key.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.bypay.util;

import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.richerpay.core.model.CoreTransInfo;

/**
 * 3DES加密工具类
 * @date 2014-04-17
 */
public class Des3 {
	static Logger log = Logger.getLogger(Des3.class);
	// 密钥
	private final static String secretKey = "zhongyi@163.com$#6688#$";
	// 向量
	private final static String iv = "87654321";
	// 加解密统一使用的编码方式
	private final static String encoding = "utf-8";

	/**
	 * 3DES加密
	 * @param plainText 普通文本
	 * @param secretKey
	 * @return
	 * @throws Exception 
	 */
	public static String encode(String plainText,String secretKey){
		try{
			Key deskey = null;
			DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
			deskey = keyfactory.generateSecret(spec);
	
			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
			byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
			//return Des3Base64.encode(encryptData);
			byte[] bb = Base64.encodeBase64(encryptData);
			return new String(bb,"UTF-8");
		}catch(Exception e){
			e.printStackTrace();
		}
		return plainText;
	}
	
	/**
	 * 3DES加密
	 * @param plainText 普通文本
	 * @return
	 * @throws Exception 
	 */
	public static String encode(String plainText){
		return encode(plainText,secretKey);
	}

	/**
	 * 3DES解密
	 * 
	 * @param encryptText 加密文本
	 * @return
	 * @throws Exception
	 */
	public static String decode(String encryptText,String secretKey){
		try{
			Key deskey = null;
			DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
			deskey = keyfactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
	
			//byte[] decryptData = cipher.doFinal(Des3Base64.decode(encryptText));
			byte[] decryptData = cipher.doFinal(Base64.decodeBase64(encryptText.getBytes("UTF-8")));
			return new String(decryptData, encoding);
		}catch(Exception e){
			e.printStackTrace();
		}
		return encryptText;
	}
	
	/**
	 * 3DES解密
	 * 
	 * @param encryptText 加密文本
	 * @return
	 * @throws Exception
	 */
	public static String decode(String encryptText){
		return decode(encryptText,secretKey);
	}
	
	/**
	 * 生成8位DES密钥  转码的16位密钥
	 * @return
	 */
	public static String generate16Key(){
		KeyGenerator kg;
		try {
			kg = KeyGenerator.getInstance("DES");
			kg.init(56);
			SecretKey secretKey = kg.generateKey();
			byte[] key = secretKey.getEncoded();
			return byte2hex(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 转换成十六进制字符串
	 * @param b
	 * @return
	 */
    public static String byte2hex(byte[] b) {
        String hs="";
        String stmp="";

        for (int n=0;n<b.length;n++) {
            stmp=(java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length()==1) 
            	hs=hs+"0"+stmp;
            else 
            	hs=hs+stmp;
            //if (n<b.length-1)  hs=hs+":";
        }
        return hs.toUpperCase();
    }
    
	/**
	 * 生成8位DES密钥  转码的32位密钥
	 * @return
	 */
	public static String generate32Key(){
		return MD5.mD5ofStr(generate16Key());
	}
	
	public static byte[] hexStringToByte(String hex) {
		hex=hex.toUpperCase();
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}
	
	private static byte toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}
	

	/**
	 * 3DES解密
	 * @param dataS 解密数据
	 * @return keys 密钥
	 */
	public static String decode3DES(String dataS, String keys)throws Exception{
		String result="";
		 try {
			 	byte[] data=hexStringToByte(dataS);
			 	byte[] key=hexStringToByte(keys);
				byte[] km = new byte[24];
				System.arraycopy(key, 0, km, 0, 16);
				System.arraycopy(key, 0, km, 16, 8);
				Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
				DESedeKeySpec dks = new DESedeKeySpec(km);
				SecretKey k = SecretKeyFactory.getInstance("DESede")
						.generateSecret(dks);
				cipher.init(Cipher.DECRYPT_MODE, k);
				result=byte2hex(cipher.doFinal(data));
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e);
			}
			return result;
	 }
	/**
	 * 3DES加密
	 * @param key 密钥
	 * @param data 待加密数据
	 * @return byte[] 加密数据
	 */
	public static byte[] encode3DES(byte[] key, byte[] data) throws Exception{
		try {
			byte[] km = new byte[24];
			System.arraycopy(key, 0, km, 0, 16);
			System.arraycopy(key, 0, km, 16, 8);
			Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
			DESedeKeySpec dks = new DESedeKeySpec(km);
			SecretKey k = SecretKeyFactory.getInstance("DESede")
					.generateSecret(dks);
			cipher.init(Cipher.ENCRYPT_MODE, k);
			byte[] result = cipher.doFinal(data);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	
	/**
	 * 3DES加密
	 * @param key 密钥
	 * @param dataS 待加密数据
	 * @return keys 加密数据
	 */
	public static String encode3DES(String dataS, String keys) throws Exception{
		byte[] data=hexStringToByte(dataS);
	 	byte[] key=hexStringToByte(keys);
	 	String result = byte2hex(encode3DES(key, data));
	 	return result;
	}

	/**
	 * DES解密
	 * @param myinfo
	 * @param key
	 * @return
	 */
	public static byte[] decodc(byte[] myinfo,byte[] key)throws Exception {
		String Algorithm = "DES"; //DES,DESede,Blowfish
		try {
			DESKeySpec dks = new DESKeySpec(key);

			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(Algorithm);
			SecretKey deskey = keyFactory.generateSecret(dks);

			//log.debug("解密前数据=[" + byte2hex(myinfo)+"]");

			Cipher c1 = Cipher.getInstance("DES/ECB/NoPadding");
			c1.init(Cipher.DECRYPT_MODE, deskey);

			byte[] cipherByte = c1.doFinal(myinfo);
			//log.debug("解密结果=[" + byte2hex(cipherByte)+"]");
			return cipherByte;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * DES加密
	 * @param myinfo
	 * @param key
	 * @return
	 */
	public static byte[] encodc(byte[] myinfo,byte[] key)throws Exception{
		// Security.addProvider(new com.sun.crypto.provider.SunJCE());
		String Algorithm = "DES"; //DES,DESede,Blowfish
		try {
			DESKeySpec dks = new DESKeySpec(key);

			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(Algorithm);
			SecretKey deskey = keyFactory.generateSecret(dks);
			//log.debug("加密前数据=[" + byte2hex(myinfo)+"]");
			Cipher c1 = Cipher.getInstance("DES/ECB/NoPadding");
			//Cipher c1 = Cipher.getInstance("DES");
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			byte[] cipherByte = c1.doFinal(myinfo);
			//log.debug("加密结果=[" + byte2hex(cipherByte)+"]");
			return cipherByte;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	//异或
	public static byte[] XORContent( byte[] content1,byte[] content2 ){
	   int i=0;
	   int len = content1.length;
	   int len2 =  content2.length;
	   System.out.println("len="+len+"len2="+len2);
	   //byte []sOutMAC =new byte[8];
	   byte[] res=new byte[len];
	   for( i=0; i<len; i++){
		   res[i]=(byte) (content1[i]^content2[i]);
	   }
	   return res;
	   
	}
	
	/**
	 * 转成十六进制数字
	 * @param n
	 * @return
	 */
	public static String Int2HexStr(int n){
		String HexStr=Integer.toHexString(n);
		HexStr=HexStr.length()==1?"0"+HexStr:HexStr;
		return HexStr;
	}
	
	public static byte[] getDESKey(String hexKey){
		String strKey=hexKey.toUpperCase();
		return hexStringToByte(strKey);
	}
	
	/**
	 * PIN加密
	 * @param Pan 卡号
	 * @param Pin 密码
	 * @param PinKey 密码密钥
	 * @return
	 */
	public static String encryptPin(String Pan,String Pin,String PinKey)throws Exception{
		String DesPin="";
		try{
			Pan=Pan.substring(Pan.length()-13, Pan.length()-1);
			int PanLen=Pan.length();
			for(int i=0;i<16-PanLen;i++)
			{
				Pan="0"+Pan;
			}
			Pin=Int2HexStr(Pin.length())+Pin;
			int PinLen=Pin.length();
			for(int j=0;j<16-PinLen;j++)
			{
				Pin+="F";
			}
			byte[] res=XORContent(hexStringToByte(Pin),hexStringToByte(Pan));
			log.debug("异或数据=["+Pan+"]["+Pin+"]");
			log.debug("异或的结果=["+byte2hex(res)+"]");
			if(PinKey.length()==32)
			{
				log.debug("=====加密开始========");
				log.debug("加密数据=["+byte2hex(res)+"]["+PinKey+"]");
				byte[] res1=encode3DES(getDESKey(PinKey),res);
				DesPin=byte2hex(res1);
				log.debug("加密的结果=["+DesPin+"]");
				log.debug("=====加密结束========");
			}else{
				log.debug("=====加密开始========");
				log.debug("加密数据=["+byte2hex(res)+"]["+PinKey+"]");
				byte[] res1=encodc(res,getDESKey(PinKey));
				DesPin=byte2hex(res1);
				log.debug("加密的结果=["+DesPin+"]");
				log.debug("=====加密结束========");
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return DesPin;
	}
	
	/**
	 * 密码解密
	 * @param Pan
	 * @param DesPin
	 * @param PinKey
	 * @return
	 * @throws Exception
	 */
	public static String decryptPin(String Pan,String DesPin,String PinKey)throws Exception{
		String pin = "";
		try{
			Pan=Pan.substring(Pan.length()-13, Pan.length()-1);
			int PanLen=Pan.length();
			for(int i=0;i<16-PanLen;i++){
				Pan="0"+Pan;
			}
			
			byte[] res = null;
			if(PinKey.length()==32){
				res = hexStringToByte(decode3DES(DesPin,PinKey));
			}else{
				res = decodc(hexStringToByte(DesPin),hexStringToByte(PinKey));
			}
			pin = byte2hex(XORContent(res,hexStringToByte(Pan)));
			int len = Integer.parseInt(pin.substring(0, 2), 10);
			
			pin = pin.substring(2,2+len);
		}catch (Exception e) {
			//e.printStackTrace();
			throw new Exception("密码密钥错误！",e);
		}
		return pin;
	}
	
	/**
	 * 二磁道加密
	 * @param trkKey
	 * @param track
	 * @return
	 */
	public static String encryptStanderTranck(String trkKey,String track)throws Exception{	
		log.debug("trkKey:"+trkKey);
		int trkLen=track.length();
		//track=Int2HexStr(trkLen)+track;
		int mod=trkLen%16;
		log.debug("mod:"+mod);
		for(int i=0;i<16-mod;i++)
		{
			track+="F";
		}
		
		String mTrack="";
		try {
			log.debug("track:"+track);
			mTrack=byte2hex(encode3DES(hexStringToByte(trkKey),hexStringToByte(track)));
			mTrack=Int2HexStr(trkLen)+mTrack;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return mTrack;
	}
	
	/**
	 * 二磁道解密
	 * @param trkKey
	 * @param track
	 * @return
	 */
	public static String decryptStanderTranck(String trkKey,String desTtrack)throws Exception{	
		String track = "";
		try {
			int len = Integer.parseInt(desTtrack.substring(0, 2), 16);
			desTtrack = desTtrack.substring(2);
			track = decode3DES(desTtrack,trkKey);
			track = track.substring(0,len).toLowerCase();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("二磁道密钥错误！");
		}
		return track;
	}
	
	public static String decrypt3DES(String data, String key) throws Exception {
        if (key.length() != 32) {
            throw new InvalidKeyException();
        }

        String firstKey = key.substring(0, 16);
        String secondKey = key.substring(16, 32);
        String result = null;
        result = decryptDES(data, firstKey);
        result = encryptDES(result, secondKey);
        result = decryptDES(result, firstKey);

        return result;
    }
	public static String decryptDES(String data, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(hex2byte(key), "DES");
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte decryptedData[] = cipher.doFinal(hex2byte(data));
        return byte2hex(decryptedData);
    }
	
	   public static String encryptDES(String data, String key) throws Exception {
	        SecretKeySpec secretKey = new SecretKeySpec(hex2byte(key), "DES");
	        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	        byte[] encryptedData = cipher.doFinal(hex2byte(data));
	        return byte2hex(encryptedData);
	    }
	    
	   /**
     * 功能：16进制字符字串转化成byte数值数组
     * 
     * @param hex
     *            16进制字符字串
     * @return 16进制字符字串转化成byte数值的数组
     */
    public static byte[] hex2byte(String hex) throws IllegalArgumentException {
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException();
        }
        char[] arr = hex.toCharArray();
        byte[] b = new byte[hex.length() / 2];
        for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
            String swap = "" + arr[i++] + arr[i];
            int byteint = Integer.parseInt(swap, 16) & 0xFF;
            b[j] = new Integer(byteint).byteValue();
        }
        return b;
    }
    
    
    /** 
     * ANSIX9.8格式
     * 
     * @param strPassword
     * @param strCardNo
     * @return
     */
    public static byte[] pinBlock(String strPassword, String strCardNo) {
        // PIN BLOCK - 8位
        byte[] bytesPin = new byte[] { (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
                (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF };
        System.out.println("strPassword:" + strPassword);
        System.out.println("strPassword.length():" + strPassword.length());
        bytesPin[0] = (byte) strPassword.length();
        byte[] bcdPwd = str2bcd(strPassword);
        System.arraycopy(bcdPwd, 0, bytesPin, 1, bcdPwd.length);
        // PAN - 这里没算了前面的0，是6位
        int nLength = strCardNo.length();
        if (nLength == 20) {
            nLength = 19;
        }
        String strCardNo12 = strCardNo.substring(nLength - 13, nLength - 1);
        System.out.println("pan:" + strCardNo12);
        byte[] bcdPAN = str2bcd(strCardNo12);
        // 异或
        byte[] bytesPinBlock = new byte[8];
        bytesPinBlock[0] = bytesPin[0];
        bytesPinBlock[1] = bytesPin[1];
        for (int i = 2; i < 8; i++) {
            bytesPinBlock[i] = (byte) (bytesPin[i] ^ bcdPAN[i - 2]);
        }
        return bytesPinBlock;
    }
    
    /**
     * 功能：10进制串转为BCD码
     * 
     * @param str
     *            待转的字符串
     * @return bcd码编码到的byte数组
     */
    public static byte[] str2bcd(String str) {
        if (str.length() % 2 != 0)
            str = "0" + str;

        StringBuffer sb = new StringBuffer(str);
        ByteBuffer bb = ByteBuffer.allocate(str.length() / 2);

        int i = 0;
        while (i < str.length()) {
            bb
                    .put((byte) ((Integer.parseInt(sb.substring(i, i + 1))) << 4 | Integer
                            .parseInt(sb.substring(i + 1, i + 2))));
            i = i + 2;
        }
        return bb.array();
    }

	
	 /**
     *转PINData
     */
    public static String handleSens(CoreTransInfo coreTransInfo)
            throws Exception {
        String tempPin = Des3.decrypt3DES(coreTransInfo.getPinData(),
                "9E69BFEEC777C27C9E69BFEEC777C27C");
        String tmpStr = pwdFromPinBlock(Des3.hex2byte(tempPin),
                "0000000000000000000");
        String tmpBlock = encrypt3DES(byte2hex(Des3.pinBlock(tmpStr, coreTransInfo
                .getPan())), "9E69BFEEC777C27C9E69BFEEC777C27C");
        return tmpBlock;
    }
    
	public static String pwdFromPinBlock(byte[] bytesPinBlock, String strCardNo) {
        // PIN - 8位
        byte[] bytesPin = new byte[8];
        // PAN - 这里没算了前面的0，是6位
        int nLength = strCardNo.length();
        String strCardNo12 = strCardNo.substring(nLength - 13, nLength - 1);
        byte[] bcdPAN = str2bcd(strCardNo12);
        // 异或
        bytesPin[0] = bytesPinBlock[0];
        bytesPin[1] = bytesPinBlock[1];
        for (int i = 2; i < 8; i++) {
            bytesPin[i] = (byte) (bytesPinBlock[i] ^ bcdPAN[i - 2]);
        }

        String strPwd = byte2hex(bytesPin).substring(2, 8);
        return strPwd;
    }
	
	/**
     * 3DES Encrypt
     */
    public static String encrypt3DES(String data, String key) throws Exception {
        if (key.length() != 32) {
            throw new InvalidKeyException();
        }

        String firstKey = key.substring(0, 16);
        String secondKey = key.substring(16, 32);
        String result = null;
        result = encryptDES(data, firstKey);
        result = decryptDES(result, secondKey);
        result = encryptDES(result, firstKey);

        return result;
    }
    
    public static String PassToData(String mainKey,String pinKey,String pan,String mingPin){
        String pinBlock = "06"+mingPin+"FFFFFFFF";
        byte[] re = StringUtil.hexStringToByteArray(pinBlock);
        String panKey = "0000"+pan.substring(pan.length()-13,pan.length()-1);
        System.out.println("panKey="+panKey);
        byte[] teb = StringUtil.hex2byte(panKey);
        byte[] edb = StringUtil.XOR(re, teb);
        String mppKey = StringUtil.byteArrayToHexString(edb);
        System.out.println("mppKey="+mppKey);
        String pinData="";
        
//        String mppKey = StringUtil.byteArrayToHexString(Tools.decode3DES(StringUtil.hexStringToByteArray(mpKey), StringUtil.hexStringToByteArray(pinData)));
        try {
            String mpKey = StringUtil.byteArrayToHexString(decode3DES(StringUtil.hexStringToByteArray(mainKey), StringUtil.hexStringToByteArray(pinKey)));
            System.out.println("mpKey="+mpKey);
            pinData = StringUtil.byteArrayToHexString(encode3DES(StringUtil.hexStringToByteArray(mpKey), StringUtil.hexStringToByteArray(mppKey)));
            System.out.println("pinData="+pinData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pinData;
    }
    
    
    public static byte[] decode3DES(byte[] key,byte[] data ){
        try {
               byte[] km = new byte[24];
               System.arraycopy(key, 0, km, 0, 16);
               System.arraycopy(key, 0, km, 16, 8);
               Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
               DESedeKeySpec dks = new DESedeKeySpec(km);
               SecretKey k = SecretKeyFactory.getInstance("DESede")
                       .generateSecret(dks);
               cipher.init(Cipher.DECRYPT_MODE, k);
               byte[] result = cipher.doFinal(data);
               return result;
           } catch (Exception e) {
               e.printStackTrace();
               throw new RuntimeException("" + e);
           }
    }
    
    public static String encryptECB(String encryptString, String encryptKey) throws Exception {    
        DESedeKeySpec dks = new DESedeKeySpec(encryptKey.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        Key secreKey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding ");    
        cipher.init(Cipher.ENCRYPT_MODE, secreKey,new SecureRandom());    
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes("utf-8"));    
        byte[] bb = Base64.encodeBase64(encryptedData);
        return new String(bb,"UTF-8");
    }    
    
    
    public static void main(String[] args) throws Exception {
        
//        Des3.PassToData("E98E6E49A376772BDC943FD9D9476A27","1D014F4CA56BFDFD10656292ACCE7EAA", "6223330816895527", "123456");
        String rsaKey="OPJURIZFIWZWJIFDNXUXWSMFILOCLJQK";
        String customerInfo="4267421273610498563|吴海泉|01|18626269633|||";
        System.out.println(encryptECB(customerInfo,rsaKey));;
        
    }
    
//  public static void main(String args[]){
//      String s1 = "明文数据";
//      System.out.println("明文数据:"+s1);
//      try {
//          String s2 = encode(s1);
//          System.out.println("密文数据:"+s2);
//          String s3 = decode(s2);
//          System.out.println("解密数据:"+s3);
//          
//      } catch (Exception e) {
//          e.printStackTrace();
//      }
//  }
    
}

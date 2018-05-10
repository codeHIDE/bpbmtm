package com.bypay.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Jiami {
	private static String daijiami="E:/ms/民生银行待加密/";
	private static String jiami="E:/ms/民生银行已加密/";
	private static String daijiemi="E:/ms/民生银行待解密/";
	private static String jiemi="E:/ms/民生银行已解密/";
	private static String temp="E:/ms/temp/";
	private static byte[] key = "1E5B6F08AD2400B8".getBytes();
	public static void main(String[] args) {
		makeDir();
		jiami();
		jiemi();
	}
	
	//加密文件
	public static void jiami(){
		File file = new File(daijiami);
		File[] daijiamiFiles = file.listFiles();
		// 明文文件路径
		String plainFilePath = "";
		// 密文文件路径
		String secretFilePath = "";
		try {
			for(File daijiamiFile :daijiamiFiles){
				secretFilePath=jiami+"M"+daijiamiFile.getName();
				plainFilePath = daijiami+daijiamiFile.getName();
	//			 加密
				encodeAESFile(key, plainFilePath, secretFilePath);
				daijiamiFile.renameTo(new File(temp+daijiamiFile.getName()));
			}
		} catch (Exception e) {
			printLog(e.getMessage());e.printStackTrace();
		}
	}
	
	//解密文件
	public static void jiemi(){
		File file = new File(daijiemi);
		File[] daijiamiFiles = file.listFiles();
		// 明文文件路径
		String plainFilePath = "";
		// 密文文件路径
		String secretFilePath = "";
		try {
			for(File daijiamiFile :daijiamiFiles){
				plainFilePath=jiemi+"A"+daijiamiFile.getName();
				secretFilePath = daijiemi+daijiamiFile.getName();
				
				decodeAESFile(key, plainFilePath, secretFilePath);
				daijiamiFile.renameTo(new File(temp+daijiamiFile.getName()));
			}
		} catch (Exception e) {
			printLog(e.getMessage());e.printStackTrace();
		}
	}
	
	public static void makeDir(){
		//用于建立文件夹
		File file = new File("E:/ms");
		if(!file.exists())
			file.mkdir();
		file = new File(daijiami);
		if(!file.exists())
			file.mkdir();
		file = new File(jiami);
		if(!file.exists())
			file.mkdir();
		file = new File(daijiemi);
		if(!file.exists())
			file.mkdir();
		file = new File(jiemi);
		if(!file.exists())
			file.mkdir();
		file = new File(temp);
		if(!file.exists())
			file.mkdir();
	}
	
	
	/**
	 * AES加密
	 * 
	 * @param key
	 *            密钥信息
	 * @param content
	 *            待加密信息
	 */
	public static byte[] encodeAES(byte[] key, byte[] content) throws Exception {
		// 不是16的倍数的，补足
		int base = 16;
		if (key.length % base != 0) {
			int groups = key.length / base + (key.length % base != 0 ? 1 : 0);
			byte[] temp = new byte[groups * base];
			Arrays.fill(temp, (byte) 0);
			System.arraycopy(key, 0, temp, 0, key.length);
			key = temp;
		}

		SecretKey secretKey = new SecretKeySpec(key, "AES");
		IvParameterSpec iv = new IvParameterSpec(new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
		byte[] tgtBytes = cipher.doFinal(content);
		return tgtBytes;
	}

	/**
	 * AES解密
	 * 
	 * @param key
	 *            密钥信息
	 * @param content
	 *            待加密信息
	 * @return
	 * @throws Exception
	 */
	public static byte[] decodeAES(byte[] key, byte[] content) throws Exception {
		// 不是16的倍数的，补足
		int base = 16;
		if (key.length % base != 0) {
			int groups = key.length / base + (key.length % base != 0 ? 1 : 0);
			byte[] temp = new byte[groups * base];
			Arrays.fill(temp, (byte) 0);
			System.arraycopy(key, 0, temp, 0, key.length);
			key = temp;
		}

		SecretKey secretKey = new SecretKeySpec(key, "AES");
		IvParameterSpec iv = new IvParameterSpec(new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
		byte[] tgtBytes = cipher.doFinal(content);
		return tgtBytes;
	}

	/**
	 * 加密文件
	 * 
	 * @param key
	 *            密钥
	 * @param plainFilePath
	 *            明文文件路径路径
	 * @param secretFilePath
	 *            密文文件路径
	 * @throws Exception
	 */
	public static void encodeAESFile(byte[] key, String plainFilePath, String secretFilePath) throws Exception {
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
		FileOutputStream fos = null;
		try {

			fis = new FileInputStream(plainFilePath);
			bos = new ByteArrayOutputStream(fis.available());
			byte[] buffer = new byte[1024];
			int count = 0;
			while ((count = fis.read(buffer)) != -1) {
				bos.write(buffer, 0, count);
			}
			bos.flush();

			byte[] bytes = encodeAES(key, bos.toByteArray());

			fos = new FileOutputStream(secretFilePath);
			fos.write(bytes);
			fos.flush();
		} catch (Exception e) {
			throw e;
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (Exception e) {
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * 解密文件
	 * 
	 * @param key
	 *            密钥
	 * @param plainFilePath
	 *            明文文件路径路径
	 * @param secretFilePath
	 *            密文文件路径
	 * @throws Exception
	 */
	public static void decodeAESFile(byte[] key, String plainFilePath, String secretFilePath) throws Exception {
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(secretFilePath);
			bos = new ByteArrayOutputStream(fis.available());

			byte[] buffer = new byte[1024];
			int count = 0;
			while ((count = fis.read(buffer)) != -1) {
				bos.write(buffer, 0, count);
			}
			bos.flush();

			byte[] bytes = decodeAES(key, bos.toByteArray());

			fos = new FileOutputStream(plainFilePath);
			fos.write(bytes);
			fos.flush();
		} catch (Exception e) {
			throw e;
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (Exception e) {
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
				}
			}
		}
	}
	
	public static void printLog(String s){
		try {
			PrintWriter pw = new PrintWriter(new File("E:/ms/log.log"));
			pw.write(s);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			printLog(e.getMessage());e.printStackTrace();
		}
	}
}	

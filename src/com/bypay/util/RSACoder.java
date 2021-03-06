package com.bypay.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public abstract class RSACoder {
	public static final String KEY_ALGORITHM = "RSA";
	public static final String PUBLIC_KEY = "RSAPublicKey";
	public static final String PRIVATE_KEY = "RSAPrivateKey";
	public static final String KEY_DES_PADDING = "DESede/ECB/PKCS5Padding";
	public static final String KEY_DES = "DESede";

	private static final int KEY_SIZE = 1024;
	public final static String KEY_FTB_PUBLICKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCT1aeuD6Mq/xZI3p7KubYbll3ifChlR9OPlLku70m83ODnMzH/O9CoeMY8ID8HwHMph3FAMOfMqZ4nPKSy+3fPjmaa3YSPNgu5a5ReqcvQm4oQKjVeQGdgpe47ZsSM1IEOF6kyoKfDWf0ZFtAAUoEvHuWRS+M4sE5WvlJS8YsVvQIDAQAB";
	public final static String KEY_PADDING = "RSA/ECB/PKCS1Padding";
	
	// 私钥解密
	public static byte[] decryptByPrivateKey(byte[] data, byte[] key)
			throws Exception {
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	// 私钥解密2
	public static String decryptByPrivateKey(String data, String key)
			throws Exception {
		BASE64Decoder dec = new BASE64Decoder();
//		BASE64Encoder enc = new BASE64Encoder();
		byte[] dataByte = decryptByPrivateKey(dec.decodeBuffer(data),
				dec.decodeBuffer(key));
		return new String(dataByte);
	}

	// 公钥解密
	public static byte[] decryptByPublicKey(byte[] data, byte[] key)
			throws Exception {
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);

		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);

		return cipher.doFinal(data);
	}

	// 公钥解密2
	public static String decryptByPublicKey(String data, String key)
			throws Exception {
		BASE64Decoder dec = new BASE64Decoder();
//		BASE64Encoder enc = new BASE64Encoder();
		byte[] dataByte = decryptByPublicKey(dec.decodeBuffer(data),
				dec.decodeBuffer(key));
		return new String(dataByte);
	}

	// 公钥加密
	public static byte[] encryptByPublicKey(byte[] data, byte[] key)
			throws Exception {
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}
	
	public static byte[] encryptByPublicKeyToBusiness(byte[] data, byte[] key)
			throws Exception {
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(KEY_PADDING);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}

	// 公钥加密2
	public static String encryptByPublicKey(String data, String key)
			throws Exception {
		BASE64Decoder dec = new BASE64Decoder();
		BASE64Encoder enc = new BASE64Encoder();
		byte[] signByte = encryptByPublicKey(data.getBytes(),
				dec.decodeBuffer(key));
		return enc.encode(signByte);
	}

	// 私钥加密
	public static byte[] encryptByPrivateKey(byte[] data, byte[] key)
			throws Exception {
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	// 私钥加密2
	public static String encryptByPrivateKey(String data, String key)
			throws Exception {
		BASE64Decoder dec = new BASE64Decoder();
		BASE64Encoder enc = new BASE64Encoder();
		byte[] signByte = encryptByPrivateKey(data.getBytes(),
				dec.decodeBuffer(key));
		return enc.encode(signByte);
	}

	// 私钥验证公钥密文
	public static boolean checkPublicEncrypt(String data, String sign,
			String pvKey) throws Exception {
		return data.equals(decryptByPrivateKey(sign, pvKey));
	}

	public static boolean checkPrivateEncrypt(String data, String sign,
			String pbKey) throws Exception {
		return data.equals(decryptByPublicKey(sign, pbKey));
	}

	// 取得私钥
	public static byte[] getPrivateKey(Map<String, Object> keyMap)
			throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return key.getEncoded();
	}

	// 取得公钥
	public static byte[] getPublicKey(Map<String, Object> keyMap)
			throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		return key.getEncoded();
	}

	// 初始化密钥
	public static Map<String, Object> initKey() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator
				.getInstance(KEY_ALGORITHM);

		keyPairGen.initialize(KEY_SIZE);

		KeyPair keyPair = keyPairGen.generateKeyPair();

		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}

	public static String getPrivate(String path, String pwd) {
		try {
			KeyStore ks = KeyStore.getInstance("PKCS12");
			FileInputStream is = new FileInputStream(path);
			ks.load(is, pwd.toCharArray());
			is.close();
			System.out.println("keystore type=" + ks.getType());
			Enumeration<String> enuma = ks.aliases();
			String keyAlias = null;
			if (enuma.hasMoreElements()) {
				keyAlias =  enuma.nextElement();
				System.out.println("alias=[" + keyAlias + "]");
			}

			System.out.println("is key entry=" + ks.isKeyEntry(keyAlias));
			BASE64Encoder enc = new BASE64Encoder();
			PrivateKey privatekey = (PrivateKey) ks.getKey(keyAlias,
					pwd.toCharArray());
			System.out.println("private key = "
					+ enc.encode(privatekey.getEncoded()));
			return enc.encode(privatekey.getEncoded());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取公钥
	 * 
	 * @param file
	 *            公钥文件
	 * @return
	 */
	public static String getPublic(File file) {
		try {
			CertificateFactory cff = CertificateFactory.getInstance("X.509");
			Certificate cf = cff.generateCertificate(new FileInputStream(file));
			PublicKey key = cf.getPublicKey();

			return Base64.encodeBase64String(key.getEncoded());
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param file
	 *            私钥文件
	 * @param pwd
	 *            密码
	 * @return
	 */
	public static String getPrivate(File file, String pwd) {
		KeyStore ks = null;
		try {
			ks = KeyStore.getInstance("PKCS12");
			ks.load(new FileInputStream(file), pwd.toCharArray());
			Enumeration<String> alises = ks.aliases();
			String alise = null;
			if (alises.hasMoreElements()) {
				alise = alises.nextElement();
			}
			PrivateKey key = (PrivateKey) ks.getKey(alise, pwd.toCharArray());
			return Base64.encodeBase64String(key.getEncoded());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 验证私钥与公钥是否匹配
	 * 
	 * @param privateKey
	 * @param publicKey
	 * @return
	 */
	public static boolean validateKey(String privateKey, String publicKey) {
		// TODO Auto-generated method stub
		if (privateKey == null || publicKey == null) {
			return false;
		}
		String data = "298154a877da66b9eccba11ba875aeaf";
		byte[] decodedata = null;
		try {
			byte[] encoderData = encryptByPrivateKey(data.getBytes("UTF-8"),
					Base64.decodeBase64(privateKey));
			decodedata = decryptByPublicKey(encoderData,
					Base64.decodeBase64(publicKey));
			return data.equals(new String(decodedata, "UTF-8"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * DESec加密
	 * 
	 * @param data
	 *            需要加密过的数据
	 * @param key
	 *            密钥(私钥或者公钥)
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptDESec(byte[] data, byte[] key) throws Exception {
		Key keys = toKey(key);
		Cipher cipher = Cipher.getInstance(KEY_DES_PADDING);
		cipher.init(Cipher.ENCRYPT_MODE, keys);
		return cipher.doFinal(data);
	}

	/**
	 * DESec解密
	 * 
	 * @param data
	 *            需要解密的数据
	 * @param key
	 *            密钥(私钥或者公钥)
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptDesc(byte[] data, byte[] key) throws Exception {
		Key keys = toKey(key);
		Cipher cipher = Cipher.getInstance(KEY_DES_PADDING);
		cipher.init(Cipher.DECRYPT_MODE, keys);
		return cipher.doFinal(data);
	}

	// public static byte[] encryptDESec(byte[] data,String keyy) throws
	// Exception {
	// byte [] key=decryptBASE64(keyy);
	// Key keys = toKey(key);
	// Cipher cipher = Cipher.getInstance(KEY_DES_PADDING);
	// cipher.init(Cipher.ENCRYPT_MODE, keys);
	// return cipher.doFinal(data);
	// }

	private static Key toKey(byte[] key) throws Exception {
		DESedeKeySpec dks = new DESedeKeySpec(key);
		SecretKeyFactory skf = SecretKeyFactory.getInstance(KEY_DES);
		return skf.generateSecret(dks);
	}
	
	  public static String yinLianSign(String text, String privateKey, String charset)
	          throws Exception
        {
          byte[] keyBytes = Base64.decodeBase64(privateKey);
          PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
          KeyFactory keyFactory = KeyFactory.getInstance("RSA");
          PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
          Signature signature = Signature.getInstance("SHA1withRSA");
          signature.initSign(privateK);
          signature.update(getContentBytes(text, charset));
          byte[] result = signature.sign();
          return Base64.encodeBase64String(result);
        }
	  
	  private static byte[] getContentBytes(String content, String charset)
	  {
	    if ((charset == null) || ("".equals(charset)))
	      return content.getBytes();
	    try
	    {
	      return content.getBytes(charset); } catch (UnsupportedEncodingException e) {
	    }
	    throw new RuntimeException("签名过程中出现错误。指定的编码集不对,您目前指定的编码集是:" + charset);
	  }
	  
	public static void main(String[] args) {
	    String signKey="MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALHSQP5Hq39D/nU4Xa2XJZxfaHNx+InSNjjynpvMe0ejuYuf7ghbOC3TvYqeDNoesY3lFr/5Sh33qb+Es46jow1VlGPJJBkZ/KXzV/uqRO9rB5rWiGdCGzPbKby719bvXnIIVJBe86gLPHXrjHABwQUkjaoIQFHJmdTpcwYFwZk/AgMBAAECgYAIN/SDDCL0Bdt75XgG7uZxHMPCGjFnhUy2QxhrkP7dp8aKmoCw6C5nh9LJ1lY3upVwPndXthjj269/x41Y+V0uy9eNImhp2IF13lgPXFmuY3G0aV+3f3BDQcgc84VypdbEURWLg7q5zts3uFRwdpDmOGueizOyBL7Z4GsCY+mLMQJBAOBFjGsy2TS8E6eKxgbeCYq6+G9onOZcmGoDEX41JlW8hGJMsNZz7KlAnvEMfrSB4v7PYXxe2q9xl4xtZ4yKiOcCQQDK+mlUg8eNE2h6H2Jls5OL8bT9YiWJ04Iq+KFMegXv+SLSadyNS8sjWt83AACZR9tLtGXjgQNyUZmh6ZNokinpAkEAtga82aUDtlGQgkOYRqqghAEM8x+teSteaWzkHdN1sdC4gjBMt0KPqy/P0UWa8VcarkYTkaZLSGqh3lBN4zvtsQJBAKzGVGwtM+oA8DiriE07e0du+fmI1p0oHa/ILTx0zaMD9UEX/TWEo6g3jLM7XEcdJCyfpO7vfWiMzvJEaUZkzCkCQQC0RjIsYNBEFq07digfUNummebzwDGqh+S2xMNbA/ab7tr/7efKPUAINVmMIOHw/pTWnikzWPNfY5r5M7WITkIV";
        String customerInfo="version=1.0.0&transType=1006&termType=03,04&merId=201705260001&supMerId=8088000000000001&name=星译付商户&shortName=星译付&tel=18626269633&business=56&customerInfo=SczuUeeTwagYYqAOABFaIfV7qfE00c9G/rpki2FK0timnr5VCLAlp3HSkTmL03Il&billCycle=02&fee0=0.2&fee1=0.2&fee2=0.2&fee3=0.2";

		try {
			String a = encryptByPublicKey(customerInfo,signKey);
			System.out.println(a);
		String i = 	decryptByPrivateKey(a,"MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKEPfNisvXwbSy92NWI2hL46589LRygHyBDuVQXHE1rlxVOZdVx9uddd54+JwvCVuWaL39Ns9LB2g5qpmVZ+8iLL/93mHC99KVPaJ9QI66srren2HnRKmfB5HLdGmFoJetlzdiovD9uQRvamS0iECx8kwxQ4aj8kwZO2syeLnrPxAgMBAAECgYEAhBTAbKyUpB959A070DQnfh2ulsgELabcAk6BeUB99fAyd9GEdnpAmobO7F6seEJBDgCtaKSUsdYvLPni3xUyGdtvQFoH/xzv2XMQyvpYTexIrDiJr3MEpDJd8Iid5ZubpMWqSOmutJtLfV3gC0MGfQm6+nZdGfiXV7ohFRMmwUECQQDY5NNtQcsf+DKv4xJdEZHU6tIK/A0wcswI5QMd/v8l9FGjyX14ApYSCdnPhQZtqWzwuQlUxWPgqSAi1BE8KqEJAkEAvhmPgMqv3co1YXRJ47zVF+ACNOhlaaG8x+OXHBfv2NN6jvYAfHdELa/tYFlWLazOpy0Q0CU26+5rT6h3V1F9qQJAU+6RBrmsMi3o53mWtJ9E8MECETAipnn2DQcaYrQ35mcaZKhnPla53jcjq5ONvkgPGURxoPVVxi2Mew3XsZHJiQJADFbgZ73AWKcte9vuh+fT9S7HNeP34TlsZZUyU9KB8RMZG3qAYZPkSwrmX6Cs5V4YM+XK95fSztG1CYCn7nUNsQJAWanfSF18w5GilDnygQg62rP59X2XNqSLxjLaD15QiodhiiiFrWVjMoFgukkebNCO9ZLGz1jTnMx/9sQJgFxtfQ==");
			System.out.println(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

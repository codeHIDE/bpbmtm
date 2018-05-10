package com.bypay.domain.clientTool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.commons.codec.binary.Base64;

import com.Ostermiller.util.MD5;
import com.bypay.domain.MerInfo;
import com.bypay.util.RSACoder;
import com.bypay.util.XmlUtil;

public class BankUtils {

	public static String getCodeByName(String name) {

		try {
			System.out.println(name);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					BankUtils.class.getClassLoader().getResourceAsStream(
							"com/bypay/config/bank.txt"), "UTF-8"));
			String apString = null;
			int code = 10000;
			boolean _exsits = false;
			while ((apString = br.readLine()) != null) {
				code++;
				if (name.equals(apString.trim())) {
					_exsits = true;
					break;
				}
			}
			if (_exsits) {
				return code + "";
			} else {
				return null;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 到业务平台添加代发商户
	 * @param merId  商户号
	 * @param pubKey  公钥
	 * @param merchantName  商户名
	 * @param backUrl   后台通知地址
	 * @param superMerId   上级商户ID
	 * @return
	 * @throws Exception
	 */
	public static String[] toBusinessMerAdd(MerInfo bbMerConfig, String pubKey,
			String merchantName, String superMerId)
			throws Exception {

		TransInfoReq tir = new TransInfoReq();
		tir.setApplication("GwBiz.Req");
		tir.setVersion("1.0.0");
		tir.setSendTime(dateFormat("yyyyMMddHHmmss", new Date()));
		tir.setTransCode("7015");
		tir.setMerchantName(merchantName);
		tir.setMerchantId(bbMerConfig.getMerSysId().toString());
		tir.setGwType("00");
		tir.setBizType("06");
		tir.setTransType("10");
		tir.setBackUrl(bbMerConfig.getBackUrl());
		tir.setMsgExt(bbMerConfig.getMerSysId()+"|0|"+"HXB");
		tir.setMisc(bbMerConfig.getRate()+"|"+bbMerConfig.getPayTract());
		String[] message = packageReport(pubKey, tir);
		return message;

	}
	
	

	/**
	 * 封装成业务可识别的报文
	 * 
	 * @param pubKey  公钥
	 * @param tir  报文信息
	 * @return
	 * @throws Exception
	 * @throws UnsupportedEncodingException
	 */
	private static String[] packageReport(String pubKey, TransInfoReq tir)
			throws Exception, UnsupportedEncodingException {
		String xml = null;
		String[] message = new String[2];
		try {
			xml = XmlUtil.ObjToXml(tir);
			System.out.println(">>>" +xml);
			// 加密报文体格式：BASE64(前置标示)| BASE64(RSA(报文加密密钥))| BASE64(3DES(报文原文))
			StringBuffer sb = new StringBuffer();
			sb.append(Base64.encodeBase64String("1".getBytes()));
			sb.append("|");
			message[0] = getDesKey();
			byte[] dks = message[0].getBytes();
			sb.append(Base64.encodeBase64String(RSACoder
					.encryptByPublicKeyToBusiness(dks,
							Base64.decodeBase64(pubKey))));
			sb.append("|");
			sb.append(Base64.encodeBase64String(RSACoder.encryptDESec(
					xml.getBytes("UTF-8"), dks)));
			message[1] = sb.toString();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return message;
	}

	/**
	 * 准备报文数据
	 * 
	 * @param lists
	 * @param merId
	 * @return String[] length 2 0 key 1报文体
	 * @throws Exception
	 */
	public static String[] preBehalfToBusiness(List<ProxyPay> lists,
			String merId, String pubKey) throws Exception {
		String[] message = new String[2];
		// TODO Auto-generated method stub
		TransInfoReq tir = new TransInfoReq();
		tir.setApplication("GwBiz.Req");
		tir.setVersion("1.0.0");
		tir.setSendTime(dateFormat("yyyyMMddHHmmss", new Date()));
		tir.setTransCode("7011");
		tir.setMerchantId(merId);
		tir.setMerchantOrderId(dateFormat("yyyyMMddHHmmssSSS", new Date()));
		tir.setMerchantOrderTime(dateFormat("yyyyMMddHHmmss", new Date()));
		Integer amount = 0;
		for (ProxyPay pp : lists) {
			amount += Integer.valueOf(pp.getAmt());
		}
		tir.setMerchantOrderAmt(amount + "");
		tir.setMerchantOrderCurrency("156");
		tir.setGwType("00");
		tir.setBizType("06");
		tir.setTransType("10");
		tir.setTransNum(lists.size() + "");
		tir.setLists(lists);
		String xml = null;
		try {
			xml = XmlUtil.ObjToXml(tir);
			// 加密报文体格式：BASE64(前置标示)| BASE64(RSA(报文加密密钥))| BASE64(3DES(报文原文))
			StringBuffer sb = new StringBuffer();
			sb.append(Base64.encodeBase64String("1".getBytes()));
			sb.append("|");
			message[0] = getDesKey();
			byte [] dks=message[0].getBytes();
			sb.append(Base64.encodeBase64String(RSACoder.encryptByPublicKeyToBusiness(dks, Base64.decodeBase64(pubKey))));
			sb.append("|");
			sb.append(Base64.encodeBase64String(RSACoder.encryptDESec(
					xml.getBytes("UTF-8"), dks)));
			message[1] = sb.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
		return message;
	}

	/**
	 * 准备报文数据
	 * 
	 * @param lists
	 * @param merId
	 * @return String[] length 2 0 key 1报文体
	 * @throws Exception
	 */
	public static String [] preBehalfToBusinessSub(List<ProxyPay> lists,
			String merId, String pubKey,String orderId,String amt) throws Exception {
		String[] message = new String[2];
		// TODO Auto-generated method stub
		TransInfoReq tir = new TransInfoReq();
		tir.setApplication("GwBiz.Req");
		tir.setVersion("1.0.0");
		tir.setSendTime(dateFormat("yyyyMMddHHmmss", new Date()));
		tir.setTransCode("7011");
		tir.setMerchantId(merId);
		tir.setMerchantOrderId(orderId);
		tir.setMerchantOrderTime(dateFormat("yyyyMMddHHmmss", new Date()));		
		
		//
		tir.setMerchantOrderAmt(amt);
		tir.setMerchantOrderCurrency("156");
		tir.setGwType("00");
		tir.setBizType("06");
		tir.setTransType("10");
		tir.setTransNum(lists.size() + "");
		tir.setLists(lists);
			
		String xml = null;
		try {
			xml = XmlUtil.ObjToXml(tir);
			// 加密报文体格式：BASE64(前置标示)| BASE64(RSA(报文加密密钥))| BASE64(3DES(报文原文))
			StringBuffer sb = new StringBuffer();
			sb.append(Base64.encodeBase64String("1".getBytes()));
			sb.append("|");
			message[0] = getDesKey();
			byte [] dks=message[0].getBytes();
			sb.append(Base64.encodeBase64String(RSACoder.encryptByPublicKeyToBusiness(dks, Base64.decodeBase64(pubKey))));
			sb.append("|");
			sb.append(Base64.encodeBase64String(RSACoder.encryptDESec(
					xml.getBytes("UTF-8"), dks)));
			message[1] = sb.toString();
			System.out.println("请求业务平台代发密文："+xml);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			xml=null;
			return null;
		}
	
		return message;
	}

	/**
	 * 获取RSA加密的Key
	 * 
	 * @return
	 */
	public static String getDesKey() {
		String rand = Math.random() * 10000 + "";
		return MD5.getHashString(rand);
	}

	public static String dateFormat(String pattern, Object obj) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		if (obj == null) {
			return null;
		}
		if (obj instanceof Date) {
			return sdf.format(obj);
		} else {

			try {
				return sdf.format(sdf.parse((String) obj));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}
	/**
	 * 代发商户充值
	 * @param merId  商户号
	 * @param pubKey   公钥
	 * @param balance   余额
	 * @param chargeBalance  手续费余额
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	public static String[] toBusinessBalanceReCharge(String merId,
			String pubKey, String balance, String chargeBalance,String merchantOrderId)
			throws UnsupportedEncodingException, Exception {

		// TODO Auto-generated method stub
		TransInfoReq tir = new TransInfoReq();
		tir.setApplication("GwBiz.Req");
		tir.setVersion("1.0.0");
		tir.setSendTime(dateFormat("yyyyMMddHHmmss", new Date()));
		tir.setTransCode("7016");
		tir.setMerchantId(merId);
		tir.setGwType("00");
		tir.setBizType("06");
		tir.setTransType("10");
		tir.setBalance(balance);
		tir.setMerchantOrderId(merchantOrderId);
		tir.setChargeBalance(chargeBalance);
		String[] message = packageReport(pubKey, tir);
		return message;
	}
}

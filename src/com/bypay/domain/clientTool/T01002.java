package com.bypay.domain.clientTool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.rd.constant.ValueConstant;
import com.rd.model.MerchantBaseMsg;
import com.rd.model.MerchantPayReq;
import com.rd.util.HttpClientUtil;
import com.rd.util.MerchantUtil;

/**
 * 快捷支付（网页跳转，限制卡号）
 * @author Administrator
 *
 */
public class T01002 {
	private static final Logger logger = LoggerFactory.getLogger(T01002.class);
	private String merchantPrivateKey;
	private String aesKey;
	private String merId;
	private String submitUrl;
	
	public T01002(String merchantPrivateKey, String aesKey, String merId, String submitUrl) {
		super();
		this.merchantPrivateKey = merchantPrivateKey;
		this.aesKey = aesKey;
		this.merId = merId;
		this.submitUrl = submitUrl;
	}
	
	
	public void payment() throws Exception{
		String transactionId = "ZF"+new SimpleDateFormat("yyyyMMddHHmmdd").format(new Date());
		MerchantPayReq req = new MerchantPayReq();
		req.setPageUrl("http://www.mi.com/");
		req.setBgUrl("http://www.mi.com/");
		req.setTransactionId(transactionId);
		req.setOrderAmount("1");//以元为单位
		req.setOrderDesc("测试单");
		req.setCur("CNY");
		req.setProductName("小米MIX");
		req.setProductDesc("小米开卖狂野之梦般的概念手机 ");
		req.setPayType(ValueConstant.PAY_TYPE_1002);
		req.setSettleAging("T0");
		req.setPayerAcc("623094332000017136666");
		req.setPayeeBankName("中国建设银行");
		req.setPayeeAcc("6230943320000171366");
		req.setPayeeIdNum("440882198912068932");
		req.setPayeeName("张三");
		req.setFeeRate("0.4");
		String jsonStr = JSON.toJSONString(req);
		String signData = MerchantUtil.sign(jsonStr, this.merchantPrivateKey, MerchantUtil.SIGNTYPE_RSA, "UTF-8").replaceAll("\\\\r\\\\n", "");
		System.out.println("jsonStr="+jsonStr);
		System.out.println("merchantPrivateKey="+merchantPrivateKey);
		System.out.println("signData="+signData);
		
		logger.info("请求报文：{}", signData);
		System.out.println("请求:"+signData);
		//2.加密
		String businessContext = MerchantUtil.encryptDataByAES(signData, this.aesKey, "UTF-8");
		logger.info("密文：{}",businessContext);
		System.out.println("businessContext="+businessContext);
		//3.组装报文
		MerchantBaseMsg baseReq = new MerchantBaseMsg();
		baseReq.setVersion("1.0");
		baseReq.setMerId(this.merId);
		baseReq.setTransCode(ValueConstant.TRANS_CODE_T01002);
		baseReq.setOrderTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		baseReq.setSignType("RSA");
		baseReq.setCharse("UTF-8");
		baseReq.setBusinessContext(businessContext);
		
		//4.发送报文
		@SuppressWarnings("unchecked")
		Map<String,String> requestParams = JSON.parseObject(JSON.toJSONString(baseReq), Map.class);
		String rspStr = HttpClientUtil.doPost(requestParams, this.submitUrl);
		logger.info("回应报文:{}",rspStr);
		System.out.println("回应:"+rspStr);
		//rspStr是html页面，直接返回给下游就可以了  response.setContentType("text/html;charset=UTF-8");  response.getWriter().write(rspStr);
		/*response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(rspStr);*/
		
		//后端通知使用 MerchantPayRsp
	}
	
}

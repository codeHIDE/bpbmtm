package com.bypay.service;

import net.sf.json.JSONObject;

import com.bypay.domain.AccountManage;
import com.bypay.domain.SubMerTerminal;
import com.bypay.domain.clientTool.AccountBalance;
import com.bypay.domain.clientTool.AccountEnquiry;
import com.bypay.domain.clientTool.AccountLogIn;
import com.bypay.domain.clientTool.AccountRegist;
import com.bypay.domain.clientTool.CheckVersion;
import com.bypay.domain.clientTool.CreditInfoList;
import com.bypay.domain.clientTool.DrawMoneyRecord;
import com.bypay.domain.clientTool.Enquiry;
import com.bypay.domain.clientTool.GFinvestment;
import com.bypay.domain.clientTool.OpenPurchase;
import com.bypay.domain.clientTool.PhoneNumber;
import com.bypay.domain.clientTool.PrdOrderInfo;
import com.bypay.domain.clientTool.QueryBalance;
import com.bypay.domain.clientTool.RegisterMerInfo;
import com.bypay.domain.clientTool.SelectPassWord;
import com.bypay.domain.clientTool.SettBank;
import com.bypay.domain.clientTool.TerminalEnable;
import com.bypay.domain.clientTool.TerminalValidate;
import com.bypay.domain.clientTool.TranSerialListInfo;
import com.bypay.domain.clientTool.UpBankCardInfo;
import com.bypay.domain.clientTool.XunlianOrder;

public interface InterfaceService {

	/**
	 * 收款开通
	 * @param openPurchase
	 * @return
	 */
	Object openReceivablesBus(OpenPurchase openPurchase);
	/**
	 * 登陆
	 */
	Object regMer(AccountLogIn accountLogIn);
	
	Object userMer(AccountLogIn accountLogIn);
	/**
	 * 结算银行列表
	 */
	Object regBank(SettBank settBank);
	/**
	 * 交易列表
	 */
	Object regEnquiry(Enquiry enquiry);
	
	Object regNewEnquiry(Enquiry enquiry);
	//修改终端状态
	Object updateStatus(TerminalEnable terminalId);
	
	//更新终端密码
	Object updatePwd(SelectPassWord selectPassWord);
	/**
	 * 终端验证
	 */
	Object terminalValidate(TerminalValidate terminalValidate);

	/**
	 * 账户申请
	 * @param accountRegist
	 * @return
	 */
	Object checkRegist(AccountRegist accountRegist) throws Exception;
	/**
	 * 账户申请第一步
	 * @param accountRegist
	 * @return
	 */
	Object userRegist(AccountRegist accountRegist) throws Exception;
	/**
	 * 忘记密码
	 * @Title:        forgetPwd 
	 * @Description:  
	 * @param:        @param accountRegist
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Object    
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2015-11-6 下午2:20:00
	 */
	Object forgetPwd(AccountLogIn accountLogIn) throws Exception;
	
	/**
	 * 盛云接口
	 * @Title:        registerMerSY 
	 * @Description:  
	 * @param:        @param registerMerInfo
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Object    
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2015-11-12 下午2:50:49
	 */
	Object registerMerSY(RegisterMerInfo registerMerInfo) throws Exception;
	
	
	Object accountPay(AccountBalance accountBalance) throws Exception;
	Object GFbussiness(GFinvestment gFinvestment) throws Exception;
	
	Object creditbussiness(CreditInfoList creditInfoList) throws Exception;
	
	Object checkVersion(CheckVersion creditInfoList) throws Exception;
	
	Object xunlianOrder(XunlianOrder xunlianOrder) throws Exception;
	/**
	 * 绑定银行卡
	 * @Title:        upBankCard 
	 * @Description:  
	 * @param:        @param upBankCardInfo
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Object    
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2015-11-17 上午9:56:41
	 */
	Object upBankCardSY(UpBankCardInfo upBankCardInfo) throws Exception;
	/**
	 * 下单
	 * @Title:        prdOrder 
	 * @Description:  
	 * @param:        @param prdOrderInfo
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Object    
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2015-11-17 上午9:57:36
	 */
	Object prdOrderSY(PrdOrderInfo prdOrderInfo) throws Exception;
	
	Object getTranSerialListSY(TranSerialListInfo tranSerialListInfo) throws Exception;
	
	/**
	 * 账户申请第二步
	 * @param subMerRegist
	 * @return
	 */
	Object subMerRegist(AccountRegist accountRegist) throws Exception;
	
	/**
	 * 注册第三步 绑终端
	 * @Title:        terminalRegist 
	 * @Description:  
	 * @param:        @param accountRegist
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Object    
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2015-9-1 上午11:28:01
	 */
	Object terminalRegist(AccountRegist accountRegist) throws Exception;
	
	Object changeCard(AccountRegist accountRegist) throws Exception;
	/**
	 * 账户信息查询
	 * @param accountEnquiry
	 * @return
	 */
	Object accountEnquiry(AccountEnquiry accountEnquiry);
	/**
	 * 终端注销
	 * @param terminal
	 * @return
	 */
	String terminalCancel(SubMerTerminal terminal);
	/**
	 * 上账
	 * @param accountManages
	 * @return
	 */
	String upAccount(AccountManage accountManages) throws Exception ;
	/**
	 * 实名认证
	 * @param subMerAuthInfo
	 * @return
	 * @throws Exception
	 */
	String authentication(AccountRegist accountRegist) throws Exception;
	/**
	 * 商户提现申请记录
	 * @param drawMoneyRecord
	 * @return
	 * @throws Exception
	 */
	Object drawMoneyRecord(DrawMoneyRecord drawMoneyRecord) throws Exception;
	/**
	 * 查询余额
	 * @param queryBalance
	 * @return
	 */
	Object queryBalance(QueryBalance queryBalance);
	/**
	 * 短信下发
	 * @param phoneNumber
	 * @return
	 */
	Object phoneNumber(PhoneNumber phoneNumber);
	
	JSONObject addOTOMer(JSONObject json);
	
	JSONObject mobileRegist(JSONObject json);
	
	JSONObject invite(JSONObject json);
	JSONObject waresList(JSONObject json);
	JSONObject specList(JSONObject json);
	JSONObject showInvite(JSONObject json);
	JSONObject sendMsg(JSONObject json);
	JSONObject payToDy(JSONObject json);
	JSONObject deyiPayQuery(JSONObject json);
	JSONObject addMerchant(JSONObject json);
	JSONObject purchase(JSONObject json);
	JSONObject scanPay(JSONObject json);
	JSONObject pradePay(JSONObject json);
	JSONObject yinlianPay(JSONObject json);
	void pradePayBack(JSONObject json);
	JSONObject pradePayList(JSONObject json);
	JSONObject driverBussiness(JSONObject json);
	JSONObject scanRegist(JSONObject json);
	JSONObject getScanMer(JSONObject json);
	
//	JSONObject searchOrder(JSONObject json);
	
}

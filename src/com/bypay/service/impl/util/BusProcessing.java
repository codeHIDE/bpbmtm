package com.bypay.service.impl.util;

import java.util.Map;

import javax.inject.Inject;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

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
import com.bypay.domain.clientTool.QueryBalance;
import com.bypay.domain.clientTool.RegisterMerInfo;
import com.bypay.domain.clientTool.SelectPassWord;
import com.bypay.domain.clientTool.SettBank;
import com.bypay.domain.clientTool.TerminalEnable;
import com.bypay.domain.clientTool.TerminalValidate;
import com.bypay.domain.clientTool.XunlianOrder;
import com.bypay.service.InterfaceService;

@Service
public  class  BusProcessing  {

	@Inject
	private InterfaceService interfaceService;
	/**
	 * 分流业务
	 * 
	 * @param object
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object toBus(Object busObj) {
		
		Object busObject = new Object();
		System.out.println("BusOject="+busObject);
		String application = "";
		try {
			Class superClass = busObj.getClass().getSuperclass();
			System.out.println("superClass="+superClass);
			application = (String) superClass.getDeclaredMethod("getApplication", null).invoke(busObj, null);
			if("AccountRegist.Req".equals(application)){//注册
				AccountRegist accountRegist = (AccountRegist)busObj;
				busObject = interfaceService.checkRegist(accountRegist);
			}else if("UserRegist.Req".equals(application)){//注册第一步
				AccountRegist accountRegist = (AccountRegist)busObj;
				busObject = interfaceService.userRegist(accountRegist);
			}else if("SubMerRegist.Req".equals(application)){//注册第二步
				AccountRegist accountRegist = (AccountRegist)busObj;
				busObject = interfaceService.subMerRegist(accountRegist);
			}else if("TerminalRegist.Req".equals(application)){//注册第三步 绑定终端
				AccountRegist accountRegist = (AccountRegist)busObj;
				busObject = interfaceService.terminalRegist(accountRegist);
			}else if("ChangeCard.Req".equals(application)){//跟换银行卡
				AccountRegist accountRegist = (AccountRegist)busObj;
				busObject = interfaceService.changeCard(accountRegist);
			}else if("AccountLogIn.Req".equals(application)){//登陆	
				AccountLogIn accountLogIn = (AccountLogIn)busObj;
				busObject = interfaceService.regMer(accountLogIn);
			}else if("UserLogIn.Req".equals(application)){//公版手机登录
				AccountLogIn accountLogIn = (AccountLogIn)busObj;
				busObject = interfaceService.userMer(accountLogIn);
			}else if("OpenPurchase.Req".equals(application)){//收款开通
				OpenPurchase openPurchase = (OpenPurchase)busObj;
				busObject = interfaceService.openReceivablesBus(openPurchase);
			}else if("EnquiryList.Req".equals(application)){//交易列表
				Enquiry enquiry = (Enquiry)busObj;
				busObject = interfaceService.regEnquiry(enquiry);
			}else if("NewEnquiryList.Req".equals(application)){//交易列表(新)
				Enquiry enquiry = (Enquiry)busObj;
				busObject = interfaceService.regNewEnquiry(enquiry);
			}else if("SettBankList.Req".equals(application)){//结算银行列表
				SettBank settBank = (SettBank)busObj;
				busObject = interfaceService.regBank(settBank);
			}else if("TerminalEnable.Req".equals(application)){//终端启用 YJH
				TerminalEnable settBank = (TerminalEnable)busObj;
				busObject=interfaceService.updateStatus(settBank);
			}else if("TerminalValidate.Req".equals(application)){//终端验证
				TerminalValidate terminalValidate = (TerminalValidate)busObj;
				busObject = interfaceService.terminalValidate(terminalValidate);
			} else if("AccountEnquiry.Req".equals(application)){//账户信息查询
				AccountEnquiry accountEnquiry = (AccountEnquiry) busObj;
				busObject = interfaceService.accountEnquiry(accountEnquiry);
			}else if("FindPwd.Req".equals(application)){//找回密码
				SelectPassWord selectPassWord= (SelectPassWord)busObj;
				busObject=interfaceService.updatePwd(selectPassWord);
			} else if("WithDrawRecords.Req".equals(application)){//商户提现申请记录
				DrawMoneyRecord drawMoneyRecord = (DrawMoneyRecord) busObj;
				busObject=interfaceService.drawMoneyRecord(drawMoneyRecord);
			} else if("QueryBalance.Req".equals(application)){//查询余额
				QueryBalance queryBalance = (QueryBalance) busObj;
				busObject = interfaceService.queryBalance(queryBalance);
			} else if("PhoneNumber.Req".equals(application)){//短信下发
				PhoneNumber phoneNumber = (PhoneNumber) busObj;
				busObject = interfaceService.phoneNumber(phoneNumber);
			}else if("ForgetPwd.Req".equals(application)){//忘记密码
				AccountLogIn accountRegist = (AccountLogIn)busObj;
				busObject = interfaceService.forgetPwd(accountRegist);
			}else if("registerMerSY.Req".equals(application)){//盛云认证商户
				RegisterMerInfo registerMerInfo = (RegisterMerInfo)busObj;
				busObject = interfaceService.registerMerSY(registerMerInfo);
			}else if("AccountPay.Req".equals(application)){//余额支付
				AccountBalance accountBalance = (AccountBalance)busObj;
				busObject = interfaceService.accountPay(accountBalance);
			}else if("GFregist.Req".equals(application)){//高风投资
				GFinvestment gFinvestment = (GFinvestment)busObj;
				busObject = interfaceService.GFbussiness(gFinvestment);
			}else if("CreditList.Req".equals(application)){//信用卡列表
				CreditInfoList creditInfoList = (CreditInfoList)busObj;
				busObject = interfaceService.creditbussiness(creditInfoList);
			}else if("CheckVersion.Req".equals(application)){//版本信息
				CheckVersion creditInfoList = (CheckVersion)busObj;
				busObject = interfaceService.checkVersion(creditInfoList);
			}else if("XunlianOrder.Req".equals(application)){//2016.07.13新迅联订单
				XunlianOrder xunlianOrder = (XunlianOrder)busObj;
				busObject = interfaceService.xunlianOrder(xunlianOrder);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return busObject;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject addOTOMer(JSONObject json) {
		return interfaceService.addOTOMer(json);
	}
	
	public JSONObject mobileRegist(JSONObject json){
		return interfaceService.mobileRegist(json);
	}
	
	public JSONObject invite(JSONObject json){
		return interfaceService.invite(json);
	}
	
	public JSONObject waresList(JSONObject json){
		return interfaceService.waresList(json);
	}
	
	public JSONObject specList(JSONObject json){
		return interfaceService.specList(json);
	}
	
	public JSONObject showInvite(JSONObject json){
		return interfaceService.waresList(json);
	}
	
   public JSONObject sendMsg(JSONObject json){
        return interfaceService.sendMsg(json);
    }
   
   public JSONObject payToDy(JSONObject json){
       return interfaceService.payToDy(json);
       
   }
   
   public JSONObject deyiPayQuery(JSONObject json){
       return interfaceService.deyiPayQuery(json);
   }
   
   public JSONObject addMerchant(JSONObject json){
       return interfaceService.addMerchant(json);
   }
   
   public JSONObject purchase(JSONObject json){
       return interfaceService.purchase(json);
   }
   
   public JSONObject scanPay(JSONObject json){
       return interfaceService.scanPay(json);
   }
   
   public JSONObject pradePay(JSONObject json){
       return interfaceService.pradePay(json);
   }
   public JSONObject yinlianPay(JSONObject json){
       return interfaceService.yinlianPay(json);
   }
   public void pradePayBack(JSONObject json){
        interfaceService.pradePayBack(json);
   }
   
   public JSONObject driverBussiness(JSONObject json){
       return interfaceService.driverBussiness(json);
   }
   
   public JSONObject scanRegist(JSONObject json){
       return interfaceService.scanRegist(json);
   }
   
   public JSONObject getScanMer(JSONObject json){
       return interfaceService.getScanMer(json);
   }
//	@SuppressWarnings("unchecked")
//	public JSONObject searchOrder(JSONObject json) {
//		return interfaceService.searchOrder(json);
//	}
   public JSONObject pradePayList(JSONObject json){
       return interfaceService.pradePayList(json);
   }
	
}


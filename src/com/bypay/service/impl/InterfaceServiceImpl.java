package com.bypay.service.impl;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.axis.client.Call;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.Ostermiller.util.MD5;
import com.alipay.api.internal.util.AlipaySignature;
import com.bypay.dao.AccountManageDao;
import com.bypay.dao.AgenctInfoDao;
import com.bypay.dao.BankBehalfBranchDao;
import com.bypay.dao.BlackInfoDao;
import com.bypay.dao.CheckCreditDao;
import com.bypay.dao.CoreTransLogDao;
import com.bypay.dao.CreditInfoDao;
import com.bypay.dao.DriverInfoDao;
import com.bypay.dao.DyPayInfoDao;
import com.bypay.dao.FactoryRiskDao;
import com.bypay.dao.MerInfoDao;
import com.bypay.dao.MerManagerDao;
import com.bypay.dao.MerSettleStatictisDao;
import com.bypay.dao.MerTerminalInfoDao;
import com.bypay.dao.MerTransDao;
import com.bypay.dao.MobileUserDao;
import com.bypay.dao.OrderInfoDao;
import com.bypay.dao.PhoneVerifyCodeDao;
import com.bypay.dao.ProcedureDao;
import com.bypay.dao.RuleInfoDao;
import com.bypay.dao.ScanOrderInfoDao;
import com.bypay.dao.SubMerAuthInfoDao;
import com.bypay.dao.SubMerCashoutDao;
import com.bypay.dao.SubMerInfoDao;
import com.bypay.dao.SubMerRateDao;
import com.bypay.dao.SubMerTerminalDao;
import com.bypay.dao.SubMerTerminalInfoDao;
import com.bypay.dao.SubMerTransDao;
import com.bypay.dao.TractInfoDao;
import com.bypay.dao.WaresInfoDao;
import com.bypay.dao.WaresSpecInfoDao;
import com.bypay.domain.AccountManage;
import com.bypay.domain.AgenctInfo;
import com.bypay.domain.BankBehalfBranch;
import com.bypay.domain.BlackInfo;
import com.bypay.domain.CheckCredit;
import com.bypay.domain.CoreTransLog;
import com.bypay.domain.CreditInfo;
import com.bypay.domain.DriverInfo;
import com.bypay.domain.DyPayInfo;
import com.bypay.domain.FactoryRisk;
import com.bypay.domain.MerInfo;
import com.bypay.domain.MerManager;
import com.bypay.domain.MerSettleStatictis;
import com.bypay.domain.MerTerminalInfo;
import com.bypay.domain.MerTrans;
import com.bypay.domain.MobileUser;
import com.bypay.domain.OrderInfo;
import com.bypay.domain.PhoneVerifyCode;
import com.bypay.domain.RuleInfo;
import com.bypay.domain.ScanOrderInfo;
import com.bypay.domain.SubMerAuthInfo;
import com.bypay.domain.SubMerCashout;
import com.bypay.domain.SubMerInfo;
import com.bypay.domain.SubMerRate;
import com.bypay.domain.SubMerTerminal;
import com.bypay.domain.SubMerTerminalInfo;
import com.bypay.domain.SubMerTrans;
import com.bypay.domain.TractInfo;
import com.bypay.domain.WaresInfo;
import com.bypay.domain.WaresSpecInfo;
import com.bypay.domain.clientTool.AccountBalance;
import com.bypay.domain.clientTool.AccountEnquiry;
import com.bypay.domain.clientTool.AccountLogIn;
import com.bypay.domain.clientTool.AccountRegist;
import com.bypay.domain.clientTool.BankUtils;
import com.bypay.domain.clientTool.CheckVersion;
import com.bypay.domain.clientTool.CreditInfoList;
import com.bypay.domain.clientTool.DrawMoneyRecord;
import com.bypay.domain.clientTool.DrawMoneyRecordList;
import com.bypay.domain.clientTool.Enquiry;
import com.bypay.domain.clientTool.EnquiryList;
import com.bypay.domain.clientTool.GFinvestment;
import com.bypay.domain.clientTool.OpenPurchase;
import com.bypay.domain.clientTool.PhoneNumber;
import com.bypay.domain.clientTool.PrdOrderInfo;
import com.bypay.domain.clientTool.QueryBalance;
import com.bypay.domain.clientTool.RegisterMerInfo;
import com.bypay.domain.clientTool.SelectPassWord;
import com.bypay.domain.clientTool.SettBank;
import com.bypay.domain.clientTool.SettBankList;
import com.bypay.domain.clientTool.TerminalCancel;
import com.bypay.domain.clientTool.TerminalEnable;
import com.bypay.domain.clientTool.TerminalValidate;
import com.bypay.domain.clientTool.TranSerialListInfo;
import com.bypay.domain.clientTool.TransInfoReq;
import com.bypay.domain.clientTool.UpBankCardInfo;
import com.bypay.domain.clientTool.XunlianOrder;
import com.bypay.service.InterfaceService;
import com.bypay.service.SubMerInfoService;
import com.bypay.service.impl.util.RuiyinCredit;
import com.bypay.service.impl.util.TmallYfDataPackage;
import com.bypay.util.AESCoder;
import com.bypay.util.AuthenticationUtil;
import com.bypay.util.BASE64Util;
import com.bypay.util.CertificateUtils;
import com.bypay.util.DateUtil;
import com.bypay.util.Des3;
import com.bypay.util.HFSendData;
import com.bypay.util.HttpUtils;
import com.bypay.util.JAUtil;
import com.bypay.util.JSONStringBuilder;
import com.bypay.util.Md5Util;
import com.bypay.util.Md5withRsa;
import com.bypay.util.PageUtil;
import com.bypay.util.PropertiesUtils;
import com.bypay.util.RSACoder;
import com.bypay.util.RefundUtil;
import com.bypay.util.RemoteAccessor;
import com.bypay.util.StringEncrypt;
import com.bypay.util.TimeUtils;
import com.bypay.util.ValueTool;
import com.bypay.util.XmlUtil;
import com.bypay.util.XmlUtilnew;
import com.bypay.util.YMInterfaceUtil;
import com.richerpay.core.model.CoreTransInfo;

@Service("interfaceService")
public class InterfaceServiceImpl implements InterfaceService {
	@Inject
	private MerInfoDao merInfoDao;
	@Inject
	private FactoryRiskDao factoryRiskDao;
	@Inject
	private SubMerCashoutDao subMerCashoutDao;
	@Inject
	private MerManagerDao merManagerDao;
	@Inject
	private MerSettleStatictisDao merSettleStatictisDao;
	@Inject
	private AccountManageDao accountManageDao;
	@Inject
	private ProcedureDao procedureDao;
	@Inject
	private SubMerTerminalInfoDao subMerTerminalInfoDao;
	@Inject
	private MerTerminalInfoDao merTerminalInfoDao;
	@Inject
	private SubMerAuthInfoDao subMerAuthInfoDao;
	@Inject
	private BankBehalfBranchDao bankBehalfBranchDao;
	@Inject
	private SubMerInfoDao subMerInfoDao;
	@Inject
	private MobileUserDao mobileUserDao;
	@Inject
	private SubMerTransDao subMerTransDao;
	@Inject
	private SubMerRateDao subMerRateDao;
	@Inject
	private MerTransDao merTransDao;
	@Inject
	private AgenctInfoDao agenctInfoDao;
	@Inject
	private SubMerTerminalDao subMerTerminalDao;
	@Inject
	private XmlUtilnew xmlUtilnew;
	@Inject
	private OrderInfoDao orderInfoDao;
	@Inject
	private PhoneVerifyCodeDao phoneVerifyCodeDao;
	@Inject
	private BlackInfoDao blackInfoDao;
	@Inject
	private CreditInfoDao creditInfoDao;
	@Inject
	private CheckCreditDao checkCreditDao;
	@Inject
	private RuleInfoDao ruleInfoDao;
	@Inject
	private WaresInfoDao waresInfoDao;
	@Inject
	private WaresSpecInfoDao waresSpecInfoDao;
	@Inject
	private DyPayInfoDao dyPayInfoDao;
	@Inject
    private CoreTransLogDao coreTransLogDao;
   @Inject
    private SubMerInfoService subMerInfoService;
   @Inject
    private TractInfoDao tractInfoDao;
   @Inject
   private ScanOrderInfoDao scanOrderInfoDao;
   @Inject
   private DriverInfoDao driverInfoDao;
//    @Autowired
//    private NewPayService newPayService;
	private String checkCode;
	static ResourceBundle rb = ResourceBundle.getBundle(
			"com/bypay/config/bmtmUtil", Locale.getDefault());

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	/**
	 * 注册时的数据查询验证、添加商户
	 * 
	 * @throws Exception
	 */
	public AccountRegist checkRegist(AccountRegist regist) throws Exception {
		setCheckCode("");
		regist.copyBusBeanParent(regist);
		String merchantId = "";// 商户号
		Map<Object, Object> map = new HashMap<Object, Object>();
		procedureDao.getSequence(map);
		Integer terminalId = (Integer) map.get("o_id");
		try {
			// 手机验证码验证
//			PhoneVerifyCode phoneVerifyCode = new PhoneVerifyCode();
//			phoneVerifyCode.setTerminalId(regist.getTerminalId());
//			phoneVerifyCode.setPhone(regist.getMobileNum());
//			phoneVerifyCode.setCode(regist.getMsgExt());
//			PhoneVerifyCode phoneVerifyCodes = phoneVerifyCodeDao.selectPhoneVerifyCode(phoneVerifyCode);

//			if ((null == regist.getMsgExt() || "".equals(regist.getMsgExt()))
//					|| (null != phoneVerifyCodes)) {
//				long a = 0;
//				long b = 0;
//				if (null != phoneVerifyCodes) {
//					a = Long.parseLong(DateUtil.getDate("yyyyMMddHHmmss"));
//					b = Long.parseLong(phoneVerifyCodes.getCreateTime());
//				}
//				long c = a - b;
//				if (c > 1500) {
//					setCheckCode("3060");
//				} else {
					// 查询法人身份验证
					List<SubMerInfo> subMerInfos = subMerInfoDao.selectSubInfoByIdCard(regist.getLegalManIdcard());
					if (null == subMerInfos || subMerInfos.size() <= 0) {
						// 子商户终端登录名(手机号)
						List<SubMerTerminal> terminals = subMerTerminalDao.selectSubMerTerminalByPhone(regist.getMobileNum());
						if (null == terminals || terminals.size() <= 0) {
							// 获取子商户设备信息
							SubMerTerminal terminal = new SubMerTerminal();
							terminal.setTsn(regist.getTerminalId());
							SubMerTerminal subMerTerminal = subMerTerminalDao.selectSubMerTerminalByTerminalId(terminal);
							setCheckCode("9001");
							if (null != subMerTerminal) {
								setCheckCode("9004");
								if (subMerTerminal.getSubMerId() == null
										|| "".equals(subMerTerminal.getSubMerId())
										|| "-1".equals(subMerTerminal.getSubMerId())) {
									String status = "0";// 子商户状态
									// 代理 暂停进件验证 (代理商状态为3）
									String isIntoPieces1 = "";
									String isIntoPieces2 = "";
									if (null != subMerTerminal.getAgentIdL1()
											&& !"".equals(subMerTerminal.getAgentIdL1())
											&& !"-1".equals(subMerTerminal.getAgentIdL1())) {
										
										status = "-2";

										AgenctInfo agenctInfo = new AgenctInfo();
										agenctInfo.setAgentId(subMerTerminal.getAgentIdL1());
										agenctInfo = agenctInfoDao.selectAgenctInfoById(agenctInfo);
										isIntoPieces1 = agenctInfo.getIsIntoPieces();
									}
									if (null != subMerTerminal.getAgentIdL2()
											&& !"".equals(subMerTerminal.getAgentIdL2())
											&& !"-1".equals(subMerTerminal.getAgentIdL2())) {
										status = "-3";

										AgenctInfo agenctInfo = new AgenctInfo();
										agenctInfo.setAgentId(subMerTerminal.getAgentIdL2());
										agenctInfo = agenctInfoDao.selectAgenctInfoById(agenctInfo);
										isIntoPieces2 = agenctInfo.getIsIntoPieces();
									}
									MerInfo mi = new MerInfo();
									mi.setMerSysId(subMerTerminal.getMerSysId());
									mi = merInfoDao.selectMerInfoById(mi);
									//机构 暂停进件
									if(null != mi && !"1".equals(mi.getIsIntoPieces())) {
										// 代理暂停进件验证
										if (!"1".equals(isIntoPieces1) && !"1".equals(isIntoPieces2)) {
											// 获取机构交易配置信息
											MerTrans merTrans = merTransDao.getMerTransInfo(subMerTerminal.getMerSysId());
											setCheckCode("3039");
											if (null != merTrans) {
												if (null != merTrans.getAutoApprove()
														&& !"".equals(merTrans.getAutoApprove())
														&& "1".equals(merTrans.getAutoApprove())) {
													status = "2";
												}
												String authStatus = "";// 实名认证状态
												String serialNo = "";// 实名认证流水号
												// 自动审核
												if ("1".equals(merTrans
														.getAutoApprove())) {
													if (null != regist.getSettleAccountType()
															&& !"".equals(regist.getSettleAccountType())
															&& "2".equals(regist.getSettleAccountType())) {// 对私注册
														// 实名认证
														String authResult = authentication(regist);
														String authStr[] = authResult
																.split("\\|");
														authStatus = authStr[0];
														serialNo = authStr[1];
														if ("1".equals(authStatus)) {
															Long subMerId = null;// 子商户号
															// ：机构号后三位+机构号默认地区码+机构号默认MCC码+4位数的编码
															// 组装子商户号
															merchantId = merTrans
																	.getMerSysId()
																	.substring(merTrans.getMerSysId().length() - 3)
																	+ merTrans.getBasicRegion()
																	+ merTrans.getBasicMcc();// 缺少后4位数的编码
															boolean res = false;
															// 一次只能有一个线程进入，将子商户号组装完整
															synchronized (merchantId) {
																SubMerInfo merInfo = new SubMerInfo();
																merInfo.setSubMerId(merchantId);
																String subId = subMerInfoDao.setMaxSubMerId(merInfo);// 获取子商户表当中的相关数据
																if (subId != null && !"".equals(subId)) {
																	subMerId = Long.parseLong(subId) + 1;
																} else {
																	subMerId = Long.parseLong(merchantId + "0001");
																}
																res = insertSubMerInfo(regist, subMerTerminal, status, subMerId, merTrans);
															}
															// 添加子商户信息
															setCheckCode("9009");
															if (res == true) { // 判断子商户添加成功是否
																merchantId = subMerId .toString();
																// 添加修改商户相关信息
																optSubMerInfo(merTrans, subMerTerminal, subMerId, regist, terminalId);
															}
															// 修改子商户交易配置-认证状态
															SubMerTrans subMerTrans = new SubMerTrans();
															subMerTrans.setSubMerId(subMerId.toString());
															subMerTrans.setAuthStatus(authStatus);
															subMerTrans.setAuthTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
															if (subMerTransDao.updateSubMerTransInfo(subMerTrans) != 1) {
																setCheckCode("3047");
															}
														} else {
															setCheckCode("2001");
														}
														// 添加实名数据到数据库
														SubMerAuthInfo auth = new SubMerAuthInfo();
														auth.setCreateTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
														if ("".equals(merchantId)) {
															merchantId = "-1";
														}
														auth.setSubMerId(merchantId);
														auth.setRemitType("1");
														auth.setRealName(regist.getLegalManName());
														auth.setIdNum(regist.getLegalManIdcard());
														if ("-1".equals(authStatus)) {
															authStatus = "4";
														}
														auth.setAuthStatus(authStatus);
														auth.setSerialNo(serialNo);
														auth.setTerminalId(regist.getTerminalId());
														// 添加认证信息到数据库
														if (subMerAuthInfoDao
																.addSubMerAuthInfo(auth) != 1) {
															setCheckCode("3048");
														}
													} else {// 对公注册
														Long subMerId = null;// 子商户号：机构号后三位+机构号默认地区码+机构号默认MCC码+4位数的编码
														// 组装子商户号
														merchantId = merTrans.getMerSysId().substring(merTrans.getMerSysId().length() - 3)
																+ merTrans.getBasicRegion()
																+ merTrans.getBasicMcc();// 缺少后4位数的编码
														boolean res = false;
														// 一次只能有一个线程进入，将子商户号组装完整
														synchronized (merchantId) {
															SubMerInfo merInfo = new SubMerInfo();
															merInfo.setSubMerId(merchantId);
															String subId = subMerInfoDao.setMaxSubMerId(merInfo);// 获取子商户表当中的相关数据
															if (subId != null && !"".equals(subId)) {
																subMerId = Long.parseLong(subId) + 1;
															} else {
																subMerId = Long.parseLong(merchantId + "0001");
															}
															res = insertSubMerInfo(regist, subMerTerminal, status, subMerId, merTrans);
														}
														// 添加子商户信息
														setCheckCode("9009");
														if (res == true) { // 判断子商户添加成功是否
															merchantId = subMerId.toString();
															// 添加修改商户相关信息
															optSubMerInfo(merTrans, subMerTerminal, subMerId, regist, terminalId);
														}
													}
													// 添加子商户超级管理员
													insertSubMerManager(merchantId, regist);
												} else {// 手动审核
													Long subMerId = null;// 子商户号
													// ：机构号后三位+机构号默认地区码+机构号默认MCC码+4位数的编码
													// 组装子商户号
													merchantId = merTrans.getMerSysId().substring(
																	merTrans.getMerSysId().length() - 3)
															+ merTrans.getBasicRegion()
															+ merTrans.getBasicMcc();// 缺少后4位数的编码
													boolean res = false;
													// 一次只能有一个线程进入，将子商户号组装完整
													synchronized (merchantId) {
														SubMerInfo merInfo = new SubMerInfo();
														merInfo.setSubMerId(merchantId);
														String subId = subMerInfoDao.setMaxSubMerId(merInfo);// 获取子商户表当中的相关数据
														if (subId != null && !"".equals(subId)) {
															subMerId = Long.parseLong(subId) + 1;
														} else {
															subMerId = Long.parseLong(merchantId + "0001");
														}
														res = insertSubMerInfo(regist, subMerTerminal, status, subMerId, merTrans);
													}
													// 添加子商户信息
													setCheckCode("9009");
													if (res == true) { // 判断子商户添加成功是否
														merchantId = subMerId.toString();
														// 添加修改商户相关信息
														optSubMerInfo(merTrans, subMerTerminal, subMerId, regist, terminalId);
													}
												}

											}
										} else {
											setCheckCode("3063");
										}
									}else {
										setCheckCode("3062");
									}
								}
							}
						} else {
							setCheckCode("3057");
						}
					} else {
						setCheckCode("3055");
					}
//				}
//			} else {
//				setCheckCode("3059");
//			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
		}
		regist.setTerminalId(regist.getTerminalId());
		regist.setFactoryId(regist.getFactoryId());
		regist.setMerchantName(regist.getMerchantName());
		regist.setLegalManName(regist.getLegalManName());
		regist.setLegalManIdcard(regist.getLegalManIdcard());
		regist.setMobileNum(regist.getMobileNum());
		regist.setPersonalMerRegNo(regist.getPersonalMerRegNo());
		regist.setTaxNo(regist.getTaxNo());
		regist.setOccNo("");
		regist.setSettleAccountType(regist.getSettleAccountType());
		regist.setSettleAccount(regist.getSettleAccount());
		regist.setSettleAccountNo(regist.getSettleAccountNo());
		regist.setSettleAgency(regist.getSettleAgency());
		regist.setAccountPwd("");
		regist.setTerminalInFo("");
		regist.setMerchantId(merchantId);
		regist.setMsgExt(regist.getMsgExt());
		regist.setMisc(regist.getMisc());
		if ("".equals(checkCode)) {
			setCheckCode("3001");
		}
		regist.setAccountName(regist.getMobileNum());// 返回手机号
		regist.setRespCode(checkCode);
		regist.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
		return regist;
	}

	/**
	 * 注册第一步,手机密码验证码
	 * 
	 * @throws Exception
	 */
	public AccountRegist userRegist(AccountRegist regist) throws Exception {
		setCheckCode("");
		regist.copyBusBeanParent(regist);
		Map<Object, Object> map = new HashMap<Object, Object>();
		procedureDao.getSequence(map);
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
		int rI = (int)(Math.random()*9);
		String subMerId = sdf.format(new Date())+rI;// 子商户号
		try {
			// 子商户终端登录名(手机号)
			List<SubMerTerminal> terminals = subMerTerminalDao.selectSubMerTerminalByPhone(regist.getMobileNum());
			//判断手机号是否在黑名单内
			BlackInfo blackInfo = new BlackInfo();
			blackInfo.setPhone(regist.getMobileNum());
			blackInfo = blackInfoDao.selectBlackInfo(blackInfo);
			if(blackInfo==null){
				if (null == terminals || terminals.size() <= 0) {
					// 查询Mobile 信息
					MobileUser mobileUser = new MobileUser();
					mobileUser.setLoginName(regist.getMobileNum());
	//				mobileUser.setLoginPwd(regist.getAccountPwd());
					mobileUser = mobileUserDao.getMobileUser(mobileUser);
					System.out.println("mobileUser="+mobileUser);
					if(mobileUser==null){
						String status = "0";// 子商户状态
						// ：机构号后三位+机构号默认地区码+机构号默认MCC码+4位数的编码
						boolean res = false;
						// 一次只能有一个线程进入，将手机号号组装完整	
						synchronized (regist.getMobileNum()) {
							System.out.println("go to insert");
							res = insertSubMerInfo(regist, status, subMerId);
						}
					}else{
						setCheckCode("3057");
					}
				} else {
					setCheckCode("3057");
				}
			}else{
				setCheckCode("3057");
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
		}
		regist.setApplication("UserRegist.Rsp");
		regist.setMerchantId(subMerId);
		regist.setMobileNum(regist.getMobileNum());
		regist.setAccountPwd("");
		regist.setMsgExt(regist.getMsgExt());
		regist.setMisc(regist.getMisc());
		if ("".equals(checkCode)) {
			setCheckCode("3001");
		}
		regist.setRespCode(checkCode);
		regist.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
		return regist;
	}
	
	public AccountLogIn forgetPwd(AccountLogIn accountLogIn) throws Exception {
		accountLogIn.copyBusBeanParent(accountLogIn);
		setCheckCode("");
		try {
			if (accountLogIn != null) {
				MobileUser mobileUser = new MobileUser();
				mobileUser.setLoginName(accountLogIn.getAccountName());
//				mobileUser.setLoginPwd(accountLogIn.getAccountPwd());
				mobileUser = mobileUserDao.getMobileUser(mobileUser);
				// 查询设备信息
				SubMerTerminal subMerTerminal = new SubMerTerminal();
				subMerTerminal.setLoginName(accountLogIn.getAccountName());
				subMerTerminal = subMerTerminalDao
						.getSubMerTerminal(subMerTerminal);
				if (mobileUser != null) {
						setCheckCode("9006");
						mobileUser.setLoginPwd(accountLogIn.getAccountPwd());
						mobileUserDao.updateMobileUser(mobileUser);
						if(subMerTerminal!=null){
						      SubMerTerminal resetTerminal = new SubMerTerminal();
						      resetTerminal.setId(subMerTerminal.getId());
						      resetTerminal.setLoginPwd(accountLogIn.getAccountPwd());
						      subMerTerminalDao.reset(resetTerminal);
					    }
						setCheckCode("0000");
				} else {
					// 查询Mobile 信息
					setCheckCode("9010");
				}
			} else {
				setCheckCode("9998");
			}
		} catch (Exception e) {
			setCheckCode("9998");
			e.printStackTrace();
		}
		accountLogIn.setApplication("ForgetPwd.Rsp");
		accountLogIn.setRespCode(checkCode);
		accountLogIn.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
		return accountLogIn;
	}
	
	/**
	 * 注册第二步
	 * 
	 * @throws Exception
	 */
	public AccountRegist subMerRegist(AccountRegist regist) throws Exception {
		setCheckCode("");
		regist.copyBusBeanParent(regist);
		String merchantId = "";// 商户号
		Map<Object, Object> map = new HashMap<Object, Object>();
		procedureDao.getSequence(map);
		try {
			// 查询法人身份验证
//			List<SubMerInfo> subMerInfosList = subMerInfoDao.selectSubInfoByIdCard(regist.getLegalManIdcard());
//			if (null == subMerInfosList || subMerInfosList.size() <= 0) {
			Boolean idFlag = true;
			//判定 黑名单
			//卡号黑名单
			BlackInfo cardBlackInfo = new BlackInfo();
			cardBlackInfo.setCardNo(regist.getSettleAccountNo());
			cardBlackInfo = blackInfoDao.selectBlackInfo(cardBlackInfo);
			BlackInfo idBlackInfo = new BlackInfo();
			//身份证黑名单
			idBlackInfo.setIdNum(regist.getLegalManIdcard());
			idBlackInfo = blackInfoDao.selectBlackInfo(idBlackInfo);
			if(cardBlackInfo==null && idBlackInfo==null){
			  //一个身份证可以注册多个商户
			SubMerInfo subMerInfos  = subMerInfoDao.getSubMerInfoById(regist.getMerchantId());
//				if(subMerInfos==null){
//					List<SubMerInfo> subMerInfosList = subMerInfoDao.selectSubInfoByIdCard(regist.getLegalManIdcard());
//					if (null != subMerInfosList && subMerInfosList.size() > 0) {
//						idFlag=false;
//					}
//				}
					setCheckCode("9004");
					String status = "0";// 子商户状态
					if(idFlag) {
							setCheckCode("3039");
								String authStatus = "";// 实名认证状态
								String serialNo = "";// 实名认证流水号
									Long subMerId = null;// 子商户号
									// ：机构号后三位+机构号默认地区码+机构号默认MCC码+4位数的编码
									// 组装子商户号
									MobileUser mobileUser = new MobileUser();
									mobileUser.setLoginName(regist.getAccountName());
									mobileUser = mobileUserDao.getMobileUser(mobileUser);
									merchantId = mobileUser.getSubMerId();
									boolean res = false;
									// 一次只能有一个线程进入，将子商户号组装完整
									synchronized (merchantId) {
										SubMerInfo merInfo = new SubMerInfo();
										merInfo.setSubMerId(merchantId);
										if(subMerInfos==null){
											res = insertSubMer(regist,status, merchantId);
											mobileUser.setStatus("1");
										}else{
											res = updateSubMer(regist,status, merchantId);
											mobileUser.setStatus("2");
										}
										//更新用户信息
										mobileUserDao.updateMobileUser(mobileUser);
									}
							
						
					}else {
						setCheckCode("3055");
					}
			} else {
				setCheckCode("3055");
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
		}
		regist.setApplication("SubMerRegist.Rsp");
		regist.setTerminalId(regist.getTerminalId());
		regist.setFactoryId(regist.getFactoryId());
		regist.setMerchantName(regist.getMerchantName());
		regist.setLegalManName(regist.getLegalManName());
		regist.setLegalManIdcard(regist.getLegalManIdcard());
		regist.setMobileNum(regist.getMobileNum());
		regist.setPersonalMerRegNo(regist.getPersonalMerRegNo());
		regist.setTaxNo(regist.getTaxNo());
		regist.setOccNo("");
		regist.setSettleAccountType(regist.getSettleAccountType());
		regist.setSettleAccount(regist.getSettleAccount());
		regist.setSettleAccountNo(regist.getSettleAccountNo());
		regist.setSettleAgency(regist.getSettleAgency());
		regist.setAccountPwd("");
		regist.setTerminalInFo("");
		regist.setMerchantId(merchantId);
		regist.setMsgExt(regist.getMsgExt());
		regist.setMisc(regist.getMisc());
		if ("".equals(checkCode)) {
			setCheckCode("3001");
		}
		regist.setAccountName(regist.getMobileNum());// 返回手机号
		regist.setRespCode(checkCode);
		regist.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
		return regist;
	}
	
	public AccountRegist terminalRegist(AccountRegist regist) throws Exception {
		setCheckCode("");
		regist.copyBusBeanParent(regist);
		String merchantId = "";// 商户号
		Map<Object, Object> map = new HashMap<Object, Object>();
		procedureDao.getSequence(map);
		Integer terminalId = (Integer) map.get("o_id");
		MobileUser mobileUser = new MobileUser();
		mobileUser.setLoginName(regist.getMobileNum());
		mobileUser = mobileUserDao.getMobileUser(mobileUser);
		// 获取子商户设备信息
		SubMerTerminal terminal = new SubMerTerminal();
		terminal.setTsn(regist.getTerminalId());
		SubMerTerminal subMerTerminal = subMerTerminalDao.selectSubMerTerminalByTerminalId(terminal);
		if(subMerTerminal.getSubMerId().equals(mobileUser.getSubMerId())){
			if(mobileUser.getTerminalId().toUpperCase().equals(regist.getTerminalId().toUpperCase())){
				regist.setMerSysId(subMerTerminal.getMerSysId());
				setCheckCode("3066");	
			}else{
				setCheckCode("3064");
			}
			regist.setApplication("TerminalRegist.Rsp");
			regist.setRespCode(checkCode);
			regist.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
			return regist;
		}
		try {
		// 子商户终端登录名(手机号)
		List<SubMerTerminal> terminals = subMerTerminalDao.selectSubMerTerminalByPhone(regist.getMobileNum());
		if (null == terminals || terminals.size() <= 0) {
			
			setCheckCode("9001");
			if (null != subMerTerminal) {
				setCheckCode("9004");
				if (subMerTerminal.getSubMerId() == null
						|| "".equals(subMerTerminal.getSubMerId())
						|| "-1".equals(subMerTerminal.getSubMerId())) {
					String status = "0";// 子商户状态
					// 代理 暂停进件验证 (代理商状态为3）
					String isIntoPieces1 = "";
					String isIntoPieces2 = "";
					if (null != subMerTerminal.getAgentIdL1()
							&& !"".equals(subMerTerminal.getAgentIdL1())
							&& !"-1".equals(subMerTerminal.getAgentIdL1())) {
						
						status = "-2";

						AgenctInfo agenctInfo = new AgenctInfo();
						agenctInfo.setAgentId(subMerTerminal.getAgentIdL1());
						agenctInfo = agenctInfoDao.selectAgenctInfoById(agenctInfo);
						isIntoPieces1 = agenctInfo.getIsIntoPieces();
					}
					if (null != subMerTerminal.getAgentIdL2()
							&& !"".equals(subMerTerminal.getAgentIdL2())
							&& !"-1".equals(subMerTerminal.getAgentIdL2())) {
						status = "-3";

						AgenctInfo agenctInfo = new AgenctInfo();
						agenctInfo.setAgentId(subMerTerminal.getAgentIdL2());
						agenctInfo = agenctInfoDao.selectAgenctInfoById(agenctInfo);
						isIntoPieces2 = agenctInfo.getIsIntoPieces();
					}
					MerInfo mi = new MerInfo();
					mi.setMerSysId(subMerTerminal.getMerSysId());
					mi = merInfoDao.selectMerInfoById(mi);
					//机构 暂停进件
					if(null != mi && !"1".equals(mi.getIsIntoPieces())) {
						// 代理暂停进件验证
						if (!"1".equals(isIntoPieces1) && !"1".equals(isIntoPieces2)) {
							// 获取机构交易配置信息
							MerTrans merTrans = merTransDao.getMerTransInfo(subMerTerminal.getMerSysId());
							setCheckCode("3039");
							if (null != merTrans) {
								if (null != merTrans.getAutoApprove()
										&& !"".equals(merTrans.getAutoApprove())
										&& "1".equals(merTrans.getAutoApprove())) {
									status = "2";
								}
								String authStatus = "";// 实名认证状态
								String serialNo = "";// 实名认证流水号
								// 自动审核
								if ("1".equals(merTrans
										.getAutoApprove())) {
									if (null != regist.getSettleAccountType()
											&& !"".equals(regist.getSettleAccountType())
											&& "2".equals(regist.getSettleAccountType())) {// 对私注册
										// 实名认证
										String authResult = authentication(regist);
										String authStr[] = authResult
												.split("\\|");
										authStatus = authStr[0];
										serialNo = authStr[1];
										if ("1".equals(authStatus)) {
											Long subMerId = null;// 子商户号
											// ：机构号后三位+机构号默认地区码+机构号默认MCC码+4位数的编码
											// 组装子商户号
											merchantId = merTrans
													.getMerSysId()
													.substring(merTrans.getMerSysId().length() - 3)
													+ merTrans.getBasicRegion()
													+ merTrans.getBasicMcc();// 缺少后4位数的编码
											boolean res = false;
											// 一次只能有一个线程进入，将子商户号组装完整
											synchronized (merchantId) {
												SubMerInfo merInfo = new SubMerInfo();
												merInfo.setSubMerId(merchantId);
												String subId = subMerInfoDao.setMaxSubMerId(merInfo);// 获取子商户表当中的相关数据
												if (subId != null && !"".equals(subId)) {
													if(regist.getMerchantId().trim().length()>15){
														subMerId = Long.parseLong(subId) + 1;
													}else{
														subMerId = Long.parseLong(regist.getMerchantId());
													}
												} else {
													subMerId = Long.parseLong(merchantId + "0001");
												}
												res = updateSubMerInfo(regist, subMerTerminal, status, subMerId, merTrans);
											}
											// 添加子商户信息
											setCheckCode("9009");
											if (res == true) { // 判断子商户添加成功是否
												merchantId = subMerId .toString();
												// 添加修改商户相关信息
												optSubMerInfoAndTerminal(merTrans, subMerTerminal, subMerId, regist, terminalId);
											}
											// 修改子商户交易配置-认证状态
											SubMerTrans subMerTrans = new SubMerTrans();
											subMerTrans.setSubMerId(subMerId.toString());
											subMerTrans.setAuthStatus(authStatus);
											subMerTrans.setAuthTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
											if (subMerTransDao.updateSubMerTransInfo(subMerTrans) != 1) {
												setCheckCode("3047");
											}
										} else {
											setCheckCode("2001");
										}
										// 添加实名数据到数据库
										SubMerAuthInfo auth = new SubMerAuthInfo();
										auth.setCreateTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
										if ("".equals(merchantId)) {
											merchantId = "-1";
										}
										auth.setSubMerId(merchantId);
										auth.setRemitType("1");
										auth.setRealName(regist.getLegalManName());
										auth.setIdNum(regist.getLegalManIdcard());
										if ("-1".equals(authStatus)) {
											authStatus = "4";
										}
										auth.setAuthStatus(authStatus);
										auth.setSerialNo(serialNo);
										auth.setTerminalId(regist.getTerminalId());
										// 添加认证信息到数据库
										if (subMerAuthInfoDao
												.addSubMerAuthInfo(auth) != 1) {
											setCheckCode("3048");
										}
									} else {// 对公注册
										Long subMerId = null;// 子商户号：机构号后三位+机构号默认地区码+机构号默认MCC码+4位数的编码
										// 组装子商户号
										merchantId = merTrans.getMerSysId().substring(merTrans.getMerSysId().length() - 3)
												+ merTrans.getBasicRegion()
												+ merTrans.getBasicMcc();// 缺少后4位数的编码
										boolean res = false;
										// 一次只能有一个线程进入，将子商户号组装完整
										synchronized (merchantId) {
											SubMerInfo merInfo = new SubMerInfo();
											merInfo.setSubMerId(merchantId);
											String subId = subMerInfoDao.setMaxSubMerId(merInfo);// 获取子商户表当中的相关数据
											if (subId != null && !"".equals(subId)) {
												if(regist.getMerchantId().trim().length()>15){
													subMerId = Long.parseLong(subId) + 1;
												}else{
													subMerId = Long.parseLong(regist.getMerchantId());
												}
											} else {
												subMerId = Long.parseLong(merchantId + "0001");
											}
											res = updateSubMerInfo(regist, subMerTerminal, status, subMerId, merTrans);
										}
										// 添加子商户信息
										setCheckCode("9009");
										if (res == true) { // 判断子商户添加成功是否
											merchantId = subMerId.toString();
											// 添加修改商户相关信息
											optSubMerInfoAndTerminal(merTrans, subMerTerminal, subMerId, regist, terminalId);
											setCheckCode("0000");
										}
									}
									// 添加子商户超级管理员
									insertSubMerManager(merchantId, regist);
								} else {// 手动审核
									Long subMerId = null;// 子商户号
									// ：机构号后三位+机构号默认地区码+机构号默认MCC码+4位数的编码
									// 组装子商户号
									merchantId = merTrans.getMerSysId().substring(
													merTrans.getMerSysId().length() - 3)
											+ merTrans.getBasicRegion()
											+ merTrans.getBasicMcc();// 缺少后4位数的编码
									boolean res = false;
									// 一次只能有一个线程进入，将子商户号组装完整
									synchronized (merchantId) {
										SubMerInfo merInfo = new SubMerInfo();
										merInfo.setSubMerId(merchantId);
										String subId = subMerInfoDao.setMaxSubMerId(merInfo);// 获取子商户表当中的相关数据
										if (subId != null && !"".equals(subId)) {
											if(regist.getMerchantId().trim().length()>15){
												subMerId = Long.parseLong(subId) + 1;
											}else{
												subMerId = Long.parseLong(regist.getMerchantId());
											}
										} else {
											subMerId = Long.parseLong(merchantId + "0001");
										}
										res = updateSubMerInfo(regist, subMerTerminal, status, subMerId, merTrans);
									}
									// 添加子商户信息
									setCheckCode("9009");
									if (res == true) { // 判断子商户添加成功是否
										merchantId = subMerId.toString();
										// 添加修改商户相关信息
										optSubMerInfoAndTerminal(merTrans, subMerTerminal, subMerId, regist, terminalId);
										setCheckCode("0000");
									}
								}

							}
						} else {
							setCheckCode("3063");
						}
					}else {
						setCheckCode("3062");
					}
				}
			}
		} else {
			setCheckCode("3057");
		}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
		}
		regist.setApplication("TerminalRegist.Rsp");
		regist.setMerSysId(subMerTerminal.getMerSysId());
		regist.setTerminalId(regist.getTerminalId());
		regist.setFactoryId(regist.getFactoryId());
		regist.setMerchantName(regist.getMerchantName());
		regist.setLegalManName(regist.getLegalManName());
		regist.setLegalManIdcard(regist.getLegalManIdcard());
		regist.setMobileNum(regist.getMobileNum());
		regist.setPersonalMerRegNo(regist.getPersonalMerRegNo());
		regist.setTaxNo(regist.getTaxNo());
		regist.setOccNo("");
		regist.setSettleAccountType(regist.getSettleAccountType());
		regist.setSettleAccount(regist.getSettleAccount());
		regist.setSettleAccountNo(regist.getSettleAccountNo());
		regist.setSettleAgency(regist.getSettleAgency());
		regist.setAccountPwd("");
		regist.setTerminalInFo("");
		regist.setMerchantId(merchantId);
		regist.setMsgExt(regist.getMsgExt());
		regist.setMisc(regist.getMisc());
		if ("".equals(checkCode)) {
			setCheckCode("3001");
		}
		regist.setAccountName(regist.getMobileNum());// 返回手机号
		regist.setRespCode(checkCode);
		regist.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
		return regist;
	
	}
	
	
	public AccountRegist changeCard(AccountRegist regist) throws Exception {
		setCheckCode("");
		regist.copyBusBeanParent(regist);
		String merchantId = regist.getMerchantId();// 商户号
		Map<Object, Object> map = new HashMap<Object, Object>();
		procedureDao.getSequence(map);
		SubMerInfo subMerInfo = subMerInfoDao.getSubMerInfoById(merchantId);
		try {
			if(subMerInfo!=null && subMerInfo.getStatus().equals("2")){
				setCheckCode("9004");
				String status = "0";// 子商户状态
						setCheckCode("3039");
						boolean res = false;
						// 一次只能有一个线程进入，将子商户号组装完整
						synchronized (merchantId) {
							SubMerInfo merInfo = new SubMerInfo();
							merInfo.setSubMerId(merchantId);
							res = changeCard(regist,status, merchantId);
						}
			}else{
				setCheckCode("9008");
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
		}
		regist.setApplication("ChangeCard.Rsp");
		regist.setTerminalId(regist.getTerminalId());
		regist.setFactoryId(regist.getFactoryId());
		regist.setMerchantName(regist.getMerchantName());
		regist.setLegalManName(regist.getLegalManName());
		regist.setLegalManIdcard(regist.getLegalManIdcard());
		regist.setMobileNum(regist.getMobileNum());
		regist.setPersonalMerRegNo(regist.getPersonalMerRegNo());
		regist.setTaxNo(regist.getTaxNo());
		regist.setOccNo("");
		regist.setSettleAccountType(regist.getSettleAccountType());
		regist.setSettleAccount(regist.getSettleAccount());
		regist.setSettleAccountNo(regist.getSettleAccountNo());
		regist.setSettleAgency(regist.getSettleAgency());
		regist.setAccountPwd("");
		regist.setTerminalInFo("");
		regist.setMerchantId(merchantId);
		regist.setMsgExt(regist.getMsgExt());
		regist.setMisc(regist.getMisc());
		if ("".equals(checkCode)) {
			setCheckCode("3001");
		}
		regist.setAccountName(regist.getMobileNum());// 返回手机号
		regist.setRespCode(checkCode);
		regist.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
		return regist;
	}
	
	
	/**
	 * 添加修改商户相关信息
	 * 
	 * @param merTrans
	 * @param subMerTerminal
	 * @param subMerId
	 * @param regist
	 * @throws Exception
	 * @throws Exception
	 */
	private void optSubMerInfo(MerTrans merTrans,
			SubMerTerminal subMerTerminal, Long subMerId, AccountRegist regist,
			Integer terminalId) throws Exception {
		String subMerIds = subMerId.toString();
		// 添加子商户交易费率信息
		insertSubMerRateInfo(merTrans, subMerIds, subMerTerminal);
		// 添加子商户交易配置信息
		insertSubMerTransInfo(merTrans, subMerIds, regist);
		// 修改子商户设备信息
		updateSubMerTerminal(regist, subMerIds);
		// 添加子商户终端信息
		if (insertSubMerTerminalInfo(subMerIds, regist, merTrans, terminalId)) {
			setCheckCode("0000");
		}
	}
	
	private void optSubMerInfoAndTerminal(MerTrans merTrans,
			SubMerTerminal subMerTerminal, Long subMerId, AccountRegist regist,
			Integer terminalId) throws Exception {
		String subMerIds = subMerId.toString();
		// 添加子商户交易费率信息
		insertSubMerRateInfo(merTrans, subMerIds, subMerTerminal);
		// 添加子商户交易配置信息
		insertSubMerTransInfo(merTrans, subMerIds, regist);
		// 修改子商户设备信息
		updateSubMerTerminal(regist, subMerIds);
		// 修改mobileUser信息
		updateMoblieUser(regist, subMerIds);
		// 添加子商户终端信息
		if (insertSubMerTerminalInfo(subMerIds, regist, merTrans, terminalId)) {
			setCheckCode("0000");
		}
	}
	
	/**
	 * 添加子商户管理员
	 * 
	 * @param subMerIds
	 * @return
	 */
	private boolean insertSubMerManager(String subMerId, AccountRegist regist)
			throws Exception {
		boolean result = false;

		MerManager merManager = new MerManager();
		merManager.setMid(subMerId);
		merManager.setMerType("1");
		merManager.setLoginName("admin");
		merManager.setLoginPwd(MD5.getHashString("admin"));
		merManager.setRealName(regist.getLegalManName());
		merManager.setPhone(regist.getMobileNum());
		merManager.setEmail("");
		merManager.setCreateTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
		merManager.setStatus("1");
		merManager.setRemark("1");

		if (merManagerDao.insertMerManager(merManager) > 0) {
			result = true;
		} else {
			setCheckCode("3049");
		}
		return result;
	}

	/**
	 * 添加子商户终端信息
	 * 
	 * @param string
	 * @param regist
	 * @return
	 */
	private boolean insertSubMerTerminalInfo(String subMerId,
			AccountRegist regist, MerTrans merTrans, Integer terminalId)
			throws Exception {
		boolean result = false;
		SubMerTerminalInfo subMerTerminalInfo = new SubMerTerminalInfo();
		setCheckCode("3045");
		subMerTerminalInfo.setTerminalId(terminalId.toString());// subMerId.substring(subMerId.length()-8,
		// subMerId.length())
		subMerTerminalInfo.setSubMerId(subMerId);
		subMerTerminalInfo.setTsn(regist.getTerminalId());
		// subMerTerminalInfo.setFactoryId(regist.getFactoryId());
		// 自动审核
		if (null != merTrans.getAutoApprove()
				&& "1".equals(merTrans.getAutoApprove())) {
			subMerTerminalInfo.setTerminalType(merTrans.getRateType());
		}
		// 手动审核
		if (null != merTrans.getAutoApprove()
				&& "0".equals(merTrans.getAutoApprove())) {
			subMerTerminalInfo.setTerminalType("01");
		}

		setCheckCode("3046");
		int re = subMerTerminalInfoDao
				.insertSubMerTerminalInfo(subMerTerminalInfo);
		if (re > 0) {
			result = true;
		}
		return result;
	}

	/**
	 * 修改子商户设备信息
	 * 
	 * @param results
	 * @param string
	 * @return
	 */
	private Boolean updateSubMerTerminal(AccountRegist regist, String subMerId)
			throws Exception {
		Boolean result = false;
		SubMerTerminal smt = new SubMerTerminal();
		smt.setTsn(regist.getTerminalId());
		// smt.setFactory(regist.getFactoryId());
		smt.setSubMerId(subMerId);
		smt.setLoginName(regist.getMobileNum());
		smt.setLoginPwd(regist.getAccountPwd());
		// 修改子商户设备信息
		setCheckCode("3021");
		if (subMerTerminalDao.updateSubMerTerminal(smt)) {
			result = true;
		}
		return result;
	}

	private Boolean updateMoblieUser(AccountRegist regist, String subMerId)
			throws Exception {
		Boolean result = false;
		MobileUser smt = new MobileUser();
		smt.setSubMerId(subMerId.trim());
		smt.setOldSubMerId(regist.getMerchantId());
		smt.setTerminalId(regist.getTerminalId());
		smt.setStatus("2");
		// 修改子商户设备信息
		setCheckCode("3021");
		if (mobileUserDao.updateTerminal(smt)) {
			result = true;
		}
		return result;
	}
	
	/**
	 * 添加子商户交易配置信息
	 * 
	 * @param merTrans
	 * @param subMerId
	 */
	private Boolean insertSubMerTransInfo(MerTrans merTrans, String subMerId,
			AccountRegist regist) throws Exception {
	    SubMerTrans oldTrans = new SubMerTrans();
	    oldTrans.setSubMerId(subMerId);
	    oldTrans = subMerTransDao.selectSubMerTransById(oldTrans);
	    if(oldTrans!=null){
	        return true;
	    }
		SubMerInfo subMerInfo = subMerInfoDao.getSubMerInfoById(subMerId);
		Boolean result = false;
		SubMerTrans subMerTrans = new SubMerTrans();
		subMerTrans.setSubMerId(subMerId);
		subMerTrans.setDispMerId(merTrans.getMerSysId());
		String dispName = "";
		if (null != regist.getMerchantName()
				&& !"".equals(regist.getMerchantName())) {
			dispName = subMerInfo.getSubMerName();
		} else {
			dispName = subMerInfo.getSubMerName();
		}
		subMerTrans.setDisMerName(dispName);
		subMerTrans.setTerminalType(merTrans.getTerminalType());
		subMerTrans.setBusType(merTrans.getBusType());
		// subMerTrans.setClearTime("01");
		subMerTrans.setAuthStatus("0");// 认证状态
		// subMerTrans.setAuthTime(new
		// SimpleDateFormat("yyyy-MM-dd mm:hh:ss").format(new Date()));
		subMerTrans.setCreateTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
		setCheckCode("3023");
		if (subMerTransDao.insertSubMerTransInfo(subMerTrans) > 0) {
			result = true;
		}
		return result;
	}

	/**
	 * 添加子商户交易费率
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean insertSubMerRateInfo(MerTrans merTrans, String subMerId,
			SubMerTerminal subMerTerminal) throws Exception {
		setCheckCode("3022");
		boolean result = false;
		SubMerRate oldRate = new SubMerRate();
		oldRate.setSubMerId(subMerId);
		oldRate = subMerRateDao.selectSubMerRateGetById(oldRate);
		if(oldRate!=null){
		    return true;
		}
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		String agentL1Rate = "-1";// 一级代理费率（单位%）扣率
		String agentL1Rate2 = "-1";//封顶
		String agentL1HighestFee = "-1";// 封顶值
		String agentL1ProfitRate = "-1";// 分润占比（单位%）
		String agentL1NoTop = "-1"; //积分费率

		String agentL2Rate = "-1";// 二级代理费率（单位%）
		String agentL2Rate2 = "-1";// 封顶
		String agentL2HighestFee = "-1";// 封顶值
		String agentL2ProfitRate = "-1";// 分润占比（单位%）
		String agentL2NoTop = "-1"; //积分费率
		
		String agentL3Rate = "-1";// 三级代理费率（单位%）
		String agentL3Rate2 = "-1";// 封顶
		String agentL3HighestFee = "-1";// 封顶值
		String agentL3ProfitRate = "-1";// 分润占比（单位%）
		String agentL3NoTop = "-1"; //积分费率
		
		String agentL4Rate = "-1";// 四级代理费率（单位%）
		String agentL4Rate2 = "-1";// 封顶
		String agentL4HighestFee = "-1";// 封顶值
		String agentL4ProfitRate = "-1";// 分润占比（单位%）
		String agentL4NoTop = "-1"; //积分费率
		String agentL5Rate = "-1";// 四级代理费率（单位%）
		String agentL6Rate = "-1";// 四级代理费率（单位%）
		String agentL7Rate = "-1";// 四级代理费率（单位%）
		String agentL8Rate = "-1";// 四级代理费率（单位%）
		String agentL9Rate = "-1";// 四级代理费率（单位%）
		String agentL10Rate = "-1";// 四级代理费率（单位%）

		// 一级代理商
		if (null != subMerTerminal.getAgentIdL1() && !"".equals(subMerTerminal.getAgentIdL1())
				&& !"-1".equals(subMerTerminal.getAgentIdL1())) {
			// 获取代理商信息
			AgenctInfo agenctInfo = agenctInfoDao.getAgentInfo(subMerTerminal.getAgentIdL1());
			if(null != agenctInfo) {
				agentL1Rate = agenctInfo.getAgentRate1();
				agentL1Rate2 = agenctInfo.getAgentRate2();
				agentL1HighestFee = agenctInfo.getAgentHighestFee();
				agentL1ProfitRate = agenctInfo.getAgentProfitRate();
				agentL1NoTop = agenctInfo.getAgentRateNoTop();
				
				if(null == agentL1Rate || "".equals(agentL1Rate) || "-1".equals(agentL1Rate)){
					agentL1Rate = "-1";
				}
				if(null == agentL1Rate2 || "".equals(agentL1Rate2) || "-1".equals(agentL1Rate2)){
					agentL1Rate2 = "-1";
				}
				if(null == agentL1HighestFee || "".equals(agentL1HighestFee) || "-1".equals(agentL1HighestFee)){
					agentL1HighestFee = "-1";
				}
				if(null == agentL1ProfitRate || "".equals(agentL1ProfitRate) || "-1".equals(agentL1ProfitRate)){
					agentL1ProfitRate = "-1";
				}
				if(null == agentL1NoTop || "".equals(agentL1NoTop) || "-1".equals(agentL1NoTop)){
					agentL1NoTop = "-1";
				}
			}
		}
		// 二级代理商
		if (null != subMerTerminal.getAgentIdL2() && !"".equals(subMerTerminal.getAgentIdL2())
				&& !"-1".equals(subMerTerminal.getAgentIdL2())) {
			// 获取代理商信息
			AgenctInfo agenctInfo = agenctInfoDao.getAgentInfo(subMerTerminal.getAgentIdL2());
			if(null != agenctInfo) {
				agentL2Rate = agenctInfo.getAgentRate1();
				agentL2Rate2 = agenctInfo.getAgentRate2();
				agentL2HighestFee = agenctInfo.getAgentHighestFee();
				agentL2ProfitRate = agenctInfo.getAgentProfitRate();
				agentL2NoTop = agenctInfo.getAgentRateNoTop();
				
				if(null == agentL2Rate || "".equals(agentL2Rate) || "-1".equals(agentL2Rate)){
					agentL2Rate = "-1";
				}
				if(null == agentL2Rate2 || "".equals(agentL2Rate2) || "-1".equals(agentL2Rate2)){
					agentL2Rate2 = "-1";
				}
				if(null == agentL2HighestFee || "".equals(agentL2HighestFee) || "-1".equals(agentL2HighestFee)){
					agentL2HighestFee = "-1";
				}
				if(null == agentL2ProfitRate || "".equals(agentL2ProfitRate) || "-1".equals(agentL2ProfitRate)){
					agentL2ProfitRate = "-1";
				}
				if(null == agentL2NoTop || "".equals(agentL2NoTop) || "-1".equals(agentL2NoTop)){
					agentL2NoTop = "-1";
				}
			}
			
		}
		
		// 三级代理商
		if (null != subMerTerminal.getAgentIdL3() && !"".equals(subMerTerminal.getAgentIdL3())
				&& !"-1".equals(subMerTerminal.getAgentIdL3())) {
			// 获取代理商信息
			AgenctInfo agenctInfo = agenctInfoDao.getAgentInfo(subMerTerminal.getAgentIdL3());
			if(null != agenctInfo) {
				agentL3Rate = agenctInfo.getAgentRate1();
				agentL3Rate2 = agenctInfo.getAgentRate2();
				agentL3HighestFee = agenctInfo.getAgentHighestFee();
				agentL3ProfitRate = agenctInfo.getAgentProfitRate();
				agentL3NoTop = agenctInfo.getAgentRateNoTop();
				
				if(null == agentL3Rate || "".equals(agentL3Rate) || "-1".equals(agentL3Rate)){
					agentL3Rate = "-1";
				}
				if(null == agentL3Rate2 || "".equals(agentL3Rate2) || "-1".equals(agentL3Rate2)){
					agentL3Rate2 = "-1";
				}
				if(null == agentL3HighestFee || "".equals(agentL3HighestFee) || "-1".equals(agentL3HighestFee)){
					agentL3HighestFee = "-1";
				}
				if(null == agentL3ProfitRate || "".equals(agentL3ProfitRate) || "-1".equals(agentL3ProfitRate)){
					agentL3ProfitRate = "-1";
				}
				if(null == agentL3NoTop || "".equals(agentL3NoTop) || "-1".equals(agentL3NoTop)){
					agentL3NoTop = "-1";
				}
			}
			
		}
			
		// 四级代理商
		if (null != subMerTerminal.getAgentIdL4() && !"".equals(subMerTerminal.getAgentIdL4())
				&& !"-1".equals(subMerTerminal.getAgentIdL4())) {
			// 获取代理商信息
			AgenctInfo agenctInfo = agenctInfoDao.getAgentInfo(subMerTerminal.getAgentIdL4());
			if(null != agenctInfo) {
				agentL4Rate = agenctInfo.getAgentRate1();
				agentL4Rate2 = agenctInfo.getAgentRate2();
				agentL4HighestFee = agenctInfo.getAgentHighestFee();
				agentL4ProfitRate = agenctInfo.getAgentProfitRate();
				agentL4NoTop = agenctInfo.getAgentRateNoTop();
				
				if(null == agentL4Rate || "".equals(agentL4Rate) || "-1".equals(agentL4Rate)){
					agentL4Rate = "-1";
				}
				if(null == agentL4Rate2 || "".equals(agentL4Rate2) || "-1".equals(agentL4Rate2)){
					agentL4Rate2 = "-1";
				}
				if(null == agentL4HighestFee || "".equals(agentL4HighestFee) || "-1".equals(agentL4HighestFee)){
					agentL4HighestFee = "-1";
				}
				if(null == agentL4ProfitRate || "".equals(agentL4ProfitRate) || "-1".equals(agentL4ProfitRate)){
					agentL4ProfitRate = "-1";
				}
				if(null == agentL4NoTop || "".equals(agentL4NoTop) || "-1".equals(agentL4NoTop)){
					agentL4NoTop = "-1";
				}
			}
			
		}
		
		if (null != subMerTerminal.getAgentIdL5() && !"".equals(subMerTerminal.getAgentIdL5())
                && !"-1".equals(subMerTerminal.getAgentIdL5())) {
            // 获取代理商信息
            AgenctInfo agenctInfo = agenctInfoDao.getAgentInfo(subMerTerminal.getAgentIdL5());
            if(null != agenctInfo) {
                agentL5Rate = agenctInfo.getAgentRate1();
                if(null == agentL5Rate || "".equals(agentL5Rate) || "-1".equals(agentL5Rate)){
                    agentL5Rate = "-1";
                }
            }
        }
		
		if (null != subMerTerminal.getAgentIdL6() && !"".equals(subMerTerminal.getAgentIdL6())
                && !"-1".equals(subMerTerminal.getAgentIdL6())) {
            // 获取代理商信息
            AgenctInfo agenctInfo = agenctInfoDao.getAgentInfo(subMerTerminal.getAgentIdL6());
            if(null != agenctInfo) {
                agentL6Rate = agenctInfo.getAgentRate1();
                if(null == agentL6Rate || "".equals(agentL6Rate) || "-1".equals(agentL6Rate)){
                    agentL6Rate = "-1";
                }
            }
        }
		
		if (null != subMerTerminal.getAgentIdL7() && !"".equals(subMerTerminal.getAgentIdL7())
		        && !"-1".equals(subMerTerminal.getAgentIdL7())) {
		    // 获取代理商信息
		    AgenctInfo agenctInfo = agenctInfoDao.getAgentInfo(subMerTerminal.getAgentIdL7());
		    if(null != agenctInfo) {
		        agentL7Rate = agenctInfo.getAgentRate1();
		        if(null == agentL7Rate || "".equals(agentL7Rate) || "-1".equals(agentL7Rate)){
		            agentL7Rate = "-1";
		        }
		    }
		}
		
		if (null != subMerTerminal.getAgentIdL8() && !"".equals(subMerTerminal.getAgentIdL8())
		        && !"-1".equals(subMerTerminal.getAgentIdL8())) {
		    // 获取代理商信息
		    AgenctInfo agenctInfo = agenctInfoDao.getAgentInfo(subMerTerminal.getAgentIdL8());
		    if(null != agenctInfo) {
		        agentL8Rate = agenctInfo.getAgentRate1();
		        if(null == agentL8Rate || "".equals(agentL8Rate) || "-1".equals(agentL8Rate)){
		            agentL8Rate = "-1";
		        }
		    }
		}
		
		if (null != subMerTerminal.getAgentIdL9() && !"".equals(subMerTerminal.getAgentIdL9())
		        && !"-1".equals(subMerTerminal.getAgentIdL9())) {
		    // 获取代理商信息
		    AgenctInfo agenctInfo = agenctInfoDao.getAgentInfo(subMerTerminal.getAgentIdL9());
		    if(null != agenctInfo) {
		        agentL9Rate = agenctInfo.getAgentRate1();
		        if(null == agentL9Rate || "".equals(agentL9Rate) || "-1".equals(agentL9Rate)){
		            agentL9Rate = "-1";
		        }
		    }
		}
		
		if (null != subMerTerminal.getAgentIdL10() && !"".equals(subMerTerminal.getAgentIdL10())
		        && !"-1".equals(subMerTerminal.getAgentIdL10())) {
		    // 获取代理商信息
		    AgenctInfo agenctInfo = agenctInfoDao.getAgentInfo(subMerTerminal.getAgentIdL10());
		    if(null != agenctInfo) {
		        agentL10Rate = agenctInfo.getAgentRate1();
		        if(null == agentL10Rate || "".equals(agentL10Rate) || "-1".equals(agentL10Rate)){
		            agentL10Rate = "-1";
		        }
		    }
		}
		
		map.put("subMerId", subMerId);
		map.put("rateType", "03");
		map.put("transRate", "-1");
		map.put("transRateH", "-1");
		map.put("transHighestFee", "-1");
		map.put("lowestFee", "-1");
		
		map.put("agentL1Rate", agentL1Rate);
		map.put("agentL1RateH", agentL1Rate2);
		map.put("agentL1HighestFee", agentL1HighestFee);
		map.put("agentL1NoTop", agentL1NoTop);
		map.put("agentL1ProfitRate", agentL1ProfitRate);
		map.put("agentL2Rate", agentL2Rate);
		map.put("agentL2RateH", agentL2Rate2);
		map.put("agentL2HighestFee", agentL2HighestFee);
		map.put("agentL2NoTop", agentL2NoTop);
		map.put("agentL2ProfitRate", agentL2ProfitRate);
		
		map.put("agentL3Rate", agentL3Rate);
		map.put("agentL3RateH", agentL3Rate2);
		map.put("agentL3HighestFee", agentL3HighestFee);
		map.put("agentL3NoTop", agentL3NoTop);
		map.put("agentL3ProfitRate", agentL3ProfitRate);
		
		map.put("agentL4Rate", agentL4Rate);
		map.put("agentL4RateH", agentL4Rate2);
		map.put("agentL4HighestFee", agentL4HighestFee);
		map.put("agentL4NoTop", agentL4NoTop);
		map.put("agentL4ProfitRate", agentL4ProfitRate);
		
		map.put("agentL5Rate", agentL5Rate);
		map.put("agentL6Rate", agentL6Rate);
		map.put("agentL7Rate", agentL7Rate);
		map.put("agentL8Rate", agentL8Rate);
		map.put("agentL9Rate", agentL9Rate);
		map.put("agentL10Rate", agentL10Rate);
		map.put("createTime", DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
		map.put("status", "1");
		
		if (subMerRateDao.insertSubMerRateInfo(map) > 0) {
			result = true;
		}
		return result;
	}

	// 实人认证YJH
	public String realPeopleAuthentication(SubMerInfo subMerInfo)
			throws Exception {
		String serialNo = "-1";// 流水号
		String authStatus = "-1";// 认证状态

		try {
			// 获取人脸数据
			byte datas[] = null;
			BASE64Encoder encoder = new BASE64Encoder();
			String path = rb.getString("ImagesUrl");
			path += "/SubMerImages/" + subMerInfo.getMerSysId() + "/"
					+ subMerInfo.getSubMerId() + "/" + "person.jpg";
			FileInputStream is = null;
			File file = new File(path);
			if (file.exists()) {
				is = new FileInputStream(path);
				int i = is.available(); // 得到文件大小
				datas = new byte[i];
				is.read(datas); // 读数据
				is.close();
			}
			String orderId = ValueTool.createByTime();
			String userName = subMerInfo.getLegalPerson();
			String idCard = subMerInfo.getLegalIdcard();
			String imgInfo = "";
			if (null != datas) {
				imgInfo = encoder.encode(datas);
			}
			String merchentId = ValueTool.AUTH_MER_ID;
			String orderTime = DateUtil.getDate("yyyyMMddHHmmss");
			// 报文第一段
			String first = merchentId;
			// 报文第二段
			String Second = "201|" + orderId + "|" + orderTime + "|" + userName
					+ "|" + idCard + "|" + imgInfo;
			// 报文第三段
			String Third = merchentId + "201" + orderId + "" + orderTime + ""
					+ idCard + "" + ValueTool.AUTH_MER_SIGN_KEY + "";

			// 发送请求
			AuthenticationUtil authenticationUtil = new AuthenticationUtil();
			System.out.println("Second:" + Second);
			String data = authenticationUtil.encrypt(first, Second, Third);// 加密并组合
			RemoteAccessor remoteAccessor = new RemoteAccessor();
			String paths = rb.getString("shimingUrl");
			String decryptXml = remoteAccessor.getResponseByStream(paths,
					"utf-8", data);
			String[] ss = decryptXml.split("\\|");
			if (ss[0].equals("0")) {// 解析成功
				String respXml = new AuthenticationUtil().decrypt(ss[1]);
				String[] result = respXml.split("\\|");
				String respCode = result[5];// 返回码
				serialNo = result[4];// 流水号
				System.out.println("respCode:" + respCode + " miaoshu:"
						+ result[6]);
				if (!"".equals(respCode) && "0000".equals(respCode)) {// 认证成功
					authStatus = "2";
				} else {
					authStatus = "4";
				}
			}
			if (ss[0].equals("1")) {// 解析失败
				BASE64Decoder decoder = new BASE64Decoder();
				System.out.println(">>>>>>>" + ss[1]
						+ new String(decoder.decodeBuffer(ss[2]), "utf-8"));
				authStatus = "4";
			}
		} catch (Exception e) {
			authStatus = "4";
		}
		// authStatus = "2";
		// serialNo = "00";
		return authStatus + "|" + serialNo;
	}

	/**
	 * 添加子商户信息
	 * 
	 * @param regist
	 * @return
	 */
	public boolean insertSubMerInfo(AccountRegist regist,
			SubMerTerminal subMerTerminal, String status, Long subMerId,
			MerTrans merTrans) throws Exception {

		status = "0";

		boolean boo = false;
		SubMerInfo subMerInfo = new SubMerInfo();
		subMerInfo.setSubMerId(String.valueOf(subMerId));
		subMerInfo.setLineNum(regist.getLineNum());
		subMerInfo.setOpenBank(regist.getOpenBank());
		if (null != regist) {
			String subMerName = "";
			String shortName = "";
			if (null != regist.getMerchantName()
					&& !"".equals(regist.getMerchantName())) {
				subMerName = regist.getMerchantName();
				shortName = regist.getMerchantName();
			} else {
				subMerName = regist.getLegalManName();
				shortName = regist.getLegalManName();
			}
			subMerInfo.setSubMerName(subMerName);
			subMerInfo.setShortName(shortName);
			subMerInfo.setLegalPerson(regist.getLegalManName());
			subMerInfo.setLegalIdcard(regist.getLegalManIdcard());
			subMerInfo.setRegNo(regist.getPersonalMerRegNo());
			subMerInfo.setTaxNo(regist.getTaxNo());
			subMerInfo.setOrganizationCode(regist.getOccNo());
			String settAccType = "-1";
			if (!"".equals(regist.getSettleAccountType())
					&& null != regist.getSettleAccountType()) {
				settAccType = regist.getSettleAccountType();
			}
			subMerInfo.setSettAccType(settAccType);
			String settAccName = "-1";
			if (!"".equals(regist.getSettleAccount())
					&& null != regist.getSettleAccount()) {
				settAccName = regist.getSettleAccount();
			}
			subMerInfo.setSettAccountName(regist.getLegalManName());
			String settAccNo = "-1";
			if (!"".equals(regist.getSettleAccountNo())
					&& null != regist.getSettleAccountNo()) {
				settAccNo = regist.getSettleAccountNo();
			}
			subMerInfo.setSettAccountNo(settAccNo);
			String settAgency = "ICBC";
			if (!"".equals(regist.getSettleAgency())
					&& null != regist.getSettleAgency()
					&& regist.getSettleAgency().indexOf("null") == -1) {
				settAgency = regist.getSettleAgency();
			}
			subMerInfo.setSettAgency(settAgency);
			subMerInfo.setStatus(status);
			String merSysId = "";
			String agentIdL1 = "";
			String agentIdL2 = "";
			String agentIdL3 = "";
			String agentIdL4 = "";
			if (null != subMerTerminal) {
				merSysId = subMerTerminal.getMerSysId();
				agentIdL1 = subMerTerminal.getAgentIdL1();
				agentIdL2 = subMerTerminal.getAgentIdL2();
				agentIdL3 = subMerTerminal.getAgentIdL3();
				agentIdL4 = subMerTerminal.getAgentIdL4();
			}
			subMerInfo.setMerSysId(merSysId);
			subMerInfo.setMcc(merTrans.getBasicMcc());
			subMerInfo.setRegion(merTrans.getBasicRegion());
			subMerInfo.setCreateTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
			subMerInfo.setAgentIdL1(agentIdL1);
			subMerInfo.setAgentIdL2(agentIdL2);
			subMerInfo.setAgentIdL3(agentIdL3);
			subMerInfo.setAgentIdL4(agentIdL4);
			if ("1".equals(merTrans.getAutoApprove())) {
				subMerInfo.setBillCycle("01");
			} else {
				subMerInfo.setBillCycle("01");
			}
			subMerInfo.setBillStatus("1");
			subMerInfo.setMerKind("个体户");// 默认公司性质
			subMerInfo.setContactor(regist.getLegalManName());
			subMerInfo.setContactorPhone(regist.getMobileNum());
			//公司地址
			subMerInfo.setRegAddr(regist.getSubMerAddress());
		}
		setCheckCode("9009");
		int result = subMerInfoDao.insertSubMerInfo(subMerInfo);
		if (result > 0) {
			boo = true;
		}
		return boo;
	}
	
	/**
	 * 更换绑定机具
	 * @Title:        updateSubMerInfo 
	 * @Description:  
	 * @param:        @param regist
	 * @param:        @param subMerTerminal
	 * @param:        @param status
	 * @param:        @param subMerId
	 * @param:        @param merTrans
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       boolean    
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2015-9-8 下午3:59:32
	 */
	public boolean updateSubMerInfo(AccountRegist regist,
			SubMerTerminal subMerTerminal, String status, Long subMerId,
			MerTrans merTrans) throws Exception {
		status = "0";
		boolean boo = false;
		SubMerInfo subMerInfo = new SubMerInfo();
		subMerInfo.setSubMerId(String.valueOf(subMerId));
		subMerInfo.setOldSubMerId(regist.getMerchantId());
//		subMerInfo.setLineNum(regist.getLineNum());
//		subMerInfo.setOpenBank(regist.getOpenBank());
		String merSysId = "";
		if (null != regist && regist.getMerchantId().length()>15) {
//			String subMerName = "";
//			String shortName = "";
//			if (null != regist.getMerchantName()
//					&& !"".equals(regist.getMerchantName())) {
//				subMerName = regist.getMerchantName();
//				shortName = regist.getMerchantName();
//			} else {
//				subMerName = regist.getLegalManName();
//				shortName = regist.getLegalManName();
//			}
//			subMerInfo.setSubMerName(subMerName);
//			subMerInfo.setShortName(shortName);
//			subMerInfo.setLegalPerson(regist.getLegalManName());
//			subMerInfo.setLegalIdcard(regist.getLegalManIdcard());
			subMerInfo.setRegNo(regist.getPersonalMerRegNo());
			subMerInfo.setTaxNo(regist.getTaxNo());
			subMerInfo.setOrganizationCode(regist.getOccNo());
//			String settAccType = "-1";
//			if (!"".equals(regist.getSettleAccountType())
//					&& null != regist.getSettleAccountType()) {
//				settAccType = regist.getSettleAccountType();
//			}
//			subMerInfo.setSettAccType(settAccType);
//			String settAccName = "-1";
//			if (!"".equals(regist.getSettleAccount())
//					&& null != regist.getSettleAccount()) {
//				settAccName = regist.getSettleAccount();
//			}
//			subMerInfo.setSettAccountName(regist.getLegalManName());
//			String settAccNo = "-1";
//			if (!"".equals(regist.getSettleAccountNo())
//					&& null != regist.getSettleAccountNo()) {
//				settAccNo = regist.getSettleAccountNo();
//			}
//			subMerInfo.setSettAccountNo(settAccNo);
//			String settAgency = "ICBC";
//			if (!"".equals(regist.getSettleAgency())
//					&& null != regist.getSettleAgency()
//					&& regist.getSettleAgency().indexOf("null") == -1) {
//				settAgency = regist.getSettleAgency();
//			}
//			subMerInfo.setSettAgency(settAgency);
			subMerInfo.setStatus(status);
			String agentIdL1 = "";
			String agentIdL2 = "";
			String agentIdL3 = "";
			String agentIdL4 = "";
			String agentIdL5 = "";
			String agentIdL6 = "";
			String agentIdL7 = "";
			String agentIdL8 = "";
			String agentIdL9 = "";
			String agentIdL10 = "";
			if (null != subMerTerminal) {
				merSysId = subMerTerminal.getMerSysId();
				agentIdL1 = subMerTerminal.getAgentIdL1();
				agentIdL2 = subMerTerminal.getAgentIdL2();
				agentIdL3 = subMerTerminal.getAgentIdL3();
				agentIdL4 = subMerTerminal.getAgentIdL4();
				agentIdL5 = subMerTerminal.getAgentIdL5();
				agentIdL6 = subMerTerminal.getAgentIdL6();
				agentIdL7 = subMerTerminal.getAgentIdL7();
				agentIdL8 = subMerTerminal.getAgentIdL8();
				agentIdL9 = subMerTerminal.getAgentIdL9();
				agentIdL10 = subMerTerminal.getAgentIdL10();
			}
			subMerInfo.setMerSysId(merSysId);
			subMerInfo.setMcc(merTrans.getBasicMcc());
			subMerInfo.setRegion(merTrans.getBasicRegion());
//			subMerInfo.setCreateTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
			subMerInfo.setAgentIdL1(agentIdL1);
			subMerInfo.setAgentIdL2(agentIdL2);
			subMerInfo.setAgentIdL3(agentIdL3);
			subMerInfo.setAgentIdL4(agentIdL4);
			subMerInfo.setAgentIdL5(agentIdL5);
			subMerInfo.setAgentIdL6(agentIdL6);
			subMerInfo.setAgentIdL7(agentIdL7);
			subMerInfo.setAgentIdL8(agentIdL8);
			subMerInfo.setAgentIdL9(agentIdL9);
			subMerInfo.setAgentIdL10(agentIdL10);
			if ("1".equals(merTrans.getAutoApprove())) {
				subMerInfo.setBillCycle("01");
			} else {
				subMerInfo.setBillCycle("01");
			}
//			subMerInfo.setBillStatus("1");
//			subMerInfo.setMerKind("个体户");// 默认公司性质
//			subMerInfo.setContactor(regist.getLegalManName());
//			subMerInfo.setContactorPhone(regist.getMobileNum());
			//公司地址
			subMerInfo.setRegAddr(regist.getSubMerAddress());
		}
		if(subMerTerminal.getFactory().equals("16")){
		    subMerInfo.setScanTsn(subMerTerminal.getTsn());
		}
		setCheckCode("9009");
		int result = subMerInfoDao.updateSubMerInfoReg(subMerInfo);
		if (result > 0) {
			//切换图片
			String oldPath = rb.getString("ImagesUrl");
			oldPath += "/SubMerImages/-1/"
					+ regist.getMerchantId();
			String newPath = rb.getString("ImagesUrl");
			newPath += "/SubMerImages/"+merSysId+"/"
					+ String.valueOf(subMerId);
			File oldFile = new File(oldPath);
			oldFile.renameTo(new File(newPath));
			boo = true;
		}
		return boo;
	}
	
	/**
	 * 注册第一步
	 * @Title:        insertSubMerInfo 
	 * @Description:  
	 * @param:        @param regist
	 * @param:        @param subMerTerminal
	 * @param:        @param status
	 * @param:        @param subMerId
	 * @param:        @param merTrans
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       boolean    
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2015-8-27 上午10:36:06
	 */
	public boolean insertSubMerInfo(AccountRegist regist, String status, String subMerId) throws Exception {

		status = "0";

		boolean boo = false;
		MobileUser mobileUser = new MobileUser();
		mobileUser.setSubMerId(subMerId);
		mobileUser.setLoginName(regist.getMobileNum());
		mobileUser.setLoginPwd(regist.getAccountPwd());
		mobileUser.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		mobileUser.setStatus("0"); //初始用户
		int result=mobileUserDao.insertMobileUser(mobileUser);
		setCheckCode("9009");
		if(result > 0){
			boo = true;
			setCheckCode("0000");
		}
	
		return boo;
	}
	
	/**
	 * 注册第二部
	 * @Title:        insertSubMer 
	 * @Description:  
	 * @param:        @param regist
	 * @param:        @param subMerTerminal
	 * @param:        @param status
	 * @param:        @param subMerId
	 * @param:        @param merTrans
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       boolean    
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2015-8-31 下午6:04:06
	 */
	public boolean insertSubMer(AccountRegist regist,String status, String subMerId) throws Exception {

		status = "0";

		boolean boo = false;
		SubMerInfo subMerInfo = new SubMerInfo();
		subMerInfo.setSubMerId(subMerId);
		subMerInfo.setLineNum(regist.getLineNum());
		subMerInfo.setOpenBank(regist.getOpenBank());
		if (null != regist) {
			String subMerName = "";
			String shortName = "";
			if (null != regist.getMerchantName()
					&& !"".equals(regist.getMerchantName())) {
				subMerName = regist.getMerchantName();
				shortName = regist.getMerchantName();
			} else {
				subMerName = regist.getLegalManName();
				shortName = regist.getLegalManName();
			}
			subMerInfo.setSubMerName(subMerName);
			subMerInfo.setShortName(shortName);
			subMerInfo.setLegalPerson(regist.getLegalManName());
			subMerInfo.setLegalIdcard(regist.getLegalManIdcard());
			subMerInfo.setRegNo(regist.getPersonalMerRegNo());
			subMerInfo.setTaxNo(regist.getTaxNo());
			subMerInfo.setOrganizationCode(regist.getOccNo());
			String settAccType = "-1";
			if (!"".equals(regist.getSettleAccountType())
					&& null != regist.getSettleAccountType()) {
				settAccType = regist.getSettleAccountType();
			}
			subMerInfo.setSettAccType(settAccType);
			String settAccName = "-1";
			if (!"".equals(regist.getSettleAccount())
					&& null != regist.getSettleAccount()) {
				settAccName = regist.getSettleAccount();
			}
			subMerInfo.setSettAccountName(settAccName);
			String settAccNo = "-1";
			if (!"".equals(regist.getSettleAccountNo())
					&& null != regist.getSettleAccountNo()) {
				settAccNo = regist.getSettleAccountNo();
			}
			subMerInfo.setSettAccountNo(settAccNo);
			String settAgency = "ICBC";
			if (!"".equals(regist.getSettleAgency())
					&& null != regist.getSettleAgency()
					&& regist.getSettleAgency().indexOf("null") == -1) {
				settAgency = regist.getSettleAgency();
			}
			subMerInfo.setSettAgency(settAgency);
			subMerInfo.setStatus(status);
			String merSysId = "-1";
			String agentIdL1 = "-1";
			String agentIdL2 = "-1";
			String agentIdL3 = "-1";
			String agentIdL4 = "-1";
			subMerInfo.setMerSysId(merSysId);
//			subMerInfo.setMcc(merTrans.getBasicMcc());
//			subMerInfo.setRegion(merTrans.getBasicRegion());
			subMerInfo.setCreateTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
			subMerInfo.setAgentIdL1(agentIdL1);
			subMerInfo.setAgentIdL2(agentIdL2);
			subMerInfo.setAgentIdL3(agentIdL3);
			subMerInfo.setAgentIdL4(agentIdL4);
//			if ("1".equals(merTrans.getAutoApprove())) {
//				subMerInfo.setBillCycle("01");
//			} else {
//				subMerInfo.setBillCycle("01");
//			}
			subMerInfo.setBillStatus("1");
			subMerInfo.setMerKind("个体户");// 默认公司性质
			subMerInfo.setContactor(regist.getLegalManName());
			subMerInfo.setContactorPhone(regist.getMobileNum());
			//公司地址
			subMerInfo.setRegAddr(regist.getSubMerAddress());
		}
		setCheckCode("9009");
		int result = subMerInfoDao.insertSubMerInfo(subMerInfo);
		if (result > 0) {
			boo = true;
			setCheckCode("0000");
//			JSONObject mobileLocal = getMobileFrom(subMerInfo.getContactorPhone());
//			if(mobileLocal!=null){
//				subMerInfo.setPhoneProvince(mobileLocal.getString("province"));
//				subMerInfo.setPhoneCity(mobileLocal.getString("cityname"));
//				subMerInfoDao.updateMobileFrom(subMerInfo);
//			}
		}
		return boo;
	}
	
	public boolean updateSubMer(AccountRegist regist,String status, String subMerId) throws Exception {
		
		status = "0";
		
		boolean boo = false;
		SubMerInfo subMerInfo = new SubMerInfo();
		subMerInfo.setSubMerId(subMerId);
		subMerInfo.setOldSubMerId(subMerId);
		subMerInfo.setLineNum(regist.getLineNum());
		subMerInfo.setOpenBank(regist.getOpenBank());
		if (null != regist) {
			String subMerName = "";
			String shortName = "";
			if (null != regist.getMerchantName()
					&& !"".equals(regist.getMerchantName())) {
				subMerName = regist.getMerchantName();
				shortName = regist.getMerchantName();
			} else {
				subMerName = regist.getLegalManName();
				shortName = regist.getLegalManName();
			}
			subMerInfo.setSubMerName(subMerName);
			subMerInfo.setShortName(shortName);
			subMerInfo.setLegalPerson(regist.getLegalManName());
			subMerInfo.setLegalIdcard(regist.getLegalManIdcard());
			subMerInfo.setRegNo(regist.getPersonalMerRegNo());
			subMerInfo.setTaxNo(regist.getTaxNo());
			subMerInfo.setOrganizationCode(regist.getOccNo());
			String settAccType = "-1";
			if (!"".equals(regist.getSettleAccountType())
					&& null != regist.getSettleAccountType()) {
				settAccType = regist.getSettleAccountType();
			}
			subMerInfo.setSettAccType(settAccType);
			String settAccName = "-1";
			if (!"".equals(regist.getSettleAccount())
					&& null != regist.getSettleAccount()) {
				settAccName = regist.getSettleAccount();
			}
			subMerInfo.setSettAccountName(settAccName);
			String settAccNo = "-1";
			if (!"".equals(regist.getSettleAccountNo())
					&& null != regist.getSettleAccountNo()) {
				settAccNo = regist.getSettleAccountNo();
			}
			subMerInfo.setSettAccountNo(settAccNo);
			String settAgency = "ICBC";
			if (!"".equals(regist.getSettleAgency())
					&& null != regist.getSettleAgency()
					&& regist.getSettleAgency().indexOf("null") == -1) {
				settAgency = regist.getSettleAgency();
			}
			subMerInfo.setSettAgency(settAgency);
			subMerInfo.setStatus(status);
//			subMerInfo.setMerSysId(merSysId);
//			subMerInfo.setMcc(merTrans.getBasicMcc());
//			subMerInfo.setRegion(merTrans.getBasicRegion());
			subMerInfo.setCreateTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
//			subMerInfo.setAgentIdL1(agentIdL1);
//			subMerInfo.setAgentIdL2(agentIdL2);
//			subMerInfo.setAgentIdL3(agentIdL3);
//			subMerInfo.setAgentIdL4(agentIdL4);
//			if ("1".equals(merTrans.getAutoApprove())) {
//				subMerInfo.setBillCycle("01");
//			} else {
//				subMerInfo.setBillCycle("01");
//			}
			subMerInfo.setBillStatus("1");
			subMerInfo.setMerKind("个体户");// 默认公司性质
			subMerInfo.setContactor(regist.getLegalManName());
			subMerInfo.setContactorPhone(regist.getMobileNum());
			//公司地址
			subMerInfo.setRegAddr(regist.getSubMerAddress());
		}
		setCheckCode("9009");
		int result = subMerInfoDao.updateSubMerInfoReg(subMerInfo);
		if (result > 0) {
			boo = true;
			setCheckCode("0000");
		}
		return boo;
	}
	
	
	public boolean changeCard(AccountRegist regist,String status, String subMerId) throws Exception {
		status = "0";
		boolean boo = false;
		SubMerInfo subMerInfo = new SubMerInfo();
		subMerInfo.setSubMerId(subMerId);
//		subMerInfo.setLineNum(regist.getLineNum());
//		subMerInfo.setOpenBank(regist.getOpenBank());
		String settAccType = "-1";
		if (!"".equals(regist.getSettleAccountType())
				&& null != regist.getSettleAccountType()) {
			settAccType = regist.getSettleAccountType();
		}
		subMerInfo.setSettAccType(settAccType);
//		String settAccName = "-1";
//		if (!"".equals(regist.getSettleAccount())
//				&& null != regist.getSettleAccount()) {
//			settAccName = regist.getSettleAccount();
//		}
//		subMerInfo.setSettAccountName(settAccName);
		String settAccNo = "-1";
		if (!"".equals(regist.getSettleAccountNo())
				&& null != regist.getSettleAccountNo()) {
			settAccNo = regist.getSettleAccountNo();
		}
		subMerInfo.setSettAccountNo(settAccNo);
		String settAgency = "ICBC";
		if (!"".equals(regist.getSettleAgency())
				&& null != regist.getSettleAgency()
				&& regist.getSettleAgency().indexOf("null") == -1) {
			settAgency = regist.getSettleAgency();
		}
		subMerInfo.setSettAgency(settAgency);
		setCheckCode("9009");
		boolean result = subMerInfoDao.update(subMerInfo);
		if (result) {
			boo = true;
			setCheckCode("0000");
		}
		return boo;
	}
	
	
	@SuppressWarnings("unused")
	@Override
	public Object regMer(AccountLogIn accountLogIn) {
		accountLogIn.copyBusBeanParent(accountLogIn);
		setCheckCode("");
		accountLogIn.setMsgExt("9:00-20:00");
		String transRate = "";// 刷卡费率
		try {
			if (accountLogIn != null) {
				// 查询设备信息
				SubMerTerminal subMerTerminal = new SubMerTerminal();
				subMerTerminal.setTsn(accountLogIn.getTerminalId());
				subMerTerminal.setLoginName(accountLogIn.getAccountName());
				subMerTerminal.setLoginPwd(accountLogIn.getAccountPwd());
				subMerTerminal = subMerTerminalDao
						.getSubMerTerminal(subMerTerminal);
				if (subMerTerminal != null) {
					// 查询子商户信息
					SubMerInfo subMerInfo = new SubMerInfo();
					subMerInfo = subMerInfoDao.getSubMerInfoById(subMerTerminal
							.getSubMerId());
					setCheckCode("9006");
					if (null != subMerInfo) {
						// 组装返回内容
						accountLogIn.setTerminalId(subMerTerminal.getTsn());
						accountLogIn.setFactoryId(subMerTerminal.getFactory());
						accountLogIn
								.setMerchantId(subMerTerminal.getSubMerId());
						accountLogIn
								.setMerchantName(subMerInfo.getSubMerName());
						accountLogIn.setAccountName(subMerTerminal
								.getLoginName());
						accountLogIn.setPersonalMerRegNo(subMerInfo.getRegNo());
						accountLogIn.setTaxNo(subMerInfo.getTaxNo());
						accountLogIn.setLegalManIdcard(subMerInfo
								.getLegalIdcard());
						if ("-1".equals(subMerInfo.getSettAccountName()))
							subMerInfo.setSettAccountName("");
						if ("-1".equals(subMerInfo.getSettAccountNo()))
							subMerInfo.setSettAccountNo("");
						if ("-1".equals(subMerInfo.getSettAgency()))
							subMerInfo.setSettAgency("");
						accountLogIn.setSettleAccount(subMerInfo
								.getSettAccountName());
						accountLogIn.setSettleAccountNo(subMerInfo
								.getSettAccountNo());
						accountLogIn
								.setSettleAgency(subMerInfo.getSettAgency());
						String merStatus = "";
						merStatus = subMerInfo.getStatus();
						if ("2".equals(merStatus)) {
							merStatus = "1";
						} else if ("5".equals(merStatus)) {
							merStatus = "2";
						} else {
							merStatus = "0";
						}
						accountLogIn.setAccountStatus(merStatus);
						String authStatus = "";
						if (null != subMerInfo.getSettAccType()
								&& !"".equals(subMerInfo.getSettAccType())
								&& "1".equals(subMerInfo.getSettAccType())) {
							authStatus = "null";
						} else {
							authStatus = subMerInfo.getAuthStatus();
						}
						accountLogIn.setAuthStatus(authStatus);
						accountLogIn.setSettleAccountType(subMerInfo
								.getSettAccType());
						accountLogIn.setApplication("AccountLogIn.Rsp");
						setCheckCode("0000");

						// 子商户交易费率
						SubMerRate subMerRate = new SubMerRate();
						subMerRate.setSubMerId(subMerTerminal.getSubMerId());
						subMerRate = subMerRateDao
								.getSubMerRateBySubMerId(subMerRate);
						if (null != subMerRate) {
							transRate = subMerRate.getTeransRate();
							if ("-1".equals(transRate)) {
								transRate = "0";
							}
						}
						accountLogIn.setTransRate(transRate);
					}
				} else {
					setCheckCode("9017");
				}

				// 修改设备最近登录时间
				SubMerTerminal terminal = new SubMerTerminal();
				terminal.setTsn(accountLogIn.getTerminalId());
				terminal.setLastLoginTime(DateUtil
						.getDate("yyyy-MM-dd HH:mm:ss"));
				subMerTerminalDao.updateSubMerByLastLoginTime(terminal);
			} else {
				setCheckCode("9017");
			}
		} catch (Exception e) {
			setCheckCode("9017");
			e.printStackTrace();
		}
		accountLogIn.setRespCode(checkCode);
		accountLogIn.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
		return accountLogIn;
	}

	@SuppressWarnings("unused")
	@Override
	public Object userMer(AccountLogIn accountLogIn) {
		accountLogIn.copyBusBeanParent(accountLogIn);
		setCheckCode("");
		accountLogIn.setMsgExt("9:00-20:00");
		String transRate = "";// 刷卡费率
		try {
			if (accountLogIn != null) {
				MobileUser mobileUser = new MobileUser();
				mobileUser.setLoginName(accountLogIn.getAccountName());
//				mobileUser.setLoginPwd(accountLogIn.getAccountPwd());
				mobileUser = mobileUserDao.getMobileUser(mobileUser);
				// 查询设备信息
				SubMerTerminal subMerTerminal = new SubMerTerminal();
				subMerTerminal.setLoginName(accountLogIn.getAccountName());
//				subMerTerminal.setLoginPwd(accountLogIn.getAccountPwd());
				subMerTerminal = subMerTerminalDao
						.getSubMerTerminal(subMerTerminal);
				if(subMerTerminal!=null&&mobileUser==null &&accountLogIn.getAccountPwd().equals(subMerTerminal.getLoginPwd())){
					mobileUser = new MobileUser();
					mobileUser.setLoginName(subMerTerminal.getLoginName());
					mobileUser.setTerminalId(subMerTerminal.getTsn());
					mobileUser.setSubMerId(subMerTerminal.getSubMerId());
					mobileUser.setLoginPwd(subMerTerminal.getLoginPwd());
					mobileUser.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					mobileUser.setStatus("2"); //初始用户
					mobileUserDao.insertMobileUser(mobileUser);
				}
				if (mobileUser != null) {
						setCheckCode("9006");
						//亿商盟 ysm过来不判断密码
						boolean checkPwd = false;
						System.out.println("PWD="+accountLogIn.getAccountPwd());
						if(accountLogIn.getAccountPwd().equals("ysm")){
							checkPwd = true;
						}else if(accountLogIn.getAccountPwd().toUpperCase().equals(mobileUser.getLoginPwd().toUpperCase())){
							checkPwd = true;
						}
						System.out.println("checkPwd"+checkPwd);
						if(!checkPwd){
							setCheckCode("9017");//密码错
						}else{
							// 组装返回内容
							accountLogIn
									.setMerchantId(mobileUser.getSubMerId());
							accountLogIn.setAccountName(mobileUser
									.getLoginName());
							accountLogIn.setUserStatus(mobileUser.getStatus());
							accountLogIn.setMerchantId(mobileUser.getSubMerId());
							SubMerInfo subMerInfo = subMerInfoDao.getSubMerInfoById(mobileUser.getSubMerId());
							if(subMerInfo!=null){
								accountLogIn.setMerchantName(subMerInfo.getSubMerName());
								if(StringUtils.isNotEmpty(subMerInfo.getScanTsn())){
								    accountLogIn.setAccountStatus("6");
								    if(subMerInfo.getStatus().equals("2")){
								        accountLogIn.setAccountStatus("7");
								    }
								}else{
								    accountLogIn.setAccountStatus(subMerInfo.getStatus());
								}
								accountLogIn.setAuthStatus(subMerInfo.getAuthStatus());
								accountLogIn.setReMack(subMerInfo.getRemark());
								accountLogIn.setSettleAccountNo(subMerInfo.getSettAccountNo());
								accountLogIn.setSettleAccount(subMerInfo.getSettAccountName());
								accountLogIn.setLegalManIdcard(subMerInfo.getLegalIdcard());
								accountLogIn.setTerminalId(mobileUser.getTerminalId());
							}
							// 修改设备最近登录时间
							MobileUser user = new MobileUser();
							user.setId(mobileUser.getId());
							user.setLastLoginTime(DateUtil
									.getDate("yyyy-MM-dd HH:mm:ss"));
							mobileUserDao.updateUserByLastLoginTime(user);
							setCheckCode("0000");
						}
				} else {
					// 查询Mobile 信息
					setCheckCode("9017");
				}
			} else {
				setCheckCode("9998");
			}
		} catch (Exception e) {
			setCheckCode("9998");
			e.printStackTrace();
		}
		accountLogIn.setRespCode(checkCode);
		accountLogIn.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
		return accountLogIn;
	}
	
	@Override
	public Object regBank(SettBank settBank) {
		settBank.copyBusBeanParent(settBank);
		setCheckCode("");
		try {
			if (settBank != null) {
				List<BankBehalfBranch> bankBehalfBranchList = bankBehalfBranchDao
						.selectBankBehalfBranchList();
				SettBankList settBankList = null;
				List<SettBankList> settBankLists = new ArrayList<SettBankList>();
				Iterator<BankBehalfBranch> bankBehalfBranchIt = bankBehalfBranchList
						.iterator();
				while (bankBehalfBranchIt.hasNext()) {
					settBankList = new SettBankList();
					BankBehalfBranch bankBehalfBranch = (BankBehalfBranch) bankBehalfBranchIt
							.next();
					settBankList.setBankNo(bankBehalfBranch.getCode());
					settBankList.setBankName(bankBehalfBranch.getBankName());
					settBankLists.add(settBankList);
				}
				settBank.setBankList(settBankLists);
				settBank.setApplication("SettBankList.Rsp");
				setCheckCode("0000");
			} else {
				setCheckCode("9014");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ("".equals(checkCode)) {
			checkCode = "3001";
		}
		settBank.setRespCode(checkCode);
		settBank.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
		return settBank;
	}

	/**
	 * 收款开通
	 */
	@Override
	public Object openReceivablesBus(OpenPurchase openPurchase) {
		openPurchase.copyBusBeanParent(openPurchase);
		setCheckCode("");
		try {
			// 获取子商户设备信息
			SubMerTerminal terminal = new SubMerTerminal();
			terminal.setSubMerId(openPurchase.getMerchantId());
			terminal.setTsn(openPurchase.getTerminalId());
			// terminal.setFactory(openPurchase.getFactoryId());
			setCheckCode("3034");
			SubMerTerminal subMerTerminal = subMerTerminalDao
					.getSubMerTerInfo(terminal);
			if (null != subMerTerminal && !"".equals(subMerTerminal)) {
				// 查询商户收款功能是否已开通
				SubMerInfo info = subMerInfoDao.getSubMerInfoById(openPurchase
						.getMerchantId());
				setCheckCode("9006");
				if (null != info) {
					setCheckCode("3035");
					if (info.getSettAgency() == null
							|| "".equals(info.getSettAgency())
							|| "null".equals(info.getSettAgency())
							|| "-1".equals(info.getSettAgency())) {
						// 收款功能开通
						SubMerInfo subMerInfo = new SubMerInfo();
						subMerInfo.setSubMerId(openPurchase.getMerchantId());
						subMerInfo
								.setSubMerName(openPurchase.getMerchantName());
						subMerInfo.setSettAccountName(openPurchase
								.getSettleAccountName());
						subMerInfo.setSettAccountNo(openPurchase
								.getSettleAccountNo());
						subMerInfo.setSettAgency(openPurchase.getBankNo());
						setCheckCode("3028");
						if (subMerInfoDao.updateSubMerInfoByOpenCur(subMerInfo)) {
							// 修改子商户交易配置中AUTH_STATUS字段
							SubMerTrans subMerTrans = new SubMerTrans();
							subMerTrans.setSubMerId(openPurchase
									.getMerchantId());
							subMerTrans.setAuthStatus("3");// 3已开通收款
							setCheckCode("3036");
							subMerTransDao.updateSubMerTransInfo(subMerTrans);

							// 修改实人认证AUTH_STATUS字段
							SubMerAuthInfo subMerAuthInfo = new SubMerAuthInfo();
							subMerAuthInfo.setSubMerId(openPurchase
									.getMerchantId());
							subMerAuthInfo.setAuthStatus("3");
							setCheckCode("3032");
							subMerAuthInfoDao
									.updateSubMerAuthInfoByStatus(subMerAuthInfo);

							openPurchase.setAccountStatus("1");
							setCheckCode("0000");
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
		}
		openPurchase.setTerminalId(openPurchase.getTerminalId());
		openPurchase.setMerchantId(openPurchase.getMerchantId());
		openPurchase.setMerchantName("");
		openPurchase.setSettleAccountName("");
		openPurchase.setSettleAccountNo("");
		openPurchase.setBankNo("");
		String accStatus = "2";
		if (!"".equals(openPurchase.getAccountStatus())
				&& null != openPurchase.getAccountStatus()) {
			accStatus = openPurchase.getAccountStatus();
		}
		openPurchase.setAccountStatus(accStatus);
		openPurchase.setTerminalInFo("");
		openPurchase.setMsgExt(openPurchase.getMsgExt());
		openPurchase.setMisc(openPurchase.getMisc());
		if ("".equals(checkCode)) {
			checkCode = "3001";
		}
		openPurchase.setRespCode(checkCode);
		openPurchase.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
		return openPurchase;
	}

	@Override
	public Object regEnquiry(Enquiry enquiry) {
		enquiry.copyBusBeanParent(enquiry);
		setCheckCode("");
		try {
			// 组查询条件
			int curPage = 1;
			int pageCount = 10;
			if (enquiry.getCurPage() != null
					&& !"".equals(enquiry.getCurPage()))
				curPage = Integer.parseInt(enquiry.getCurPage());
			if (enquiry.getPageCount() != null
					&& !"".equals(enquiry.getPageCount()))
				pageCount = Integer.parseInt(enquiry.getPageCount());
			Map map = PageUtil.getPageMap(curPage, pageCount);
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setTerminalId(enquiry.getTerminalId());
			if ("100000".equals(enquiry.getTransType()))
				orderInfo.setTransType("01");
			else if ("200000".equals(enquiry.getTransType()))
				orderInfo.setTransType("04");
			else if ("100002".equals(enquiry.getTransType()))
				orderInfo.setTransType("07");
			else if ("100003".equals(enquiry.getTransType()))
				orderInfo.setTransType("06");
			else if ("100004".equals(enquiry.getTransType()))
				orderInfo.setTransType("09");
			// 查询
			int count = 0;
			SubMerTerminal merTerminal = new SubMerTerminal();
			merTerminal.setTsn(enquiry.getTerminalId());
			// merTerminal.setFactory(enquiry.getFactoryId());
			SubMerInfo subMerInfo = subMerInfoDao
					.getSubMerInfoByTerminalId(merTerminal);
			if (subMerInfo != null && subMerInfo.getSubMerId() != null) {

				if (null == enquiry.getMerchantId()
						|| "".equals(enquiry.getMerchantId())) {
					orderInfo.setSubMerId(subMerInfo.getSubMerId());
				} else {
					orderInfo.setSubMerId(enquiry.getMerchantId());
				}
//				map.put("tsn", enquiry.getTerminalId());
				// map.put("factoryId", enquiry.getFactoryId());
				map.put("orderInfo", orderInfo);
				map.put("beginTime", enquiry.getBeginTime());
				map.put("endTime", enquiry.getEndTime());

				count = orderInfoDao.selectEnquiryListCount(map);
				List<OrderInfo> orderInfoList = orderInfoDao
						.selectEnquiryList(map);
				// 拼返回内容
				List<EnquiryList> enquiryLists = new ArrayList<EnquiryList>();
				Iterator<OrderInfo> orderInfoIt = orderInfoList.iterator();
				while (orderInfoIt.hasNext()) {
					OrderInfo orderInfo1 = (OrderInfo) orderInfoIt.next();
					EnquiryList enquiryList = new EnquiryList();
					if ("01".equals(orderInfo1.getTransType()))
						enquiryList.setTransType("100000");
					else if ("04".equals(orderInfo1.getTransType()))
						enquiryList.setTransType("200000");
					else if ("07".equals(orderInfo1.getTransType()))
						enquiryList.setTransType("100002");
					else if ("06".equals(orderInfo1.getTransType()))
						enquiryList.setTransType("100003");
					else if ("09".equals(orderInfo1.getTransType()))
						enquiryList.setTransType("100004");
					enquiryList.setTransId(orderInfo1.getMerOrderId());
					enquiryList.setMerchantOrderId(orderInfo1.getMerOrderId());
					enquiryList.setTransAmt(orderInfo1.getMerAmt());
					enquiryList.setTransTime(orderInfo1.getTransTime());
					enquiryList.setCurrency(orderInfo1.getCurrency());
					enquiryList.setOrderRateType(orderInfo1.getOrderRateType());
					enquiryLists.add(enquiryList);
				}
				enquiry.setEnquiryList(enquiryLists);
				enquiry.setApplication("EnquiryList.Rsp");
				enquiry.setVersion("1.0.0");
				enquiry.setMerchantId(subMerInfo.getSubMerId());
				enquiry.setMerchantName(subMerInfo.getSubMerName());
				setCheckCode("0000");
			} else {
				setCheckCode("2002");
			}
			enquiry.setTotalCount(count + "");
			enquiry.setCurPage(curPage + "");
			enquiry.setPageCount(pageCount + "");
		} catch (Exception e) {
			setCheckCode("2002");
			e.printStackTrace();
		} finally {
			enquiry.setRespCode(checkCode);
			enquiry.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
		}
		return enquiry;
	}

	
	@Override
	public Object regNewEnquiry(Enquiry enquiry) {
		enquiry.copyBusBeanParent(enquiry);
		setCheckCode("");
		try {
			// 组查询条件
			int curPage = 1;
			int pageCount = 10;
			if (enquiry.getCurPage() != null
					&& !"".equals(enquiry.getCurPage()))
				curPage = Integer.parseInt(enquiry.getCurPage());
			if (enquiry.getPageCount() != null
					&& !"".equals(enquiry.getPageCount()))
				pageCount = Integer.parseInt(enquiry.getPageCount());
			Map map = PageUtil.getPageMap(curPage, pageCount);
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setTerminalId(enquiry.getTerminalId());
			if ("100000".equals(enquiry.getTransType()))
				orderInfo.setTransType("01");
			else if ("200000".equals(enquiry.getTransType()))
				orderInfo.setTransType("04");
			else if ("100002".equals(enquiry.getTransType()))
				orderInfo.setTransType("07");
			else if ("100003".equals(enquiry.getTransType()))
				orderInfo.setTransType("06");
			else if ("100004".equals(enquiry.getTransType()))
				orderInfo.setTransType("09");
			// 查询
			int count = 0;
			SubMerInfo subMerInfo = subMerInfoDao.getSubMerInfoById(enquiry.getMerchantId());
			if (subMerInfo != null && subMerInfo.getSubMerId() != null) {

				if (null == enquiry.getMerchantId()
						|| "".equals(enquiry.getMerchantId())) {
					orderInfo.setSubMerId(subMerInfo.getSubMerId());
				} else {
					orderInfo.setSubMerId(enquiry.getMerchantId());
				}
				// map.put("factoryId", enquiry.getFactoryId());
				map.put("orderInfo", orderInfo);
				map.put("beginTime", enquiry.getBeginTime());
				map.put("endTime", enquiry.getEndTime());

				count = orderInfoDao.selectNewEnquiryListCount(map);
				List<OrderInfo> orderInfoList = orderInfoDao
						.selectNewEnquiryList(map);
				// 拼返回内容
				List<EnquiryList> enquiryLists = new ArrayList<EnquiryList>();
				Iterator<OrderInfo> orderInfoIt = orderInfoList.iterator();
				while (orderInfoIt.hasNext()) {
					OrderInfo orderInfo1 = (OrderInfo) orderInfoIt.next();
					EnquiryList enquiryList = new EnquiryList();
					if ("01".equals(orderInfo1.getTransType()))
						enquiryList.setTransType("100000");
					else if ("04".equals(orderInfo1.getTransType()))
						enquiryList.setTransType("200000");
					else if ("07".equals(orderInfo1.getTransType()))
						enquiryList.setTransType("100002");
					else if ("06".equals(orderInfo1.getTransType()))
						enquiryList.setTransType("100003");
					else if ("09".equals(orderInfo1.getTransType()))
						enquiryList.setTransType("100004");
					enquiryList.setTransId(orderInfo1.getMerOrderId());
					enquiryList.setMerchantOrderId(orderInfo1.getMerOrderId());
					enquiryList.setTransAmt(orderInfo1.getMerAmt());
					enquiryList.setTransTime(orderInfo1.getTransTime());
					enquiryList.setCurrency(orderInfo1.getCurrency());
					enquiryList.setOrderRateType(orderInfo1.getOrderRateType());
					enquiryLists.add(enquiryList);
				}
				enquiry.setEnquiryList(enquiryLists);
				enquiry.setApplication("NewEnquiryList.Rsp");
				enquiry.setVersion("1.0.0");
				enquiry.setMerchantId(subMerInfo.getSubMerId());
				enquiry.setMerchantName(subMerInfo.getSubMerName());
				setCheckCode("0000");
			} else {
				setCheckCode("2002");
			}
			enquiry.setTotalCount(count + "");
			enquiry.setCurPage(curPage + "");
			enquiry.setPageCount(pageCount + "");
		} catch (Exception e) {
			setCheckCode("2002");
			e.printStackTrace();
		} finally {
			enquiry.setRespCode(checkCode);
			enquiry.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
		}
		return enquiry;
	}
	
	
	@Override
	public Object terminalValidate(TerminalValidate terminalValidate) {
		terminalValidate.copyBusBeanParent(terminalValidate);
		setCheckCode("");
		String merSysId = ""; // 机构商号
		String subMerId = ""; // 子商户号
		String terminalStatus = ""; // 终端状态
		String version = ""; // 版本号
		String versionDesc = ""; // 版本信息描述
		String updateType = ""; // 更新状态
		String updatePath = ""; // 更新地址
		String t0Status = "";// T+0状态 0 未开通 1 已开通

		String lowsetMentionAmt = "";// 最低提现金额
		String t0MerRate = "";// T0费率
		String d1MerRate = "";// D1费率

		String agreementStatus = "";// 电子协议阅读状态 0：未阅读 1：已阅读

		try {
			SubMerTerminal subMerTerminal = new SubMerTerminal();
			subMerTerminal.setTsn(terminalValidate.getTerminalId());
			// 根据终端ID获取终端信息
			subMerTerminal = subMerTerminalDao
					.selectSubMerTerminalByTerminalId(subMerTerminal);
			if (subMerTerminal == null) {
				setCheckCode("9001");
			} else {
				subMerId = subMerTerminal.getSubMerId();
				if ("-1".equals(subMerId)) {
					subMerId = "";
				}

				terminalStatus = subMerTerminal.getStatus();// 终端状态
				merSysId = subMerTerminal.getMerSysId();// 终端机构号

				// 厂商风控
				if (null != subMerTerminal.getFactory()
						&& !"".equals(subMerTerminal.getFactory())) {
					FactoryRisk factoryRisk = new FactoryRisk();
					factoryRisk.setFactoryNo(subMerTerminal.getFactory());
					factoryRisk = factoryRiskDao
							.selectFactoryRiskById(factoryRisk);

					if (null != factoryRisk
							&& null != factoryRisk.getLowsetMentionAmt()
							&& !"".equals(factoryRisk.getLowsetMentionAmt())) {
						lowsetMentionAmt = factoryRisk.getLowsetMentionAmt();// 起结金额
					}

				}

				// 机构交易配置
				if (null != merSysId && !"".equals(merSysId)) {
					MerTrans merTrans = merTransDao
							.selectMerTransByMerSysId(merSysId);
					if (null != merTrans) {
						t0MerRate = merTrans.getT0MerRate();
						d1MerRate = merTrans.getD1MerRate();
					}
				}

				if (null != subMerId && !"".equals(subMerId)) {
					// 根据子商户号获取子商户交易配置信息
					SubMerTrans subMerTrans = new SubMerTrans();
					subMerTrans.setSubMerId(subMerId);
					subMerTrans = subMerTransDao
							.selectSubMerTransById(subMerTrans);
					if (null != subMerTrans) {
						t0Status = subMerTrans.getT0Status();
					}

					// 子商户表
					SubMerInfo subMerInfo = subMerInfoDao
							.getSubMerInfoById(subMerId);
					if (null != subMerInfo) {
						if (null == subMerInfo.getAgreementStatus()
								|| "".equals(subMerInfo.getAgreementStatus())) {
							agreementStatus = "0";
						} else {
							agreementStatus = subMerInfo.getAgreementStatus();
						}
					}

				}

				// 获取机构终端信息
//				MerTerminalInfo merTerminalInfo = new MerTerminalInfo();
//				merTerminalInfo.setMerSysId(subMerTerminal.getMerSysId()); //104
//				merTerminalInfo.setTerminalSysterm(terminalValidate
//						.getTerminalSysterm());  //01
//				merTerminalInfo.setVersion(terminalValidate.getVersionCode());
//				merTerminalInfo.setStatus("1");
//				MerTerminalInfo info = merTerminalInfoDao
//						.selectMerTerminalInfo(merTerminalInfo);// 当前设备版本
//				setCheckCode("3051");
//				if (null != info) {
//					if ("0".equals(info.getStatus())) {// 设备版本未启用
//						setCheckCode("3056");
//					} else {
//						// 获取机构终端版本管理表id最大并且为启用的版本号
//						MerTerminalInfo terminalInfo = new MerTerminalInfo();
//						// terminalInfo.setMerSysId(subMerTerminal.getMerSysId());
//						terminalInfo.setTerminalSysterm(terminalValidate
//								.getTerminalSysterm());
//
//						MerTerminalInfo infos = merTerminalInfoDao
//								.selectMaxIdMerTerminalInfo(terminalInfo);
//						// 机构设置的最新版本与当前设备版本不一致
//						if (!"".equals(infos.getVersion())
//								&& !terminalValidate.getVersionCode().equals(
//										infos.getVersion())) {
//							version = infos.getVersion();
//							versionDesc = infos.getVersionDesc();
//							updateType = infos.getUpdateType();
//							if ("01".equals(terminalValidate
//									.getTerminalSysterm())) {
//								updatePath = "," + infos.getUpdatePath();
//							} else if ("02".equals(terminalValidate
//									.getTerminalSysterm())) {
//								updatePath = infos.getUpdatePath() + "" + ",";
//							}
//							updatePath = infos.getUpdatePath();
//							// 修改子商户设备的版本信息
//							SubMerTerminal terminal = new SubMerTerminal();
//							terminal.setTsn(terminalValidate.getTerminalId());
//							terminal.setVersion(infos.getVersion());
//							setCheckCode("3021");
//							if (subMerTerminalDao
//									.updateSubMerTerminalVersion(terminal)) {
//								setCheckCode("0000");
//							}
//						} else {
//							// 最新信息
//							version = info.getVersion();
//							versionDesc = info.getVersionDesc();
//							updateType = "00";
//							if ("01".equals(terminalValidate
//									.getTerminalSysterm())) {
//								updatePath = "," + info.getUpdatePath();
//							} else if ("02".equals(terminalValidate
//									.getTerminalSysterm())) {
//								updatePath = info.getUpdatePath() + "" + ",";
//							}
//						}
//
//					}
//				}
							setCheckCode("0000");
			}
		} catch (Exception e) {
			setCheckCode("9020");
			e.printStackTrace();
		}
		terminalValidate.setTerminalId(terminalValidate.getTerminalId());
		terminalValidate.setVersionCode(version);
		terminalValidate.setTerminalStatus(terminalStatus);
		terminalValidate.setVersionDesc(versionDesc);
		terminalValidate.setUpdateType(updateType);
		terminalValidate.setMerchantId(subMerId);
		terminalValidate.setUpdatePath(updatePath);
		terminalValidate.setMerSysId(merSysId);
		terminalValidate.setT0Status(t0Status);

		terminalValidate.setD1MerRate(d1MerRate);
		terminalValidate.setT0MerRate(t0MerRate);
		terminalValidate.setLowsetMentionAmt(lowsetMentionAmt);
		terminalValidate.setAgreementStatus(agreementStatus);

		terminalValidate.setRespCode(checkCode);
		terminalValidate.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
		return terminalValidate;
	}

	// 修改终端状态
	@Override
	public Object updateStatus(TerminalEnable terminalId) {
		terminalId.copyBusBeanParent(terminalId);
		SubMerTerminal subMerTerminal = new SubMerTerminal();
		subMerTerminal.setTsn(terminalId.getTerminalId());
		// subMerTerminal.setFactory(terminalId.getFactoryId());
		setCheckCode("9001");
		subMerTerminal = subMerTerminalDao
				.selectSubMerTerminalByTerminalId(subMerTerminal);
		if (subMerTerminal != null) {
			setCheckCode("3025");
			if (subMerTerminal.getStatus().equals("0")) {
				int update = 0;
				setCheckCode("3038");
				update = subMerTerminalDao.updateStatus(terminalId);
				if (update > 0) {
					System.out.println("更新终端成功！");
					setCheckCode("0000");
				}
			}

		}
		terminalId.setRespCode(checkCode);
		terminalId.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
		return terminalId;
	}

	/**
	 * 账户信息查询
	 */
	@Override
	public Object accountEnquiry(AccountEnquiry accountEnquiry) {
		accountEnquiry.copyBusBeanParent(accountEnquiry);
		SubMerTerminal subMerTerminal = new SubMerTerminal();
		subMerTerminal.setTsn(accountEnquiry.getTerminalId());
		subMerTerminal.setSubMerId(accountEnquiry.getMerchantId());
		SubMerInfo subMerInfo = subMerInfoDao.accountEnquiry(subMerTerminal);
		setCheckCode("3001");
		String merchantName = "";
		String legalManName = "";
		String legalManIdcard = "";
		String mobileNum = "";
		String settleAccount = "";
		String settleAccountNo = "";
		String settleAgency = "";
		String merStatus = "";
		String settleAccountType = "";
		String grade = "";
		String gradeStatus = "";

		String subAuthStatus = "";
		if (null != subMerInfo) {
			String autoApprove = "";
			MerTrans merTrans = merTransDao.getMerTransInfo(subMerInfo
					.getMerSysId());
			if (null != merTrans) {
				autoApprove = merTrans.getAutoApprove();// 机构是否开通自动审核 1开通
			}
			String settAccType = subMerInfo.getSettAccType();// 子商户结算账户类型 是2对私
			// 还是1对公
			subAuthStatus = subMerInfo.getAuthStatus();// 子商户认证类型

			String realPeopleStatus = "";
			String serialNo = "";
			String opt = "-1";
			// 实人认证
			if ("1".equals(autoApprove) && "2".equals(settAccType)
					&& "1".equals(subAuthStatus)) {// 实名成功
				try {
					String strResult = realPeopleAuthentication(subMerInfo);
					String str[] = strResult.split("\\|");
					realPeopleStatus = str[0];
					serialNo = str[1];
					opt = "00";
				} catch (Exception e) {
					opt = "-1";
					e.printStackTrace();
				}
			} else if ("1".equals(autoApprove) && "2".equals(settAccType)
					&& "4".equals(subAuthStatus)) {// 实人失败
				try {
					String strResult = realPeopleAuthentication(subMerInfo);
					String str[] = strResult.split("\\|");
					realPeopleStatus = str[0];
					serialNo = str[1];
					opt = "00";
				} catch (Exception e) {
					opt = "-1";
					e.printStackTrace();
				}
			}
			// 当操作了实人认证 需要修改相关数据库数据
			if ("00".equals(opt)) {
				// 修改子商户交易配置-认证状态
				SubMerTrans subMerTrans = new SubMerTrans();
				subMerTrans.setSubMerId(subMerInfo.getSubMerId());
				subMerTrans.setAuthStatus(realPeopleStatus);
				subMerTrans
						.setAuthTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
				if (subMerTransDao.updateSubMerTransInfo(subMerTrans) != 1) {
					setCheckCode("3047");
				}

				// 添加实人数据到数据库
				SubMerAuthInfo auth = new SubMerAuthInfo();
				auth.setCreateTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
				auth.setSubMerId(subMerInfo.getSubMerId());
				auth.setRemitType("2");
				auth.setRealName(subMerInfo.getLegalPerson());
				auth.setIdNum(subMerInfo.getLegalIdcard());
				if ("-1".equals(realPeopleStatus)) {
					realPeopleStatus = "4";
				}
				auth.setAuthStatus(realPeopleStatus);
				auth.setSerialNo(serialNo);
				auth.setTerminalId(accountEnquiry.getTerminalId());
				// 添加认证信息到数据库
				if (subMerAuthInfoDao.addSubMerAuthInfo(auth) != 1) {
					setCheckCode("3048");
				}
				subAuthStatus = realPeopleStatus;
			}
			merchantName = subMerInfo.getSubMerName();
			legalManName = subMerInfo.getLegalPerson();
			legalManIdcard = subMerInfo.getLegalIdcard();
			mobileNum = subMerInfo.getContactorPhone();
			settleAccount = subMerInfo.getSettAccountName();
			settleAccountNo = subMerInfo.getSettAccountNo();
			settleAgency = subMerInfo.getSettAgency();
			merStatus = subMerInfo.getStatus();
			settleAccountType = subMerInfo.getSettAccType();
			grade = subMerInfo.getGrade();
			gradeStatus = subMerInfo.getGradeStatus();
			setCheckCode("0000");
		}
		accountEnquiry.setTerminalId(accountEnquiry.getTerminalId());
		accountEnquiry.setMerchantId(accountEnquiry.getMerchantId());
		accountEnquiry.setMerchantName(merchantName);
		accountEnquiry.setLegalManName(legalManName);
		accountEnquiry.setLegalManIdcard(legalManIdcard);
		accountEnquiry.setMobileNum(mobileNum);
		accountEnquiry
				.setPersonalMerRegNo(accountEnquiry.getPersonalMerRegNo());
		accountEnquiry.setTaxNo(accountEnquiry.getTaxNo());
		accountEnquiry.setOccNo(accountEnquiry.getOccNo());
		if ("-1".equals(settleAccount)) {
			settleAccount = "";
		}
		accountEnquiry.setSettleAccount(settleAccount);
		if ("-1".equals(settleAccountNo)) {
			settleAccountNo = "";
		}
		accountEnquiry.setSettleAccountNo(settleAccountNo);
		accountEnquiry.setSettleAgency(settleAgency);

		if ("2".equals(merStatus)) {
			merStatus = "1";
		} else if ("5".equals(merStatus)) {
			merStatus = "2";
		} else {
			merStatus = "0";
		}
		accountEnquiry.setMerStatus(merStatus);

		if (null != settleAccountType && !"".equals(settleAccountType)
				&& "1".equals(settleAccountType)) {
			accountEnquiry.setAuthStatus("null");
		} else {
			accountEnquiry.setAuthStatus(subAuthStatus);
		}
		accountEnquiry.setSettleAccountType(settleAccountType);
		accountEnquiry.setRespCode(checkCode);
		accountEnquiry.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
		accountEnquiry.setGrade(grade); //认证等级
		accountEnquiry.setGradeStatus(gradeStatus); //认证状态
		return accountEnquiry;
	}

	// 找回密码
	@Override
	public Object updatePwd(SelectPassWord selectPassWord) {
		selectPassWord.copyBusBeanParent(selectPassWord);
		SubMerTerminal subMerTerminal = new SubMerTerminal();
		subMerTerminal.setTsn(selectPassWord.getTerminalId());
		// subMerTerminal.setFactory(selectPassWord.getFactoryId());
		subMerTerminal.setSubMerId(selectPassWord.getMerchantId());
		subMerTerminal.setLoginName(selectPassWord.getPhoneNum());
		setCheckCode("9006");
		// 修改终端前查询终端是否存在
		subMerTerminal = subMerTerminalDao
				.selectSubMerTerminalByUpdatePwd(subMerTerminal);
		if (subMerTerminal != null) {
			subMerTerminal.setLoginPwd(selectPassWord.getPwd());
			setCheckCode("3042");
			int update = 0;
			try {
				update = subMerTerminalDao.updatePwd(subMerTerminal);
			} catch (Exception e) {
				e.printStackTrace();
				setCheckCode("9999");// 更新失败，返回错误报文
			}
			if (update > 0) {
				System.out.println("更新密码！");
				setCheckCode("0000");
			}
		}
		selectPassWord.setRespCode(checkCode);
		selectPassWord.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
		return selectPassWord;
	}

	/**
	 * 终端注销
	 */
	@Override
	public String terminalCancel(SubMerTerminal terminal) {
		String returns = "";

		TerminalCancel terminalCancel = new TerminalCancel();
		terminalCancel.setApplication("TerminalCancel.Req");
		terminalCancel.setVersion("1.0.0");
		terminalCancel.setSendTime(new SimpleDateFormat("yyyyMMddmmhhss")
				.format(new Date()));
		terminalCancel.setTerminalId(terminal.getTsn());
		terminalCancel.setFactoryId(terminal.getFactory());

		String data = "";// 3段内容
		try {
			data = xmlUtilnew.ObjToXml(terminalCancel);
			System.out.println(data);
			RefundUtil refundUtil = new RefundUtil();
			data = refundUtil.encrypt(data, terminal.getTsn());
			System.out.println("3段内容>>>>>>>:" + data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RemoteAccessor remoteAccessor = new RemoteAccessor();
		String decryptXml = "";
		try {
			String url = rb.getString("posp-url");
			System.out.println("url:" + url);
			decryptXml = remoteAccessor.getResponseByStream(url, "utf-8", data);
			System.out.println("返回XML>>>>" + decryptXml);
			String[] ss = decryptXml.split("\\|");
			if (ss[0].equals("0")) {
				returns = "0";
			}
			if (ss[0].equals("1")) {
				String str = new RefundUtil().decrypt(ss[1]);
				String[] result = str.split("\\|");
				if ("0000".equals(result[1])) {// 获取正确返回的code
					returns = "1";
				} else {
					returns = "0";
				}
			}
		} catch (Exception e) {
			returns = "0";
			e.printStackTrace();
		}
		return returns;
	}

	/**
	 * 上账
	 * 
	 * @throws Exception
	 * @throws UnsupportedEncodingException
	 */
	@Override
	public String upAccount(AccountManage accountManages) throws Exception {
		Random random = new Random();
		String res = "";
		for (int i = 0; i < 7; i++) {
			res += random.nextInt(10);
		}
		System.out.print(res);
		// 组装数据结构
		String[] reqMsg = BankUtils.toBusinessBalanceReCharge(
				accountManages.getMerSysId(), RSACoder.KEY_FTB_PUBLICKEY,
				accountManages.getMerAmt(), accountManages.getFeeAmt(), res
						+ accountManages.getId());
		System.out.println("reqMsg[1]:" + reqMsg[1]);
		String respMsg = "";
		String status = "0";
		String result = "";
		try {
			respMsg = new RemoteAccessor().getResponseByPost(
					PropertiesUtils.getPropertiesValueInPath("busUrl"),
					"UTF-8", new String[] { "msg", reqMsg[1] });
		} catch (Exception e) {
			status = "3";
			return "上账失败，系统错误";
		}

		if (respMsg == null || "".equals(respMsg)) {
			status = "2";
			return "上账失败，系统错误";
		}
		String[] msgs = respMsg.split("\\|");

		if ("0".equals(msgs[0])) {
			status = "2";
			// 错误
			// 错误响应格式：0|错误码|BASE64(错误描述)
			result = "上账失败,"
					+ new String(Base64.decodeBase64(msgs[2]), "UTF-8");
		} else {
			// 正常响应格式：1|BASE64(3DES(报文))|BASE64(MD5(报文))
			String xml = new String(RSACoder.decryptDesc(
					Base64.decodeBase64(msgs[1]), reqMsg[0].getBytes()),
					"UTF-8");
			System.out.println("上账返回的xml:" + xml);
			TransInfoReq transInfo = (TransInfoReq) XmlUtil.XmlToObj(xml,
					TransInfoReq.class);
			if ("0000".equals(transInfo.getRespCode())) {
				status = "1";
				result = "上账成功!";
			} else {
				status = "2";
				result = "上账失败!";
			}
		}
		if ("1".equals(status) && "1".equals(accountManages.getAccountType())) {// 上账成功、上账类型为1
			// 清算款上账
			// 修改商户日清分统计表的 ---清分状态为1待清分
			MerSettleStatictis merSettleStatictis = new MerSettleStatictis();
			merSettleStatictis.setClearStatus("1");
			merSettleStatictis.setMid(accountManages.getMerSysId());
			merSettleStatictis.setMerType("0");
			merSettleStatictis.setSettleDate(accountManages.getSettleDate());
			merSettleStatictisDao.updateClearStatus(merSettleStatictis);

		}
		AccountManage manage = new AccountManage();
		manage.setId(accountManages.getId());
		manage.setMerSysId(accountManages.getMerSysId());
		manage.setSettleDate(accountManages.getSettleDate());
		manage.setStatus(status);
		// 修改预存款状态
		accountManageDao.updateAccountManageByStatus(manage);
		return result;
	}

	/**
	 * 实名认证
	 */
	@Override
	public String authentication(AccountRegist accountRegist) throws Exception {
		String authStatus = "-1";// 认证状态 4：认证失败
		String serialNo = "-1";// 流水号
		try {
			String orderId = ValueTool.createByTime();
			String orderTime = DateUtil.getDate("yyyyMMddHHmmss");
			String userName = accountRegist.getLegalManName();
			String idCard = accountRegist.getLegalManIdcard();

			String merchantId = ValueTool.AUTH_MER_ID;
			String first = merchantId;
			String Second = "101|" + orderId + "|" + orderTime + "|" + userName
					+ "|" + idCard;
			String Third = merchantId + "101" + orderId + "" + orderTime
					+ idCard + "" + ValueTool.AUTH_MER_SIGN_KEY + "";

			AuthenticationUtil authenticationUtil = new AuthenticationUtil();
			String data = authenticationUtil.encrypt(first, Second, Third);// 加密并组合
			RemoteAccessor remoteAccessor = new RemoteAccessor();

			String path = rb.getString("shimingUrl");

			// 发送请求
			String decryptXml = remoteAccessor.getResponseByStream(path,
					"utf-8", data);
			String[] ss = decryptXml.split("\\|");
			if (ss[0].equals("0")) {// 解析成功
				String respXml = new AuthenticationUtil().decrypt(ss[1]);// 得到正确的报文内容
				System.out.println(respXml);
				String[] result = respXml.split("\\|");
				String respCode = result[5];// 返回码
				serialNo = result[4];// 流水号
				if (!"".equals(respCode) && "1".equals(respCode)) {// 认证成功
					authStatus = "1";
				} else {
					authStatus = "4";
				}
			}
			if (ss[0].equals("1")) {// 解析失败
				authStatus = "4";
			}
		} catch (Exception e) {
			return authStatus + "|" + serialNo;
		}
		return authStatus + "|" + serialNo;
	}

	/**
	 * 商户提现申请记录
	 */
	@Override
	public Object drawMoneyRecord(DrawMoneyRecord drawMoneyRecord)
			throws Exception {
		drawMoneyRecord.copyBusBeanParent(drawMoneyRecord);
		setCheckCode("");
		try {
			// 组查询条件
			int curPage = 1;
			int pageCount = 10;
			if (drawMoneyRecord.getCurPage() != null
					&& !"".equals(drawMoneyRecord.getCurPage())) {
				curPage = Integer.parseInt(drawMoneyRecord.getCurPage());
			}
			if (drawMoneyRecord.getPageCount() != null
					&& !"".equals(drawMoneyRecord.getPageCount())) {
				pageCount = Integer.parseInt(drawMoneyRecord.getPageCount());
			}
			Map map = PageUtil.getPageMap(curPage, pageCount);

			// 查询
			Integer count = null;
			map.put("subMerId", drawMoneyRecord.getMerchantId());
			map.put("beginTime", drawMoneyRecord.getBeginTime());
			map.put("endTime", drawMoneyRecord.getEndTime());
			count = subMerCashoutDao.getSubMerCashoutBySubIdCount(map); // 总条数
			if (null == count) {
				count = 0;
			}
			if (count > 0) {
				List<SubMerCashout> list = subMerCashoutDao
						.getSubMerCashoutBySubId(map); // 总数据
				// 拼返回内容
				List<DrawMoneyRecordList> drawMoneyRecordLists = new ArrayList<DrawMoneyRecordList>();

				Iterator<SubMerCashout> subMerCashoutList = list.iterator();
				while (subMerCashoutList.hasNext()) {
					SubMerCashout info = (SubMerCashout) subMerCashoutList
							.next();
					DrawMoneyRecordList drawMoneyRecordList = new DrawMoneyRecordList();
					drawMoneyRecordList.setOrderAmt(info.getOrderAmt());
					drawMoneyRecordList.setCurrency("156");
					drawMoneyRecordList.setMerchantOrderId(info.getTransId());
					drawMoneyRecordList.setMerchantOrderTime(info
							.getCreateTime());
					drawMoneyRecordList.setOrderStatus(info.getOrderStatus());
					drawMoneyRecordLists.add(drawMoneyRecordList);
				}

				SubMerInfo subMerInfo = subMerInfoDao
						.getSubMerInfoById(drawMoneyRecord.getMerchantId());
				String subMerName = "";
				if (null != subMerInfo) {
					subMerName = subMerInfo.getSubMerName();
				}
				drawMoneyRecord.setDrawMoneyRecordLists(drawMoneyRecordLists);
				drawMoneyRecord.setMerchantId(drawMoneyRecord.getMerchantId());
				drawMoneyRecord.setMerchantName(subMerName);
				drawMoneyRecord.setTerminalId(drawMoneyRecord.getTerminalId());
				drawMoneyRecord.setBeginTime(drawMoneyRecord.getBeginTime());
				drawMoneyRecord.setEndTime(drawMoneyRecord.getEndTime());
				setCheckCode("0000");
				drawMoneyRecord.setTotalCount(count + "");
				drawMoneyRecord.setCurPage(curPage + "");
				drawMoneyRecord.setPageCount(pageCount + "");
			} else {
				setCheckCode("2002");
			}
		} catch (Exception e) {
			setCheckCode("2002");
			e.printStackTrace();
		} finally {
			drawMoneyRecord.setRespCode(checkCode);
			drawMoneyRecord.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
		}
		return drawMoneyRecord;
	}

	/**
	 * 查询余额
	 */
	@Override
	public Object queryBalance(QueryBalance queryBalance) {
		queryBalance.copyBusBeanParent(queryBalance);
		setCheckCode("");
		SubMerInfo subMerInfo = subMerInfoDao.getSubMerInfoById(queryBalance
				.getMerchantId());
		queryBalance.setMerchantId(queryBalance.getMerchantId());
		queryBalance.setTerminalId(queryBalance.getTerminalId());
		setCheckCode("3054");
		if (null != subMerInfo) {
			queryBalance.setMerchantName(subMerInfo.getSubMerName());
			queryBalance.setAccountBalance(subMerInfo.getgAccStatus());
			setCheckCode("0000");
		}
		queryBalance.setBalanceCurrency("156");
		queryBalance.setRespCode(checkCode);
		queryBalance.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
		return queryBalance;
	}

	/**
	 * 短信下发
	 */
	@Override
	public Object phoneNumber(PhoneNumber phoneNumber) {
		phoneNumber.copyBusBeanParent(phoneNumber);
		setCheckCode("3061");

		String phoneNo = phoneNumber.getPhoneNum();
		String code = getSixRandom(100000, 999999).toString();

		String result = HFSendData.sendData(phoneNo, code);
		if (!"error".equals(result)) {
			if ("00".equals(result.split("[|]")[0])) {
				setCheckCode("0000");
				PhoneVerifyCode phoneVerifyCode = new PhoneVerifyCode();
				phoneVerifyCode.setTerminalId(phoneNumber.getTerminalId());
				phoneVerifyCode.setPhone(phoneNumber.getPhoneNum());
				phoneVerifyCode.setCode(Md5Util.getMD5(code));
				phoneVerifyCode.setCreateTime(DateUtil
						.getDate("yyyyMMddHHmmss"));
				phoneVerifyCodeDao.insertPhoneVerifyCode(phoneVerifyCode);
			}
		} else {
			setCheckCode("9999");
		}
		phoneNumber.setRespCode(checkCode);
		phoneNumber.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
		phoneNumber.setMsgExt1(Md5Util.getMD5(code));

		/*
		 * try { URL url = new
		 * URL("http://www.chinapnr.com/hfsubms/clientAction!sendInfos.ac");
		 * String phoneNo = phoneNumber.getPhoneNum(); String code =
		 * getSixRandom(100000, 999999).toString(); URLConnection connection =
		 * url.openConnection(); connection.setDoOutput(true);
		 * OutputStreamWriter out = new
		 * OutputStreamWriter(connection.getOutputStream(), "8859_1");
		 * out.write("phoneNo=" + phoneNo + "&checkCode=" + code + ""); //
		 * 向页面传递数据。post的关键所在！ out.flush(); out.close(); //
		 * 一旦发送成功，用以下方法就可以得到服务器的回应： String sCurrentLine; String sTotalString;
		 * sCurrentLine = ""; sTotalString = ""; InputStream l_urlStream;
		 * l_urlStream = connection.getInputStream(); // 传说中的三层包装阿！
		 * BufferedReader l_reader = new BufferedReader(new
		 * InputStreamReader(l_urlStream)); while ((sCurrentLine =
		 * l_reader.readLine()) != null) { sTotalString += sCurrentLine +
		 * "\r\n";
		 * 
		 * } System.out.println("==================" + sTotalString);
		 * phoneNumber.setRespCode("00".equals(sTotalString.split("[|]")[0]) ?
		 * "0000" : sTotalString .split("[|]")[0]);
		 * phoneNumber.setRespDesc("00".equals(sTotalString.split("[|]")[0]) ?
		 * "成功" : "失败"); phoneNumber.setMsgExt1(Md5Util.getMD5(code));
		 * 
		 * if ("00".equals(sTotalString.split("[|]")[0])) { PhoneVerifyCode
		 * phoneVerifyCode = new PhoneVerifyCode();
		 * phoneVerifyCode.setTerminalId(phoneNumber.getTerminalId());
		 * phoneVerifyCode.setPhone(phoneNumber.getPhoneNum());
		 * phoneVerifyCode.setCode(Md5Util.getMD5(code));
		 * phoneVerifyCode.setCreateTime(DateUtil.getDate("yyyyMMddHHmmss"));
		 * phoneVerifyCodeDao.insertPhoneVerifyCode(phoneVerifyCode); } } catch
		 * (Exception e) { setCheckCode("9999"); e.printStackTrace(); }
		 */

		return phoneNumber;
	}
	
	/**
	 * 无卡支付 商户认证
	 */
	public RegisterMerInfo registerMerSY(RegisterMerInfo registerMerInfo) throws Exception {
		registerMerInfo.copyBusBeanParent(registerMerInfo);
		setCheckCode("");
		DefaultHttpClient httpClient = new DefaultHttpClient();  
		HttpPost request = new HttpPost(rb.getString("SYurl")+"IN0001.json");  
		try {
			if (registerMerInfo != null) {
				Map<String,String> map = new LinkedHashMap<String, String>();  
				map.put("custMobile", registerMerInfo.getCustMobile());
				map.put("cardHandheld", registerMerInfo.getCardHandheld());
				map.put("cardFront", registerMerInfo.getCardFront());
				map.put("cardBack", registerMerInfo.getCardBack());
				map.put("custName", registerMerInfo.getCustName());
				map.put("certificateType", registerMerInfo.getCertificateType());
				map.put("certificateNo ", registerMerInfo.getCertificateNo ());
				map.put("userEmail", registerMerInfo.getUserEmail());
				map.put("provinceId", registerMerInfo.getProvinceId());
				map.put("userEmail", registerMerInfo.getUserEmail());
				map.put("cityId", registerMerInfo.getCityId());
				map.put("regionId", registerMerInfo.getRegionId());
				map.put("identifyBackUrl", "127.0.0.1");
				map.put("txnDate", registerMerInfo.getTxnDate());
				map.put("txnTime", registerMerInfo.getTxnTime());
				map.put("agentId", registerMerInfo.getAgentId());
				StringBuffer sb = new StringBuffer();
				//制作签名
				String checkSignMsg=Md5Util.generateMD5String(TmallYfDataPackage.convertObjectToJson(map)+rb.getString("SYsign"));
		        String jsonString = "{\"REQ_HEAD\":{\"SIGN\":\""+checkSignMsg+"\"},\"REQ_BODY\":"+TmallYfDataPackage.convertObjectToJson(map)+"}";
		        System.out.println("jsonString="+jsonString);
		        
		        //配置参数
		        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();  
		        nvps.add(new BasicNameValuePair("REQ_MESSAGE", jsonString)); // 参数
		        UrlEncodedFormEntity params =new UrlEncodedFormEntity(nvps, HTTP.UTF_8);
		        request.setEntity(params);
		        //发送请求
		        HttpResponse response = httpClient.execute(request);  
		        HttpEntity entity = response.getEntity();  
		        InputStream instream = entity.getContent();  
		        BufferedReader in = new BufferedReader(new InputStreamReader(instream,"UTF-8"));  
		        sb = new StringBuffer();  
		        String data = null;  
		        while((data = in.readLine())!=null){  
		            sb.append(data);  
		        }  
		        if(in != null)  
		            in.close(); 
		        System.out.println("return="+sb);
		        JSONObject json=JSONObject.fromObject(sb.toString());
		        String respString = json.optString("REP_BODY");
		        JSONObject respJson=JSONObject.fromObject(respString);
			}
		} catch (Exception e) {
			setCheckCode("9998");
			e.printStackTrace();
		}
		registerMerInfo.setApplication("registerMerSY.Rsp");
		registerMerInfo.setRespCode(checkCode);
		registerMerInfo.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
		return registerMerInfo;
	}
	
	/**
	 * 无卡支付 绑定银行卡
	 * @Title:        upBankCard 
	 * @Description:  
	 * @param:        @param upBankCardInfo
	 * @param:        @return    
	 * @return:       UpBankCardInfo    
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2015-11-16 下午5:25:48
	 */
	public UpBankCardInfo upBankCardSY(UpBankCardInfo upBankCardInfo){
		upBankCardInfo.copyBusBeanParent(upBankCardInfo);
		setCheckCode("");
		DefaultHttpClient httpClient = new DefaultHttpClient();  
		HttpPost request = new HttpPost(rb.getString("SYurl")+"IN0002.json");  
		try {
			if (upBankCardInfo != null) {
				Map<String,String> map = new LinkedHashMap<String, String>();  
				map.put("custId", upBankCardInfo.getCustId());
				map.put("bankType", upBankCardInfo.getBankType());
				map.put("cardNo", upBankCardInfo.getCardNo());
				map.put("cardFront", upBankCardInfo.getCardFront());
				map.put("cardBack", upBankCardInfo.getCardBack());
				map.put("provinceId", upBankCardInfo.getProvinceId());
				map.put("cityId", upBankCardInfo.getCityId());
				map.put("areaId", upBankCardInfo.getAreaId());
				map.put("bankcode", upBankCardInfo.getBankcode());
				map.put("txnDate", upBankCardInfo.getTxnDate());
				map.put("txnTime", upBankCardInfo.getTxnTime());
				map.put("agentId", upBankCardInfo.getAgentId());
				StringBuffer sb = new StringBuffer();
				//制作签名
				String checkSignMsg=Md5Util.generateMD5String(TmallYfDataPackage.convertObjectToJson(map)+rb.getString("SYsign"));
				
				String jsonString = "{\"REQ_HEAD\":{\"SIGN\":\""+checkSignMsg+"\"},\"REQ_BODY\":"+TmallYfDataPackage.convertObjectToJson(map)+"}";
		        System.out.println("jsonString="+jsonString);
		        
		        //配置参数
		        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();  
		        nvps.add(new BasicNameValuePair("REQ_MESSAGE", jsonString)); // 参数
		        UrlEncodedFormEntity params =new UrlEncodedFormEntity(nvps, HTTP.UTF_8);
		        request.setEntity(params);
		        //发送请求
		        HttpResponse response = httpClient.execute(request);  
		        HttpEntity entity = response.getEntity();  
		        InputStream instream = entity.getContent();  
		        BufferedReader in = new BufferedReader(new InputStreamReader(instream,"UTF-8"));  
		        sb = new StringBuffer();  
		        String data = null;  
		        while((data = in.readLine())!=null){  
		            sb.append(data);  
		        }  
		        if(in != null)  
		            in.close(); 
		        System.out.println("return="+sb);
		        JSONObject json=JSONObject.fromObject(sb.toString());
		        String respString = json.optString("REP_BODY");
		        JSONObject respJson=JSONObject.fromObject(respString);
			}
		} catch (Exception e) {
			setCheckCode("9998");
			e.printStackTrace();
		}
		upBankCardInfo.setRespCode(checkCode);
		upBankCardInfo.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
		return upBankCardInfo;
	}
	
	public PrdOrderInfo prdOrderSY(PrdOrderInfo prdOrderInfo){
		prdOrderInfo.copyBusBeanParent(prdOrderInfo);
		setCheckCode("");
		DefaultHttpClient httpClient = new DefaultHttpClient();  
		HttpPost request = new HttpPost(rb.getString("SYurl")+"IN0003.json");  
		try {
			if (prdOrderInfo != null) {
				Map<String,String> map = new LinkedHashMap<String, String>();  
				map.put("custId", prdOrderInfo.getCustId());
				map.put("custMobile", prdOrderInfo.getCustMobile());
				map.put("prdordType", prdOrderInfo.getPrdordType());
				map.put("prdordAmt", prdOrderInfo.getPrdordAmt());
				map.put("agentOrderNo", prdOrderInfo.getAgentOrderNo());
				map.put("PayBackUrl", prdOrderInfo.getPayBackUrl());
				map.put("txnDate", prdOrderInfo.getTxnDate());
				map.put("txnTime", prdOrderInfo.getTxnTime());
				map.put("agentId", prdOrderInfo.getAgentId());
				StringBuffer sb = new StringBuffer();
				//制作签名
				String checkSignMsg=Md5Util.generateMD5String(TmallYfDataPackage.convertObjectToJson(map)+rb.getString("SYsign"));
				
				String jsonString = "{\"REQ_HEAD\":{\"SIGN\":\""+checkSignMsg+"\"},\"REQ_BODY\":"+TmallYfDataPackage.convertObjectToJson(map)+"}";
		        System.out.println("jsonString="+jsonString);
		        
		        //配置参数
		        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();  
		        nvps.add(new BasicNameValuePair("REQ_MESSAGE", jsonString)); // 参数
		        UrlEncodedFormEntity params =new UrlEncodedFormEntity(nvps, HTTP.UTF_8);
		        request.setEntity(params);
		        //发送请求
		        HttpResponse response = httpClient.execute(request);  
		        HttpEntity entity = response.getEntity();  
		        InputStream instream = entity.getContent();  
		        BufferedReader in = new BufferedReader(new InputStreamReader(instream,"UTF-8"));  
		        sb = new StringBuffer();  
		        String data = null;  
		        while((data = in.readLine())!=null){  
		            sb.append(data);  
		        }  
		        if(in != null)  
		            in.close(); 
		        System.out.println("return="+sb);
		        JSONObject json=JSONObject.fromObject(sb.toString());
		        String respString = json.optString("REP_BODY");
		        JSONObject respJson=JSONObject.fromObject(respString);
			}
		} catch (Exception e) {
			setCheckCode("9998");
			e.printStackTrace();
		}
		prdOrderInfo.setRespCode(checkCode);
		prdOrderInfo.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
		return prdOrderInfo;
	}
	
	public TranSerialListInfo getTranSerialListSY(TranSerialListInfo tranSerialListInfo){
		tranSerialListInfo.copyBusBeanParent(tranSerialListInfo);
		setCheckCode("");
		DefaultHttpClient httpClient = new DefaultHttpClient();  
		HttpPost request = new HttpPost(rb.getString("SYurl")+"IN0004.json");  
		try {
			if (tranSerialListInfo != null) {
				Map<String,String> map = new LinkedHashMap<String, String>();  
				map.put("custId", tranSerialListInfo.getCustId());
				map.put("start", tranSerialListInfo.getStart());
				map.put("pageSize", tranSerialListInfo.getPageSize());
				map.put("txnDate", tranSerialListInfo.getTxnDate());
				map.put("txnTime", tranSerialListInfo.getTxnTime());
				map.put("agentId", tranSerialListInfo.getAgentId());
				StringBuffer sb = new StringBuffer();
				//制作签名
				String checkSignMsg=Md5Util.generateMD5String(TmallYfDataPackage.convertObjectToJson(map)+rb.getString("SYsign"));
				
				String jsonString = "{\"REQ_HEAD\":{\"SIGN\":\""+checkSignMsg+"\"},\"REQ_BODY\":"+TmallYfDataPackage.convertObjectToJson(map)+"}";
		        System.out.println("jsonString="+jsonString);
		        
		        //配置参数
		        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();  
		        nvps.add(new BasicNameValuePair("REQ_MESSAGE", jsonString)); // 参数
		        UrlEncodedFormEntity params =new UrlEncodedFormEntity(nvps, HTTP.UTF_8);
		        request.setEntity(params);
		        //发送请求
		        HttpResponse response = httpClient.execute(request);  
		        HttpEntity entity = response.getEntity();  
		        InputStream instream = entity.getContent();  
		        BufferedReader in = new BufferedReader(new InputStreamReader(instream,"UTF-8"));  
		        sb = new StringBuffer();  
		        String data = null;  
		        while((data = in.readLine())!=null){  
		            sb.append(data);  
		        }  
		        if(in != null)  
		            in.close(); 
		        System.out.println("return="+sb);
		        JSONObject json=JSONObject.fromObject(sb.toString());
		        String respString = json.optString("REP_BODY");
		        JSONObject respJson=JSONObject.fromObject(respString);
			}
		} catch (Exception e) {
			setCheckCode("9998");
			e.printStackTrace();
		}
		tranSerialListInfo.setRespCode(checkCode);
		tranSerialListInfo.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
		return tranSerialListInfo;
	}
	
	/**
	 * 余额支付
	 */
	@Override
	public Object accountPay(AccountBalance accountBalance) throws Exception {
		accountBalance.copyBusBeanParent(accountBalance);
		setCheckCode("");
		SubMerInfo subMerInfo = new SubMerInfo();
		subMerInfo.setContactorPhone(accountBalance.getMobileNum());
		try {
			  subMerInfo = subMerInfoDao.findByPhone(subMerInfo);
			  SubMerInfo updateInfo = new SubMerInfo();
			  System.out.println("subMerInfo="+subMerInfo);
			  System.out.println("subMerInfo="+subMerInfo.getgAccStatus());
			  if(subMerInfo!=null && subMerInfo.getgAccStatus()!=null){
				  BigDecimal balance = new BigDecimal(subMerInfo.getgAccStatus());
				  BigDecimal orderMoney = new BigDecimal(accountBalance.getPayAmt());
				  //0.95费率
				  BigDecimal payMoney = orderMoney.multiply(new BigDecimal("0.95")).setScale(0, BigDecimal.ROUND_UP);
				  System.out.println("商户余额"+balance.toString());
				  System.out.println("实际支付"+payMoney.toString());
				  if(balance.compareTo(payMoney)>=0){
					  BigDecimal newBalance = balance.subtract(payMoney);
					  updateInfo.setSubMerId(subMerInfo.getSubMerId());
					  updateInfo.setgAccStatus(newBalance.toString());
					  if(subMerInfoDao.update(updateInfo)){
						  accountBalance.setAccountBalance(newBalance.toString());
						  setCheckCode("0000");
						  //插入消费记录
						SubMerCashout subMerCashout = new SubMerCashout();
				 		subMerCashout.setOrderStatus("6"); // 余额支付
						subMerCashout.setTransId("");
						subMerCashout.setTransQid("");
						subMerCashout.setOrderAmt(payMoney.toString());
						subMerCashout.setTransFee(orderMoney.toString());
						subMerCashout.setT0MerGain("0");
						subMerCashout.setT0MerRate("0");
						subMerCashout.setSubMerId(subMerInfo.getSubMerId());
						subMerCashout.setMerSysId(subMerInfo.getMerSysId());
						subMerCashout.setFinishTime(DateUtil
								.getDateFormatStr("yyyyMMddHHmmss"));
						subMerCashout.setCreateTime(DateUtil
								.getDateFormatStr("yyyyMMddHHmmss"));
						subMerCashout.setBatchId(DateUtil
								.getDateFormatStr("yyyyMMddHHmmssSSS"));
						subMerCashout.setGrade("0");
						subMerCashoutDao.insertSubMerCashout(subMerCashout);
					  }else{
						  setCheckCode("9996");
					  }
				  }else{
					  setCheckCode("3067");
				  }
			  }else{
				  setCheckCode("3067");
			  } 
		} catch (Exception e) {
			setCheckCode("9998");
			e.printStackTrace();
		}
		accountBalance.setRespCode(checkCode);
		accountBalance.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
		return accountBalance;
	}
	
	/**
	 * 
	 * @Description:获取六位随机数
	 * @Auther: lijialiang
	 * @Date: 2014-12-17 上午11:27:48
	 */
	private Integer getSixRandom(final int min, final int max) {
		Random rand = new Random();
		int tmp = Math.abs(rand.nextInt());
		return tmp % (max - min + 1) + min;
	}
	
	public boolean insertoto(JSONObject json) throws Exception {
		//先insert 手机用户
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		int rI = (int)(Math.random()*9);
		String subMerId = sdf.format(new Date())+rI;// 子商户号
		MobileUser mobileUser = new MobileUser();
		mobileUser.setSubMerId(subMerId);
		mobileUser.setLoginName(json.optString("mobileNum"));
		mobileUser.setLoginPwd(Md5Util.generateMD5String(json.optString("accountPwd")));
		mobileUser.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		mobileUser.setStatus("1"); //实名用户
		int result=mobileUserDao.insertMobileUser(mobileUser);
		SubMerInfo subMerInfo = new SubMerInfo();
		if(result>0){
			subMerInfo.setSubMerId(subMerId);
			subMerInfo.setLineNum(json.optString("lineNum"));
			subMerInfo.setOpenBank(json.optString("openBank"));
			subMerInfo.setSubMerName(json.optString("legalManName"));
			subMerInfo.setShortName(json.optString("legalManName"));
			subMerInfo.setLegalPerson(json.optString("legalManName"));
			subMerInfo.setLegalIdcard(json.optString("legalManIdcard"));
			subMerInfo.setRegNo("-1");
			subMerInfo.setTaxNo("-1");
			subMerInfo.setOrganizationCode("-1");
			String settAccType = "-1";
			subMerInfo.setSettAccType(settAccType);
			String settAccName = json.optString("legalManName");
			subMerInfo.setSettAccountName(settAccName);
			String settAccNo = json.optString("settleAccountNo");
			subMerInfo.setSettAccountNo(settAccNo);
			subMerInfo.setSettAgency("ICBC");
			subMerInfo.setStatus("0");
			String merSysId = "-1";
			String agentIdL1 = "-1";
			String agentIdL2 = "-1";
			String agentIdL3 = "-1";
			String agentIdL4 = "-1";
			subMerInfo.setMerSysId(merSysId);
			subMerInfo.setCreateTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
			subMerInfo.setAgentIdL1(agentIdL1);
			subMerInfo.setAgentIdL2(agentIdL2);
			subMerInfo.setAgentIdL3(agentIdL3);
			subMerInfo.setAgentIdL4(agentIdL4);
			subMerInfo.setBillStatus("1");
			subMerInfo.setMerKind("个体户");// 默认公司性质
			subMerInfo.setContactor(json.optString("legalManName"));
			subMerInfo.setContactorPhone(json.optString("mobileNum"));
			//公司地址
			subMerInfo.setRegAddr(json.optString("regAddr"));
			result = subMerInfoDao.insertSubMerInfo(subMerInfo);
		}
		if(result>0){
			//上传图片
			uploadMerPic(json,subMerInfo.getSubMerId());
			JSONObject mobileLocal = getMobileFrom(subMerInfo.getContactorPhone());
			if(mobileLocal!=null){
				subMerInfo.setPhoneProvince(mobileLocal.getString("province"));
				subMerInfo.setPhoneCity(mobileLocal.getString("cityname"));
				subMerInfoDao.updateMobileFrom(subMerInfo);
			}
			return true;
		}else{
			return false;
		}
	}
	
	public boolean updateoto(JSONObject json) throws Exception {
			int result =0;
			MobileUser mobileUser = new MobileUser();
			mobileUser.setLoginName(json.optString("mobileNum"));
			mobileUser = mobileUserDao.getMobileUser(mobileUser);
			SubMerInfo subMerInfo = new SubMerInfo();
			if(mobileUser!=null){
			subMerInfo.setSubMerId(mobileUser.getSubMerId());
			subMerInfo.setLineNum(json.optString("lineNum"));
			subMerInfo.setOpenBank(json.optString("openBank"));
			subMerInfo.setSubMerName(json.optString("legalManName"));
			subMerInfo.setShortName(json.optString("legalManName"));
			subMerInfo.setLegalPerson(json.optString("legalManName"));
			subMerInfo.setLegalIdcard(json.optString("legalManIdcard"));
			subMerInfo.setRegNo("-1");
			subMerInfo.setTaxNo("-1");
			subMerInfo.setOrganizationCode("-1");
			String settAccType = "-1";
			subMerInfo.setSettAccType(settAccType);
			String settAccName = "-1";
			subMerInfo.setSettAccountName(settAccName);
			String settAccNo = "-1";
			subMerInfo.setSettAccountNo(settAccNo);
			subMerInfo.setSettAgency("ICBC");
			subMerInfo.setStatus("0");
			String merSysId = "-1";
			String agentIdL1 = "-1";
			String agentIdL2 = "-1";
			String agentIdL3 = "-1";
			String agentIdL4 = "-1";
			subMerInfo.setMerSysId(merSysId);
			subMerInfo.setCreateTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
			subMerInfo.setAgentIdL1(agentIdL1);
			subMerInfo.setAgentIdL2(agentIdL2);
			subMerInfo.setAgentIdL3(agentIdL3);
			subMerInfo.setAgentIdL4(agentIdL4);
			subMerInfo.setBillStatus("1");
			subMerInfo.setMerKind("个体户");// 默认公司性质
			subMerInfo.setContactor(json.optString("legalManName"));
			subMerInfo.setContactorPhone(json.optString("mobileNum"));
			//公司地址
			subMerInfo.setRegAddr(json.optString("regAddr"));
			result = subMerInfoDao.updateSubMerInfoReg(subMerInfo);
			}
		if(result>0){
			//上传图片
			uploadMerPic(json,subMerInfo.getSubMerId());
			return true;
		}else{
			return false;
		}
	
	}
	
	public void uploadMerPic(JSONObject json,String subMerId){
		String card = json.optString("card");
		String cardBack = json.optString("cardBack");
		String idCard = json.optString("idCard");
		String idCardBack = json.optString("idCardBack");
		String person = json.optString("person");
		String path = rb.getString("ImagesUrl") + "/SubMerImages/-1/"
		+ subMerId + "/";
		File pathFile = new File(path);
		if(!pathFile.exists()){
			pathFile.mkdir();
		}
		BufferedImage bi1  =  null;
		ByteArrayInputStream bais = null;
		try{
			//card
			System.out.println("card="+card);
			byte[] bytes1 = Base64.decodeBase64(card.replaceAll(" ", "+"));
			bais = new ByteArrayInputStream(bytes1);
			bi1 = ImageIO.read(bais);
			File pic = new File(path+"card.jpg");
			ImageIO.write(bi1, "jpg", pic);
			bi1.flush();
			//cardback
			bytes1 = Base64.decodeBase64(cardBack.replaceAll(" ", "+"));
			bais = new ByteArrayInputStream(bytes1);
			bi1 = ImageIO.read(bais);
			pic = new File(path+"cardBack.jpg");
			ImageIO.write(bi1, "jpg", pic);
			bi1.flush();
			//idCard
			bytes1 = Base64.decodeBase64(idCard.replaceAll(" ", "+"));
			bais = new ByteArrayInputStream(bytes1);
			bi1 = ImageIO.read(bais);
			pic = new File(path+"idCard.jpg");
			ImageIO.write(bi1, "jpg", pic);
			bi1.flush();
			//idCardBack
			bytes1 = Base64.decodeBase64(idCardBack.replaceAll(" ", "+"));
			bais = new ByteArrayInputStream(bytes1);
			bi1 = ImageIO.read(bais);
			pic = new File(path+"idCardBack.jpg");
			ImageIO.write(bi1, "jpg", pic);
			bi1.flush();
			//person
			bytes1 = Base64.decodeBase64(person.replaceAll(" ", "+"));
			bais = new ByteArrayInputStream(bytes1);
			bi1 = ImageIO.read(bais);
			pic = new File(path+"person.jpg");
			ImageIO.write(bi1, "jpg", pic);
			bi1.flush();
			bais.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public JSONObject addOTOMer(JSONObject json){
		String mobileNum = json.optString("mobileNum");
		Long i = Long.parseLong(mobileNum);
		System.out.println("req oto:mobile="+mobileNum);
		Map<String, String> map = new HashMap<String, String>();
		try {
				boolean res = false;
				// 一次只能有一个线程进入，将子商户号组装完整
				synchronized (i) {
					List<SubMerInfo> subMerInfos  = subMerInfoDao.selectSubInfoByPhone(mobileNum);
					if(subMerInfos.size()==0){
						res = insertoto(json);
					}else{
						for(SubMerInfo sub :subMerInfos){
							if(sub.getStatus()!=null && sub.getStatus().equals("2")){
								map.put("respCode", "9998");
								map.put("respMsg", "该商户已上线");
								String jsonString = TmallYfDataPackage.convertObjectToJson(map);
								json=JSONObject.fromObject(jsonString);
								return json;
							}
						}
						res = updateoto(json);
					}
				}
				if(res){
					map.put("respCode", "0000");
					map.put("respMsg", "操作成功");
					String jsonString = TmallYfDataPackage.convertObjectToJson(map);
					json=JSONObject.fromObject(jsonString);
				}else{
					map.put("respCode", "9999");
					map.put("respMsg", "操作失败");
					String jsonString = TmallYfDataPackage.convertObjectToJson(map);
					json=JSONObject.fromObject(jsonString);
				}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
		}
		return json;
	}
	
	/**
	 * 交易查询接口
	 */
//	@Override
//	public JSONObject searchOrder(JSONObject json){
//		String createDate = json.optString("createDate");
//		String orderId = json.optString("orderId");
//		
//		Map maps = new HashMap();
//		try {
////			 maps.put("createDate", DateUtil.getDate("yyyyMMdd"));
////		      maps.put("subMerId", busMerId);
////		      maps.put("orderId", orderId);
////		      maps.put("merOrderId", merOrderId);
////		      maps.put("userMobile", userMobile);
////		      maps.put("agentIdL1", agentIdL1);
////		      maps.put("agentIdL2", agentIdL2);
////		      maps.put("merOrderTime", merOrderTime);
////		      maps.put("transType", transType);
////		      maps.put("orderStatus", orderStatus);
////		      maps.put("merSysId", merSysId);
////		      maps.put("terminalId", terminalSerialNumber);
////		      maps.put("cardNo", cardNo);
////		      maps.put("orderRateType", orderRateType);
////		      maps.put("signStatus", signStatus);
////		      maps.put("transMerId", this.getParameterForString("transMerId"));
////			  maps.put("orderStatictis", this.getParameterForString("orderStatictis"));
//		} catch (Exception e) {
//			e.printStackTrace();
//			TransactionAspectSupport.currentTransactionStatus()
//					.setRollbackOnly();
//		}
//		return json;
//	}
	
	/**
	 * 高风投资注册
	 */
	@Override
	public GFinvestment GFbussiness(GFinvestment regist) throws Exception {
		setCheckCode("");
		regist.copyBusBeanParent(regist);
		DefaultHttpClient httpClient = new DefaultHttpClient();  
		String orgCode="4001000009989898";
		String aesKey="oQ3G4xqD6lzmOaWu/m6Llg==";
		try {
			HttpPost request = new HttpPost("http://112.64.130.142:8089/ext/gaoexapi/request.htm");  //test
			Map<String,String> map = new LinkedHashMap<String, String>();  
			Map<String,String> dataMap = new LinkedHashMap<String, String>();
			map.put("orgCode", orgCode);
			map.put("bizCode", regist.getTransCode());
			if("601106".equals(regist.getTransCode())){		//同步会员
				dataMap.put("mobile", regist.getMobileNum());
				dataMap.put("type", "1");
				dataMap.put("role", "1");
				dataMap.put("info", regist.getMsgExt());
				dataMap.put("source", "1");
				dataMap.put("channel", "2");
				dataMap.put("orgNo", orgCode);
				dataMap.put("realName", regist.getLegalManName());
				dataMap.put("certNo", regist.getLegalManIdcard());
				dataMap.put("certType", "1");
				dataMap.put("cardNo", regist.getCardNo());
				dataMap.put("bankCode", regist.getOpenBank());
				dataMap.put("bankProvince", regist.getCity());
			}else if("601114".equals(regist.getTransCode())){		//会员获取token接口
				
			}
			//组包
			String dataString = TmallYfDataPackage.convertObjectToJson(dataMap); 
			String keyStorePath = "d:/gaoex.keystore";
			String password = "123456";  
			String alias = "www.gaoex.com"; 
			String sign = dataString+orgCode;
			System.out.println("sign="+sign);
			byte[] bbb=CertificateUtils.sign(sign.getBytes("utf-8"), keyStorePath, alias, password);
			sign = BASE64Util.encodeBySun(CertificateUtils.sign(sign.getBytes("utf-8"), keyStorePath, alias, password));
			String AesData = BASE64Util.encodeBySun(AESCoder.encrypt(dataString.getBytes("utf-8"),Base64.decodeBase64(aesKey.getBytes())));
			map.put("data", AesData);
			map.put("sign", sign);
			String jsonString = TmallYfDataPackage.convertObjectToJson(map);
			System.out.println("send="+jsonString);
			StringEntity params =new StringEntity(jsonString,"UTF-8");  
	        request.addHeader("content-type", "application/json");  
	        request.setEntity(params);  
	        
	        HttpResponse response = httpClient.execute(request);  
	        HttpEntity entity = response.getEntity();  
	        InputStream instream = entity.getContent();  
	        BufferedReader in = new BufferedReader(new InputStreamReader(instream,"UTF-8"));  
	        StringBuffer sb = new StringBuffer();  
	        String data = null;  
	        while((data = in.readLine())!=null){  
	            sb.append(data);  
	        }  
	        if(in != null)  
	            in.close(); 
	        System.out.println(sb);
	        JSONObject json=JSONObject.fromObject(sb.toString());
			String respCode=json.optString("respCode");
			String respMsg=json.optString("respMsg");
			String respData=json.optString("data");
	        System.out.println("respCode="+respCode);
	        System.out.println("respMsg="+respMsg);
	      //解密
			byte [] decodedData = AESCoder.decrypt(BASE64Util.decodeBySun(respData), BASE64Util.decodeBySun(aesKey));
			//解密后的原始报文
			String outputStr = new String(decodedData);
			System.out.println("原始明文内容：" + outputStr);
			JSONObject respJson=JSONObject.fromObject(outputStr);
			if("601106".equals(regist.getTransCode())){		//同步会员
				String memberId = respJson.optString("respJson");
				MobileUser mobileUser = new MobileUser();
				mobileUser.setLoginName(regist.getMobileNum());
//				mobileUser.setGfMemberId(memberId);
				mobileUserDao.updateMemberId(mobileUser);
			}
			String token = respJson.optString("Token");
			
			
		} catch (Exception e) {
			setCheckCode("9998");
			e.printStackTrace();
		}
		return regist;
	}
	
	
	public JSONObject getMobileFrom(String mobile){
		DefaultHttpClient httpClient = new DefaultHttpClient();  
		StringBuffer sb = new StringBuffer();
		HttpGet request = new HttpGet("http://virtual.paipai.com/extinfo/GetMobileProductInfo?mobile="+mobile+"&amount=10&callname=");  
		try {
			  HttpResponse response = httpClient.execute(request);  
		        HttpEntity entity = response.getEntity();  
		        InputStream instream = entity.getContent();  
		        BufferedReader in = new BufferedReader(new InputStreamReader(instream,"GBK"));  
		        sb = new StringBuffer();  
		        String data = null;  
		        while((data = in.readLine())!=null){  
		            sb.append(data);  
		        }  
		        if(in != null)  
		            in.close(); 
		        System.out.println(sb.toString());
		        String ss = sb.substring(0, sb.indexOf(");"));
		        ss = ss.substring(1);
		        System.out.println(ss);
		        JSONObject json=JSONObject.fromObject(ss);
		        System.out.println("mobile="+mobile);
		        System.out.println(json.optString("province"));
		        System.out.println(json.optString("isp"));
		        System.out.println("cityname="+json.optString("cityname"));
		        return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 信用卡业务
	 */
	@Override
	public CreditInfoList creditbussiness(CreditInfoList regist) throws Exception {
		setCheckCode("");
		regist.copyBusBeanParent(regist);
		Map<Object, Object> map = new HashMap<Object, Object>();
		procedureDao.getSequence(map);
		String transCode = regist.getTransCode();
		//0000,查询;0001 添加;0002删除;
		if(transCode.equals("0000")){
			CreditInfo creditInfo = new CreditInfo();
			creditInfo.setMerchantId(regist.getMerchantId());
			List<CreditInfo> creditInfoList = creditInfoDao.selectCreditInfoBySubMerId(creditInfo);
			StringBuffer cardLine = new StringBuffer();
			for(CreditInfo info:creditInfoList){
				cardLine.append(info.getCreditCard());
				cardLine.append(",");
			}
			setCheckCode("0000");
			regist.setCreditNumber(cardLine.toString());
			regist.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
		}else if(transCode.equals("0001")){
		    //先看看有没有存在
		    CreditInfo creditInfo = new CreditInfo();
            creditInfo.setMerchantId(regist.getMerchantId());
	        List<CreditInfo> creditInfoList = creditInfoDao.selectCreditInfoBySubMerId(creditInfo);
	        for(CreditInfo credit:creditInfoList){
	            if(credit.getCreditCard().equals(regist.getCreditNumber())){
	                setCheckCode("0000");
	                regist.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
	                return regist;
	            }
	        }
	        
//	        JSONObject json = checkJACredit(regist);
//		    if(json!=null && json.optString("respCode").equals("000")){
				creditInfo = new CreditInfo();
				creditInfo.setCreditCard(regist.getCreditNumber());
				creditInfo.setIdNum(regist.getLegalManIdcard());
				creditInfo.setIdNum(regist.getLegalManIdcard());
				creditInfo.setCardLength(regist.getCreditNumber().length());
				creditInfo.setMerchantId(regist.getMerchantId());
				creditInfo.setMobileNum(regist.getMobileNum());
				creditInfo.setUserName(regist.getLegalManName());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				creditInfo.setCreateTime(sdf.format(new Date()));
				int result = creditInfoDao.deleteCredit(creditInfo);
				if(result>0){
				    setCheckCode("0000");
                }else{
                    insertCheckCredit(creditInfo);
                    result = creditInfoDao.insertCredit(creditInfo);
                    if(result>0){
                        setCheckCode("0000");
                    }else{
                        setCheckCode("9995");
                    }
                }
				regist.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
				String path = rb.getString("creditcard-path");
				byte[] imageByte = Base64.decodeBase64(regist.getPicBuffer());
				String imagePath = path+regist.getMerchantId();
				File w2 = new File(imagePath);// 可以是jpg,png,gif格式
				if (!w2.exists())
					w2.mkdirs();
				ByteArrayInputStream bais = new ByteArrayInputStream(imageByte);
				BufferedImage bi1 = ImageIO.read(bais);
				File pic = new File(imagePath+"/"+regist.getCreditNumber()+".jpg");
				System.out.println("信用卡路径:"+imagePath+"/"+regist.getCreditNumber()+".jpg");
				ImageIO.write(bi1, "jpg", pic);
//			}else{
//				setCheckCode("9995");
////				regist.setRespDesc(json.optString("reqMsg"));
//				regist.setRespDesc(json.optString("respDesc"));
//			}
		}else if(transCode.equals("0002")){
			CreditInfo creditInfo = new CreditInfo();
			creditInfo.setMerchantId(regist.getMerchantId());
			creditInfo.setCreditCard(regist.getCreditNumber());
			creditInfo.setCardLength(0);
			//删除改为修改lenth为0
			int result = creditInfoDao.deleteCredit(creditInfo);
			if(result>0){
				setCheckCode("0000");
			}else{
				setCheckCode("9995");
			}
			regist.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
//            setCheckCode("9995");
//            regist.setRespDesc("不允许删除");
		}
		regist.setPicBuffer("");
		regist.setRespCode(checkCode);
		System.out.println("CREDIT="+regist.toString());
		return regist;
	}
	
	public void insertCheckCredit(CreditInfo creditInfo){
		CheckCredit checkCredit = new CheckCredit();
		checkCredit.setCreditCard(creditInfo.getCreditCard());
		checkCredit.setIdNum(creditInfo.getIdNum());
		checkCredit.setMobileNum(creditInfo.getMobileNum());
		checkCredit.setUserName(creditInfo.getUserName());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		checkCredit.setCreateTime(sdf.format(new Date()));
		int result = checkCreditDao.insertCredit(checkCredit);
	}
	/**
	 * 信用卡四要素验证
	 * @Title:        checkCredit 
	 * @Description:  
	 * @param:            
	 * @return:       void    
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2016-4-20 下午4:21:05
	 */
	public JSONObject checkCredit(CreditInfoList regist){
		String mcode = "137458150713099";
	    String tcode = "60110";
		Map<String,String> result = new LinkedHashMap<String, String>();  
		try {
			result.put("mcode", mcode);
			result.put("tcode", tcode);
			result.put("version", "1.0");
			
			String accountNumber = regist.getCreditNumber();
			Date date = new Date();
			//四要素认证
			String accountName = regist.getLegalManName();
			String idCard = regist.getLegalManIdcard();
			String mobile = regist.getMobileNum();
			result.put("action","add_bankCheck_userBankCardCheck");
			result.put("merchantOrderId", TimeUtils.dateString(date, "yyyyMMddHHmmssSSS"));//商户订单号  可以自定义
			result.put("merchantOrderTime", TimeUtils.dateString(date, "yyyyMMddHHmmss"));			
			result.put("accountNumber", accountNumber);//卡号
			result.put("accountName", accountName);
			result.put("idCard", idCard);
			result.put("mobile", mobile);
			JSONObject json = RuiyinCredit.diaoyong(JSONStringBuilder.getAjaxString(result));
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public JSONObject checkYMCredit(CreditInfoList regist){
		JSONObject tokenJson = getToken2();
		JSONObject json = null;
		if(tokenJson!=null){
			String token = tokenJson.optString("access_token");
//			json = YMInterfaceUtil.checkBankCard(regist.getLegalManName(), regist.getLegalManIdcard(), regist.getMobileNum(), regist.getCreditNumber(), token);
			json = YMInterfaceUtil.checkBankCard2(regist.getLegalManName(),regist.getCreditNumber(), token);
		}
		return json;
	}
	
	public JSONObject checkJACredit(CreditInfoList regist){
	    Map<String, String> param = new HashMap<String, String>();
	    param.put("userName", regist.getLegalManName());
	    param.put("idNum", regist.getLegalManIdcard());
	    param.put("cardNo", regist.getCreditNumber());
	    param.put("cellNo", regist.getMobileNum());
	    JSONObject json = JAUtil.card4(param);
	    return json;
	}
	
	
	public  JSONObject getToken2(){
		String url ="http://opensdk.emay.cn:9080/HD_GetAccess_Token.asmx"; // 在浏览器中打开url，可以找到
		String namespace = "http://tempuri.org/"; // Action路径 
		String op = "GetACCESS_TOKEN"; // 要调用的方法名
		org.apache.axis.client.Service service = new org.apache.axis.client.Service();
		try {
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(url)); 
			call.setUseSOAPAction(true);
			// action uri
			call.setSOAPActionURI(namespace + op);
			// 设置要调用哪个方法
			call.setOperationName(new QName(namespace, op));
			// 设置参数名称，具体参照从浏览器中看到的
			call.addParameter(new QName(namespace, "AppID"),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
			call.addParameter(new QName(namespace, "AppSecret"),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
			call.addParameter(new QName(namespace, "Key"),XMLType.XSD_STRING, ParameterMode.IN); // 要返回的数据类型 
			call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_STRING);
			Object[] params = new Object[] {"CB3AC210WE3A5W4C75WAA0BWAAF148FB5142","62C586C8L3691L4499L9EC5L0725BF4486F2","A9AD8378H4523H40FFH9E05HE08B0274F349"}; // 调用方法并传递参数
			System.out.println("参数发送完毕!");
			String v = (String) call.invoke(params); 
			System.out.println("获取信息");
			System.out.println(v.toString());
			JSONObject json = JSONObject.fromObject(v);
			
			return json;
		} 
		catch (Exception ex) 
		{ 
			ex.printStackTrace(); 
			return null;
		} 
	}
	
	@Override
	public CheckVersion checkVersion(CheckVersion regist) throws Exception {
		setCheckCode("");
		regist.copyBusBeanParent(regist);
		MerTerminalInfo merTerminal = new MerTerminalInfo();
		merTerminal.setMerSysId(regist.getMerSysId());
		merTerminal.setTerminalSysterm(regist.getTerminalSysterm());
		merTerminal = merTerminalInfoDao.selectMerTerminalVersion(merTerminal);
		if(merTerminal!=null){
			regist.setVersion(merTerminal.getVersion());
			regist.setVersionDesc(merTerminal.getVersionDesc());
			regist.setUpdatePath(merTerminal.getUpdatePath());
			regist.setUpdateType(merTerminal.getUpdateType());
			setCheckCode("0000");
		}else{
			setCheckCode("9013");
		}
	
		regist.setRespCode(checkCode);
		regist.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
		System.out.println("CheckVersion="+regist.toString());
		return regist;
	}
	
	
	@Override
	public XunlianOrder xunlianOrder(XunlianOrder regist) throws Exception {
		setCheckCode("");
		regist.copyBusBeanParent(regist);
		String merOrderId = regist.getMerchantOrderId();
		String code = regist.getRespCode();
		String resp = regist.getRespDesc();
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setMerOrderId(merOrderId);
		orderInfo = orderInfoDao.selectByMerOrder(orderInfo);
		if(orderInfo==null){
			setCheckCode("1008");
			regist.setRespCode(checkCode);
			regist.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
			return regist;
		}
		if(!code.equals("00")){
			orderInfo.setOrderStatus("2");
		}
		orderInfo.setRespCode(code);
		orderInfo.setRespDesc(resp);
		orderInfoDao.updateOrder(orderInfo);
		setCheckCode("0000");
		regist.setRespCode(checkCode);
		regist.setRespDesc(ValueTool.SYS_CODE.get(checkCode));
		System.out.println("xunlianOrder="+regist.toString());
		return regist;
	}
	
	@Override
	public JSONObject mobileRegist(JSONObject json){
			String loginName = json.optString("loginName");
			String password = json.optString("password");
			MobileUser mobileUser = new MobileUser();
			mobileUser.setLoginName(loginName);
			mobileUser = mobileUserDao.getMobileUser(mobileUser);
			Map<String, String> map = new HashMap<String, String>();
			if(mobileUser!=null){
				map.put("respCode", "3057");
				map.put("respMsg", ValueTool.SYS_CODE.get("3057"));
			}else{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				int rI = (int)(Math.random()*9);
				String subMerId = sdf.format(new Date())+rI;// 子商户号
				String status = "0";// 子商户状态
				int result=0;
				synchronized (loginName) {
					mobileUser = new MobileUser();
					mobileUser.setSubMerId(subMerId);
					mobileUser.setLoginName(loginName);
					mobileUser.setLoginPwd(password);
					mobileUser.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					mobileUser.setStatus("0"); //初始用户
					result=mobileUserDao.insertMobileUser(mobileUser);
				}
				if(result > 0){
					map.put("respCode", "0000");
					map.put("respMsg", ValueTool.SYS_CODE.get("0000"));
				}else{
					map.put("respCode", "9995");
					map.put("respMsg", ValueTool.SYS_CODE.get("9995"));
				}
			}
//		try {
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			TransactionAspectSupport.currentTransactionStatus()
//					.setRollbackOnly();
//		}
		String jsonString = TmallYfDataPackage.convertObjectToJson(map);
		json=JSONObject.fromObject(jsonString);
		return json;
	}
	
	@Override
	public JSONObject invite(JSONObject json){
		String loginName = json.optString("loginName");
		String password = json.optString("password");
		String invite = json.optString("inviteName");
		MobileUser mobileUser = new MobileUser();
		mobileUser.setLoginName(loginName);
		mobileUser = mobileUserDao.getMobileUser(mobileUser);
		Map<String, String> map = new HashMap<String, String>();
		if(mobileUser!=null){
			map.put("respCode", "3057");
			map.put("respMsg", ValueTool.SYS_CODE.get("3057"));
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			int rI = (int)(Math.random()*9);
			String subMerId = sdf.format(new Date())+rI;// 子商户号
			String status = "0";// 子商户状态
			int result=0;
			synchronized (loginName) {
				mobileUser = new MobileUser();
				mobileUser.setSubMerId(subMerId);
				mobileUser.setLoginName(loginName);
				mobileUser.setLoginPwd(password);
				mobileUser.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				mobileUser.setStatus("0"); //初始用户
				result=mobileUserDao.insertMobileUser(mobileUser);
			}
			if(result > 0){
				//注册成功 绑定关系
				RuleInfo ruleInfo = new RuleInfo();
				ruleInfo.setUserId(invite);
				ruleInfo.setChildId(loginName);
				ruleInfoDao.insertRuleInfo(ruleInfo);
				map.put("respCode", "0000");
				map.put("respMsg", ValueTool.SYS_CODE.get("0000"));
			}else{
				map.put("respCode", "9995");
				map.put("respMsg", ValueTool.SYS_CODE.get("9995"));
			}
		}
//		try {
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			TransactionAspectSupport.currentTransactionStatus()
//					.setRollbackOnly();
//		}
		String jsonString = TmallYfDataPackage.convertObjectToJson(map);
		json=JSONObject.fromObject(jsonString);
		return json;
	}
	
	@Override
	public JSONObject waresList(JSONObject json){
		String page = json.optString("page");
		String pageSize = json.optString("pageSize");

		Map map = PageUtil.getPageMap(Integer.parseInt(page), Integer.parseInt(pageSize));
		WaresInfo waresInfo = new WaresInfo();
		map.put("waresInfo", waresInfo);
		int count = waresInfoDao.selectWaresCount(map);
		List<WaresInfo>waresInfoList = waresInfoDao.selectWares(map);
		JSONArray jsonarray = JSONArray.fromObject(waresInfoList);  
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("count",count+"");
		jsonMap.put("respCode", "0000");
		jsonMap.put("respMsg", ValueTool.SYS_CODE.get("0000"));
		String jsonString = TmallYfDataPackage.convertObjectToJson(jsonMap);
		json=JSONObject.fromObject(jsonString);
		json.element("waresList", jsonarray);
		return json;
	}
	
	@Override
	public JSONObject specList(JSONObject json){
		String page = json.optString("page");
		String pageSize = json.optString("pageSize");
		String waresId =  json.optString("waresId");
		Map map = PageUtil.getPageMap(Integer.parseInt(page), Integer.parseInt(pageSize));
		WaresSpecInfo waresSpecInfo = new WaresSpecInfo();
		waresSpecInfo.setWaresId(Long.parseLong(waresId));
		map.put("waresSpecInfo", waresSpecInfo);
		int count = waresSpecInfoDao.selectSpecCount(map);
		List<WaresSpecInfo> waresSpecInfoList = waresSpecInfoDao.selectSpec(map);		
		JSONArray jsonarray = JSONArray.fromObject(waresSpecInfoList);  
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("count",count+"");
		jsonMap.put("respCode", "0000");
		jsonMap.put("respMsg", ValueTool.SYS_CODE.get("0000"));
		String jsonString = TmallYfDataPackage.convertObjectToJson(jsonMap);
		json=JSONObject.fromObject(jsonString);
		json.element("specList", jsonarray);
		return json;
	}
	
	@Override
	public JSONObject showInvite(JSONObject json){
		String loginName = json.optString("loginName");
		RuleInfo ruleInfo = new RuleInfo();
		ruleInfo.setParentId(loginName);
		List<RuleInfo> childrens = ruleInfoDao.getChildren(ruleInfo);
		Map jsonMap = new HashMap();
		jsonMap.put("respCode", "0000");
		jsonMap.put("respMsg", ValueTool.SYS_CODE.get("0000"));
		jsonMap.put("childrens", JSONArray.fromObject(childrens));
		String jsonString = TmallYfDataPackage.convertObjectToJson(jsonMap);
		return JSONObject.fromObject(jsonString);
	}

    @Override
    public JSONObject sendMsg(JSONObject json) {
        String loginName = json.optString("loginName");
        int a = (int)(Math.random()*(9999-1000+1))+1000;//产生1000-9999的随机数
        String message = "验证码为:"+a;
        try {
            YMInterfaceUtil.sendSMS(loginName, message, 12);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map jsonMap = new HashMap();
        jsonMap.put("respCode", "0000");
        jsonMap.put("respMsg", ValueTool.SYS_CODE.get("0000"));
        jsonMap.put("checkCode", String.valueOf(a));
        String jsonString = TmallYfDataPackage.convertObjectToJson(jsonMap);
        return JSONObject.fromObject(jsonString);
    }

    @Override
    public JSONObject payToDy(JSONObject rJson) {
        JSONObject json = new JSONObject();
        String isSearch = rJson.optString("search");
        //代付查询
        if(StringUtils.isNotEmpty(isSearch) && isSearch.equals("true")){
            json.put("id", rJson.optString("id"));
            json.put("time", rJson.optString("tran_dt_tm"));
            json.put("checkCode",Md5Util.generateMD5String("NTFREEPAY"+json.getString("id")));
            try {
                rJson = payDY(json,"http://122.144.173.166:18080/dyHttpServer/dyAction!searchOrder.action");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return rJson;
        }
        
        json.put("cardNo", rJson.optString("cardNo"));
        json.put("userName", rJson.optString("userName"));
        json.put("lineNum", rJson.optString("lineNum"));
        json.put("bankName", rJson.optString("bankName"));
        json.put("transAmt", rJson.optString("transAmt"));
        json.put("purpose", "代付");
        json.put("memo",rJson.getString("orgNo")+"代付");
        json.put("reserved", "");
        json.put("id", "trd"+rJson.getString("orgNo")+rJson.getString("merId")+rJson.getString("referenceNo"));
        json.put("checkCode",Md5Util.generateMD5String("NTFREEPAY"+json.getString("id")));
        
        CoreTransLog coreTransLog = new CoreTransLog();
        coreTransLog.setIntTxnDt(rJson.optString("inTxnDt"));
        coreTransLog.setReferenceNo(rJson.optString("referenceNo"));
        coreTransLog.setMerId(rJson.optString("merId"));
        coreTransLog=coreTransLogDao.selectInfo(coreTransLog);
        
        if(coreTransLog==null){
            rJson.put("respCode", "96");
            rJson.put("ret_cd", "96");
            rJson.put("ret_msg", "找不到原始交易");
            return rJson;
        }
        BigDecimal oldAmt = new BigDecimal(coreTransLog.getTransAmt()).multiply(new BigDecimal("100"));
        BigDecimal payAmt = new BigDecimal(rJson.optString("transAmt"));
        if(payAmt.compareTo(oldAmt)>=0){
            rJson.put("respCode", "95");
            rJson.put("ret_cd", "95");
            rJson.put("ret_msg", "代付金额不得大于等于原交易金额");
            return rJson;
        }
        if(payAmt.compareTo(new BigDecimal(5000000))>0){
            rJson.put("respCode", "95");
            rJson.put("ret_cd", "95");
            rJson.put("ret_msg", "单笔代付金额不得大于50000元");
            return rJson;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        if(!rJson.optString("inTxnDt").equals(sdf.format(new Date()))){
            rJson.put("respCode", "58");
            rJson.put("ret_cd", "58");
            rJson.put("ret_msg", "只允许代付当天交易");
            return rJson;
        }
        
        DyPayInfo dyPayInfo = new DyPayInfo();
        dyPayInfo.setOrderId(json.getString("id"));
        dyPayInfo = dyPayInfoDao.selectInfoByOrderId(dyPayInfo);
        if(dyPayInfo!=null){
            rJson.put("respCode", "94");
            rJson.put("ret_cd", "94");
            rJson.put("ret_msg", "原交易已代付过");
            return rJson;
        }
        //是否需要判断商户
        if(json.optString("orgNo").equals("100")){
            
        }
        dyPayInfo = new DyPayInfo();
        dyPayInfo.setOrderId(json.getString("id"));
        dyPayInfo.setPayType("4");      //第三方
        dyPayInfo.setAccountName(json.getString("userName"));
        dyPayInfo.setCardNo(json.getString("cardNo"));
        dyPayInfo.setTransAmt(json.getString("transAmt"));
        dyPayInfoDao.insertInfo(dyPayInfo);
//        {"settle_dt":"20161219","req_reserved":"","ret_msg":"","ret_cd":"00","tran_dt_tm":"20161219092338","reserved":""}
        try {
            rJson = payDY(json,"http://122.144.173.166:18080/dyHttpServer/dyAction!dyInterface.action");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("*******doPay resp："
                + rJson.toString());
        rJson.put("respCode", "00");
        rJson.put("id", json.getString("id"));
        System.out.println("infoId="+dyPayInfo.getId());
        
        dyPayInfo.setSettleDt(rJson.optString("settle_dt"));
        dyPayInfo.setTransTime(rJson.optString("tran_dt_tm"));
        dyPayInfo.setRespCode(rJson.optString("ret_cd"));
        dyPayInfo.setRespMsg(rJson.optString("ret_msg"));
        dyPayInfoDao.updateInfoById(dyPayInfo);
        
        return rJson;
    }
	
    
    @Override
    public JSONObject deyiPayQuery(JSONObject rJson) {
        JSONObject json = new JSONObject();
        //代付查询
        json.put("id", rJson.optString("id"));
        json.put("time", rJson.optString("tran_dt_tm"));
        json.put("checkCode",Md5Util.generateMD5String("NTFREEPAY"+json.getString("id")));
        DyPayInfo dyPayInfo = new DyPayInfo();
        dyPayInfo.setOrderId(rJson.optString("id"));
        dyPayInfo = dyPayInfoDao.selectInfoByOrderId(dyPayInfo);
        if(dyPayInfo==null){
            rJson.put("respCode", "96");
            rJson.put("ret_cd", "96");
            rJson.put("ret_msg", "找不到原始交易");
            return rJson;
        }
        try {
            rJson = payDY(json,"http://122.144.173.166:18080/dyHttpServer/dyAction!searchOrder.action");
        } catch (Exception e) {
            e.printStackTrace();
        }
      //接受返回流水存入数据库
        if(rJson.optString("ret_cd").equals("00")){
            //如果不为00则更新状态等
            if(!dyPayInfo.getRespCode().equals("00")){
                dyPayInfo.setSettleDt(rJson.optString("settle_dt"));
                dyPayInfo.setTransTime(rJson.optString("tran_dt_tm"));
                dyPayInfo.setRespCode(rJson.optString("ret_cd"));
                dyPayInfo.setRespMsg(rJson.optString("ret_msg"));
                dyPayInfoDao.updateInfo(dyPayInfo);
            }
        }
        
        return rJson;
        
    }
    
    public JSONObject payDY(JSONObject json,String url) throws Exception{
        DefaultHttpClient httpClient = new DefaultHttpClient();  
        httpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
        HttpPost request = new HttpPost(url);  
         //配置参数
        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();  
        nvps.add(new BasicNameValuePair("message", json.toString())); // 参数
        UrlEncodedFormEntity params =new UrlEncodedFormEntity(nvps, HTTP.UTF_8);
        request.setEntity(params);
        //发送请求
        HttpResponse response = httpClient.execute(request);  
        HttpEntity entity = response.getEntity();  
        InputStream instream = entity.getContent();  
        BufferedReader in = new BufferedReader(new InputStreamReader(instream,"UTF-8"));  
        StringBuffer sb = new StringBuffer();  
        String data = null;  
        while((data = in.readLine())!=null){  
            sb.append(data);  
        }  
        if(in != null)  
            in.close(); 
        System.out.println("return="+sb);
        JSONObject returnJson = JSONObject.fromObject(sb.toString());
        return returnJson;
    }
    
    @Override
    public JSONObject addMerchant(JSONObject json) {
        List<SubMerInfo> subMerInfos = subMerInfoDao.selectSubInfoByPhone(json.optString("phoneNum"));
        if(subMerInfos.size()>0){
            json.put("respCode ", "96");
            json.put("respMsg", "该手机号已注册");
            return json;
        }
        MerTrans merTrans = merTransDao.getMerTransInfo(json.optString("merSysId"));
        BigDecimal fee = new BigDecimal(json.optString("feeRate"));
        if(fee.compareTo(new BigDecimal("0.003"))<0){
            json.put("respCode ", "97");
            json.put("respMsg", "不得低于签约价格");
            return json;
        }
        String merchantId = merTrans
                .getMerSysId()
                .substring(merTrans.getMerSysId().length() - 3)
                + merTrans.getBasicRegion()
                + merTrans.getBasicMcc();// 缺少后4位数的编码
        synchronized (merchantId) {
            SubMerInfo merInfo = new SubMerInfo();
            merInfo.setSubMerId(merchantId);
            String subId = subMerInfoDao.setMaxSubMerId(merInfo);// 获取子商户表当中的相关数据
            Long subMerId = null;// 子商户号
            if (subId != null && !"".equals(subId)) {
                subMerId = Long.parseLong(subId) + 1;
            } else {
                subMerId = Long.parseLong(merchantId + "0001");
            }
            if(insertMerchant(json, "1", subMerId, merTrans)){
                json.put("subMerId", subMerId);
                json.put("respCode ", "00");
                json.put("respMsg", "添加成功");
            }else{
                json.put("respCode ", "96");
                json.put("respMsg", "添加失败");
            }
        }
        return json;
    }
    
    public boolean insertMerchant(JSONObject json,
             String status, Long subMerId,
            MerTrans merTrans) {
        status = "0";
        boolean boo = false;
        SubMerInfo subMerInfo = new SubMerInfo();
        subMerInfo.setSubMerId(String.valueOf(subMerId));
        subMerInfo.setLineNum(json.optString("lineNum"));
        subMerInfo.setOpenBank(json.optString("openBank"));
            String subMerName = json.optString("subMerName");
            String shortName = json.optString("shortName");
            subMerInfo.setSubMerName(subMerName);
            subMerInfo.setShortName(shortName);
            subMerInfo.setLegalPerson(json.optString("legalManName"));
            subMerInfo.setLegalIdcard(json.optString("legalManIdcard"));
            subMerInfo.setRegNo(json.optString("regNo"));
            subMerInfo.setTaxNo(json.optString("taxNo"));
            subMerInfo.setOrganizationCode(json.optString("organizationCode"));
            subMerInfo.setSettAccType(json.optString("settAccType"));
            subMerInfo.setSettAccountName(json.optString("legalManName"));
            subMerInfo.setSettAccountNo(json.optString("settAccountNo"));
            subMerInfo.setSettAgency(json.optString("settAgency"));
            subMerInfo.setStatus(status);
            subMerInfo.setMerSysId(json.optString("merSysId"));
            subMerInfo.setMcc(merTrans.getBasicMcc());
            subMerInfo.setRegion(merTrans.getBasicRegion());
            subMerInfo.setCreateTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
            subMerInfo.setAgentIdL1("-1");
            subMerInfo.setAgentIdL2("-1");
            subMerInfo.setAgentIdL3("-1");
            subMerInfo.setAgentIdL4("-1");
            if ("1".equals(merTrans.getAutoApprove())) {
                subMerInfo.setBillCycle("01");
            } else {
                subMerInfo.setBillCycle("01");
            }
            subMerInfo.setBillStatus("1");
            subMerInfo.setMerKind("个体户");// 默认公司性质
            subMerInfo.setContactor(json.optString("legalManName"));
            subMerInfo.setContactorPhone(json.optString("phoneNum"));
            //公司地址
            subMerInfo.setRegAddr(json.optString("regAddr"));
            int result = subMerInfoDao.insertSubMerInfo(subMerInfo);
            if (result > 0) {
                boo = true;
                Map<String,String> map = new LinkedHashMap<String, String>();  
                map.put("cardType", "1");
                map.put("pmsBankNo", subMerInfo.getLineNum());
                map.put("certNo", subMerInfo.getLegalIdcard());
                map.put("mobile", subMerInfo.getContactorPhone());
                map.put("password", subMerInfo.getContactorPhone());
                map.put("cardNo", subMerInfo.getSettAccountNo());
//                String orderId=DateUtil.getDate("yyMMddHHmmssSSS");
//                map.put("orderNo", orderId);
                map.put("realName", subMerInfo.getSettAccountName());
                map.put("account", subMerInfo.getContactorPhone());
                map.put("mchntName", subMerInfo.getSubMerName());
                map.put("wxT1Fee", json.optString("feeRate"));
                map.put("wxT0Fee", json.optString("feeRate"));
                map.put("alipayT1Fee", json.optString("feeRate"));
                map.put("alipayT0Fee", json.optString("feeRate"));
                String url = "http://cf.rytpay.com.cn/wxpay/v2.0/merchant/regist";
                cfSendScan(map,url);
            }
            return boo;
    }
    
    public JSONObject purchase(JSONObject json){
        SubMerInfo subMerInfo = subMerInfoService.getSubMerInfoById(json.optString("subMerId"));
        OrderInfo orderInfo = null;
        JSONObject rJson = new JSONObject();
        CoreTransInfo coreTransInfo = null;
        try {
            orderInfo = getOrderInfo(subMerInfo, json);
            json.put("orderId", orderInfo.getOrderId());
            json.put("pinData",Des3.PassToData(json.optString("mainKey"), json.optString("pinKey"), json.optString("cardNo"), json.optString("password")));
            coreTransInfo = getPayTransInfo2CoreTransInfo(orderInfo, json);
           // 瑞银自己的通道
//            coreTransInfo = newPayService.doPay(coreTransInfo);
            json.put("respCode", coreTransInfo.getResponseCode());
            json.put("respDesc", coreTransInfo.getResponseDesc());
            if(coreTransInfo.getResponseCode()!=null && coreTransInfo.getResponseCode().equals("00")){
                orderInfo.setOrderStatus("1");
            }else{
                orderInfo.setOrderStatus("2");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (orderInfo != null) {
                orderInfo.setSettleAmt(coreTransInfo.getAmount());
                orderInfo.setSettleDate(coreTransInfo.getSettlement());
                orderInfo.setSettleCur("156");
                orderInfo.setAuthNo(coreTransInfo.getAuthNo()); // 授权号
                orderInfo.setBatchId(coreTransInfo.getBatchNo()); // 批次号
                orderInfo.setVoucherNo(coreTransInfo.getTrackingNo()); // 凭证号
                orderInfo.setRefferNo(coreTransInfo.getReferenceNo()); // 参考号
                orderInfo.setRespCode(coreTransInfo.getResponseCode()); // 返回状态码
                orderInfo.setRespDesc(coreTransInfo.getResponseDesc()); // 返回描述
                orderInfo.setFinishTime(DateUtil
                        .getDateFormatStr("yyyy-MM-dd HH:mm:ss"));
                orderInfoDao.updateOrder(orderInfo);
                }
        }
        return rJson;
    }
    
    public CoreTransInfo getPayTransInfo2CoreTransInfo(
            OrderInfo orderInfo,JSONObject json) throws Exception {
        CoreTransInfo coreTransInfo = new CoreTransInfo();
        coreTransInfo.setAmount(orderInfo.getMerAmt());
        coreTransInfo.setChMerId(orderInfo.getTransMerId());
        coreTransInfo.setChTermId(orderInfo.getTransTerId());
        coreTransInfo.setDcData(json.optString("dcData"));
        coreTransInfo.setMerId(orderInfo.getSubMerId());
        coreTransInfo.setMerName("外部交易");
        coreTransInfo.setMessageType("0200");
        coreTransInfo.setOrderId(orderInfo.getOrderId());
        coreTransInfo.setPan(orderInfo.getCardNo());
        coreTransInfo.setPhone(json.optString("phone"));
        coreTransInfo.setPinData(json.optString("pinData"));
        coreTransInfo.setProcessingCode("000000");
        coreTransInfo.setReferenceNo(getReferenceNo(orderInfo.getOrderId()));
        coreTransInfo.setServiceConditionCode("00");
        if (StringUtils.isNotEmpty(json.optString("dcData"))) {
            coreTransInfo.setServiceEntryModeCode("051");
        } else {
            coreTransInfo.setServiceEntryModeCode("021");
        }
        coreTransInfo.setServicePinCaptureCode("06");
        coreTransInfo.setTerminalNo(orderInfo.getTransTerId());
        coreTransInfo.setTrack2Data(json.optString("track2Data"));
        coreTransInfo.setTrack3Data(json.optString("track3Data"));
        coreTransInfo.setTrackingNo(orderInfo.getOrderId().substring(orderInfo.getOrderId().length()-6,orderInfo.getOrderId().length()));
        coreTransInfo.setTransKey("EECAB5B3DD477688EECAB5B3DD477688");
        coreTransInfo.setTransSource("201");
        coreTransInfo.setTransType("1002"); 
        coreTransInfo.setBatchNo("000001");
        // coreTransInfo.setUserDefined1("000001"); //域 60
        coreTransInfo.setUserName(json.optString("userName"));
        coreTransInfo.setIdNumber(json.optString("idNum"));
        coreTransInfo.setPhone(json.optString("phone"));
        coreTransInfo.setPinData(Des3.handleSens(coreTransInfo));
        // coreTransInfo.setCardSeriNo(payTransInfo.ge);
        // coreTransInfo.setUserDefined2(payTransInfo.geto); //域 61
        // coreTransInfo.setUserDefined3(userDefined3);

        // 测试数据
        // CoreTransInfo coreTransInfo = new CoreTransInfo();
        // coreTransInfo.setTransType("1001");
        // coreTransInfo.setMessageType("0200");
        // coreTransInfo.setProcessingCode("310000");
        // coreTransInfo.setServiceEntryModeCode("021");
        // coreTransInfo.setServicePinCaptureCode("12");
        // coreTransInfo.setTrack2Data("6212790200001797=12015208084198109646");
        // coreTransInfo.setChMerId("857410073116001");
        // coreTransInfo.setChTermId("90039149");
        // coreTransInfo.setMerId("100000001012900");
        // coreTransInfo.setTerminalNo("00012900");
        // coreTransInfo.setCurrencyCode("156");
        // coreTransInfo.setPinData("67ED1F3096578464");
        // coreTransInfo.setUserDefined1("010000010000");
        return coreTransInfo;
    }
    
    public String getReferenceNo(String orderId){
        int orderLength = orderId.length();
        String referenceNo="";
        for(int i =0;i<(11-orderLength);i++){
            referenceNo+="0";
        }
         return "2"+referenceNo+orderId;
    }
    
    public OrderInfo getOrderInfo(SubMerInfo subMerInfo,JSONObject json) throws Exception {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setAgentIdL1(subMerInfo.getAgentIdL1());
        orderInfo.setAgentIdL2(subMerInfo.getAgentIdL2());
        orderInfo.setAgentIdL3(subMerInfo.getAgentIdL3());
        orderInfo.setAgentIdL4(subMerInfo.getAgentIdL4());
        orderInfo.setAgentIdL5(subMerInfo.getAgentIdL5());
        orderInfo.setAgentIdL6(subMerInfo.getAgentIdL6());
        orderInfo.setAgentIdL7(subMerInfo.getAgentIdL7());
        orderInfo.setAgentIdL8(subMerInfo.getAgentIdL8());
        orderInfo.setAgentIdL9(subMerInfo.getAgentIdL9());
        orderInfo.setAgentIdL10(subMerInfo.getAgentIdL10());
        
        orderInfo.setTerminalId(json.optString("terminalId"));// 终端号
        orderInfo.setTransMerId(json.optString("transMerId")); // 交易商户号
        orderInfo.setTerminalType("04");
        orderInfo.setCurrency("156"); // 币种默认人民币156
        orderInfo.setOrderFee("0"); // 手续费
        orderInfo.setMerAmt(json.optString("transAmt"));
        orderInfo.setBusType("05");
        orderInfo.setTransType("01");
        orderInfo.setMerOrderId(json.optString("merOrderId"));      //商户流水
        orderInfo.setMerOrderTime(json.optString("merOrderTime"));      //商户交易时间
        // orderInfo.setISSUE_BANK_NAME(balanceInfo);
        orderInfo.setOrderDesc("消费,支付");
        orderInfo.setOrderOverTime("0");
//      orderInfo.setCARD_NO(TransUtil.captureCardNumber(balanceInfo
//              .getCardNo()));
        orderInfo.setCardNo(json.optString("cardNo"));      //卡号
        orderInfo.setFullCardNo(json.optString("cardNo"));      
        orderInfo.setCardType(json.optString("cardType"));      //1借记卡 2贷记卡
        
        orderInfo.setIssueBankId(json.optString("issueBankId"));        //银行代码
        orderInfo.setIssueBankName(json.optString("issueBankName"));        //银行名称
        orderInfo.setReserved(json.optString("reserved"));      //备注
        orderInfo.setRefundStatus("");
        orderInfo.setBackUrl("");
        // Date date = new Date();
        orderInfo.setTransTime(DateUtil.getDateFormatStr("yyyyMMddHHmmss"));
        orderInfo.setCreateTime(DateUtil.getDateFormatStr("yyyy-MM-dd HH:mm:ss"));
        orderInfo.setUserMobile("");
        orderInfo.setSubMerId(json.optString("subMerId"));          //商户号
        orderInfo.setMerSysId(json.optString("merSysId"));          //机构号
        orderInfo.setOrderStatus("0");
        orderInfo.setSignStatus(9);
        TractInfo tractInfo = new TractInfo();
        tractInfo.setTransMerId(json.optString("transMerId"));          //渠道商户号
        tractInfo = tractInfoDao.selectTractByMerId(json.optString("transMerId"));
        orderInfo.setTransMerName(tractInfo.getTractName());
        orderInfo.setTransMerId(tractInfo.getTransMerId());
        orderInfo.setTransTerId(tractInfo.getTerminalId());
        orderInfo.setOrderRateType("01");
        String orderId;
        orderId = procedureDao.getOrderId();
        orderInfo.setOrderId(orderId);
        int i = orderInfoDao.insertOrderInfo(orderInfo);
        return orderInfo;

    }

    //扫码付 下单
    @Override
    public JSONObject scanPay(JSONObject json) {
        String merId = json.optString("merId");
        SubMerInfo subMerInfo = new SubMerInfo(); 
        subMerInfo.setSubMerId(merId);
        subMerInfo =  subMerInfoDao.findById(subMerInfo);
        String orderId=DateUtil.getDate("yyMMddHHmmssSSS");

        Map<String,String> map = new LinkedHashMap<String, String>();  
        map.put("source", json.optString("source"));
        BigDecimal amount = new BigDecimal(json.optString("txamt"));
        BigDecimal transFee = new BigDecimal(0.0038);
        String fee = String.valueOf(mul(amount.doubleValue(), transFee.doubleValue())).replaceAll(".0", "");
        
        map.put("settleAmt", fee);
        map.put("account", json.optString("phone"));
        map.put("amount", json.optString("txamt"));
        map.put("notifyUrl", "http://manager.1-town.cn:8088/bpbmtm/clientAction!pradePayBack.ac");
        map.put("tranTp", "0");
        map.put("orgOrderNo", orderId);
        
        ScanOrderInfo scanOrderInfo = new ScanOrderInfo();
        scanOrderInfo.setOrderId(orderId);
        scanOrderInfo.setMerSysId(json.optString("merSysId"));
        scanOrderInfo.setOrderType("01");       //扫码支付
        scanOrderInfo.setCurrency(json.optString("currency"));
        scanOrderInfo.setTxamt(json.optString("txamt"));
        scanOrderInfo.setUserId(Long.parseLong(merId));
        scanOrderInfo.setChcd(json.optString("chcd"));
        scanOrderInfo.setStatus("1");           //初始订单
        scanOrderInfo.setScanMerId(subMerInfo.getScanMerId());
        scanOrderInfo.setScanMerSign(subMerInfo.getScanMerSign());
        scanOrderInfoDao.insertInfo(scanOrderInfo);
        String url = "http://cf.rytpay.com.cn/wxpay/v5.0/order/fixedQrpay";
        JSONObject rJson = cfSendScan(map,url);
        scanOrderInfo.setQrcode(rJson.optString("qrcode"));
        scanOrderInfo.setTransTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
        scanOrderInfo.setRespcd(rJson.optString("respcd"));
        scanOrderInfo.setRespmsg(rJson.optString("errorDetail"));
        scanOrderInfoDao.updateInfo(scanOrderInfo);
        return rJson;
        
    }
    
  //扫码付 预下单     迅联
    /*@Override
    public JSONObject pradePay(JSONObject json) {
        Map<String,String> map=new HashMap<String,String>();
        MobileUser mobileUser = new MobileUser();
        mobileUser.setLoginName(json.optString("phone"));
        mobileUser = mobileUserDao.getMobileUser(mobileUser);
        
        SubMerInfo subMerInfo = new SubMerInfo(); 
        subMerInfo.setSubMerId(mobileUser.getSubMerId());
        subMerInfo =  subMerInfoDao.findById(subMerInfo);
        String orderId=DateUtil.getDate("yyMMddHHmmssSSS");
        map.put("version", "2.1");
        map.put("signType", "SHA256");
        map.put("charset", "utf-8");
        map.put("orderNum", orderId);
        map.put("txndir", "Q");
        map.put("busicd", "PAUT");
        map.put("inscd", "92171888");     //机构号
        map.put("chcd", json.optString("chcd"));
        map.put("mchntid", subMerInfo.getScanMerId());     //商户号
        map.put("terminalid", "00000001");     //终端号
        map.put("txamt", addZero(true, json.optString("txamt"), 12, "0"));     //交易金额 1d2字符，单位为分，左补0
        map.put("currency", json.optString("currency"));     
        map.put("backUrl", "http://manager.1-town.cn:8088/bpbmtm/clientAction!pradePayBack.ac");     //回调函数
//        map.put("goodsList", json.optString("goodsList"));     
        map.put("outOrderNum", orderId);     //建议商家使⽤此字段来标示商家内部订单信息，此字段系统会原样返回，并且会在流件中体现（请不要填⼊特殊字符）
        //排序
        Collection<String> keyset= map.keySet();   
        List list=new ArrayList<String>(keyset);  
        Collections.sort(list);  
        //这种打印出的字符串顺序和微信官网提供的字典序顺序是一致的  
        String stringSignTemp = "";
        for(int i=0;i<list.size();i++){  
            stringSignTemp+=list.get(i)+"="+map.get(list.get(i));
            if(i!=list.size()-1){
                stringSignTemp+="&";
            }
        } 
//        stringSignTemp+="zsdfyreuoyamdphhaweyrjbvzkgfdycs";
        stringSignTemp+=subMerInfo.getScanMerSign();
        //制作签名
        String sign = StringEncrypt.Encrypt(stringSignTemp, "");
        map.put("sign", sign);
        JSONObject paramJson = JSONObject.fromObject(map);
        System.out.println("paramJson="+paramJson.toString());
        
        ScanOrderInfo scanOrderInfo = new ScanOrderInfo();
        scanOrderInfo.setOrderId(orderId);
        scanOrderInfo.setOrderType("02");       //预下单
        scanOrderInfo.setCurrency(json.optString("currency"));
        scanOrderInfo.setTxamt(json.optString("txamt"));
        scanOrderInfo.setUserId(mobileUser.getId());
        scanOrderInfo.setChcd( json.optString("chcd"));
        scanOrderInfo.setStatus("1");           //初始订单
        scanOrderInfo.setScanMerId(subMerInfo.getScanMerId());
        scanOrderInfo.setScanMerSign(subMerInfo.getScanMerSign());
        scanOrderInfoDao.insertInfo(scanOrderInfo);
        JSONObject rJson = sendScan(paramJson);
        scanOrderInfo.setQrcode(rJson.optString("qrcode"));
        scanOrderInfo.setTransTime(rJson.optString("transTime"));
        scanOrderInfo.setRespcd(rJson.optString("respcd"));
        scanOrderInfo.setRespmsg(rJson.optString("errorDetail"));
        scanOrderInfoDao.updateInfo(scanOrderInfo);
        return rJson;
       } */

    /**e
     * 生产二维码 民生
     */
    
    /*@Override
    public JSONObject pradePay(JSONObject json) {
        MobileUser mobileUser = new MobileUser();
        mobileUser.setLoginName(json.optString("phone"));
        mobileUser = mobileUserDao.getMobileUser(mobileUser);
        
        SubMerInfo subMerInfo = new SubMerInfo(); 
        subMerInfo.setSubMerId(mobileUser.getSubMerId());
        subMerInfo =  subMerInfoDao.findById(subMerInfo);
        String orderId=DateUtil.getDate("yyMMddHHmmssSSS");
        Map<String,String> map = new LinkedHashMap<String, String>();  
        map.put("version", "1.0.0");
        map.put("transType", "1007");
        map.put("merId", subMerInfo.getScanMerId());//8088000000000323
        map.put("transDate", DateUtil.getDate("yyyyMMdd"));
        map.put("transTime", DateUtil.getDate("HHmmss"));
        map.put("orderNo", orderId);
        map.put("curyId", json.optString("currency"));
        map.put("transAmt", json.optString("txamt"));
        String chcd = "";
        if(json.optString("chcd").equals("WXP")){
            chcd="04";
            map.put("tradeType", "NATIVE");
//        map.put("appid", value);
//        map.put("openid", value);
        }else if(json.optString("chcd").equals("ALP")){
            chcd="03";
        }
        map.put("termType", chcd);
        map.put("priv1", subMerInfo.getScanMerId());
        map.put("notifyUrl", "http://manager.1-town.cn:8088/bpbmtm/clientAction!pradePayBack.ac");     //回调函数
        
        ScanOrderInfo scanOrderInfo = new ScanOrderInfo();
        scanOrderInfo.setOrderId(orderId);
        scanOrderInfo.setOrderType("02");       //预下单
        scanOrderInfo.setCurrency(json.optString("currency"));
        scanOrderInfo.setTxamt(json.optString("txamt"));
        scanOrderInfo.setUserId(mobileUser.getId());
        scanOrderInfo.setChcd(json.optString("chcd"));
        scanOrderInfo.setStatus("1");           //初始订单
        scanOrderInfo.setScanMerId(subMerInfo.getScanMerId());
        scanOrderInfo.setScanMerSign(subMerInfo.getScanMerSign());
        scanOrderInfoDao.insertInfo(scanOrderInfo);
        
        JSONObject rJson = msSendScan(map);
        scanOrderInfo.setQrcode(rJson.optString("payLink"));
        scanOrderInfo.setTransTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
        scanOrderInfo.setRespcd(rJson.optString("orderCode"));
        scanOrderInfo.setRespmsg(rJson.optString("orderDesc"));
        scanOrderInfoDao.updateInfo(scanOrderInfo);
        return rJson;
    }*/
    
    /**
     * 扫码下单 翰鑫
     */
    @Override
    public JSONObject pradePay(JSONObject json) {
        MobileUser mobileUser = new MobileUser();
        mobileUser.setLoginName(json.optString("phone"));
        mobileUser = mobileUserDao.getMobileUser(mobileUser);
        String merId = "";
        if(mobileUser!=null){
            merId = mobileUser.getSubMerId();
        }else{
            merId = json.optString("merId");
        }
        
        SubMerInfo subMerInfo = new SubMerInfo(); 
        subMerInfo.setSubMerId(merId);
        subMerInfo =  subMerInfoDao.findById(subMerInfo);
        String orderId=DateUtil.getDate("yyMMddHHmmssSSS");

        Map<String,String> map = new LinkedHashMap<String, String>();  
        if(json.optString("chcd").equals("WXP")){
            map.put("source", "0");
        }else if(json.optString("chcd").equals("ALP")){
            map.put("source", "1");
        }
        BigDecimal amount = new BigDecimal(json.optString("txamt"));
        BigDecimal transFee = new BigDecimal(0.0038);
        String fee = String.valueOf(mul(amount.doubleValue(), transFee.doubleValue())).replaceAll(".0", "");
        
        map.put("settleAmt", fee);
        map.put("account", json.optString("phone"));
        map.put("amount", json.optString("txamt"));
        map.put("notifyUrl", "http://manager.1-town.cn:8088/bpbmtm/clientAction!pradePayBack.ac");
        map.put("tranTp", "0");
        map.put("orgOrderNo", orderId);
        
        ScanOrderInfo scanOrderInfo = new ScanOrderInfo();
        scanOrderInfo.setOrderId(orderId);
        scanOrderInfo.setMerSysId(json.optString("merSysId"));
        scanOrderInfo.setOrderType("02");       //预下单
        scanOrderInfo.setCurrency(json.optString("currency"));
        scanOrderInfo.setTxamt(json.optString("txamt"));
        scanOrderInfo.setUserId(Long.parseLong(merId));
        scanOrderInfo.setChcd(json.optString("chcd"));
        scanOrderInfo.setStatus("1");           //初始订单
        scanOrderInfo.setScanMerId(subMerInfo.getScanMerId());
        scanOrderInfo.setScanMerSign(subMerInfo.getScanMerSign());
        scanOrderInfoDao.insertInfo(scanOrderInfo);
        String url = "http://cf.rytpay.com.cn/wxpay/v5.0/order/qrpay";
        JSONObject rJson = cfSendScan(map,url);
        scanOrderInfo.setQrcode(rJson.optString("qrcode"));
        scanOrderInfo.setTransTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
        scanOrderInfo.setRespcd(rJson.optString("respcd"));
        scanOrderInfo.setRespmsg(rJson.optString("errorDetail"));
        scanOrderInfoDao.updateInfo(scanOrderInfo);
        rJson.put("orderId", orderId);
        return rJson;
        
    }
    
    public static double mul(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        BigDecimal result = b1.multiply(b2);
        // return Double.valueOf(result.toString());
        return roundFee(result.doubleValue(), 0);

    }
    /**
     * 计算手续费的时候向上取整
     * @param v
     * @param scale
     * @return
     */
    public static double roundFee(double v, int scale)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_UP).doubleValue();
    }
    
    /**
     * 银联快捷
     */
    @Override
    public JSONObject yinlianPay(JSONObject json) {
        MobileUser mobileUser = new MobileUser();
        mobileUser.setLoginName(json.optString("phone"));
        mobileUser = mobileUserDao.getMobileUser(mobileUser);
        String merId = "";
        if(mobileUser!=null){
            merId = mobileUser.getSubMerId();
        }else{
            merId = json.optString("merId");
        }
        SubMerInfo subMerInfo = new SubMerInfo(); 
        subMerInfo.setSubMerId(merId);
        subMerInfo =  subMerInfoDao.findById(subMerInfo);
        String orderId=DateUtil.getDate("yyMMddHHmmssSSS");
     
        ScanOrderInfo scanOrderInfo = new ScanOrderInfo();
        scanOrderInfo.setOrderId(orderId);
//        scanOrderInfo.setOrderType("03");       //银联快捷
        scanOrderInfo.setOrderType(json.optString("orderType"));       //银联快捷03   封顶订单06
        scanOrderInfo.setCurrency(json.optString("currency"));
        scanOrderInfo.setTxamt(json.optString("txamt"));
        scanOrderInfo.setMerSysId(json.optString("merSysId"));
        scanOrderInfo.setUserId(Long.parseLong(merId));
        scanOrderInfo.setChcd(json.optString("chcd"));
        scanOrderInfo.setStatus("1");           //初始订单
        scanOrderInfoDao.insertInfo(scanOrderInfo);
        
        JSONObject rJson = new JSONObject();
        rJson.put("transactionId", orderId);
        rJson.put("payeeBankName", subMerInfo.getSettAgency()+subMerInfo.getOpenBank());
        rJson.put("payeeAcc", subMerInfo.getSettAccountNo());
        rJson.put("payeeIdNum", subMerInfo.getLegalIdcard());
        rJson.put("payeeName", subMerInfo.getSettAccountName());
        SubMerRate subMerRate = new SubMerRate();
        subMerRate.setSubMerId(mobileUser.getSubMerId());
        subMerRate = subMerRateDao
                .getSubMerRateBySubMerId(subMerRate);        
        if(json.optString("orderType").equals("03")){
            if(subMerRate.getTransRateH().equals("-1")){
                rJson.put("feeRate", "0.4");
            }else{
                rJson.put("feeRate",subMerRate.getTransRateH());
            }
        }else{
         // 子商户交易费率
            if(subMerRate.getTransHighestFee().equals("-1")){
                rJson.put("feeRate","4000");
            }else{
                rJson.put("feeRate",subMerRate.getTransHighestFee());
            }
        }
        
        rJson.put("bgUrl", "http://manager.1-town.cn:8088/bpbmtm/clientAction!yinLianPayBack.ac");
        
        return rJson;
    }
    
    public void yinlianPayment(JSONObject rJson,String transCode){
        String aesKey="0t797Yw1RjWBUHVvoBAG+w==";
        String privateKey = "";
        String jsonStr = rJson.toString();
        System.out.println("");
        
        DefaultHttpClient httpClient = new DefaultHttpClient();  
        HttpPost request = new HttpPost("http://183.60.125.17:18533/rdPay/payProcess");  
        try {
            String signData = RSACoder.yinLianSign(jsonStr, privateKey, "UTF-8");
            String businessContext = Base64.encodeBase64String(AESCoder.encrypt(signData.getBytes("UTF-8"), aesKey));
                //配置参数
                List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();  
                nvps.add(new BasicNameValuePair("version", "1.0")); // 参数
                nvps.add(new BasicNameValuePair("merId", "0000000000000120")); 
                nvps.add(new BasicNameValuePair("orderTime", DateUtil.getDate("yyyyMMddHHmmss"))); 
                nvps.add(new BasicNameValuePair("transCode", transCode));   //T01002
                nvps.add(new BasicNameValuePair("signType", "RSA"));  
                nvps.add(new BasicNameValuePair("charse", "UTF-8"));  
                nvps.add(new BasicNameValuePair("businessContext", businessContext));  
                UrlEncodedFormEntity params =new UrlEncodedFormEntity(nvps, HTTP.UTF_8);
                request.setEntity(params);
                //发送请求
                HttpResponse response = httpClient.execute(request);  
                HttpEntity entity = response.getEntity();  
                InputStream instream = entity.getContent();  
                BufferedReader in = new BufferedReader(new InputStreamReader(instream,"UTF-8"));  
                StringBuffer sb = new StringBuffer();  
                String data = null;  
                while((data = in.readLine())!=null){  
                    sb.append(data);  
                }  
                if(in != null)  
                    in.close(); 
                System.out.println("return="+sb);
        }catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public static String addZero(boolean left,String text,int length,String add){
        String addString="";
           for(int i=0;i<(length-text.length());i++){
               addString+=add;
           }
        if(left){
            return addString+text;
        }else{
            return text+addString;
        }
    }
   
    public static JSONObject sendScan(JSONObject json){
        DefaultHttpClient httpClient = new DefaultHttpClient();  
        try {
//            HttpPost request = new HttpPost("http://test.quick.ipay.so/scanpay/unified");  //test
            HttpPost request = new HttpPost("https://showmoney.cn/scanpay/unified");  //生产环境
            StringEntity params =new StringEntity(json.toString(),"UTF-8");  
            request.addHeader("content-type", "application/json");  
            request.setEntity(params);  
            HttpResponse response = httpClient.execute(request);  
            HttpEntity entity = response.getEntity();  
            InputStream instream = entity.getContent();  
            BufferedReader in = new BufferedReader(new InputStreamReader(instream,"UTF-8"));  
            StringBuffer sb = new StringBuffer();  
            String data = null;  
            while((data = in.readLine())!=null){  
                sb.append(data);  
            }  
            if(in != null)  
                in.close(); 
            System.out.println(sb);
            JSONObject rJson=JSONObject.fromObject(sb.toString());
            return rJson;
        } catch (Exception e) {
           e.printStackTrace();
           return null;
        }
    }
    
    @Override
    public void pradePayBack(JSONObject json) {
        ScanOrderInfo scanOrderInfo = new ScanOrderInfo();
        scanOrderInfo.setOrderId(json.optString("orderNum"));
        scanOrderInfo = scanOrderInfoDao.selectInfoByOrderId(scanOrderInfo);
        scanOrderInfo.setRespcd(json.optString("respcd"));
        scanOrderInfo.setRespmsg(json.optString("errorDetail"));
        scanOrderInfoDao.updateInfo(scanOrderInfo);
    }
    
    @Override
    public JSONObject pradePayList(JSONObject json){
        String page = json.optString("page");
        String pageSize = json.optString("pageSize");
        MobileUser mobileUser = new MobileUser();
        mobileUser.setLoginName(json.optString("phone"));
        mobileUser = mobileUserDao.getMobileUser(mobileUser);
        String merId = "";
        if(mobileUser!=null){
            merId = mobileUser.getSubMerId();
        }else{
            merId = json.optString("merId");
        }
        Map map = PageUtil.getPageMap(Integer.parseInt(page), Integer.parseInt(pageSize));
        
        ScanOrderInfo scanOrderInfo = new ScanOrderInfo();
        scanOrderInfo.setUserId(Long.parseLong(merId));
        scanOrderInfo.setOrderId(json.optString("orderId"));
        map.put("scanOrderInfo", scanOrderInfo);
        int count = scanOrderInfoDao.countScanOrderInfo(map);
        List<ScanOrderInfo> scanOrderInfoList = scanOrderInfoDao.selectScanOrderInfo(map);
        JSONArray jsonarray = JSONArray.fromObject(scanOrderInfoList);  
        Map<String, String> jsonMap = new HashMap<String, String>();
        jsonMap.put("count",count+"");
        jsonMap.put("respCode", "0000");
        jsonMap.put("respMsg", ValueTool.SYS_CODE.get("0000"));
        String jsonString = TmallYfDataPackage.convertObjectToJson(jsonMap);
        json=JSONObject.fromObject(jsonString);
        json.element("scanOrderInfoList", jsonarray);
        return json;
    }
    
    @Override
    public JSONObject driverBussiness(JSONObject json){
        String transCode =  json.optString("transCode");
        MobileUser mobileUser = new MobileUser();
        mobileUser.setLoginName(json.optString("phone"));
        mobileUser = mobileUserDao.getMobileUser(mobileUser);
        if(transCode.equals("0000")){
            //添加车辆信息
            Map<String,String> map = new HashMap<String, String>();
            map.put("carorg", json.optString("carorg"));
            map.put("engineno", json.optString("engineno"));
            map.put("frameno", json.optString("frameno"));
            map.put("lsnum", json.optString("lsnum"));
            map.put("lsprefix", json.optString("lsprefix"));
            JSONObject rJson = new JSONObject();
            String respCode = rJson.optString("status");
            if(respCode.equals("0")){
                //存入数据库 并将最近的 记录反出去
                DriverInfo driverInfo = new DriverInfo();
                driverInfo.setCarorg(json.optString("carorg"));
                driverInfo.setEngineno(json.optString("engineno"));
                driverInfo.setFrameno(json.optString("frameno"));
                driverInfo.setLsnum(json.optString("lsnum"));
                driverInfo.setLsprefix(json.optString("lsprefix"));
                driverInfo.setLstype(json.optString("lstype"));
                driverInfo.setSubMerId(mobileUser.getSubMerId());
                driverInfo.setUserId(mobileUser.getId()+"");
                driverInfoDao.insertDriverInfo(driverInfo);
                rJson.put("respCode", "0000");
            }else{
                rJson.put("respCode", "9998");
            }
            return rJson;
        }
        return json;
    }
    
    @Override
    public JSONObject scanRegist(JSONObject json){
        String merchantId = "";// 商户号
        MobileUser mobileUser = new MobileUser();
        mobileUser.setLoginName(json.optString("phone"));
        mobileUser = mobileUserDao.getMobileUser(mobileUser);
        // 获取子商户设备信息
        SubMerTerminal terminal = new SubMerTerminal();
        terminal.setTsn(json.optString("tsn"));
        SubMerTerminal subMerTerminal = subMerTerminalDao.selectSubMerTerminalByTerminalId(terminal);
        JSONObject rJson = new JSONObject();
        try {
            if(subMerTerminal!=null){
                if(!subMerTerminal.getStatus().equals("0")){
                    rJson.put("respCode", "9998");
                    rJson.put("respMsg", "序列号已使用");
                }else{
                    MerTrans merTrans = merTransDao.getMerTransInfo(subMerTerminal.getMerSysId());
                    merchantId = merTrans
                            .getMerSysId()
                            .substring(merTrans.getMerSysId().length() - 3)
                            + merTrans.getBasicRegion()
                            + merTrans.getBasicMcc();// 缺少后4位数的编码
                    Long subMerId = null;// 子商户号
                 // 一次只能有一个线程进入，将子商户号组装完整
                    synchronized (merchantId) {
                        SubMerInfo merInfo = new SubMerInfo();
                        merInfo.setSubMerId(merchantId);
                        String subId = subMerInfoDao.setMaxSubMerId(merInfo);// 获取子商户表当中的相关数据
                        if (subId != null && !"".equals(subId)) {
                            //大于15位说明没用过卡头
                            if(json.optString("merchantId").trim().length()>15){
                                subMerId = Long.parseLong(subId) + 1;
                            }else{
                                subMerId = Long.parseLong(json.optString("merchantId"));
                            }
                        } else {
                            subMerId = Long.parseLong(merchantId + "0001");
                        }
                        AccountRegist regist = new AccountRegist();
                        regist.setMerchantId(json.optString("merchantId"));
                        updateSubMerInfo(regist, subMerTerminal, "0", subMerId, merTrans);
                     // 添加子商户交易费率信息
                        insertSubMerRateInfo(merTrans, String.valueOf(subMerId), subMerTerminal);
                        // 添加子商户交易配置信息
                        insertSubMerTransInfo(merTrans, String.valueOf(subMerId), regist);
                        // 修改子商户设备信息
                        updateMoblieUser(regist, String.valueOf(subMerId));
                        SubMerTerminal smt = new SubMerTerminal();
                        smt.setTsn(json.optString("tsn"));
                        // smt.setFactory(regist.getFactoryId());
                        smt.setSubMerId("-1");
//                        smt.setLoginName(json.optString("phone"));
//                        smt.setLoginPwd(json.optString("pwd"));
                        smt.setStatus("1");
                        // 修改子商户设备信息
                        subMerTerminalDao.updateSubMerTerminal(smt);
                        rJson.put("respCode", "0000");
                        rJson.put("respMsg", "序列号使用成功");
                        //激活成功 报备商户走扫码
                        
                    }
                }
            }else{
                rJson.put("respCode", "9998");
                rJson.put("respMsg", "序列号错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus()
                    .setRollbackOnly();
        }
        return rJson;
    }
    
    public JSONObject sendDriver(Map<String,String> map){
        String host = "http://jisuqgclwz.market.alicloudapi.com";
        String path = "/illegal/query";
        String method = "GET";
        String appcode = "a24d435895674d74af5c984907163a49";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
//        querys.put("carorg", "jiangsu");
//        querys.put("engineno", "182309");
//        querys.put("frameno", "020023");
//        querys.put("iscity", "1");
//        querys.put("lsnum", "F819UC");
//        querys.put("lsprefix", "苏");
        querys.put("carorg", map.get("carorg"));
        querys.put("engineno", map.get("engineno"));
        querys.put("frameno", map.get("frameno"));
        querys.put("iscity", "1");
        querys.put("lsnum", map.get("lsnum"));
        querys.put("lsprefix", map.get("lsprefix"));
//        querys.put("lstype", "02");
        try {
            /**
            * 重要提示如下:
            * HttpUtils请从
            * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
            * 下载
            *
            * 相应的依赖请参照
            * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
            */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println("resp===============");
            HttpEntity entity = response.getEntity();  
            InputStream instream = entity.getContent();  
            BufferedReader in = new BufferedReader(new InputStreamReader(instream,"UTF-8"));  
            StringBuffer sb = new StringBuffer();  
            String data = null;  
            while((data = in.readLine())!=null){  
                sb.append(data);  
            }  
            if(in != null)  
                in.close(); 
            System.out.println("resp=================");
            System.out.println(sb.toString());
            JSONObject json = JSONObject.fromObject(sb.toString());
            //获取response的body
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    
    }
    
    public static void driveTest(){

        String host = "http://jisuqgclwz.market.alicloudapi.com";
        String path = "/illegal/query";
        String method = "GET";
        String appcode = "a24d435895674d74af5c984907163a49";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("carorg", "jiangsu");
        querys.put("engineno", "182309");
        querys.put("frameno", "020023");
        querys.put("iscity", "1");
        querys.put("lsnum", "F819UC");
        querys.put("lsprefix", "苏");
//        querys.put("lstype", "02");


        try {
            /**
            * 重要提示如下:
            * HttpUtils请从
            * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
            * 下载
            *
            * 相应的依赖请参照
            * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
            */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println("resp===============");
            HttpEntity entity = response.getEntity();  
            InputStream instream = entity.getContent();  
            BufferedReader in = new BufferedReader(new InputStreamReader(instream,"UTF-8"));  
            StringBuffer sb = new StringBuffer();  
            String data = null;  
            while((data = in.readLine())!=null){  
                sb.append(data);  
            }  
            if(in != null)  
                in.close(); 
            System.out.println("resp=================");
            System.out.println(sb.toString());
            //获取response的body
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    
  //扫码报备接口
    @Override
    public JSONObject getScanMer(JSONObject json){
        String merId =  json.optString("merId");
        SubMerInfo subMerInfo = subMerInfoDao.getSubMerInfoById(merId);
        Map<String,String> map = new LinkedHashMap<String, String>();  
        map.put("version", "1.0.0");
        map.put("transType", "1006");
        map.put("termType", "");
        map.put("name",subMerInfo.getSubMerName());
        map.put("shortName", subMerInfo.getShortName());
        map.put("tel", subMerInfo.getContactorPhone());
        map.put("business", "56");
        String customerInfo=subMerInfo.getSettAccountNo()+"|"+subMerInfo.getSettAccountName()+"|01|"+subMerInfo.getContactorPhone()+"|||";
        map.put("billCycle", "02");     //01:T+1 02:T+0
        map.put("customerInfo",customerInfo);     
        map.put("fee0", "0.38");
        map.put("fee1", "0.38");
        map.put("fee2", "0.38");
        map.put("fee3", "0.38");
        JSONObject rJson = msSendScan(map);
        JSONObject backJson = new JSONObject();
        if(rJson!=null){
            String scanMerId = rJson.optString("merId");
            String code = rJson.optString("code");
            String msg = rJson.optString("msg");
            if(code.equals("0000")){
                subMerInfo.setScanMerId(scanMerId);
                subMerInfoDao.update(subMerInfo);
                backJson.put("respCode", "0000");
                backJson.put("respMsg","开通扫码成功");
            }else{
                backJson.put("respCode", "9998");
                backJson.put("respMsg",msg);
            }
        }else{
            backJson.put("respCode", "9998");
            backJson.put("respMsg", "开通扫码失败");
        }
        return backJson;
    }
    
    
    
    /**
     * 民生扫码发送
     * @Title:        msSendScan 
     * @Description:  
     * @param:        @param subMerInfo    
     * @return:       void    
     * @throws 
     * @author        Eason Jiang
     * @Date          2017-5-31 下午4:05:16
     */
    public JSONObject msSendScan(Map<String,String> map){
        String url = "http://140.207.38.90:41501/pay/api.do";
        String supMerId="8088000000000001";
        String rsaKey="OPJURIZFIWZWJIFDNXUXWSMFILOCLJQK";
        String signKey="MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALHSQP5Hq39D/nU4Xa2XJZxfaHNx+InSNjjynpvMe0ejuYuf7ghbOC3TvYqeDNoesY3lFr/5Sh33qb+Es46jow1VlGPJJBkZ/KXzV/uqRO9rB5rWiGdCGzPbKby719bvXnIIVJBe86gLPHXrjHABwQUkjaoIQFHJmdTpcwYFwZk/AgMBAAECgYAIN/SDDCL0Bdt75XgG7uZxHMPCGjFnhUy2QxhrkP7dp8aKmoCw6C5nh9LJ1lY3upVwPndXthjj269/x41Y+V0uy9eNImhp2IF13lgPXFmuY3G0aV+3f3BDQcgc84VypdbEURWLg7q5zts3uFRwdpDmOGueizOyBL7Z4GsCY+mLMQJBAOBFjGsy2TS8E6eKxgbeCYq6+G9onOZcmGoDEX41JlW8hGJMsNZz7KlAnvEMfrSB4v7PYXxe2q9xl4xtZ4yKiOcCQQDK+mlUg8eNE2h6H2Jls5OL8bT9YiWJ04Iq+KFMegXv+SLSadyNS8sjWt83AACZR9tLtGXjgQNyUZmh6ZNokinpAkEAtga82aUDtlGQgkOYRqqghAEM8x+teSteaWzkHdN1sdC4gjBMt0KPqy/P0UWa8VcarkYTkaZLSGqh3lBN4zvtsQJBAKzGVGwtM+oA8DiriE07e0du+fmI1p0oHa/ILTx0zaMD9UEX/TWEo6g3jLM7XEcdJCyfpO7vfWiMzvJEaUZkzCkCQQC0RjIsYNBEFq07digfUNummebzwDGqh+S2xMNbA/ab7tr/7efKPUAINVmMIOHw/pTWnikzWPNfY5r5M7WITkIV";
        
        DefaultHttpClient httpClient = new DefaultHttpClient();  
        try {  
            HttpPost request = new HttpPost(url);  
            map.put("supMerId", supMerId);
            if(StringUtils.isNotEmpty(map.get("customerInfo"))){
                String customerInfo=map.get("customerInfo");
                System.out.println("customerInfo="+customerInfo);
                customerInfo = Des3.encryptECB(customerInfo, rsaKey);
                System.out.println("customerInfo="+customerInfo);
                map.put("customerInfo", customerInfo);
            }
            
            StringBuffer signSb = new StringBuffer();
            int index = 0;
            Collection<String> keyset= map.keySet();   
            List list=new ArrayList<String>(keyset);  
            Collections.sort(list);  
            //这种打印出的字符串顺序和微信官网提供的字典序顺序是一致的  
            for(int i=0;i<list.size();i++){  
                if(StringUtils.isEmpty(map.get(list.get(i)))){
                    continue;
                }
                if(index!=0){
                    signSb.append("&");
                }
                index=1;
                signSb.append(list.get(i));
                signSb.append("=");
                signSb.append(map.get(list.get(i)));
             }
            System.out.println(signSb.toString());
            String signBase64Str = AlipaySignature.rsaSign(signSb.toString(), signKey, "utf-8");
            System.out.println("sign="+signBase64Str);
            map.put("sign", signBase64Str);

            StringBuffer sb = new StringBuffer();
            
            String jsonString = TmallYfDataPackage.convertObjectToJson(map); 
            System.out.println("send:"+jsonString);
            StringEntity params =new StringEntity(jsonString,"UTF-8");  
            request.addHeader("content-type", "application/json");  
            request.setEntity(params);  
            HttpResponse response = httpClient.execute(request);  
            HttpEntity entity = response.getEntity();  
            InputStream instream = entity.getContent();  
            BufferedReader in = new BufferedReader(new InputStreamReader(instream,"UTF-8"));  
            sb = new StringBuffer();  
            String data = null;  
            while((data = in.readLine())!=null){  
                sb.append(data);  
            }  
            if(in != null)  
                in.close(); 
            System.out.println(sb);
            JSONObject json=JSONObject.fromObject(sb.toString());
            //回应跟迅联一样
            if(json.optString("orderCode").equals("0000")){
                json.put("respcd", "09");
            }else{
                json.put("respcd", json.opt("code"));
            }
            json.put("qrcode", json.opt("payLink"));
            json.put("errorDetail", json.opt("msg"));
            System.out.println("msJson:"+json.toString());
            return json;
        }catch (Exception e) {  
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 城付天下 民生
     * @Title:        cfSendScan 
     * @Description:  
     * @param:        @param map
     * @param:        @return    
     * @return:       JSONObject    
     * @throws 
     * @author        Eason Jiang
     * @Date          2017-6-21 下午4:45:56
     */
    public JSONObject cfSendScan(Map<String,String> map,String url){
//        String url = "http://test.rytpay.com.cn/wxpay/v5.0/order/fixedQrpay";
        
        String supMerId="000075";
        String signKey="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALeMOSiiEhBaiGicAZysbin4XgnvxLUvHC95R41NZxLf4ukSr1H6QLWewOUZn2kLgQV5Qsfu4K09yw1hqZ2iSbDJlKg9M1l15BUbl1aFgiXlA7MXm5TD5qYfHsYUp3u9huLxvsiuq6AojRSRPgsESBilBFXpqZwhupIrqUvkQ9F3AgMBAAECgYALmbMrHEMkAXTJl8KKUmOMB4R6AEtgYB/Z6EJbbd7r1HaU4HdwKO7aC7SJRjtK+k0nOWi9Fh3hFRy2NcvwiIc7p3U2lil56MHCwj9Hz0MnH0sLdgPbWX/8Y4w+VEsJYeQCtK66fKt2iGmRIfcA72WtCBLHdsjKHKboXsYNBcq0gQJBAPmtEjwtRuwTHRCNVrHiUmzuylsdsN0U66hKaEP3x+xFePL0tyfd6giKT9sc3SQQNFTUylfWvHI7sU3DKSY/FhcCQQC8Ml5LL9lKq8qtVsI8BEx1Gsu+4e5yyfKc3Oc9AmszMY4MDP5naMUW+yHxlBUg9ACixSTgDeehlox1qw/W4ZuhAkANvN4h/YPHfY292WkJxKAwajssJEgVAg+tZNqz0rBnabMr+xcQ1H8KFUIrljz8vP+EI2k2yBP6XRp2YuSivBnlAkAk1795/K4PXj5ZXOi9kyHcsxg8EEL8GejzKJPkb7rI1OS4Gzsax3n+G7/zaUgRoXMWan3jlxVHnw90Yb7CFHzBAkBo7bNettQUUkfJCQtLPBdAwInot6E/wgSeE++wtlj/FCMHB4LDawqUi0FdrA27GOgzW2CdeTgUUPB7V81sYpao";
        
        DefaultHttpClient httpClient = new DefaultHttpClient();  
        try {  
            HttpPost request = new HttpPost(url);  
            map.put("orgId", supMerId);
            
            StringBuffer signSb = new StringBuffer();
            int index = 0;
            Collection<String> keyset= map.keySet();   
            List list=new ArrayList<String>(keyset);  
            Collections.sort(list);  
            //这种打印出的字符串顺序和微信官网提供的字典序顺序是一致的  
            for(int i=0;i<list.size();i++){  
                if(StringUtils.isEmpty(map.get(list.get(i)))){
                    continue;
                }
                if(index!=0){
                    signSb.append("&");
                }
                index=1;
                signSb.append(list.get(i));
                signSb.append("=");
                signSb.append(map.get(list.get(i)));
             }
            System.out.println(signSb.toString());
//            String signBase64Str = AlipaySignature.rsaSign(signSb.toString(), signKey, "utf-8");
            String signBase64Str = Md5withRsa.sign(signSb.toString().getBytes(), signKey);
            System.out.println("sign="+signBase64Str);
            map.put("signature", signBase64Str);

            StringBuffer sb = new StringBuffer();
            
            String jsonString = TmallYfDataPackage.convertObjectToJson(map); 
            System.out.println("send:"+jsonString);
            StringEntity params =new StringEntity(jsonString,"UTF-8");  
            request.addHeader("content-type", "application/json");  
            request.setEntity(params);  
            HttpResponse response = httpClient.execute(request);  
            HttpEntity entity = response.getEntity();  
            InputStream instream = entity.getContent();  
            BufferedReader in = new BufferedReader(new InputStreamReader(instream,"UTF-8"));  
            sb = new StringBuffer();  
            String data = null;  
            while((data = in.readLine())!=null){  
                sb.append(data);  
            }  
            if(in != null)  
                in.close(); 
            System.out.println(sb);
            JSONObject json=JSONObject.fromObject(sb.toString());
            //回应跟迅联一样
            if(json.optString("respCode").equals("200")){
                json.put("respcd", "09");
            }else{
                json.put("respcd", json.opt("respCode"));
            }
            json.put("qrcode", json.opt("qrcode"));
            json.put("errorDetail", json.opt("respMsg"));
            System.out.println("cfJson:"+json.toString());
            json.remove("signature");
            return json;
        }catch (Exception e) {  
            e.printStackTrace();
            return null;
        }
    }
    
    
    
    public static void noCardScan1(){
        String url = "http://test.rytpay.com.cn/wxpay/v5.0/order/qrpay";
        String supMerId="000002";
        String signKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALXUnTcMjv1M49tcgG+LMql52hVXOob0uOcSwBLgFBRVaTqDnw0pOjntVI3/cevQxgZTdguIffA9Ub1jHszCU/5A2OrGrLo8K9y52LnYzPsd143+3/jjdYVORQRDVQ6uVmjKaEBg6w8/H1ixt4kUZSQvTGuYI78U3YLmARy69p5HAgMBAAECgYBAJJu9Qqnig1BSHuST9MfPb1PWeWoFF6luonetcOzEa4jugmYGT4fhzW4plXZLmN9yddjLor/Cku8V3zSLSPY1v6OoJjXQtOkT44UW05neT0+o6Ysc8RxSvwcoGT2sEcYLUajpBOGcL0M7bMe+5+t5s5SNnmZVxP2J+qiewE+++QJBAOLI4R0Th7/oGnq8k/zzaues8qPydqVjPxkz/6+aHDklnvHcuNC1y7UHaQ0BM9cjmP6T6MMOlCU1rK4YW+pBX0sCQQDNQTGa08ZqIRdK77JKtNtaGV/j4U/VxUBEqJXuCMbbnatuZTV90dZcEaOSHliyo2EOb3tCQ68igtpTCCFDWpN1AkAzZb2HPnbkqnDd4kFT8cMH0K2JNJlpME4LozpOjQnadclJHXIRczJAy/YmCWC7YIj7IYB+PJ6ctEZ3inPCaqBzAkEAzIf8gEYr37Hd3iSAzATw2BWVAiWRQnnukvxTewFv94RkQfvEk/4310MUdhSbWleH7bSVZPDBYmh2WmstikqFyQJAcv0k32sNfBJlv7QDW/8z37CfwjxJ0j8avfU55OBIEIcKq39WbBSpfh5u2UHFF8DONWRUC2K+ooB1VpL6UoiHvA==";
        
        DefaultHttpClient httpClient = new DefaultHttpClient();  
        try {  
            HttpPost request = new HttpPost(url);  
            Map<String,String> map = new LinkedHashMap<String, String>();  
            map.put("orgId", supMerId);
            map.put("source", "1");
            map.put("settleAmt", "100");
            map.put("account", "18626269633");
            map.put("amount", "100");
            map.put("notifyUrl", "http://manager.1-town.cn:8088/bpbmtm/clientAction!pradePayBack.ac");
            map.put("tranTp", "0");
            String orderId=DateUtil.getDate("yyMMddHHmmssSSS");

            map.put("orgOrderNo", orderId);
            
            StringBuffer signSb = new StringBuffer();
            int index = 0;
            Collection<String> keyset= map.keySet();   
            List list=new ArrayList<String>(keyset);  
            Collections.sort(list);  
            //这种打印出的字符串顺序和微信官网提供的字典序顺序是一致的  
            for(int i=0;i<list.size();i++){  
                if(StringUtils.isEmpty(map.get(list.get(i)))){
                    continue;
                }
                if(index!=0){
                    signSb.append("&");
                }
                index=1;
                signSb.append(list.get(i));
                signSb.append("=");
                signSb.append(map.get(list.get(i)));
             }
            System.out.println(signSb.toString());
//            String signBase64Str = AlipaySignature.rsaSign(signSb.toString(), signKey, "utf-8");
            String signBase64Str = Md5withRsa.sign(signSb.toString().getBytes(), signKey);
            System.out.println("sign="+signBase64Str);
            map.put("signature", signBase64Str);

            StringBuffer sb = new StringBuffer();
            
            String jsonString = TmallYfDataPackage.convertObjectToJson(map); 
            System.out.println("send:"+jsonString);
            StringEntity params =new StringEntity(jsonString,"UTF-8");  
            request.addHeader("content-type", "application/json");  
            request.setEntity(params);  
            HttpResponse response = httpClient.execute(request);  
            HttpEntity entity = response.getEntity();  
            InputStream instream = entity.getContent();  
            BufferedReader in = new BufferedReader(new InputStreamReader(instream,"UTF-8"));  
            sb = new StringBuffer();  
            String data = null;  
            while((data = in.readLine())!=null){  
                sb.append(data);  
            }  
            if(in != null)  
                in.close(); 
            System.out.println(sb);
            JSONObject json=JSONObject.fromObject(sb.toString());
            Iterator keys=json.keys();
            String key = null;
            String value = null;
            while(keys.hasNext()){
                key=(String) keys.next();
                value=json.get(key).toString();
                System.out.println("return  key:"+key+"-value:"+value);
            }
            
        }catch (Exception e) {  
            e.printStackTrace();
        }
        
    }
    
    public static void noCardScan2(){
//        String url = "http://cf.rytpay.com.cn/wxpay/v1.0/payfor/query";
        String url = "http://cf.rytpay.com.cn/wxpay/v1.0/order/query";
        String supMerId="000075";
        String signKey="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALeMOSiiEhBaiGicAZysbin4XgnvxLUvHC95R41NZxLf4ukSr1H6QLWewOUZn2kLgQV5Qsfu4K09yw1hqZ2iSbDJlKg9M1l15BUbl1aFgiXlA7MXm5TD5qYfHsYUp3u9huLxvsiuq6AojRSRPgsESBilBFXpqZwhupIrqUvkQ9F3AgMBAAECgYALmbMrHEMkAXTJl8KKUmOMB4R6AEtgYB/Z6EJbbd7r1HaU4HdwKO7aC7SJRjtK+k0nOWi9Fh3hFRy2NcvwiIc7p3U2lil56MHCwj9Hz0MnH0sLdgPbWX/8Y4w+VEsJYeQCtK66fKt2iGmRIfcA72WtCBLHdsjKHKboXsYNBcq0gQJBAPmtEjwtRuwTHRCNVrHiUmzuylsdsN0U66hKaEP3x+xFePL0tyfd6giKT9sc3SQQNFTUylfWvHI7sU3DKSY/FhcCQQC8Ml5LL9lKq8qtVsI8BEx1Gsu+4e5yyfKc3Oc9AmszMY4MDP5naMUW+yHxlBUg9ACixSTgDeehlox1qw/W4ZuhAkANvN4h/YPHfY292WkJxKAwajssJEgVAg+tZNqz0rBnabMr+xcQ1H8KFUIrljz8vP+EI2k2yBP6XRp2YuSivBnlAkAk1795/K4PXj5ZXOi9kyHcsxg8EEL8GejzKJPkb7rI1OS4Gzsax3n+G7/zaUgRoXMWan3jlxVHnw90Yb7CFHzBAkBo7bNettQUUkfJCQtLPBdAwInot6E/wgSeE++wtlj/FCMHB4LDawqUi0FdrA27GOgzW2CdeTgUUPB7V81sYpao";
        
        DefaultHttpClient httpClient = new DefaultHttpClient();  
        try {  
            HttpPost request = new HttpPost(url);  
            Map<String,String> map = new LinkedHashMap<String, String>();  
            map.put("orgId", supMerId);
//            map.put("source", "0");
//            map.put("settleAmt", "100");
//            map.put("account", "18626269633");
//            map.put("amount", "100");
//            map.put("callbackUrl", "http://manager.1-town.cn:8088/bpbmtm");
//            map.put("notifyUrl", "http://manager.1-town.cn:8088/bpbmtm/clientAction!pradePayBack.ac");
//            map.put("tranTp", "0");
//            String orderId=DateUtil.getDate("yyMMddHHmmssSSS");

            map.put("orgOrderNo", "170622140742594");
            
            StringBuffer signSb = new StringBuffer();
            int index = 0;
            Collection<String> keyset= map.keySet();   
            List list=new ArrayList<String>(keyset);  
            Collections.sort(list);  
            //这种打印出的字符串顺序和微信官网提供的字典序顺序是一致的  
            for(int i=0;i<list.size();i++){  
                if(StringUtils.isEmpty(map.get(list.get(i)))){
                    continue;
                }
                if(index!=0){
                    signSb.append("&");
                }
                index=1;
                signSb.append(list.get(i));
                signSb.append("=");
                signSb.append(map.get(list.get(i)));
             }
            System.out.println(signSb.toString());
//            String signBase64Str = AlipaySignature.rsaSign(signSb.toString(), signKey, "utf-8");
            String signBase64Str = Md5withRsa.sign(signSb.toString().getBytes(), signKey);
            System.out.println("sign="+signBase64Str);
            map.put("signature", signBase64Str);

            StringBuffer sb = new StringBuffer();
            
            String jsonString = TmallYfDataPackage.convertObjectToJson(map); 
            System.out.println("send:"+jsonString);
            StringEntity params =new StringEntity(jsonString,"UTF-8");  
            request.addHeader("content-type", "application/json");  
            request.setEntity(params);  
            HttpResponse response = httpClient.execute(request);  
            HttpEntity entity = response.getEntity();  
            InputStream instream = entity.getContent();  
            BufferedReader in = new BufferedReader(new InputStreamReader(instream,"UTF-8"));  
            sb = new StringBuffer();  
            String data = null;  
            while((data = in.readLine())!=null){  
                sb.append(data);  
            }  
            if(in != null)  
                in.close(); 
            System.out.println(sb);
            JSONObject json=JSONObject.fromObject(sb.toString());
            Iterator keys=json.keys();
            String key = null;
            String value = null;
            while(keys.hasNext()){
                key=(String) keys.next();
                value=json.get(key).toString();
                System.out.println("return  key:"+key+"-value:"+value);
            }
            
        }catch (Exception e) {  
            e.printStackTrace();
        }
        
    }
    
    public static void main(String[] args) {
//        noCardScan();
//        noCardScan1();
        noCardScan2();
    }
    
}

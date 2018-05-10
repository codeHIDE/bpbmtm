package com.bypay.action;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
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
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.Ostermiller.util.MD5;
import com.alipay.api.internal.util.AlipaySignature;
import com.bypay.common.BaseAction;
import com.bypay.dao.BankBehalfBranchDao;
import com.bypay.dao.ChannelAddInfoDao;
import com.bypay.dao.ChannelMerInfoDao;
import com.bypay.dao.MccCodeDao;
import com.bypay.dao.MerInfoUpdateDao;
import com.bypay.dao.MerManagerDao;
import com.bypay.dao.MerTractDao;
import com.bypay.dao.MerTransDao;
import com.bypay.dao.MobileUserDao;
import com.bypay.dao.OrderInfoDao;
import com.bypay.dao.OrderStatictisDao;
import com.bypay.dao.ProcedureDao;
import com.bypay.dao.RegionCodeDao;
import com.bypay.dao.SubMerAuthInfoDao;
import com.bypay.dao.SubMerInfoDao;
import com.bypay.dao.SubMerRateDao;
import com.bypay.dao.SubMerTerminalDao;
import com.bypay.dao.SubMerTerminalInfoDao;
import com.bypay.dao.SubMerTransDao;
import com.bypay.dao.SysOpLogDao;
import com.bypay.dao.TractInfoDao;
import com.bypay.domain.BankBehalfBranch;
import com.bypay.domain.ChannelAddInfo;
import com.bypay.domain.ChannelMerInfo;
import com.bypay.domain.MccCode;
import com.bypay.domain.MerInfoUpdate;
import com.bypay.domain.MerManager;
import com.bypay.domain.MerTract;
import com.bypay.domain.MerTrans;
import com.bypay.domain.MobileUser;
import com.bypay.domain.OrderInfo;
import com.bypay.domain.OrderStatictis;
import com.bypay.domain.RegionCode;
import com.bypay.domain.SubMerAuthInfo;
import com.bypay.domain.SubMerInfo;
import com.bypay.domain.SubMerRate;
import com.bypay.domain.SubMerTerminal;
import com.bypay.domain.SubMerTerminalInfo;
import com.bypay.domain.SubMerTrans;
import com.bypay.domain.SysManager;
import com.bypay.domain.SysOpLog;
import com.bypay.domain.TractInfo;
import com.bypay.service.RegionCodeService;
import com.bypay.service.SysManagerService;
import com.bypay.service.impl.util.TmallYfDataPackage;
import com.bypay.util.DateUtil;
import com.bypay.util.Des3;
import com.bypay.util.Md5withRsa;
import com.bypay.util.PageUtil;
import com.bypay.util.YMInterfaceUtil;
import com.richerpay.core.model.CoreTransInfo;
import com.richerpay.dubbo.service.NewPayService;

@SuppressWarnings("unchecked")
public class SubMerInfoAction extends BaseAction {
	private Logger logger = LoggerFactory.getLogger(SubMerInfoAction.class);
	private static final long serialVersionUID = 1L;
	// /////////////////////////////////声明要用到的对象////////////////////////////////////////////////
	@Inject
	private BankBehalfBranchDao bankBehalfBranchDao;// 银行 Dao层对象
	@Inject
	private SysManagerService sysManagerService; // 系统管理员service层对象
	@Inject
	private SubMerTerminalDao subMerTerminalDao; // 子商户设备信息dao层对象
	@Inject
	private MerInfoUpdateDao merInfoUpdateDao;
	@Inject
	private TractInfoDao tractInfoDao; // 通道Dao层
	@Inject
	private SubMerRateDao subMerRateDao; // 子商户交易费率Dao层
	@Inject
	private SubMerTransDao subMerTransDao; // 子商户交易配置DaoO
	@Inject
	private SubMerInfoDao subMerInfoDao; // 子商户Dao层对象
	@Inject
	private MerManagerDao merManagerDao; // 管理员Dao层对象
	@Inject
	private SysOpLogDao sysOpLogDao; // 系统管理员日志Dao层对象
	@Inject
	private MccCodeDao mccCodeDao; // 行业类别Dao层对象
	@Inject
	private RegionCodeDao regionCodeDao; // 业务类别Dao层对象
	@Inject
	private MerTransDao merTransDao; // 机构交易配置Dao
	@Inject
	private MerTractDao merTractDao; // 机构通道Dao
	@Inject
	private OrderInfoDao orderInfoDao; // 机构通道Dao
	@Inject
	private OrderStatictisDao orderStatictisDao; // 机构通道Dao
	@Inject
	private ProcedureDao procedureDao; // 机构通道Dao
	@Inject
	private SubMerTerminalInfoDao subMerTerminalInfoDao;
	@Inject
	private MobileUserDao mobileUserDao;
	@Inject
    private ChannelAddInfoDao channelAddInfoDao;
	@Inject
	private RegionCodeService regionCodeService;
	@Inject
    private ChannelMerInfoDao channelMerInfoDao;
//	@Autowired
	private NewPayService payService;
	private Map param; // 用于存储按条件查询的条件
	private String id; // 用于传递查询指定子商务信息的id
	private String subMerList; // 用于查询所有的getResponse().getWriter()
	private String rateStatus;
	private MerTrans merTrans; // 机构交易配置
	private SubMerInfo subMerInfo; // 子商户实体类对象
	private SubMerRate subMerRate; // 子商户交易费率
	private SysManager sysManager; // 系统管理员 对象
	private String transHighestFee;
	private SubMerTrans subMerTrans; // 子商户交易配置表对象
	private RegionCode regionCode;
	private List<MccCode> mccCodeList; // 行业类别
	private List<TractInfo> tractInfoList; // 通道对象
	private List<RegionCode> regionCodeList; // 业务类别
	private SubMerTerminal subMerTerminal; // 子商户设备信息实体类对象
	private List<SubMerRate> subMerRateList; // 用于存储查询属于同一个子商户的交易费率
	private List<SubMerInfo> listSubMerInfo; // 用于存储查询所有的子商务信息集合
	private List<MerTract> merTractList;
	@Inject
	private SubMerAuthInfoDao subMerAuthInfoDao; // 实人认证Dao
	private List<SubMerTerminal> listSubMerTerminal;// 用于存储查询属于同一个子商户的所有子商户设备信息
	private List<BankBehalfBranch> bankBehalfBranchList;// 用于存储查询到的银行
	private List<ChannelAddInfo> channelAddInfoList;       //渠道地址列表
	private List<ChannelMerInfo> channelMerInfoList;       //渠道地址列表
	private ChannelMerInfo channelMerInfo;       //渠道地址列表

	   
	/** *分页开始** */
	private int page = 1;
	private int pageSize = 15;
	/** *读取图片路径** */
	ResourceBundle rb = ResourceBundle.getBundle("com/bypay/config/bmtmUtil",
			Locale.getDefault());
	private List imgUrlList;
	/** *上传图片的参数** */
	private String imgType;
	private List<File> file;
	private String imgRealPath;
	private String headImgFile; // 门头照
	private String hallImgFile; // 大厅照
	private String regNoImgFile; // 营业执照
	private String taxNoImgFile; // 税务登记证
	private String occNoImgFile; // 组织机构代码证
	private String idCardImgFile; // 身份证照片
	private String idCardBackImgFile; // 身份证照片背面
	private String personImgFile; // 个人大头照
	private String otherImgFile1; // 其他1
	private String otherImgFile2; // 其他2
	private String cardImgFile; // 银行卡
	private String cardBackImgFile; // 银行卡
	private String bankPermitImgFile; // 银行开户许可证
	private List<String> fileFileName;
	private List<String> fileContentType;

	// ///////////////////////////////// 执行操作方法
	// /////////////////////////////////////////////

	// 审批失败，清空相关信息，重新注册
	public void eavFailSubInfo() {
		try {
			if (null == subMerInfo) {
				this.renderText("fone");
				return;
			} else {
				SubMerTerminal smt = new SubMerTerminal();
				smt.setSubMerId(String.valueOf(subMerInfo.getSubMerId()));
				smt.setMerSysId(subMerInfo.getMerSysId());
				List<SubMerTerminal> subMerTerminals = subMerTerminalDao
						.selectSubMerTerminalAll(smt);
				// 清空商户的法人信息
				if (null != subMerTerminals && subMerTerminals.size() != 0) {
					subMerInfo.setRemark(subMerInfo.getRemark() + "|"
							+ subMerTerminals.get(0).getTsn());
				}
				// 清空商户的法人信息
				subMerInfoDao.updateLegalInfo(subMerInfo);

				// 设备信息，清楚商户号，登录账户号、密码
				SubMerTerminal subMerTerminal = new SubMerTerminal();
				subMerTerminal.setSubMerId(String.valueOf(subMerInfo
						.getSubMerId()));
				subMerTerminalDao.updateLoginInfoAndSubId(subMerTerminal);

				// 删除设备明细信息
				SubMerTerminalInfo subMerTerminalInfo = new SubMerTerminalInfo();
				subMerTerminalInfo.setSubMerId(String.valueOf(subMerInfo
						.getSubMerId()));
				subMerTerminalInfoDao.deleteTerminalInfo(subMerTerminalInfo);

				subMerInfo = subMerInfoDao.getSubMerInfoById(subMerInfo
						.getSubMerId());
				//删除子商户通道
				SubMerTrans subMerTrans = new SubMerTrans();
				subMerTrans.setSubMerId(subMerInfo.getSubMerId());
				subMerTransDao.deleteSubMerTransInfo(subMerTrans);
				//删除子商户费率
				SubMerRate subMerRate = new SubMerRate();
				subMerRate.setSubMerId(subMerInfo.getSubMerId());
				subMerRateDao.deleteSubMerRate(subMerRate);
				//修改mobile表
				MobileUser mobileUser = new MobileUser();
				mobileUser.setSubMerId(subMerInfo.getSubMerId());
				mobileUserDao.updateUserEavFail(mobileUser);
				
				//发送短信
				YMInterfaceUtil.sendSMS(subMerInfo.getContactorPhone(), "您申请的注册因信息不完整已被驳回，请重新注册", 12);
//				sendMsg(subMerInfo.getContactorPhone());
//				if ("101".equals(subMerInfo.getMerSysId())) { // 至尊宝短信
//					log.info("********至尊宝发送短信");
//					HFSendData.sendZhiZuBao(subMerInfo.getContactorPhone(),
//							"【至尊宝】您注册的商户审核失败!失败原因:"
//									+ subMerInfo.getRemark().split("[|]")[0]);
//				} else if ("103".equals(subMerInfo.getMerSysId())) { // 酷支付短信
//					log.info("********酷支付发送短信");
//					HFSendData.sendKuZhiFu(subMerInfo.getContactorPhone(),
//							"【酷支付】您注册的商户审核失败!失败原因:"
//									+ subMerInfo.getRemark().split("[|]")[0]);
//				}
				// HFSendData.sendData(subMerInfo.getContactorPhone(),
				// "您注册的商户审核失败!失败原因:"+subMerInfo.getRemark().split("[|]")[0]);

				this.renderText("success");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			try {
				this.renderText("fone");
				return;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public int sendMsg(String mobile){
		logger.info("send mobile="+mobile);
		String url = "http://manager.1-town.com/RyManager/errorTongzhi/send_phone_code.do?phone=";
		try {
			HttpClient httpClient = new HttpClient();
			GetMethod get = new GetMethod(url+mobile);
//			PostMethod post=new PostMethod(url);
			get.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
			int statusCode = httpClient.executeMethod(get);
			return statusCode;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}
	} 
	
	// 调账
	public void addAccounts() {
		try {
			String subMerId = getRequest().getParameter("subMerId");
			String merAmt = getRequest().getParameter("merAmt");
			if (subMerId == null || subMerId.equals("")) {
				getResponse().getWriter().write("子商户号不能为空");
				return;
			}
			if (merAmt == null || merAmt.equals("") || merAmt.equals("0")) {
				getResponse().getWriter().write("调帐金额不能为空或者为0");
				return;
			}
			subMerInfo = subMerInfoDao.getSubMerInfoById(subMerId);
			if (subMerInfo == null) {
				getResponse().getWriter().write("无此商户");
				return;
			}
			String settleDate = DateUtil.getDate("yyyyMMdd");
			settleDate = "20141105";
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setSubMerId(subMerId);
			orderInfo.setSettleDate(settleDate);
			List<OrderInfo> orderInfoList = orderInfoDao
					.selectOrderInfoBySubMerId(orderInfo);
			if (orderInfoList == null || orderInfoList.size() == 0) {
				getResponse().getWriter().write("此商户今日无交易");
				return;
			}
			Iterator<OrderInfo> orderInfoIt = orderInfoList.iterator();
			Long merAmt1 = 0L;
			Double merAmt2 = Double.parseDouble(merAmt) * 100;
			while (orderInfoIt.hasNext()) {
				OrderInfo orderInfo1 = (OrderInfo) orderInfoIt.next();
				if (orderInfo1.getMerAmt() != null)
					merAmt1 += Long.parseLong(orderInfo1.getMerAmt());
			}
			System.out.println("此商户今日交易金额：" + merAmt1 / 100 + "元");
			if (merAmt1 < merAmt2) {
				getResponse().getWriter().write("此商户今日交易金额不足：" + merAmt + "元");
				return;
			}
			orderInfo = orderInfoList.get(0);
			OrderInfo orderInfo1 = new OrderInfo();
			orderInfo1.setBatchId("88888888");
			orderInfo1.setTerminalId(orderInfo.getTerminalId());
			orderInfo1.setSubMerId(subMerId);
			orderInfo1.setMerSysId(orderInfo.getMerSysId());
			orderInfo1.setAgentIdL1(orderInfo.getAgentIdL1());
			orderInfo1.setAgentIdL2(orderInfo.getAgentIdL2());
			orderInfo1.setTransMerId(orderInfo.getTransMerId());
			orderInfo1.setTransType("10");
			orderInfo1.setTerminalType(orderInfo.getTerminalType());
			orderInfo1.setBusType(orderInfo.getBusType());
			orderInfo1.setMerOrderId(DateUtil.getDate("yyyyMMddHHmmss"));
			orderInfo1.setMerOrderTime(DateUtil.getDate("yyyyMMddHHmmss"));
			orderInfo1.setMerAmt(merAmt2 + "");
			orderInfo1.setOrderFee("0");
			orderInfo1.setCurrency(orderInfo.getCurrency());
			orderInfo1.setOrderDesc("调账订单");
			orderInfo1.setOrderOverTime("");
			orderInfo1.setOrderStatus("1");
			orderInfo1.setRefundStatus("0");
			orderInfo1.setCreateTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
			orderInfo1.setTransTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
			orderInfo1.setFinshTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
			orderInfo1.setBatchNo("888888");
			orderInfo1.setRefferNo("88888888");
			orderInfo1.setAuthNo("888888");
			orderInfo1.setVoucherNo("888888");
			orderInfo1.setSettleAmt(merAmt2 + "");
			orderInfo1.setSettleDate(DateUtil.getDate("yyyyMMdd"));
			orderInfo1.setTransFee("0");
			orderInfo1.setOrderRateType(orderInfo.getOrderRateType());
			orderInfo1.setRespDesc("调账成功");
			String orderId = procedureDao.getOrderId();
			orderInfo1.setOrderId(orderId);
			OrderStatictis orderStatictis = new OrderStatictis();
			orderStatictis.setOrderId(orderId);
			orderStatictis.setSubMerId(subMerId);
			orderStatictis.setMerAmt(merAmt2 + "");
			orderStatictis.setTransFee("0");
			orderStatictis.setClearAmt(merAmt2 + "");
			orderStatictis.setSettleDate(DateUtil.getDate("yyyyMMdd"));
			orderStatictis.setTransType("10");
			orderStatictis.setClearType("1");
			orderStatictis.setCreateTime(DateUtil.getDate("yyyyMMddHHmmss"));
			int i = orderInfoDao.insertOrderInfo(orderInfo1);
			if (i != 0) {
				i = orderStatictisDao.insertOrderStatictis(orderStatictis);
				if (i != 0) {
					getResponse().getWriter().write("调账成功");
				} else {
					getResponse().getWriter().write("调账失败");
				}
			} else {
				getResponse().getWriter().write("调账失败");
			}
		} catch (Exception e) {
			try {
				getResponse().getWriter().write("调账失败");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void downloadImg() {
		FileInputStream fis = null;
		OutputStream out = null;
		try {
			subMerInfo = subMerInfoDao.getSubMerInfoById(id);
			String path = rb.getString("ImagesUrl") + "/SubMerImages/"
					+ subMerInfo.getMerSysId() + "/" + subMerInfo.getSubMerId()
					+ "/" + imgType + ".jpg";
			System.out.println(path);
			File file = new File(path);
			if (!file.exists()) {
				getRequest().setAttribute("mess", "FondImage");
				return;
			}
			HttpServletResponse response = getResponse();
			response.setContentType("image/jpeg");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ file.getName());
			response.addHeader("Content-Length", file.length() + "");
			fis = new FileInputStream(file);
			out = response.getOutputStream();
			byte[] b = new byte[1024];
			int len = 0;
			while ((len = fis.read(b)) != -1) {
				out.write(b, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		getRequest().setAttribute("mess", "Succ");
	}

	// 查询所有子商务信息，用于在页面显示
	public void findAll() {
		try {
			if (subMerInfo == null) {
				subMerInfo = new SubMerInfo();
				subMerTrans = new SubMerTrans();
			}
			if (subMerInfo.getSubMerName() != null) {
				System.out.println("转码前：SubMerName="
						+ subMerInfo.getSubMerName());
				// subMerInfo.setSubMerName(new
				// String(subMerInfo.getSubMerName().getBytes("ISO-8859-1"),
				// "UTF-8"));
				// System.out.println("转码后：SubMerName="+subMerInfo.getSubMerName());
			}
			Map map = PageUtil.getPageMap(page, pageSize);
			
			String logName = (String) getSession().getAttribute("userName");
		      //特殊用户只看外卡
			map.put("subMerInfo", subMerInfo);
			if(logName.equals("whf")){ 
			    map.put("merSysIds", "'104','126','144','149','151'");
		      }else{
		          map.put("merSysIds", null);
		      }
			int count = subMerInfoDao.selectSubMerchantInfoCount(map);
			listSubMerInfo = subMerInfoDao.findAll(map);
			JSONArray array = JSONArray.fromObject(listSubMerInfo);
			JSONObject object = new JSONObject();
			object.put("Rows", array.toString());
			object.put("Total", count);
			subMerList = object.toString();
			getResponse().getWriter().write(subMerList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 代付验证
	 * @Title:        checkPay 
	 * @Description:  
	 * @param:            
	 * @return:       void    
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2015-7-1 下午5:19:57
	 */
	public void checkPay(){
		 String subMerId = this.getParameterForString("subMerId");
		 //卡号跟姓名 商户号
		 CoreTransInfo coreTransInfo = new CoreTransInfo();
    	 Boolean flag = Boolean.FALSE;
		 try{
			 SubMerInfo subMerInfo = subMerInfoDao.getSubMerInfoById(subMerId);
			 coreTransInfo.setPan(subMerInfo.getSettAccountNo());
			//先判断身份证末尾是否是X
	    	 String idNum = subMerInfo.getLegalIdcard();
	    	 if(idNum.toLowerCase().endsWith("x")){
	    		 idNum = idNum.substring(idNum.length()-7,idNum.length()-1);
	    	 }else{
	    		 idNum = idNum.substring(idNum.length()-6);
	    	 }
	    	 coreTransInfo.setIdNumber(idNum);
	    	 coreTransInfo.setUserName(subMerInfo.getLegalPerson());		//姓名
	    	 coreTransInfo.setChTermId("01010611");		//终端号
	    	 coreTransInfo.setTerminalNo("01010611");
	    	 coreTransInfo.setChMerId("898220183980106");		//商户号
	    	 coreTransInfo.setMerId("898220183980106");
	    	 SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
	    	 coreTransInfo.setTrackingNo(sdf.format(new Date()));
	    	 coreTransInfo.setTransType("1001");		//银行卡
	    	 coreTransInfo.setTransSource("201");		//手刷
	    	 coreTransInfo.setMessageType("0200");		//消费
	    	 coreTransInfo.setProcessingCode("330000");
	    	 coreTransInfo.setBatchNo("000001");
	    	 coreTransInfo.setCurrencyCode("156");		//恒定156
	    	 logger.info("子商户代付验证make object suc coreTransInfo="+coreTransInfo.toString());
	    	 coreTransInfo = payService.doEntrustPayVerify(coreTransInfo);
	    	 logger.info("*******doPay resp："
						+ coreTransInfo.getResponseCode());
			 if(StringUtils.isNotEmpty(coreTransInfo.getResponseCode()) && coreTransInfo.getResponseCode().equals("00")){
				 flag = true;
				 //成功则更新状态
				 subMerInfoDao.updateCheckSub(subMerId);
			 }
			 getResponse().getWriter().write(flag.toString());
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	}
	
	
	/**
	 * 民生扫码报备商户
	 * @Title:        getScanMer 
	 * @Description:  
	 * @param:            
	 * @return:       void    
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2017-6-2 上午11:33:20
	 */
	public void getScanMer(){
        String subMerId = this.getParameterForString("subMerId");
        try{
            SubMerInfo subMerInfo = subMerInfoDao.getSubMerInfoById(subMerId);
            Map<String,String> map = new LinkedHashMap<String, String>();  
            map.put("cardType", "1");
            map.put("pmsBankNo", subMerInfo.getLineNum());
            map.put("certNo", subMerInfo.getLegalIdcard());
            map.put("mobile", subMerInfo.getContactorPhone());
            map.put("password", subMerInfo.getContactorPhone());
            map.put("cardNo", subMerInfo.getSettAccountNo());
//            String orderId=DateUtil.getDate("yyMMddHHmmssSSS");
//            map.put("orderNo", orderId);
            map.put("realName", subMerInfo.getSettAccountName());
            map.put("account", subMerInfo.getContactorPhone());
            map.put("mchntName", subMerInfo.getSubMerName());
            map.put("wxT1Fee", "0.0038");
            map.put("wxT0Fee", "0.0038");
            map.put("alipayT1Fee", "0.0038");
            map.put("alipayT0Fee", "0.0038");
            String url = "http://cf.rytpay.com.cn/wxpay/v2.0/merchant/regist";
            JSONObject rJson = cfSendScan(map,url);
            getResponse().getWriter().write(rJson.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
   }
	
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
            System.out.println("msJson:"+json.toString());
            return json;
        }catch (Exception e) {  
            e.printStackTrace();
            return null;
        }
    }
    
	// 查询 指定子商务的信息，用于修改
	public String getById() {
		try {
			if (this.getSubMerInfo() == null)
				this.setSubMerInfo(new SubMerInfo());
			this.subMerInfo.setSubMerId(id);
			subMerInfo = this.getSubMerInfoDao().findById(subMerInfo);
			subMerTrans = new SubMerTrans();
			subMerTrans.setSubMerId(id);
			//subMerTrans = subMerTransDao.selectSubMerTransById(subMerTrans);
			subMerTrans = subMerTransDao.selectSubMerTransById(subMerTrans);
			//扣率通道
			if(null!=subMerTrans.getSubMerTractR1()&&!subMerTrans.getSubMerTractR1().equals("")){
				TractInfo tractR1 = tractInfoDao.selectTractById(subMerTrans.getSubMerTractR1());
				if(null!=tractR1&&null!=tractR1.getTractId()&&!"".equals(tractR1.getTractId())){
					subMerTrans.setSubMerTractR1(tractR1.getTractName());
				}else{
					subMerTrans.setSubMerTractR1("");
				}
			}
			//积分通道
//			if(null!=subMerTrans.getSubMerTractR3()&&!subMerTrans.getSubMerTractR3().equals("")&&!subMerTrans.getSubMerTractR3().equals("-1")){
//			    ChannelMerInfo channelMerInfo = new ChannelMerInfo();
//                channelMerInfo = channelMerInfoDao.selectChannelMerInfoById(channelMerInfo);
//                if(null!=channelMerInfo){
//					subMerTrans.setSubMerTractR3(channelMerInfo.getChName());
//				}else{
//					subMerTrans.setSubMerTractR3("");
//				}
//			}
			
			mccCodeList = mccCodeDao.selectMccCodeAll();
			channelAddInfoList = channelAddInfoDao.selectAllChannelAddInfo();
			param = new HashMap<String, Object>();
			param.put("page", null);
			param.put("pageSize", null);
			regionCodeList = regionCodeDao.selectRegionCodeList(param);
			subMerRate = new SubMerRate();
			subMerRate.setSubMerId(id);
			merTractList = merTractDao.selectmerTractByMerSysId(subMerInfo
					.getMerSysId());
			rateInformationOrType();
			if (subMerRateList.size() > 0)
				subMerRate = subMerRateList.get(0);
			BigDecimal ss =new BigDecimal(subMerRate.getTeransRate()).divide(new BigDecimal("100"));
			Map merMap = new HashMap();
            merMap.put("chAddId", subMerTrans.getSubMerTractR2());
            merMap.put("t0Rate", ss.toString());
            channelMerInfoList = channelMerInfoDao.selectChannelMerInfoByFee(merMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "findById";
	}

	
	public void getChannelMerCode() {
        log.info("getChannelMerCode");
        try {
            BigDecimal ss =new BigDecimal(channelMerInfo.getT0Rate()).divide(new BigDecimal("100"));
            Map merMap = new HashMap();
            merMap.put("chAddId", channelMerInfo.getChAddId());
            merMap.put("t0Rate", ss.toString());
            channelMerInfoList = channelMerInfoDao.selectChannelMerInfoByFee(merMap);
            JSONArray jsonArray = JSONArray.fromObject(channelMerInfoList);
            System.out.println(jsonArray.toString());
            getResponse().getWriter().write(jsonArray.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	// 查询 指定子商务的信息
	public String getSubMerInfoById() {
		try {
			if (this.getSubMerInfo() == null)
				this.setSubMerInfo(new SubMerInfo());
			this.subMerInfo.setSubMerId(id);
			subMerInfo = this.getSubMerInfoDao().findById(subMerInfo);
			subMerTrans = new SubMerTrans();
			subMerTrans.setSubMerId(id);
			subMerTrans = subMerTransDao.selectSubMerTransById(subMerTrans);
			mccCodeList = mccCodeDao.selectMccCodeAll();
	        channelAddInfoList = channelAddInfoDao.selectAllChannelAddInfo();
			param = new HashMap<String, Object>();
			param.put("page", null);
			param.put("pageSize", null);
			regionCodeList = regionCodeDao.selectRegionCodeList(param);
			subMerRate = new SubMerRate();
			subMerRate.setSubMerId(id);
			merTractList = merTractDao.selectmerTractByMerSysId(subMerInfo
					.getMerSysId());
			rateInformationOrType();
			if (subMerRateList.size() > 0)
				subMerRate = subMerRateList.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "findSubMerInfoById";
	}

	// 当用户改变费率类型时触发改事件
	public void onchangeRateType() {
		try {
			subMerRateList = subMerRateDao
					.selectListSubMerRateGetById(subMerInfo.getSubMerRate());
			JSONArray object = JSONArray.fromObject(subMerRateList.get(0));
			getResponse().getWriter().write(object.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 修改 费率 前查询费率信息
	public String rateInformationOrType() {
		subMerRateList = subMerRateDao.selectListSubMerRateGetById(subMerRate);// 查询子商户的费率信息
		String subMerId = subMerRate.getSubMerId();
		subMerInfo = new SubMerInfo();
		subMerInfo.setSubMerId(subMerId);
		subMerInfo = subMerInfoDao.findById(subMerInfo);
		if ("type".equals(id))
			return "type";
		else
			return "information";
	}

	// 修改指定的子商户信息
	public void updateFindById() {
		try {
			if (subMerInfo != null) {
				
				subMerInfo.setRegion(subMerInfo.getRegion()
						+ subMerInfo.getCity());
				if (subMerInfoDao.update(subMerInfo)) {
					SubMerTrans merTrans = subMerTrans;
					//扣率通道
					if(null!=subMerTrans.getSubMerTractR1()&&!subMerTrans.getSubMerTractR1().equals("")){
						TractInfo tractR1 = tractInfoDao.selectTractByMerId(subMerTrans.getSubMerTractR1());
						if(null!=tractR1&&null!=tractR1.getTractId()&&!"".equals(tractR1.getTractId())&&"01".equals(tractR1.getRatesType())){
							merTrans.setSubMerTractR1(tractR1.getTractId());
						}else{
							getResponse().getWriter().write("5");
							merTrans.setSubMerTractR1("-1");
						}
					}else{
						merTrans.setSubMerTractR1("-1");
					}
					//POS渠道
					if(null!=subMerTrans.getSubMerTractR2()&&!subMerTrans.getSubMerTractR2().equals("")){
//						TractInfo tractR2 = tractInfoDao.selectTractByMerId(subMerTrans.getSubMerTractR2());
//						if(null!=tractR2&&null!=tractR2.getTractId()&&!"".equals(tractR2.getTractId())&&"02".equals(tractR2.getRatesType())){
//							merTrans.setSubMerTractR2(tractR2.getTractId());
//						}else{
//							getResponse().getWriter().write("5");
//							merTrans.setSubMerTractR2("-1");
//						}
					}else{
						merTrans.setSubMerTractR2("-1");
					}
					//POS渠道通道
					if(null!=subMerTrans.getSubMerTractR3()&&!subMerTrans.getSubMerTractR3().equals("")){
//						TractInfo tractR3 = tractInfoDao.selectTractByMerId(subMerTrans.getSubMerTractR3());
//						if(null!=tractR3&&null!=tractR3.getTractId()&&!"".equals(tractR3.getTractId())&&"05".equals(tractR3.getRatesType())){
//							merTrans.setSubMerTractR3(tractR3.getTractId());
//						}else{
//							getResponse().getWriter().write("5");
//							merTrans.setSubMerTractR3("-1");
//						}
					}else{
						merTrans.setSubMerTractR3("-1");
					}
					merTrans.setSubMerId(subMerInfo.getSubMerTrans()
							.getSubMerId());
					merTrans.setDispMerId(subMerInfo.getSubMerTrans()
							.getDispMerId());
					merTrans.setDisMerName(subMerInfo.getSubMerTrans()
							.getDisMerName());
					int i = subMerTransDao.updateSubMerTransInfo(merTrans);
					if (i > 0) {
						getResponse().getWriter().write("2");
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 修改指定子商户的交易费率 类型
	public void updateSubMerchantInfoRateType() {
		try {
			JSONObject jF = null; // 封顶JSON
			JSONObject jK = null; // 扣率JSON
			MerInfoUpdate merInfoUpdate = new MerInfoUpdate(); // 初始化变更表
			merInfoUpdate.setMid(id); // 商户号
			merInfoUpdate.setMerType("1"); // 商户类型 1是子商户
			merInfoUpdate.setStatus("0"); // 变更状态 0：审请
			merInfoUpdate.setModifyType("03"); // 更新类型 01类型变更
			merInfoUpdate.setCreateTime(DateUtil.getDate("yyyy-MM-dd")); // 创建时间
			String feng = subMerInfo.getSubMerRate().getRateType(); // 提取封顶类型
			String kou = subMerRate.getRateType(); // 提取扣率类型
			SubMerRate smr1 = new SubMerRate();
			smr1.setSubMerId(id);
			subMerRateList = subMerRateDao.selectListSubMerRateGetById(smr1); // 查询子商户的费率信息
			for (SubMerRate smr : subMerRateList) {
				String smrTypt = smr.getRateType(); // 提取当前费率类型
				if (smrTypt.equals(feng) && !"1".equals(smr.getStatus())) {
					jF = JSONObject.fromObject(subMerInfo.getSubMerRate());
				}
				if (smrTypt.equals(kou) && !"1".equals(smr.getStatus())) {
					jK = JSONObject.fromObject(subMerRate);
				}
			}
			if (jF != null && jK != null)
				merInfoUpdate.setValue(jF.toString() + "|" + jK.toString()); // 变更数据值
			else if (jF != null)
				merInfoUpdate.setValue(jF.toString() + "|"); // 变更数据值
			else if (jK != null)
				merInfoUpdate.setValue(jK.toString() + "|"); // 变更数据值
			int num = merInfoUpdateDao.insertMerInfoUpdate(merInfoUpdate);
			if (num > 0)
				getResponse().getWriter().write("2");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 修改指定子商户的交易费率 信息
	public void updateSubMerchantInfoRate() {
		try {
			JSONObject jo = null;
			JSONObject jb = null;
			if (subMerInfo != null) {
				if (subMerInfo.getSubMerRate() != null) {
					subMerInfo.getSubMerRate().setSubMerId(id);
					jb = JSONObject.fromObject(subMerInfo.getSubMerRate());
				}
			}
			if (subMerRate != null) {
				subMerRate.setSubMerId(id);
				jo = JSONObject.fromObject(subMerRate);
			}
			if (jb.toString().equals(jo.toString())) {
				getResponse().getWriter().write("4");
				return;
			}
			int num = 0;
			num = subMerRateDao.updateSubMerRate(subMerRate);
			if (num > 0)
				getResponse().getWriter().write("2");
			// MerInfoUpdate merInfoUpdate=new MerInfoUpdate(); //初始化变更表
			// merInfoUpdate.setMid(id); //商户号
			// merInfoUpdate.setMerType("1"); //商户类型 1是子商户
			// merInfoUpdate.setStatus("0"); //变更状态 0：审请
			// merInfoUpdate.setModifyType("01"); //更新类型 01费率变更
			// merInfoUpdate.setCreateTime(DateUtil.getDate("yyyy-MM-dd"));
			// //创建时间
			// num=merInfoUpdateDao.insertMerInfoUpdate(merInfoUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 查询子商户的所有终端号
	public void selectSubMerTerminal() {
		try {
			System.out.println("1243:" + getRequest().getParameter("id"));
			this.subMerTerminal = new SubMerTerminal();
			this.subMerTerminal.setSubMerId(this.id);
			this.listSubMerTerminal = subMerTerminalDao
					.selectSubMerTerminalAll(subMerTerminal);
			JSONArray array = JSONArray.fromObject(listSubMerTerminal);
			JSONObject object = new JSONObject();
			object.put("Rows", array.toString());
			object.put("Total", listSubMerTerminal.size());
			subMerList = object.toString();
			getResponse().getWriter().write(subMerList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 重置终端信息密码
	public void updateTerminalpePass() {
		try {
			HttpServletResponse response = getResponse();
			PrintWriter printWriter = response.getWriter();
			if (this.subMerTerminalDao.updateTreminalPwd(MD5
					.getHashString("123456"), id)) {
				printWriter.write("succ");
				System.out.println("修改成功！");
			} else {
				printWriter.write("fones");
				System.out.println("修改失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 重置终端信息状态
	public void updateTerminalStatus() {
		try {
			HttpServletResponse response = getResponse();
			PrintWriter printWriter = response.getWriter();
			if (this.subMerTerminalDao.updateTreminalStatus("0", id)) {
				printWriter.write("succ");
				System.out.println("修改成功！");
			} else {
				printWriter.write("fones");
				System.out.println("修改失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 查询指定子商户的信息 用于详情显示
	public String subMerchantTop() {
		getById();
		// 查询机构的终端产品类型
		merTrans = subMerInfoDao.getMerTransOnTerminalType(subMerInfo
				.getMerSysId());
		// String[] terminalType=merTrans.getTerminalType().split("|");
		// System.out.println("机构终端产品类型："+terminalType[1]+"：：：：：：："+terminalType[3]);
		subMerTrans = new SubMerTrans();
        subMerTrans.setSubMerId(id);
        //subMerTrans = subMerTransDao.selectSubMerTransById(subMerTrans);
        subMerTrans = subMerTransDao.selectSubMerTransById(subMerTrans);
		merTractList = merTractDao.selectmerTractByMerSysId(subMerInfo
				.getMerSysId());
		if (this.rateStatus == "1" || "1".equals(rateStatus)) {
			return "getByIdSelect1";
		}
		return "getByIdSelect";
	}

	// 暂停/恢复/上线/详情 子商户
	public void subMerDetail() {
		try {
			if (subMerInfo == null) {
				getResponse().getWriter().write("fone");
				return;
			}
			if (subMerTrans != null) {

				// 如果机构终端产品类型为08 POS,上线时将子商户的终端状态改为使用中的
				if ("2".equals(subMerInfo.getStatus())) {
					SubMerTerminal subMerTerminal = subMerTerminalDao
							.getSubMerTerminalTSN(subMerInfo.getSubMerId());
					if (null != subMerTerminal
							&& null != subMerTerminal.getCategory()
							&& !"".equals(subMerTerminal.getCategory())
							&& "08".equals(subMerTerminal.getCategory())) {
						// 修改子商户终端状态
						subMerTerminalDao.updateTreminalStatusBySubMerId("1",
								subMerInfo.getSubMerId());
					}
				}
			}

			if (subMerTrans != null) {
				subMerTrans.setSubMerId(subMerInfo.getSubMerId());
//				if (!"-1".equals(subMerTrans.getSubMerTractR1())
//						&& !"-1".equals(subMerTrans.getSubMerTractR2())
//						&& !"-1".equals(subMerTrans.getSubMerTractR3()))
				SubMerTrans subMerTrans2 = subMerTrans;
				//扣率通道
				if(null!=subMerTrans.getSubMerTractR1()&&!subMerTrans.getSubMerTractR1().equals("")){
				}else{
					subMerTrans2.setSubMerTractR1("-1");
				}
				//封顶通道
				if(null!=subMerTrans.getSubMerTractR2()&&!subMerTrans.getSubMerTractR2().equals("")){
				}else{
					subMerTrans2.setSubMerTractR2("-1");
				}
				//积分通道
				if(null!=subMerTrans.getSubMerTractR3()&&!subMerTrans.getSubMerTractR3().equals("")){
				}else{
					subMerTrans2.setSubMerTractR3("-1");
				}
				
				//subMerTransDao.updateSubMerTransInfo(subMerTrans);
				subMerTransDao.updateSubMerTransInfo(subMerTrans2);
			}
			boolean f = subMerInfoDao.updateStatus(subMerInfo);
			if (Integer.parseInt(subMerInfo.getStatus()) < 3)
				if (f) {
					// 添加子商户管理员
					MerManager merManager = new MerManager();
					merManager.setMid(subMerInfo.getSubMerId());
					int number = merManagerDao
							.selectMerManagerNameByMid(merManager);
					if (number == 0) {
						merManager.setMerType("1");
						merManager.setLoginName("admin");
						merManager.setLoginPwd(MD5.getHashString("admin"));
						merManager.setStatus("1");
						merManagerDao.insertMerManager(merManager);
					}

//					if ("101".equals(subMerInfo.getMerSysId())) { // 至尊宝短信
//						log.info("********至尊宝发送短信");
//						HFSendData.sendZhiZuBao(subMerInfo.getContactorPhone(),
//								"【至尊宝】您于" + DateUtil.getDate("yyyy-MM-dd")
//										+ ",商户" + subMerInfo.getSubMerName()
//										+ "上线成功!");
//					} else if ("103".equals(subMerInfo.getMerSysId())) { // 酷支付短信
//						log.info("********酷支付发送短信");
//						HFSendData.sendKuZhiFu(subMerInfo.getContactorPhone(),
//								"【酷支付】您于" + DateUtil.getDate("yyyy-MM-dd")
//										+ ",商户" + subMerInfo.getSubMerName()
//										+ "上线成功!");
//					}
					getResponse().getWriter().write("succ");
				} else {
//					if ("101".equals(subMerInfo.getMerSysId())) { // 至尊宝短信
//						log.info("********至尊宝发送短信");
//						HFSendData.sendZhiZuBao(subMerInfo.getContactorPhone(),
//								"【至尊宝】您于" + DateUtil.getDate("yyyy-MM-dd")
//										+ ",商户" + subMerInfo.getSubMerName()
//										+ "上线失败!");
//					} else if ("103".equals(subMerInfo.getMerSysId())) { // 酷支付短信
//						log.info("********酷支付发送短信");
//						HFSendData.sendKuZhiFu(subMerInfo.getContactorPhone(),
//								"【酷支付】您于" + DateUtil.getDate("yyyy-MM-dd")
//										+ ",商户" + subMerInfo.getSubMerName()
//										+ "上线失败!");
//					}
					getResponse().getWriter().write("fone");
					// HFSendData.sendData(subMerInfo.getContactorPhone(),
					// "您于"+DateUtil.getDate("yyyy-MM-dd")+",商户"+subMerInfo.getSubMerName()+"上线失败!");
				}
			if (subMerInfo.getStatus().equals("3"))
				if (f) {
					getResponse().getWriter().write("succSuspended");
				} else {
					getResponse().getWriter().write("fonesSuspended");
				}
			if (subMerInfo.getStatus().equals("5"))
				if (f) {
					getResponse().getWriter().write("succ");
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送报文 同步商户
	 * @Title:        sendMer 
	 * @Description:  
	 * @param:            
	 * @return:       void    
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2015-11-18 下午2:22:10
	 */
//	public void sendMer(SubMerInfo subMerInfo){
//	    DefaultHttpClient httpClient = new DefaultHttpClient();  
//	    try {  
//	    	HttpPost request = new HttpPost("http://101.231.72.90:8099/ryms/login_check.action");
//	        Map<String,String> map = new LinkedHashMap<String, String>(); 
//	        map.put("merchantNo", subMerInfo.getSubMerId());		//商户号
//			map.put("orgCode", subMerInfo.getMerSysId());		//机构号
//			map.put("agentCode", subMerInfo.getMerSysId());		//机构号
//			map.put("agentCode", subMerInfo.getAgentIdL1());		//代理商代码
//			map.put("provId", );		//区域id
//			map.put("mccType", );		//商户组别
//			map.put("areaCode", );		//地区码
//			map.put("mccCode", subMerInfo.getMcc());		//MCC码
//			map.put("merName", subMerInfo.getMerName());		//商户名称
//			map.put("merRange", );		//经营范围
//			map.put("merAddr", subMerInfo.getContactorAddr());		//经营地址
//			map.put("legalPersonName",subMerInfo.getLegalPerson() );		//法人姓名
//			map.put("legalPersonMob", subMerInfo.getContactorPhone());		//法人手机号码
//			map.put("licenseNo", );		//营业执照号
//			map.put("lpIdentiNo", subMerInfo.getLegalIdcard());		//法人身份证号
//			map.put("lpEmail", subMerInfo.getContactorEmail());		//常用邮箱
//			map.put("acctName", subMerInfo.getSettAccountName());		//账户姓名
//			map.put("acctNo", subMerInfo.getSettAccountNo());		//账户号
//			map.put("acctBank", subMerInfo.getOpenBank());		//开户行
//			map.put("bankCode", subMerInfo.getLineNum());		//联行号
//			map.put("merStat", "4");		//商户状态0- 未审核1 - 审核未通过2 - 审核通过4 - 关闭
//			map.put("settleFlag", );		//清算标志 1- T+1 0 - T+0
//			map.put("merFeeRate", );		//刷卡费率
//			map.put("topFee", );		//封顶
//			map.put("identiImgUrl", );		//法人身份证正反面照
//			map.put("identiImgUrl2", );		//法人手持身份证半身照
//			map.put("cardImgUrl", );		//银行卡正反面照
//			map.put("licenseImgUrl", );		//营业执照
//			map.put("taxLicenseImgUrl", );		//税务登记证
//			map.put("orgLicenseUrl", );		//组织机构代码证
//			map.put("openProveImgUrl", );		//开户证明
//			map.put("outViewImgUrl", );		//门头照片
//			map.put("innerViewImgUrl", );		//内景照片
//			map.put("createDate", subMerInfo.getCreateTime());		//创建时间
//			map.put("createUserId", );		//创建人id
//			map.put("verifyUserId", );		//审核人Id
//			map.put("verifyDate", );		//审核通过日期
//			map.put("userId", );		//商户登陆id 关联用户表t_employee
//			map.put("scoreRegId", );		//风控规则Id
//			map.put("feeRate", );		//签约费率
//			map.put("merAcctBal", );		//账户余额
//			map.put("merAvailBal", );		//可用余额
//			map.put("riskRegId", );		//风控id
//			map.put("transFlag", );		//交易类型
//			map.put("chId", );		//渠道商户id
//			map.put("chAddId", );		//渠道地址id
//			map.put("isOTO", );		//是否 oto 0是，1否
//			map.put("lastUpdUid", );		//最后修改人id
//			map.put("lastUpdTs", );		//最后修改时间
//			map.put("identibackImgUrl", );		//法人身份证反面照
//			map.put("cardImgUrl2", );		//银行卡反面照
//			map.put("alipayStatus", );		//支付宝状态
//	        StringBuffer sb = new StringBuffer();
//	        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();  
//	        nvps.add(new BasicNameValuePair("username", "181admin")); // 参数
//	        nvps.add(new BasicNameValuePair("password", "123456")); // 参数
//	        UrlEncodedFormEntity params =new UrlEncodedFormEntity(nvps, HTTP.UTF_8);
//	        request.setEntity(params);
////	        StringEntity params =new StringEntity(jsonString,"UTF-8");  
////	        request.addHeader("content-type", "application/json");  
//	        request.setEntity(params);  
//	        HttpResponse response = httpClient.execute(request);  
//	        HttpEntity entity = response.getEntity();  
//	        InputStream instream = entity.getContent();  
//	        BufferedReader in = new BufferedReader(new InputStreamReader(instream,"UTF-8"));  
//	        sb = new StringBuffer();  
//	        String data = null;  
//	        while((data = in.readLine())!=null){  
//	            sb.append(data);  
//	        }  
//	        if(in != null)  
//	            in.close(); 
//	        System.out.println(sb);
//	        JSONObject json=JSONObject.fromObject(sb.toString());
//			Iterator keys=json.keys();
//			String key = null;
//			String value = null;
//			while(keys.hasNext()){
//				key=(String) keys.next();
//				value=json.get(key).toString();
//				System.out.println("return  key:"+key+"-value:"+value);
//			}
//			
//	    }catch (Exception e) {  
//	    } finally {  
//	        httpClient.getConnectionManager().shutdown();  
//	    }  
//	
//	}
	
	// 验证密码的页面跳转方法
	public String passCheck() {
		return "checkPass";
	}

	// 点击修改结算信息前进行密码验证
	public void selectSubMerInfoPass() {
		try {
			if (sysManager != null) {
				sysManager.setLevelPwd(MD5.getHashString(sysManager
						.getLevelPwd()));
				SysManager sm = sysManagerService.getSysManager(sysManager);
				SysOpLog sysOpLog = new SysOpLog();
				if (sm != null) {
					getResponse().getWriter().write("succ");
					sysOpLog.setOpDesc("成功");
				} else {
					getResponse().getWriter().write("fone");
					sysOpLog.setOpDesc("失败");
				}
				sysOpLog.setLoginName(sysManager.getLoginName()); // 登录名
				sysOpLog.setOpTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));// 操作时间
				sysOpLog.setOpUrl("subMerInfo!selectSubMerInfoPass.ac"); // 路径
				sysOpLogDao.insertSysOpLog(sysOpLog);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 查询详情 用于修改结算信息
	public String selectUpdateSettSubMerchant() {
		try {
			selectSubMerInfoPass();
			if (subMerInfo == null) {
				subMerInfo = new SubMerInfo();
			}
			subMerInfo.setSubMerId(id);
			subMerInfo = subMerInfoDao.findById(subMerInfo);
			subMerTrans = new SubMerTrans();
			subMerTrans.setSubMerId(id);
			subMerTrans = subMerTransDao.selectSubMerTransById(subMerTrans);
			bankBehalfBranchList = bankBehalfBranchDao
					.selectBankBehalfBranchList();
			regionCodeList = regionCodeService
					.selectRegionCodeList2(regionCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "selectUpdateSettSubMerchant";
	}

	public void updatet0Status() {
		try {
			if (subMerTrans == null) {
				getResponse().getWriter().write("fone");
				return;
			}
			int i = subMerTransDao.updateSubMerTransInfo(subMerTrans);
			if (i != 1) {
				getResponse().getWriter().write("fone");
			} else {
				getResponse().getWriter().write("succ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 修改结算信息
	public void updateSettSubMerchantInfo() {
		try {
			if (subMerInfo == null) {
				getResponse().getWriter().write("fone");
				return;
			}
			subMerInfo.setBillCycle(subMerInfo.getBillCycle().replace(" ", ""));
			String openBank = subMerInfo.getOpenBank();
			String region = subMerInfo.getRegion();
			String[] regions = region.split(",");
			String[] openBanks = openBank.split(",");
			openBank = openBanks[1];
			String lineNum = openBanks[0];
			String settAgency = subMerInfo.getSettAgency();

			subMerInfo.setRegion(regions[0].trim() + regions[2].trim());
			subMerInfo.setSettAgency(settAgency.split(",")[0]);
			subMerInfo.setOpenBank(openBank);
			subMerInfo.setLineNum(lineNum);
			if (subMerInfoDao.update(subMerInfo))
				getResponse().getWriter().write("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据省份编号，查询出地域编号
	 */
	public void getRegionCodeListBySuperCode() {
		getResponse().setCharacterEncoding("UTF-8");
		try {
			log.info("根据省份信息获取城市信息");
			regionCodeList = regionCodeService
					.selectRegionCodeList2(regionCode);
			JSONArray jsonArray = JSONArray.fromObject(regionCodeList);
			System.out.println(jsonArray);
			getResponse().getWriter().write(jsonArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取开户行信息
	 */
	public void getOpenBank() {
		log.info("获取开户行信息");
		try {
			List<SubMerInfo> openBankMap = subMerInfoDao
					.findLineNum(subMerInfo);
			JSONArray jsonArray = JSONArray.fromObject(openBankMap);
			System.out.println(jsonArray.toString());
			getResponse().getWriter().write(jsonArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 手动认证
	public void manual() {
		System.out.println("设置手动认证");
		try {
			if (id == null) {
				System.out.println("设置手动认证失败：" + id);
				getResponse().getWriter().write("N");
			} else {
				SubMerAuthInfo smai = new SubMerAuthInfo();
				smai.setSubMerId(id);
				smai.setAuthStatus("2");
				subMerAuthInfoDao.updateSubMerAuthInfoByStatus(smai);
				subMerTrans = new SubMerTrans();
				subMerTrans.setSubMerId(id);
				subMerTrans.setAuthStatus("2");
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");// 设置日期格式
				subMerTrans.setAuthTime(df.format(new Date()));
				subMerTransDao.updateSubMerTransInfo(subMerTrans);
				getResponse().getWriter().write("Y");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 查询子商户LOGO
	public String selectSubMerchantPicture() {
		System.out.println("获得子商户图片 subMerId="+subMerInfo.getSubMerId());
		subMerInfo = subMerInfoDao.findById(subMerInfo);
		String url = "";
		if (subMerInfo != null) {
			url = "/SubMerImages/" + subMerInfo.getMerSysId() + "/"
					+ subMerInfo.getSubMerId();
		}
//		System.out.println("执行次数：" + id + "；子商户ID：" + subMerInfo.getSubMerId());
		getAllFileName("0", url);
		return "logoLook";
	}

	// 获得子商户图片
	public void getAllFileName(String count, String url) {
		String uploadPath = rb.getString("ImagesUrl") + url;
		imgUrlList = new ArrayList<String[]>();
		try {
			System.out.println("读取图片路经：" + uploadPath);
			File root = new File(uploadPath);
			if (root.exists() && root.isDirectory()) {
				File files[] = root.listFiles();
				if (files != null) {
					for (int i = 0; i < files.length; i++) {
						if (files[i].getName() != null
								&& !"".equals(files[i].getName())
								&& files[i].getName().indexOf(".") != -1
								&& files[i].getName().indexOf("orderSign") == -1) {
							String[] img = new String[6]; // 存储图片信息
							// 得到图片的标题 3:标题 4:操作
							int imgTypeTop = files[i].getName().indexOf(".");
							System.out.println(files[i].getName().substring(0,
									imgTypeTop));
							String imgTitle = files[i].getName().substring(0,
									imgTypeTop);
							System.out.println(imgTitle);
							// 获取图片信息。
							BufferedImage sourceImg = ImageIO
									.read(new FileInputStream(uploadPath + "/"
											+ files[i].getName()));
							img[0] = uploadPath + "/" + files[i].getName(); // 图片路径
							img[1] = sourceImg.getWidth() + ""; // 页面显示图片宽度
							img[2] = sourceImg.getHeight() + ""; // 页面显示图片高度
							// 如果是查看所有图片则缩小图片分辨率
							System.out.println("缩小图片分辨率，开始！！");
							Image imageIO = ImageIO.read(new File(uploadPath
									+ "/" + files[i].getName()));
							// 图片路径
							img[0] = uploadPath + "/minImg/" + imgTitle
									+ files[i].getName();
							sourceImg = new BufferedImage(48, 64,
									BufferedImage.TYPE_INT_RGB);
							img[1] = "48"; // 图片宽度
							img[2] = "64"; // 图片高度
							sourceImg.getGraphics().drawImage(
									imageIO.getScaledInstance(Integer
											.parseInt(img[1]), Integer
											.parseInt(img[2]),
											Image.SCALE_SMOOTH), 0, 0, null);
							File f = new File(uploadPath + "/minImg/");
							if (!f.exists()) {
								f.mkdirs();
							}
							// 如果之前没有缩小分辨率的图片则保存缩小后的图片
							ImageIO.write(sourceImg, "jpg", new File(uploadPath
									+ "/minImg/" + imgTitle
									+ files[i].getName()));
							imageIO.flush();
							sourceImg.flush();
							// 根据图片名进行图片信息匹配。存入数组
							if ("headImg".equals(imgTitle)) {
								img[3] = "门头照";
								img[4] = "none;";
								img[5] = "headImg";
							} else if ("hall".equals(imgTitle)) {
								img[3] = "大厅照";
								img[4] = "none;";
								img[5] = "hall";
							} else if ("regNo".equals(imgTitle)) {
								img[3] = "营业执照";
								img[4] = "none;";
								img[5] = "regNo";
							} else if ("taxNo".equals(imgTitle)) {
								img[3] = "税务登记证";
								img[4] = "none;";
								img[5] = "taxNo";
							} else if ("occNo".equals(imgTitle)) {
								img[3] = "组织机构代码证";
								img[4] = "none;";
								img[5] = "occNo";
							} else if ("idCard".equals(imgTitle)) {
								img[3] = "身份证照片";
								img[4] = "none;";
								img[5] = "idCard";
							} else if ("idCardBack".equals(imgTitle)) {
								img[3] = "身份证照片反面";
								img[4] = "none;";
								img[5] = "idCardBack";
							} else if ("person".equals(imgTitle)) {
								img[3] = "本人手持身份证照";
								img[4] = "inline;";
								img[5] = "person";
							} else if ("other1".equals(imgTitle)) {
								img[3] = "其他1";
								img[4] = "none;";
								img[5] = "other1";
							} else if ("other2".equals(imgTitle)) {
								img[3] = "其他2";
								img[4] = "none;";
								img[5] = "other2";
							} else if ("bankPermit".equals(imgTitle)) {
								img[3] = "银行开户许可证";
								img[4] = "none;";
								img[5] = "bankPermit";
							} else if ("card".equals(imgTitle)) {
								img[3] = "银行卡";
								img[4] = "none;";
								img[5] = "card";
							} else if ("cardBack".equals(imgTitle)) {
								img[3] = "银行卡反面";
								img[4] = "none;";
								img[5] = "cardBack";
							}  else {
								img[3] = "未命名";
								img[4] = "none;";
								img[5] = "1111";
							}
							// 如果当前图片为个人大头照则将图片信息存入第一位
							if (img[4].equals("inline;")) {
								if (imgUrlList.size() > 0) {
									String[] ss = (String[]) imgUrlList.get(0);
									imgUrlList.set(0, img);
									imgUrlList.add(ss);
								} else
									imgUrlList.add(img);
							} else
								imgUrlList.add(img);
							// 控制台输出图片信息
							System.out.println("url:" + img[0] + ";\n width:"
									+ img[1] + ";\n height:" + img[2]
									+ ";\n title:" + img[3] + ";\n 操作："
									+ img[4]);
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获得子商户图片用
	public String getImgIO() {
		try {
			FileInputStream is = new FileInputStream(imgRealPath);
			int i = is.available(); // 得到文件大小
			byte data[] = new byte[i];
			is.read(data); // 读数据
			is.close();
			getResponse().setContentType("image/*"); // 设置返回的文件类型
			OutputStream toClient = getResponse().getOutputStream(); // 得到向客户端输出二进制数据的对象
			toClient.write(data); // 输出数据
			toClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void getMaxImgageUrl() {
		try {
			if (imgRealPath.indexOf("/minImg/") > 0) {
				// /usr\local\New_ProjectFiles\New_acqPosPlatform\SubMerImages
				// \27321323503\173665683924960\
				String url = imgRealPath.substring(0, imgRealPath
						.indexOf("/minImg/"));
				// regNoregNo.png
				String ss = imgRealPath.substring(imgRealPath
						.indexOf("/minImg/") + 8);
				// .jpg
				String type = ss.substring(ss.indexOf("."), ss.length());
				// regNo
				ss = ss.substring(0, ss.indexOf(".") / 2);
				System.out.println("图片名：" + ss + type);
				imgRealPath = url + "/" + ss + type;
				BufferedImage sourceImg = ImageIO.read(new FileInputStream(
						imgRealPath));
				String[] imgInfo = new String[3];
				imgInfo[0] = imgRealPath;// 图片路径
				imgInfo[1] = sourceImg.getWidth() + "";// 图片宽度
				imgInfo[2] = (sourceImg.getHeight() + 50) + "";// 图片高度
				getResponse().getWriter().write(
						imgInfo[0] + "|" + imgInfo[1] + "|" + imgInfo[2]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询所有未上线的子商户
	 * 
	 * @return
	 */
	public void selectSubMerchantNoOnline() {
		try {
			if (subMerInfo == null) {
				subMerInfo = new SubMerInfo();
			}
			subMerInfo.setStatus("1");
			Map map = PageUtil.getPageMap(page, pageSize);
			map.put("subMerInfo", subMerInfo);
			List<SubMerInfo> subMerInfoList = subMerInfoDao
					.selectSubMerchantNoOnline(map);
			int count = subMerInfoDao.selectSubMerchantNoOnlineCount(map);
			JSONArray array = JSONArray.fromObject(subMerInfoList);
			JSONObject object = new JSONObject();
			object.put("Rows", array.toString());
			object.put("Total", count);
			subMerList = object.toString();
			getResponse().getWriter().write(subMerList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(subMerList);
	}

	public boolean getFileSize() {
		boolean flag = false;
		int size = 0;
		for (File uploadFile : file) {
			if (uploadFile != null) {
				try {
					size += new FileInputStream(uploadFile).available();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (size <= 4194304) {
			flag = true;
		}
		return flag;
	}

	// 图片上传
	public String uploadImgs() {
		try {
			System.out.println("123");
			String subMerId = this.getId();
			if (subMerId != null) {
				if (!getFileSize()) {
					id = "3";
					return "uploadYes";
				}
				Map<String, String> map = new HashMap<String, String>();
				map.put("regNo", "regNo");// 营业执照
				map.put("taxNo", "taxNo");// 税务登记证
				map.put("occNo", "occNo");// 组织机构代码证
				map.put("idCard", "idCard");// 身份证照片
				map.put("idCardBack", "idCardBack");// 身份证照片
				map.put("person", "person");// 个人大头照
				map.put("bankPermit", "bankPermit");// 银行开户许可证
				map.put("headImg", "headImg");// 门头照
				map.put("hall", "hall");// 大厅照
				map.put("other1", "other1");// 其他1
				map.put("other2", "other2");// 其他2
				map.put("card", "card");// 银行卡
				map.put("cardBack", "cardBack");// 银行卡
				// String currentId = new SimpleDateFormat("yyyyMMddHHmmss")
				// .format(new Date());
				// 查询机构号 开始
				subMerInfo = new SubMerInfo();
				subMerInfo.setSubMerId(id);
				subMerInfo = subMerInfoDao.findById(subMerInfo);
				if (subMerInfo != null) {// 确定查询到机构号，修改路径
					subMerId = "/SubMerImages/" + subMerInfo.getMerSysId()
							+ "/" + id;
				}
				id = "2";// 标记！页面提示 查询机构号结束
				// 设置上传文件目录
				String uploadPath = rb.getString("ImagesUrl") + subMerId;
				// 判断文件夹是否在，不存在就创建
				File uploadfile = new File(uploadPath);
				if (!uploadfile.exists() && !uploadfile.isDirectory()) {
					// 不存在
					uploadfile.mkdirs();
				}
				boolean d = true; // 存储删除文件的结果
				String mapKey = ""; // 存储map的键
				// 批量读取图片文件
				if (file != null && file.size() > 0) {
					for (int i = 0; i < file.size(); i++) {
						String imgName = fileFileName.get(i);
						if (regNoImgFile.endsWith(imgName)) {
							mapKey = "regNo";
						} else if (taxNoImgFile.endsWith(imgName)) {
							mapKey = "taxNo";
						} else if (occNoImgFile.endsWith(imgName)) {
							mapKey = "occNo";
						} else if (idCardImgFile.endsWith(imgName)) {
							mapKey = "idCard";
						} else if (idCardBackImgFile.endsWith(imgName)) {
							mapKey = "idCardBack";
						} else if (personImgFile.endsWith(imgName)) {
							mapKey = "person";
						} else if (bankPermitImgFile.endsWith(imgName)) {
							mapKey = "bankPermit";
						} else if (headImgFile.endsWith(imgName)) {
							mapKey = "headImg";
						} else if (hallImgFile.endsWith(imgName)) {
							mapKey = "hall";
						} else if (otherImgFile1.endsWith(imgName)) {
							mapKey = "other1";
						} else if (otherImgFile2.endsWith(imgName)) {
							mapKey = "other2";
						} else if (cardImgFile.endsWith(imgName)) {
							mapKey = "card";
						} else if (cardBackImgFile.endsWith(imgName)) {
							mapKey = "cardBack";
						}
						// 拼接：文件名+格式
						String uploadName = map.get(mapKey) + ".jpg";
						System.out.println("文件名：" + uploadName);
						// //定义图片的格式
						// String []
						// imgType={".jpg",".png","bmp","tiff","gif","pcx",
						// "tga","exif","fpx","svg","psd","cdr",
						// "pcd","dxf","ufo","eps","ai","raw"};
						// //每种不同格式但同名的图片都进行删除操作
						// for (int j2 = 0; j2 < imgType.length; j2++) {
						// File fileOne=new File(uploadPath+
						// "/"+map.get(mapKey)+imgType[j2]);
						// if(fileOne.exists()){
						// System.out.println("删除文件");
						// d = fileOne.delete();
						// }
						// }
						if (d) {// 进行上传操作
							ys(uploadPath, uploadName, file.get(i));
						} else {
							this.id = "1";
						}
					}
				}
			}
		} catch (Exception e) {
			this.id = "1";
			e.printStackTrace();
		}
		return "uploadYes";
	}

	public void ys(String uploadPath, String uploadName, File file)
			throws IOException {
		String fileName = uploadPath + "/" + uploadName;
		// 获取图片信息。
		BufferedImage sourceImg = ImageIO.read(new FileInputStream(file));
		int width = sourceImg.getWidth(); // 页面显示图片宽度
		int height = sourceImg.getHeight();
		Double px = 1D;
		// 页面显示图片高度
		// 如果是查看所有图片则缩小图片分辨率
//		if (width > 400 || height > 300) {
//			Double widthPx = Double.parseDouble(width + "") / 400;
//			Double heightPx = Double.parseDouble(height + "") / 300;
//			if (heightPx > widthPx) {
//				px = heightPx;
//			} else {
//				px = widthPx;
//			}
//		}
		Image imageIO = ImageIO.read(file);
		int w = Integer.parseInt((width / px + "").substring(0,
				(width / px + "").indexOf(".")));
		int h = Integer.parseInt((height / px + "").substring(0,
				(height / px + "").indexOf(".")));
		sourceImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		sourceImg.getGraphics().drawImage(
				imageIO.getScaledInstance(Integer.parseInt((width / px + "")
						.substring(0, (width / px + "").indexOf("."))), Integer
						.parseInt((height / px + "").substring(0,
								(height / px + "").indexOf("."))),
						Image.SCALE_SMOOTH), 0, 0, null);
		ImageIO.write(sourceImg, "jpg", new File(fileName));
	}

	/**
	 * 
	 * @Description: 修改T+0状态
	 * @Auther: ljl
	 * @Date: 2014-9-24 下午3:37:39
	 */
	public void changet0Status() {
		if (subMerTrans == null) {
			subMerTrans = new SubMerTrans();
		}
		Integer flag = subMerTransDao.updateSubMerTransInfo(subMerTrans);
		try {
			if (flag > 0) {
				this.renderText("true");
			} else {
				this.renderText("false");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// ////////////////////////////////GET
	// SET//////////////////////////////////////////////////

	public SubMerInfoDao getSubMerInfoDao() {
		return subMerInfoDao;
	}

	public String getIdCardBackImgFile() {
		return idCardBackImgFile;
	}

	public void setIdCardBackImgFile(String idCardBackImgFile) {
		this.idCardBackImgFile = idCardBackImgFile;
	}

	public String getCardBackImgFile() {
		return cardBackImgFile;
	}

	public void setCardBackImgFile(String cardBackImgFile) {
		this.cardBackImgFile = cardBackImgFile;
	}

	public String getRateStatus() {
		return rateStatus;
	}

	public void setRateStatus(String rateStatus) {
		this.rateStatus = rateStatus;
	}

	public String getTransHighestFee() {
		return transHighestFee;
	}

	public void setTransHighestFee(String transHighestFee) {
		this.transHighestFee = transHighestFee;
	}

	public List<SubMerTerminal> getListSubMerTerminal() {
		return listSubMerTerminal;
	}

	public void setListSubMerTerminal(List<SubMerTerminal> listSubMerTerminal) {
		this.listSubMerTerminal = listSubMerTerminal;
	}

	public SubMerTerminalDao getSubMerTerminalDao() {
		return subMerTerminalDao;
	}

	public void setSubMerTerminalDao(SubMerTerminalDao subMerTerminalDao) {
		this.subMerTerminalDao = subMerTerminalDao;
	}

	public SubMerTerminal getSubMerTerminal() {
		return subMerTerminal;
	}

	public void setSubMerTerminal(SubMerTerminal subMerTerminal) {
		this.subMerTerminal = subMerTerminal;
	}

	public void setSubMerInfoDao(SubMerInfoDao subMerInfoDao) {
		this.subMerInfoDao = subMerInfoDao;
	}

	public Map getParam() {
		return param;
	}

	public void setParam(Map map) {
		this.param = map;
	}

	public List<SubMerInfo> getListSubMerInfo() {
		return listSubMerInfo;
	}

	public void setListSubMerInfo(List<SubMerInfo> listSubMerInfo) {
		this.listSubMerInfo = listSubMerInfo;
	}

	public SubMerInfo getSubMerInfo() {
		return subMerInfo;
	}

	public void setSubMerInfo(SubMerInfo subMerInfo) {
		this.subMerInfo = subMerInfo;
	}

	public String getSubMerList() {
		return subMerList;
	}

	public void setSubMerList(String subMerList) {
		this.subMerList = subMerList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public SubMerTrans getSubMerTrans() {
		return subMerTrans;
	}

	public void setSubMerTrans(SubMerTrans subMerTrans) {
		this.subMerTrans = subMerTrans;
	}

	public ResourceBundle getRb() {
		return rb;
	}

	public void setRb(ResourceBundle rb) {
		this.rb = rb;
	}

	public BankBehalfBranchDao getBankBehalfBranchDao() {
		return bankBehalfBranchDao;
	}

	public void setBankBehalfBranchDao(BankBehalfBranchDao bankBehalfBranchDao) {
		this.bankBehalfBranchDao = bankBehalfBranchDao;
	}

	public List<BankBehalfBranch> getBankBehalfBranchList() {
		return bankBehalfBranchList;
	}

	public void setBankBehalfBranchList(
			List<BankBehalfBranch> bankBehalfBranchList) {
		this.bankBehalfBranchList = bankBehalfBranchList;
	}

	public List getImgUrlList() {
		return imgUrlList;
	}

	public void setImgUrlList(List imgUrlList) {
		this.imgUrlList = imgUrlList;
	}

	public String getImgRealPath() {
		return imgRealPath;
	}

	public void setImgRealPath(String imgRealPath) {
		this.imgRealPath = imgRealPath;
	}

	public MerManagerDao getMerManagerDao() {
		return merManagerDao;
	}

	public void setMerManagerDao(MerManagerDao merManagerDao) {
		this.merManagerDao = merManagerDao;
	}

	public SysManager getSysManager() {
		return sysManager;
	}

	public void setSysManager(SysManager sysManager) {
		this.sysManager = sysManager;
	}

	public SysManagerService getSysManagerService() {
		return sysManagerService;
	}

	public void setSysManagerService(SysManagerService sysManagerService) {
		this.sysManagerService = sysManagerService;
	}

	public SysOpLogDao getSysOpLogDao() {
		return sysOpLogDao;
	}

	public void setSysOpLogDao(SysOpLogDao sysOpLogDao) {
		this.sysOpLogDao = sysOpLogDao;
	}

	public MerInfoUpdateDao getMerInfoUpdateDao() {
		return merInfoUpdateDao;
	}

	public void setMerInfoUpdateDao(MerInfoUpdateDao merInfoUpdateDao) {
		this.merInfoUpdateDao = merInfoUpdateDao;
	}

	public SubMerRateDao getSubMerRateDao() {
		return subMerRateDao;
	}

	public void setSubMerRateDao(SubMerRateDao subMerRateDao) {
		this.subMerRateDao = subMerRateDao;
	}

	public List<SubMerRate> getSubMerRateList() {
		return subMerRateList;
	}

	public void setSubMerRateList(List<SubMerRate> subMerRateList) {
		this.subMerRateList = subMerRateList;
	}

	public SubMerRate getSubMerRate() {
		return subMerRate;
	}

	public void setSubMerRate(SubMerRate subMerRate) {
		this.subMerRate = subMerRate;
	}

	public String getImgType() {
		return imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}

	public String getRegNoImgFile() {
		return regNoImgFile;
	}

	public void setRegNoImgFile(String regNoImgFile) {
		this.regNoImgFile = regNoImgFile;
	}

	public String getTaxNoImgFile() {
		return taxNoImgFile;
	}

	public void setTaxNoImgFile(String taxNoImgFile) {
		this.taxNoImgFile = taxNoImgFile;
	}

	public String getOccNoImgFile() {
		return occNoImgFile;
	}

	public void setOccNoImgFile(String occNoImgFile) {
		this.occNoImgFile = occNoImgFile;
	}

	public String getIdCardImgFile() {
		return idCardImgFile;
	}

	public void setIdCardImgFile(String idCardImgFile) {
		this.idCardImgFile = idCardImgFile;
	}

	public String getPersonImgFile() {
		return personImgFile;
	}

	public void setPersonImgFile(String personImgFile) {
		this.personImgFile = personImgFile;
	}

	public String getBankPermitImgFile() {
		return bankPermitImgFile;
	}

	public void setBankPermitImgFile(String bankPermitImgFile) {
		this.bankPermitImgFile = bankPermitImgFile;
	}

	public String getHeadImgFile() {
		return headImgFile;
	}

	public void setHeadImgFile(String headImgFile) {
		this.headImgFile = headImgFile;
	}

	public String getHallImgFile() {
		return hallImgFile;
	}

	public void setHallImgFile(String hallImgFile) {
		this.hallImgFile = hallImgFile;
	}

	public String getOtherImgFile1() {
		return otherImgFile1;
	}

	public void setOtherImgFile1(String otherImgFile1) {
		this.otherImgFile1 = otherImgFile1;
	}

	public String getOtherImgFile2() {
		return otherImgFile2;
	}

	public void setOtherImgFile2(String otherImgFile2) {
		this.otherImgFile2 = otherImgFile2;
	}

	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public List<String> getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}

	public List<String> getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}

	public MccCodeDao getMccCodeDao() {
		return mccCodeDao;
	}

	public void setMccCodeDao(MccCodeDao mccCodeDao) {
		this.mccCodeDao = mccCodeDao;
	}

	public List<MccCode> getMccCodeList() {
		return mccCodeList;
	}

	public void setMccCodeList(List<MccCode> mccCodeList) {
		this.mccCodeList = mccCodeList;
	}

	public RegionCodeDao getRegionCodeDao() {
		return regionCodeDao;
	}

	public void setRegionCodeDao(RegionCodeDao regionCodeDao) {
		this.regionCodeDao = regionCodeDao;
	}

	public List<RegionCode> getRegionCodeList() {
		return regionCodeList;
	}

	public void setRegionCodeList(List<RegionCode> regionCodeList) {
		this.regionCodeList = regionCodeList;
	}

	public MerTransDao getMerTransDao() {
		return merTransDao;
	}

	public void setMerTransDao(MerTransDao merTransDao) {
		this.merTransDao = merTransDao;
	}

	public MerTrans getMerTrans() {
		return merTrans;
	}

	public void setMerTrans(MerTrans merTrans) {
		this.merTrans = merTrans;
	}

	public TractInfoDao getTractInfoDao() {
		return tractInfoDao;
	}

	public void setTractInfoDao(TractInfoDao tractInfoDao) {
		this.tractInfoDao = tractInfoDao;
	}

	public List<TractInfo> getTractInfoList() {
		return tractInfoList;
	}

	public void setTractInfoList(List<TractInfo> tractInfoList) {
		this.tractInfoList = tractInfoList;
	}

	public SubMerAuthInfoDao getSubMerAuthInfoDao() {
		return subMerAuthInfoDao;
	}

	public void setSubMerAuthInfoDao(SubMerAuthInfoDao subMerAuthInfoDao) {
		this.subMerAuthInfoDao = subMerAuthInfoDao;
	}

	public SubMerTransDao getSubMerTransDao() {
		return subMerTransDao;
	}

	public void setSubMerTransDao(SubMerTransDao subMerTransDao) {
		this.subMerTransDao = subMerTransDao;
	}

	public MerTractDao getMerTractDao() {
		return merTractDao;
	}

	public void setMerTractDao(MerTractDao merTractDao) {
		this.merTractDao = merTractDao;
	}

	public List<MerTract> getMerTractList() {
		return merTractList;
	}

	public void setMerTractList(List<MerTract> merTractList) {
		this.merTractList = merTractList;
	}

	public String getCardImgFile() {
		return cardImgFile;
	}

	public void setCardImgFile(String cardImgFile) {
		this.cardImgFile = cardImgFile;
	}

	public OrderInfoDao getOrderInfoDao() {
		return orderInfoDao;
	}

	public void setOrderInfoDao(OrderInfoDao orderInfoDao) {
		this.orderInfoDao = orderInfoDao;
	}

	public OrderStatictisDao getOrderStatictisDao() {
		return orderStatictisDao;
	}

	public void setOrderStatictisDao(OrderStatictisDao orderStatictisDao) {
		this.orderStatictisDao = orderStatictisDao;
	}

	public ProcedureDao getProcedureDao() {
		return procedureDao;
	}

	public void setProcedureDao(ProcedureDao procedureDao) {
		this.procedureDao = procedureDao;
	}

	public MobileUserDao getMobileUserDao() {
		return mobileUserDao;
	}

	public void setMobileUserDao(MobileUserDao mobileUserDao) {
		this.mobileUserDao = mobileUserDao;
	}

	public SubMerTerminalInfoDao getSubMerTerminalInfoDao() {
		return subMerTerminalInfoDao;
	}

	public void setSubMerTerminalInfoDao(
			SubMerTerminalInfoDao subMerTerminalInfoDao) {
		this.subMerTerminalInfoDao = subMerTerminalInfoDao;
	}

	public RegionCodeService getRegionCodeService() {
		return regionCodeService;
	}

	public void setRegionCodeService(RegionCodeService regionCodeService) {
		this.regionCodeService = regionCodeService;
	}

	public RegionCode getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(RegionCode regionCode) {
		this.regionCode = regionCode;
	}

    public List<ChannelAddInfo> getChannelAddInfoList() {
        return channelAddInfoList;
    }

    public void setChannelAddInfoList(List<ChannelAddInfo> channelAddInfoList) {
        this.channelAddInfoList = channelAddInfoList;
    }

    public List<ChannelMerInfo> getChannelMerInfoList() {
        return channelMerInfoList;
    }

    public void setChannelMerInfoList(List<ChannelMerInfo> channelMerInfoList) {
        this.channelMerInfoList = channelMerInfoList;
    }

    public ChannelMerInfo getChannelMerInfo() {
        return channelMerInfo;
    }

    public void setChannelMerInfo(ChannelMerInfo channelMerInfo) {
        this.channelMerInfo = channelMerInfo;
    }
	
	
}

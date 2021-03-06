package com.bypay.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.Ostermiller.util.MD5;
import com.bypay.common.BaseAction;
import com.bypay.dao.AgenctInfoDao;
import com.bypay.dao.CheckCreditDao;
import com.bypay.dao.CoreTransLogDao;
import com.bypay.dao.DyPayInfoDao;
import com.bypay.dao.MerTransDao;
import com.bypay.dao.OrderFrozenDao;
import com.bypay.dao.OrderInfoDao;
import com.bypay.dao.OrderProfitDao;
import com.bypay.dao.OrderStatictisDao;
import com.bypay.dao.ProcedureDao;
import com.bypay.dao.ScanOrderInfoDao;
import com.bypay.dao.SubMerCashoutDao;
import com.bypay.dao.SubMerInfoDao;
import com.bypay.dao.SysOpLogDao;
import com.bypay.domain.AgenctInfo;
import com.bypay.domain.CheckCredit;
import com.bypay.domain.CoreTransLog;
import com.bypay.domain.DyPayInfo;
import com.bypay.domain.MerTrans;
import com.bypay.domain.OrderFrozen;
import com.bypay.domain.OrderInfo;
import com.bypay.domain.OrderProfit;
import com.bypay.domain.ScanOrderInfo;
import com.bypay.domain.SubMerCashout;
import com.bypay.domain.SubMerInfo;
import com.bypay.domain.SysManager;
import com.bypay.domain.SysOpLog;
import com.bypay.domain.TractInfo;
import com.bypay.domain.clientTool.TRAN_REQ;
import com.bypay.domain.clientTool.TRAN_RESP;
import com.bypay.service.OrderInfoService;
import com.bypay.service.SubMerInfoService;
import com.bypay.service.SysManagerService;
import com.bypay.service.TractInfoService;
import com.bypay.util.AddressUtil;
import com.bypay.util.DateUtil;
import com.bypay.util.ImportExcelFile;
import com.bypay.util.JzjrUtil;
import com.bypay.util.Md5Util;
import com.bypay.util.PageUtil;
import com.bypay.util.StringEncrypt;
import com.google.common.collect.Lists;
import com.richerpay.core.model.CoreTransInfo;
import com.richerpay.dubbo.service.NewPayService;

public class OrderInfoAction extends BaseAction {
	private Logger logger = LoggerFactory.getLogger(OrderInfoAction.class);
	@Inject
	private SysOpLogDao sysOpLogDao;
	private OrderInfo orderInfo;
	@Inject
	private SysManagerService sysManagerService;
	@Inject
	private OrderInfoService orderInfoService;
	@Inject
	private SubMerInfoService subMerInfoService;
	@Inject
	private TractInfoService tractInfoService;
	@Inject
	private OrderStatictisDao orderStatictisDao;
	@Inject
	private SubMerInfoDao subMerInfoDao;
	@Inject
	private OrderFrozenDao orderFrozenDao;
	@Inject
	private MerTransDao merTransDao;
	@Inject
	private AgenctInfoDao agenctInfoDao;
	@Inject
	private SubMerCashoutDao subMerCashoutDao;
	@Inject
	private OrderInfoDao orderInfoDao;
	@Inject
	private ProcedureDao procedureDao; // 机构通道Dao
	@Inject
	private OrderProfitDao orderProfitDao;
	@Inject
	private CheckCreditDao checkCreditDao;
   @Inject
    private CoreTransLogDao coreTransLogDao;
   @Inject
   private DyPayInfoDao dyPayInfoDao;
   @Inject
   private ScanOrderInfoDao scanOrderInfoDao;
	private OrderProfit orderProfit;
	@Autowired
	private NewPayService newPayService;
//	@Autowired
//	private JPayRemoteService jPayRemoteService;
	
	private List<OrderInfo> orderInfoList;
	private List<OrderFrozen> orderFrozenList;
	private SysManager sysManager;
	private OrderFrozen orderFrozen;
	private String orderList;
	private OrderInfo orderDetailInfo;
	private Map totalMap;
	static ResourceBundle rb = ResourceBundle.getBundle("com/bypay/config/bmtmUtil", Locale.getDefault());
	private String orderSign;
	private DyPayInfo dyPayInfo;
	private ScanOrderInfo scanOrderInfo;
	
	// 查询条件
	private String busMerId; // 商户号
	private String orderId; // 订单号
	private String merOrderId; // 商户订单号
	private String userMobile; // 手机号
	private String settleDate; // 清算时间
	private String merOrderTime; // 商户交易时间
	private String transQid; // 交易流水号
	private String transType; // 交易类型
	private String orderStatus; // 交易状态
	private String agentIdL1; // 一级代理商号
	private String agentIdL2; // 二级代理商号
	private String terminalId; // 终端号
	private String merSysId; // 机构商户号
	private String cardNo; // 卡号
	private String terminalSerialNumber; // orderInfo 终端号
	private String busType; // 业务类别
	private String shipmentStatus; // 出货状态
	private String startTime;
	private String endTime;
	private String refundStatus;//退款状态
	private String signStatus;//签名状态
	private String orderRateType;//费率类型
	private String orderStatictis;//清算状态 1：不清算
	private String t0Status;       //t0标识      1开通  0不开通
	
	// 分页开始
	private int page = 1;
	private int pageSize = 15;
	
	public String getSignStatus() {
		return signStatus;
	}
	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}
	public String getOrderRateType() {
		return orderRateType;
	}
	public void setOrderRateType(String orderRateType) {
		this.orderRateType = orderRateType;
	}
	
	public String getT0Status() {
        return t0Status;
    }
    public void setT0Status(String t0Status) {
        this.t0Status = t0Status;
    }

    //代付订单同步ID
	private String payOrderId;
	
	public DyPayInfo getDyPayInfo() {
        return dyPayInfo;
    }
    public void setDyPayInfo(DyPayInfo dyPayInfo) {
        this.dyPayInfo = dyPayInfo;
    }
    
    public ScanOrderInfo getScanOrderInfo() {
        return scanOrderInfo;
    }
    public void setScanOrderInfo(ScanOrderInfo scanOrderInfo) {
        this.scanOrderInfo = scanOrderInfo;
    }
    /**
	 * 
	 * 冻结订单
	 */
	public void forzenSum(){
		JSONObject jsonObject = new JSONObject();
		try {
			int data = Integer.parseInt(DateUtil.getDate("HH"));
			if(data < 2){
				jsonObject.put("msg", "00:00:00 至 02:00:00 内，不可操作");
				getResponse().getWriter().write(jsonObject.toString());
				return;
			}
			String orderId = getRequest().getParameter("orderId");
			String status = getRequest().getParameter("status");
			Integer i = orderStatictisDao.selectOrderStatictisOrderId(orderId);
			if(i != 1){
				jsonObject.put("msg", "该订单未清算，无法操作");
				getResponse().getWriter().write(jsonObject.toString());
				return;
			}
			orderInfo = orderInfoService.getOrderInfo(orderId);
			SubMerInfo subMerInfo = new SubMerInfo();
			subMerInfo.setSubMerId(orderInfo.getSubMerId());
			subMerInfo.setFrozenSumMax(("2".equals(status)?"":"-")+orderInfo.getMerAmt());
			if("3".equals(status)){
				subMerInfo.setFrozenSum("-"+orderInfo.getMerAmt());
			}
			orderFrozen = new OrderFrozen();
			orderFrozen.setOrderId(orderId);
			orderFrozen.setSubMerId(orderInfo.getSubMerId());
			orderFrozen.setFrozenStatus(status);
			if("1".equals(status)){
				orderFrozen.setReturnTime(DateUtil.getDate("yyyy-MM-dd hh:mm:ss"));
			}else if("2".equals(status)){
				orderFrozen.setFrozenTime(DateUtil.getDate("yyyy-MM-dd hh:mm:ss"));
			}else if("3".equals(status)){
				OrderFrozen orderFrozen1 = orderFrozenDao.selectOrderFrozenByOrderId(orderId);
				String frozenTime = orderFrozen1.getFrozenTime();
				if(frozenTime == null){
					jsonObject.put("msg", "该订单没有冻结时间，无法扣除");
					getResponse().getWriter().write(jsonObject.toString());
					return;
				}
				String deductionTime = DateUtil.getDate("yyyy-MM-dd");
				if(frozenTime.indexOf(deductionTime) != -1){
					jsonObject.put("msg", "冻结当天不可扣除");
					getResponse().getWriter().write(jsonObject.toString());
					return;
				}
				orderFrozen.setDeductionTime(frozenTime);
			}
			if("2".equals(status)){
				OrderFrozen orderFrozen1 = orderFrozenDao.selectOrderFrozenByOrderId(orderId);
				if(orderFrozen1 == null){
					orderFrozen.setReserved1(DateUtil.getDate("yyyyMMdd"));
					orderFrozenDao.insertOrderFrozen(orderFrozen);
				}else{
					orderFrozenDao.updateOrderFrozen(orderFrozen);
				}
			}else{
				orderFrozenDao.updateOrderFrozen(orderFrozen);
			}
			subMerInfoDao.update(subMerInfo);
			jsonObject.put("msg", "操作成功");
			getResponse().getWriter().write(jsonObject.toString());
		} catch (Exception e) {
			try {
				jsonObject.put("msg", "出错，请记录出错时间并联系管理员");
				getResponse().getWriter().write(jsonObject.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * 查询冻结订单
	 */
	public void selectOrderFrozen(){
		try {
			if(orderFrozen == null){
				orderFrozen = new OrderFrozen();
			}
			Map map = PageUtil.getPageMap(page,pageSize);
			map.put("orderFrozen", orderFrozen);
			orderFrozenList = orderFrozenDao.selectOrderFrozen(map);
			int count = orderFrozenDao.selectOrderFrozenCount(map);
			JSONArray array = JSONArray.fromObject(orderFrozenList);
			JSONObject object = new JSONObject();
			object.put("Rows", array.toString());
			object.put("Total", count);
			getResponse().getWriter().write(object.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 历史订单退款
	 * @throws IOException 
	 */
	public void orderRefund() throws IOException {
		String rest = "授权人ID或密码错误！";
		if(sysManager != null && null != sysManager.getLevelPwd().trim() && !"".equals(sysManager.getLevelPwd().trim())
				&& null != sysManager.getLoginName().trim() && !"".equals(sysManager.getLoginName().trim())) {
			SysManager manager = new SysManager();
			manager.setLoginName(sysManager.getLoginName());
			manager.setLevelPwd(MD5.getHashString(sysManager.getLevelPwd()));
			manager = sysManagerService.getSysManager(manager);
			if(null != manager) {				
				if(orderInfo != null && null != orderInfo.getOrderId() && !"".equals(orderInfo.getOrderId())) {
					orderInfo = orderInfoService.getOrderInfo(orderInfo.getOrderId());
					String str = orderInfoService.orderRefund(orderInfo);
					System.out.println("str" + str);
					String[] re =  str.split("\\|");
					rest = re[2];
					getResponse().getWriter().write(re[2]);//结果描述
				}
			} else {
				getResponse().getWriter().write("2");
			}
		} else {
			getResponse().getWriter().write("2");
		}
		//将退款操作记录到数据库日志
		HttpServletRequest request = ServletActionContext.getRequest();
		SysOpLog sysOpLog = new SysOpLog();
		sysOpLog.setLoginName(sysManager.getLoginName());
		sysOpLog.setOpDesc("退款操作");
		sysOpLog.setOpUrl("/orderInfo!orderRefund.ac");
		sysOpLog.setIp(AddressUtil.getIP(request));
		sysOpLog.setOpTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		sysOpLog.setRemark(rest);
		sysOpLogDao.insertSysOpLog(sysOpLog);
	}

	/**
	 * 应用交易查询
	 */
	public void selectApplyList() {
		Map<Object, Object> maps = new HashMap<Object, Object>();
		maps.put("subMerId", busMerId);
		maps.put("orderId", orderId);
		maps.put("merOrderId", merOrderId);
		maps.put("userMobile", userMobile);
		maps.put("agentIdL1", agentIdL1);
		maps.put("agentIdL2", agentIdL2);
		maps.put("cardNo", cardNo);
		maps.put("merOrderTime", merOrderTime);
		maps.put("transType", transType);
		maps.put("terminalId", terminalSerialNumber);
		maps.put("orderStatus", orderStatus);
		maps.put("startTime", startTime);
		maps.put("endTime", endTime);
		maps.put("merSysId", merSysId);
		maps.put("busType", busType);
		totalMap = orderInfoService.selectApplyCount(maps);
		int count = Integer.parseInt(totalMap.get("count").toString());
		double sumMerAmt = Double.parseDouble(totalMap.get("sumMerAmt").toString());
		double sumTransFee = Double.parseDouble(totalMap.get("sumTransFee").toString());
		Map map = PageUtil.getPageMap(page, pageSize);
		map.putAll(maps);
		orderInfoList = orderInfoService.selectApplyList(map);
		OrderInfo info = null;
		String time;
		// 时间格式转换
		for (int i = 0; i < orderInfoList.size(); i++) {
			info = orderInfoList.get(i);
			time = info.getMerOrderTime();
			if (time != null && time.length() == 14) {
				time = time.substring(0, 4) + "-" + time.substring(4, 6) + "-"
						+ time.substring(6, 8) + " " + time.substring(8, 10)
						+ ":" + time.substring(10, 12) + ":"
						+ time.substring(12);
				info.setMerOrderTime(time);
			} else {
				continue;
			}
		}
		DecimalFormat df = new DecimalFormat("#0.00");
		
		JSONArray array = JSONArray.fromObject(orderInfoList);
		JSONObject object = new JSONObject();
		object.put("Rows", array.toString());
		object.put("Total", count);
		object.put("sumMerAmt", df.format(sumMerAmt/100));
		object.put("sumTransFee", df.format(sumTransFee/100));
		orderList = object.toString();
		System.out.println(orderList);
		try {
			getResponse().getWriter().write(orderList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询当天交易信息
	 */
	@SuppressWarnings("unchecked")
	public void selectOrderTheDay() {
	  Map maps = new HashMap();
      maps.put("createDate", DateUtil.getDate("yyyyMMdd"));
      maps.put("subMerId", busMerId);
      maps.put("orderId", orderId);
      maps.put("merOrderId", merOrderId);
      maps.put("userMobile", userMobile);
      maps.put("agentIdL1", agentIdL1);
      maps.put("agentIdL2", agentIdL2);
      maps.put("merOrderTime", merOrderTime);
      maps.put("transType", transType);
      maps.put("orderStatus", orderStatus);
      maps.put("merSysId", merSysId);
      maps.put("terminalId", terminalSerialNumber);
      maps.put("cardNo", cardNo);
      maps.put("signStatus", signStatus);
      maps.put("transMerId", this.getParameterForString("transMerId"));
	  maps.put("orderStatictis", this.getParameterForString("orderStatictis"));
	  
	  String logName = (String) getSession().getAttribute("userName");
	  //特殊用户只看外卡
	  if(logName.equals("whf")){
	      maps.put("orderRateTypes", "'31','32','33','34','35','36','37','38','39','40'");
	  }else{
	      maps.put("orderRateType", orderRateType);
	      maps.put("orderRateTypes", null);
	  }
	  
      totalMap = orderInfoService.selectOrderTheDateCount(maps);
      int count = Integer.parseInt(totalMap.get("count").toString());
      double sumMerAmt = 0;
      if(null != totalMap.get("sumMerAmt")) {
          sumMerAmt = Double.parseDouble(totalMap.get("sumMerAmt").toString());
      }
      double sumTransFee = 0;
      if(null != totalMap.get("sumTransFee")) {
          sumTransFee = Double.parseDouble(totalMap.get("sumTransFee").toString());
      }
      Map map = PageUtil.getPageMap(page, pageSize);
      map.putAll(maps);
      
      orderInfoList = orderInfoService.selectOrderTheDate(map);
      OrderInfo info = null;
      String time;
      // 格式转换
      for (int i = 0; i < orderInfoList.size(); i++) {
          info = orderInfoList.get(i);
          time = info.getMerOrderTime();
          if (time != null && time.length() == 14) {
              time = time.substring(0, 4) + "-" + time.substring(4, 6) + "-"
                      + time.substring(6, 8) + " " + time.substring(8, 10)
                      + ":" + time.substring(10, 12) + ":"
                      + time.substring(12);
              info.setMerOrderTime(time);
          } else {
              continue;
          }
      }
      DecimalFormat df = new DecimalFormat("#0.00");
      JSONArray array = JSONArray.fromObject(orderInfoList);
      JSONObject object = new JSONObject();
      object.put("Rows", array.toString());
      object.put("Total", count);
      object.put("sumMerAmt", df.format(sumMerAmt/100));
      object.put("sumTransFee", df.format(sumTransFee/100));
      orderList = object.toString();
      System.out.println(orderList);
      try {
          getResponse().getWriter().write(orderList);
      } catch (IOException e) {
          e.printStackTrace();
      }
	}

	/**
	 * 
	 * @Title:        selectPayOrder 
	 * @Description:  代付交易查询
	 * @param:            
	 * @return:       void    
	 * @throws Exception 
	 * @throws UnsupportedEncodingException 
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2015-6-29 下午3:36:01
	 */
	public void selectPayOrder() throws UnsupportedEncodingException, Exception {
	  Map<String,String> maps = new HashMap<String,String>();
      maps.put("createDate", DateUtil.getDate("yyyyMMdd"));
      maps.put("subMerId", busMerId);
      maps.put("orderId", orderId);
      maps.put("merOrderId", merOrderId);
      maps.put("userMobile", userMobile);
      maps.put("agentIdL1", agentIdL1);
      maps.put("agentIdL2", agentIdL2);
      maps.put("merOrderTime", merOrderTime);
      maps.put("transType", transType);
      maps.put("orderStatus", orderStatus);
      maps.put("merSysId", merSysId);
      maps.put("terminalId", terminalSerialNumber);
      maps.put("cardNo", cardNo);
      maps.put("transMerId", this.getParameterForString("transMerId"));
      maps.put("orderRateType", "'10','15'");	//代付交易 10
      totalMap = orderInfoService.selectOrderTheDateCountRate(maps);
      int count = Integer.parseInt(totalMap.get("count").toString());
      double sumMerAmt = 0;
      if(null != totalMap.get("sumMerAmt")) {
          sumMerAmt = Double.parseDouble(totalMap.get("sumMerAmt").toString());
      }
      double sumTransFee = 0;
      if(null != totalMap.get("sumTransFee")) {
          sumTransFee = Double.parseDouble(totalMap.get("sumTransFee").toString());
      }
      Map map = PageUtil.getPageMap(page, 30);
      map.putAll(maps);
      orderInfoList = orderInfoService.selectOrderTheDateRate(map);
      OrderInfo info = null;
      String time;
      //需要提示商户
      String subMerPath = rb.getString("alertSub");
      List<String> alertSubList = new ArrayList<String>();
      File file=new File(subMerPath);
      if(file.isFile() && file.exists()){ //判断文件是否存在
          InputStreamReader read = new InputStreamReader(
          new FileInputStream(file),"UTF-8");//考虑到编码格式
          BufferedReader bufferedReader = new BufferedReader(read);
          String lineTxt = null;
          while((lineTxt = bufferedReader.readLine()) != null){
        	  alertSubList.add(lineTxt);
          		}
          }
      String addrMerPath = rb.getString("alertAddr");
      List<String> alertAddrList = new ArrayList<String>();
      File addrFile=new File(addrMerPath);
      if(addrFile.isFile() && addrFile.exists()){ //判断文件是否存在
          InputStreamReader read = new InputStreamReader(
          new FileInputStream(addrFile),"UTF-8");//考虑到编码格式
          BufferedReader bufferedReader = new BufferedReader(read);
          String lineTxt = null;
          while((lineTxt = bufferedReader.readLine()) != null){
        	  alertAddrList.add(lineTxt);
          		}
          }
      // 格式转换
      for (int i = 0; i < orderInfoList.size(); i++) {
          info = orderInfoList.get(i);
          if(alertSubList.contains(info.getSubMerId())){
        	  logger.info("find subMer="+info.getSubMerId());
        	  info.setBatchId("01");
          }else if(StringUtils.isNotEmpty(info.getAddress())){
        	  for(String address :alertAddrList){
        		  if(info.getAddress().indexOf(address)!=-1){
        			  info.setBatchId("01");
        			  break;
        		  }
        	  }
          }
          time = info.getMerOrderTime();
          if (time != null && time.length() == 14) {
              time = time.substring(0, 4) + "-" + time.substring(4, 6) + "-"
                      + time.substring(6, 8) + " " + time.substring(8, 10)
                      + ":" + time.substring(10, 12) + ":"
                      + time.substring(12);
              info.setMerOrderTime(time);
          } else {
              continue;
          }
      }
      DecimalFormat df = new DecimalFormat("#0.00");
      JSONArray array = JSONArray.fromObject(orderInfoList);
      JSONObject object = new JSONObject();
      object.put("Rows", array.toString());
      object.put("Total", count);
      object.put("sumMerAmt", df.format(sumMerAmt/100));
      object.put("sumTransFee", df.format(sumTransFee/100));
      orderList = object.toString();
      System.out.println(orderList);
      try {
          getResponse().getWriter().write(orderList);
      } catch (IOException e) {
          e.printStackTrace();
      }
	}
	
	/**
	 * ORDER详情
	 * 
	 * @return
	 */
	public String selectOrderDetail() {
		Map map = new HashMap();
		map.put("orderId", orderId);
		orderDetailInfo = orderInfoService.selectOrderDetail(map);
		orderProfit = orderProfitDao.selectOrderProfitByOrderId(orderId);
		orderDetailInfo.setOrderProfit(orderProfit);
		orderFrozen = orderFrozenDao.selectOrderFrozenByOrderId(orderId);
		if(null != orderDetailInfo) {
			String cardNo = orderDetailInfo.getCardNo();
			//信用卡
//			if(orderDetailInfo.getCardType().equals("2")){
				CheckCredit checkCredit = new CheckCredit();
				checkCredit.setCreditCard(cardNo);
				int isChecked = checkCreditDao.selectChecked(checkCredit);
				logger.info("checked="+isChecked);
				if(isChecked>0){
					orderDetailInfo.setReserved("01");
				}
//			}
			if(null != cardNo && !"".equals(cardNo)) {
				String str1 = cardNo.substring(0, 6);
				String str2 = cardNo.substring(cardNo.length()-4, cardNo.length());
				cardNo = str1 + "******" + str2;
			}
			orderDetailInfo.setCardNo(cardNo);
			
//			//检查并拼取图片地址
			String str = rb.getString("upload-path");
			str += orderDetailInfo.getMerSysId() + "/" + orderDetailInfo.getSubMerId();
			File root = new File(str);
			 if(root .exists() && root .isDirectory()){ 
				 File files [] = root.listFiles();
				 if(files!=null){
					for(int i=0;i<files.length;i++){
						if(files[i].getName()!=null&&!"".equals(files[i].getName())&&
								files[i].getName().indexOf("orderSign-"+orderDetailInfo.getMerOrderId())!=-1){
							orderSign = str + "/" + files[i].getName();
						}
					}
				 }
		     } 
		}
		return "selectOrderInfoDetail";
	}
	/**
	 * 签名图片
	 * @throws IOException 
	 */
	public void getImage() throws IOException {
		System.out.println("读取签名照路经："+orderSign);
		String str = orderSign;
		System.out.println(str);
		File file = new File(str);
		FileInputStream fie = new FileInputStream(file);
		byte[] b = new byte[fie.available()];
		fie.read(b);
		OutputStream outputStream = getResponse().getOutputStream();
		outputStream.write(b);
		outputStream.close();
	}
	
	public void noLiquidation(){
		String orderId = this.getParameterForString("orderId");
		Boolean flag = Boolean.FALSE;
		if (StringUtils.isNotEmpty(orderId)) {
			flag = orderStatictisDao.noLiquidation(orderId);
		}
		try {
			getResponse().getWriter().write(flag.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @Description:标记异常订单 
	 * @Auther: ljl
	 * @Date: 2014-6-10 下午4:55:48
	 */
    public void markOrderException() {
        String orderId = this.getParameterForString("orderId");
        Boolean flag = Boolean.FALSE;
        if(StringUtils.isNotEmpty(orderId)){
          flag = orderInfoService.markOrderException(orderId);
        } 
        try {
          getResponse().getWriter().write(flag.toString());
        } catch (IOException e) {
          e.printStackTrace();
        }
    }
    /**
     * 
     * @Title:        payOrder 
     * @Description:  代付交易
     * @param:            
     * @return:       void    
     * @throws Exception 
     * @throws 
     * @author        Eason Jiang
     * @Date          2015-6-29 下午4:39:10
     */
    public void payOrder() throws Exception{
      	 payOrderId = this.getParameterForString("orderId");
      	 String orderId  = payOrderId;
    	 String flag = "代付失败";
    	   /*0.代付订单成功
    	   1.代付订单失败,已冲正!
    	   2.代付订单失败,冲正失败!
    	   3.已经代付过
    	   */
    	 //需要同步
    	 logger.info("进入代付交易 orderId="+payOrderId);
    	 //同步块 不允许相同交易
    	 OrderInfo orderInfo = orderInfoService.getOrderInfo(payOrderId);
    	 synchronized (payOrderId) {
	    	 if(new BigDecimal(orderInfo.getPayAmt()).compareTo(new BigDecimal(0))<0){
	    		 flag="代付失败";
	    		 try {
						getResponse().getWriter().write(flag.toString());
					} catch (IOException e) {
						e.printStackTrace();
					}
		    		 return;
	    	 }
	    	 orderInfo.setOrderId(orderId);
	    	 //判断是否已经代付
	    	 if(orderInfo.getSettleStatus()>0){
	    		 flag = "交易正在处理";
	    		 try {
					getResponse().getWriter().write(flag.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
	    		 return;
	    	 }
    	 		logger.info("查询是否已代付");
    	 		SubMerCashout subMerCashout = subMerCashoutDao.selectSubMerCashoutByOrderId(orderInfo.getOrderId());
    	 		if(subMerCashout!=null){
   	    		 flag = "已经代付";
   	    		 try {
   					getResponse().getWriter().write(flag.toString());
   				} catch (IOException e) {
   					e.printStackTrace();
   				}
   	    		 return;
   	    	   }
    	 		//先更新订单 防止重复交易
   	 		   orderInfo.setSettleStatus(5);
   	 		   orderInfoService.updateOrder(orderInfo);
   	 		   
    	 	   TractInfo tractInfo = tractInfoService.selectTractByMerId(orderInfo.getTransMerId());
    	 	   
    	 		if(tractInfo.getPayMerId().startsWith("SQ")){
    	 			//杉奇提现
    	 			flag=sendToSD(orderInfo,tractInfo);
    	 		}else if(tractInfo.getRatesType().equals("15") || tractInfo.getPayMerId().startsWith("065") ||tractInfo.getPayMerId().equals("723361052310001")){
    	 			//迅联急速到账
    	 			flag=sendToXL(orderInfo,tractInfo);
    	 		}else if(tractInfo.getPayMerId().startsWith("850")){
    	 		    flag=sendToDY(orderInfo,tractInfo);
    	 		}else{
    	 			logger.info("非898商户号");
    	 			//普通代付
    	 			flag=payCashOut(orderInfo);
    	 		}
    	 		 //代付成功 添加分润
//    	 		OrderProfit orderProfit = this.makeProfit(orderInfo);
//    	 		subMerCashout = new SubMerCashout();
//    	 		subMerCashout.setOrderStatus("0"); // 提现申请
//				subMerCashout.setTransId("");
//				subMerCashout.setTransQid("");
//				subMerCashout.setOrderAmt(orderInfo.getPayAmt());
//				int transFee = Integer.parseInt(orderInfo.getMerAmt())-Integer.parseInt(orderInfo.getPayAmt());
//				subMerCashout.setTransFee(String.valueOf(transFee));
//				subMerCashout.setT0MerGain(orderProfit.getMerProfit());
//				subMerCashout.setT0MerRate(orderProfit.getMerRate());
//				subMerCashout.setSubMerId(orderInfo.getSubMerId());
//				subMerCashout.setMerSysId(orderInfo.getMerSysId());
//				subMerCashout.setFinishTime(DateUtil
//						.getDateFormatStr("yyyyMMddHHmmss"));
//				subMerCashout.setCreateTime(DateUtil
//						.getDateFormatStr("yyyyMMddHHmmss"));
//				subMerCashout.setBatchId(DateUtil
//						.getDateFormatStr("yyyyMMddHHmmssSSS"));
//				subMerCashout.setGrade("0");
//				subMerCashout.setOrderId(orderInfo.getOrderId());
//				subMerCashoutDao.insertSubMerCashout(subMerCashout);
//				 orderInfo.setSettleStatus(1);
//	    		 orderInfoService.updateOrderSettle(orderId);
//				 //代付成功
//				 flag = "0";
//				 //实时代付
//				 TRAN_RESP resp = payToMs(subMerCashout);
//				 logger.info(orderId+"="+resp.getRESP_MSG());
//				 if(resp.getRESP_CODE().equals("00")){
//					 //如果交易成功 更改状态
//					 SubMerCashout cashOutSuccess = new SubMerCashout();
//					cashOutSuccess.setId(subMerCashout.getId());
//					cashOutSuccess.setOrderStatus("2");
//					cashOutSuccess.setFinishTime(DateUtil.getDate("yyyyMMddHHmmss"));
//					subMerCashoutDao.updateSubMerCashout(cashOutSuccess);
//				 }
				//实时代付 结束
				 try{
					 getResponse().getWriter().write(flag.toString());
				 }catch(Exception e){
					 e.printStackTrace();
				 }
    	 }
    }
    
    /**
     * 查询代付情况
     * @Title:        searchPay 
     * @Description:  
     * @param:            
     * @return:       void    
     * @throws 
     * @author        Eason Jiang
     * @Date          2016-4-25 下午2:51:37
     */
    public void searchPay(){
    	 String orderId = this.getParameterForString("orderId");
    	 String flag = "代付失败";
    	 logger.info("进入查询 orderId="+orderId);
    	 OrderInfo orderInfo = orderInfoService.getOrderInfo(orderId);
    	 SubMerInfo subMerInfo = subMerInfoService.getSubMerInfoById(orderInfo.getSubMerId());
	 	 TractInfo tractInfo = tractInfoService.selectTractByMerId(orderInfo.getTransMerId());

       	 CoreTransInfo coreTransInfo = new CoreTransInfo();
       	 coreTransInfo.setPan(subMerInfo.getSettAccountNo());

       	 String idNum = subMerInfo.getLegalIdcard();
//       	 if(idNum.toLowerCase().endsWith("x")){
//       		 idNum = idNum.substring(idNum.length()-7,idNum.length()-1);
//       	 }else{
//       		 idNum = idNum.substring(idNum.length()-6);
//       	 }
       	 coreTransInfo.setIdNumber(idNum);
       	 coreTransInfo.setUserName(subMerInfo.getLegalPerson());		//姓名
       	 coreTransInfo.setTransType("1001");		//银行卡
       	 coreTransInfo.setTransSource("201");		//手刷
       	 coreTransInfo.setMessageType("310000");		//消费发送0200
       	 coreTransInfo.setProcessingCode("740030");		//发送定义提现类型
       	 //查找上一笔流水
//       	 OrderInfo lastOrder = new OrderInfo();
//       	 lastOrder.setSubMerId(orderInfo.getSubMerId());
//       	 lastOrder.setTerminalId(orderInfo.getTerminalId());
//       	 lastOrder = orderInfoDao.selectLastOrder(lastOrder);
//       	 int voucherNo = Integer.parseInt(lastOrder.getVoucherNo())+1;
//       	 String trackIngNo = String.valueOf(voucherNo);
//       	 String zero="";
//       	 for(int i=0;i<6-trackIngNo.length();i++){
//       		 zero+="0";
//       	 }
       	 
       	 coreTransInfo.setTrackingNo("000000");		//交易流水
//       	 coreTransInfo.setTrackingNo("038871");		//交易流水
       	 coreTransInfo.setBatchNo("000001");		//批次号
       	 coreTransInfo.setCurrencyCode("156");		//156人民币
       	 //交易金额为原交易金额减去代付手续费4元001803  142937
       	 coreTransInfo.setAmount(orderInfo.getPayAmt());		//提现金额已经在消费时算好
       	 coreTransInfo.setChTermId(tractInfo.getPayTerId());		//代付终端号
       	 coreTransInfo.setTerminalNo(orderInfo.getTerminalId());	//终端号
       	 coreTransInfo.setChMerId(tractInfo.getPayMerId());		//代付商户号
       	 coreTransInfo.setMerId(orderInfo.getSubMerId());		//商户号
     	//15费率类型急速收款
    	coreTransInfo.setUserDefined1(orderInfo.getTraceNo());		//代付那一笔流水号
    	coreTransInfo.setUserName(subMerInfo.getSettAccountName());
    	coreTransInfo.setUserDefined2(subMerInfo.getOpenBank());
    	coreTransInfo.setUserDefined3(subMerInfo.getLineNum());
    	orderInfo.setVoucherNo("000000");
    	insertOrder(orderInfo);
       	 logger.info("发送到代付查询  coreTransInfo="+coreTransInfo.toString());
       	// 瑞银自己的通道
    		 coreTransInfo = newPayService.doEntrustPayCheck(coreTransInfo);
    		 logger.info("*******doPay resp："
    					+ coreTransInfo.getResponseCode());
    		 coreTransInfo.setResponseDesc(getXlSearchCode(coreTransInfo.getResponseCode().trim()));
    		   	logger.info("*******doPay resp："
    		   			+ coreTransInfo.getResponseCode()+coreTransInfo.getResponseDesc());
    	flag=coreTransInfo.getResponseCode()+"-"+coreTransInfo.getResponseDesc();
    	try{
			 getResponse().getWriter().write(flag.toString());
		 }catch(Exception e){
			 e.printStackTrace();
		 }
    	 
    }
    
    /**
     * 德颐代付查询
     * @Title:        queryDyPay 
     * @Description:  
     * @param:            
     * @return:       void    
     * @throws Exception 
     * @throws 
     * @author        Eason Jiang
     * @Date          2016-12-20 上午11:55:45
     */
    public void queryDyPay() throws Exception{
        String id = this.getParameterForString("id");
        logger.info("进入德颐查询 id="+id);
        DyPayInfo dyPayInfo = new DyPayInfo();
        dyPayInfo.setId(Long.parseLong(id));
        dyPayInfo = dyPayInfoDao.selectInfoById(dyPayInfo);
        String flag = "";
        JSONObject json = new JSONObject();
        json.put("id", dyPayInfo.getOrderId());
        json.put("time", dyPayInfo.getTransTime());
        json.put("checkCode",Md5Util.generateMD5String("NTFREEPAY"+json.getString("id")));
//        {"settle_dt":"20161219","req_reserved":"","ret_msg":"","ret_cd":"00","tran_dt_tm":"20161219092338","reserved":""}
        JSONObject rJson = payDY(json,"http://122.144.173.166:18080/dyHttpServer/dyAction!searchOrder.action");
        logger.info("*******doPay resp："
                   + rJson.toString());
        //接受返回流水存入数据库
        if(rJson.optString("ret_cd").equals("00")){
            flag=rJson.optString("org_ret_cd")+rJson.optString("org_ret_msg");
            //如果不为00则更新状态等
            if(!dyPayInfo.getRespCode().equals("00")){
                dyPayInfo.setSettleDt(rJson.optString("settle_dt"));
                dyPayInfo.setTransTime(rJson.optString("tran_dt_tm"));
                dyPayInfo.setRespCode(rJson.optString("ret_cd"));
                dyPayInfo.setRespMsg(rJson.optString("ret_msg"));
                dyPayInfoDao.updateInfo(dyPayInfo);
            }
        }else{
            flag="查询失败"+rJson.optString("ret_msg");
        }
        getResponse().getWriter().write(flag.toString());
   }
    
    public boolean insertOrder(OrderInfo orderInfo){
    	try {
    	OrderInfo orderInfo1 = orderInfo;
    	orderInfo1.setOrderRateType("16");//代付查询
    	orderInfo1.setCreateTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
		orderInfo1.setTransTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
		orderInfo1.setFinshTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
    	String orderId;
			orderId = procedureDao.getOrderId();
		orderInfo1.setOrderId(orderId);
		int i = orderInfoDao.insertOrderInfo(orderInfo1);
		if(i>0){
			return true;
		}else{
			return false;
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    }
    public String payCashOut(OrderInfo orderInfo){
    	String flag = "代付失败";
    	OrderProfit orderProfit = this.makeProfit(orderInfo);
    	SubMerCashout subMerCashout = new SubMerCashout();
 		subMerCashout.setOrderStatus("0"); // 提现申请
		subMerCashout.setTransId("");
		subMerCashout.setTransQid("");
		subMerCashout.setOrderAmt(orderInfo.getPayAmt());
		int transFee = Integer.parseInt(orderInfo.getMerAmt())-Integer.parseInt(orderInfo.getPayAmt());
		subMerCashout.setTransFee(String.valueOf(transFee));
		subMerCashout.setT0MerGain(orderProfit.getMerProfit());
		subMerCashout.setT0MerRate(orderProfit.getMerRate());
		subMerCashout.setSubMerId(orderInfo.getSubMerId());
		subMerCashout.setMerSysId(orderInfo.getMerSysId());
		subMerCashout.setFinishTime(DateUtil
				.getDateFormatStr("yyyyMMddHHmmss"));
		subMerCashout.setCreateTime(DateUtil
				.getDateFormatStr("yyyyMMddHHmmss"));
		subMerCashout.setBatchId(DateUtil
				.getDateFormatStr("yyyyMMddHHmmssSSS"));
		subMerCashout.setGrade("0");
		subMerCashout.setOrderId(orderInfo.getOrderId());
		subMerCashoutDao.insertSubMerCashout(subMerCashout);
		 orderInfo.setSettleStatus(1);
		 orderInfoService.updateOrder(orderInfo);
		 //代付成功
		 flag = "代付成功";
		 //实时代付
//		 TRAN_RESP resp = payToMs(subMerCashout);
//		 logger.info(orderId+"="+resp.getRESP_MSG());
//		 if(resp.getRESP_CODE().equals("00")){
//			 //如果交易成功 更改状态
//			SubMerCashout cashOutSuccess = new SubMerCashout();
//			cashOutSuccess.setId(subMerCashout.getId());
//			cashOutSuccess.setOrderStatus("2");
//			cashOutSuccess.setFinishTime(DateUtil.getDate("yyyyMMddHHmmss"));
//			subMerCashoutDao.updateSubMerCashout(cashOutSuccess);
//		 }
		 //实时代付结束
		 return flag;
    }
    
    /**
     * 提现接口
     * @Title:        sendToSD 
     * @Description:  
     * @param:        @param orderInfo
     * @param:        @return    
     * @return:       String    
     * @throws 
     * @author        Eason Jiang
     * @Date          2015-12-16 下午6:45:18
     */
    public String sendToSD(OrderInfo orderInfo,TractInfo tractInfo){
    	 String flag = "提现失败";
    	 SubMerInfo subMerInfo = subMerInfoService.getSubMerInfoById(orderInfo.getSubMerId());
    	 CoreTransInfo coreTransInfo = new CoreTransInfo();
    	 coreTransInfo.setPan(subMerInfo.getSettAccountNo());

    	 String idNum = subMerInfo.getLegalIdcard();
//    	 if(idNum.toLowerCase().endsWith("x")){
//    		 idNum = idNum.substring(idNum.length()-7,idNum.length()-1);
//    	 }else{
//    		 idNum = idNum.substring(idNum.length()-6);
//    	 }
    	 coreTransInfo.setIdNumber(idNum);
    	 coreTransInfo.setUserName(subMerInfo.getLegalPerson());		//姓名
    	 coreTransInfo.setTransType("1001");		//银行卡
    	 coreTransInfo.setTransSource("201");		//手刷
    	 coreTransInfo.setMessageType("0200");		//消费发送0200
    	 coreTransInfo.setProcessingCode("740030");		//发送定义提现类型
    	 coreTransInfo.setTrackingNo(orderInfo.getVoucherNo());		//交易流水
    	 coreTransInfo.setBatchNo("000001");		//批次号
    	 coreTransInfo.setCurrencyCode("156");		//156人民币
    	 //交易金额为原交易金额减去代付手续费4元001803  142937
    	 coreTransInfo.setAmount(orderInfo.getPayAmt());		//提现金额已经在消费时算好
    	 coreTransInfo.setChTermId(orderInfo.getTransTerId());		//代付终端号
    	 coreTransInfo.setTerminalNo(orderInfo.getTerminalId());	//终端号
    	 coreTransInfo.setChMerId(tractInfo.getTransMerId());		//代付商户号
    	 coreTransInfo.setMerId(orderInfo.getSubMerId());		//商户号
    	 String transTime = orderInfo.getTransTime();
    	 if(transTime.length()!=10){
    		 flag = "交易时间不正确";
    		 return flag;
    	 }
    	 String tDate = transTime.substring(0,4);
		 String tTime = transTime.substring(4,transTime.length());
    	 coreTransInfo.setTimeLocalTransaction(tTime);
    	 coreTransInfo.setDateLocalTransaction(tDate);
         //自定义域 
    	 //户名+身份证号(中行要求上送,其它可以为空)+账号+联行号    	 （使用竖线分割）
    	 coreTransInfo.setUserDefined3(subMerInfo.getSettAccountName()+"|"+subMerInfo.getLegalIdcard()+"|"+subMerInfo.getSettAccountNo()+"|"+subMerInfo.getLineNum());
    	 logger.info("发送到提现  coreTransInfo="+coreTransInfo.toString());
    	// 瑞银自己的通道
		 coreTransInfo = newPayService.doPay(coreTransInfo);
		 logger.info("*******doPay resp："
					+ coreTransInfo.getResponseCode());
		 if(coreTransInfo.getResponseCode().equals("00")){
			 orderInfo.setSettleStatus(1);
//			 orderInfoService.updateOrderSettle(orderInfo.getOrderId());
			 orderInfoService.updateOrder(orderInfo);
			 flag="提现成功";
		 }
    	 return flag;
    }
    
    /**
     * 德颐代付
     * @Title:        sendToDY 
     * @Description:  
     * @param:        @param orderInfo
     * @param:        @param tractInfo
     * @param:        @return    
     * @return:       String    
     * @throws Exception 
     * @throws 
     * @author        Eason Jiang
     * @Date          2016-12-5 下午5:23:24
     */
    public String sendToDY(OrderInfo orderInfo,TractInfo tractInfo) throws Exception{
        String flag = "提现失败";
        SubMerInfo subMerInfo = subMerInfoService.getSubMerInfoById(orderInfo.getSubMerId());
        JSONObject json = new JSONObject();
        json.put("cardNo", subMerInfo.getSettAccountNo());
        json.put("userName", subMerInfo.getSettAccountName());
        json.put("lineNum", subMerInfo.getLineNum());
        json.put("bankName", subMerInfo.getOpenBank());
        json.put("transAmt", orderInfo.getPayAmt());
        json.put("purpose", "代付");
        json.put("memo", "代付");
        json.put("reserved", "");
        json.put("id", "mob"+subMerInfo.getSubMerId()+orderInfo.getOrderId());
        json.put("checkCode",Md5Util.generateMD5String("NTFREEPAY"+json.getString("id")));
        
        DyPayInfo dyPayInfo = new DyPayInfo();
        dyPayInfo.setOrderId(json.getString("id"));
        dyPayInfo.setPayType("1");
        dyPayInfo.setAccountName(json.getString("userName"));
        dyPayInfo.setCardNo(json.getString("cardNo"));
        dyPayInfo.setTransAmt(json.getString("transAmt"));
        dyPayInfoDao.insertInfo(dyPayInfo);
//        {"settle_dt":"20161219","req_reserved":"","ret_msg":"","ret_cd":"00","tran_dt_tm":"20161219092338","reserved":""}
        JSONObject rJson = payDY(json,"http://122.144.173.166:18080/dyHttpServer/dyAction!dyInterface.action");
        logger.info("*******doPay resp："
                   + rJson.toString());
        dyPayInfo.setSettleDt(rJson.optString("settle_dt"));
        dyPayInfo.setTransTime(rJson.optString("tran_dt_tm"));
        dyPayInfo.setRespCode(rJson.optString("ret_cd"));
        dyPayInfo.setRespMsg(rJson.optString("ret_msg"));
        dyPayInfoDao.updateInfo(dyPayInfo);
        //接受返回流水存入数据库
        if(rJson.optString("ret_cd").equals("00")){
            orderInfo.setSettleStatus(1);
            flag="提现成功";
        }else{
            orderInfo.setSettleStatus(3);
            flag=rJson.optString("ret_cd")+rJson.optString("ret_msg");
        }
        orderInfoService.updateOrder(orderInfo);
//          searchXl(orderInfo,subMerInfo);
        return flag;
      }
    
    public JSONObject payDY(JSONObject json,String url) throws Exception{
        DefaultHttpClient httpClient = new DefaultHttpClient();  
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
        log.info("return="+sb);
        JSONObject returnJson = JSONObject.fromObject(sb.toString());
        return returnJson;
    }
    
    /**
     * 迅联急速到账
     * @Title:        sendToXL 
     * @Description:  
     * @param:        @param orderInfo
     * @param:        @param tractInfo
     * @param:        @return    
     * @return:       String    
     * @throws 
     * @author        Eason Jiang
     * @Date          2016-4-21 下午5:38:19
     */
    public String sendToXL(OrderInfo orderInfo,TractInfo tractInfo){
   	 String flag = "提现失败";
   	 SubMerInfo subMerInfo = subMerInfoService.getSubMerInfoById(orderInfo.getSubMerId());
   	 CoreTransInfo coreTransInfo = new CoreTransInfo();
   	 coreTransInfo.setPan(subMerInfo.getSettAccountNo());

   	 String idNum = subMerInfo.getLegalIdcard();
//   	 if(idNum.toLowerCase().endsWith("x")){
//   		 idNum = idNum.substring(idNum.length()-7,idNum.length()-1);
//   	 }else{
//   		 idNum = idNum.substring(idNum.length()-6);
//   	 }
   	 coreTransInfo.setOrderId(orderInfo.getOrderId());
   	 coreTransInfo.setIdNumber(idNum);
   	 coreTransInfo.setTransType("1001");		//银行卡
   	 coreTransInfo.setTransSource("201");		//手刷
   	 coreTransInfo.setMessageType("193000");		//消费发送0200
   	 coreTransInfo.setProcessingCode("740030");		//发送定义提现类型
   	 //查找上一笔流水
//   	 OrderInfo lastOrder = new OrderInfo();
//   	 lastOrder.setSubMerId(orderInfo.getSubMerId());
//   	 lastOrder.setTerminalId(orderInfo.getTerminalId());
//   	 lastOrder = orderInfoDao.selectLastOrder(lastOrder);
//   	 int voucherNo = Integer.parseInt(lastOrder.getVoucherNo())+2;
//   	 String trackIngNo = String.valueOf(voucherNo);
//   	 String zero="";
//   	 for(int i=0;i<6-trackIngNo.length();i++){
//   		 zero+="0";
//   	 }
   	 
   	 coreTransInfo.setTrackingNo("000000");		//交易流水
//   	 coreTransInfo.setTrackingNo("038871");		//交易流水
   	 coreTransInfo.setBatchNo("000001");		//批次号
   	 coreTransInfo.setCurrencyCode("156");		//156人民币
   	 //交易金额为原交易金额减去代付手续费4元001803  142937
   	 coreTransInfo.setAmount(orderInfo.getPayAmt());		//提现金额已经在消费时算好
   	 coreTransInfo.setChTermId(tractInfo.getPayTerId());		//代付终端号
   	 coreTransInfo.setTerminalNo(orderInfo.getTerminalId());	//终端号
   	 coreTransInfo.setChMerId(tractInfo.getPayMerId());		//代付商户号
   	 coreTransInfo.setMerId(orderInfo.getSubMerId());		//商户号
 	//15费率类型急速收款
	coreTransInfo.setUserDefined1("00");		//00对私 01对公
	coreTransInfo.setUserName(subMerInfo.getSettAccountName());
	coreTransInfo.setUserDefined2(subMerInfo.getOpenBank());
//	coreTransInfo.setUserName("test");
//	coreTransInfo.setUserDefined2("xunlian bank");
	coreTransInfo.setUserDefined3(subMerInfo.getLineNum());
   	 logger.info("发送到提现  coreTransInfo="+coreTransInfo.toString());
   	// 瑞银自己的通道
		 coreTransInfo = newPayService.doEntrustPay(coreTransInfo);
		 logger.info("*******doPay resp："
					+ JSONObject.fromObject(coreTransInfo));
		 //接受返回流水存入数据库
		 orderInfo.setTraceNo(coreTransInfo.getTrackingNo());
		 if(coreTransInfo.getResponseCode().equals("00")){
			 orderInfo.setSettleStatus(1);
			 flag="提现成功";
		 }else if(coreTransInfo.getResponseCode().equals("96T")){			//96超时
			 orderInfo.setSettleStatus(4);
			 flag="提现超时";
		 }else if(coreTransInfo.getResponseCode().equals("94T")){			//94重复交易
			 flag="提现间隔不少于10秒";
		 }else{
			 orderInfo.setSettleStatus(3);
			 flag=coreTransInfo.getResponseCode()+coreTransInfo.getResponseDesc();
		 }
		 orderInfoService.updateOrder(orderInfo);
//		 searchXl(orderInfo,subMerInfo);
   	 return flag;
   }
    
    public void searchXl(OrderInfo orderInfo ,SubMerInfo subMerInfo){
   	 String orderId = this.getParameterForString("orderId");
   	 String flag = "代付失败";
   	 logger.info("进入XL代付查询 orderId="+orderId);
	 	 TractInfo tractInfo = tractInfoService.selectTractByMerId(orderInfo.getTransMerId());

      	 CoreTransInfo coreTransInfo = new CoreTransInfo();
      	 coreTransInfo.setPan(subMerInfo.getSettAccountNo());

      	 String idNum = subMerInfo.getLegalIdcard();
//      	 if(idNum.toLowerCase().endsWith("x")){
//      		 idNum = idNum.substring(idNum.length()-7,idNum.length()-1);
//      	 }else{
//      		 idNum = idNum.substring(idNum.length()-6);
//      	 }
      	 coreTransInfo.setIdNumber(idNum);
      	 coreTransInfo.setUserName(subMerInfo.getLegalPerson());		//姓名
      	 coreTransInfo.setTransType("1001");		//银行卡
      	 coreTransInfo.setTransSource("201");		//手刷
      	 coreTransInfo.setMessageType("310000");		//消费发送0200
      	 coreTransInfo.setProcessingCode("740030");		//发送定义提现类型
      	 //查找上一笔流水
      	 OrderInfo lastOrder = new OrderInfo();
      	 lastOrder.setSubMerId(orderInfo.getSubMerId());
      	 lastOrder.setTerminalId(orderInfo.getTerminalId());
      	 lastOrder = orderInfoDao.selectLastOrder(lastOrder);
      	 int voucherNo = Integer.parseInt(lastOrder.getVoucherNo())+1;
      	 String trackIngNo = String.valueOf(voucherNo);
      	 String zero="";
      	 for(int i=0;i<6-trackIngNo.length();i++){
      		 zero+="0";
      	 }
      	 
      	 coreTransInfo.setTrackingNo(zero+trackIngNo);		//交易流水
//      	 coreTransInfo.setTrackingNo("038871");		//交易流水
      	 coreTransInfo.setBatchNo("000001");		//批次号
      	 coreTransInfo.setCurrencyCode("156");		//156人民币
      	 //交易金额为原交易金额减去代付手续费4元001803  142937
      	 coreTransInfo.setAmount(orderInfo.getPayAmt());		//提现金额已经在消费时算好
      	 coreTransInfo.setChTermId(tractInfo.getPayTerId());		//代付终端号
      	 coreTransInfo.setTerminalNo(orderInfo.getTerminalId());	//终端号
      	 coreTransInfo.setChMerId(tractInfo.getPayMerId());		//代付商户号
      	 coreTransInfo.setMerId(orderInfo.getSubMerId());		//商户号
    	//15费率类型急速收款
   	coreTransInfo.setUserDefined1(orderInfo.getTraceNo());		//代付那一笔流水号
   	coreTransInfo.setUserName(subMerInfo.getSettAccountName());
   	coreTransInfo.setUserDefined2(subMerInfo.getOpenBank());
   	coreTransInfo.setUserDefined3(subMerInfo.getLineNum());
   	orderInfo.setVoucherNo(zero+trackIngNo);
   	insertOrder(orderInfo);
      	 logger.info("发送到代付查询  coreTransInfo="+coreTransInfo.toString());
      	// 瑞银自己的通道
   		 coreTransInfo = newPayService.doEntrustPayCheck(coreTransInfo);
   	coreTransInfo.setResponseDesc(getXlSearchCode(coreTransInfo.getResponseCode().trim()));
   	logger.info("*******doPay resp："
   			+ coreTransInfo.getResponseCode()+coreTransInfo.getResponseDesc());
   	flag=coreTransInfo.getResponseCode()+"-"+coreTransInfo.getResponseDesc();
   	try{
			 getResponse().getWriter().write(flag.toString());
		 }catch(Exception e){
			 e.printStackTrace();
		 }
   	 
   }
    
    /**
     * 民生银行实时代付接口
     * @Title:        payToMs 
     * @Description:  
     * @param:        @return    
     * @return:       boolean    
     * @throws 		
     * @author        Eason Jiang
     * @Date          2015-9-15 下午5:01:12
     */
    public TRAN_RESP payToMs(SubMerCashout subMerCashout){
    	boolean flag = false;
    	TRAN_REQ tranReq = makeObj(subMerCashout);
    	TRAN_RESP resp = orderInfoService.payToMs(tranReq);
    	return resp;
    }
    
    public TRAN_REQ makeObj(SubMerCashout subMerCashout){
    	String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
    	String time = new SimpleDateFormat("HHmmss").format(new Date());
    	SubMerInfo subMerInfo = subMerInfoDao.getSubMerInfoById(subMerCashout.getSubMerId());
    	TRAN_REQ tranReq = new TRAN_REQ();
    	tranReq.setCOMPANY_ID("DF_XYF");
    	tranReq.setTRAN_DATE(date);
    	tranReq.setTRAN_TIME(time);
    	tranReq.setTRAN_ID(subMerCashout.getOrderId());
    	tranReq.setCURRENCY("RMB");
    	tranReq.setACC_NO(subMerInfo.getSettAccountNo());
    	tranReq.setACC_NAME(subMerInfo.getSettAccountName());
    	tranReq.setBANK_TYPE(subMerInfo.getLineNum());
    	tranReq.setBANK_NAME(getBankNameByCode(subMerInfo.getSettAgency()));
    	tranReq.setTRANS_AMT(subMerCashout.getOrderAmt());
    	tranReq.setREMARK(subMerCashout.getSubMerId());
    	return tranReq;
    }
    
private String getBankNameByCode(String bankCode){
		
		if( StringUtils.isEmpty((bankCode))){
			return "";
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("ICBC", "工商银行");
		map.put("ABC", "农业银行");
		map.put("CMB", "招商银行");
		map.put("CCB", "建设银行");
		map.put("BCCB", "北京银行");
		map.put("BOC", "中国银行");
		map.put("BOCOM", "交通银行");
		map.put("CBHB", "渤海银行");
		map.put("CEB", "光大银行");
		map.put("CIB", "兴业银行");
		map.put("CITIC", "中信银行");
		map.put("CZB", "浙商银行");
		map.put("GDB", "广发银行");
		map.put("HXB", "华夏银行");
		map.put("PINGAN", "平安银行");
		map.put("SRCB", "上海农村商业银行");
		map.put("FRCU", "沙县农商行");
		map.put("PSBC", "中国邮政储蓄银行");
		return map.get(bankCode.toUpperCase());
			
	}
    public void failOrder(){
    	 String orderId = this.getParameterForString("orderId");
    	 logger.info("标记代付失败orderId="+orderId);
    	 String flag="1";
    	 try{
    		 OrderInfo orderInfo = orderInfoService.getOrderInfo(orderId);
	    	 orderInfo.setOrderId(orderId);
	    	 orderInfo.setRefundStatus("5");	//冲正失败
			 orderInfo.setSettleStatus(3);
			 orderInfoService.updateOrder(orderInfo);
			 flag="0";
    	 }catch(Exception e){
    		 e.printStackTrace();
    		 logger.error(e.getMessage());
    	 }
    	 try{
			 getResponse().getWriter().write(flag.toString());
		 }catch(Exception e){
			 e.printStackTrace();
		 }
    }
    //代付成功需要添加profit
    public OrderProfit makeProfit(OrderInfo orderInfo){
    	MerTrans mertrans = merTransDao.getMerTransInfo(orderInfo.getMerSysId());
    	//获取机构通道配置
    	//机构利润=t0服务费率*交易金额
    	//费率
    	BigDecimal rate = new BigDecimal(mertrans.getT0MerProfit()).divide(new BigDecimal(100));
    	BigDecimal merProfit = new BigDecimal(orderInfo.getMerAmt()).multiply(rate).setScale(0,BigDecimal.ROUND_HALF_UP);
    	OrderProfit orderProfit = new OrderProfit();
    	
    	orderProfit.setOrderId(orderInfo.getOrderId());
    	orderProfit.setMerSysId(orderInfo.getMerSysId());
    	orderProfit.setMerAmt(orderInfo.getMerAmt());
		orderProfit.setMerProfit(merProfit.toString());
		orderProfit.setMerRate(rate.toString());
		orderProfit.setMerHightFee("0");
		//一级代理
		orderProfit.setAgentL1Id(orderInfo.getAgentIdL1());
		AgenctInfo agenctInfo1 = agenctInfoDao.getAgentInfo(orderInfo.getAgentIdL1());
		if(agenctInfo1!=null){
			BigDecimal a1rate = new BigDecimal(agenctInfo1.getAgentT0Rate()).divide(new BigDecimal(100));
			BigDecimal a1Profit = new BigDecimal(orderInfo.getMerAmt()).multiply(a1rate).setScale(0,BigDecimal.ROUND_HALF_UP);
			orderProfit.setAgent1Profit(a1Profit.toString());
			orderProfit.setAgent1Rate(agenctInfo1.getAgentT0Rate());	//代付取t0费率
			orderProfit.setAgent1HightFee(agenctInfo1.getAgentHighestFee());
		}else{
			orderProfit.setAgent1Profit("0");
			orderProfit.setAgent1Rate("0");	//代付取t0费率
			orderProfit.setAgent1HightFee("0");
		}
		
		//二级代理
		orderProfit.setAgentL2Id(orderInfo.getAgentIdL2());
		AgenctInfo agenctInfo2 = agenctInfoDao.getAgentInfo(orderInfo.getAgentIdL2());
		if(agenctInfo2!=null){
			BigDecimal a2rate = new BigDecimal(agenctInfo2.getAgentT0Rate()).divide(new BigDecimal(100));
			BigDecimal a2Profit = new BigDecimal(orderInfo.getMerAmt()).multiply(a2rate).setScale(0,BigDecimal.ROUND_HALF_UP);
			orderProfit.setAgent2Profit(a2Profit.toString());
			orderProfit.setAgent2Rate(agenctInfo2.getAgentT0Rate());	//代付取t0费率
			orderProfit.setAgent2HightFee(agenctInfo2.getAgentHighestFee());
		}else{
			orderProfit.setAgent2Profit("0");
			orderProfit.setAgent2Rate("0");	//代付取t0费率
			orderProfit.setAgent2HightFee("0");
		}
		orderProfit.setCreateTime(new SimpleDateFormat("HH:mm:ss").format(new Date()));
		orderProfit.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		orderProfit.setPlatId("0");
		orderProfit.setPlatProfit("0");
		orderProfit.setBypayProfit("0");
		orderProfit.setTractAcqbankProfit("0");
		orderProfit.setTractBypayProfit("0");
		orderProfit.setTractCost("0");
		orderProfit.setTractFee("0");
		orderProfit.setSubMerRate("0");
		orderProfit.setSubMerHightFee("0");
		orderProfitDao.insertOrderProfit(orderProfit);
		return orderProfit;
    }
    
    /**
     * 代付测试
     * @Title:        testPay 
     * @Description:  
     * @param:            
     * @return:       void    
     * @throws 
     * @author        Eason Jiang
     * @Date          2015-7-22 下午3:00:47
     */
    public void testPay(){
    	logger.info("进入XL代付确认测试");
    	StringBuffer sb = new StringBuffer();
    	String times = getRequest().getParameter("testTime");
    	if(StringUtils.isEmpty(times)){
    		times="0";
    	}
    	int t = Integer.parseInt(times);
    	int orderId = 2000000;
    	int trackingNo = 800000;
    	int result =0;
    	for(int i =1;i<=t;i++){
    		 logger.info("XL代付测试第"+i+"次,共"+t+"次");
    		 CoreTransInfo coreTransInfo = new CoreTransInfo();
    		 coreTransInfo.setPan("6226090214538882");
    		 coreTransInfo.setOrderId(orderId+"");
    	   	 coreTransInfo.setIdNumber("612401199001101685");
    	   	 coreTransInfo.setTransType("1001");		//银行卡
    	   	 coreTransInfo.setTransSource("201");		//手刷
    	   	 coreTransInfo.setMessageType("193000");		//消费发送0200
    	   	 coreTransInfo.setProcessingCode("740030");		//发送定义提现类型
    	   	 coreTransInfo.setTrackingNo(trackingNo+"");		//交易流水
//    	   	 coreTransInfo.setTrackingNo("038871");		//交易流水
    	   	 coreTransInfo.setBatchNo("000001");		//批次号
    	   	 coreTransInfo.setCurrencyCode("156");		//156人民币
    	   	 //交易金额为原交易金额减去代付手续费4元001803  142937
    	   	 coreTransInfo.setAmount(orderInfo.getPayAmt());		//提现金额已经在消费时算好
    	   	 coreTransInfo.setChTermId("960518885812002");		//代付终端号
    	   	 coreTransInfo.setTerminalNo(orderInfo.getTerminalId());	//终端号
    	   	 coreTransInfo.setChMerId("00000003");		//代付商户号
    	   	 coreTransInfo.setMerId(orderInfo.getSubMerId());		//商户号
    	 	//15费率类型急速收款
    		coreTransInfo.setUserDefined1("00");		//00对私 01对公
//    		coreTransInfo.setUserName(subMerInfo.getSettAccountName());
//    		coreTransInfo.setUserDefined2(subMerInfo.getOpenBank());
    		coreTransInfo.setUserName("test");
    		coreTransInfo.setUserDefined2("xunlian bank");
    		coreTransInfo.setUserDefined3("308290003351");
		   	 logger.info("make object suc coreTransInfo="+coreTransInfo.toString());
		  // 瑞银自己的通道
			 coreTransInfo = newPayService.doPay(coreTransInfo);
			 if(StringUtils.isNotEmpty(coreTransInfo.getResponseCode())){
				 logger.info("第"+i+"次测试结果 doPay resp："
						 + coreTransInfo.getResponseCode());
				 sb.append("第"+i+"次测试结果 doPay resp："
						 + coreTransInfo.getResponseCode());
				 sb.append("\r\n");
				 result++;
			 }
			 orderId++;
			 trackingNo++;
    	}
    	 try{
    		 logger.info(sb.toString());
			 getResponse().getWriter().write(result+"/"+times);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
    }
    
	/**
	 * 查询历史交易信息
	 * @throws Exception 
	 */
	public void selecrOrderHistory() throws Exception {
	  Map maps = new HashMap();
      maps.put("createDate", DateUtil.getDate("yyyyMMdd"));
      maps.put("subMerId", busMerId);
      maps.put("orderId", orderId);
      maps.put("merOrderId", merOrderId);
      maps.put("userMobile", userMobile);
      maps.put("agentIdL1", agentIdL1);
      maps.put("agentIdL2", agentIdL2);
      maps.put("merOrderTime", merOrderTime);
      maps.put("transType", transType);
      maps.put("orderStatus", orderStatus);
      maps.put("startTime", startTime);
      maps.put("endTime", endTime);
      maps.put("merSysId", merSysId);
      maps.put("cardNo", cardNo);
      maps.put("refundStatus", refundStatus);
      maps.put("signStatus", signStatus);
//      String settleDates=settleDate.replaceAll("-", "");
//      System.out.println(settleDate);
      maps.put("settleDate", settleDate.replace("-", ""));
      maps.put("transMerId", this.getParameterForString("transMerId"));
//      System.out.println(startTime);
//      System.out.println(endTime);
      String logName = (String) getSession().getAttribute("userName");
      System.out.println("loginName="+logName);
      //特殊用户只看外卡
      if(logName.equals("whf")){
          System.out.println("types");
          maps.put("orderRateTypes", "'31','32','33','34','35','36','37','38','39','40'");
      }else{
          System.out.println("do type");
          maps.put("orderRateTypes", null);
          maps.put("orderRateType", orderRateType);
      }
      totalMap = orderInfoService.selectOrderHistoryCount(maps);
      int count = Integer.parseInt(totalMap.get("count").toString());
      double sumMerAmt = 0;
      if(null != totalMap.get("sumMerAmt")) {
          sumMerAmt = Double.parseDouble(totalMap.get("sumMerAmt").toString());
      }
      double sumTransFee = 0;
      if(null != totalMap.get("sumTransFee")) {
          sumTransFee = Double.parseDouble(totalMap.get("sumTransFee").toString());
      }
      Map map = PageUtil.getPageMap(page, pageSize);
      map.putAll(maps);
      orderInfoList = orderInfoService.selecrOrderHistoryList(map);
      OrderInfo info = null;
      String time;
      // 时间格式转换
      for (int i = 0; i < orderInfoList.size(); i++) {
          info = orderInfoList.get(i);
          time = info.getMerOrderTime();
          if (time != null && time.length() == 14) {
              time = time.substring(0, 4) + "-" + time.substring(4, 6) + "-"
                      + time.substring(6, 8) + " " + time.substring(8, 10)
                      + ":" + time.substring(10, 12) + ":"
                      + time.substring(12);
              info.setMerOrderTime(time);
          } else {
              continue;
          }
      }
      DecimalFormat df = new DecimalFormat("#0.00");
      JSONArray array = JSONArray.fromObject(orderInfoList);
      JSONObject object = new JSONObject();
      //假数据
//      if(StringUtils.isNotEmpty(startTime)&&StringUtils.isNotEmpty(endTime)){
//    	  //日期必须都填写才是假数据
//    	  if(orderStatus.equals("-1")){
//            object.put("sumMerAmt", radMoney(250000000,300000000,daysBetween(startTime,endTime)));
//            //所有交易 2亿5-3亿
//    	  }else if(orderStatus.equals("1")){
//            object.put("sumMerAmt", radMoney(180000000,230000000,daysBetween(startTime,endTime)));
//            //成功交易 2亿-2亿5
//    	  }else if(orderStatus.equals("2")){
//            object.put("sumMerAmt", radMoney(50000000,100000000,daysBetween(startTime,endTime)));
//            //失败交易 5千万-1亿
//    	  }
//    	  if(StringUtils.isNotEmpty(orderRateType) && orderRateType.equals("10")){
//    		  object.put("sumMerAmt", radMoney(50000000,100000000,daysBetween(startTime,endTime)));
//              //T0交易 5千万-1亿
//    	  }
//    	  
//      }else{
//    	  object.put("sumMerAmt", "0.00");
//      }
      object.put("sumMerAmt", df.format(sumMerAmt/100));
      object.put("Rows", array.toString());
//      object.put("Total", count*25);		//交易笔数 25倍
      object.put("Total", count);		//交易笔数 25倍
      object.put("sumTransFee", df.format(sumTransFee/100));
      orderList = object.toString();
      System.out.println(orderList);
      try {
          getResponse().getWriter().write(orderList);
      } catch (IOException e) {
          e.printStackTrace();
      }

	}

	public String radMoney(int min,int max,int times ){
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        int ss = random.nextInt(99)%(99-10+1) + 10;
        System.out.println(s+"."+ss);
        String result = s+"."+ss;
        BigDecimal ranMoney = new BigDecimal(result);
        if(times>1){
        	ranMoney = ranMoney.multiply(new BigDecimal(times));
        }
        return ranMoney.toString();
	}
	
	public int daysBetween(String smdate,String bdate) throws Exception{ 
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(sdf.parse(smdate)); 
		long time1 = cal.getTimeInMillis(); 
		cal.setTime(sdf.parse(bdate)); 
		long time2 = cal.getTimeInMillis(); 
		long between_days=(time2-time1)/(1000*3600*24); 

		return Integer.parseInt(String.valueOf(between_days)); 
		} 
	/**
	 * 应用交易报表下载
	 */
	public void downloadAppayExcel() {
		Map maps = new HashMap();
		maps.put("busMerId", busMerId);
		maps.put("orderId", orderId);
		maps.put("merOrderId", merOrderId);
		maps.put("userMobile", userMobile);
		maps.put("agentIdL1", agentIdL1);
		maps.put("agentIdL2", agentIdL2);
		maps.put("cardNo", cardNo);
		maps.put("merOrderTime", merOrderTime);
		maps.put("transType", transType);
		maps.put("orderStatus", orderStatus);
		maps.put("startTime", startTime);
		maps.put("endTime", endTime);
		maps.put("merSysId", merSysId);
		maps.put("busType", busType);
		maps.put("shipmentStatus", shipmentStatus);
		
		HttpServletResponse response = getResponse();
		response.reset();//清除缓存
		
		totalMap = orderInfoService.selectOrderHistoryCount(maps);
		int count = 0;
		if(null != totalMap && null != totalMap.get("count") && !"".equals(totalMap.get("count")))
			count = Integer.parseInt(totalMap.get("count").toString());
		
		if (count > 2000) {
			try {
				response.setHeader("Content-type", "text/html;charset=UTF-8");
				response.getWriter().write("<script>alert('弹出，超2000');window.close()</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		Map map = PageUtil.getPageMap(1, count);
		map.putAll(maps);
		orderInfoList = orderInfoService.selectApplyList(map);
		//设置下载类型
		response.setContentType("application/x-download");
		String sheetName = "应用交易";//文件名
		
		Map<String, String> mapfile = new LinkedHashMap<String, String>();
		mapfile.put("orderId", "订单号");
		mapfile.put("subMerId", "商户号");
		mapfile.put("shortName", "商户简称");
		mapfile.put("merOrderId", "商户订单号");
		mapfile.put("merOrderTime", "商户订单时间");
		mapfile.put("merAmt", "交易金额");
		mapfile.put("transTypes", "交易类型");
		mapfile.put("orderStatuss", "交易状态");
		mapfile.put("respDesc", "返回码描述");
		mapfile.put("cardNo", "卡号");
		mapfile.put("cardTypes", "卡类型");
		try {
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(sheetName, "UTF-8") + ".xls");
			ImportExcelFile.ImportExcel(orderInfoList, response.getOutputStream(), mapfile, sheetName, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 历史查询报表下载
	 */
	public void download_excel_new() {
		Map maps = new HashMap();
		maps.put("busMerId", busMerId);
		maps.put("orderId", orderId);
		maps.put("merOrderId", merOrderId);
		maps.put("userMobile", userMobile);
		maps.put("agentIdL1", agentIdL1);
		maps.put("agentIdL2", agentIdL2);
		maps.put("merOrderTime", merOrderTime);
		maps.put("transType", transType);
		maps.put("orderStatus", orderStatus);
		maps.put("startTime", startTime);
		maps.put("endTime", endTime);
		maps.put("merSysId", merSysId);
		maps.put("cardNo", cardNo);
		 String logName = (String) getSession().getAttribute("userName");
	      //特殊用户只看外卡
	      if(logName.equals("whf")){
	          maps.put("orderRateTypes", "'31','32','33','34','35','36','37','38','39','40'");
	      }else{
	          maps.put("orderRateTypes", null);
	          maps.put("orderRateType", orderRateType);
	      }
		HttpServletResponse response = getResponse();
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.reset();//清除缓存
		totalMap = orderInfoService.selectOrderHistoryCount(maps);
		int count = 0;
		if(null != totalMap && null != totalMap.get("count") && !"".equals(totalMap.get("count")))	
			count = Integer.parseInt(totalMap.get("count").toString());
		if(count > 30000) {
			try {
				this.renderText("<script>alert('失败,下载记录大于30000行');window.close()</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		Map map = PageUtil.getPageMap(1, count);
		map.putAll(maps);
		orderInfoList = orderInfoService.selecrOrderHistoryList(map);
//		DecimalFormat df = new DecimalFormat("#0.00");
//		long sumMerAmt = 0;
		if(null != orderInfoList && orderInfoList.size() > 0) {
			for(int i = 0; i < orderInfoList.size(); i++) {
				OrderInfo info = orderInfoList.get(i);
//				long merAmts = Long.parseLong(info.getMerAmt());
//				System.out.println(merAmts);
//				sumMerAmt = sumMerAmt + merAmts;
//				System.out.println("--:" + sumMerAmt);
//				if(null != orderInfoList.get(i).getSubMerTerminal()) {
//					
//				}
				double merAmt = Double.parseDouble(info.getMerAmt())/100;
				orderInfoList.get(i).setMerAmt(merAmt+"");
			}
		}
		//设置下载类型
		response.setContentType("application/x-download");
		String sheetName = "历史交易";//文件名
		//设置导出字段  其中map中的key必须与实体类中的字段一致，不区分大小写
		Map<String, String> mapfile = new LinkedHashMap<String, String>();
		mapfile.put("merOrderTime", "日期");
		mapfile.put("merOrderTime", "时间");
		mapfile.put("subMerId", "商户号");
		mapfile.put("merSysId", "代理商号");
		mapfile.put("merOrderId", "交易号");
		mapfile.put("merAmt", "交易金额");
		mapfile.put("transTypes", "业务类型");
		mapfile.put("orderRateType", "交易类型");
		mapfile.put("orderStatus", "交易状态");
		mapfile.put("orderStatuss", "状态描述");
		mapfile.put("issueBankName", "银行");
		mapfile.put("fullCardNo", "卡号");
		mapfile.put("cardTypes", "卡类型");
		mapfile.put("orderRateTypes", "手续费类型");
		mapfile.put("osfeeRate", "手续费率");
		mapfile.put("ostransFee", "总手续费");
		mapfile.put("merRate", "代理商费率");
		mapfile.put("merProfit", "代理分润");
		mapfile.put("terminalId", "终端号");
		mapfile.put("terminalTypes", "终端机型");
		mapfile.put("tsn", "设备序列号");
		mapfile.put("transMerId", "通道商户号");
		mapfile.put("transTerId", "通道终端号");
		mapfile.put("payAmt", "代付金额");
		mapfile.put("settleStatus", "代付状态");
		/**
		mapfile.put("orderId", "订单号");
		mapfile.put("shortName", "商户简称");
		mapfile.put("respDesc", "返回码描述");
		**/
		
		
		//合计
//		Map mapAmountTo = new LinkedHashMap();
//		mapAmountTo.put("总交易金额:", df.format((double)sumMerAmt/100));
		//设置下载文件名
		try {
			response.setHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode(sheetName, "UTF-8") + ".xls");
			ImportExcelFile.ImportExcel(orderInfoList, response.getOutputStream(), mapfile, sheetName, null);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 查询所有转账订单信息
	 */
	public void selectTransfer() {
		Map maps = new HashMap();
		maps.put("busMerId", busMerId);
		maps.put("orderId", orderId);
		maps.put("merOrderId", merOrderId);
		maps.put("agentIdL1", agentIdL1);
		maps.put("agentIdL2", agentIdL2);
		maps.put("terminalId", terminalId);
		maps.put("createTime", merOrderTime);
		maps.put("orderStatus", orderStatus);
		maps.put("cardNo", cardNo);
		totalMap = orderInfoService.selectTransferCount(maps);
		int count = Integer.parseInt(totalMap.get("count").toString());
		double sumMerAmt = Double.parseDouble(totalMap.get("sumMerAmt").toString());
		double sumTransFee = Double.parseDouble(totalMap.get("sumTransFee").toString());
		Map map = PageUtil.getPageMap(page, pageSize);
		map.putAll(maps);
		orderInfoList = orderInfoService.selectTransfer(map);
		OrderInfo info = null;
		String time;
		// 时间格式转换
		for (int i = 0; i < orderInfoList.size(); i++) { 
			info = orderInfoList.get(i);
			time = info.getMerOrderTime();
			if (time != null && time.length() == 14) {
				time = time.substring(0, 4) + "-" + time.substring(4, 6) + "-"
						+ time.substring(6, 8) + " " + time.substring(8, 10)
						+ ":" + time.substring(10, 12) + ":"
						+ time.substring(12);
				info.setMerOrderTime(time);
			} else {
				continue;
			}
		}
		DecimalFormat df = new DecimalFormat("#0.00");
		
		JSONArray array = JSONArray.fromObject(orderInfoList);
		JSONObject object = new JSONObject();
		object.put("Rows", array.toString());
		object.put("Total", count);
		object.put("sumMerAmt", df.format(sumMerAmt/100));
		object.put("sumTransFee", df.format(sumTransFee/100));
		orderList = object.toString();
		System.out.println(orderList);
		try {
			getResponse().getWriter().write(orderList);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 转账交易导出
	 */
	public void download_excel_news() {
		Map maps = new HashMap();
		maps.put("busMerId", busMerId);
		maps.put("orderId", orderId);
		maps.put("merOrderId", merOrderId);
		maps.put("agentIdL1", agentIdL1);
		maps.put("agentIdL2", agentIdL2);
		maps.put("terminalId", terminalId);
		maps.put("createTime", merOrderTime);
		maps.put("orderStatus", orderStatus);
		maps.put("cardNo", cardNo);
		
		String[] columNames = { "订单号", "商户号", "商户简称", "商户订单号", "商户订单时间",
				"通道商户号", "交易金额", "交易状态" };
		
		HttpServletResponse response = getResponse();
		response.reset();//清除缓存
		totalMap = orderInfoService.selectTransferCount(maps);
		int count = 0;
		if(null != totalMap && null != totalMap.get("count") && !"".equals(totalMap.get("count")))	
			count = Integer.parseInt(totalMap.get("count").toString());
		if (count > 2000) {
			try {
				response.setHeader("Content-type", "text/html;charset=UTF-8");
				response.getWriter().write("<script>alert('弹出，超2000');window.close()</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		Map map = PageUtil.getPageMap(1, count);
		map.putAll(maps);
		orderInfoList = orderInfoService.selectTransfer(map);
		if(null != orderInfoList && orderInfoList.size()> 0){
			for(int i = 0;i<orderInfoList.size();i++){
				OrderInfo info = new OrderInfo();
				info = orderInfoList.get(i);
				String cardType = info.getCardType();
				if(null != cardType && "1".equals(cardType)) {
					info.setCardType("借记卡");
				}else if(null != cardType && "2".equals(cardType)){
					info.setCardType("信用卡");
				}else if(null != cardType && "3".equals(cardType)){
					info.setCardType("准贷记卡");
				}else if(null != cardType && "4".equals(cardType)){
					info.setCardType("储值卡");
				}else {
					info.setCardType("未知");
				}
			}
		}
		//设置下载类型
		response.setContentType("application/x-download");
		String sheetName = "转账交易";//文件名
		//设置导出字段  其中map中的key必须与实体类中的字段一致，不区分大小写
		Map<String, String> mapfile = new LinkedHashMap<String, String>();
		mapfile.put("orderId", "订单号");
		mapfile.put("subMerId", "商户号");
		mapfile.put("shortName", "商户简称");
		mapfile.put("merOrderId", "商户订单号");
		mapfile.put("merOrderTime", "商户订单时间");
		mapfile.put("merAmt", "交易金额");
		mapfile.put("transTypes", "交易类型");
		mapfile.put("orderStatuss", "交易状态");
		mapfile.put("respDesc", "返回码描述");
		mapfile.put("cardNo", "卡号");
		mapfile.put("cardType", "卡类型");
		
		try {
			response.setHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode(sheetName, "UTF-8") + ".xls");
			ImportExcelFile.ImportExcel(orderInfoList, response.getOutputStream(), mapfile, sheetName, null);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void errorSign(){
		String orderId = getRequest().getParameter("orderId");
		String status = getRequest().getParameter("status");
		System.out.println("orderId="+orderId);
		System.out.println("status="+status);
		OrderInfo orderInfo = orderInfoService.getOrderInfo(orderId);
		SubMerInfo subMerInfo = subMerInfoService.getSubMerInfoById(orderInfo.getSubMerId());
		try {
			orderInfo.setOrderId(orderId);
			orderInfo.setSignStatus(Integer.parseInt(status));
			orderInfoService.updateOrder(orderInfo);
			if(Integer.parseInt(status)==1){
				if(StringUtils.isNotEmpty(subMerInfo.getContactorPhone())){
					sendMsg(subMerInfo.getContactorPhone());
				}
				getResponse().getWriter().write("签名已设置为错误");
			}else if(Integer.parseInt(status)==2){
				getResponse().getWriter().write("签名已设置为正确");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getXlSearchCode(String code){
		Map<String,String> map = new HashMap<String,String>();
		map.put("00", "代付成功");
		map.put("05", "代付失败");
		map.put("25", "无原始交易");
		map.put("19", "代付中");
		String resp = map.get(code);
		if(StringUtils.isEmpty(resp)){
			resp ="请稍后再查";
		}
		return resp;
	}
	
	public String getOrderList() {
		return orderList;
	}

	public void setOrderList(String orderList) {
		this.orderList = orderList;
	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public String getBusMerId() {
		return busMerId;
	}

	public void setBusMerId(String busMerId) {
		this.busMerId = busMerId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getMerOrderId() {
		return merOrderId;
	}

	public void setMerOrderId(String merOrderId) {
		this.merOrderId = merOrderId;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	public String getMerOrderTime() {
		return merOrderTime;
	}

	public void setMerOrderTime(String merOrderTime) {
		this.merOrderTime = merOrderTime;
	}

	public String getTransQid() {
		return transQid;
	}

	public void setTransQid(String transQid) {
		this.transQid = transQid;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}


	public String getAgentIdL1() {
		return agentIdL1;
	}

	public void setAgentIdL1(String agentIdL1) {
		this.agentIdL1 = agentIdL1;
	}

	public String getAgentIdL2() {
		return agentIdL2;
	}

	public void setAgentIdL2(String agentIdL2) {
		this.agentIdL2 = agentIdL2;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getMerSysId() {
		return merSysId;
	}

	public void setMerSysId(String merSysId) {
		this.merSysId = merSysId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getTerminalSerialNumber() {
		return terminalSerialNumber;
	}

	public void setTerminalSerialNumber(String terminalSerialNumber) {
		this.terminalSerialNumber = terminalSerialNumber;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getShipmentStatus() {
		return shipmentStatus;
	}

	public void setShipmentStatus(String shipmentStatus) {
		this.shipmentStatus = shipmentStatus;
	}
	
	public SysManager getSysManager() {
		return sysManager;
	}

	public void setSysManager(SysManager sysManager) {
		this.sysManager = sysManager;
	}

	public OrderInfo getOrderDetailInfo() {
		return orderDetailInfo;
	}

	public void setOrderDetailInfo(OrderInfo orderDetailInfo) {
		this.orderDetailInfo = orderDetailInfo;
	}

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

	public String getOrderSign() {
		return orderSign;
	}

	public void setOrderSign(String orderSign) {
		this.orderSign = orderSign;
	}
	public SysOpLogDao getSysOpLogDao() {
		return sysOpLogDao;
	}
	public void setSysOpLogDao(SysOpLogDao sysOpLogDao) {
		this.sysOpLogDao = sysOpLogDao;
	}
	public SysManagerService getSysManagerService() {
		return sysManagerService;
	}
	public void setSysManagerService(SysManagerService sysManagerService) {
		this.sysManagerService = sysManagerService;
	}
	public OrderInfoService getOrderInfoService() {
		return orderInfoService;
	}
	public void setOrderInfoService(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}
	public OrderStatictisDao getOrderStatictisDao() {
		return orderStatictisDao;
	}
	public void setOrderStatictisDao(OrderStatictisDao orderStatictisDao) {
		this.orderStatictisDao = orderStatictisDao;
	}
	public SubMerInfoDao getSubMerInfoDao() {
		return subMerInfoDao;
	}
	public void setSubMerInfoDao(SubMerInfoDao subMerInfoDao) {
		this.subMerInfoDao = subMerInfoDao;
	}
	public OrderFrozenDao getOrderFrozenDao() {
		return orderFrozenDao;
	}
	public void setOrderFrozenDao(OrderFrozenDao orderFrozenDao) {
		this.orderFrozenDao = orderFrozenDao;
	}
	public List<OrderInfo> getOrderInfoList() {
		return orderInfoList;
	}
	public void setOrderInfoList(List<OrderInfo> orderInfoList) {
		this.orderInfoList = orderInfoList;
	}
	public OrderFrozen getOrderFrozen() {
		return orderFrozen;
	}
	public void setOrderFrozen(OrderFrozen orderFrozen) {
		this.orderFrozen = orderFrozen;
	}
	public Map getTotalMap() {
		return totalMap;
	}
	public void setTotalMap(Map totalMap) {
		this.totalMap = totalMap;
	}
	public static ResourceBundle getRb() {
		return rb;
	}
	public static void setRb(ResourceBundle rb) {
		OrderInfoAction.rb = rb;
	}
	public OrderProfitDao getOrderProfitDao() {
		return orderProfitDao;
	}
	public void setOrderProfitDao(OrderProfitDao orderProfitDao) {
		this.orderProfitDao = orderProfitDao;
	}
	public OrderProfit getOrderProfit() {
		return orderProfit;
	}
	public MerTransDao getMerTransDao() {
		return merTransDao;
	}
	public void setMerTransDao(MerTransDao merTransDao) {
		this.merTransDao = merTransDao;
	}
	public void setOrderProfit(OrderProfit orderProfit) {
		this.orderProfit = orderProfit;
	}
	public SubMerInfoService getSubMerInfoService() {
		return subMerInfoService;
	}
	public void setSubMerInfoService(SubMerInfoService subMerInfoService) {
		this.subMerInfoService = subMerInfoService;
	}
	public TractInfoService getTractInfoService() { 
		return tractInfoService;
	}
	public void setTractInfoService(TractInfoService tractInfoService) {
		this.tractInfoService = tractInfoService;
	}
	public AgenctInfoDao getAgenctInfoDao() {
		return agenctInfoDao;
	}
	public void setAgenctInfoDao(AgenctInfoDao agenctInfoDao) {
		this.agenctInfoDao = agenctInfoDao;
	}
	public SubMerCashoutDao getSubMerCashoutDao() {
		return subMerCashoutDao;
	}
	public void setSubMerCashoutDao(SubMerCashoutDao subMerCashoutDao) {
		this.subMerCashoutDao = subMerCashoutDao;
	}
	public String getOrderStatictis() {
		return orderStatictis;
	}
	public void setOrderStatictis(String orderStatictis) {
		this.orderStatictis = orderStatictis;
	}
	public int sendMsg(String mobile){
		logger.info("send mobile="+mobile);
		String url = "http://172.16.1.12:8699/RyManager/shoujizhifu/send_phone_code.do?phone=";
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
	
//	public static void main(String[] args) {
//
//		String url = "http://101.231.72.93:8199/ryms_OTO_JK/RyManager/send_phone_inform?message=2B&phone=";
//		try {
//			HttpClient httpClient = new HttpClient();
//			 String encoding="GBK";
//             File file=new File("D:/2.txt"); 
//             InputStreamReader read = new InputStreamReader(
//                     new FileInputStream(file),encoding);//考虑到编码格式
//             BufferedReader bufferedReader = new BufferedReader(read);
//             String lineTxt = null;
//             while((lineTxt = bufferedReader.readLine()) != null){
//            	 System.out.println(lineTxt);
//            	 GetMethod get = new GetMethod(url+lineTxt);
//            	 get.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
//            	 int statusCode = httpClient.executeMethod(get);
//             }
//             read.close();
////			PostMethod post=new PostMethod(url);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	
//	}
	
	/**
	 * 中亿代付
	 * @Title:        sendToZY 
	 * @Description:  
	 * @param:        @param orderInfo
	 * @param:        @param tractInfo
	 * @param:        @return    
	 * @return:       String    
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2016-5-27 上午10:56:53
	 */
	public String sendToZY(OrderInfo orderInfo,TractInfo tractInfo){
	   	 String flag = "提现失败";
	   	 SubMerInfo subMerInfo = subMerInfoService.getSubMerInfoById(orderInfo.getSubMerId());
	   	 CoreTransInfo coreTransInfo = new CoreTransInfo();
	   	 coreTransInfo.setPan(subMerInfo.getSettAccountNo());

	   	 String idNum = subMerInfo.getLegalIdcard();
//	   	 if(idNum.toLowerCase().endsWith("x")){
//	   		 idNum = idNum.substring(idNum.length()-7,idNum.length()-1);
//	   	 }else{
//	   		 idNum = idNum.substring(idNum.length()-6);
//	   	 }
	   	 coreTransInfo.setOrderId(orderInfo.getOrderId());
	   	 coreTransInfo.setIdNumber(idNum);
	   	 coreTransInfo.setTransType("1001");		//银行卡
	   	 coreTransInfo.setTransSource("201");		//手刷
	   	 coreTransInfo.setMessageType("193000");		//消费发送0200
	   	 coreTransInfo.setProcessingCode("740030");		//发送定义提现类型
	   	 //查找上一笔流水
//	   	 OrderInfo lastOrder = new OrderInfo();
//	   	 lastOrder.setSubMerId(orderInfo.getSubMerId());
//	   	 lastOrder.setTerminalId(orderInfo.getTerminalId());
//	   	 lastOrder = orderInfoDao.selectLastOrder(lastOrder);
//	   	 int voucherNo = Integer.parseInt(lastOrder.getVoucherNo())+2;
//	   	 String trackIngNo = String.valueOf(voucherNo);
//	   	 String zero="";
//	   	 for(int i=0;i<6-trackIngNo.length();i++){
//	   		 zero+="0";
//	   	 }
	   	 
	   	 coreTransInfo.setTrackingNo("000000");		//交易流水
//	   	 coreTransInfo.setTrackingNo("038871");		//交易流水
	   	 coreTransInfo.setBatchNo("000001");		//批次号
	   	 coreTransInfo.setCurrencyCode("156");		//156人民币
	   	 //交易金额为原交易金额减去代付手续费4元001803  142937
	   	 coreTransInfo.setAmount(orderInfo.getPayAmt());		//提现金额已经在消费时算好
	   	 coreTransInfo.setChTermId(tractInfo.getPayTerId());		//代付终端号
	   	 coreTransInfo.setTerminalNo(orderInfo.getTerminalId());	//终端号
	   	 coreTransInfo.setChMerId(tractInfo.getPayMerId());		//代付商户号
	   	 coreTransInfo.setMerId(orderInfo.getSubMerId());		//商户号
	 	//15费率类型急速收款
		coreTransInfo.setUserDefined1("00");		//00对私 01对公
		coreTransInfo.setUserName(subMerInfo.getSettAccountName());
		coreTransInfo.setUserDefined2(subMerInfo.getOpenBank());
//		coreTransInfo.setUserName("test");
//		coreTransInfo.setUserDefined2("xunlian bank");
		coreTransInfo.setUserDefined3(subMerInfo.getLineNum());
	   	 logger.info("发送到提现  coreTransInfo="+coreTransInfo.toString());
	   	// 瑞银自己的通道
			 coreTransInfo = newPayService.doPay(coreTransInfo);
			 logger.info("*******doPay resp："
						+ coreTransInfo.getResponseCode());
			 //接受返回流水存入数据库
			 orderInfo.setTraceNo(coreTransInfo.getTrackingNo());
			 if(coreTransInfo.getResponseCode().equals("00")){
				 orderInfo.setSettleStatus(1);
				 flag="提现成功";
			 }else if(coreTransInfo.getResponseCode().equals("96T")){			//96超时
				 orderInfo.setSettleStatus(4);
				 flag="提现超时";
			 }else{
				 orderInfo.setSettleStatus(3);
				 flag=coreTransInfo.getResponseCode()+coreTransInfo.getResponseDesc();
			 }
			 orderInfoService.updateOrder(orderInfo);
//			 searchXl(orderInfo,subMerInfo);
	   	 return flag;
	   }
	
	public void payTojz() throws InterruptedException{
		String message = getRequest().getParameter("subMerId");
		String result = "";
		StringBuffer errorCard = new StringBuffer("失败流水:");
		int sucNum = 0;
	    List<Map<String,Object>> list = Lists.newArrayList();
		String[] orders = message.split("\n");// 截取用户输入的，存入数组
		JSONObject json = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		int transId = 0;
		for(String msg:orders){
			StringBuffer sb = new StringBuffer(msg);
//			logger.info(msg);
			String transTime = sdf.format(new Date());
			String id=sb.substring(0, sb.indexOf("|")).trim();			//没用的
			sb = sb.delete(0, sb.indexOf("|")+1);
			String time=sb.substring(0, sb.indexOf("|")).trim();		//没用的
			sb = sb.delete(0, sb.indexOf("|")+1);
			String cardNo=sb.substring(0, sb.indexOf("|")).trim();
			sb = sb.delete(0, sb.indexOf("|")+1);
			String name = sb.substring(0, sb.indexOf("|")).trim();
			sb = sb.delete(0, sb.indexOf("|")+1);
			String openBank = sb.substring(0, sb.indexOf("|")).trim();
			sb = sb.delete(0, sb.indexOf("|")+1);
			String transMoney = sb.substring(0, sb.indexOf("|")).trim();
//			sb = sb.delete(0, sb.indexOf("|")+1);
			json = JzjrUtil.payJz(transTime+getOrder(transId),transTime, cardNo, name, openBank, transMoney);
			String respCode = json.getString("respCode");
			if(respCode.equals("00000")){
				sucNum++;
			}else{
				errorCard.append(id+"|");
			}
			transId++;
		}
		logger.info("总数量:"+orders.length+"成功:"+sucNum);
		result = "总数量:"+orders.length+"成功:"+sucNum;
		try{
			 if(orders.length==sucNum){
				 getResponse().getWriter().write(result);
			 }else{
				 getResponse().getWriter().write(result+errorCard);
			 }
		 }catch(Exception e){
			 e.printStackTrace(); 
		 }
	}
	
	public void selectTransLog(){
	      Map maps = new HashMap();
	      maps.put("subMerId", busMerId);
	      maps.put("orderStatus", orderStatus);
	      maps.put("startTime", startTime);
	      maps.put("endTime", endTime);
	      maps.put("merSysId", merSysId);
	      maps.put("cardNo", cardNo);
	      maps.put("refundStatus", refundStatus);
	      maps.put("t0Status", t0Status);
	      maps.put("transMerId", this.getParameterForString("transMerId"));
	      totalMap = coreTransLogDao.selectCoreTransLogCount(maps);
	      
	      int count = Integer.parseInt(totalMap.get("count").toString());
	      double sumMerAmt = 0;
	      if(null != totalMap.get("sumMerAmt")) {
	          sumMerAmt = Double.parseDouble(totalMap.get("sumMerAmt").toString());
	      }
	      double sumTransFee = 0;
	      if(null != totalMap.get("sumTransFee")) {
	          sumTransFee = Double.parseDouble(totalMap.get("sumTransFee").toString());
	      }
	      Map map = PageUtil.getPageMap(page, pageSize);
	      map.putAll(maps);
	      
	      List<CoreTransLog> coreTransLogList = coreTransLogDao.selectCoreTransLog(map);
	     
	      OrderInfo info = null;
	      String time;
	      // 时间格式转换
	      DecimalFormat df = new DecimalFormat("#0.00");
	      JSONArray array = JSONArray.fromObject(coreTransLogList);
	      JSONObject object = new JSONObject();
	      object.put("sumMerAmt", df.format(sumMerAmt));
	      object.put("Rows", array.toString());
//	      object.put("Total", count*25);      //交易笔数 25倍
	      object.put("Total", count);       //交易笔数 25倍
	      object.put("sumTransFee", df.format(sumTransFee));
	      orderList = object.toString();
	      try {
	          getResponse().getWriter().write(orderList);
	      } catch (IOException e) {
	          e.printStackTrace();
	      }

	    }
	
	public void downloadCoreXls() {
        Map maps = new HashMap();
        maps.put("subMerId", busMerId);
        maps.put("orderStatus", orderStatus);
        maps.put("startTime", startTime);
        maps.put("endTime", endTime);
        maps.put("merSysId", merSysId);
        maps.put("cardNo", cardNo);
        maps.put("refundStatus", refundStatus);
        maps.put("t0Status", t0Status);
        maps.put("transMerId", this.getParameterForString("transMerId"));
        
        HttpServletResponse response = getResponse();
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.reset();//清除缓存
        totalMap = coreTransLogDao.selectCoreTransLogCount(maps);
        int count = 0;
        if(null != totalMap && null != totalMap.get("count") && !"".equals(totalMap.get("count")))  
            count = Integer.parseInt(totalMap.get("count").toString());
        if(count > 30000) {
            try {
                this.renderText("<script>alert('失败,下载记录大于30000行');window.close()</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        Map map = PageUtil.getPageMap(1, count);
        map.putAll(maps);
        List<CoreTransLog> coreTransLogList = coreTransLogDao.selectCoreTransLog(map);
//      DecimalFormat df = new DecimalFormat("#0.00");
//      long sumMerAmt = 0;
        //设置下载类型
        response.setContentType("application/x-download");
        String sheetName = "POS交易";//文件名
        //设置导出字段  其中map中的key必须与实体类中的字段一致，不区分大小写
        Map<String, String> mapfile = new LinkedHashMap<String, String>();
        mapfile.put("intTxnDt", "交易日期");
        mapfile.put("intTxnTm", "交易时间");
        mapfile.put("merId", "商户号");
        mapfile.put("termId", "终端号");
        mapfile.put("chMerId", "渠道商户号");
        mapfile.put("chTermId", "渠道终端号");
        mapfile.put("chTrackingNo", "渠道流水号");
        mapfile.put("tradeCode", "交易类型");
        mapfile.put("transSource", "transSource");
        mapfile.put("cancelStat", "撤销状态");
        mapfile.put("cardNo", "卡号");
        mapfile.put("referenceNo", "'检索参考号");
        mapfile.put("transAmt", "'交易金额");
        mapfile.put("channelSettle", "渠道手续费");
        mapfile.put("replyCd", "应答码");
        mapfile.put("cardTypeFlag", "卡类别");
        /**
        mapfile.put("orderId", "订单号");
        mapfile.put("shortName", "商户简称");
        mapfile.put("respDesc", "返回码描述");
        **/
        
        
        //合计
//      Map mapAmountTo = new LinkedHashMap();
//      mapAmountTo.put("总交易金额:", df.format((double)sumMerAmt/100));
        //设置下载文件名
        try {
            response.setHeader("Content-Disposition", "attachment;filename="
                    + URLEncoder.encode(sheetName, "UTF-8") + ".xls");
            ImportExcelFile.ImportExcel(coreTransLogList, response.getOutputStream(), mapfile, sheetName, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
	
	
	public String getOrder(int orderId){
		String id="";
		for(int i=0;i<6-String.valueOf(orderId).length();i++){
			id+="0";
		}
		return id+orderId;
	}
	
	public void selectDyPay() {
        try {
            if (dyPayInfo == null) {
                dyPayInfo = new DyPayInfo();
            }
            Map map = PageUtil.getPageMap(page, pageSize);
            if(StringUtils.isNotEmpty(dyPayInfo.getMerSysId())){
                dyPayInfo.setMerSysId("trd"+dyPayInfo.getMerSysId()+"%");
            }
            map.put("dyPayInfo", dyPayInfo);
            map.put("merSysId", merSysId);
            int count = 0;
            count = dyPayInfoDao.selectDyPayInfoInfoCount(map);
            List<DyPayInfo> dyPayInfos = dyPayInfoDao.selectDyPayInfoInfo(map);
            JSONArray array = JSONArray.fromObject(dyPayInfos);
            JSONObject object = new JSONObject();
            object.put("Rows", array.toString());
            object.put("Total", count);
            getResponse().getWriter().write(object.toString());
          } catch (Exception e) {
            e.printStackTrace();
          }
    }
	
	public void selectScanPay() {
        try {
            if (scanOrderInfo == null) {
                scanOrderInfo = new ScanOrderInfo();
            }
            Map map = PageUtil.getPageMap(page, pageSize);
            map.put("scanOrderInfo", scanOrderInfo);
            int count = 0;
            count = scanOrderInfoDao.countScanOrderInfo(map);
            List<ScanOrderInfo> scanOrderInfoList = scanOrderInfoDao.selectScanOrderInfo(map);
            JSONArray array = JSONArray.fromObject(scanOrderInfoList);
            JSONObject object = new JSONObject();
            object.put("Rows", array.toString());
            object.put("Total", count);
            getResponse().getWriter().write(object.toString());
          } catch (Exception e) {
            e.printStackTrace();
          }
    }
	
	public void queryScanPay() throws Exception{
        String id = this.getParameterForString("id");
        logger.info("进入扫码查询 id="+id);
        ScanOrderInfo scanPayInfo = new ScanOrderInfo();
        scanPayInfo.setId(Long.parseLong(id));
        scanPayInfo = scanOrderInfoDao.selectInfoByOrderId(scanPayInfo);
        Map<String,String> map=getScanMap(scanPayInfo);
        map.put("busicd", "INQY");
        map.put("origOrderNum", scanPayInfo.getOrderId());
        
        map = signScanMap(map,scanPayInfo.getScanMerSign());
        JSONObject paramJson = JSONObject.fromObject(map);
        System.out.println("paramJson="+paramJson.toString());
        JSONObject rJson = sendScan(paramJson);
        if(!rJson.optString("respcd").equals(scanPayInfo.getRespcd())){
            scanPayInfo.setStatus("2");
            scanPayInfo.setRespcd(rJson.optString("respcd"));
            scanPayInfo.setRespmsg(rJson.optString("errorDetail"));
            scanOrderInfoDao.updateInfo(scanPayInfo);
        }
        getResponse().getWriter().write(rJson.toString());
   }
	
	
	public void cancelScanPay() throws Exception{
        String id = this.getParameterForString("id");
        logger.info("进入扫码取消 id="+id);
        String orderId=DateUtil.getDate("yyMMddHHmmssSSS");

        ScanOrderInfo scanPayInfo = new ScanOrderInfo();
        scanPayInfo.setId(Long.parseLong(id));
        scanPayInfo = scanOrderInfoDao.selectInfoByOrderId(scanPayInfo);
        Map<String,String> map=getScanMap(scanPayInfo);
        map.put("busicd", "CANC");
        map.put("orderNum", orderId);
        map.put("origOrderNum", scanPayInfo.getOrderId());
        
        map = signScanMap(map,scanPayInfo.getScanMerSign());
        JSONObject paramJson = JSONObject.fromObject(map);
        System.out.println("paramJson="+paramJson.toString());
        
        ScanOrderInfo cancelInfo = new ScanOrderInfo();
        cancelInfo.setOrderId(orderId);
        cancelInfo.setOrderType("04");       //取消订单
        cancelInfo.setCurrency(scanPayInfo.getCurrency());
        cancelInfo.setTxamt(scanPayInfo.getTxamt());
        cancelInfo.setUserId(scanPayInfo.getUserId());
        cancelInfo.setChcd(scanPayInfo.getChcd());
        cancelInfo.setStatus("1");           //初始订单
        cancelInfo.setOrigOrderId(scanPayInfo.getOrderId());
        scanOrderInfoDao.insertInfo(cancelInfo);
        JSONObject rJson = sendScan(paramJson);
        if(rJson.optString("respcd").equals("00")){
            cancelInfo.setStatus("2");
            }
        cancelInfo.setTransTime(rJson.optString("transTime"));
        cancelInfo.setRespcd(rJson.optString("respcd"));
        cancelInfo.setRespmsg(rJson.optString("errorDetail"));
        scanOrderInfoDao.updateInfo(cancelInfo);
        
        getResponse().getWriter().write(rJson.toString());
   }
	
	public void refundScanPay() throws Exception{
	    String id = this.getParameterForString("id");
	    logger.info("进入扫码退款 id="+id);
	    String orderId=DateUtil.getDate("yyMMddHHmmssSSS");
	    
	    ScanOrderInfo scanPayInfo = new ScanOrderInfo();
	    scanPayInfo.setId(Long.parseLong(id));
	    scanPayInfo = scanOrderInfoDao.selectInfoByOrderId(scanPayInfo);
	    Map<String,String> map=getScanMap(scanPayInfo);
	    map.put("busicd", "REFD");
	    map.put("orderNum", orderId);
	    map.put("txamt", addZero(true, scanPayInfo.getTxamt(), 12, "0"));
	    map.put("origOrderNum", scanPayInfo.getOrderId());
	    
	    map = signScanMap(map,scanPayInfo.getScanMerSign());
	    JSONObject paramJson = JSONObject.fromObject(map);
	    System.out.println("paramJson="+paramJson.toString());
	    
	    ScanOrderInfo cancelInfo = new ScanOrderInfo();
	    cancelInfo.setOrderId(orderId);
	    cancelInfo.setOrderType("05");       //退款订单
	    cancelInfo.setCurrency(scanPayInfo.getCurrency());
	    cancelInfo.setTxamt(scanPayInfo.getTxamt());
	    cancelInfo.setUserId(scanPayInfo.getUserId());
	    cancelInfo.setChcd(scanPayInfo.getChcd());
	    cancelInfo.setStatus("1");           //初始订单
        cancelInfo.setOrigOrderId(scanPayInfo.getOrderId());
	    scanOrderInfoDao.insertInfo(cancelInfo);
	    JSONObject rJson = sendScan(paramJson);
	    if(rJson.optString("respcd").equals("00")){
	        cancelInfo.setStatus("2");
            }
	    cancelInfo.setTransTime(rJson.optString("transTime"));
	    cancelInfo.setRespcd(rJson.optString("respcd"));
	    cancelInfo.setRespmsg(rJson.optString("errorDetail"));
	    scanOrderInfoDao.updateInfo(cancelInfo);
	    
	    getResponse().getWriter().write(rJson.toString());
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
	
	public Map<String,String> getScanMap(ScanOrderInfo scanOrderInfo){
        Map<String,String> map=new HashMap<String,String>();
        map.put("version", "2.1");
        map.put("signType", "SHA256");
        map.put("charset", "utf-8");
        
        map.put("txndir", "Q");
        map.put("inscd", "92171888");     //机构号
        map.put("mchntid", scanOrderInfo.getScanMerId());     //商户号
        map.put("terminalid", "00000001");     //终端号
        
        return map;
	}
	
	public Map<String,String> signScanMap(Map<String,String> map,String scanMerSign ){
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
        stringSignTemp+=scanMerSign;
        //制作签名
        String sign = StringEncrypt.Encrypt(stringSignTemp, "");
        map.put("sign", sign);
        
        return map;
    }
	
	public static JSONObject sendScan(JSONObject json){
       DefaultHttpClient httpClient = new DefaultHttpClient(); 
        try {
//         HttpPost request = new HttpPost("http://test.quick.ipay.so/scanpay/unified");  //test
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
            System.out.println("收到应答:"+sb.toString());
            JSONObject rJson=JSONObject.fromObject(sb.toString());
            JSONObject returnJson = new JSONObject();
            returnJson.put("transTime", rJson.get("transTime"));
            returnJson.put("respcd", rJson.get("respcd"));
            returnJson.put("errorDetail", rJson.get("errorDetail"));
            returnJson.put("orderNum", rJson.get("orderNum"));
            return returnJson;
        } catch (Exception e) {
           e.printStackTrace();
           return null;
        }
    }
	
	
	public void downloadDy(){

	    if (dyPayInfo == null) {
            dyPayInfo = new DyPayInfo();
        }
	    Map map = new HashMap();
        if(StringUtils.isNotEmpty(dyPayInfo.getMerSysId())){
            dyPayInfo.setMerSysId("trd"+dyPayInfo.getMerSysId()+"%");
        }
        map.put("dyPayInfo", dyPayInfo);
        map.put("merSysId", merSysId);
        int count = 0;
        
        HttpServletResponse response = getResponse();
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.reset();//清除缓存
        count = dyPayInfoDao.selectDyPayInfoInfoCount(map);
        if(count > 30000) {
            try {
                this.renderText("<script>alert('失败,下载记录大于30000行');window.close()</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }      
        map = PageUtil.getPageMap(1, count);

        List<DyPayInfo> dyPayInfos = dyPayInfoDao.selectDyPayInfoInfo(map);
        //设置下载类型
        response.setContentType("application/x-download");
        String sheetName = "代付交易";//文件名
        //设置导出字段  其中map中的key必须与实体类中的字段一致，不区分大小写
        Map<String, String> mapfile = new LinkedHashMap<String, String>();
        mapfile.put("orderId", "流水号");
        mapfile.put("accountName", "姓名");
        mapfile.put("cardNo", "卡号");
        mapfile.put("transTime", "交易时间");
        mapfile.put("transAmt", "交易金额");
        mapfile.put("respCode", "应答码");
        try {
            response.setHeader("Content-Disposition", "attachment;filename="
                    + URLEncoder.encode(sheetName, "UTF-8") + ".xls");
            ImportExcelFile.ImportExcel(dyPayInfos, response.getOutputStream(), mapfile, sheetName, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void downloadScan(){

	    if (scanOrderInfo == null) {
            scanOrderInfo = new ScanOrderInfo();
        }
        Map map = PageUtil.getPageMap(page, pageSize);
        map.put("scanOrderInfo", scanOrderInfo);
        int count = 0;
        count = scanOrderInfoDao.countScanOrderInfo(map);
        
        HttpServletResponse response = getResponse();
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.reset();//清除缓存
        count = scanOrderInfoDao.countScanOrderInfo(map);
        if(count > 30000) {
            try {
                this.renderText("<script>alert('失败,下载记录大于30000行');window.close()</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }      
        map = PageUtil.getPageMap(1, count);

        List<ScanOrderInfo> scanOrderInfoList = scanOrderInfoDao.selectScanOrderInfo(map);
        //设置下载类型
        response.setContentType("application/x-download");
        String sheetName = "快捷交易";//文件名
        //设置导出字段  其中map中的key必须与实体类中的字段一致，不区分大小写
        Map<String, String> mapfile = new LinkedHashMap<String, String>();
        mapfile.put("orderId", "流水号");
        mapfile.put("chcd", "渠道");
        mapfile.put("merSysId", "机构号");
        mapfile.put("createTime", "交易时间");
        mapfile.put("txamt", "交易金额");
        mapfile.put("respcd", "应答码");
        mapfile.put("respmsg", "应答码解释");
        mapfile.put("orderType", "交易类型");
        try {
            response.setHeader("Content-Disposition", "attachment;filename="
                    + URLEncoder.encode(sheetName, "UTF-8") + ".xls");
            ImportExcelFile.ImportExcel(scanOrderInfoList, response.getOutputStream(), mapfile, sheetName, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    
    }
	
}

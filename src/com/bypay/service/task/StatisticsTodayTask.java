package com.bypay.service.task;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bypay.dao.ScanOrderInfoDao;
import com.bypay.dao.SumMerProfitDao;
import com.bypay.dao.TractInfoDao;
import com.bypay.domain.ScanOrderInfo;
import com.bypay.domain.SubMerCashout;
import com.bypay.domain.SubMerCashoutBatch;
import com.bypay.domain.SubMerInfo;
import com.bypay.domain.SumMerProfit;
import com.bypay.domain.TractInfo;
import com.bypay.service.SubMerCashoutBatchService;
import com.bypay.service.SubMerCashoutService;
import com.bypay.service.SubMerInfoService;
import com.bypay.util.DateUtil;
import com.bypay.util.FTPUtil;
import com.bypay.util.FileUtil;
import com.bypay.util.StringEncrypt;
import com.google.common.collect.Maps;

@Component("statisticsTodayComponent")
public class StatisticsTodayTask {
	
	private static Logger logger = Logger.getLogger(StatisticsTodayTask.class);
	
	
	@Autowired
	private SubMerCashoutService subMerCashoutService;
	@Autowired
	private SubMerCashoutBatchService subMerCashoutBatchService;
	@Autowired
	private SubMerInfoService subMerInfoService;
   @Inject
    private SumMerProfitDao sumMerProfitDao;
   @Inject
    private TractInfoDao tractInfoDao;
   @Inject
    private ScanOrderInfoDao scanOrderInfoDao;
	private static final String ENTER = "\r\n"; //回车换行符

	private static final String DOT = "|";   //逗号
	
	private static final String UNDERLINE = "_";
	
	ResourceBundle rb = ResourceBundle.getBundle("com/bypay/config/path", Locale.getDefault()); // 存放路径
	
//	String batchId = "1";			//1系统
//	String batchId = "2";			//2系统
	String batchId = rb.getString("system");
	
	public void execute() {
		SubMerCashout subMerCashout = new SubMerCashout();
		subMerCashout.setCreateTime(DateUtil.getDate(new Date(), "yyyy-MM-dd")
				.replace("-", ""));
		subMerCashout.setOrderStatus("0"); //用户订单状态为申请
		subMerCashout.setGrade("0"); //只查询普通的商户
		Map<String, Object> map = Maps.newHashMap();
		map.put("subMerCashout", subMerCashout);
		List<SubMerCashout> subMerCashouts = subMerCashoutService
				.selectSubMerCashoutListNotPage(map);
		Long orderAmtTotal = 0L; // 提现总金额
		Long transFeeTotal = 0L; // 提现总手续费
		Long orderCountTotal = 0L; // 总条数
		for (SubMerCashout subMerCashout2 : subMerCashouts) {
			SubMerInfo subMerInfo = subMerInfoService
					.getSubMerInfoById(subMerCashout2.getSubMerId());
			if ("1".equals(subMerInfo.getBillStatus())) {
				if (StringUtils.isNotBlank(subMerCashout2.getOrderAmt())) {
					orderAmtTotal += Long.parseLong(subMerCashout2
							.getOrderAmt());
				}
				if (StringUtils.isNotBlank(subMerCashout2.getTransFee())) {
					transFeeTotal += Long.parseLong(subMerCashout2
							.getTransFee());
				}
			}
			orderCountTotal++;
		}
		SubMerCashoutBatch subMerCashoutBatch = new SubMerCashoutBatch();
		subMerCashoutBatch.setBatchTime(DateUtil.getDate(new Date(), "HH"));
		subMerCashoutBatch.setOrderAmt(String.valueOf(orderAmtTotal));
		subMerCashoutBatch.setCreateDate(DateUtil.getDate(new Date(),
				"yyyy-MM-dd"));
		subMerCashoutBatch.setFinishTime(null);
		subMerCashoutBatch.setTransFee(String.valueOf(transFeeTotal));
		subMerCashoutBatch.setOrderCount(String.valueOf(orderCountTotal));
		subMerCashoutBatch.setStatus("1"); // 生成代发文件等待代发
		String batchId = subMerCashoutBatchService
				.insertSubMerCashoutBatch(subMerCashoutBatch);

		for (SubMerCashout subMerCashout3 : subMerCashouts) {
			SubMerInfo subMerInfo = subMerInfoService
					.getSubMerInfoById(subMerCashout3.getSubMerId());
			if ("1".equals(subMerInfo.getBillStatus())) {
				subMerCashout3.setBatchId(batchId);
				// 操作完之后更改数据状态
				subMerCashout3.setOrderStatus("1");
				subMerCashoutService.updateSubMerCashout(subMerCashout3);
			}
		}

	}
	
	/**
	 * 机构分润统计
	 * @Title:        sumProfit 
	 * @Description:  
	 * @param:            
	 * @return:       void    
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2016-12-22 上午11:16:14
	 */
	public void sumProfit(){
	     //获取前一天日期
	    Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String settleDate = formatter.format(c.getTime());
//        String settleDate = "2016-12-22";
        Map map = new HashMap();
        map.put("createDate", settleDate);
        List<Map> sumList = sumMerProfitDao.getSumList(map);
        for(Map merMap:sumList){
            //3是撤销交易
            if(!merMap.get("transType").equals("3")){
                SumMerProfit sumMerProfit  = new SumMerProfit();
                sumMerProfit.setCreateDate(settleDate);
                sumMerProfit.setMerSysId(merMap.get("merSysId").toString());
                sumMerProfit.setPayfor("0");        //0未提现 1已提现
                sumMerProfit.setSumAgent1Profit(merMap.get("agent1Profit").toString());
                sumMerProfit.setSumAgent2Profit(merMap.get("agent2Profit").toString());
                sumMerProfit.setSumAgent3Profit(merMap.get("agent3Profit").toString());
                sumMerProfit.setSumAgent4Profit(merMap.get("agent4Profit").toString());
                sumMerProfit.setSumAgent5Profit(merMap.get("agent5Profit").toString());
                sumMerProfit.setSumAgent6Profit(merMap.get("agent6Profit").toString());
                sumMerProfit.setSumAgent7Profit(merMap.get("agent7Profit").toString());
                sumMerProfit.setSumAgent8Profit(merMap.get("agent8Profit").toString());
                sumMerProfit.setSumAgent9Profit(merMap.get("agent9Profit").toString());
                sumMerProfit.setSumAgent10Profit(merMap.get("agent10Profit").toString());
                sumMerProfit.setSumAmt(merMap.get("sumMerAmt").toString());
                sumMerProfit.setSumMerProfit(merMap.get("sumMerProfit").toString());
                sumMerProfit.setSumMerProfit(merMap.get("sumMerProfit").toString());
                sumMerProfit.setTransType(merMap.get("transType").toString());
                sumMerProfitDao.insertMerProfit(sumMerProfit);
            }
        }
        
	}
	
	public void checkTract(){
	    logger.info("定时任务:开关通道");
//	    3218
//	    3215
//	    14-24
//	    时间
//	    3217  时间是9-14
	    SimpleDateFormat sdf = new SimpleDateFormat("HH");
	    String hour = sdf.format(new Date());
	    if(hour.equals("03")){
            //开启
	        String[] array = {"3246","3247","3248","3249","3255","3256","3254","3257","3258","3259","3260","3261","3262","3263","3264","3265"
	                ,"3266","3267","3268","3269","3270"};
	        changeStatus(array,"1");
            
        }else if(hour.equals("05")){
            //关闭
            String[] array = {"3249","3250","3251","3252","3253","3234","3233","3232","3215","3221","3222","3235","3236","3237","3238","3239",
                    "3240","3241","3242","3243"};
            changeStatus(array,"2");
            
            //关闭 17-05
        }else if(hour.equals("06")){
	        //开启
            String [] array = {"3217","3219","3220"};
            changeStatus(array,"1");
	    }else if(hour.equals("10")){
            //关闭
	        String [] array = {"3246","3247","3248","3255","3256","3254","3257","3258","3259","3260"};
            changeStatus(array,"2");
	    }else if(hour.equals("14")){
	        //关闭
	        String [] array = {"3217","3219","3220","3261","3262","3263","3264","3265","3266","3267","3268","3269","3270"};
            changeStatus(array,"2");
            //开启
            String [] array1 = {"3218","3224","3223","3249","3250","3251","3252","3253","3234","3233","3232"};
            changeStatus(array1,"1");
            
	    }else if(hour.equals("17")){
	        //开启 17-05
	        String[] array = {"3215","3221","3222","3235","3236","3237","3238","3239","3240","3241","3242","3243"};
	        changeStatus(array,"1");
	        
	    }else if(hour.equals("00")){
	        //关闭
	        String[] array = {"3218","3224","3223"};
            changeStatus(array,"2");
        }

	}
	
	//更改状态方法
	public void changeStatus(String[] tractIds,String status){
	    TractInfo tractInfo;
	    for(String tractId:tractIds){
	        tractInfo = new TractInfo();
            tractInfo.setTractId(tractId);
            tractInfo.setStatus(status);
            tractInfoDao.updateTractInfo(tractInfo);
	    }
	}
	
	/**
	 * 每小时 取消 扫码未支付的订单
	 * @Title:        cancelScan 
	 * @Description:  
	 * @param:            
	 * @return:       void    
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2017-5-8 上午11:37:30
	 */
	public void cancelScan(){
	    String before = getTimeByHour(-1);
	    logger.info("定时任务:关闭扫码订单"+before);
	    Map map = new HashMap();
	    ScanOrderInfo scanOrderInfo = new ScanOrderInfo();
	    scanOrderInfo.setRespcd("09");// 待支付
	    scanOrderInfo.setTransTime(before);
	    map.put("scanOrderInfo", scanOrderInfo);
	    List<ScanOrderInfo> noPayList = scanOrderInfoDao.selectNoPayScanOrderInfo(map);
	    for(ScanOrderInfo nopay : noPayList){
	        Map<String,String> paramMap=getScanMap(nopay);
	        paramMap.put("busicd", "INQY");
	        paramMap.put("origOrderNum", nopay.getOrderId());
	        paramMap = signScanMap(paramMap,nopay.getScanMerSign());
	        JSONObject paramJson = JSONObject.fromObject(paramMap);
	        System.out.println("paramJson="+paramJson.toString());
	        JSONObject rJson = sendScan(paramJson);
	        //停10秒再查一次
	        try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
	        rJson = sendScan(paramJson);
	        //先查询 再发送取消
	       if(!rJson.optString("respcd").equals("00")){
	            //发起取消
	            String orderId=DateUtil.getDate("yyMMddHHmmssSSS");

	            Map<String,String> cancelMap=getScanMap(nopay);
	            cancelMap.put("busicd", "CANC");
	            cancelMap.put("orderNum", orderId);
	            cancelMap.put("origOrderNum", nopay.getOrderId());
	            
	            cancelMap = signScanMap(cancelMap,nopay.getScanMerSign());
	            paramJson = JSONObject.fromObject(cancelMap);
	            System.out.println("paramJson="+paramJson.toString());
	            
	            ScanOrderInfo cancelInfo = new ScanOrderInfo();
	            cancelInfo.setOrderId(orderId);
	            cancelInfo.setOrderType("04");       //取消订单
	            cancelInfo.setCurrency(nopay.getCurrency());
	            cancelInfo.setTxamt(nopay.getTxamt());
	            cancelInfo.setUserId(nopay.getUserId());
	            cancelInfo.setChcd(nopay.getChcd());
	            cancelInfo.setStatus("1");           //初始订单
	            cancelInfo.setOrigOrderId(nopay.getOrderId());
	            scanOrderInfoDao.insertInfo(cancelInfo);
	            rJson = sendScan(paramJson);
	            if(rJson.optString("respcd").equals("00")){
	                cancelInfo.setStatus("2");
	                //修改原订单状态
	                nopay.setStatus("2");
	                nopay.setRespcd("Y9");
	                nopay.setRespmsg("订单已关闭或取消");
	                scanOrderInfoDao.updateInfo(nopay);
	                }
	            cancelInfo.setTransTime(rJson.optString("transTime"));
	            cancelInfo.setRespcd(rJson.optString("respcd"));
	            cancelInfo.setRespmsg(rJson.optString("errorDetail"));
	            scanOrderInfoDao.updateInfo(cancelInfo);
	        }
	    }
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
//	            HttpPost request = new HttpPost("http://test.quick.ipay.so/scanpay/unified");  //test
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
	/**
	 * 获得前后小时时间
	 * @Title:        getTimeByHour 
	 * @Description:  
	 * @param:        @param hour
	 * @param:        @return    
	 * @return:       String    
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2017-5-8 上午11:51:53
	 */
	public static String getTimeByHour(int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
    }
	
	public void cashout(){
		logger.info("开始生成T0文件");
		DecimalFormat df = new DecimalFormat("0.00");
		SubMerCashout subMerCashout = new SubMerCashout();
		String fileDate = DateUtil.getDate(new Date(), "YYYYMMdd_HHmm");
		String date = DateUtil.getDate(new Date(), "yyyyMMdd");
		subMerCashout.setCreateTime(date);
		subMerCashout.setOrderStatus("0"); //用户订单状态为申请
//		subMerCashout.setGrade("0"); //只查询普通的商户

		List<SubMerCashout> subMerCashouts = subMerCashoutService.selectSubMerCashoutList2(subMerCashout);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < subMerCashouts.size(); i++){
			SubMerCashout subMer = subMerCashouts.get(i);
			sb.append("NAPP").append(date).append(subMer.getId()).append(DOT);
			sb.append(date).append(DOT);
			sb.append(subMer.getSettAccountNo()).append(DOT);
			sb.append(subMer.getSettAccountName()).append(DOT);
			sb.append(subMer.getLineNum()).append(DOT);
			sb.append(getBankNameByCode(subMer.getSettAgency())).append(subMer.getOpenBank()).append(DOT);
			sb.append("156").append(DOT);
			Double orderAmt = Double.valueOf(subMer.getOrderAmt()) / 100.0;
			sb.append(df.format(orderAmt)).append(DOT);
			sb.append("测试").append(DOT);
			sb.append(ENTER);
		}
		if( subMerCashouts.size() > 0){
//			String fileName = "D0_"+fileDate+"_t";		//D0_日期_时间_t.txt103环境1系统
			String fileName = "";						//T0_日期_时间_t.txt23环境2系统
			if(Integer.parseInt(batchId)==2){
				fileName = "T0_"+fileDate+"_t";
			}else{
				fileName = "D0_"+fileDate+"_t";	
			}
			String path = rb.getString("cashOutT0");
			String type = "txt";
			boolean result = FileUtil.generateFile(sb.toString(), path, fileName, type);
			if( !result ){
				System.out.println("生成文件失败");
				logger.info("生成文件失败！");
				return ;
			}
			//获取上传信息
			String host = rb.getString("sftp.host");
			String port = rb.getString("sftp.port");
			String userName = rb.getString("sftp.username");
			String passWord = rb.getString("sftp.password");
			String uploadDir = rb.getString("sftp.upload.dir");
//			String uploadFile = path + File.separator + fileName+"."+type;
			
			//上传文件并且改名
//			SFTPUtils sftp = new SFTPUtils();
//			ChannelSftp channel = sftp.connect(host, Integer.valueOf(port), username, password); //打开连接
			boolean uploadResult = FTPUtil.uploadFileRename(host, Integer.valueOf(port), userName, passWord, path, fileName + "." +type, uploadDir); 
				//sftp.upload(uploadDir, uploadFile, channel); //上传文件
			//sftp.connectionClose(channel); //关闭连接
			
			if( uploadResult ){
				System.out.println("上传文件成功!");
				logger.info("上传文件成功！");
				for(int j =0; j < subMerCashouts.size(); j++){
					SubMerCashout cashOut = subMerCashouts.get(j);
					SubMerCashout cashOutSuccess = new SubMerCashout();
					cashOutSuccess.setId(cashOut.getId());
					cashOutSuccess.setOrderStatus("2");
					cashOutSuccess.setFinishTime(DateUtil.getDate("yyyyMMddHHmmss"));
					subMerCashoutService.updateSubMerCashout(cashOutSuccess);
				}
			}else{
				logger.info("上传文件失败！");
				for(int j =0; j < subMerCashouts.size(); j++){
					SubMerCashout cashOut = subMerCashouts.get(j);
					SubMerCashout cashOutSuccess = new SubMerCashout();
					cashOutSuccess.setId(cashOut.getId());
					cashOutSuccess.setOrderStatus("9");
					cashOutSuccess.setFinishTime(DateUtil.getDate("yyyyMMddHHmmss"));
					subMerCashoutService.updateSubMerCashout(cashOutSuccess);
				}
			}
		}else{
			logger.info("没有体现记录,不生成T0文件");
		}
		execute();
	}

	
	public void cashoutMS() throws UnsupportedEncodingException{ 
		logger.info("开始生成民生银行T0文件");
		
		DecimalFormat df = new DecimalFormat("0.00");
		SubMerCashout subMerCashout = new SubMerCashout();
		String date = DateUtil.getDate(new Date(), "yyyyMMdd");
		subMerCashout.setCreateTime(date);
		subMerCashout.setOrderStatus("0"); //用户订单状态为申请
//		subMerCashout.setGrade("0"); //只查询普通的商户
		
		List<SubMerCashout> subMerCashouts = subMerCashoutService.selectSubMerCashoutList2(subMerCashout);
		//进行筛选
		List<String> orderIdList = new ArrayList<String>();
		//重复交易
		List<String> errorIds = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		StringBuilder sbMS = new StringBuilder();	//民生
		StringBuffer top = new StringBuffer();		//报文头 
		StringBuffer topMS= new StringBuffer();		//报文头 民生
		BigDecimal totalMoney = new BigDecimal(0);	//总金额
		BigDecimal totalMoneyMS = new BigDecimal(0);	//总金额
		int count =0;
		int countMS = 0;
		for(int i = 0; i < subMerCashouts.size(); i++){
			SubMerCashout subMer = subMerCashouts.get(i);
			if(StringUtils.isNotEmpty(subMer.getOrderId()) && orderIdList.contains(subMer.getOrderId())){
				errorIds.add(subMer.getId());
				continue;
			}
			orderIdList.add(subMer.getOrderId());
			if(subMer.getOpenBank().indexOf("民生")==-1){		//非民生银行
				sb.append(subMer.getOrderId()==null?"0":subMer.getOrderId()).append(DOT);		//第三方流水号
				sb.append(subMer.getSettAccountNo()).append(DOT);		//卡号
				sb.append(subMer.getSettAccountName()).append(DOT);		//户名
				sb.append(subMer.getLineNum()).append(DOT);				//支付行号
				sb.append(getBankNameByCode(subMer.getSettAgency())).append(subMer.getOpenBank()).append(DOT);	//开户行
				sb.append(subMer.getOrderAmt()).append(DOT);		//金额已分为单位
				totalMoney = totalMoney.add(new BigDecimal(subMer.getOrderAmt()));		//总金额计算
				sb.append("ruiyin").append(DOT);		//摘要
				//备注
				sb.append(ENTER);
				count++;
			}else{
				sbMS.append(subMer.getOrderId()==null?"0":subMer.getOrderId()).append(DOT);		//第三方流水号
				sbMS.append(subMer.getSettAccountNo()).append(DOT);		//卡号
				sbMS.append(subMer.getSettAccountName()).append(DOT);		//户名
				sbMS.append(subMer.getOrderAmt()).append(DOT);		//金额已分为单位
				totalMoneyMS = totalMoneyMS.add(new BigDecimal(subMer.getOrderAmt()));		//总金额计算
				sbMS.append("ruiyin").append(DOT);		//摘要
				//备注
				sbMS.append(ENTER);
				countMS++;
			}
		}
		//开始制作表头
		top.append("PO").append(DOT).append(count).append(DOT).append(totalMoney.toString()).append(ENTER);		//PO|总笔数|总金额
		topMS.append("P").append(DOT).append(countMS).append(DOT).append(totalMoneyMS.toString()).append(ENTER);		//P|总笔数|总金额
		//文件名req_yyyyMMdd_NNN.txt
		String time = DateUtil.getDate(new Date(), "HHmm");
		if( subMerCashouts.size() > 0){
			String fileName = "req_outer_"+date+"_"+batchId+time+"M";
			String fileNameMS = "req_"+date+"_"+batchId+time+"M";
			String keyName = fileName.substring(0,fileName.length()-1);
			String keyNameMS = fileNameMS.substring(0,fileNameMS.length()-1);
			String path = rb.getString("cashoutMST0");
			path = path +"/"+date;
			String type = "txt";
			String txtContent= top.append(sb).append("########").toString();
			String txtContentMS= topMS.append(sbMS).append("########").toString();
			if(count>0){
			boolean result = FileUtil.generateFileGBK(txtContent, path, fileName, type);
				if( !result ){
					System.out.println("生成跨行文件失败");
					logger.info("生成跨行文件失败");
					return ;
				}
			}
			if(countMS>0){
				boolean result = FileUtil.generateFileGBK(txtContentMS, path, fileNameMS, type);
				if( !result ){
					System.out.println("生成非跨行文件失败");
					logger.info("生成非跨行文件失败");
					return ;
				}
			}
			//获取上传信息
			String msKey = rb.getString("ms.key");
			String host = rb.getString("ftp.host");
			String port = rb.getString("ftp.port");
			String userName = rb.getString("ftp.username");
			String passWord = rb.getString("ftp.password");
			String uploadDir = rb.getString("ftp.upload.dir");
			String uploadDirMS = rb.getString("ftp.upload.dirMS");
//			String uploadFile = path + File.separator + fileName+"."+type;
			// 密文文件路径
			byte[] key = msKey.getBytes();
			// 加密
			try {
				if(count>0){
				encodeAESFile(key, path+"/"+fileName+".txt", path+"/"+keyName+".txt");
				}
				if(countMS>0){
				encodeAESFile(key, path+"/"+fileNameMS+".txt", path+"/"+keyNameMS+".txt");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//上传文件
//			SFTPUtils sftp = new SFTPUtils();
//			ChannelSftp channel = sftp.connect(host, Integer.valueOf(port), username, password); //打开连接
			boolean uploadResult=false;
			if(count>0){
				uploadResult = FTPUtil.uploadFileMS(host, Integer.valueOf(port), userName, passWord, path, keyName + "." +type, uploadDir);
			}
			if(countMS>0){
				uploadResult = FTPUtil.uploadFileMS(host, Integer.valueOf(port), userName, passWord, path, keyNameMS + "." +type, uploadDirMS);
			}
				//sftp.upload(uploadDir, uploadFile, channel); //上传文件
			//sftp.connectionClose(channel); //关闭连接
			
			if( uploadResult ){
				System.out.println("上传文件成功!");
				logger.info("上传文件成功！");
				for(int j =0; j < subMerCashouts.size(); j++){
					SubMerCashout cashOut = subMerCashouts.get(j);
					SubMerCashout cashOutSuccess = new SubMerCashout();
					cashOutSuccess.setId(cashOut.getId());
					cashOutSuccess.setOrderStatus("2");
					cashOutSuccess.setFinishTime(DateUtil.getDate("yyyyMMddHHmmss"));
					subMerCashoutService.updateSubMerCashout(cashOutSuccess);
				}
			}else{
				logger.info("上传文件失败！");
				for(int j =0; j < subMerCashouts.size(); j++){
					SubMerCashout cashOut = subMerCashouts.get(j);
					SubMerCashout cashOutSuccess = new SubMerCashout();
					cashOutSuccess.setId(cashOut.getId());
					cashOutSuccess.setOrderStatus("9");
					cashOutSuccess.setFinishTime(DateUtil.getDate("yyyyMMddHHmmss"));
					subMerCashoutService.updateSubMerCashout(cashOutSuccess);
				}
			}
			logger.info("更改重复交易状态");
			for(int j =0; j < errorIds.size(); j++){
				String id = errorIds.get(j);
				SubMerCashout cashOutError = new SubMerCashout();
				cashOutError.setId(id);
				cashOutError.setOrderStatus("7");
				cashOutError.setFinishTime(DateUtil.getDate("yyyyMMddHHmmss"));
				subMerCashoutService.updateSubMerCashout(cashOutError);
			}
//			batchId = Integer.parseInt(batchId)+1+"";
		}else{
			logger.info("没有体现记录,不生成T0文件");
		}
		execute();
//		decodeMS();
	}
	
	public void decodeMS(){
		logger.info("开始解密民生银行T0文件");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		//获取昨天的文件夹名
//		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		System.out.println(date);
		
		String msKey = rb.getString("ms.key");
		String host = rb.getString("ftp.host");
		String port = rb.getString("ftp.port");
		String userName = rb.getString("ftp.username");
		String passWord = rb.getString("ftp.password");
		String localPath = rb.getString("cashoutMsres");
		String resPath = rb.getString("ftp.res.dir");
		String resouterPath = rb.getString("ftp.resouter.dir");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateDir = sdf.format(date);
		localPath = localPath+"/"+dateDir;
		FTPUtil.downFileMS(host, Integer.valueOf(port), userName, passWord, resPath, localPath);
		FTPUtil.downFileMS(host, Integer.valueOf(port), userName, passWord, resouterPath, localPath);
		File file = new File(localPath);
		File[] files = file.listFiles();
		byte[] key = msKey.getBytes();
		for(File f:files){
			if(f.getName().endsWith("M.txt")){
				continue;
			}
			String system = f.getName().substring(f.getName().length()-9,f.getName().length()-8);
			if(!system.equals(batchId)){		
				continue;
			}
			try {
				String fileName = f.getName().replace(".", "M.");
				logger.info("正在解密文件"+fileName);
				decodeAESFile(key, localPath+"/"+fileName, f.getPath());
				File mFile = new File(localPath+"/"+fileName);
				if(mFile.exists()){
					 InputStreamReader read = new InputStreamReader(
                     new FileInputStream(mFile),"GBK");//考虑到编码格式
                     BufferedReader bufferedReader = new BufferedReader(read);
                     String lineTxt = null;
                     int i =0;
                     while((lineTxt = bufferedReader.readLine()) != null){
                    	 if(i==0){//第一行
                    		 i++;
                    		 continue;
                    	 }
                    	 StringBuffer sb = new StringBuffer(lineTxt);
                    	 if(lineTxt.startsWith("#")){
                    		 continue;		//最后一行
                    	 }
                    	 String batchId = sb.substring(0,sb.indexOf("|"));
                    	 sb = sb.delete(0, sb.indexOf("|")+1);
                    	 sb = sb.delete(0, sb.indexOf("|")+1);
                    	 sb = sb.delete(0, sb.indexOf("|")+1);
                    	 sb = sb.delete(0, sb.indexOf("|")+1);
                    	 sb = sb.delete(0, sb.indexOf("|")+1);
                    	 String suc = sb.substring(0,sb.indexOf("|"));
                    	 sb = sb.delete(0, sb.indexOf("|")+1);
                    	 sb = sb.delete(0, sb.indexOf("|")+1);
                    	 String comment = sb.substring(0,sb.indexOf("|"));
                    	 
                    	 SubMerCashout smc = new SubMerCashout();
                    	 smc.setBatchId(batchId);
                    	 smc.setReserved1(comment);
                    	 if(suc.equals("S")){
                    		 smc.setOrderStatus("5");
                    	 }else{
                    		 smc.setOrderStatus("3");
                    	 }
                    	 smc.setFinishTime(DateUtil.getDate("yyyyMMddHHmmss"));
                    	 subMerCashoutService.updateSubMerCashoutByBatch(smc);
                     }
                     read.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	private String getBankNameByCode(String bankCode){
		
		if( StringUtils.isBlank(bankCode)){
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
					logger.error(e.getLocalizedMessage(), e);
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e);
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e);
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
					logger.error(e.getLocalizedMessage(), e);
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e);
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e);
				}
			}
		}
	}
	

	public static void main(String[] args) {
	    System.out.println(getTimeByHour(-1));
	}

}

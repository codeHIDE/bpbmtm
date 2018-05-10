package com.bypay.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map.Entry;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;

import com.bypay.common.BaseAction;
import com.bypay.dao.DyPayInfoDao;
import com.bypay.dao.MerInfoDao;
import com.bypay.dao.MerProfitApplyDao;
import com.bypay.dao.MerSettleStatictisDao;
import com.bypay.dao.SubMerTerminalDao;
import com.bypay.dao.SumMerProfitDao;
import com.bypay.domain.DyPayInfo;
import com.bypay.domain.MerInfo;
import com.bypay.domain.MerProfitApply;
import com.bypay.domain.MerProfitStatictis;
import com.bypay.domain.MerSettleStatictis;
import com.bypay.domain.StatisticsInfo;
import com.bypay.domain.SubMerTerminal;
import com.bypay.domain.SumMerProfit;
import com.bypay.service.BypayProfitStatictisService;
import com.bypay.service.MerProfitStatictisService;
import com.bypay.service.StatisticsService;
import com.bypay.service.task.StatisticsTodayTask;
import com.bypay.util.DateUtil;
import com.bypay.util.FileUtil;
import com.bypay.util.ImportExcelFile;
import com.bypay.util.Md5Util;
import com.bypay.util.PageUtil;

public class StatisticsAction extends BaseAction {
	private final static Logger logger = Logger.getLogger("bpbmtm");

	private String seriesJson;// JSONS数据
	private String axisJson;// 横坐标JSON数据
	private String sumInfo;// 辅助字段
	@Inject
	private StatisticsService statisticsService;
	@Inject
	private BypayProfitStatictisService bypayProfitStatictisService;
    ResourceBundle rb = ResourceBundle.getBundle("com/bypay/config/path", Locale.getDefault()); // 存放路径

	@Inject
	private MerProfitStatictisService merProfitStatictisService;
	private String startTime;// 开始时间
	private String endTime;// 结束时间
	private String merSysId;// 机构号
	private String mid;// 机构号
	private String agentId;// 代理商号
	private String merType; // 商户类型
	private String transType; // 交易类型
	private SumMerProfit sumMerProfit;	private MerProfitApply merProfitApply;
	
	@Inject
	private MerSettleStatictisDao merSettleStatictisDao;
	@Inject
	private SubMerTerminalDao subMerTerminalDao;
	@Inject
    private SumMerProfitDao sumMerProfitDao;
	@Inject
    private MerProfitApplyDao merProfitApplyDao;
	@Inject
    private MerInfoDao  merInfoDao;
	@Inject
	private DyPayInfoDao  dyPayInfoDao;
	// 分页开始
	private int page = 1;
	private int pageSize = 15;

	private String startMon; // 交易月份

	/**
	 * 获取系统日分润统计LIST
	 * 
	 * @return
	 */
	public void byPayProfitStatictis() {
		if (!"".equals(startTime) && null != startTime && !"".equals(endTime)
				&& null != endTime) {
			String startTimes = startTime.replace("-", "");
			String endTimes = endTime.replace("-", "");
			Map mapParameter = new HashMap();
			mapParameter.put("startTime", startTimes);
			mapParameter.put("endTime", endTimes);
			Map totalMap = bypayProfitStatictisService
					.getBypayProfitStatictisCount(mapParameter);
			int countSum = Integer
					.parseInt(totalMap.get("countSum").toString());
			double sumAmtSum = Double.parseDouble(totalMap.get("sumAmtSum")
					.toString());
			double sumFeeAmtSum = Double.parseDouble(totalMap.get(
					"sumFeeAmtSum").toString());
			double sumCamtSum = Double.parseDouble(totalMap.get("sumCamtSum")
					.toString());
			double sumFeeCamtSum = Double.parseDouble(totalMap.get(
					"sumFeeCamtSum").toString());
			double sumRamtSum = Double.parseDouble(totalMap.get("sumRamtSum")
					.toString());
			double sumFeeRamtSum = Double.parseDouble(totalMap.get(
					"sumFeeRamtSum").toString());
			double sumTractFeeSum = Double.parseDouble(totalMap.get(
					"sumTractFeeSum").toString());
			double bypayProfitSum = Double.parseDouble(totalMap.get(
					"bypayProfitSum").toString());
			Map map = PageUtil.getPageMap(page, pageSize);
			map.putAll(mapParameter);
			List<StatisticsInfo> statisticsInfos = bypayProfitStatictisService
					.getBypayProfitStatictisList(map);
			DecimalFormat df = new DecimalFormat("#0.00");
			JSONArray array = JSONArray.fromObject(statisticsInfos);
			JSONObject object = new JSONObject();
			object.put("Rows", array.toString());
			object.put("Total", countSum);
			object.put("sumAmtSum", df.format(sumAmtSum / 100));
			object.put("sumFeeAmtSum", df.format(sumFeeAmtSum / 100));
			object.put("sumCamtSum", df.format(sumCamtSum / 100D));
			object.put("sumFeeCamtSum", df.format(sumFeeCamtSum / 100));
			object.put("sumRamtSum", df.format(sumRamtSum / 100));
			object.put("sumFeeRamtSum", df.format(sumFeeRamtSum / 100));
			object.put("sumTractFeeSum", df.format(sumTractFeeSum / 100));
			object.put("bypayProfitSum", df.format(bypayProfitSum / 100));
			System.out.println(object.toString());
			try {
				getResponse().getWriter().write(object.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void selectSumMerProfit(){
        try {
            if (sumMerProfit == null) {
                sumMerProfit = new SumMerProfit();
            }
            Map map = PageUtil.getPageMap(page, pageSize);
            map.put("sumMerProfit", sumMerProfit);
            int count = 0;
            count = sumMerProfitDao.selectSumMerCount(map);
            List<SumMerProfit> sumMerProfitList = sumMerProfitDao.selectSumMerList(map);
            JSONArray array = JSONArray.fromObject(sumMerProfitList);
            JSONObject object = new JSONObject();
            object.put("Rows", array.toString());
            object.put("Total", count);
            getResponse().getWriter().write(object.toString());
          } catch (Exception e) {
            e.printStackTrace();
          }
	}
	
	public void selectMerApply(){
        try {
            if (merProfitApply == null) {
                merProfitApply = new MerProfitApply();
            }
            Map map = PageUtil.getPageMap(page, pageSize);
            map.put("merSysId", merSysId);
            map.put("startTime", startTime);
            map.put("endTime", endTime);
            int count = 0;
            count = merProfitApplyDao.selectApplyCount(map);
            List<MerProfitApply> merProfitApplyList = merProfitApplyDao.selectApplyList(map);
            JSONArray array = JSONArray.fromObject(merProfitApplyList);
            JSONObject object = new JSONObject();
            object.put("Rows", array.toString());
            object.put("Total", count);
            getResponse().getWriter().write(object.toString());
          } catch (Exception e) {
            e.printStackTrace();
          }
    }
	
	public void makeFile(){
	    Map map = new HashMap();
        map.put("status", "0");
	    List<MerProfitApply> merProfitApplyList = merProfitApplyDao.selectApplyList(map);
	    writeFile(merProfitApplyList);
	    
	}
	
	public void writeFile(List<MerProfitApply> merProfitApplyList){
	    String ENTER = "\r\n"; //回车换行符
	    String DOT = "|";   //逗号
	    String date = DateUtil.getDate(new Date(), "yyyyMMdd");
	    StringBuilder sb = new StringBuilder();
        StringBuffer top = new StringBuffer();      //报文头 
        BigDecimal totalMoney = new BigDecimal(0);  //总金额
        int count =0;
        for(MerProfitApply merProfitApply:merProfitApplyList){
            MerInfo merInfo = new MerInfo();
            merInfo.setMerSysId(merProfitApply.getMerSysId());
            merInfo = merInfoDao.selectMerInfoById(merInfo);
            sb.append(merInfo.getMerSysId()+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())).append(DOT);       //第三方流水号
            sb.append(merInfo.getSettAccountNo()).append(DOT);       //卡号
            sb.append(merInfo.getSettAccountName()).append(DOT);     //户名
            sb.append(merInfo.getLineNum()).append(DOT);             //支付行号
            sb.append(DateUtil.getBankNameByCode(merInfo.getSettAgency())).append(merInfo.getOpenBank()).append(DOT);  //开户行
            sb.append(merProfitApply.getPayMoney()).append(DOT);        //金额已分为单位
            totalMoney = totalMoney.add(new BigDecimal(merProfitApply.getPayMoney()));      //总金额计算
            sb.append("ruiyin").append(DOT);        //摘要
            //备注
            sb.append(ENTER);
            count++;
        }
        top.append("PO").append(DOT).append(count).append(DOT).append(totalMoney.toString()).append(ENTER);     //PO|总笔数|总金额
        String time = DateUtil.getDate(new Date(), "HHmm");
        String fileName = "req_outer_"+date+"_"+"3"+time+"M";
        String path = rb.getString("cashoutMST0");
        path = path +"/"+date;
        String type = "txt";
        String txtContent= top.append(sb).append("########").toString();
        boolean result = FileUtil.generateFileGBK(txtContent, path, fileName, type);
        if( !result ){
            System.out.println("生成跨行文件失败");
            logger.info("生成跨行文件失败");
            return ;
        }
        // 加密
        File txt = new File(path+"/"+fileName+".txt");
        try {
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(txt));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            HttpServletResponse response = getResponse();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(txt.getName().getBytes()));
            response.addHeader("Content-Length", "" + txt.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
//            response.setContentType("application/x-xls");
          response.setContentType("text/html");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
      } catch (Exception e) {
          e.printStackTrace();
      }
	}
	
	public void payOrder(){
	    String id = this.getParameterForString("id");
	    MerProfitApply merProfitApply = new MerProfitApply();
	    merProfitApply.setId(Long.parseLong(id));
	    merProfitApply =  merProfitApplyDao.selectApplyInfo(merProfitApply);
	    //更新sum
	    SumMerProfit sumMerProfit = new SumMerProfit();
	    sumMerProfit.setIds(merProfitApply.getOpIds().toString());
	    sumMerProfit.setPayfor("2");
	    sumMerProfitDao.updateProfit(sumMerProfit);
	    //更新APPLY
	    merProfitApply.setStatus("1");
	    merProfitApplyDao.updateApplyInfo(merProfitApply);
	    try {
            getResponse().getWriter().write("确认成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	
	/**
	 * 代理商利润下载
	 */
	public void agentProfitDownLoad() {
		// 此处startTime代表年份 endTime代表月份
		if (!"".equals(agentId) && null != agentId && !"".equals(startTime)
				&& null != startTime && !"".equals(endTime) && null != endTime) {
			int months = Integer.parseInt(endTime);
			if (months < 10) {
				endTime = "0" + endTime;
			}
			String yearMonth = startTime.trim() + endTime.trim();// 年+月 20140722
			Map mapParameter = new HashMap();
			mapParameter.put("yearMonth", yearMonth);
			mapParameter.put("agentId", agentId);

			List<StatisticsInfo> list = statisticsService
					.agentProfitDayStatistics(mapParameter);

			String merName = "";
			String data = "";

			DecimalFormat df = new DecimalFormat("#0.00");

			long sumBypayProfit = 0;
			long sumMerProfit = 0;
			long sumAgent1Profit = 0;
			long sumAgent2Profit = 0;

			long bypayProfit = 0;
			long merProfit = 0;
			long agent1Profit = 0;
			long agent2Profit = 0;
			long merAmt = 0;
			long merCapital = 0;

			if (null != list && list.size() > 0) {
				merName = list.get(0).getAgentName();
				data = list.get(0).getCreateTime();
				for (int i = 0; i < list.size(); i++) {
					StatisticsInfo info = list.get(i);

					if (null != info && null != info.getBypayProfit()
							&& !"".equals(info.getBypayProfit())) {
						bypayProfit = Long.parseLong(info.getBypayProfit());
					}
					if (null != info && null != info.getMerProfit()
							&& !"".equals(info.getMerProfit())) {
						merProfit = Long.parseLong(info.getMerProfit());
					}
					if (null != info && null != info.getAgent1Profit()
							&& !"".equals(info.getAgent1Profit())) {
						agent1Profit = Long.parseLong(info.getAgent1Profit());
					}
					if (null != info && null != info.getAgent2Profit()
							&& !"".equals(info.getAgent2Profit())) {
						agent2Profit = Long.parseLong(info.getAgent2Profit());
					}
					if (null != info && null != info.getMerAmt()
							&& !"".equals(info.getMerAmt())) {
						merAmt = Long.parseLong(info.getMerAmt());
					}
					if (null != info && null != info.getMerCapital()
							&& !"".equals(info.getMerCapital())) {
						merCapital = Long.parseLong(info.getMerCapital());
					}
					sumBypayProfit = sumBypayProfit + bypayProfit;
					sumMerProfit = sumMerProfit + merProfit;
					sumAgent1Profit = sumAgent1Profit + agent1Profit;
					sumAgent2Profit = sumAgent2Profit + agent2Profit;

					list.get(i).setMerAmt(df.format((double) merAmt / 100));
					list.get(i).setMerCapital(
							df.format((double) merCapital / 100));
					list.get(i).setBypayProfit(
							df.format((double) bypayProfit / 100));
					list.get(i).setMerProfit(
							df.format((double) merProfit / 100));
					list.get(i).setAgent1Profit(
							df.format((double) agent1Profit / 100));
					list.get(i).setAgent2Profit(
							df.format((double) agent2Profit / 100));
				}

			}

			HttpServletResponse response = getResponse();
			response.reset();// 清除缓存
			// 设置下载类型
			response.setContentType("application/x-download");
			String sheetName = "";
			if (null != data && !"".equals(data)) {
				sheetName = merName + data.substring(5, 6) + "月";// 文件名
			}

			Map<String, String> mapfile = new LinkedHashMap<String, String>();
			mapfile.put("createTime", "日期"); // 对应实体字段
			mapfile.put("merAmt", "交易金额");
			mapfile.put("merCapital", "子商户交易本金");
			mapfile.put("bypayProfit", "平台分润");
			mapfile.put("merProfit", "机构分润");
			mapfile.put("agent1Profit", "一级代理分润");
			mapfile.put("agent2Profit", "二级代理分润");

			// 合计
			Map mapAmountTo = new LinkedHashMap();
			mapAmountTo
					.put("平台分润合计:", df.format((double) sumBypayProfit / 100));
			mapAmountTo.put("机构分润合计:", df.format((double) sumMerProfit / 100));
			mapAmountTo.put("一级代理分润合计:", df
					.format((double) sumAgent1Profit / 100));
			mapAmountTo.put("二级代理分润合计:", df
					.format((double) sumAgent2Profit / 100));

			try {
				response.setHeader("Content-Disposition",
						"attachment;filename="
								+ URLEncoder.encode(sheetName, "UTF-8")
								+ ".xls");
				ImportExcelFile.ImportExcel(list, response.getOutputStream(),
						mapfile, data.substring(0, 4) + "年" + sheetName
								+ "份机构商利润清分", mapAmountTo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 代理商利润统计
	 * 
	 * @return
	 */
	public String agentProfitDetails() {
		// 此处startTime代表年份 endTime代表月份
		if (!"".equals(agentId) && null != agentId && !"".equals(startTime)
				&& null != startTime && !"".equals(endTime) && null != endTime) {
			int months = Integer.parseInt(endTime);
			if (months < 10) {
				endTime = "0" + endTime;
			}
			String yearMonth = startTime.trim() + endTime.trim();// 年+月 20140722
			Map mapParameter = new HashMap();
			mapParameter.put("yearMonth", yearMonth);
			mapParameter.put("agentId", agentId);
			List<StatisticsInfo> list = statisticsService
					.agentProfitDayStatistics(mapParameter);

			String dayProfit = "";// 代理商利润
			String dayFee = "";// 交易手续费
			String dayDate = "";// 日期
			DecimalFormat df = new DecimalFormat("#0.00");
			if (null != list && list.size() > 0) {
				Map maps = new HashMap();
				for (int i = 0; i < list.size(); i++) {
					StatisticsInfo info = list.get(i);
					long profit = 0;
					long fee = 0;
					String date = "";
					if (null != info && null != info.getAgentProfit()
							&& !"".equals(info.getAgentProfit())) {
						profit = Long.parseLong(info.getAgentProfit());
					}
					if (null != info && !"".equals(info.getTransFee())
							&& null != info.getTransFee()) {
						fee = Long.parseLong(info.getTransFee());
					}

					if (null != info && !"".equals(info.getCreateTime())
							&& null != info.getCreateTime()) {
						date = info.getCreateTime();
					}
					dayProfit += df.format((double) profit / 100) + ",";
					dayFee += df.format((double) fee / 100) + ",";
					if (date.length() >= 8) {
						date = date.substring(6, 8);
					}
					dayDate += "'" + date + "日'" + ",";
				}
				if (!"".equals(dayProfit))
					dayProfit = dayProfit.substring(0, dayProfit.length() - 1);
				if (!"".equals(dayFee))
					dayFee = dayFee.substring(0, dayFee.length() - 1);
				if (!"".equals(dayDate))
					dayDate = dayDate.substring(0, dayDate.length() - 1);
				maps.put("利润", dayProfit);
				maps.put("手续费", dayFee);
				axisJson = "{categories:[" + dayDate + "]}";
				System.out.println("xAxisJson:" + axisJson);
				seriesJson = getHighChartsColumnData(maps);
			}
		}
		return "toAgentProfitDetails";
	}

	/**
	 * 代理商利润数据
	 * 
	 * @return
	 */
	public void getAgentProfitStatistics() {
		// 此处startTime代表年份 endTime代表月份
		if (!"".equals(merSysId) && null != merSysId) {
			int months = Integer.parseInt(endTime);
			if (months < 10) {
				endTime = "0" + endTime;
			}
			String yearMonth = startTime.trim() + endTime.trim();// 年+月 20140722
			Map mapParameter = new HashMap();
			mapParameter.put("yearMonth", yearMonth);
			mapParameter.put("merSysId", merSysId);
			Integer result = statisticsService
					.getAgentProfitStatisticsCount(mapParameter);
			int count = 0; // 总数据条数
			if (null != result) {
				count = result;
			}
			Map map = PageUtil.getPageMap(page, pageSize);
			map.putAll(mapParameter);
			List<StatisticsInfo> statisticsInfos = statisticsService
					.getAgentProfitStatistics(map);
			if (null != statisticsInfos) {
				for (int i = 0; i < statisticsInfos.size(); i++) {
					StatisticsInfo info = statisticsInfos.get(i);
					if ("1".equals(info.getLevel())) {
						statisticsInfos.get(i).setAgentProfit(
								info.getAgent1Profit());
					}
					if ("2".equals(info.getLevel())) {
						statisticsInfos.get(i).setAgentProfit(
								info.getAgent2Profit());
					}
				}
			}
			JSONArray array = JSONArray.fromObject(statisticsInfos);
			JSONObject object = new JSONObject();
			object.put("Rows", array.toString());
			object.put("Total", count);
			System.out.println(object.toString());
			try {
				getResponse().getWriter().write(object.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 借贷记统计
	 * 
	 * @return
	 */
	public String getCardTypeStatistics() {
		if (!"".equals(startTime) && null != startTime && !"".equals(endTime)
				&& null != endTime) {
			Map mapParameter = new HashMap();
			mapParameter.put("startTime", startTime + " 00:00:00");
			mapParameter.put("endTime", endTime + " 23:59:59");
			StatisticsInfo statisticsInfo = statisticsService
					.getOrderInfoByCardType(mapParameter);
			if (null != statisticsInfo) {
				int debitCardNum = statisticsInfo.getDebitCard();
				int creditCardNum = statisticsInfo.getCreditCard();
				Map maps = new HashMap();
				maps.put("借记卡(" + debitCardNum + "条)", debitCardNum);
				maps.put("信用卡(" + creditCardNum + "条)", creditCardNum);
				seriesJson = getHighChartsPieData("pie", "借贷记卡占有率", maps);
			}
		}
		return "toCardTypeStatistics";
	}

	/**
	 * 错误码统计
	 * 
	 * @return
	 */
	public String getErrRespCodeStatistics() {
		if (!"".equals(startTime) && null != startTime && !"".equals(endTime)
				&& null != endTime) {
			Map mapParameter = new HashMap();
			mapParameter.put("merSysId", merSysId);
			mapParameter.put("transType", transType);
			mapParameter.put("startTime", startTime + " 00:00:00");
			mapParameter.put("endTime", endTime + " 23:59:59");
			List<StatisticsInfo> list = statisticsService
					.getErrRespCodeStatistics(mapParameter);
			if (null != list && list.size() > 0) {
				Map maps = new HashMap();
				for (int i = 0; i < list.size(); i++) {
					StatisticsInfo info = list.get(i);
					maps.put(info.getRespDesc() + "[" + info.getRespCode()
							+ "]" + "(" + info.getCountNum() + "条)", info
							.getCountNum());
				}
				seriesJson = getHighChartsPieData("pie", "错误码占有率", maps);
			}
		}
		return "toErrRespCodeStatistics";
	}

	/**
	 * 组装扇形数据格式
	 * 
	 * @param map
	 * @return
	 */
	public String getHighChartsPieData(String type, String name, Map map) {
		Set set = map.entrySet();
		Iterator it = set.iterator();
		StringBuffer sb = new StringBuffer();
		int re = 0;
		while (it.hasNext()) {
			Map.Entry<String, Double> entry = (Entry<String, Double>) it.next();
			String str = "";
			if (re == map.size() - 1) {
				str += "{name:'" + entry.getKey() + "',y: " + entry.getValue()
						+ ",sliced: true,selected: true}";
			} else {
				str = "[" + "'" + entry.getKey() + "'" + "," + entry.getValue()
						+ "]";
			}
			re++;
			if (re < map.size()) {
				str += ",";
			}
			sb.append(str);
		}
		String strs = "[{type:'" + type + "',name:'" + name + "',data:" + "["
				+ sb + "]" + "}]";
		System.out.println(strs);
		return strs;
	}

	/**
	 * 组装柱状图数据格式
	 * 
	 * @param map
	 * @return
	 */
	public String getHighChartsColumnData(Map map) {
		Set set = map.entrySet();
		Iterator it = set.iterator();
		StringBuffer sb = new StringBuffer();
		int re = 0;
		while (it.hasNext()) {
			Map.Entry<String, Double> entry = (Entry<String, Double>) it.next();
			String data = "{name:'" + entry.getKey() + "',data:["
					+ entry.getValue() + "]}";
			re++;
			if (re < map.size()) {
				data += ",";
			}
			sb.append(data);
		}
		String strs = "[" + sb + "]";
		System.out.println(strs);
		return strs;
	}

	/**
	 * 日交易金额、分润统计
	 * 
	 * @return
	 */
	public String dayMerAmtProfit() {
		if (!"".equals(startTime) && null != startTime && !"".equals(endTime)
				&& null != endTime) {
			Map mapParameter = new HashMap();
			mapParameter.put("mid", mid);
			mapParameter.put("merType", merType);
			mapParameter.put("startTime", startTime.replace("-", ""));
			mapParameter.put("endTime", endTime.replace("-", ""));
			String daySumTransAmt = "";// 金额
			String dayDate = "";// 日期
			String daySumProfit = "";// 分润
			long sumAmt = 0;// 总交易金额
			double sumProfit = 0.0;// 总交易分润
			DecimalFormat df = new DecimalFormat("#0.00");
			DecimalFormat dfs = new DecimalFormat("#0.0000");
			List<MerProfitStatictis> list = merProfitStatictisService
					.getSucTransAmtFeeStatistics(mapParameter);
			if (null != list && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					MerProfitStatictis info = list.get(i);
					long amt = 0;
					double profit = 0;
					String date = "";
					if (null != info && !"".equals(info.getSumAmt())
							&& null != info.getSumAmt()) {
						amt = Long.parseLong(info.getSumAmt());
					}
					if (null != info && !"".equals(info.getProfit())
							&& null != info.getProfit()) {
						profit = Double.parseDouble(info.getProfit());
					}
					if (null != info && !"".equals(info.getSettleDate())
							&& null != info.getSettleDate()) {
						date = info.getSettleDate();
					}
					sumAmt = sumAmt + amt; // 总金额
					sumProfit = sumProfit + profit;// 总交易分润
					daySumTransAmt += df.format((double) amt / 100) + ",";
					daySumProfit += dfs.format(profit / 100) + ",";
					dayDate += "'" + date + "'" + ",";
				}
			}
			Map maps = new HashMap();
			sumInfo = "总交易金额：" + df.format((double) sumAmt / 100) + "元"
					+ "总交易分润：" + dfs.format(sumProfit / 100) + "分";
			if (!"".equals(daySumTransAmt))
				daySumTransAmt = daySumTransAmt.substring(0, daySumTransAmt
						.length() - 1);
			if (!"".equals(daySumProfit))
				daySumProfit = daySumProfit.substring(0,
						daySumProfit.length() - 1);
			if (!"".equals(dayDate))
				dayDate = dayDate.substring(0, dayDate.length() - 1);
			maps.put("金额(元)", daySumTransAmt);
			maps.put("分润(分)", daySumProfit);
			axisJson = "{categories:[" + dayDate + "]}";
			System.out.println("xAxisJson:" + axisJson);
			seriesJson = getHighChartsColumnData(maps);
		}

		return "dayMerAmtProfit";
	}

	/**
	 * 日交易金额、手续费统计
	 * 
	 * @return
	 */
	public String dayMerAmtFee() {
		String daySumTransAmt = "";// 金额
		String dayDate = "";// 日期
		String daySumFee = "";// 手续费
		long sumAmt = 0;// 总交易金额
		long sumFee = 0;// 总交易手续费

		if (!"".equals(startTime) && null != startTime && !"".equals(endTime)
				&& null != endTime) {
			Map mapParameter = new HashMap();
			mapParameter.put("mid", mid);
			if ("-1".equals(merType)) {
				mapParameter.put("merType", "0");
			} else {
				mapParameter.put("merType", merType);
			}
			mapParameter.put("startTime", startTime.replace("-", ""));
			mapParameter.put("endTime", endTime.replace("-", ""));

			DecimalFormat df = new DecimalFormat("#0.00");

			List<MerSettleStatictis> list = merSettleStatictisDao
					.dayMerAmtFee(mapParameter);

			if (null != list && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					MerSettleStatictis info = list.get(i);
					long amt = 0;
					long fee = 0;
					String date = "";
					if (null != info && !"".equals(info.getSumAmt())
							&& null != info.getSumAmt()) {
						amt = Long.parseLong(info.getSumAmt());
					}
					if (null != info && !"".equals(info.getSumTransFee())
							&& null != info.getSumTransFee()) {
						fee = Long.parseLong(info.getSumTransFee());
					}
					if (null != info && !"".equals(info.getSettleDate())
							&& null != info.getSettleDate()) {
						date = info.getSettleDate();
					}
					sumAmt = sumAmt + amt; // 总金额
					sumFee = sumFee + fee;// 总交易手续费
					daySumTransAmt += df.format((double) amt / 100) + ",";
					daySumFee += df.format((double) fee / 100) + ",";
					dayDate += "'" + date + "'" + ",";
				}
			}
			Map maps = new HashMap();
			sumInfo = "总交易金额：" + df.format((double) sumAmt / 100) + "元 "
					+ "总交易手续费：" + df.format((double) sumFee / 100) + "元";
			if (!"".equals(daySumTransAmt))
				daySumTransAmt = daySumTransAmt.substring(0, daySumTransAmt
						.length() - 1);
			if (!"".equals(daySumFee))
				daySumFee = daySumFee.substring(0, daySumFee.length() - 1);
			if (!"".equals(dayDate))
				dayDate = dayDate.substring(0, dayDate.length() - 1);
			maps.put("金额(元)", daySumTransAmt);
			maps.put("手续费(元)", daySumFee);
			axisJson = "{categories:[" + dayDate + "]}";
			System.out.println("xAxisJson:" + axisJson);
			seriesJson = getHighChartsColumnData(maps);
		}
		return "dayMerAmtFee";
	}

	// *********************/******************************************

	/**
	 * 按月统计机构分润 YJH
	 * 
	 * @return
	 */
	public String getMerSucTransAmtFeeStatistics() {
		startMon = getRequest().getParameter("startMon");
		getRequest().setAttribute("startMons", startMon); // 往页面上传值在页面上接受
		String startTimes = startTime + startMon;

		DecimalFormat df = new DecimalFormat("#0.00");

		if (!"".equals(startTime) && startTime != null && null != startMon
				&& !"".equals(startMon) && null != merSysId
				&& !"".equals(merSysId)) {
			Map mapParameter = new HashMap();
			mapParameter.put("merSysId", merSysId);
			mapParameter.put("createTime", startTimes);
			List<StatisticsInfo> list = statisticsService
					.getMerFeeStatistics(mapParameter);
			String profits = "";// 金额
			String fees = "";// 手续费
			String dates = "";// 日期
			if (null != list && list.size() > 0) {
				Map maps = new HashMap();
				for (int i = 0; i < list.size(); i++) {
					StatisticsInfo info = list.get(i);
					long profit = 0;
					long fee = 0;
					String date = "";
					if (null != info && null != info.getMerProfit()
							&& !"".equals(info.getMerProfit())) {
						profit = Long.parseLong(info.getMerProfit());
					}
					if (null != info && null != info.getTransFee()
							&& !"".equals(info.getTransFee())) {
						fee = Long.parseLong(info.getTransFee());
					}
					if (null != info && null != info.getCreateTime()
							&& !"".equals(info.getCreateTime())) {
						date = info.getCreateTime();
					}
					profits += df.format((double) profit / 100) + ",";
					fees += df.format((double) fee / 100) + ",";
					if (date.length() >= 8) {
						date = date.substring(6, 8);
						dates += "'" + date + "日" + "'" + ",";
					}
				}
				if (!"".equals(profits))
					profits = profits.substring(0, profits.length() - 1);
				if (!"".equals(fees))
					fees = fees.substring(0, fees.length() - 1);
				if (!"".equals(dates))
					dates = dates.substring(0, dates.length() - 1);
				maps.put("利润", profits);
				maps.put("手续费", fees);
				axisJson = "{categories:[" + dates + "]}";
				System.out.println("xAxisJson:" + axisJson);
				seriesJson = getHighChartsColumnData(maps);
			}
		}
		return "toMerSucTransAmtFeeStatistics";
	}

	/**
	 * 按月统计机构分润 YJH 报表下载
	 */
	public void download_excel_new() {
		startMon = getRequest().getParameter("startMon");
		String startTimes = startTime + startMon;
		if (!"".equals(merSysId) && null != merSysId && !"".equals(startTime)
				&& null != startTime && null != startMon
				&& !"".equals(startMon)) {
			Map mapParameter = new HashMap();
			mapParameter.put("merSysId", merSysId);
			mapParameter.put("createTime", startTimes);
			List<StatisticsInfo> list = statisticsService
					.getMerFeeStatistics(mapParameter);
			String merName = "";
			String data = "";
			DecimalFormat df = new DecimalFormat("#0.00");

			long sumBypayProfit = 0;
			long sumMerProfit = 0;
			long sumAgent1Profit = 0;
			long sumAgent2Profit = 0;

			long bypayProfit = 0;
			long merProfit = 0;
			long agent1Profit = 0;
			long agent2Profit = 0;
			long merAmt = 0;
			long merCapital = 0;
			if (null != list && list.size() > 0) {
				merName = list.get(0).getMerName();
				data = list.get(0).getCreateTime();
				for (int i = 0; i < list.size(); i++) {
					StatisticsInfo info = list.get(i);
					if (null != info && null != info.getBypayProfit()
							&& !"".equals(info.getBypayProfit())) {
						bypayProfit = Long.parseLong(info.getBypayProfit());
					}
					if (null != info && null != info.getMerProfit()
							&& !"".equals(info.getMerProfit())) {
						merProfit = Long.parseLong(info.getMerProfit());
					}
					if (null != info && null != info.getAgent1Profit()
							&& !"".equals(info.getAgent1Profit())) {
						agent1Profit = Long.parseLong(info.getAgent1Profit());
					}
					if (null != info && null != info.getAgent2Profit()
							&& !"".equals(info.getAgent2Profit())) {
						agent2Profit = Long.parseLong(info.getAgent2Profit());
					}
					if (null != info && null != info.getMerAmt()
							&& !"".equals(info.getMerAmt())) {
						merAmt = Long.parseLong(info.getMerAmt());
					}
					if (null != info && null != info.getMerCapital()
							&& !"".equals(info.getMerCapital())) {
						merCapital = Long.parseLong(info.getMerCapital());
					}
					sumBypayProfit = sumBypayProfit + bypayProfit;
					sumMerProfit = sumMerProfit + merProfit;
					sumAgent1Profit = sumAgent1Profit + agent1Profit;
					sumAgent2Profit = sumAgent2Profit + agent2Profit;
					list.get(i).setMerAmt(df.format((double) merAmt / 100));
					list.get(i).setMerCapital(
							df.format((double) merCapital / 100));
					list.get(i).setBypayProfit(
							df.format((double) bypayProfit / 100));
					list.get(i).setMerProfit(
							df.format((double) merProfit / 100));
					list.get(i).setAgent1Profit(
							df.format((double) agent1Profit / 100));
					list.get(i).setAgent2Profit(
							df.format((double) agent2Profit / 100));

				}
			}
			HttpServletResponse response = getResponse();
			response.reset();// 清除缓存
			// 设置下载类型
			response.setContentType("application/x-download");
			String sheetName = merName + data.substring(5, 6) + "月";// 文件名
			Map<String, String> mapfile = new LinkedHashMap<String, String>();
			mapfile.put("createTime", "日期"); // 对应实体字段
			mapfile.put("merAmt", "交易金额");
			mapfile.put("merCapital", "子商户交易本金");
			mapfile.put("bypayProfit", "平台分润");
			mapfile.put("merProfit", "机构分润");
			mapfile.put("agent1Profit", "一级代理分润");
			mapfile.put("agent2Profit", "二级代理分润");

			// 合计
			Map mapAmountTo = new LinkedHashMap();
			mapAmountTo
					.put("平台分润合计:", df.format((double) sumBypayProfit / 100));
			mapAmountTo.put("机构分润合计:", df.format((double) sumMerProfit / 100));
			mapAmountTo.put("一级代理分润合计:", df
					.format((double) sumAgent1Profit / 100));
			mapAmountTo.put("二级代理分润合计:", df
					.format((double) sumAgent2Profit / 100));

			try {
				response.setHeader("Content-Disposition",
						"attachment;filename="
								+ URLEncoder.encode(sheetName, "UTF-8")
								+ ".xls");
				ImportExcelFile.ImportExcel(list, response.getOutputStream(),
						mapfile, data.substring(0, 4) + "年" + sheetName
								+ "份机构商利润清分", mapAmountTo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	* @Title: getCountMerPusineTer 
	* @Description: 用于统计根据机构商号统计下属终端及统计下属已登记子商户数 syw 2015.08.06
	* @param  
	* @return void    返回类型 
	* @throws
	 */
	public void getCountMerPusineTer(){
		logger.info("StatisticsAction.getCountMerPusineTer()...begin");
		String merSysId = this.merSysId;
		Map<String,Object> map = PageUtil.getPageMap(page, pageSize);
		map.put("merSysId", merSysId);
		List<SubMerTerminal> terminals =  subMerTerminalDao.queryCountMerPusineTer(map);
		if(terminals != null && terminals.size()>0){
			JSONArray array = JSONArray.fromObject(terminals);
			JSONObject object = new JSONObject();
			object.put("Rows", array.toString());
			logger.info("result:"+object.toString());
			try {
				getResponse().getWriter().write(object.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
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

	public String getSeriesJson() {
		return seriesJson;
	}

	public void setSeriesJson(String seriesJson) {
		this.seriesJson = seriesJson;
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

	public String getMerSysId() {
		return merSysId;
	}

	public void setMerSysId(String merSysId) {
		this.merSysId = merSysId;
	}

	public String getAxisJson() {
		return axisJson;
	}

	public void setAxisJson(String axisJson) {
		this.axisJson = axisJson;
	}

	public String getSumInfo() {
		return sumInfo;
	}

	public void setSumInfo(String sumInfo) {
		this.sumInfo = sumInfo;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMerType() {
		return merType;
	}

	public void setMerType(String merType) {
		this.merType = merType;
	}

	public String getStartMon() {
		return startMon;
	}

	public void setStartMon(String startMon) {
		this.startMon = startMon;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

    public SumMerProfit getSumMerProfit() {
        return sumMerProfit;
    }

    public void setSumMerProfit(SumMerProfit sumMerProfit) {
        this.sumMerProfit = sumMerProfit;
    }

    public MerProfitApply getMerProfitApply() {
        return merProfitApply;
    }

    public void setMerProfitApply(MerProfitApply merProfitApply) {
        this.merProfitApply = merProfitApply;
    }
	
	
}

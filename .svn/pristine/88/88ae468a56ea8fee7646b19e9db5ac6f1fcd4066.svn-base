package com.bypay.action;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import com.bypay.common.BaseAction;
import com.bypay.domain.MerProfitStatictis;
import com.bypay.service.MerProfitStatictisService;
import com.bypay.util.ImportExcelFile;

public class MerProfitStatictisAction extends BaseAction{

	@Inject
	private MerProfitStatictisService merProfitStatictisService;
	
	private String seriesJson;//JSONS数据
	private String axisJson;//横坐标JSON数据
	private String totalInfo;//显示 总合数据
	
	private MerProfitStatictis merProfitStatictis;
	private List<MerProfitStatictis> merProfitStatictisList;
	
	//---------页面参数-----------
	private String year;
	private String month;
	
	/**
	 *机构日分润统计下载 
	 */
	public void download_mer_excel() {
		//总合计
		long sumProfits = 0;//总分润
		long sumAmts = 0;//总交易金额
		String merName = "";
		DecimalFormat df = new DecimalFormat("#0.00");
		HttpServletResponse response = getResponse();
		if(null != merProfitStatictis && null != merProfitStatictis.getMid()
				&& !"".equals(merProfitStatictis.getMid())) {
			String mid = merProfitStatictis.getMid();
			String merType = merProfitStatictis.getMerType();
			int months = Integer.parseInt(month);
			String monthStr = ""; 
			if(months < 10) {
				monthStr = "0"+ months;
			}
			String yearMonth = year + monthStr;//年+月 201407
			//参数
			MerProfitStatictis merProfitStatictis = new MerProfitStatictis();
			merProfitStatictis.setMid(mid);
			merProfitStatictis.setMerType(merType);
			merProfitStatictis.setSettleDate(yearMonth);
			//查询结果
			merProfitStatictisList = merProfitStatictisService.dayProfitStatistics(merProfitStatictis);
			if (null != merProfitStatictisList&& merProfitStatictisList.size() > 2000) {
				try {
					response.setHeader("Content-type", "text/html;charset=UTF-8");
					response.getWriter().write("<script>alert('弹出，超2000');window.close()</script>");
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
			if(null != merProfitStatictisList && merProfitStatictisList.size() > 0) {
				for(int i = 0; i < merProfitStatictisList.size(); i++) {
					MerProfitStatictis info = merProfitStatictisList.get(i);
					long profit = 0;
					long sumAmt = 0;
					if(null != info) {
						merName = info.getMerName();
						if(null != info.getProfit() && !"".equals(info.getProfit())) {
							profit = Long.parseLong(info.getProfit());
						}
						if(null != info.getSumAmt() && !"".equals(info.getSumAmt())) {
							sumAmt = Long.parseLong(info.getSumAmt());
						}
					}
					sumProfits = sumProfits + profit;
					sumAmts = sumAmts + sumAmt;
					merProfitStatictisList.get(i).setProfit(df.format((double)profit/100));
					merProfitStatictisList.get(i).setSumAmt(df.format((double)sumAmt/100));
				}
				
			}
		}
		
		response.reset();//清除缓存
		//设置下载类型
		response.setContentType("application/x-download");
		String sheetName = "日分润统计";
		if(null != merName && !"".equals(merName)) {
			sheetName = merName + sheetName;//文件名
		}
		
		Map<String, String> mapfile = new LinkedHashMap<String, String>();
		mapfile.put("settleDate", "清算日期"); //对应实体字段
		mapfile.put("mid", "机构号");
		mapfile.put("merName", "机构名称");
		mapfile.put("sumAmt", "交易额");
		mapfile.put("profit", "商户分润");
		//合计
		Map<String, String> mapAmountTo = new LinkedHashMap<String, String>();
		mapAmountTo.put("总交易金额：", df.format((double)sumAmts/100));
		mapAmountTo.put("总分润：", df.format((double)sumProfits/100));
		
		try {
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(sheetName, "UTF-8") + ".xls");
			ImportExcelFile.ImportExcel(merProfitStatictisList, response.getOutputStream(), mapfile, sheetName, mapAmountTo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 商户日分润统计
	 * @return
	 */
	public String merDayProfitStatistics() {
		if(null != merProfitStatictis && null != merProfitStatictis.getMid()
				&& !"".equals(merProfitStatictis.getMid())) {
			String mid = merProfitStatictis.getMid();
			String merType = merProfitStatictis.getMerType();
			int months = Integer.parseInt(month);
			String monthStr = ""; 
			if(months < 10) {
				monthStr = "0"+ months;
			}
			String yearMonth = year + monthStr;//年+月 201407
			//参数
			MerProfitStatictis merProfitStatictis = new MerProfitStatictis();
			merProfitStatictis.setMid(mid);
			merProfitStatictis.setMerType(merType);
			merProfitStatictis.setSettleDate(yearMonth);
			//查询结果
			merProfitStatictisList = merProfitStatictisService.dayProfitStatistics(merProfitStatictis);
			DecimalFormat df = new DecimalFormat("#0.0000");
			String dayProfit = "";//商户日利润
			String dayAmt = "";//商户日总金额
			String dayDate = "";//日期
			if(null != merProfitStatictisList && merProfitStatictisList.size() > 0) {
				double sumProfits = 0;//总分润
				double sumAmts = 0;//总交易金额
				for(int i = 0; i < merProfitStatictisList.size(); i++) {
					MerProfitStatictis info = merProfitStatictisList.get(i);
					String settleDate = "";
					double profit = 0;
					double sumAmt = 0;
					if(null != info) {
						settleDate = info.getSettleDate();
						if(null != info.getProfit() && !"".equals(info.getProfit())) {
							profit = Double.parseDouble(info.getProfit());
						}
						if(null != info.getSumAmt() && !"".equals(info.getSumAmt())) {
							sumAmt = Double.parseDouble(info.getSumAmt());
						}
					}
					sumProfits = sumProfits + profit;
					sumAmts = sumAmts + sumAmt;
					settleDate = settleDate.substring(6, settleDate.length());
					dayDate += "'"+settleDate+"日'" + ",";
					dayProfit += df.format((double)profit/100) + ",";
					dayAmt += df.format((double)sumAmt/100) + ",";
				}
				
				totalInfo = "总交易金额："+df.format((double)sumAmts/100)+"元,  总分润："+df.format((double)sumProfits/100)+"元";
			}
			if(!"".equals(dayProfit)) dayProfit = dayProfit.substring(0,dayProfit.length()-1);
			if(!"".equals(dayDate)) dayDate = dayDate.substring(0,dayDate.length()-1);
			
			Map<String, String> maps = new HashMap<String, String>();
			maps.put("分润", dayProfit);
			axisJson = "{categories:["+dayDate+"]}";
			System.out.println("xAxisJson:" + axisJson);
			seriesJson = getHighChartsColumnData(maps);
			
		}
		return "merDayProfitStatistics";
	}
	
	/**
	 * 组装柱状图数据格式
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
			String data = "{name:'"+entry.getKey()+"',data:["+entry.getValue()+"]}";
			re++;
			if (re < map.size()) {
				data += ",";
			}
			sb.append(data);
		}
		String strs = "["+sb+"]";
		System.out.println(strs);
		return strs;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSeriesJson() {
		return seriesJson;
	}

	public void setSeriesJson(String seriesJson) {
		this.seriesJson = seriesJson;
	}

	public String getAxisJson() {
		return axisJson;
	}

	public void setAxisJson(String axisJson) {
		this.axisJson = axisJson;
	}

	public String getTotalInfo() {
		return totalInfo;
	}

	public void setTotalInfo(String totalInfo) {
		this.totalInfo = totalInfo;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public MerProfitStatictis getMerProfitStatictis() {
		return merProfitStatictis;
	}

	public void setMerProfitStatictis(MerProfitStatictis merProfitStatictis) {
		this.merProfitStatictis = merProfitStatictis;
	}
	
}

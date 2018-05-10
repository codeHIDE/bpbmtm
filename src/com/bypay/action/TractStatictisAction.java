package com.bypay.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.ryms.model.CoreTransInfoDubbo;
import org.ryms.remote.JPayRemoteService;
import org.springframework.beans.factory.annotation.Autowired;

import com.bypay.common.BaseAction;
import com.bypay.domain.TractInfo;
import com.bypay.domain.TractStatictis;
import com.bypay.service.TractInfoService;
import com.bypay.service.TractStatictisService;
import com.bypay.util.DateUtil;
import com.bypay.util.PageUtil;

public class TractStatictisAction extends BaseAction{
	
	@Inject
	private TractStatictisService tractStatictisService;
	private TractStatictis tractStatictis;
	private List<TractStatictis> tractStatictisList;
	private String tractList;
	// ========分页
	private int page = 1;
	private int pageSize = 15;
	
	@Autowired
	private JPayRemoteService jPayRemoteService;
	
	private String tractName;
	public TractInfo getTractInfo() {
		return tractInfo;
	}


	public void setTractInfo(TractInfo tractInfo) {
		this.tractInfo = tractInfo;
	}
	private String ratesType;
	private String statictisDate;
	private String settleDate2;
	private String payTract;
	private TractInfo tractInfo;
	@Inject
	private TractInfoService tractInfoService;


	public String getSettDate(){
		Calendar c = Calendar.getInstance(); 
		c.add(Calendar.DAY_OF_MONTH, -1); 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		settleDate2=formatter.format(c.getTime()); 
		getRequest().setAttribute("settleDate2", settleDate2);
		//以上是获取前一天时间 格式是 20131126
		return "getSettDate";
	}


	// 通道列表
	public void selectTractStatictisList() {
	  if (tractStatictis == null) {
        tractStatictis = new TractStatictis();
        tractStatictis.setTractId(tractId);
        try {
            tractStatictis.setTractName(new String(tractName .getBytes("ISO8859-1"), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        tractStatictis.setRatesType("-1".equals(ratesType) ? null : ratesType);
        tractStatictis.setStatictisDate(settleDate2.replaceAll("-",""));
        tractStatictis.setPayTract(payTract);
//      getRequest().setAttribute("settleDate2", settleDate2);
      }
      Map<String,Object> totalMap = tractStatictisService.selectTractStatictisCount(tractStatictis);  
      int count = Integer.parseInt(totalMap.get("count").toString());
      
      Double payAmtCount = Double.parseDouble(totalMap.get("payAmtCount").toString());
      Double refundAmt = Double.parseDouble(totalMap.get("refundAmt").toString());
      Double useAmt = Double.parseDouble(totalMap.get("useAmt").toString());
      Double reserved1 = Double.parseDouble(totalMap.get("reserved1").toString());
      Double amt = useAmt - refundAmt - reserved1;
      Map map=PageUtil.getPageMap(page, pageSize);
      map.put("tractStatictis", tractStatictis);
      tractStatictisList = tractStatictisService.selectTractStatictisList(map);
      JSONArray array = JSONArray.fromObject(tractStatictisList);
      JSONObject object = new JSONObject();
      object.put("Rows", array.toString());
      object.put("Total", count);
      DecimalFormat df = new DecimalFormat("#0.00");
      object.put("payAmtCount", df.format(payAmtCount/100));
      object.put("refundAmt", df.format(refundAmt/100));
      object.put("useAmt", df.format(useAmt/100));
      object.put("reserved1", df.format(reserved1/100));
      object.put("amt", df.format(amt/100));
      tractList = object.toString();
      System.out.println(tractList);
      try {
          getResponse().getWriter().write(tractList);
      } catch (IOException e) {
          e.printStackTrace();
      }
	}
	
	
	//统计添加
	public void addTractStatictis(){
		if(tractStatictis==null){
			tractStatictis=new TractStatictis();
		}
		tractInfo=new TractInfo();
		tractInfo = tractInfoService.selectTractById(tractId);
		tractStatictis.setTractId(tractId);
		tractStatictis.setStatictisDate(DateUtil.getDate("yyyyMMdd"));
		tractStatictis=tractStatictisService.getTractStatictis(tractStatictis);
		    if(tractStatictis==null){
		    	tractStatictis=new TractStatictis();
		    	try {
		    		System.out.println(tractStatictis);
		    		tractStatictis.setTractId(tractId);
		    		tractStatictis.setStatictisDate(DateUtil.getDate("yyyyMMdd"));
						if(tractInfo.getTractQuota()==null||tractInfo.getTractQuota()==""){
							tractStatictis.setPayAmt("0");
						}else{
							tractStatictis.setPayAmt(tractInfo.getTractQuota());
							System.out.println(tractInfo.getTractQuota());
							System.out.println(tractStatictis.getPayAmt());
						}
						tractStatictis.setUseAmt("0");
						tractStatictis.setRefundAmt("0");
						int insertTractStatictis=tractStatictisService.insertTractStatictisCount(tractStatictis);
						if(insertTractStatictis>0){
							System.out.println("插入跑批彪成功！");
							getResponse().getWriter().write("succ");
						} 
					} catch (IOException e) {
						e.printStackTrace();
					}
			}else{
			     try {
			    	 System.out.println("已有记录不能插入");
					getResponse().getWriter().write("fones");
				} catch (IOException e) {
					e.printStackTrace();
				}
			  }
	}

	/**
	 * 平台签到
	 * @Title:        tractQiandao 
	 * @Description:  
	 * @param:            
	 * @return:       void    
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2016-7-7 上午9:48:07
	 */
	public void tractQiandao(){
		if(tractStatictis==null){
			tractStatictis=new TractStatictis();
		}
		tractInfo=new TractInfo();
		tractInfo = tractInfoService.selectTractById(tractId);
		CoreTransInfoDubbo  coreTransInfo = new CoreTransInfoDubbo();
//		coreTransInfo.setChMerId("001530000000001");
//		coreTransInfo.setChTermId("00000001");
		coreTransInfo.setChMerId(tractInfo.getTransMerId());
		coreTransInfo.setChTermId(tractInfo.getTerminalId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		coreTransInfo.setReferenceNo(sdf.format(new Date()));
		coreTransInfo = jPayRemoteService.doChannelSignIn(coreTransInfo);
		System.out.println("tractQiandao");
		System.out.println(JSONObject.fromObject(coreTransInfo).toString());
		 try {
			getResponse().getWriter().write("签到");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public TractStatictis getTractStatictis() {
		return tractStatictis;
	}
	public void setTractStatictis(TractStatictis tractStatictis) {
		this.tractStatictis = tractStatictis;
	}
	public List<TractStatictis> getTractStatictisList() {
		return tractStatictisList;
	}
	public void setTractStatictisList(List<TractStatictis> tractStatictisList) {
		this.tractStatictisList = tractStatictisList;
	}
	public String getTractList() {
		return tractList;
	}
	public void setTractList(String tractList) {
		this.tractList = tractList;
	}
	private String tractId;
	public String getSettleDate2() {
		return settleDate2;
	}
	public void setSettleDate2(String settleDate2) {
		this.settleDate2 = settleDate2;
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
	public String getTractId() {
		return tractId;
	}
	public void setTractId(String tractId) {
		this.tractId = tractId;
	}
	public String getTractName() {
		return tractName;
	}
	public String getStatictisDate() {
		return statictisDate;
	}
	public void setStatictisDate(String statictisDate) {
		this.statictisDate = statictisDate;
	}
	public void setTractName(String tractName) {
		this.tractName = tractName;
	}
	public String getRatesType() {
		return ratesType;
	}
	public void setRatesType(String ratesType) {
		this.ratesType = ratesType;
	}
	public String getPayTract() {
		return payTract;
	}
	public void setPayTract(String payTract) {
		this.payTract = payTract;
	}
}

package com.bypay.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.bypay.common.BaseAction;
import com.bypay.domain.AppTractInfo;
import com.bypay.service.AppTractInfoService;
import com.bypay.util.DateUtil;
import com.bypay.util.PageUtil;
import com.ibm.db2.jcc.a.a;

public class AppTractInfoAction extends BaseAction{
	@Inject
	private AppTractInfoService appTractInfoService;
	private AppTractInfo appTractInfo;
	private List<AppTractInfo> appTractInfoList;
	private String appTractList;
	private String busId;
	
	// ======界面传参数
	private String appTractId;
	private String appTractName;
	private String transMerId;
	private String payTract;
	private String status;
	
	// ========分页
	private int page = 1;
	private int pageSize = 15;
	
	private List sysy = new ArrayList();  
	
	public List getSysy() {
		return sysy;
	}

	public void setSysy(List sysy) {
		this.sysy = sysy;
	}
	
	
	// 点击添加通道时跳转到添加通道页面的Action
	public String goAddAppTract(){
		return "goAddAppTract";
	}
	
	// 添加通道
	public void addAppTractInfo() throws IOException {
		getResponse().setContentType("text/html;charset=UTF-8");
		if(null != appTractInfo) {
//			AppTractInfo info = appTractInfoService.selectAppTractInfoRepeat(appTractInfo.getTransMerId());
//			if(info != null) {
//				getResponse().getWriter().write("er");
//				System.out.println("已存在此支付商户号");
//				return;
//			}
			String tractType = appTractInfo.getTractType();
			tractType = tractType.replaceAll(",","|").replaceAll(" ", "");
			appTractInfo.setTractType(tractType);
			if(appTractInfo.getTerminalId()==null || "".equals(appTractInfo.getTerminalId()))
				appTractInfo.setTerminalId("00000000");
			appTractInfo.setCreateTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss")); // 通道创建日期
			appTractInfo.setStatus("0");
			
			//信息验证域
			String idCard = "0";//1代表选中
			String name = "0";//2代表选中
			String phone = "0";//3代表选中
			if(null != appTractInfo.getReserved() && !"".equals(appTractInfo.getReserved())) {
				String reserved = appTractInfo.getReserved();
				if(reserved.indexOf("1") >=0){
					idCard = "1";
				}
				if(reserved.indexOf("2") >=0){
					name = "1";
				}
				if(reserved.indexOf("3") >=0){
					phone = "1";
				}
			}
			appTractInfo.setReserved(idCard + name + phone);
			int addResult = appTractInfoService.insertAppTractInfo(appTractInfo);
			if (addResult > 0) {
				getResponse().getWriter().write("succ");
				System.out.println("添加通道成功");
			} else {
				getResponse().getWriter().write("err");
				System.out.println("添加通道失败");
			}
		}else {
			getResponse().getWriter().write("err");
			System.out.println("添加通道失败");
		}
	}

	
	// 查询判断添加通道时候的商户好是否存在
	public void selectAppTransMerId() {
		String flag = "false";
		appTractInfo = new AppTractInfo();
		appTractInfo.setTransMerId(transMerId);
		appTractInfo = appTractInfoService.selectAppTractInfoRepeat(transMerId);
		if (appTractInfo != null) {
			flag = "true";
		}
		try {
			getResponse().setContentType("text/plain");
			getResponse().getWriter().write(flag);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// 通道列表
	public void selectAppTractAllList() {
		if (appTractInfo == null) {
			appTractInfo = new AppTractInfo();
			appTractInfo.setAppTractId(appTractId);
			appTractInfo.setAppTractName(appTractName);
//			try {
//				appTractInfo.setAppTractName(new String(appTractName.getBytes("ISO8859-1"), "utf-8"));
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
			appTractInfo.setTransMerId(transMerId);
			appTractInfo.setPayTract("-1".equals(payTract) ? null : payTract);
			appTractInfo.setStatus("-1".equals(status) ? null : status);
		}
		int count = appTractInfoService.selectAppTractInfoCount(appTractInfo);
		Map map=PageUtil.getPageMap(page, pageSize);
		map.put("appTractInfo", appTractInfo);
		appTractInfoList = appTractInfoService.selectAppTractInfoList(map);
		
		JSONArray array = JSONArray.fromObject(appTractInfoList);
		JSONObject object = new JSONObject();
		object.put("Rows", array.toString());
		object.put("Total", count);
		appTractList = object.toString();
		System.out.println(appTractList);
		try {
			getResponse().getWriter().write(appTractList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// 启动通道
	public String approveAppTractInfo() throws Exception {
		AppTractInfo appTractInfo = new AppTractInfo();
		appTractInfo.setAppTractId(getRequest().getParameter("appTractId"));
		appTractInfo.setStatus("1");
		int result = appTractInfoService.updateAppTractInfo(appTractInfo);
		if (result > 0) {
			getResponse().getWriter().write("succ");
		} else {
			getResponse().getWriter().write("fone");
		}
		return null;
	}

	// 暂停通道
	public String approveAppTractInfoOFF() throws Exception {
		AppTractInfo appTractInfo = new AppTractInfo();
		appTractInfo.setAppTractId(getRequest().getParameter("appTractId"));
		appTractInfo.setStatus("2");
		int result = appTractInfoService.updateAppTractInfoOFF(appTractInfo);
		if (result > 0) {
			getResponse().getWriter().write("succOFF");
		} else {
			getResponse().getWriter().write("foneOFF");
		}
		return null;
	}

	// 通道审批 显示通道详情
	public String appTractDetail() {
		appTractId = getRequest().getParameter("appTractId");
		appTractInfo = appTractInfoService.selectAppTractById(appTractId);
		return "appTractDetail";
	}
	


	// 通道详情
	public String selectAppTractDetail() {
		appTractId = getRequest().getParameter("appTractId");
		appTractInfo = appTractInfoService.selectAppTractById(appTractId);
		//业务类别匹配转换
		if(appTractInfo.getTractType()!=null||!appTractInfo.getTractType().equals("")){
			busId=appTractInfo.getTractType();

			if(busId!=null&&busId.length()>0){
				String s = "01,02,03";
				String[] busIdArr = s.split("\\|");
				sysy = Arrays.asList(busIdArr);  
			}
		}
		return "selectAppTractDetail";
	}

	private List reservedList;
	
	public List getReservedList() {
		return reservedList;
	}

	public void setReservedList(List reservedList) {
		this.reservedList = reservedList;
	}

	// 查询通道信息，修改通道信息
	public String updateAppTractInfoDetail() {
		appTractId = getRequest().getParameter("appTractId");
		appTractInfo = appTractInfoService.selectAppTractById(appTractId);
		//通道类别转换
		if(appTractInfo.getTractType()!=null && !appTractInfo.getTractType().equals("")){
			busId=appTractInfo.getTractType();
			if(busId!=null&&busId.length()>0){
				String[] busIdArr = busId.split("\\|");
				sysy = Arrays.asList(busIdArr);  
			}
		}
		//信息验证域
		if(null != appTractInfo.getReserved() && !"".equals(appTractInfo.getReserved())) {
			String reserved = appTractInfo.getReserved();
			char[] res =  reserved.toCharArray();
			String result[] = new String[3];
			if(res[0] == '1'){
				result[0] = "1";
			}
			if(res[1] == '1'){
				result[1] = "2";
			}
			if(res[2] == '1'){
				result[2] = "3";
			}
			reservedList = Arrays.asList(result);
		}
		return "updateAppTractInfoDetail";
	}

	// 通道信息修改
	public String updateAppTractInfo() {  
		if (appTractInfo == null) {
			appTractInfo = new AppTractInfo();
			appTractInfo.setAppTractId(getRequest().getParameter("appTractId"));
		}
		String tractType = appTractInfo.getTractType();
		appTractInfo.setTractType(tractType.replaceAll(", ", "|"));
		
		//信息验证域
		String idCard = "0";//1代表选中
		String name = "0";//2代表选中
		String phone = "0";//3代表选中
		if(null != appTractInfo.getReserved() && !"".equals(appTractInfo.getReserved())) {
			String reserved = appTractInfo.getReserved();
			if(reserved.indexOf("1") >=0){
				idCard = "1";
			}
			if(reserved.indexOf("2") >=0){
				name = "1";
			}
			if(reserved.indexOf("3") >=0){
				phone = "1";
			}
		}
		appTractInfo.setReserved(idCard + name + phone);
		
		int result = appTractInfoService.updateAppTract(appTractInfo);
		if (result > 0) {
			System.out.println("修改成功");
			getRequest().setAttribute("updateAppTractInfo", "success");
			
		}
		appTractId = getRequest().getParameter("appTractId");
		appTractInfo = appTractInfoService.selectAppTractById(appTractId);
		return updateAppTractInfoDetail();
	}

	public String getTransMerId() {
		return transMerId;
	}

	public void setTransMerId(String transMerId) {
		this.transMerId = transMerId;
	}

	
	public AppTractInfo getAppTractInfo() {
		return appTractInfo;
	}

	public void setAppTractInfo(AppTractInfo appTractInfo) {
		this.appTractInfo = appTractInfo;
	}

	public List<AppTractInfo> getAppTractInfoList() {
		return appTractInfoList;
	}

	public void setAppTractInfoList(List<AppTractInfo> appTractInfoList) {
		this.appTractInfoList = appTractInfoList;
	}

	public String getAppTractList() {
		return appTractList;
	}

	public void setAppTractList(String appTractList) {
		this.appTractList = appTractList;
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

	public String getAppTractId() {
		return appTractId;
	}

	public void setAppTractId(String appTractId) {
		this.appTractId = appTractId;
	}

	public String getAppTractName() {
		return appTractName;
	}

	public void setAppTractName(String appTractName) {
		this.appTractName = appTractName;
	}

	public String getPayTract() {
		return payTract;
	}

	public void setPayTract(String payTract) {
		this.payTract = payTract;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}


	

}

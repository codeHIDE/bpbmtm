package com.bypay.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.Ostermiller.util.MD5;
import com.bypay.common.BaseAction;
import com.bypay.domain.AccountManage;
import com.bypay.domain.SysManager;
import com.bypay.service.AccountManageService;
import com.bypay.service.InterfaceService;
import com.bypay.service.SysManagerService;
import com.bypay.util.PageUtil;
public class AccountManageAction extends BaseAction{
	@Inject
	private InterfaceService interfaceService;
	@Inject
	private AccountManageService accountManageService;
	@Inject
	private SysManagerService sysManagerService;
	
	private AccountManage accountManage;
	
	private List<AccountManage> accountManageList;
	
	private String startTime;
	private String endTime;
	private String settleDate2;
	private String accountManageListJson;
	
	
	private String changeAmt;
	private String merAmt;
	private String changeReason;
	
	private String merSysId;
	private String loginName;
	private String levelPwd;
	private String settleDate;
	private String merAmts;
	private SysManager manager;
	private String changeOpenId;
	
	// 分页开始
	private int page = 1;
	private int pageSize = 15;
	private int page1 = 1;
	private int pageSize1 = 10;
	private int count = 0;
	
	/**
	 * 获取得到昨天日期
	 * @return
	 */
	public String getSettDate(){
		Calendar c = Calendar.getInstance(); 
		c.add(Calendar.DAY_OF_MONTH, -1); 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		settleDate2=formatter.format(c.getTime()); 
		getRequest().setAttribute("settleDate", settleDate2);
		//以上是获取前一天时间 格式是 20131126
		return "getSettDate";
	}
	
	
	// 列表 LIST
	public void selectAccountManageAllList() {
		if (accountManage!= null) {
			accountManage.setSettleDate(settleDate2.replaceAll("-", ""));
		}
		int count = accountManageService.selectAccountManageCount(accountManage);
		Map map=PageUtil.getPageMap(page, pageSize);
		map.put("accountManage", accountManage);
		accountManageList = accountManageService.selectAccountManageList(map);
		JSONArray array = JSONArray.fromObject(accountManageList);
		JSONObject object = new JSONObject();
		object.put("Rows", array.toString());
		object.put("Total", count);
		accountManageListJson = object.toString();
		System.out.println(accountManageListJson);
		try {
			getResponse().getWriter().write(accountManageListJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 详情
	public String selectAccountManageDetail() {
		//merSysId = getRequest().getParameter("merSysId");
		accountManage=new AccountManage();
		accountManage.setMerSysId(merSysId);
		accountManage.setSettleDate(settleDate);
		accountManage = accountManageService.selectAccountManage(accountManage);
		return "selectAccountManageDetail";
	}
	
	/**
	 * 检验授权ID和授权密码
	 * @return
	 * @throws IOException 
	 */
	public void checkInfo() throws IOException {
		manager = new SysManager();
		manager.setLoginName(loginName);
		manager.setLevelPwd(MD5.getHashString(levelPwd));
		SysManager managers = sysManagerService.getSysManager(manager);
		if(null != managers) {
			getResponse().getWriter().write("1");
		} else {
			getResponse().getWriter().write("2");
		}
	}
	
	//跳转到 变更页面
	public String toUpdateAccountManageDetail(){
		accountManage = new AccountManage();
		accountManage.setSettleDate(settleDate);
		accountManage.setMerSysId(merSysId);
		accountManage=accountManageService.selectAccountManage(accountManage);
		return "toUpdateAccountManageDetail";
	}
	

	
	// 修改详情
	public void selectUpdateAccountManageDetail() throws IOException {
			changeOpenId= (String) getRequest().getSession().getAttribute("userName");
			accountManage.setChangeAmt(accountManage.getMerAmts());
			accountManage.setChangeStatus("1");
			accountManage.setChangeOpenId(changeOpenId);
		int update = accountManageService.updateAccountManage(accountManage);
		if(update>0){
			System.out.println("修改金额成功");
			getResponse().getWriter().write("1");
		}else  {
			getResponse().getWriter().write("2");
		}
	}

	/**
	 * 上账
	 * @throws Exception 
	 */
	public void upAccount() throws Exception {
		accountManage = new AccountManage();
		accountManage.setSettleDate(settleDate);
		accountManage.setMerSysId(merSysId);
		AccountManage accountManages = accountManageService.selectAccountManage(accountManage);
		if(null != accountManages) {
//			Thread.sleep(5000);//测试数据延迟返回
//			getResponse().getWriter().write("ceshi");
			//去调用接口
			String result = interfaceService.upAccount(accountManages);
			getResponse().getWriter().write(result);
		} else {
			getResponse().getWriter().write("上账失败");
		}
	}


	public AccountManage getAccountManage() {
		return accountManage;
	}


	public void setAccountManage(AccountManage accountManage) {
		this.accountManage = accountManage;
	}


	public List<AccountManage> getAccountManageList() {
		return accountManageList;
	}


	public void setAccountManageList(List<AccountManage> accountManageList) {
		this.accountManageList = accountManageList;
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


	public String getSettleDate2() {
		return settleDate2;
	}


	public void setSettleDate2(String settleDate2) {
		this.settleDate2 = settleDate2;
	}


	public String getAccountManageListJson() {
		return accountManageListJson;
	}


	public void setAccountManageListJson(String accountManageListJson) {
		this.accountManageListJson = accountManageListJson;
	}


	public String getMerSysId() {
		return merSysId;
	}


	public void setMerSysId(String merSysId) {
		this.merSysId = merSysId;
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


	public int getPage1() {
		return page1;
	}


	public void setPage1(int page1) {
		this.page1 = page1;
	}


	public int getPageSize1() {
		return pageSize1;
	}


	public void setPageSize1(int pageSize1) {
		this.pageSize1 = pageSize1;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public String getChangeAmt() {
		return changeAmt;
	}


	public void setChangeAmt(String changeAmt) {
		this.changeAmt = changeAmt;
	}


	public String getMerAmt() {
		return merAmt;
	}


	public void setMerAmt(String merAmt) {
		this.merAmt = merAmt;
	}


	public String getChangeReason() {
		return changeReason;
	}


	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}


	public String getLoginName() {
		return loginName;
	}


	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


	public String getLevelPwd() {
		return levelPwd;
	}


	public void setLevelPwd(String levelPwd) {
		this.levelPwd = levelPwd;
	}


	public String getSettleDate() {
		return settleDate;
	}


	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}


	public String getMerAmts() {
		return merAmts;
	}


	public void setMerAmts(String merAmts) {
		this.merAmts = merAmts;
	}


	public SysManager getManager() {
		return manager;
	}


	public void setManager(SysManager manager) {
		this.manager = manager;
	}


	public String getChangeOpenId() {
		return changeOpenId;
	}


	public void setChangeOpenId(String changeOpenId) {
		this.changeOpenId = changeOpenId;
	}

}

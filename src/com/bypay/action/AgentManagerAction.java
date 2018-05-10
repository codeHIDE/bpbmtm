package com.bypay.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import com.Ostermiller.util.MD5;
import com.bypay.common.BaseAction;
import com.bypay.dao.AgentManagerDao;
import com.bypay.dao.CssConfigDao;
import com.bypay.domain.AgentManager;
import com.bypay.util.AddressUtil;
import com.bypay.util.DateUtil;
import com.bypay.util.HttpServletRequestUtil;
import com.bypay.util.RemoveSession;

public class AgentManagerAction extends BaseAction {
	@Inject
	private AgentManagerDao agentManagerDao;

	private String userName; // 用户名
	private String password; // 密码
	private String code; // 验证码
	private String agencyId;// 代理商号
	private String purview; // 权限
	private String phone; // 联系电话
	private String realName; // 真实姓名
	private String loginName; // 用户名
	private AgentManager agentManager;
	

	

	// 分页用
	private int curPage = 1; // 当前页
	private int pageSize = 15; // 每页显示条数
	private String pageBar;
//	private Page page;
	// 统计条数
	private int num;
	
	
	
	
	public AgentManagerDao getAgentManagerDao() {
		return agentManagerDao;
	}
	public void setAgentManagerDao(AgentManagerDao agentManagerDao) {
		this.agentManagerDao = agentManagerDao;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	public String getPurview() {
		return purview;
	}
	public void setPurview(String purview) {
		this.purview = purview;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public AgentManager getAgentManager() {
		return agentManager;
	}
	public void setAgentManager(AgentManager agentManager) {
		this.agentManager = agentManager;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getPageBar() {
		return pageBar;
	}
	public void setPageBar(String pageBar) {
		this.pageBar = pageBar;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public List<AgentManager> getAgentManagerList() {
		return agentManagerList;
	}
	public void setAgentManagerList(List<AgentManager> agentManagerList) {
		this.agentManagerList = agentManagerList;
	}
	public List<AgentManager> agentManagerList;

	

	
}

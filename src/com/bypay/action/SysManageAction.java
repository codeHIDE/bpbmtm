package com.bypay.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.Ostermiller.util.MD5;
import com.bypay.common.BaseAction;
import com.bypay.dao.SysModelDao;
import com.bypay.dao.SysOpLogDao;
import com.bypay.domain.SysManager;
import com.bypay.domain.SysModel;
import com.bypay.domain.SysOpLog;
import com.bypay.domain.TractInfo;
import com.bypay.service.SysManagerService;
import com.bypay.util.DateUtil;
import com.bypay.util.PageUtil;
import com.bypay.util.RemoveSession;

public class SysManageAction extends BaseAction {

	@Inject
	private SysManagerService sysManagerService;
	@Inject
	private SysModelDao sysModelDao;
	@Inject
	private SysOpLogDao sysOpLogDao;

	private SysManager sysManager;
	private SysOpLog sysOpLog;
	
	private List<SysOpLog> sysOpLogList;
	
	private String sysOpLogListJson;
	private String sysList;
	private String id;
	private String status;
	private String purview;
	private String loginName;
	private String loginPwd;
	private String realName;
	private String department;
	
	private String op;
	private String functionName;
	private String firstOpTime;
	private String lastOpTime;
	
	private String level;
	private String levelPwd1;
	// 分页使用
	private int page = 1;
	private int pageSize = 15;
	
	private PrintWriter print;

	public PrintWriter getPrint() {
		return print;
	}

	public void setPrint(PrintWriter print) {
		this.print = print;
	}

	public String toAddManager() {
		return "addManager";
	}
	/**
	 * 查询日志
	 * @return
	 */
	public void selectSysOpLog(){
		try {
			Map map = PageUtil.getPageMap(page, pageSize);
			map.put("functionName", functionName);
			map.put("firstOpTime", firstOpTime);
			map.put("lastOpTime", lastOpTime);
			map.put("loginName", loginName);
			int count = 0;
			sysOpLogList = null;
			if(!"init".equals(op)){
				count = sysOpLogDao.selectSysOpLogCount(map);
				sysOpLogList = sysOpLogDao.selectSysOpLog(map);
			}
			JSONArray array = JSONArray.fromObject(sysOpLogList);
			JSONObject object = new JSONObject();
			object.put("Rows", array.toString());
			object.put("Total", count);
			sysOpLogListJson = object.toString();
			System.out.println(sysOpLogListJson);
			getResponse().getWriter().write(sysOpLogListJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String addManager() {
		// 查询操作员是否存在
		SysManager sysManagerOld = new SysManager();
		sysManagerOld.setLoginName(sysManager.getLoginName());
		sysManagerOld = sysManagerService.selectSysManager(sysManagerOld);
		if (null == sysManagerOld) {
			sysManager.setLoginPwd(MD5.getHashString(sysManager.getLoginPwd()));
			sysManager.setStatus("1");
			sysManager.setLoginTimes(0);
			sysManager.setCreateTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
			sysManager.setLevel(sysManager.getLevel());
			System.out.println(sysManager.getLevel());
			if(sysManager.getLevel().equals("3")){
				sysManager.setLevelPwd("");
			}else{
				sysManager.setLevelPwd(MD5.getHashString(sysManager.getLevelPwd()));
			}
			int i = sysManagerService.insertSysManager(sysManager);
			if (i > 0) {
				getRequest().setAttribute("flag", "success");
				System.out.println("添加成功");
			} else {
				getRequest().setAttribute("flag", "fail");
				System.out.println("添加失败");
			}
		} else {
			getRequest().setAttribute("flag", "fail");
			System.out.println("添加失败，操作员重复");
		}

		return "addManager";
	}

	/**
	 * 获取权限列表
	 * 
	 * @throws IOException
	 */
	public void getRightList() throws IOException {
		List<SysModel> modelSysList = sysModelDao.selectSysModel();
		JSONArray array = JSONArray.fromObject(modelSysList);
		getResponse().getWriter().write(array.toString());
	}

	/**
	 * 查询所有管理员信息
	 */
	public void selectSysManagerAllList() {
		if(null == sysManager) {
			sysManager = new SysManager();
			sysManager.setLoginName(loginName);
			sysManager.setLoginPwd(loginPwd);
			sysManager.setStatus(status);
			sysManager.setDepartment(department);
			sysManager.setRealName(realName);
		}
		
		int count = sysManagerService.selectSysManagerCount(sysManager);
		Map<String, Object> map = PageUtil.getPageMap(page, pageSize);
		map.put("sysManager", sysManager);
		List<SysManager> sysManagerList = sysManagerService .selectSysManagerList(map);
		JSONArray array = JSONArray.fromObject(sysManagerList);
		JSONObject object = new JSONObject();
		object.put("Rows", array.toString());
		object.put("Total", count);
		sysList = object.toString();
		System.out.println(sysList);
		try {
			getResponse().getWriter().write(sysList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新管理员状态
	 */
	public void updateSysManageStatus() {
		sysManager = new SysManager();
		sysManager.setStatus(status);
		sysManager.setId(id);
		int update = sysManagerService.updateSysManagerStatus(sysManager);
		HttpServletResponse response = getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter printWriter;
		try {
			printWriter = response.getWriter();
			if (update > 0) {
				printWriter.write("succ");
				System.out.println("更新状态成功！");
			} else {
				printWriter.write("fones");
				System.out.println("更新状态失败！");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 重置管理员密码
	 */
	public void updateSmPass() {
		int update = sysManagerService.updateSmPass( MD5.getHashString("123456"), id);
		HttpServletResponse response = getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter printWriter;
		try {
			printWriter = response.getWriter();
			if (update > 0) {
				printWriter.write("succ");
				System.out.println("修改成功成功！");

			} else {
				System.out.println("修改失败！");
				printWriter.write("fones");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * 修改密码
	 * @return
	 */
	public String updatePass() {
		SysManager manager = new SysManager();
		manager.setLoginName((String) getRequest().getSession().getAttribute("userName"));
		HttpServletRequest request = ServletActionContext.getRequest();
		String smPass = request.getParameter("smPass");
		manager.setLoginPwd(MD5.getHashString(smPass));
		sysManagerService.updatePassWord(manager);
		RemoveSession.removeExitSession(getRequest());
		return "fail";
	}

	/**
	 * 验证密码正确与否
	 * @throws IOException 
	 */
	public void validatePass() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String smPass = request.getParameter("smPwd");
		SysManager manager = new SysManager();
		manager.setLoginName((String) getRequest().getSession().getAttribute("userName"));
		manager.setLoginPwd(MD5.getHashString(smPass));
		SysManager sysManager = sysManagerService.selectSysManager(manager);
		String flag = "fail";
		PrintWriter writer = null;
		try {
			writer = getResponse().getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (sysManager != null) {
			flag = "success";

		}
		writer.write(flag);
	}
	
	/**
	 * 点击分配权限跳转的ACTION
	 * 
	 * @return
	 */
	public String selectModelListAll() {
		return "selectModelListAll";
	}

	/**
	 * 得到已经的权限显示
	 */
	public void assignRight() {
		// 得到管理员权限
		List<SysModel> modelList = sysModelDao.selectSysModel();
		// 得到本管理员的权限；
		sysManager = sysManagerService.selectSysManagerBySmId(id);
		if (sysManager != null && sysManager.getPurview() != null && !"".equals(sysManager.getPurview())) {
			String purview = sysManager.getPurview();
			String[] rights = purview.split(",");
			for (int i = 0; i < modelList.size(); i++) {
				for (int j = 0; j < rights.length; j++) {
					if (modelList.get(i).getModelNo().equals(rights[j])) {
						modelList.get(i).setIsCheck("true");
					}
				}
			}
		}
		// 得到实际权限结束
		JSONArray array = JSONArray.fromObject(modelList);
		try {
			System.out.println(array.toString());
			getResponse().getWriter().write(array.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新管理员信息  分配管理员权限
	 */
	public void updateSysManager(){
		if (sysManager == null) {
			sysManager = new SysManager();
			sysManager.setId(id);
			sysManager.setPurview(purview);
		}
		int update=sysManagerService.updateSysManager(sysManager);
		HttpServletResponse response = getResponse();
		PrintWriter printWriter;
		try {
			printWriter = response.getWriter();
			if(update>0){
				System.out.println("更新成功！");
				printWriter.write("succ");
			}else{
				System.out.println("更新失败！");
				printWriter.write("fones");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//更新授权密码前判断原始密码是否正确
	public void selectLevelPass(){
		String userName=(String) getSession().getAttribute("userName");
		String sss=getRequest().getParameter("levelPwd");
		System.out.println(sss);
		sysManager=new SysManager();
		try {
			print = getResponse().getWriter();
			sysManager.setLoginName(userName);
			sysManager.setLevelPwd(MD5.getHashString(sss));
			sysManager=sysManagerService.selectLevelPass(sysManager);
			if(sysManager!=null){
				print.write("success");
				System.out.println(userName+"原始密码正确");
			}else{
				print.write("fail");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	//修改授权密码
	public void updateLevelPassWord(){
		try {
			sysManager=new SysManager();
			print = getResponse().getWriter();
			String userName=(String) getSession().getAttribute("userName");
			sysManager.setLoginName(userName);
			System.out.println(userName);
			System.out.println(levelPwd1);
			sysManager.setLevelPwd(MD5.getHashString(getRequest().getParameter("levelPwd1")));
			
			
			int update=sysManagerService.updateLevelPass(sysManager);
			if(update>0){
				print.write("1");
				System.out.println(userName+"管理员授权密码修改成功");
			}else{
				print.write("0");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//修改管理员信息前的 查询
	public String selectSysManager() {
		sysManager = sysManagerService.selectSysManagerBySmId(id);
		return "selectSysManager";
	}

	// 管理员信息修改
	public String updateSysManagerDetail() {  
		if (sysManager == null) {
			sysManager = new SysManager();
		}
		System.out.println(sysManager.getId());
		int result = sysManagerService.updateSysManagerDetail(sysManager);
		if (result > 0) {
			System.out.println("修改成功");
			getRequest().setAttribute("updatetractInfo", "success");
			
		}
		return "selectSysManager";
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

	public String getSysList() {
		return sysList;
	}

	public void setSysList(String sysList) {
		this.sysList = sysList;
	}

	public SysManager getSysManager() {
		return sysManager;
	}

	public void setSysManager(SysManager sysManager) {
		this.sysManager = sysManager;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPurview() {
		return purview;
	}

	public void setPurview(String purview) {
		this.purview = purview;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public SysManagerService getSysManagerService() {
		return sysManagerService;
	}
	public void setSysManagerService(SysManagerService sysManagerService) {
		this.sysManagerService = sysManagerService;
	}
	public SysModelDao getSysModelDao() {
		return sysModelDao;
	}
	public void setSysModelDao(SysModelDao sysModelDao) {
		this.sysModelDao = sysModelDao;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getFirstOpTime() {
		return firstOpTime;
	}
	public void setFirstOpTime(String firstOpTime) {
		this.firstOpTime = firstOpTime;
	}
	public String getLastOpTime() {
		return lastOpTime;
	}
	public void setLastOpTime(String lastOpTime) {
		this.lastOpTime = lastOpTime;
	}
	public SysOpLogDao getSysOpLogDao() {
		return sysOpLogDao;
	}
	public void setSysOpLogDao(SysOpLogDao sysOpLogDao) {
		this.sysOpLogDao = sysOpLogDao;
	}
	public SysOpLog getSysOpLog() {
		return sysOpLog;
	}
	public void setSysOpLog(SysOpLog sysOpLog) {
		this.sysOpLog = sysOpLog;
	}
	public List<SysOpLog> getSysOpLogList() {
		return sysOpLogList;
	}
	public void setSysOpLogList(List<SysOpLog> sysOpLogList) {
		this.sysOpLogList = sysOpLogList;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLevelPwd1() {
		return levelPwd1;
	}

	public void setLevelPwd1(String levelPwd1) {
		this.levelPwd1 = levelPwd1;
	}

}

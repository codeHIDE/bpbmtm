package com.bypay.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bypay.common.BaseAction;
import com.bypay.dao.UserTypeDao;
import com.bypay.domain.UserType;
import com.bypay.service.InterfaceService;
import com.bypay.util.Md5Util;
import com.bypay.util.PageUtil;

public class MemberAction extends BaseAction {
	/** *分页开始** */
	private int page = 1;
	private int pageSize = 15;
	private UserType userType;
	private List<UserType> userTypeList; // 用于存储查询所有的子商务信息集合
	@Inject
	private UserTypeDao userTypeDao; 
	@Inject
	private InterfaceService interfaceService;
	public void selectUserType(){
		try {
			if (userType == null) {
				userType = new UserType();
			}
			Map map = PageUtil.getPageMap(page, pageSize);
			map.put("userType", userType);
			int count = userTypeDao.selectUserTypeCount(map);
			userTypeList = userTypeDao.selectUserType(map);
			JSONArray array = JSONArray.fromObject(userTypeList);
			JSONObject object = new JSONObject();
			object.put("Rows", array.toString());
			object.put("Total", count);
			getResponse().getWriter().write(object.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addUserType(){
		userTypeDao.insertUserType(userType);
		try {
			getResponse().getWriter().write("添加成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void invite(){
		String userName = getRequest().getParameter("userName");
		String invitePhone = getRequest().getParameter("invitePhone");
		String password = getRequest().getParameter("password");
		String checkCode = getRequest().getParameter("checkCode");
		String oldCode = (String) getSession().getAttribute(userName);
		if(oldCode==null  || !oldCode.equals(checkCode)){
			try {
				getResponse().getWriter().write("验证码不正确");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ;
		}
		getSession().removeAttribute(userName);
		JSONObject json = new JSONObject();
		json.put("loginName", userName);
		json.put("password", Md5Util.getMD5(password));
		json.put("inviteName", invitePhone);
		JSONObject result = interfaceService.invite(json);
		try {
			getResponse().getWriter().write(result.optString("respMsg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getCode(){
		String userName = getRequest().getParameter("userName");
		int a = (int)(Math.random()*(9999-1000+1))+1000;//产生1000-9999的随机数
		String msg = "您的验证码为:"+a+"请尽快输入";
		getSession().setAttribute(userName, a+"");
		try {
			//发送短信
//			YMInterfaceUtil.sendSMS(userName, msg, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public List<UserType> getUserTypeList() {
		return userTypeList;
	}

	public void setUserTypeList(List<UserType> userTypeList) {
		this.userTypeList = userTypeList;
	}

	public UserTypeDao getUserTypeDao() {
		return userTypeDao;
	}

	public void setUserTypeDao(UserTypeDao userTypeDao) {
		this.userTypeDao = userTypeDao;
	}
	
	
	
}

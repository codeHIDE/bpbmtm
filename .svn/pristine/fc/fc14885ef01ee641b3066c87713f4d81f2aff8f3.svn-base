package com.bypay.service.impl.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.bypay.dao.SysOpLogDao;
import com.bypay.domain.SysOpLog;
import com.bypay.util.AddressUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LogOpertionInterceptor implements Interceptor {
	
	@Inject
	private SysOpLogDao sysOpLogDao;
	private SysOpLog sysOpLog;
	/*
	 * 拦截器实现方法
	 */
	public void init() {
		System.out.println("------拦截开始------");
	}

	public void destroy() {
		System.out.println("------拦截结束------");
	}

	public String intercept(ActionInvocation arg0) throws Exception {
		System.out.println("------我是日志拦截器------");
		sysOpLog = new SysOpLog();
		HttpServletRequest request = ServletActionContext.getRequest();
		String remoteAdd = request.getRequestURI();
		String action = remoteAdd.substring(remoteAdd.indexOf("!") + 1,remoteAdd.lastIndexOf("."));
		String actionPath = ServletActionContext.getRequest().getServletPath();
		/*
		 * 记录admin操作；
		 */
		sysOpLog.setIp(AddressUtil.getIP(request));
		String admin = (String) request.getSession().getAttribute("userName");
		if (admin == null) {
			admin = request.getParameter("userName");
		}
		
		sysOpLog.setLoginName(admin);
		sysOpLog.setOpTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		sysOpLog.setOpUrl(actionPath);

		try {
			sysOpLogDao.insertSysOpLog(sysOpLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("--------执行的方法为：-------    " + action
				+ "    ----------有效,放行");
		return arg0.invoke();

	}

}

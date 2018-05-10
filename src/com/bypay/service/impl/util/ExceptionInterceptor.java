package com.bypay.service.impl.util;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class ExceptionInterceptor implements Interceptor {

	public void destroy() {

	}

	public void init() {

	}

	public String intercept(ActionInvocation arg0) throws Exception {
		System.out.println("--------我是异常拦截器--------");
		HttpServletRequest request = ServletActionContext.getRequest();
		String result = null;
		try {
			result = arg0.invoke();
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", "系统报错啦!!");
			result = "error";
		}
		return result;
	}

}

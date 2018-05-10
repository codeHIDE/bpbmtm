package com.bypay.service.impl.util;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.bypay.dao.SysModelDao;
import com.bypay.domain.SysManager;
import com.bypay.domain.SysModel;
import com.bypay.service.SysManagerService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.bypay.util.CommonTool;
public class LoginInterceptor extends AbstractInterceptor {

	@Inject
	private SysManagerService sysManagerService;
	@Inject
	private SysModelDao sysModelDao;
	
	private SysManager sysManager;
	private SysModel sysModel;
	
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("---------------我是登录拦截器----------------");
		Map session = ActionContext.getContext().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		String action = invocation.getInvocationContext().getName();
		String actionPath = ServletActionContext.getRequest().getServletPath();
		String loginIp = CommonTool.getIpAddrByRequest(request);
		String remoteAdd = request.getRequestURI();
		String action1 = remoteAdd.substring(remoteAdd.lastIndexOf("!") + 1,
				remoteAdd.lastIndexOf("."));
		if("login".equals(action) || "member".equals(action) ||"notifyIssued".equals(action1)) {
			return invocation.invoke();
		} else {
			//验证是否有权限
			System.out.println(">>>>>>>>>>>>>>>>>"+session.get("user"));
			if(session.get("user") != null &&((String)session.get("user")).equals("alreadyLogin")) {
				ServletActionContext.getRequest().setAttribute("user", session.get("user"));
				ServletActionContext.getRequest().setAttribute("userName", session.get("userName"));
				ServletActionContext.getRequest().setAttribute("purview", session.get("purview"));
				sysModel = new SysModel();
				sysModel.setPath(actionPath);
				sysModel = sysModelDao.selectModelDetail(sysModel);
				if(null!=sysModel){
					String userName = (String)session.get("userName");
					sysManager = new SysManager();
					sysManager.setActionName(actionPath);
					sysManager.setLoginName(userName);
					sysManager = sysManagerService.selectSysMerModel(sysManager);
					if(null==sysManager){
						request.setAttribute("error", "请先登录!");
						return "fail";
					}
				}
				return invocation.invoke();
			} else {
				request.setAttribute("error", "请先登录!");
				return "fail";
			}
		}
	}
}


package com.bypay.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import net.sf.json.JSONArray;

import com.Ostermiller.util.MD5;
import com.bypay.common.BaseAction;
import com.bypay.dao.SysModelDao;
import com.bypay.domain.SysManager;
import com.bypay.domain.SysModel;
import com.bypay.service.SysManagerService;
import com.bypay.util.AddressUtil;
import com.bypay.util.RemoveSession;
import com.google.common.collect.Maps;

public class LoginAction extends BaseAction {

	@Inject
	private SysManagerService sysManagerService;
	@Inject
	private SysModelDao sysModelDao;

	private SysManager sysManager;
	private String userName; // 用户名
	private String password; // 密码
	private String code; // 验证码
	private String message; // 提示信息

	public String login() {
		System.out.println("-------清除权限session开始-------");
		RemoveSession.removeRightSession(getRequest());
		System.out.println("-------清除权限session结束-------");
		// 判断验证码
		if ((String) getSession().getAttribute("user") != null
				&& ((String) getSession().getAttribute("user"))
						.equals("alreadyLogin")) {
			return "successA";
		}
		if (userName == null || password == null || code == null) {
			message = "请正确填写登陆信息！";
			getRequest().setAttribute("error", message);
			return "login";
		}
		sysManager = new SysManager();
		sysManager.setLoginName(userName);
		sysManager.setLoginPwd(MD5.getHashString(password));
		sysManager = sysManagerService.selectSysManager(sysManager);
		if (sysManager != null) {
			if (!code.equalsIgnoreCase((String) getSession().getAttribute(
					"rand"))) {
				message = "验证码错误！";
				getRequest().setAttribute("error", message);
			} else {
				// 登录成功后更新数据库用户信息
				int count = (sysManager.getLoginTimes() == null ? 0
						: sysManager.getLoginTimes()) + 1;
				sysManager.setLoginTimes(count);
				sysManager.setLastLoginTime(new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss").format(new Date()));
				sysManager.setLastLoginIp(AddressUtil.getIP(getRequest()));
				sysManager.setLoginName(userName);
				sysManager.setLoginPwd(MD5.getHashString(password));
				if (sysManagerService.updateLoginSysManager(sysManager)) {
					getSession().setAttribute("user", "alreadyLogin");
					getSession().setAttribute("userName", userName);
					// getSession().setAttribute("password", password);
					getSession().setAttribute("level", sysManager.getLevel());
					initPurview();
					return "successA";
				} else {
					message = "修改登录信息失败！";
					getRequest().setAttribute("error", message);
				}
			}
		} else {
			message = "登录名或密码错误！";
			getRequest().setAttribute("error", message);
		}

		return "login";
	}

	// 权限初始化
	private void initPurview() {
		sysManager = new SysManager();
		sysManager.setLoginName(userName);
		sysManager = sysManagerService.selectSysManager(sysManager);
		if (sysManager != null) {
			// 将登录用户的系统权限保存的session会话中
			String rights[] = sysManager.getPurview().split(",");
			if (rights != null && rights.length > 0) {
				Map<String, Object> map = Maps.newHashMap();
				for (int i = 0; i < rights.length; i++) {
					map.put(rights[i], rights[i]);
					System.out.println("此管理员应有权限：modelNo_" + rights[i].trim()
							+ ":" + rights[i].trim());
				}
				getSession().setAttribute("purview", map);// 在session中保存用户权限
			}

		}
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

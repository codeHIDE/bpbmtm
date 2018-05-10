package com.bypay.action;

import com.bypay.common.BaseAction;
import com.bypay.util.RemoveSession;

public class ExitAction extends BaseAction {
	// 退出系统
	public String exit() {
		RemoveSession.removeExitSession(getRequest());
		return "fail";
	}
}

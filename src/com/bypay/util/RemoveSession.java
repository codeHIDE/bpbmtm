package com.bypay.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RemoveSession {
	public static void removeRightSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Enumeration e = session.getAttributeNames();
		while (e.hasMoreElements()) {
			String sessionName = (String) e.nextElement();
			if (sessionName.startsWith("modelNo_")) {
				System.out.println("清除的session：   " + sessionName);
				session.removeAttribute(sessionName);
			}
		}
	}

	public static void removeExitSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Enumeration e = session.getAttributeNames();
		while (e.hasMoreElements()) {
			String sessionName = (String) e.nextElement();
			System.out.println("清除的session：   " + sessionName);
			session.removeAttribute(sessionName);
		}
	}
}

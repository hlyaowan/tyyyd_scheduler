package com.tyyd.scheduler.util;

import javax.servlet.http.HttpSession;

import com.tyyd.scheduler.model.UserInfo;


public class SessionUtil {
	private SessionUtil() {

	}

	public static String builderUrl(HttpSession session, String url) {
		if (url == null || url.contains("sid")) {
			return url;
		}
		if (url.contains("?")) {
			return url + "&sid=" + session.getId();
		} else {
			return url + "?sid=" + session.getId();
		}
	}

	public static void setUserInfoSession(HttpSession session, UserInfo userInfo) {
		System.out.println("---------------sessionId =" + session.getId()
				+ "---------");
		session.setAttribute(session.getId(), userInfo);
	}

	public static UserInfo getUserInfo(HttpSession session) {
		String sid = session.getId();
		UserInfo userInfo=(UserInfo)session.getAttribute(sid);
		return userInfo;

	}

	public static void removeUserInfo(HttpSession session) {
		String sid = session.getId();
		session.removeAttribute(sid);
	}
	
}

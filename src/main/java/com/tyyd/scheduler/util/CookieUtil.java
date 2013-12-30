package com.tyyd.scheduler.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CookieUtil{
	/**
	 * 添加cookie
	 * 
	 * @param response
	 * @param request
	 * @param name
	 *            cookie的名称
	 * @param value
	 *            cookie的值
	 * @param maxAge
	 *            cookie存放的时间(以秒为单位,假如存放三天,即3*24*60*60; 如果值为0,cookie将随浏览器关闭而清除)
	 */
	public static void addCookie(HttpServletResponse response,
			HttpServletRequest request, String name, String value,
			Integer maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath(request.getContextPath() + "/");
		if (maxAge != null) {
			cookie.setMaxAge(maxAge);
		}

		response.addCookie(cookie);
	}

	/**
	 * remove cookeByName
	 * @param response
	 * @param request
	 * @param name
	 */
	public static void removeCookie(HttpServletResponse response,HttpServletRequest request, String name){
		Cookie [] cs = request.getCookies();
		if(cs == null || cs.length == 0) return;
		for (int j = 0; j < cs.length; j++) {
			if(name != null && name.equals(cs[j].getName())){
				cs[j].setValue(null);
				cs[j].setMaxAge(0);
				cs[j].setPath(request.getContextPath() + "/");
				response.addCookie(cs[j]);
				break;
			}
		}
	}
	
	public static String getParamFromCookie(HttpServletRequest request,
			String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

}

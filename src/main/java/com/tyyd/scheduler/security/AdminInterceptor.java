package com.tyyd.scheduler.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.tyyd.scheduler.model.UserInfo;
import com.tyyd.scheduler.util.SessionUtil;

@Repository
public class AdminInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	    UserInfo user = SessionUtil.getUserInfo(request.getSession());
        if (user != null) {
            return super.preHandle(request, response, handler);
        }
        response.sendRedirect("/index.htm");
        return false;
	}
}

/*
 * 
 */
// Created on 2013-4-29

package com.tyyd.scheduler.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tyyd.scheduler.model.UserInfo;
import com.tyyd.scheduler.service.UserService;
import com.tyyd.scheduler.util.MD5Util;
import com.tyyd.scheduler.util.SessionUtil;


/**
 * @author joe.chen
 */
@Controller
public class LoginController extends BasicController {
    
    private Logger logger =Logger.getLogger(LoginController.class);

    @Autowired
    private UserService userService;
    
    
    
    @RequestMapping(value = { "/login.json" })
    public String login(@RequestParam("account") String account, @RequestParam("password") String password,
            @RequestParam("loginState") String loginState, HttpServletRequest request, HttpServletResponse response,
            ModelMap model) {
        String md5pass =MD5Util.encoder(password);
        boolean isLogin =userService.login(account,md5pass);
        Message message =new Message();
        if(isLogin){
            UserInfo userInfo =new UserInfo();
            userInfo.setUserName(account);
            userInfo.setUserPwd(md5pass);
            userInfo =userService.getUser(userInfo);
            message.setCode("1");
            message.setMessage("success");
            SessionUtil.setUserInfoSession(request.getSession(), userInfo);
        }else {
            message.setCode("0");
            message.setMessage("fail");
        }
        model.addAttribute(message);
        return "login";
    }

    /**
     * 退出登录
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = { "/loginOut.htm" })
    public void loginOut(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        try {
            UserInfo userInfo = SessionUtil.getUserInfo(request.getSession());
            if (userInfo != null) {
                SessionUtil.removeUserInfo(request.getSession());
            }
            response.sendRedirect("/index.htm");
        }
        catch (Exception e) {
            logger.error("退出登录失败！");
        }
        
    }


    /**
     * 检测登陆状态 login： true false
     */
    @RequestMapping(value = { "/checkLogin.json" })
    public String checkLogin(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        UserInfo userInfo = SessionUtil.getUserInfo(request.getSession());
        if (userInfo != null) {
            model.addAttribute("login", true);
        }
        else {
            model.addAttribute("login", false);
        }
        return "json";
    }

    
    public class Message{
        private String code;
        private String message;
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
}

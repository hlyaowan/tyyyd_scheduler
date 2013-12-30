package com.tyyd.scheduler.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tyyd.scheduler.model.UserInfo;
import com.tyyd.scheduler.util.SessionUtil;

/***
 * @author hlyaowan
 */
@Controller
public class IndexController {
    @RequestMapping(value = { "/index.htm" })
    public String Index(
                        HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        UserInfo userInfo =SessionUtil.getUserInfo(request.getSession());
        if(userInfo!=null){
            model.addAttribute("userInfo",userInfo);
        }
        return "index";
    }
    
    
    @RequestMapping(value = { "/help.htm" })
    public String help(
                        HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        UserInfo userInfo =SessionUtil.getUserInfo(request.getSession());
        if(userInfo!=null){
            model.addAttribute("userInfo",userInfo);
        }
        return "help";
    }
}

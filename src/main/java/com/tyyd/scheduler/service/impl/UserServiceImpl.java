package com.tyyd.scheduler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyyd.scheduler.dao.UserDao;
import com.tyyd.scheduler.model.UserInfo;
import com.tyyd.scheduler.service.UserService;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    public UserInfo getUser(UserInfo usermodel) {
        return userDao.getUserInfo(usermodel);
    }


    public boolean login(String username, String password) {
        UserInfo usermodel = new UserInfo();
        usermodel.setUserName(username);
        usermodel.setUserPwd(password);
        UserInfo user = userDao.getUserInfo(usermodel);
        if(user!=null){
            return user.getUserPwd().equals(password) ? true : false;
        }else {
            return false;
        }
    }

}

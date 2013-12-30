package com.tyyd.scheduler.dao;

import java.util.List;

import com.tyyd.scheduler.model.UserInfo;


public interface UserDao {
    public List<UserInfo> getUserInfoList(UserInfo condition);


    public UserInfo getUserInfo(UserInfo condition);


    public int saveUserInfo(UserInfo condition);
}

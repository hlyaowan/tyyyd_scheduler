package com.tyyd.scheduler.service;

import com.tyyd.scheduler.model.UserInfo;


public interface UserService {
    public UserInfo getUser(UserInfo usermodel);


    public boolean login(String username, String password);

}

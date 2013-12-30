package com.tyyd.scheduler.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tyyd.scheduler.analysis.mybatis.MybatisTemplate;
import com.tyyd.scheduler.dao.UserDao;
import com.tyyd.scheduler.model.UserInfo;

@Repository
public class UserDaoImpl extends MybatisTemplate implements UserDao {
    private static final String NAMESPACE = UserDao.class.getName().concat(".");


    public List<UserInfo> getUserInfoList(UserInfo condition) {
        return super.<UserInfo> getList(NAMESPACE.concat("getUserInfo"), condition);
    }

    public UserInfo getUserInfo(UserInfo condition) {
        return super.<UserInfo> get(NAMESPACE.concat("getUserInfo"), condition);
        
    }
    
    public int saveUserInfo(UserInfo condition) {
        return super.save(NAMESPACE.concat("saveUserInfo"), condition);
        
    }
}

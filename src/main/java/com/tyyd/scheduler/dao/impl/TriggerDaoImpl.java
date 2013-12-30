package com.tyyd.scheduler.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tyyd.scheduler.analysis.mybatis.MybatisTemplate;
import com.tyyd.scheduler.dao.TriggerDao;
import com.tyyd.scheduler.model.TriggerInfo;


@Repository
public class TriggerDaoImpl extends MybatisTemplate implements TriggerDao {

    private static final String NAMESPACE = TriggerDao.class.getName().concat(".");

    public List<Map<String, Object>>  getTriggerInfoList(TriggerInfo condition) {
        return super.<Map<String, Object>> getList(NAMESPACE.concat("getTriggerList"), condition);
    }
}

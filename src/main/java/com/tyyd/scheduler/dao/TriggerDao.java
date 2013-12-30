package com.tyyd.scheduler.dao;

import java.util.List;
import java.util.Map;

import com.tyyd.scheduler.model.TriggerInfo;


public interface TriggerDao {
    public List<Map<String, Object>>   getTriggerInfoList(TriggerInfo condition) ;
}

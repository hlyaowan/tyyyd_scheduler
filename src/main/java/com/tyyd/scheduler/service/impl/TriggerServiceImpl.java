package com.tyyd.scheduler.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyyd.scheduler.common.Constant;
import com.tyyd.scheduler.dao.TriggerDao;
import com.tyyd.scheduler.model.TriggerInfo;
import com.tyyd.scheduler.service.TriggerService;

@Service
public class TriggerServiceImpl implements TriggerService{
    
    @Autowired
    private TriggerDao triggerDao;
    
    
    
    @SuppressWarnings("rawtypes")
    public List<Map<String, Object>> getTriggerInfoList() {
      List<Map<String, Object>> list= triggerDao.getTriggerInfoList(null);
      long val = 0;
      String temp = null;
      for (Map<String, Object> map : list) {
          temp = MapUtils.getString(map, "triggerName");
          if(StringUtils.indexOf(temp, "&") != -1){
              map.put("displayName", StringUtils.substringBefore(temp, "&"));
          }else{
              map.put("displayName", temp);
          }
          
          val = MapUtils.getLongValue(map, "nextFireTime");
          if (val > 0) {
              map.put("nextFireTime", DateFormatUtils.format(val, "yyyy-MM-dd HH:mm:ss"));
          }

          val = MapUtils.getLongValue(map, "prevFireTime");
          if (val > 0) {
              map.put("prevFireTime", DateFormatUtils.format(val, "yyyy-MM-dd HH:mm:ss"));
          }

          val = MapUtils.getLongValue(map, "startTime");
          if (val > 0) {
              map.put("startTime", DateFormatUtils.format(val, "yyyy-MM-dd HH:mm:ss"));
          }
          
          val = MapUtils.getLongValue(map, "endTime");
          if (val > 0) {
              map.put("endTime", DateFormatUtils.format(val, "yyyy-MM-dd HH:mm:ss"));
          }
          
          map.put("triggerState",Constant.status.get(MapUtils.getString(map, "triggerState")));
      }
        return list;
    }
}

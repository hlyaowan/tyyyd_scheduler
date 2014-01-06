package com.tyyd.scheduler.quartz.target;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class MyQuartzJobBean extends QuartzJobBean {

    private final String  GROUP_GUQIANG = "group_guqiang";
    private final String  GROUP_HONGXIU = "group_hongxiu";
    private final String  GROUP_ZHULANG = "group_zhulang";
    private final String  GROUP_NETWAY  = "group_netway";

    private SimpleService simpleService;

    public void setSimpleService(SimpleService simpleService) {
        this.simpleService = simpleService;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobexecutioncontext) throws JobExecutionException {
        Trigger trigger = jobexecutioncontext.getTrigger();
        String triggerName = trigger.getName();
        String group = trigger.getGroup();

        // 根据Trigger组别调用不同的业务逻辑方法
        if (StringUtils.equals(group, Scheduler.DEFAULT_GROUP)) {
            simpleService.executeDefaultJob(triggerName, group);
        } else if (StringUtils.equals(group, GROUP_GUQIANG)) {
            simpleService.executeGuqiangJob(triggerName, group);
        } else if (StringUtils.equals(group, GROUP_HONGXIU)) {
            simpleService.executeHongxiuJob(triggerName, group);
        } else if (StringUtils.equals(group, GROUP_NETWAY)) {
            simpleService.executeNetwayJob(triggerName, group);
        } else if (StringUtils.equals(group, GROUP_ZHULANG)) {
            simpleService.executeZhulangJob(triggerName, group);
        } else {
            simpleService.executeDefaultJob(triggerName, group);
        }
    }

}

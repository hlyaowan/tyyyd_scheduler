package com.tyyd.scheduler.quartz.target;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyyd.scheduler.common.ConstantApp;
import com.tyyd.scheduler.common.ConstantChannel;
import com.tyyd.scheduler.exec.RunShell;
import com.tyyd.scheduler.model.JobInfo;
import com.tyyd.scheduler.service.JobService;
import com.tyyd.scheduler.util.ReadAppInfoUtil;

@Service("simpleService")
public class SimpleService implements Serializable {

    private static final long   serialVersionUID = 122323233244334343L;
    private static final Logger logger           = Logger.getLogger(SimpleService.class);

    public void executeDefaultJob(String triggerName, String group) {
        // 这里执行定时调度业务
        logger.info("00000-execute executeDefaultJob:" + triggerName + "==" + group);
        System.out.println("00000-execute executeDefaultJob:" + triggerName + "==" + group);

    }

    public void executeGuqiangJob(String triggerName, String group) {
        // 这里执行定时调度业务
        logger.info("AAAAA-execute executeGuqiangJob:" + triggerName + "==" + group);
        JobInfo JobInfo = getJobInfo(ConstantChannel.GUQIANG);
        RunShell.execShellMain(ConstantApp.shpath, JobInfo.getPath());
        System.out.println("AAAAA-execute executeGuqiangJob:" + triggerName + "==" + group + "==" + JobInfo.getPath());
    }

    public void executeHongxiuJob(String triggerName, String group) {
        logger.info("BBBBB-execute executeHongxiuJob:" + triggerName + "==" + group);

        JobInfo JobInfo = getJobInfo(ConstantChannel.HONGXIU);
        RunShell.execShellMain(ConstantApp.shpath, JobInfo.getPath());
        System.out.println("BBBBB-execute executeHongxiuJob:" + triggerName + "==" + group + "==" + JobInfo.getPath());
    }

    public void executeZhulangJob(String triggerName, String group) {
        logger.info("CCCCC-execute executeZhulangJob:" + triggerName + "==" + group);

        JobInfo JobInfo = getJobInfo(ConstantChannel.ZHULANG);
        RunShell.execShellMain(ConstantApp.shpath, JobInfo.getPath());
        System.out.println("CCCCC-execute executeZhulangJob:" + triggerName + "==" + group + "==" + JobInfo.getPath());
    }

    public void executeNetwayJob(String triggerName, String group) {
        logger.info("DDDDD-execute executeNetwayJob:" + triggerName + "==" + group);
        JobInfo JobInfo = getJobInfo(ConstantChannel.NETWAY);
        RunShell.execShellMain(ConstantApp.shpath, JobInfo.getPath());
        System.out.println("DDDDD-execute executeNetwayJob:" + triggerName + "==" + group + "==" + JobInfo.getPath());
    }

    public JobInfo getJobInfo(int id) {
        List<JobInfo> list = ReadAppInfoUtil.readAppInfoFile();
        JobInfo jobInfoApp = new JobInfo();
        for (JobInfo jobInfo : list) {
            if (jobInfo.getId() == id) {
                jobInfoApp = jobInfo;
            }
        }
        return jobInfoApp;
    }
}

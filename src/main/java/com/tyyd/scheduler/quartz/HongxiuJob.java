package com.tyyd.scheduler.quartz;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.tyyd.scheduler.common.ConstantApp;
import com.tyyd.scheduler.exec.RunShell;
import com.tyyd.scheduler.model.JobInfo;
import com.tyyd.scheduler.util.ReadAppInfoUtil;


public class HongxiuJob implements StatefulJob {
    /** 日志* */
    public Log logger = LogFactory.getLog(this.getClass());
   
    

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        logger.info("start execute hongxiu job!");
        List<JobInfo> serverList =ReadAppInfoUtil.readAppInfoFile();
        JobInfo job =null;
        for (JobInfo jobInfo : serverList) {
            if(StringUtils.equals(jobInfo.getChannel(),"hongxiu")){
                job=jobInfo;
            }
        }
        RunShell.execShellMain(ConstantApp.shpath,job.getPath());
    }
}

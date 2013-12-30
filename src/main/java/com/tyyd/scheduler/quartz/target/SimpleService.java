package com.tyyd.scheduler.quartz.target;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tyyd.scheduler.common.ConstantApp;
import com.tyyd.scheduler.exec.RunShell;
import com.tyyd.scheduler.model.JobInfo;
import com.tyyd.scheduler.util.ReadAppInfoUtil;

@Service("simpleService")
public class SimpleService implements Serializable{
	
	private static final long serialVersionUID = 122323233244334343L;
	private static final Logger logger = LoggerFactory.getLogger(SimpleService.class);
	

	
	public void testMethod(String triggerName, String group){
		//这里执行定时调度业务
		logger.info("execute scheduler:"+triggerName+"=="+group);
		List<JobInfo> serverList =ReadAppInfoUtil.readAppInfoFile();
        for (JobInfo jobInfo : serverList) {
            try {
                RunShell.execShellMain(ConstantApp.shpath,jobInfo.getPath());
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                logger.error("InterruptedException:"+e.getMessage());
            }
        }
	}
	
//	public void testMethod2( String triggerName,String group){
//		//这里执行定时调度业务
//		logger.info("BBBB:"+triggerName+"=="+group);
//	}


	
	
}

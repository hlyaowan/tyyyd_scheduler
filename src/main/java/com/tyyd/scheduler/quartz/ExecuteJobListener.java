package com.tyyd.scheduler.quartz;

import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.tyyd.scheduler.model.JobInfo;
import com.tyyd.scheduler.service.JobService;
import com.tyyd.scheduler.util.ReadAppInfoUtil;

public class ExecuteJobListener extends HttpServlet implements ServletContextListener {

    private static final long serialVersionUID = 1L;

    public Log                logger           = LogFactory.getLog(this.getClass());

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
    }
    @Autowired
    private  JobService jobService;

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        try {
            while (true) {
                QuartzManager manager = QuartzManager.getInstance();
                List<JobInfo> serverList =ReadAppInfoUtil.readAppInfoFile();
                for (JobInfo jobInfo : serverList) {
                    logger.info("TimeExpress:"+jobInfo.getTimeExpress());
                    if(StringUtils.equals(jobInfo.getChannel(),"hongxiu")){
                        manager.startJob(HongxiuJob.class, jobInfo.getTimeExpress());
                    }else if (StringUtils.equals(jobInfo.getChannel(),"netway")) {
                        manager.startJob(NetwayJob.class, jobInfo.getTimeExpress());
                    }else if (StringUtils.equals(jobInfo.getChannel(),"guqiang")) {
                        manager.startJob(GuqiangJob.class, jobInfo.getTimeExpress());
                    }else if (StringUtils.equals(jobInfo.getChannel(),"zhulang")) {
                        manager.startJob(ZhulangJob.class, jobInfo.getTimeExpress());
                    }
                }
                Thread.sleep(2000);
            }
            
        } catch (ParseException e) {
            logger.error("start jobs ParseException :"+e.getMessage());
        } catch (SchedulerException e) {
            logger.error("start jobs SchedulerException :"+e.getMessage());
        } catch (InterruptedException e) {
            logger.error("start jobs thread :"+e.getMessage());
        }
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                  config.getServletContext());
    }

}

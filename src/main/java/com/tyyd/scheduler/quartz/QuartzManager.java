package com.tyyd.scheduler.quartz;

import java.text.ParseException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzManager {

    /** 日志 **/
    public Log                   logger           = LogFactory.getLog(this.getClass());

    private static Object        lock             = new Object();
    private static QuartzManager context          = null;

    private Scheduler            scheduler        = null;
    private SchedulerFactory     schedulerFactory = new StdSchedulerFactory();

    public QuartzManager(){
        try {
            scheduler = schedulerFactory.getScheduler();
            scheduler.start();
        } catch (SchedulerException e) {
            logger.error("Quartz初始化失败", e);
        }
    }

    /**
     * @author BOBO
     * @功能
     * @return
     */
    public static QuartzManager getInstance() {
        if (context == null) {
            synchronized (lock) {
                if (context == null) {
                    context = new QuartzManager();
                }
            }
        }
        return context;
    }

    /**
     * @author BOBO
     * @remark
     * @date Nov 2, 2010
     * @param clazz
     * @param cronExp eg. "0 30 07 * * ?"
     */
    @SuppressWarnings("rawtypes")
    public void startJob(Class clazz, String cronExp) throws ParseException, SchedulerException {

        JobDetail jobDetail = new JobDetail(clazz.getSimpleName() + "Job", "jobGroup", clazz);
        CronTrigger cronTrigger = new CronTrigger(clazz.getSimpleName() + "Trigger", "triggerGroup");
        CronExpression cexp = new CronExpression(cronExp);
        cronTrigger.setCronExpression(cexp);
        scheduler.scheduleJob(jobDetail, cronTrigger);
         logger.info("开始任务:" + clazz.getSimpleName() + " - " + cronExp);
    }

}

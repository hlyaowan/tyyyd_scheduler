package com.tyyd.scheduler.quartz.target;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tyyd.scheduler.quartz.service.SchedulerService;


public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext springContext = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-all.xml"});
		SchedulerService schedulerService = (SchedulerService)springContext.getBean("schedulerService");
		
		//执行业务逻辑...
		
		//设置高度任务
		//每10秒中执行调试一次
		System.out.println("=====================================");
		
		schedulerService.schedule("main","0/10 * * ? * * *"); 
		
//		Date startTime = parse("2009-06-01 22:16:00");
//		Date endTime =  parse("2014-06-01 22:20:00");
        
		//2009-06-01 21:50:00开始执行调度
//		schedulerService.schedule(startTime);
		

		//2009-06-01 21:50:00开始执行调度，2009-06-01 21:55:00结束执行调试
		//schedulerService.schedule(startTime,endTime);
		
		//2009-06-01 21:50:00开始执行调度，执行5次结束
		//schedulerService.schedule(startTime,null,5);

		//2009-06-01 21:50:00开始执行调度，每隔20秒执行一次，执行5次结束
		//schedulerService.schedule(startTime,null,5,20);
		
		//等等，查看com.sundoctor.quartz.service.SchedulerService		
	}
	
	private static Date parse(String dateStr){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

}

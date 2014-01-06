package com.tyyd.scheduler.common;

import java.util.HashMap;
import java.util.Map;

public class Constant {
	public static final String 	TRIGGERNAME = "triggerName";
	public static final String 	TRIGGERGROUP = "triggerGroup";
	public static final String STARTTIME = "startTime";
	public static final String ENDTIME = "endTime";
	public static final String REPEATCOUNT = "repeatCount";
	public static final String REPEATINTERVEL = "repeatInterval";
	
	public static final Map<String,String> status = new HashMap<String,String>();
	static{
		status.put("ACQUIRED", "<img src='/images/run.png'>");
		status.put("PAUSED", "<img src='/images/stop.png'>");
		status.put("WAITING", "<img src='/images/wait.gif' width='20px' height='20px'>");		
	}
}

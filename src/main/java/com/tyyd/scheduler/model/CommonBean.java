package com.tyyd.scheduler.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.hadoop.hbase.util.Bytes;

public class CommonBean {
	public static final int OTHER = -1;
	public static final int IP = 0;
	public static final int TIME1=1;
	public static final int TIME2 = 2;
	public static final int METHOD = 3;
	public static final int API = 4;
	public static final int CODE = 5;
	public static final int REQUESTLENGTH = 6;
	public static final int SIZE = 7;
	public static final HashMap<String, Integer> PARSE_PARAM = new HashMap<String, Integer>();
	public static final Map<Character, Integer> dateParamMap = new HashMap<Character, Integer>();
	static {
		dateParamMap.put('y', Calendar.YEAR);
		dateParamMap.put('M', Calendar.MONTH);
		dateParamMap.put('d', Calendar.DAY_OF_MONTH);
		PARSE_PARAM.put("%other", OTHER);
		PARSE_PARAM.put("%ip", IP);
		PARSE_PARAM.put("%time1", TIME1);
		PARSE_PARAM.put("%time2", TIME2);
		PARSE_PARAM.put("%method", METHOD);
		PARSE_PARAM.put("%api", API);
		PARSE_PARAM.put("%code", CODE);
		PARSE_PARAM.put("%requestlength", REQUESTLENGTH);
		PARSE_PARAM.put("%size", SIZE);
	}
	
	public static ThreadLocal<DateFormat> time1Thread = new ThreadLocal<DateFormat>() {
        protected synchronized DateFormat initialValue() {
            return new SimpleDateFormat("[dd/MMM/yyyy:HH:mm:ss Z]", Locale.US);
        }
    };
    
//    public static final byte[] INFO_BYTE = Bytes.toBytes("info"); 
    public static final byte[] T_BYTE = Bytes.toBytes("t");
    public static final byte[] IP_BYTE = Bytes.toBytes("ip");
    public static final byte[] IPSUM_BYTE = Bytes.toBytes("ipsum");
    public static final byte[] DT_BYTE = Bytes.toBytes("dt");
    public static final byte[] API_BYTE = Bytes.toBytes("api");
    public static final byte[] CODE_BYTE = Bytes.toBytes("code");
    public static final byte[] REQ_LEN_BYTE = Bytes.toBytes("reqlen");
    public static final byte[] SIZE_BYTE = Bytes.toBytes("size");
    public static final byte[] METHOD_BYTE = Bytes.toBytes("method");
    public static final byte[] TS_BYTE = Bytes.toBytes("ts");
}

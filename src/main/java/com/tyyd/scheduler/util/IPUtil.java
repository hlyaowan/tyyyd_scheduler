package com.tyyd.scheduler.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class IPUtil {
	public static long ipv4ToNum(String ipv4Str){
		long ipv4Integer = 0;
		String[] ipParams = StringUtils.split(ipv4Str, "\\.");
		if (ipParams.length != 4) {
			return ipv4Integer;
		}
		
		ipv4Integer = NumberUtils.toLong(ipParams[0])*16777216 
				+ NumberUtils.toLong(ipParams[1])*65536 
				+ NumberUtils.toLong(ipParams[2])*256 
				+ NumberUtils.toLong(ipParams[3]);
		return ipv4Integer;
	}
	
	public static String numToIPV4(long ipv4Num) {
		String[] array = new String[4];
		array[0] = String.valueOf((ipv4Num/16777216) % 256);
		array[1] = String.valueOf((ipv4Num/65536) % 256);
		array[2] = String.valueOf((ipv4Num/256) % 256);
		array[3] = String.valueOf((ipv4Num) % 256);
		String ipv4Str = StringUtils.join(array, ".");
		return ipv4Str;
	}
	
	public static void main(String[] args) {
        System.out.println(numToIPV4(13400));
    }
}

package com.tyyd.scheduler.util;

import java.security.NoSuchAlgorithmException;

public class MD5Util {
	public static String encoder(byte[] source) {
		String result = null;
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest();
			char str[] = new char[16 * 2];
			int k = 0;
			for (int i = 0; i < 16; i++) {
				byte byte0 = tmp[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			result = new String(str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String encoder(String source) {
		return encoder(source.getBytes());
	}
	
	public static void main(String[] args) {
	    String mcpkey ="88ef63a014b3f579";
	    String lastUpdateTime="0";
        System.out.println(encoder(encoder(mcpkey+lastUpdateTime)+mcpkey));
    }
}

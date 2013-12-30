package com.tyyd.scheduler.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;

public class FileUtil {

    public static String xmlFile = StringUtils.EMPTY;
    
    //最后一次读取时间
    private static long lastModifyTime = 0;
    
    //间隔读取时间，单位毫秒
    private static final long JIANGETIME = 3600*1000;

    public static Log    log     = LogFactory.getLog(FileUtil.class);

    public static String getXMLContent(String remoteFile) {
    	long current = System.currentTimeMillis();
        if (StringUtils.isBlank(xmlFile)|| (current - lastModifyTime) > JIANGETIME) {
        	FileInputStream is = null;
            try {
                File file = new File(remoteFile);
                is = new FileInputStream(remoteFile);
                int size = (int) file.length();
                byte[] bytes = getBytes(is, size);
                String content = new String(bytes, "UTF-8");
                xmlFile = content;
                lastModifyTime = System.currentTimeMillis();
            } catch (IOException e) {
                log.error("read file fail",e);
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (Exception e) {
                    	log.error("",e);
                    }
                }
            }
        }
        return xmlFile;
    }

    private static byte[] getBytes(InputStream inputStream, int size) {
        byte[] bytes = new byte[size];
        try {
            inputStream.read(bytes);
            return bytes;
        } catch (Exception e) {
        	log.error("",e);
        }
        return null;
    }

    /**
     * 字符串转换为DOCUMENT
     * 
     * @param xmlStr 字符串
     * @return doc JDOM的Document
     * @throws Exception
     */
    public static Document string2Doc(String xmlStr) throws Exception {
        Reader in = new StringReader(xmlStr);
        Document doc = (new SAXBuilder()).build(in);
        return doc;
    }

}

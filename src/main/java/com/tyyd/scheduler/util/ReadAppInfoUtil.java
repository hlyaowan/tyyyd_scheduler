package com.tyyd.scheduler.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import com.tyyd.scheduler.model.JobInfo;

public class ReadAppInfoUtil {

    public static String      xmlFile        = StringUtils.EMPTY;

    // 最后一次读取时间
    private static long       lastModifyTime = 0;

    // 间隔读取时间，单位毫秒
    private static final long JIANGETIME     = 1* 1000;

    public static Log         log            = LogFactory.getLog(FileUtil.class);

    public static String getXMLContent(URI remoteFile) {
        long current = System.currentTimeMillis();
        if (StringUtils.isBlank(xmlFile) || (current - lastModifyTime) > JIANGETIME) {
            FileInputStream is = null;
            try {
                File file = new File(remoteFile);
                is = new FileInputStream(file);
                int size = (int) file.length();
                byte[] bytes = getBytes(is, size);
                String content = new String(bytes, "UTF-8");
                xmlFile = content;
                lastModifyTime = System.currentTimeMillis();
            } catch (IOException e) {
                log.error("read file fail", e);
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (Exception e) {
                        log.error("", e);
                    }
                }
            }
        }
        return xmlFile;
    }

    @SuppressWarnings("unchecked")
    public static List<JobInfo> readAppInfoFile() {
        FileInputStream is = null;
        try {
            List<JobInfo> list = Collections.EMPTY_LIST;
            String xmlStr = getXMLContent(ReadAppInfoUtil.class.getClassLoader().getResource("conf/appinfo.xml").toURI());
            Document doclisten = string2Doc(xmlStr);
            List<Element> eleList = doclisten.getRootElement().getChild("appList").getChildren("appInfo");
            if (CollectionUtils.isNotEmpty(eleList)) {
                list = new ArrayList<JobInfo>();
            }
            for (int i = 0; i < eleList.size(); i++) {
                Element elemodel = (Element) eleList.get(i);
                JobInfo model = new JobInfo();
                model.setId(Integer.parseInt(elemodel.getChildText("id")));
                model.setCpName(elemodel.getChildText("cpName"));
                model.setChannel(elemodel.getChildText("channel"));
                model.setCreateTime(DateUtils.parseDateTime(elemodel.getChildText("createTime")));
                model.setExtendsInfo(elemodel.getChildText("extendsInfo"));
                model.setFlag(Integer.parseInt(elemodel.getChildText("flag")));
                model.setModifyTime(DateUtils.parseDateTime(elemodel.getChildText("modifyTime")));
                model.setPath(elemodel.getChildText("path"));
                model.setReadTime(DateUtils.parseDateTime(elemodel.getChildText("readTime")));
                model.setTimeExpress(elemodel.getChildText("timeExpress"));
                list.add(model);
            }
//            System.out.println("list:" + list.size());
            return list;
        } catch (Exception e) {
            return null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                }
            }
        }
    }

    private static byte[] getBytes(InputStream inputStream, int size) {
        byte[] bytes = new byte[size];
        try {
            inputStream.read(bytes);
            return bytes;
        } catch (Exception e) {
            log.error("", e);
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
    
    public static void main(String[] args) {
        System.out.println(readAppInfoFile().size());
    }
}

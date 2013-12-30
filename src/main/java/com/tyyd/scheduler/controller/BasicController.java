package com.tyyd.scheduler.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.tyyd.scheduler.util.CookieUtil;


public abstract class BasicController {

     /** json数据容器 */
    private String jsonDate;

    private String jsoncallback;

    /** 是否返回json数据 */
    private String isJson;

    // url加载类
    private static Properties path = null;


    protected void out(String str, HttpServletResponse response, HttpServletRequest request) {
        try {
            response.setContentType("application/x-javascript");
            response.setCharacterEncoding("UTF-8");
            ServletOutputStream out = response.getOutputStream();
            out.println(str);
            out.flush();
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param map
     * @param list
     *            参数不能同时传值
     */
    protected void out(Map map, List list, HttpServletResponse response, HttpServletRequest request) {
        String res = "";
        if (map != null && map.size() != 0) {
            JSONObject jsonObj = new JSONObject();
            jsonObj.putAll(map);
            res = jsonObj.toString();
        }
        else if (list != null && list.size() != 0) {
            JSONArray jsonarr = new JSONArray();
            jsonarr.addAll(list);
            res = jsonarr.toString();
        }
        outDomainJson(res, response, request);

    }


    /**
     * jquery 跨域调用
     * 
     * @param str
     */
    protected void outDomainJson(String str, HttpServletResponse response, HttpServletRequest request) {
        String jsoncallback = request.getParameter("jsoncallback");
        if (!StringUtils.isEmpty(jsoncallback)) {
            str = jsoncallback + "(" + str + ")";
        }
        out(str, response, request);
    }

    private static final String inj_str =
            "'@ and @exec@insert @select @delete @update @count@ * @%@chr@mid@master@truncate@char@declare@;@ or @lock table@grant@drop @ascii";


    /**
     * sql注入过滤处理
     * 
     * @param value
     * @return
     */
    private static String fiterSQL(String value) {
        value = value.toLowerCase();
        String inj_stra[] = inj_str.split("@");
        for (int i = 0; i < inj_stra.length; i++) {
            if (value.indexOf(inj_stra[i]) >= 0) {
                value = value.replace(inj_stra[i], "");
                break;
            }
        }
        return value;
    }


    /**
     * 脚本注入过滤处理
     * 
     * @param value
     * @return
     */
    public static String fiterScript(String value) {
        value = value.toLowerCase();
        value = value.replace("script", "");
        value = value.replace("<", "");
        value = value.replace(">", "");
        value = value.replace("%", "");
        value = value.replace("/", "");
        value = value.replace("\"", "");
        return value;
    }


    public String getJsonDate() {
        return jsonDate;
    }


    public void setJsonDate(String jsonDate) {
        this.jsonDate = jsonDate;
    }


    public String getIsJson() {
        return isJson;
    }


    public void setIsJson(String isJson) {
        this.isJson = isJson;
    }


    public String getJsoncallback() {
        return jsoncallback;
    }


    public void setJsoncallback(String jsoncallback) {
        this.jsoncallback = jsoncallback;
    }


    public String getParamFromCookie(String name, HttpServletRequest request) {
        return CookieUtil.getParamFromCookie(request, name);
    }


    public void setParamToSession(String key, Object value, HttpServletRequest request) {
        request.getSession().setAttribute(key, value);
    }


    public void setParamToRequest(String name, Object object, HttpServletRequest request) {
        request.setAttribute(name, object);
    }


    /**
     * 获取path.properties的中值 path.properties 中放各种配置url
     */
    public String getPath(String key, Object... values) {
        if (StringUtils.isBlank(key))
            return "";
        String proStr = path.getProperty(key);
        if (!StringUtils.isBlank(proStr) && values != null && values.length > 0) {
            int valuesLen = values.length;
            String[] valueStrs = new String[valuesLen];
            for (int i = 0; i < valuesLen; i++) {
                valueStrs[i] = String.valueOf(values[i]);
            }
            String path = MessageFormat.format(proStr, valueStrs);
            if (path.startsWith("http://")) {
                return path;
            }
            else {
                return "/" + path;
            }
        }
        return "/" + proStr;
    }


    /** 获取前端Urlrewriter后地址 */
    public String getFrontPathUrlrewriterBaseUrl() {
        String baseUrl = path.getProperty("frontPath_urlRewriter_baseUrl");
        return baseUrl;
    }


    /** 获取前端地址 */
    public String getFrontPathBaseUrl() {
        if ("true".equals(path.getProperty("isDev"))) {
            return path.getProperty("frontPath_dev");
        }
        else if ("false".equals(path.getProperty("isDev"))) {
            return path.getProperty("frontPath_baseUrl");
        }
        else {
            return path.getProperty("frontPath_online");
        }
    }


    /** 获取动态地址 */
    public String getDynamicPathBaseUrl() {
        if ("true".equals(path.getProperty("isDev"))) {
            return path.getProperty("dynamicPath_dev");
        }
        else if ("false".equals(path.getProperty("isDev"))) {
            return path.getProperty("dynamicPath_baseUrl");
        }
        else {
            return path.getProperty("dynamicPath_online");
        }

    }

   
}

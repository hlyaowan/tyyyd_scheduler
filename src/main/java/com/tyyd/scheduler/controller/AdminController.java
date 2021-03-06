package com.tyyd.scheduler.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tyyd.scheduler.model.JobInfo;
import com.tyyd.scheduler.model.UserInfo;
import com.tyyd.scheduler.quartz.service.SchedulerService;
import com.tyyd.scheduler.service.JobService;
import com.tyyd.scheduler.service.TriggerService;
import com.tyyd.scheduler.util.SessionUtil;

@Controller
public class AdminController {
    
    private Logger logger =Logger.getLogger(AdminController.class);

    @Autowired
    private JobService       jobService;

    @Autowired
    private TriggerService   triggerService;

    @Resource
    private SchedulerService schedulerService;

    public void setSchedulerService(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @RequestMapping("/test")
    public String test() {
        return "scrat";
    }

    @RequestMapping(value = { "/admin/manage.htm" })
    public String manage(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        UserInfo userInfo = SessionUtil.getUserInfo(request.getSession());
        if (userInfo != null) {
            model.addAttribute("userInfo", userInfo);
            String status = userInfo.getStatus();
            model.addAttribute("role", getSplitName(status));
        }
        return "manage";
    }

    @RequestMapping(value = { "/admin/config.htm" })
    public String config(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                         HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        UserInfo userInfo = SessionUtil.getUserInfo(request.getSession());

        if (userInfo != null) {
            List<Map<String, Object>> jobs = jobService.getJobInfoList(null);
            model.addAttribute("jobs", jobs);
            model.addAttribute("userInfo", userInfo);
        }
        return "config";
    }

    @RequestMapping(value = { "/admin/server_info.htm" })
    public String serverInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        UserInfo userInfo = SessionUtil.getUserInfo(request.getSession());

        if (userInfo != null) {
            Properties props = System.getProperties();
            String java_version = props.getProperty("java.version"); // Java的运行环境版本
            String java_io_tmpdir = props.getProperty("java.io.tmpdir"); // 默认的临时文件路径
            String os_name = props.getProperty("os.name"); // 操作系统的名称
            String os_arch = props.getProperty("os.arch"); // 操作系统的构架
            String os_version = props.getProperty("os.version"); // 操作系统的版本
            String file_separator = props.getProperty("file.separator"); // 文件分隔符
            String path_separator = props.getProperty("path.separator"); // 路径分隔符
            String line_separator = props.getProperty("line.separator"); // 行分隔符
            String os_user_name = props.getProperty("user.name"); // 用户的账户名称
            String os_user_home = props.getProperty("user.home"); // 用户的主目录
            String os_user_dir = props.getProperty("user.dir"); // 用户的当前工作目录
            int os_cpus = Runtime.getRuntime().availableProcessors(); // cpu信息
            int port =request.getRemotePort();
            String ip=request.getRemoteHost();
            String memory="空闲内存："+Runtime.getRuntime().freeMemory()+";总内存:"+Runtime.getRuntime().totalMemory()+",最大内存:"+Runtime.getRuntime().maxMemory()+", 已占用的内存:"+(Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory());
            model.addAttribute("java_version", java_version);
            model.addAttribute("java_io_tmpdir", java_io_tmpdir);
            model.addAttribute("os_name", os_name);
            model.addAttribute("os_arch", os_arch);
            model.addAttribute("os_version", os_version);
            model.addAttribute("file_separator", file_separator);
            model.addAttribute("path_separator", path_separator);
            model.addAttribute("line_separator", line_separator);
            model.addAttribute("os_user_name", os_user_name);
            model.addAttribute("os_user_dir", os_user_dir);
            model.addAttribute("os_user_home", os_user_home);
            model.addAttribute("os_cpus", os_cpus);
            model.addAttribute("userInfo", userInfo);
            model.addAttribute("port", port);
            model.addAttribute("ip", ip);
            model.addAttribute("memory", memory);
        }
        return "server_info";
    }

    @RequestMapping(value = { "/admin/scheduler.htm" })
    public String scheduler(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                            HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        UserInfo userInfo = SessionUtil.getUserInfo(request.getSession());

        if (userInfo != null) {
            List<Map<String, Object>> jobs = triggerService.getTriggerInfoList();
            model.addAttribute("jobs", jobs);
            model.addAttribute("userInfo", userInfo);
        }
        return "scheduler";
    }

    @RequestMapping(value = { "/admin/add_scheduler.htm" })
    public String add_scheduler(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        UserInfo userInfo = SessionUtil.getUserInfo(request.getSession());

        if (userInfo != null) {
            List<Map<String, Object>> jobs = triggerService.getTriggerInfoList();
            model.addAttribute("jobs", jobs);
            model.addAttribute("userInfo", userInfo);
        }
        return "add_scheduler";
    }

    @RequestMapping(value = { "/admin/add_trigger_group.htm" })
    public String add_trigger_group(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                    HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        UserInfo userInfo = SessionUtil.getUserInfo(request.getSession());

        if (userInfo != null) {
            model.addAttribute("userInfo", userInfo);
        }
        return "add_trigger_group";
    }

    @RequestMapping(value = { "/admin/add_group.htm" })
    public String add_group(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                            HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        UserInfo userInfo = SessionUtil.getUserInfo(request.getSession());

        if (userInfo != null) {

            // 获取界面以参数
            String triggerName = request.getParameter("triggerName");
            String cronExpression = request.getParameter("cronExpression");
            String groupName = request.getParameter("groupName");
            if (StringUtils.isEmpty(triggerName) || StringUtils.isEmpty(cronExpression)) {
                return "error";
            }
            // 添加任务调试
            schedulerService.schedule(triggerName, cronExpression, groupName);
            List<Map<String, Object>> jobs = triggerService.getTriggerInfoList();
            model.addAttribute("jobs", jobs);
            model.addAttribute("userInfo", userInfo);
        }
        return "scheduler";
    }

    @RequestMapping(value = { "/admin/addsch.htm" })
    public String addsch(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                         HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        UserInfo userInfo = SessionUtil.getUserInfo(request.getSession());

        if (userInfo != null) {

            // 获取界面以参数
            String triggerName = request.getParameter("triggerName");
            String cronExpression = request.getParameter("cronExpression");
            if (StringUtils.isEmpty(triggerName) || StringUtils.isEmpty(cronExpression)) {
                return "error";
            }
            // 添加任务调试
            schedulerService.schedule(triggerName, cronExpression);
            List<Map<String, Object>> jobs = triggerService.getTriggerInfoList();
            model.addAttribute("jobs", jobs);
            model.addAttribute("userInfo", userInfo);
        }
        return "scheduler";
    }

    @SuppressWarnings("deprecation")
    @RequestMapping(value = { "/admin/pause.json" })
    public String pause(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        UserInfo userInfo = SessionUtil.getUserInfo(request.getSession());
        String flag ="0";
        if (userInfo != null) {
            String triggerName = URLDecoder.decode(request.getParameter("triggerName"));
            String group = URLDecoder.decode(request.getParameter("group"));
            schedulerService.pauseTrigger(triggerName, group);
            flag="1";
        }
        model.addAttribute("flag",flag);
        return "json";
    }

    @RequestMapping(value = { "/admin/resume.json" })
    public String resume(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        UserInfo userInfo = SessionUtil.getUserInfo(request.getSession());
        String flag ="0";
        if (userInfo != null) {
            String triggerName = URLDecoder.decode(request.getParameter("triggerName"));
            String group = URLDecoder.decode(request.getParameter("group"));
            schedulerService.resumeTrigger(triggerName, group);
            flag="1";
        }
        model.addAttribute("flag",flag);
        return "json";
    }

    @RequestMapping(value = { "/admin/remove.json" })
    public String remove(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String flag ="0";
        UserInfo userInfo = SessionUtil.getUserInfo(request.getSession());
        if (userInfo != null) {
            String triggerName = URLDecoder.decode(request.getParameter("triggerName"));
            String group = URLDecoder.decode(request.getParameter("group"));
            schedulerService.removeTrigdger(triggerName, group);
            schedulerService.removeTrigdger(triggerName, group);
            flag="1";
        }
        model.addAttribute("flag",flag);
        return "json";
    }

    @RequestMapping(value = { "/admin/modifysch.htm" })
    public String modifysch(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        UserInfo userInfo = SessionUtil.getUserInfo(request.getSession());
        if (userInfo != null) {
            String triggerName = request.getParameter("triggerName");
            String group = request.getParameter("group");
            String cronExpression = request.getParameter("cronExpression");
            // schedulerService.modifyJobTime(triggerName, group, cronExpression);
            // schedulerService.modifyJobTime2(group,cronExpression);
            String temp = StringUtils.EMPTY;
            if (StringUtils.indexOf(triggerName, "&") != -1) {
                temp = StringUtils.substringBefore(triggerName, "&");
            } else {
                temp = triggerName;
            }
            schedulerService.removeTrigdger(triggerName, group);
            schedulerService.schedule(triggerName, cronExpression);
            List<Map<String, Object>> jobs = triggerService.getTriggerInfoList();
            model.addAttribute("jobs", jobs);
            model.addAttribute("userInfo", userInfo);
        }
        return "scheduler";
    }

    @RequestMapping(value = { "/admin/modify.htm" })
    public String modify(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        UserInfo userInfo = SessionUtil.getUserInfo(request.getSession());
        if (userInfo != null) {
            String triggerName = URLDecoder.decode(request.getParameter("triggerName"));
            String group = URLDecoder.decode(request.getParameter("group"));
            model.addAttribute("triggerName", triggerName);
            model.addAttribute("group", group);

            model.addAttribute("userInfo", userInfo);
        }
        return "modify";
    }

    @RequestMapping(value = { "/admin/operate.json" })
    public String operate(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        UserInfo userInfo = SessionUtil.getUserInfo(request.getSession());

        if (userInfo != null) {
            String action = request.getParameter("action");
            if (StringUtils.equals(action, "pause")) {
                return pause(request, response, model);
            } else if (StringUtils.equals(action, "resume")) {
                return resume(request, response, model);
            } else if (StringUtils.equals(action, "modify")) {
                return modify(request, response, model);
            } else if (StringUtils.equals(action, "remove")) {
                return remove(request, response, model);
            }
        }
        return null;
    }

    public String getSplitName(String name) {
        if (StringUtils.isEmpty(name)) {
            return StringUtils.EMPTY;
        }
        String[] splitNames = name.split(",");
        StringBuffer sBuffer = new StringBuffer();
        if (splitNames.length > 1) {
            int i = 0;
            for (String string : splitNames) {

                if (StringUtils.equals("1", string)) {
                    sBuffer.append("超级管理员");
                }
                if (StringUtils.equals("2", string)) {
                    sBuffer.append("一般管理员");
                }
                if (StringUtils.equals("3", string)) {
                    sBuffer.append("日志管理员");
                }
                if (i < splitNames.length - 1) {
                    sBuffer.append(",");
                }
                i++;
            }
        } else {

            if (StringUtils.equals("1", splitNames[0])) {
                sBuffer.append("超级管理员");
            }
            if (StringUtils.equals("2", splitNames[0])) {
                sBuffer.append("一般管理员");
            }
            if (StringUtils.equals("3", splitNames[0])) {
                sBuffer.append("日志管理员");
            }
        }

        return sBuffer.toString();
    }

    // / <summary>
    // / 计算总页数
    // / </summary>
    // / <param name="pageSize"></param>
    // / <param name="count"></param>
    // / <returns></returns>
    public static int GetPageCount(int pageSize, int count) {
        return count % pageSize != 0 ? count / pageSize + 1 : count / pageSize;
    }

    public static void main(String[] args) {
        AdminController controller = new AdminController();
        System.out.println(controller.getSplitName(""));
    }

    @RequestMapping(value = { "/admin/stop.htm" })
    public String stop(@RequestParam("status") String status, @RequestParam("id") String id,
                       HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        UserInfo userInfo = SessionUtil.getUserInfo(request.getSession());

        if (userInfo != null) {
            JobInfo job = new JobInfo();
            job.setId(Integer.parseInt(id));
            job.setFlag(Integer.parseInt(status));
            jobService.updateJobInfo(job);
            List<Map<String, Object>> jobs = triggerService.getTriggerInfoList();
            model.addAttribute("jobs", jobs);
            model.addAttribute("userInfo", userInfo);
        }
        return "scheduler";
    }
}

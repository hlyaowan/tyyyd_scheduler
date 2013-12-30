-- phpMyAdmin SQL Dump
-- version 4.0.1
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2013 年 12 月 30 日 11:04
-- 服务器版本: 5.1.51-community
-- PHP 版本: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `tyyd-scheduler`
--

-- --------------------------------------------------------

--
-- 表的结构 `job`
--

CREATE TABLE IF NOT EXISTS `job` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cpName` varchar(50) DEFAULT NULL,
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modifyTime` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `readTime` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `flag` int(11) DEFAULT '0',
  `timeExpress` varchar(50) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `extendsInfo` varchar(255) DEFAULT NULL,
  `channel` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- 转存表中的数据 `job`
--

INSERT INTO `job` (`id`, `cpName`, `createTime`, `modifyTime`, `readTime`, `flag`, `timeExpress`, `path`, `extendsInfo`, `channel`) VALUES
(1, '古羌', '2013-12-25 07:39:14', '2013-12-25 07:40:59', '2013-12-25 07:41:04', 1, '0/2 * 3-23 * * ?', '/home/taobao/datax/jobs/hongxiureader_to_hongxiuwriter_1387500928254.xml', NULL, 'guqiang'),
(2, '红袖', '2013-12-25 07:40:02', '2013-12-25 07:41:07', '2013-12-25 07:41:10', 1, '0/2 * 3-23 * * ?', '/home/taobao/datax/jobs/hongxiureader_to_hongxiuwriter_1387500928254.xml', NULL, 'hongxiu'),
(3, '北京网文', '2013-12-25 07:40:25', '2013-12-25 07:41:13', '2013-12-25 07:41:17', 1, '0/2 * 3-23 * * ?', '/home/taobao/datax/jobs/hongxiureader_to_hongxiuwriter_1387500928254.xml', NULL, 'netway'),
(4, '逐浪网', '2013-12-25 07:40:44', '2013-12-25 07:41:20', '2013-12-25 07:41:23', 1, '0/2 * 3-23 * * ?', '/home/taobao/datax/jobs/hongxiureader_to_hongxiuwriter_1387500928254.xml', NULL, 'zhulang');

-- --------------------------------------------------------

--
-- 表的结构 `qrtz_blob_triggers`
--

CREATE TABLE IF NOT EXISTS `qrtz_blob_triggers` (
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `TRIGGER_NAME` (`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- --------------------------------------------------------

--
-- 表的结构 `qrtz_calendars`
--

CREATE TABLE IF NOT EXISTS `qrtz_calendars` (
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- --------------------------------------------------------

--
-- 表的结构 `qrtz_cron_triggers`
--

CREATE TABLE IF NOT EXISTS `qrtz_cron_triggers` (
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `TRIGGER_NAME` (`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- 转存表中的数据 `qrtz_cron_triggers`
--

INSERT INTO `qrtz_cron_triggers` (`TRIGGER_NAME`, `TRIGGER_GROUP`, `CRON_EXPRESSION`, `TIME_ZONE_ID`) VALUES
('调度&03147d03-d35c-4164-a46e-d919f21aac01&059e22b4-f565-4934-9b65-8c6bf2b4681d', 'DEFAULT', '0/5 * * ? * * *', 'Asia/Shanghai');

-- --------------------------------------------------------

--
-- 表的结构 `qrtz_fired_triggers`
--

CREATE TABLE IF NOT EXISTS `qrtz_fired_triggers` (
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `IS_VOLATILE` varchar(1) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_STATEFUL` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- 转存表中的数据 `qrtz_fired_triggers`
--

INSERT INTO `qrtz_fired_triggers` (`ENTRY_ID`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `IS_VOLATILE`, `INSTANCE_NAME`, `FIRED_TIME`, `PRIORITY`, `STATE`, `JOB_NAME`, `JOB_GROUP`, `IS_STATEFUL`, `REQUESTS_RECOVERY`) VALUES
('NON_CLUSTERED1388298531493', '调度&03147d03-d35c-4164-a46e-d919f21aac01&059e22b4-f565-4934-9b65-8c6bf2b4681d', 'DEFAULT', '0', 'NON_CLUSTERED', 1388317235000, 5, 'ACQUIRED', NULL, NULL, '0', '0');

-- --------------------------------------------------------

--
-- 表的结构 `qrtz_job_details`
--

CREATE TABLE IF NOT EXISTS `qrtz_job_details` (
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_VOLATILE` varchar(1) NOT NULL,
  `IS_STATEFUL` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- 转存表中的数据 `qrtz_job_details`
--

INSERT INTO `qrtz_job_details` (`JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `JOB_CLASS_NAME`, `IS_DURABLE`, `IS_VOLATILE`, `IS_STATEFUL`, `REQUESTS_RECOVERY`, `JOB_DATA`) VALUES
('jobDetail', 'DEFAULT', NULL, 'com.tyyd.scheduler.quartz.target.MyQuartzJobBean', '0', '0', '0', '0', 0xaced0005737200156f72672e71756172747a2e4a6f62446174614d61709fb083e8bfa9b0cb020000787200266f72672e71756172747a2e7574696c732e537472696e674b65794469727479466c61674d61708208e8c3fbc55d280200015a0013616c6c6f77735472616e7369656e74446174617872001d6f72672e71756172747a2e7574696c732e4469727479466c61674d617013e62ead28760ace0200025a000564697274794c00036d617074000f4c6a6176612f7574696c2f4d61703b787001737200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c6478703f4000000000000c7708000000100000000174000d73696d706c65536572766963657372002e636f6d2e747979642e7363686564756c65722e71756172747a2e7461726765742e53696d706c655365727669636501b29455214e490702000078707800);

-- --------------------------------------------------------

--
-- 表的结构 `qrtz_job_listeners`
--

CREATE TABLE IF NOT EXISTS `qrtz_job_listeners` (
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `JOB_LISTENER` varchar(200) NOT NULL,
  PRIMARY KEY (`JOB_NAME`,`JOB_GROUP`,`JOB_LISTENER`),
  KEY `JOB_NAME` (`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- --------------------------------------------------------

--
-- 表的结构 `qrtz_locks`
--

CREATE TABLE IF NOT EXISTS `qrtz_locks` (
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- 转存表中的数据 `qrtz_locks`
--

INSERT INTO `qrtz_locks` (`LOCK_NAME`) VALUES
('CALENDAR_ACCESS'),
('JOB_ACCESS'),
('MISFIRE_ACCESS'),
('STATE_ACCESS'),
('TRIGGER_ACCESS');

-- --------------------------------------------------------

--
-- 表的结构 `qrtz_paused_trigger_grps`
--

CREATE TABLE IF NOT EXISTS `qrtz_paused_trigger_grps` (
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- --------------------------------------------------------

--
-- 表的结构 `qrtz_scheduler_state`
--

CREATE TABLE IF NOT EXISTS `qrtz_scheduler_state` (
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- --------------------------------------------------------

--
-- 表的结构 `qrtz_simple_triggers`
--

CREATE TABLE IF NOT EXISTS `qrtz_simple_triggers` (
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `TRIGGER_NAME` (`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- --------------------------------------------------------

--
-- 表的结构 `qrtz_triggers`
--

CREATE TABLE IF NOT EXISTS `qrtz_triggers` (
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `IS_VOLATILE` varchar(1) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `JOB_NAME` (`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- 转存表中的数据 `qrtz_triggers`
--

INSERT INTO `qrtz_triggers` (`TRIGGER_NAME`, `TRIGGER_GROUP`, `JOB_NAME`, `JOB_GROUP`, `IS_VOLATILE`, `DESCRIPTION`, `NEXT_FIRE_TIME`, `PREV_FIRE_TIME`, `PRIORITY`, `TRIGGER_STATE`, `TRIGGER_TYPE`, `START_TIME`, `END_TIME`, `CALENDAR_NAME`, `MISFIRE_INSTR`, `JOB_DATA`) VALUES
('调度&03147d03-d35c-4164-a46e-d919f21aac01&059e22b4-f565-4934-9b65-8c6bf2b4681d', 'DEFAULT', 'jobDetail', 'DEFAULT', '0', NULL, 1388317235000, 1388317230000, 5, 'ACQUIRED', 'CRON', 1388315294000, 0, NULL, 0, '');

-- --------------------------------------------------------

--
-- 表的结构 `qrtz_trigger_listeners`
--

CREATE TABLE IF NOT EXISTS `qrtz_trigger_listeners` (
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `TRIGGER_LISTENER` varchar(200) NOT NULL,
  PRIMARY KEY (`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_LISTENER`),
  KEY `TRIGGER_NAME` (`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- --------------------------------------------------------

--
-- 表的结构 `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `status` tinyint(8) NOT NULL DEFAULT '0' COMMENT '状态, 0 为普通用户, 1 为管理员',
  `username` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  `userpwd` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_2` (`username`),
  KEY `username` (`username`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='用户帐号表' AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `users`
--

INSERT INTO `users` (`id`, `status`, `username`, `userpwd`) VALUES
(1, 1, 'hlyaowan', 'e9450c6452c501594ec6d84de9751e94'),
(2, 1, 'tom', 'e9450c6452c501594ec6d84de9751e94');

--
-- 限制导出的表
--

--
-- 限制表 `qrtz_blob_triggers`
--
ALTER TABLE `qrtz_blob_triggers`
  ADD CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`TRIGGER_NAME`, `TRIGGER_GROUP`);

--
-- 限制表 `qrtz_cron_triggers`
--
ALTER TABLE `qrtz_cron_triggers`
  ADD CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`TRIGGER_NAME`, `TRIGGER_GROUP`);

--
-- 限制表 `qrtz_job_listeners`
--
ALTER TABLE `qrtz_job_listeners`
  ADD CONSTRAINT `qrtz_job_listeners_ibfk_1` FOREIGN KEY (`JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`JOB_NAME`, `JOB_GROUP`);

--
-- 限制表 `qrtz_simple_triggers`
--
ALTER TABLE `qrtz_simple_triggers`
  ADD CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`TRIGGER_NAME`, `TRIGGER_GROUP`);

--
-- 限制表 `qrtz_triggers`
--
ALTER TABLE `qrtz_triggers`
  ADD CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`JOB_NAME`, `JOB_GROUP`);

--
-- 限制表 `qrtz_trigger_listeners`
--
ALTER TABLE `qrtz_trigger_listeners`
  ADD CONSTRAINT `qrtz_trigger_listeners_ibfk_1` FOREIGN KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`TRIGGER_NAME`, `TRIGGER_GROUP`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

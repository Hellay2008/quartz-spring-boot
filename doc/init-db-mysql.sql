/*
Navicat MySQL Data Transfer

Source Server         : localhost-mysql
Source Server Version : 80022
Source Host           : localhost:3306
Source Database       : quartz

Target Server Type    : MYSQL
Target Server Version : 80022
File Encoding         : 65001

Date: 2021-09-01 11:29:54
*/

-- ---------------quartz专用表--start---------

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`
(
    `SCHED_NAME`    varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `TRIGGER_NAME`  varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `BLOB_DATA`     blob,
    PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`),
    CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`
(
    `SCHED_NAME`    varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `CALENDAR_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `CALENDAR`      blob                                                   NOT NULL,
    PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`
(
    `SCHED_NAME`      varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `TRIGGER_NAME`    varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `TRIGGER_GROUP`   varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `CRON_EXPRESSION` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `TIME_ZONE_ID`    varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
    PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`),
    CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers`
VALUES ('TEST_JOB_SCHEDULER', 'com.sample.job.SampleJob1', 'default', '0 0/1 * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`
(
    `SCHED_NAME`        varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `ENTRY_ID`          varchar(95) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL,
    `TRIGGER_NAME`      varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `TRIGGER_GROUP`     varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `INSTANCE_NAME`     varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `FIRED_TIME`        bigint                                                 NOT NULL,
    `SCHED_TIME`        bigint                                                 NOT NULL,
    `PRIORITY`          int                                                    NOT NULL,
    `STATE`             varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL,
    `JOB_NAME`          varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
    `JOB_GROUP`         varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
    `IS_NONCONCURRENT`  varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin   DEFAULT NULL,
    `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin   DEFAULT NULL,
    PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`
(
    `SCHED_NAME`        varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `JOB_NAME`          varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `JOB_GROUP`         varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `DESCRIPTION`       varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
    `JOB_CLASS_NAME`    varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `IS_DURABLE`        varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin   NOT NULL,
    `IS_NONCONCURRENT`  varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin   NOT NULL,
    `IS_UPDATE_DATA`    varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin   NOT NULL,
    `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin   NOT NULL,
    `JOB_DATA`          blob,
    PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details`
VALUES ('TEST_JOB_SCHEDULER', 'com.sample.job.SampleJob1', 'default', '测试定时任务SampleJob1',
        'com.sample.job.SampleJob1', '0', '1', '0', '0',
        0x230D0A23467269204665622030352031353A32343A33362043535420323032310D0A);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`
(
    `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `LOCK_NAME`  varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL,
    PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks`
VALUES ('TEST_JOB_SCHEDULER', 'STATE_ACCESS');
INSERT INTO `qrtz_locks`
VALUES ('TEST_JOB_SCHEDULER', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`
(
    `SCHED_NAME`    varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`
(
    `SCHED_NAME`        varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `INSTANCE_NAME`     varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `LAST_CHECKIN_TIME` bigint                                                 NOT NULL,
    `CHECKIN_INTERVAL`  bigint                                                 NOT NULL,
    PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state`
VALUES ('TEST_JOB_SCHEDULER', 'CW-A2005060041630465587609', '1630466987687', '20000');

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`
(
    `SCHED_NAME`      varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `TRIGGER_NAME`    varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `TRIGGER_GROUP`   varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `REPEAT_COUNT`    bigint                                                 NOT NULL,
    `REPEAT_INTERVAL` bigint                                                 NOT NULL,
    `TIMES_TRIGGERED` bigint                                                 NOT NULL,
    PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`),
    CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`
(
    `SCHED_NAME`    varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `TRIGGER_NAME`  varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `STR_PROP_1`    varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
    `STR_PROP_2`    varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
    `STR_PROP_3`    varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
    `INT_PROP_1`    int                                                    DEFAULT NULL,
    `INT_PROP_2`    int                                                    DEFAULT NULL,
    `LONG_PROP_1`   bigint                                                 DEFAULT NULL,
    `LONG_PROP_2`   bigint                                                 DEFAULT NULL,
    `DEC_PROP_1`    decimal(13, 4)                                         DEFAULT NULL,
    `DEC_PROP_2`    decimal(13, 4)                                         DEFAULT NULL,
    `BOOL_PROP_1`   varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin   DEFAULT NULL,
    `BOOL_PROP_2`   varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin   DEFAULT NULL,
    PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`),
    CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`
(
    `SCHED_NAME`     varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `TRIGGER_NAME`   varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `TRIGGER_GROUP`  varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `JOB_NAME`       varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `JOB_GROUP`      varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `DESCRIPTION`    varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
    `NEXT_FIRE_TIME` bigint                                                 DEFAULT NULL,
    `PREV_FIRE_TIME` bigint                                                 DEFAULT NULL,
    `PRIORITY`       int                                                    DEFAULT NULL,
    `TRIGGER_STATE`  varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL,
    `TRIGGER_TYPE`   varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin   NOT NULL,
    `START_TIME`     bigint                                                 NOT NULL,
    `END_TIME`       bigint                                                 DEFAULT NULL,
    `CALENDAR_NAME`  varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
    `MISFIRE_INSTR`  smallint                                               DEFAULT NULL,
    `JOB_DATA`       blob,
    PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`),
    KEY `SCHED_NAME` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
    CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers`
VALUES ('TEST_JOB_SCHEDULER', 'com.sample.job.SampleJob1', 'default', 'com.sample.job.SampleJob1', 'default',
        '2021-02-05 15:24:36', '1630467000000', '1630466940000', '5', 'ACQUIRED', 'CRON', '1612509876000',
        '0', null, '2', '');

-- ----------------------------
-- View structure for qrtz_job_detail_v
-- ----------------------------
DROP VIEW IF EXISTS `qrtz_job_detail_v`;
CREATE ALGORITHM = UNDEFINED DEFINER =`root`@`%` SQL SECURITY DEFINER VIEW `qrtz_job_detail_v` AS
select `qjb`.`JOB_NAME`        AS `job_name`,
       `qjb`.`JOB_GROUP`       AS `job_group`,
       `qjb`.`DESCRIPTION`     AS `description`,
       `qt`.`TRIGGER_STATE`    AS `trigger_state`,
       `qjb`.`JOB_CLASS_NAME`  AS `job_class_name`,
       `qt`.`TRIGGER_NAME`     AS `trigger_name`,
       `qt`.`TRIGGER_GROUP`    AS `trigger_group`,
       `qct`.`CRON_EXPRESSION` AS `cron_expression`,
       `qt`.`PREV_FIRE_TIME`   AS `prev_fire_time`,
       `qt`.`NEXT_FIRE_TIME`   AS `next_fire_time`,
       `qct`.`TIME_ZONE_ID`    AS `time_zone_id`
from ((`qrtz_job_details` `qjb` join `qrtz_triggers` `qt`)
         join `qrtz_cron_triggers` `qct`)
where ((`qjb`.`JOB_NAME` = `qt`.`JOB_NAME`) and (`qt`.`TRIGGER_NAME` = `qct`.`TRIGGER_NAME`) and
       (`qt`.`TRIGGER_GROUP` = `qct`.`TRIGGER_GROUP`));
-- ---------------quartz专用表--end---------


-- ---------------自定义表--start---------
-- ----------------------------
-- Table structure for qrtz_job_log
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_log`;
CREATE TABLE `qrtz_job_log`
(
    `id`           bigint    NOT NULL AUTO_INCREMENT,
    `job_id`       varchar(64)    DEFAULT NULL COMMENT ' bean name ',
    `job_desc`     varchar(255)   DEFAULT NULL COMMENT ' job描述 ',
    `execute_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    `status`       varchar(32)    DEFAULT NULL,
    `message`      varchar(2000)  DEFAULT NULL,
    `create_time`  timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    `create_by`    varchar(32)    DEFAULT NULL,
    `seq_id`       bigint         DEFAULT NULL,
    `step`         bigint         DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `bean_name_idx` (`job_id`) USING BTREE,
    KEY `job_desc_idx` (`job_desc`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1432908322031984643
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_job_log
-- ----------------------------
INSERT INTO `qrtz_job_log`
VALUES ('1432908319515402241', 'com.sample.job.SampleJob1', '测试定时任务SampleJob1', '2021-09-01 03:29:00', '0',
        'com.sample.job.SampleJob1--测试定时任务SampleJob1---定时任务开始', '2021-09-01 03:29:00', null, '9790', '1');
INSERT INTO `qrtz_job_log`
VALUES ('1432908319729311746', 'com.sample.job.SampleJob1', '测试定时任务SampleJob1', '2021-09-01 03:29:00', '0',
        'com.sample.job.SampleJob1--测试定时任务SampleJob1---定时任务-2-步骤1开始', '2021-09-01 03:29:00', null, '9790', '2');
INSERT INTO `qrtz_job_log`
VALUES ('1432908320039690241', 'com.sample.job.SampleJob1', '测试定时任务SampleJob1', '2021-09-01 03:29:00', '0',
        'com.sample.job.SampleJob1--测试定时任务SampleJob1---定时任务-2-步骤1执行中', '2021-09-01 03:29:00', null, '9790',
        '3');
INSERT INTO `qrtz_job_log`
VALUES ('1432908320387817474', 'com.sample.job.SampleJob1', '测试定时任务SampleJob1', '2021-09-01 03:29:00', '0',
        'com.sample.job.SampleJob1--测试定时任务SampleJob1---定时任务-2-步骤1完成', '2021-09-01 03:29:00', null, '9790', '4');
INSERT INTO `qrtz_job_log`
VALUES ('1432908320815636482', 'com.sample.job.SampleJob1', '测试定时任务SampleJob1', '2021-09-01 03:29:00', '0',
        'com.sample.job.SampleJob1--测试定时任务SampleJob1---定时任务-2-步骤2开始', '2021-09-01 03:29:00', null, '9790', '5');
INSERT INTO `qrtz_job_log`
VALUES ('1432908321230872577', 'com.sample.job.SampleJob1', '测试定时任务SampleJob1', '2021-09-01 03:29:01', '0',
        'com.sample.job.SampleJob1--测试定时任务SampleJob1---定时任务-2-步骤2执行中', '2021-09-01 03:29:01', null, '9790',
        '6');
INSERT INTO `qrtz_job_log`
VALUES ('1432908321440587777', 'com.sample.job.SampleJob1', '测试定时任务SampleJob1', '2021-09-01 03:29:01', '0',
        'com.sample.job.SampleJob1--测试定时任务SampleJob1---定时任务-2-步骤2完成', '2021-09-01 03:29:01', null, '9790', '7');
INSERT INTO `qrtz_job_log`
VALUES ('1432908322031984642', 'com.sample.job.SampleJob1', '测试定时任务SampleJob1', '2021-09-01 03:29:01', '1',
        'com.sample.job.SampleJob1--测试定时任务SampleJob1---java.lang.ArithmeticException: / by zero',
        '2021-09-01 03:29:01', null, '9790', '8');

-- ----------------------------
-- Table structure for qrtz_job_log_seq
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_log_seq`;
CREATE TABLE `qrtz_job_log_seq`
(
    `id`          bigint    NOT NULL AUTO_INCREMENT,
    `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 9791
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_job_log_seq
-- ----------------------------
INSERT INTO `qrtz_job_log_seq`
VALUES ('9784', '2021-09-01 03:23:00');
INSERT INTO `qrtz_job_log_seq`
VALUES ('9785', '2021-09-01 03:24:00');
INSERT INTO `qrtz_job_log_seq`
VALUES ('9786', '2021-09-01 03:25:00');
INSERT INTO `qrtz_job_log_seq`
VALUES ('9787', '2021-09-01 03:26:00');
INSERT INTO `qrtz_job_log_seq`
VALUES ('9788', '2021-09-01 03:27:00');
INSERT INTO `qrtz_job_log_seq`
VALUES ('9789', '2021-09-01 03:28:00');
INSERT INTO `qrtz_job_log_seq`
VALUES ('9790', '2021-09-01 03:29:00');
-- ---------------自定义表--end---------
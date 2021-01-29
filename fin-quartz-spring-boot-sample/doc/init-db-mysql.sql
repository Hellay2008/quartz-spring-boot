CREATE TABLE `qrtz_job_log_seq`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


CREATE TABLE `qrtz_job_log`
(
    `id`           bigint NOT NULL AUTO_INCREMENT,
    `job_id`       varchar(64)   DEFAULT NULL COMMENT 'bean name',
    `job_desc`     varchar(255)  DEFAULT NULL COMMENT 'job描述',
    `execute_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    `status`       varchar(32)   DEFAULT NULL,
    `message`      varchar(2000) DEFAULT NULL,
    `create_time`  timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    `create_by`    varchar(32)   DEFAULT NULL,
    `seq_id`       bigint        DEFAULT NULL,
    `step`         bigint        DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY            `bean_name_idx` (`job_id`) USING BTREE,
    KEY            `job_desc_idx` (`job_desc`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
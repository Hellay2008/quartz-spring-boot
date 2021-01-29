package com.dji.quartz.boot.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("qrtz_job_detail_v")
public class JobDetailV {

    private static final long serialVersionUID = 1L;

    private String jobName;

    private String jobGroup;

    private String description;

    private String triggerState;

    private String jobClassName;

    private String triggerName;

    private String triggerGroup;

    private String cronExpression;

    private Long prevFireTime;

    private Long nextFireTime;

    private String timeZoneId;

}

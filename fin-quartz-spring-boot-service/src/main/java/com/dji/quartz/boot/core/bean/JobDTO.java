package com.dji.quartz.boot.core.bean;

import lombok.Data;

@Data
public class JobDTO {
    String jobName;
    private String jobGroup;
    private String cronExpression;
    private String jobDescription;
}

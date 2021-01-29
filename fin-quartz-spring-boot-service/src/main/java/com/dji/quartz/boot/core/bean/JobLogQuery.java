package com.dji.quartz.boot.core.bean;

import lombok.Data;

import java.util.Date;

@Data
public class JobLogQuery {
    private String jobName;
    private String jobDesc;
    private String status;
    private String message;
    private Date executeTimeStart;
    private Date executeTimeEnd;
}

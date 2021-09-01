package com.will.quartz.boot.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@TableName("qrtz_job_log")
public class JobLogEntity implements Serializable {
    private Long id;
    private String jobId;
    private String jobDesc;
    private Long seqId;
    private Long step;
    private Date executeTime;
    private String status;
    private String message;
    private String createBy;
    private Date createTime;
}

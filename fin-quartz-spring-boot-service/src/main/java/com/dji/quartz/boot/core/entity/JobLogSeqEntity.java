package com.dji.quartz.boot.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("qrtz_job_log_seq")
public class JobLogSeqEntity {
    @TableId(type=IdType.AUTO)
    private Long id;
    private Date createTime;
}

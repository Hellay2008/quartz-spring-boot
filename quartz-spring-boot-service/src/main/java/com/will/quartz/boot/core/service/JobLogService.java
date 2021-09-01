package com.will.quartz.boot.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.will.quartz.boot.core.bean.JobHead;
import com.will.quartz.boot.core.bean.JobLogQuery;
import com.will.quartz.boot.core.entity.JobLogEntity;

public interface JobLogService {
    ThreadLocal<JobHead> threadLocalJobHead = new ThreadLocal<>();
    void log(String status, String message);
    void log(boolean reset, String jobName, String status, String message);
    Page<JobLogEntity> queryLogList(JobLogQuery jobLogQuery, int pageNo, int pageSize);
    void resetLogHead(String jobName);
}

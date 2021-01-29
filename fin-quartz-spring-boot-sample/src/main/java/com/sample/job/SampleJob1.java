package com.sample.job;

import com.dji.quartz.boot.core.annotation.QuartzLog;
import com.dji.quartz.boot.core.enums.LogStatusEnum;
import com.dji.quartz.boot.core.service.JobLogService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@DisallowConcurrentExecution
public class SampleJob1 implements Job {

    @Autowired
    JobLogService jobLogService;

    @Override
    @QuartzLog(jobDesc = "测试定时任务X", targetClass = SampleJob1.class)
    public void execute(JobExecutionContext context) throws JobExecutionException {

        jobLogService.log(LogStatusEnum.NORMAL.getCode(), "定时任务-2-步骤1开始");

        jobLogService.log(LogStatusEnum.NORMAL.getCode(), "定时任务-2-步骤1执行中");

        jobLogService.log(LogStatusEnum.NORMAL.getCode(), "定时任务-2-步骤1完成");

        jobLogService.log(LogStatusEnum.NORMAL.getCode(), "定时任务-2-步骤2开始");

        jobLogService.log(LogStatusEnum.NORMAL.getCode(), "定时任务-2-步骤2执行中");

        jobLogService.log(LogStatusEnum.NORMAL.getCode(), "定时任务-2-步骤2完成");
        int x = 1;
        int y = 0;
        x = x/y;

        jobLogService.log(LogStatusEnum.NORMAL.getCode(), "作定时任-2-务完成");
    }
}
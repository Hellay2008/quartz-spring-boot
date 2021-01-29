package com.sample.job;

import com.dji.quartz.boot.core.annotation.QuartzLog;
import com.sample.service.SampleService;
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
public class SampleJob2 implements Job {

    @Autowired
    SampleService sampleService;

    @Override
    @QuartzLog(jobDesc = "测试定时任务YY", targetClass = SampleJob2.class)
    public void execute(JobExecutionContext context) throws JobExecutionException {
        sampleService.sampleMethod();
    }
}
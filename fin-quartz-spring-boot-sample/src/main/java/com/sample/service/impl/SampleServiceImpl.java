package com.sample.service.impl;

import com.dji.quartz.boot.core.enums.LogStatusEnum;
import com.dji.quartz.boot.core.service.JobLogService;
import com.sample.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {
    @Autowired
    JobLogService jobLogService;

    @Override
    public void sampleMethod() {

        jobLogService.log(LogStatusEnum.NORMAL.getCode(), "同步数据开始");

        jobLogService.log(LogStatusEnum.NORMAL.getCode(),  "请求网关接口开始");

        jobLogService.log(LogStatusEnum.NORMAL.getCode(),  "请求网关结束");

        jobLogService.log(LogStatusEnum.NORMAL.getCode(),  "接收到数据10000条");

        jobLogService.log(LogStatusEnum.NORMAL.getCode(),  "保存数据开始");

        jobLogService.log(LogStatusEnum.NORMAL.getCode(),  "保存数据结束，报数数据10000条");

        jobLogService.log(LogStatusEnum.NORMAL.getCode(), "同步数据结束");
    }
}

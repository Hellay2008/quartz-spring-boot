package com.will.quartz.boot.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.will.quartz.boot.core.bean.JobHead;
import com.will.quartz.boot.core.bean.JobLogQuery;
import com.will.quartz.boot.core.entity.JobDetailV;
import com.will.quartz.boot.core.entity.JobLogEntity;
import com.will.quartz.boot.core.mapper.JobLogMapper;
import com.will.quartz.boot.core.service.JobLogSeqService;
import com.will.quartz.boot.core.service.JobLogService;
import com.will.quartz.boot.core.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
public class JobLogServiceImpl implements JobLogService {

    @Autowired
    JobLogMapper jobLogMapper;
    @Autowired
    JobLogSeqService jobLogSeqService;
    @Autowired
    JobService jobService;

    @Override
    public void resetLogHead(String jobName) {
        JobDetailV jobInfo = jobService.getJobInfo(jobName);
        threadLocalJobHead.set(JobHead.builder()
                .jobName(jobName)
                .jobSeqId(jobLogSeqService.getSeqId())
                .jobDesc(Optional.ofNullable(jobInfo).orElse(new JobDetailV()).getDescription())
                .step(1L)
                .build());
    }

    @Override
    public void log(String status, String message) {
        if(threadLocalJobHead.get() == null){
            log.error("定时任务日志-上下文未初始化");
            return ;
        }
        JobLogEntity jobLog = JobLogEntity.builder()
                .jobId(threadLocalJobHead.get().getJobName())
                .seqId(threadLocalJobHead.get().getJobSeqId())
                .jobDesc(threadLocalJobHead.get().getJobDesc())
                .step(threadLocalJobHead.get().getAndIncreaseStep())
                .status(status)
                .message(String.format("%s--%s---%s", threadLocalJobHead.get().getJobName(), threadLocalJobHead.get().getJobDesc(), message))
                .executeTime(new Date())
                .createTime(new Date())
                .build();
        jobLogMapper.insert(jobLog);
    }

    @Override
    public void log(boolean reset, String jobName, String status, String message) {
        if(reset) {
            this.resetLogHead(jobName);
        }
        this.log(status, message);
    }

    @Override
    public Page<JobLogEntity> queryLogList(JobLogQuery jobLogQuery, int pageNo, int pageSize) {

        LambdaQueryWrapper<JobLogEntity> wrapper = new QueryWrapper<JobLogEntity>().lambda();
        wrapper.orderByDesc(JobLogEntity::getSeqId);
        wrapper.orderByAsc(JobLogEntity::getStep);
        if(jobLogQuery.getJobName() != null && !"".equals(jobLogQuery.getJobName())){
            wrapper.likeRight(JobLogEntity::getJobId, jobLogQuery.getJobName());
        }
        if(jobLogQuery.getJobDesc() != null && !"".equals(jobLogQuery.getJobDesc())){
            wrapper.likeRight(JobLogEntity::getJobDesc, jobLogQuery.getJobDesc());
        }
        if(jobLogQuery.getStatus() != null && !"".equals(jobLogQuery.getStatus())){
            wrapper.eq(JobLogEntity::getStatus, jobLogQuery.getStatus());
        }
        if(jobLogQuery.getMessage() != null && !"".equals(jobLogQuery.getMessage())){
            wrapper.like(JobLogEntity::getMessage, jobLogQuery.getMessage());
        }
        if(jobLogQuery.getExecuteTimeStart() != null){
            wrapper.ge(JobLogEntity::getExecuteTime, jobLogQuery.getExecuteTimeStart());
        }
        if(jobLogQuery.getExecuteTimeEnd() != null){
            wrapper.le(JobLogEntity::getExecuteTime, jobLogQuery.getExecuteTimeEnd());
        }
        Page<JobLogEntity> page = new Page<>(pageNo, pageSize);
        return jobLogMapper.selectPage(page, wrapper);
    }
}

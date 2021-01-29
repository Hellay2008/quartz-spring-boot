package com.dji.quartz.boot.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dji.quartz.boot.core.bean.JobQuery;
import com.dji.quartz.boot.core.entity.JobDetailV;
import com.dji.quartz.boot.core.mapper.JobDetailVMapper;
import com.dji.quartz.boot.core.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

@Slf4j
@Service
public class JobServiceImpl implements JobService {

    public static final String JOB_PARAM="params";

	@Autowired(required = false)
    private Scheduler scheduler;
	@Autowired
	private JobDetailVMapper jobDetailVMapper;

    /**
     * 分页查询
     */
	@Override
    public Page<JobDetailV> list(JobQuery jobQuery, int pageNo, int pageSize){
        LambdaQueryWrapper<JobDetailV> wrapper = new QueryWrapper<JobDetailV>().lambda();
        wrapper.orderByDesc(JobDetailV::getJobName);
        if(jobQuery.getJobName() != null && !"".equals(jobQuery.getJobName())){
            wrapper.eq(JobDetailV::getJobName, jobQuery.getJobName());
        }
        Page<JobDetailV> page = new Page<>(pageNo, pageSize);
        return jobDetailVMapper.selectPage(page, wrapper);
    }

    /**
     * 添加
     *
     * @param jobName
     * @param jobGroup
     * @param cronExpression
     * @param jobDescription
     */
	@SuppressWarnings("unchecked")
	@Override
    public void addJob(String jobName, String jobGroup, String cronExpression, String jobDescription) {
        if (StringUtils.isAnyBlank(jobName, jobGroup, cronExpression, jobDescription)) {
            throw new RuntimeException(String.format("参数错误, jobName=%s,jobGroup=%s,cronExpression=%s,jobDescription=%s", jobName, jobGroup, cronExpression, jobDescription));
        }
        String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        try {
            log.info("添加jobName={},jobGroup={},cronExpression={},jobDescription={}", jobName, jobGroup, cronExpression, jobDescription);

            if (checkExists(jobName, jobGroup)) {
                log.error("Job已经存在, jobName={},jobGroup={}", jobName, jobGroup);
                throw new RuntimeException(String.format("Job已经存在, jobName=%s,jobGroup=%s", jobName, jobGroup));
            }

            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);

            CronScheduleBuilder schedBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withDescription(createTime).withSchedule(schedBuilder).build();

            Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(jobName);
            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(jobKey).withDescription(jobDescription).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException | ClassNotFoundException e) {
            log.error("添加job失败, jobName={},jobGroup={},e={}", jobName, jobGroup, e);
            throw new RuntimeException("类名不存在或执行表达式错误");
        }
    }

    /**
     * 修改
     *
     * @param jobName
     * @param jobGroup
     * @param cronExpression
     * @param jobDescription
     */
	@Override
    public void edit(String jobName, String jobGroup, String cronExpression, String jobDescription) {
        if (StringUtils.isAnyBlank(jobName, jobGroup, cronExpression, jobDescription)) {
            throw new RuntimeException(String.format("参数错误, jobName=%s,jobGroup=%s,cronExpression=%s,jobDescription=%s", jobName, jobGroup, cronExpression, jobDescription));
        }
        String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        try {
            log.info("修改jobName={},jobGroup={},cronExpression={},jobDescription={}", jobName, jobGroup, cronExpression, jobDescription);
            if (!checkExists(jobName, jobGroup)) {
                log.error("Job不存在, jobName={},jobGroup={}", jobName, jobGroup);
                throw new RuntimeException(String.format("Job不存在, jobName=%s,jobGroup=%s", jobName, jobGroup));
            }
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            JobKey jobKey = new JobKey(jobName, jobGroup);
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withDescription(createTime).withSchedule(cronScheduleBuilder).build();

            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            jobDetail.getJobBuilder().withDescription(jobDescription);
            HashSet<Trigger> triggerSet = new HashSet<>();
            triggerSet.add(cronTrigger);

            scheduler.scheduleJob(jobDetail, triggerSet, true);
        } catch (SchedulerException e) {
            log.error("修改job失败, jobName={},jobGroup={},e={}", jobName, jobGroup, e);
//            throw new RuntimeException("类名不存在或执行表达式错误");
            throw new RuntimeException("{{MSG:M10}}");
        }
    }

    /**
     * 删除
     *
     * @param jobName
     * @param jobGroup
     */
	@Override
    public void delete(String jobName, String jobGroup) {
        try {
            log.info("删除jobName={},jobGroup={}", jobName, jobGroup);
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            if (checkExists(jobName, jobGroup)) {
                scheduler.pauseTrigger(triggerKey);
                scheduler.unscheduleJob(triggerKey);
            }
        } catch (SchedulerException e) {
            log.error("删除job失败, jobName={},jobGroup={},e={}", jobName, jobGroup, e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 暂停
     *
     * @param jobName
     * @param jobGroup
     */
	@Override
    public void pause(String jobName, String jobGroup) {
        try {
            log.info("暂停jobName={},jobGroup={}", jobName, jobGroup);
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            if (!checkExists(jobName, jobGroup)) {
                log.error("Job不存在, jobName={},jobGroup={}", jobName, jobGroup);
                throw new RuntimeException(String.format("Job不存在, jobName=%s,jobGroup=%s", jobName, jobGroup));
            }
            scheduler.pauseTrigger(triggerKey);
        } catch (SchedulerException e) {
            log.error("暂停job失败, jobName={},jobGroup={},e={}", jobName, jobGroup, e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 重启
     *
     * @param jobName
     * @param jobGroup
     */
	@Override
    public void resume(String jobName, String jobGroup) {
        try {
            log.info("重启jobName={},jobGroup={}", jobName, jobGroup);
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            if (!checkExists(jobName, jobGroup)) {
                log.error("Job不存在, jobName={},jobGroup={}", jobName, jobGroup);
                throw new RuntimeException(String.format("Job不存在, jobName=%s,jobGroup=%s", jobName, jobGroup));
            }
            scheduler.resumeTrigger(triggerKey);
        } catch (SchedulerException e) {
            log.error("重启job失败, jobName={},jobGroup={},e={}", jobName, jobGroup, e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 立即执行
     *
     * @param jobName
     * @param jobGroup
     * @throws SchedulerException 
     */
	@Override
    public void trigger(String jobName, String jobGroup) throws SchedulerException {
        try {
            log.info("立即执行jobName={},jobGroup={}", jobName, jobGroup);
            if (!checkExists(jobName, jobGroup)) {
                log.error("Job不存在, jobName={},jobGroup={}", jobName, jobGroup);
                throw new RuntimeException(String.format("Job不存在, jobName=%s,jobGroup=%s", jobName, jobGroup));
            }
            JobKey jobKey = new JobKey(jobName, jobGroup);
            JobDataMap data=new JobDataMap();
            scheduler.triggerJob(jobKey,data);
        } catch (SchedulerException e) {
            log.error("立即执行job失败, jobName={},jobGroup={},e={}", jobName, jobGroup, e);
            throw e;
        }
    }

    /**
     * @param jobName
     * @param jobGroup
     * @param jsonData
     * @throws SchedulerException
     * @Description:立即执行job
     * @author : wei.yang
     * @date 2018年7月12日 上午11:56:15
     */
    @Override
    public void trigger(String jobName, String jobGroup, String jsonData) throws SchedulerException {
        try {
            log.info("立即执行jobName={},jobGroup={}", jobName, jobGroup);
            if (!checkExists(jobName, jobGroup)) {
                log.error("Job不存在, jobName={},jobGroup={}", jobName, jobGroup);
                throw new RuntimeException(String.format("Job不存在, jobName=%s,jobGroup=%s", jobName, jobGroup));
            }
            JobKey jobKey = new JobKey(jobName, jobGroup);
            JobDataMap data=new JobDataMap();
            data.put(JobServiceImpl.JOB_PARAM,jsonData);
            scheduler.triggerJob(jobKey,data);
        } catch (SchedulerException e) {
            log.error("立即执行job失败, jobName={},jobGroup={},e={}", jobName, jobGroup, e);
            throw e;
        }
    }

    @Override
    public JobDetailV getJobInfo(String jobName) {
        LambdaQueryWrapper<JobDetailV> wrapper = new QueryWrapper<JobDetailV>().lambda();
        wrapper.orderByDesc(JobDetailV::getJobName);
        wrapper.eq(JobDetailV::getJobName, jobName);
        return jobDetailVMapper.selectOne(wrapper);
    }

    /**
     * 验证是否存在
     *
     * @param jobName
     * @param jobGroup
     * @throws SchedulerException
     */
    private boolean checkExists(String jobName, String jobGroup) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        return scheduler.checkExists(triggerKey);
    }
}

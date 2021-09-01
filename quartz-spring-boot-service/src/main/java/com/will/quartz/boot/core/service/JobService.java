package com.will.quartz.boot.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.will.quartz.boot.core.bean.JobQuery;
import com.will.quartz.boot.core.entity.JobDetailV;
import org.quartz.SchedulerException;

public interface JobService {
	/**
	 * @Description:分页查询定时任务
	 */
	Page<JobDetailV> list(JobQuery jobQuery, int pageNo, int pageSize);
	/**
	 * @Description:新增一个job
	 */
	void addJob(String jobName, String jobGroup, String cronExpression, String jobDescription);
	/**
	 * @Description:
	 */
	void edit(String jobName, String jobGroup, String cronExpression, String jobDescription);
	/**
	 * @Description:删除job
	 */
	void delete(String jobName, String jobGroup);
	/**
	 * @Description:暂停job
	 */
	void pause(String jobName, String jobGroup);
	/**
	 * @Description:重启job
	 */
	void resume(String jobName, String jobGroup);
	/**
	 * @Description:立即执行job
	 */
	void trigger(String jobName, String jobGroup) throws SchedulerException;

	/**
	 * @Description:立即执行job
	 */
	void trigger(String jobName, String jobGroup,String jsonData) throws SchedulerException;

	JobDetailV getJobInfo(String jobName);
}

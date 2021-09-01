package com.will.quartz.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.will.quartz.boot.core.bean.JobDTO;
import com.will.quartz.boot.core.bean.JobLogQuery;
import com.will.quartz.boot.core.bean.JobQuery;
import com.will.quartz.boot.core.bean.Result;
import com.will.quartz.boot.core.entity.JobDetailV;
import com.will.quartz.boot.core.entity.JobLogEntity;
import com.will.quartz.boot.core.service.JobLogService;
import com.will.quartz.boot.core.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JobController {

    @Autowired
    private JobService jobService;
    @Autowired
    private JobLogService jobLogService;


    /**
     * 定时任务模块-查询-定时任务列表
     */
    @ResponseBody
    @PostMapping(value = "quartz/search/{pageNo}/{pageSize}")
    public Result<Page<JobDetailV>> search(@RequestBody JobQuery jobQuery,
                                           @PathVariable(name = "pageNo") Integer pageNo,
                                           @PathVariable(name = "pageSize") Integer pageSize) {
        Page<JobDetailV> model = jobService.list(jobQuery, pageNo, pageSize);
        return Result.success(model);
    }

    /**
     * 定时任务日志查询
     */
    @ResponseBody
    @PostMapping(value = "quartz/searchLog/{pageNo}/{pageSize}")
    public Result<Page<JobLogEntity>> searchLog(@RequestBody JobLogQuery jobLogQuery, @PathVariable(name = "pageNo") Integer pageNo,
                                                @PathVariable(name = "pageSize") Integer pageSize) {
        Page<JobLogEntity> model = jobLogService.queryLogList(jobLogQuery, pageNo, pageSize);
        return Result.success(model);
    }

    /**
     * 定时任务模块-新增-新增定时任务
     */
    @ResponseBody
    @PostMapping("quartz/add")
    public Result<Void> add(@RequestBody JobDTO dto) {
        jobService.addJob(dto.getJobName(), dto.getJobGroup(), dto.getCronExpression(), dto.getJobDescription());
        return Result.success();
    }

    /**
     * 定时任务模块-删除-删除定时任务
     */
    @ResponseBody
    @PostMapping(value = "quartz/delete")
    public Result<Void> delete(@RequestBody JobDTO dto) {
        jobService.delete(dto.getJobName(), dto.getJobGroup());
        return Result.success();
    }

    /**
     * 定时任务模块-恢复-恢复定时任务
     */
    @ResponseBody
    @PostMapping(value = "quartz/resume")
    public Result<Void> resume(@RequestBody JobDTO dto) {
        jobService.resume(dto.getJobName(), dto.getJobGroup());
        return Result.success();
    }

    /**
     * 定时任务模块-暂停-暂停定时任务
     */
    @ResponseBody
    @PostMapping(value = "quartz/pause")
    public Result<Void> pause(@RequestBody JobDTO dto) {
        jobService.pause(dto.getJobName(), dto.getJobGroup());
        return Result.success();
    }

    /**
     * 定时任务模块-执行-立即执行定时任务
     */
    @ResponseBody
    @PostMapping(value = "quartz/run")
    public Result<Void> run(@RequestBody JobDTO dto) throws Exception {
        jobService.trigger(dto.getJobName(), dto.getJobGroup());
        return Result.success();
    }

    /**
     * 定时任务模块-更新-更新定时任务
     */
    @ResponseBody
    @PostMapping(value = "quartz/update")
    public Result<Void> update(@RequestBody JobDTO dto) {
        jobService.edit(dto.getJobName(), dto.getJobGroup(), dto.getCronExpression(), dto.getJobDescription());
        return Result.success();
    }

}

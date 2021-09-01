package com.will.quartz.boot.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.will.quartz.boot.core.entity.JobLogEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JobLogMapper extends BaseMapper<JobLogEntity> {

}

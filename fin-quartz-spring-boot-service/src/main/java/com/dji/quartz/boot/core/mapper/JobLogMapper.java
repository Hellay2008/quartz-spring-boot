package com.dji.quartz.boot.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dji.quartz.boot.core.entity.JobLogEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JobLogMapper extends BaseMapper<JobLogEntity> {

}

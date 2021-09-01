package com.will.quartz.boot.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.will.quartz.boot.core.entity.JobLogSeqEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JobLogSeqMapper extends BaseMapper<JobLogSeqEntity> {

}

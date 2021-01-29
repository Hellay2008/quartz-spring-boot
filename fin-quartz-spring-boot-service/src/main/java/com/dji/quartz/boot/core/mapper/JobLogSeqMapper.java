package com.dji.quartz.boot.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dji.quartz.boot.core.entity.JobLogSeqEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JobLogSeqMapper extends BaseMapper<JobLogSeqEntity> {

}

package com.dji.quartz.boot.core.service.impl;

import com.dji.quartz.boot.core.entity.JobLogSeqEntity;
import com.dji.quartz.boot.core.mapper.JobLogSeqMapper;
import com.dji.quartz.boot.core.service.JobLogSeqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class JobLogSeqServiceImpl implements JobLogSeqService {

    @Autowired
    JobLogSeqMapper jobLogMapper;

    @Override
    public Long getSeqId() {
        JobLogSeqEntity seq = new JobLogSeqEntity();
        seq.setCreateTime(new Date());
        jobLogMapper.insert(seq);
        return seq.getId();
    }

}

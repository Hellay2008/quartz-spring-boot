package com.will.quartz.boot.core.bean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JobHead {
    private long jobSeqId;
    private long step;
    private String jobName;
    private String jobDesc;

    public Long getAndIncreaseStep(){
        return  step ++;
    }
}

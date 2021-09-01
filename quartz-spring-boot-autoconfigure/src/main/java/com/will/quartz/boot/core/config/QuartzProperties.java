package com.will.quartz.boot.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix=QuartzProperties.QUARTZ_PREFIX)
public class QuartzProperties {
    static final String QUARTZ_PREFIX = "quartz";
    private Boolean enabled;
    private String propertyPath;
}

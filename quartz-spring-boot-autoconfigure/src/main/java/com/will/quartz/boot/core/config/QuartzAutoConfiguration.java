package com.will.quartz.boot.core.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties(QuartzProperties.class)
@Import({QuartzConfiguration.class})
@ComponentScan(basePackages="com.will.quartz")
public class QuartzAutoConfiguration {

}

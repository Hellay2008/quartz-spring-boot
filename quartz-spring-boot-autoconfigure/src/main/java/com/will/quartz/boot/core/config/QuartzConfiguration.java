package com.will.quartz.boot.core.config;

import org.apache.commons.lang3.StringUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = QuartzProperties.QUARTZ_PREFIX, value = "enabled", havingValue = "true")
public class QuartzConfiguration {

    @Autowired
    QuartzJobFactory quartzJobFactory;
    @Autowired
    QuartzProperties quartzProperties;

    @PostConstruct
    public void checkProperties(){
        if(StringUtils.isEmpty(quartzProperties.getPropertyPath())){
            throw new RuntimeException(QuartzProperties.QUARTZ_PREFIX + ".propertyPath 配置项未设置");
        }
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(QuartzJobFactory quartzJobFactory, QuartzProperties quartzProperties, DataSource dataSource)
            throws Exception {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setJobFactory(quartzJobFactory);
        factoryBean.setConfigLocation(new ClassPathResource(quartzProperties.getPropertyPath()));
        factoryBean.setDataSource(dataSource);
        factoryBean.afterPropertiesSet();
        return factoryBean;
    }

    @Bean
    public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean) throws Exception {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.setJobFactory(quartzJobFactory);
        scheduler.start();
        return scheduler;
    }



}

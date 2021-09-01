package com.will.quartz.boot.core.config;

import org.quartz.SchedulerContext;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

@Component
public class QuartzJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private SchedulerContext schedulerContext;
    private String[] ignoredUnknownProperties;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    public void setIgnoredUnknownProperties(String... ignoredUnknownProperties) {
        this.ignoredUnknownProperties = ignoredUnknownProperties;
    }
    @Override
    public void setSchedulerContext(SchedulerContext schedulerContext) {
        this.schedulerContext = schedulerContext;
    }

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {

        Class<?> jobClass = bundle.getJobDetail().getJobClass();
        Object job = applicationContext.getBean(jobClass);
        if (isEligibleForPropertyPopulation(job)) {
            BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(job);
            MutablePropertyValues pvs = new MutablePropertyValues();
            if (this.schedulerContext != null) {
                pvs.addPropertyValues(this.schedulerContext);
            }
            pvs.addPropertyValues(bundle.getJobDetail().getJobDataMap());
            pvs.addPropertyValues(bundle.getTrigger().getJobDataMap());
            if (this.ignoredUnknownProperties != null) {
                for (String propName : this.ignoredUnknownProperties) {
                    if (pvs.contains(propName) && !bw.isWritableProperty(propName)) {
                        pvs.removePropertyValue(propName);
                    }
                }
                bw.setPropertyValues(pvs);
            } else {
                bw.setPropertyValues(pvs, true);
            }
        }
        return job;

    }
}


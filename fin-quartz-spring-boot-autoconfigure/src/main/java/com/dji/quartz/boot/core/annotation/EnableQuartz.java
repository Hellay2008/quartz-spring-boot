package com.dji.quartz.boot.core.annotation;

import com.dji.quartz.boot.core.config.QuartzAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(QuartzAutoConfiguration.class)
public @interface EnableQuartz {
}

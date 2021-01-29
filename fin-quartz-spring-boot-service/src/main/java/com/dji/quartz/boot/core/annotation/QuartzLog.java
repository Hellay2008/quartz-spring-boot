package com.dji.quartz.boot.core.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface QuartzLog {

    String jobDesc() default "";
    Class<?> targetClass();
}

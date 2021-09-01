package com.sample;

import com.will.quartz.boot.core.annotation.EnableQuartz;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableQuartz
@SpringBootApplication
public class QuartSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuartSampleApplication.class, args);
    }
}

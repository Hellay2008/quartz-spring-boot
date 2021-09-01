  ## TODO 编写使用说明

### 1 配置数据源，引pom

####   配置数据源

```properties
# starter开关
quartz.enabled=true
quartz.property-path=quartz.properties

### datasource
spring.datasource.url=jdbc:mysql://localhost:3306/quartz?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

### datasource-pool
spring.datasource.type=com.zaxxer.hikari.HikariDataSource
spring.datasource.hikari.minimum-idle=10
spring.datasource.hikari.maximum-pool-size=30
spring.datasource.hikari.auto-commit=true
spring.datasource.hikari.idle-timeout=30000
spring.datasource.hikari.pool-name=HikariCP
spring.datasource.hikari.max-lifetime=900000
spring.datasource.hikari.connection-timeout=10000
spring.datasource.hikari.connection-test-query=SELECT 1
spring.datasource.hikari.validation-timeout=1000

```

####  引pom

```xml
 <dependency>
     <groupId>com.will.quartz</groupId>
     <artifactId>quartz-spring-boot-starter</artifactId>
     <version>1.0-SNAPSHOT</version>
 </dependency>
```



### 2 写quartz官方配置  quartz.properties

```properties
org.quartz.scheduler.instanceName=TEST_JOB_SCHEDULER
org.quartz.scheduler.instanceId=AUTO
org.quartz.jobStore.class=org.quartz.impl.jdbcjobstore.JobStoreTX
org.quartz.jobStore.driverDelegateClass=org.quartz.impl.jdbcjobstore.StdJDBCDelegate
org.quartz.jobStore.tablePrefix=QRTZ_
org.quartz.jobStore.isClustered=true
org.quartz.jobStore.useProperties=true
org.quartz.jobStore.clusterCheckinInterval=20000
org.quartz.scheduler.skipUpdateCheck=true
org.quartz.threadPool.class=org.quartz.simpl.SimpleThreadPool
org.quartz.threadPool.threadCount=20
org.quartz.threadPool.threadPriority=5
org.quartz.jobStore.misfireThreshold =15000
org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread=true
```



### 3 写自定义配置

### 4 执行数据库脚本 init-db-mysql.sql

```sql
CREATE TABLE `qrtz_job_log_seq`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


CREATE TABLE `qrtz_job_log`
(
    `id`           bigint NOT NULL AUTO_INCREMENT,
    `job_id`       varchar(64)   DEFAULT NULL COMMENT 'bean name',
    `job_desc`     varchar(255)  DEFAULT NULL COMMENT 'job描述',
    `execute_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    `status`       varchar(32)   DEFAULT NULL,
    `message`      varchar(2000) DEFAULT NULL,
    `create_time`  timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    `create_by`    varchar(32)   DEFAULT NULL,
    `seq_id`       bigint        DEFAULT NULL,
    `step`         bigint        DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY            `bean_name_idx` (`job_id`) USING BTREE,
    KEY            `job_desc_idx` (`job_desc`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
```

### 5 注解开启服务

```
@EnableQuartz
```

### 6 编写定时任务

```
@Component
@DisallowConcurrentExecution
public class SampleJob1 implements Job {

    @Autowired
    JobLogService jobLogService;

    @Override
    @QuartzLog(jobDesc = "测试定时任务X", targetClass = SampleJob1.class)
    public void execute(JobExecutionContext context) throws JobExecutionException {

        jobLogService.log(LogStatusEnum.NORMAL.getCode(), "定时任务-2-步骤1开始");

        jobLogService.log(LogStatusEnum.NORMAL.getCode(), "定时任务-2-步骤1执行中");

        jobLogService.log(LogStatusEnum.NORMAL.getCode(), "定时任务-2-步骤1完成");

        jobLogService.log(LogStatusEnum.NORMAL.getCode(), "定时任务-2-步骤2开始");

        jobLogService.log(LogStatusEnum.NORMAL.getCode(), "定时任务-2-步骤2执行中");

        jobLogService.log(LogStatusEnum.NORMAL.getCode(), "定时任务-2-步骤2完成");
        int x = 1;
        int y = 0;
        x = x/y;

        jobLogService.log(LogStatusEnum.NORMAL.getCode(), "作定时任-2-务完成");
    }
}
```

### 7 测试

```
1 定时任务管理
http://localhost:{port}/{context-path}/quartz/job.html
2 定时任务日志
http://localhost:{port}/{context-path}/quartz/job-log.html
```



#### 作业管理

![image-20210127162133121](C:\Users\o-will.gongye\AppData\Roaming\Typora\typora-user-images\image-20210127162133121.png)

#### 日志

![image-20210127162048723](C:\Users\o-will.gongye\AppData\Roaming\Typora\typora-user-images\image-20210127162048723.png)
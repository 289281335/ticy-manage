package com.ticy.manage.service.impl;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @Author tkk
 * @Time 2021/4/25 21:48
 * @Description todo
 */
public class QuartzManager {
    private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
    //新增

    public static void addJob() {

        try {
            //通过SchedulerFactory来获取一个调度器
            Scheduler sched = gSchedulerFactory.getScheduler();
            //引进作业程序
            JobDetail jobDetail = JobBuilder.newJob(MyJob.class).build();// 任务名，任务组，任务执行类
            //触发器
            CronTrigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?"))
                    .build();
            // 触发器时间设定
            //trigger.setCronExpression(time);
            sched.scheduleJob(jobDetail, trigger);
            // 启动
            if (!sched.isShutdown()) {
                sched.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        addJob();
        System.out.println("添加定时任务");
    }
}

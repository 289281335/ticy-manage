package com.ticy.manage.service.impl;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Author tkk
 * @Time 2021/4/25 21:50
 * @Description todo
 */
public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("********************************");
        System.out.println("*********  JOB  TEST  **********");
        System.out.println("********************************");
    }
}

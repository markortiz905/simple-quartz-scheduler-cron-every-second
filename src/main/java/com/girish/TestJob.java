package com.girish;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob implements Job {

    private static Integer counter = 0;

    public void execute(JobExecutionContext jExeCtx) throws JobExecutionException {
        System.out.println("Test Run..." + counter);
        counter++;
    }
}

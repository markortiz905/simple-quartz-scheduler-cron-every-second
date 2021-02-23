package com.girish;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.TriggerBuilder.newTrigger;

public class MainApp {

    public static void main(String[] args) {
        System.out.println(System.getProperty("log4j.configurationFile"));
        SchedulerFactory sf = new StdSchedulerFactory();
        try {
            Scheduler sched = sf.getScheduler();
            sched.start();

            ScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("* * * ? * *");
            Trigger trigger = newTrigger()
                    .withIdentity("databasetrigger", "databasegroup")
                    .startNow()
                    .withSchedule(scheduleBuilder)
                    .build();
            JobKey jobKey = new JobKey("databasejob", "databasegroup");
            JobDetail job = JobBuilder.newJob(TestJob.class)
                    .withIdentity(jobKey)
                    .build();

            sched.scheduleJob(job, trigger);
            System.out.println("Scheduled");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }
}

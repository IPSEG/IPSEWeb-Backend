package com.ipseweb.traffic.scheduler.task;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;

public abstract class JobTask implements Runnable {

    protected JobLauncher jobLauncher;
    protected Job job;


    public abstract JobType getJobType();

    public abstract boolean supports(String jobName);

    public void setJob(Job job) {
        this.job = job;
    }

    public void setJobLauncher(JobLauncher jobLauncher) {
        this.jobLauncher = jobLauncher;
    }

}

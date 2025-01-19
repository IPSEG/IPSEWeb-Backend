package com.ipseweb.traffic.scheduler.service;

import com.ipseweb.traffic.scheduler.task.JobTask;
import com.ipseweb.traffic.scheduler.task.JobTaskFactory;
import com.ipseweb.traffic.scheduler.trigger.JobTriggerFactory;
import com.ipseweb.traffic.scheduler.trigger.TriggerFactory;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchedulerService {

    private final TaskScheduler taskScheduler;

    @Qualifier("taskExecutor")
    private final TaskExecutor taskExecutor;

    private final List<Job> jobs;

    private final JobLauncher jobLauncher;

    private final JobTaskFactory jobTaskFactory;

    private final JobTriggerFactory jobTriggerFactory;

    private final Map<String, String> jobTrigger = new HashMap<>();



    @PostConstruct
    public void init() {
        registerJobTrigger();
        registerJob();
    }

    private void registerJobTrigger() {

//        jobTrigger.put("BusStopOpenApiCronJob", "0 0 3 * * ?");
//        TODO : 테스트용 매분 실행.
        jobTrigger.put("BusStopOpenApiCronJob", "0 * * * * ?");
    }
    private void registerJob() {
        TaskExecutorJobLauncher jobLauncher1 = (TaskExecutorJobLauncher) jobLauncher;
        jobLauncher1.setTaskExecutor(taskExecutor);

        for (Job job : jobs) {

            JobTask jobTask
                    = jobTaskFactory.find(job.getName());

            jobTask.setJob(job);
            jobTask.setJobLauncher(jobLauncher1);

            TriggerFactory triggerFactory = jobTriggerFactory.find(jobTask.getJobType());


            String args
                    = jobTrigger.get(job.getName());

            Trigger trigger = triggerFactory.createTrigger(args);

            taskScheduler.schedule(jobTask, trigger);
        }
    }
}

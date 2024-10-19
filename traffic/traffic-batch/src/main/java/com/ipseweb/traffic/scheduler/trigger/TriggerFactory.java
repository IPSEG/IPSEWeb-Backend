package com.ipseweb.traffic.scheduler.trigger;

import com.ipseweb.traffic.scheduler.task.JobType;
import org.springframework.scheduling.Trigger;

public interface TriggerFactory {
    Trigger createTrigger(String args);

    boolean supports(JobType jobType);
}

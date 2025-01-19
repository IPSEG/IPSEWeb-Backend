package com.ipseweb.traffic.scheduler.trigger;

import com.ipseweb.traffic.scheduler.task.JobType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class JobTriggerFactory {

    private final List<TriggerFactory> triggerFactories;


    public TriggerFactory find(JobType jobType) {
        for (TriggerFactory triggerFactory : triggerFactories) {
            if (triggerFactory.supports(jobType)) {
                return triggerFactory;
            }
        }

        return null;
    }
}

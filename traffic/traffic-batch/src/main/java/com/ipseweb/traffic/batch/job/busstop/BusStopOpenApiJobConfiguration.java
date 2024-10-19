package com.ipseweb.traffic.batch.job.busstop;

import com.ipseweb.traffic.domain.BusStop;
import com.ipseweb.traffic.dto.busstop.OpenApiBusStopResponse;
import com.ipseweb.traffic.service.BusStopJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class BusStopOpenApiJobConfiguration {

    private final BusStopJobService busStopJobService;



    @Bean
    public Job busStopOpenApiCronJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("BusStopOpenApiCronJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(busStopOpenApiStep(jobRepository, transactionManager))
                .build();
    }

    @Bean
    @JobScope
    public Step busStopOpenApiStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("BusStopOpenApiStep", jobRepository)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

                        List<OpenApiBusStopResponse> openApiBusStopResponse = busStopJobService.getOpenApiBusStopResponse();

                        List<BusStop> busStopList = busStopJobService.getBusStopList(openApiBusStopResponse);

                        busStopJobService.saveAll(busStopList);

                        return RepeatStatus.FINISHED;
                    }
                }, transactionManager).build();
    }


}

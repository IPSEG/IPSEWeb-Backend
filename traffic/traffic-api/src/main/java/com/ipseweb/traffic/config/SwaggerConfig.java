package com.ipseweb.traffic.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Traffic Project",
                description = "버스, 지하철, 교통 신호 관리 프로젝트입니다."
        )
)
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi busStopApi() {
        return GroupedOpenApi
                .builder()
                .group("API-버스정류장")
                .pathsToMatch("/api/traffic/bus-stop/**")
                .build();
    }

    @Bean
    public GroupedOpenApi busArrivalApi() {
        return GroupedOpenApi
                .builder()
                .group("API-버스도착정보")
                .pathsToMatch("/api/traffic/bus-arrival/**")
                .build();
    }


    @Bean
    public GroupedOpenApi subWayApi() {
        return GroupedOpenApi
                .builder()
                .group("API-지하철")
                .pathsToMatch("/api/traffic/subway-arrival/**")
                .build();
    }

    @Bean
    public GroupedOpenApi cardGroupApi() {
        return GroupedOpenApi
                .builder()
                .group("API-카드그룹")
                .pathsToMatch("/api/traffic/card-group/**")
                .build();
    }

    @Bean
    public GroupedOpenApi cardApi() {
        return GroupedOpenApi
                .builder()
                .group("API-카드")
                .pathsToMatch("/api/traffic/card/**")
                .build();
    }


}

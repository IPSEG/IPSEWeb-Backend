package com.ipseweb.traffic.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;
import java.util.UUID;

@Configuration
public class TrafficConfig {

    /**
     * TODO : 향후 spring security 및 로그인 프로세스 적용 시 login_id를 넣도록 변경이 필요함.
     * @return
     */
    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.of(UUID.randomUUID().toString());
    }

}

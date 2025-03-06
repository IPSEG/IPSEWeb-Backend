package com.ipseweb.traffic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Profile({"dev", "hoyo", "seungbin"})
public class WebConfig implements WebMvcConfigurer {

    /**
     * 로컬 환경에서의 CORS 설정
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080", "http://localhost:3000")
                .allowedMethods("GET", "POST")
                .allowCredentials(true)
                .maxAge(3000);
    }
}

package com.ipseweb.traffic.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class LocalCacheConfig {
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cm = new CaffeineCacheManager("TrafficBasicCache");
        cm.setCaffeine(trafficBasicCacheBuilder());
        cm.registerCustomCache("busArrivalCache", busArrivalCacheBuilder().build());
        cm.registerCustomCache("busRouteCache", busRouteCacheBuilder().build());
        return cm;
    }

    Caffeine<Object, Object> trafficBasicCacheBuilder() {
        return Caffeine.newBuilder()
                .expireAfterWrite(3, TimeUnit.MINUTES);
    }

    Caffeine<Object, Object> busArrivalCacheBuilder() {
        return Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES);
    }

    Caffeine<Object, Object> busRouteCacheBuilder() {
        return Caffeine.newBuilder()
                .expireAfterWrite(23, TimeUnit.HOURS);
    }

}

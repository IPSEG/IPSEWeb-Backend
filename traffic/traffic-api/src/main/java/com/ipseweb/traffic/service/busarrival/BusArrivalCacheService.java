package com.ipseweb.traffic.service.busarrival;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ipseweb.traffic.dto.busarrival.BusArrivalDto.BusArrivalInfoResponse;

@Service
@Slf4j
@RequiredArgsConstructor
public class BusArrivalCacheService {

    private final BusArrivalOpenApiService busArrivalOpenApiService;

    @Cacheable("busArrivalCache")
    public List<BusArrivalInfoResponse> findBusArrivalInfo(String busStopId, String cityCode, String time) {
        return busArrivalOpenApiService.findBusArrivalInfo(busStopId, cityCode);
    }


}

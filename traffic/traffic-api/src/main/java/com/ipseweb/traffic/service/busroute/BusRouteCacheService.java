package com.ipseweb.traffic.service.busroute;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static com.ipseweb.traffic.dto.busroute.BusRouteDto.BusRouteBasicInfoResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class BusRouteCacheService {

    private final BusRouteOpenApiService busRouteOpenApiService;

    @Cacheable("busRouteCache")
    public BusRouteBasicInfoResponse findBusRouteBasicInfo(String cityCode, String routeId, String time) {
        return busRouteOpenApiService.findBusRouteBasicInfo(cityCode, routeId);
    }

}

package com.ipseweb.traffic.service.busarrival;

import com.ipseweb.traffic.dto.busarrival.OpenApiBusArrivalResponse;
import com.ipseweb.util.Request;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ipseweb.traffic.dto.busarrival.BusArrivalDto.BusArrivalInfoResponse;

@Service
@Slf4j
public class BusArrivalOpenApiService {

    @Value("${spring.openapi.busArrival.apiKey}")
    private String apiKey;

    @Value("${spring.openapi.busArrival.url.busArrivalInfo}")
    private String arrivalInfoUrl;

    public List<BusArrivalInfoResponse> findBusArrivalInfo(String busStopId, String cityCode) {
        String url = String.format(arrivalInfoUrl, apiKey, 1, 1000, cityCode, busStopId);
        OpenApiBusArrivalResponse response = Request.requestGet(url, OpenApiBusArrivalResponse.class);


        List<BusArrivalInfoResponse> collect =
                Optional.ofNullable(response)
                        .map(OpenApiBusArrivalResponse::getResponse)
                        .map(OpenApiBusArrivalResponse.Response::getBody)
                        .map(OpenApiBusArrivalResponse.OpenApiBusArrivalBody::getItems)
                        .map(OpenApiBusArrivalResponse.OpenApiBusArrivalDatas::getItem)
                        .orElseGet(() -> Collections.emptyList())
                        .stream().map(openApiBusArrivalData -> new BusArrivalInfoResponse(
                                        Optional.ofNullable(openApiBusArrivalData.getBusStopId()).orElseGet( () -> StringUtils.EMPTY),
                                        Optional.ofNullable(openApiBusArrivalData.getBusStopName()).orElseGet( () -> StringUtils.EMPTY),
                                        Optional.ofNullable(openApiBusArrivalData.getRouteType()).orElseGet(() -> StringUtils.EMPTY),
                                        Optional.ofNullable(openApiBusArrivalData.getVehicleType()).orElseGet( () -> StringUtils.EMPTY),
                                        Optional.ofNullable(openApiBusArrivalData.getArrivePrevStationCnt()).orElseGet( () -> 0L),
                                        Optional.ofNullable(openApiBusArrivalData.getArriveSeconds()).orElseGet( () -> 0L),
                                        Optional.ofNullable(openApiBusArrivalData.getRouteId()).orElseGet( () -> StringUtils.EMPTY),
                                        Optional.ofNullable(openApiBusArrivalData.getRouteNo()).orElseGet( () -> StringUtils.EMPTY)
                                )
                        ).collect(Collectors.toList());

        return collect;
    }


}


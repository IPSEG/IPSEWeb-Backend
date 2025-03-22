package com.ipseweb.traffic.service.busroute;

import com.ipseweb.error.BusRouteErrorcode;
import com.ipseweb.error.Response;
import com.ipseweb.exception.BusRouteException;
import com.ipseweb.exception.ResponseEntityFactory;
import com.ipseweb.traffic.dto.busroute.OpenApiBusRouteBasicInfoData;
import com.ipseweb.traffic.dto.busroute.OpenApiBusRouteBasicInfoResponse;
import com.ipseweb.util.Request;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static com.ipseweb.traffic.dto.busroute.BusRouteDto.*;

@Service
@Slf4j
public class BusRouteService {

    @Value("${spring.openapi.busArrival.apiKey}")
    private String apiKey;

    @Value("${spring.openapi.busRoute.url.busRouteBasicInfo}")
    private String busRouteBasicInfoUrl;

    public ResponseEntity<Response<BusRouteBasicInfoResponse>> findBusRouteBasicInfo(String cityCode, String routeId) {
        String url = String.format(busRouteBasicInfoUrl, apiKey, cityCode, routeId);
        OpenApiBusRouteBasicInfoResponse response = Request.requestGet(url, OpenApiBusRouteBasicInfoResponse.class);

        OpenApiBusRouteBasicInfoData item =
                Optional.ofNullable(response)
                        .map(OpenApiBusRouteBasicInfoResponse::getResponse)
                        .map(OpenApiBusRouteBasicInfoResponse.Response::getBody)
                        .map(OpenApiBusRouteBasicInfoResponse.OpenApiBusRouteBasicInfoBody::getItems)
                        .map(OpenApiBusRouteBasicInfoResponse.OpenApiBusRouteBasicInfoDatas::getItem)
                        .orElseThrow(() -> {
                                    log.error("Bus route was not exist.");
                                    throw new BusRouteException(BusRouteErrorcode.BUS_ROUTE_NOT_EXIST);
                                }
                        );


        BusRouteBasicInfoResponse busRouteBasicInfoResponse = new BusRouteBasicInfoResponse(
                Optional.ofNullable(item.getEndNodeName()).orElseGet( () -> StringUtils.EMPTY),
                Optional.ofNullable(item.getStartNodeName()).orElseGet(() -> StringUtils.EMPTY),
                Optional.ofNullable(LocalTime.parse(Optional.ofNullable(item.getEndVehicleTime()).orElseGet( () -> "0000")
                        , DateTimeFormatter.ofPattern("HHmm"))).orElseGet(() -> LocalTime.of(0, 0)),
                Optional.ofNullable(LocalTime.parse(Optional.ofNullable(item.getStartVehicleTime()).orElseGet( () -> "0000")
                        , DateTimeFormatter.ofPattern("HHmm"))).orElseGet(() -> LocalTime.of(0, 0)),
                Optional.ofNullable(item.getIntervalTimeMinute()).orElseGet(() -> 0L),
                Optional.ofNullable(item.getIntervalSaturdayTimeMinute()).orElseGet(() -> 0L),
                Optional.ofNullable(item.getIntervalSundayTimeMinute()).orElseGet(() -> 0L),
                Optional.ofNullable(item.getRouteId()).orElseGet( () -> StringUtils.EMPTY),
                Optional.ofNullable(item.getRouteNo()).orElseGet(() -> 0L),
                Optional.ofNullable(item.getRouteTyp()).orElseGet( () -> StringUtils.EMPTY)
        );


        return ResponseEntityFactory.success(busRouteBasicInfoResponse);
    }


}

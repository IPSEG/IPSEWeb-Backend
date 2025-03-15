package com.ipseweb.traffic.service.busroute;

import com.ipseweb.error.Response;
import com.ipseweb.exception.ResponseEntityFactory;
import com.ipseweb.traffic.dto.busroute.OpenApiBusRouteBasicInfoData;
import com.ipseweb.traffic.dto.busroute.OpenApiBusRouteBasicInfoResponse;
import com.ipseweb.util.Request;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

        OpenApiBusRouteBasicInfoData item = response.getResponse().getBody().getItems().getItem();
        BusRouteBasicInfoResponse busRouteBasicInfoResponse = new BusRouteBasicInfoResponse(
                item.getEndNodeName(),
                item.getStartNodeName(),
                LocalTime.parse(item.getEndVehicleTime(), DateTimeFormatter.ofPattern("HHmm")),
                LocalTime.parse(item.getStartVehicleTime(), DateTimeFormatter.ofPattern("HHmm")),
                item.getIntervalTimeMinute(),
                item.getIntervalSaturdayTimeMinute(),
                item.getIntervalSundayTimeMinute(),
                item.getRouteId(),
                item.getRouteNo(),
                item.getRouteTyp()
        );


        return ResponseEntityFactory.success(busRouteBasicInfoResponse);
    }


}

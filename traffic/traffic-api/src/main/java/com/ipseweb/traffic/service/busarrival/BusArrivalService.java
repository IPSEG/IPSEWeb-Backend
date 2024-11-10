package com.ipseweb.traffic.service.busarrival;

import com.ipseweb.error.Response;
import com.ipseweb.exception.ResponseEntityFactory;
import com.ipseweb.traffic.dto.busarrival.BusArrivalDto;
import com.ipseweb.traffic.dto.busarrival.OpenApiBusArrivalResponse;
import com.ipseweb.util.Request;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.UriEncoder;

import java.util.List;
import java.util.stream.Collectors;

import static com.ipseweb.traffic.dto.busarrival.BusArrivalDto.*;

@Service
@Slf4j
public class BusArrivalService {

    @Value("${spring.openapi.busArrival.apiKey}")
    private String apiKey;

    @Value("${spring.openapi.busArrival.url.busArrivalInfo}")
    private String arrivalInfoUrl;

    public ResponseEntity<Response<List<BusArrivalInfoResponse>>> findBusArrivalInfo(String busStopId, String cityCode) {
        String url = String.format(arrivalInfoUrl, apiKey, 1, 1000, cityCode, busStopId);
        OpenApiBusArrivalResponse response = Request.requestGet(url, OpenApiBusArrivalResponse.class);

        List<BusArrivalInfoResponse> collect = response.getResponse().getBody().getItems().getItem().stream().map(openApiBusArrivalData -> new BusArrivalInfoResponse(
                        openApiBusArrivalData.getBusStopId(),
                        openApiBusArrivalData.getBusStopName(),
                        openApiBusArrivalData.getRouteType(),
                        openApiBusArrivalData.getVehicleType()))
                .collect(Collectors.toList());

        return ResponseEntityFactory.success(collect);
    }


}


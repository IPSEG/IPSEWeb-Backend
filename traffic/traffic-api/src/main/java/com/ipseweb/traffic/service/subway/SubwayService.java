package com.ipseweb.traffic.service.subway;

import com.ipseweb.error.Response;
import com.ipseweb.traffic.dto.subway.OpenApiStationArrivalResponse;
import com.ipseweb.exception.ResponseEntityFactory;
import com.ipseweb.util.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SubwayService {

    @Value("${spring.openapi.subway.apiKey}")
    private String apiKey;

    @Value("${spring.openapi.subway.url.arrivalInfo}")
    private String arrivalInfoUrl;

    public ResponseEntity<Response<OpenApiStationArrivalResponse>> getStationArrivalInfo(String stationName) {
        String url = String.format(arrivalInfoUrl, apiKey, 0, 8, stationName);
        OpenApiStationArrivalResponse response = Request.requestGet(url, OpenApiStationArrivalResponse.class);

        return ResponseEntityFactory.success(response);
    }
}

package com.ipseweb.traffic.service.subway;

import com.ipseweb.error.Response;
import com.ipseweb.traffic.domain.subway.SubwayLineInfo;
import com.ipseweb.traffic.dto.subway.OpenApiStationArrivalData;
import com.ipseweb.traffic.dto.subway.OpenApiStationArrivalResponse;
import com.ipseweb.exception.ResponseEntityFactory;
import com.ipseweb.traffic.repository.subway.SubwayLineInfoRepository;
import com.ipseweb.util.Request;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubwayService {

    @Value("${spring.openapi.subway.apiKey}")
    private String apiKey;

    @Value("${spring.openapi.subway.url.arrivalInfo}")
    private String arrivalInfoUrl;

    private final SubwayLineInfoRepository subwayLineInfoRepository;

    public ResponseEntity<Response<OpenApiStationArrivalResponse>> getStationArrivalInfo(String stationName) {
        log.info("apiKey : {}", apiKey);
        String url = String.format(arrivalInfoUrl, apiKey, 0, 4, stationName);

        OpenApiStationArrivalResponse response = Request.requestGet(url, OpenApiStationArrivalResponse.class);

        Map<Integer, String> lineMap = new HashMap<>();
        OpenApiStationArrivalResponse result = new OpenApiStationArrivalResponse();

        for(OpenApiStationArrivalData data : response.realtimeArrivalList) {
            if(lineMap.get(data.subwayId) == null) {
                lineMap.put(data.subwayId, "set");
                String lineName = subwayLineInfoRepository.findById(data.subwayId).map(SubwayLineInfo::getLineName).orElse("");
                log.info("subWayId : {}, lineName : {}", data.subwayId, lineName);
                data.setSubwayName(lineName);
                result.realtimeArrivalList.add(data);
            }
        }

        return ResponseEntityFactory.success(result);
    }
}

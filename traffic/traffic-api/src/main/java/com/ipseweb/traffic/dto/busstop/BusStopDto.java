package com.ipseweb.traffic.dto.busstop;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;

public class BusStopDto {

    @Data
    @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class BusStopResponse {
        private String busStopId;
        private String busStopName;
        private String cityCode;
        private String city;
        private String detailCity;
    }



}

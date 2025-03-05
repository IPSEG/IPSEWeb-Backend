package com.ipseweb.traffic.dto.busstop;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

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


    @Data
    @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class BusStopPagingResponse {
        List<BusStopResponse>  busStopList;
        Boolean hasNext;
        Boolean hasPrevious;
        Integer pageCount;
        Integer currentPageCount;
    }
}

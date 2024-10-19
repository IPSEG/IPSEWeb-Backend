package com.ipseweb.traffic.dto.busstop;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.convert.DataSizeUnit;

public class BusStopDto {

    @Data
    @AllArgsConstructor
    public static class BusStopResponse {
        private String BusStopId;
        private String busStopName;
        private String city;
        private String detailCity;

    }


}

package com.ipseweb.traffic.dto.busstop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.convert.DataSizeUnit;

public class BusStopDto {

    @Data
    @AllArgsConstructor
    public static class BusStopResponse {
        private String busStopId;
        private String busStopName;
        private String cityCode;
        private String city;
        private String detailCity;
    }



}

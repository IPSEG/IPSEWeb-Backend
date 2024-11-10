package com.ipseweb.traffic.dto.busarrival;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class BusArrivalDto {


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BusArrivalInfoRequest{
        private String busStopName;
        private String cityCode;
        private String busStopId;
    }

    @Data
    @AllArgsConstructor
    public static class BusArrivalInfoResponse{
        private String busStopId;
        private String busStopName;
        private String routeType;
        private String vehicleType;
    }


}

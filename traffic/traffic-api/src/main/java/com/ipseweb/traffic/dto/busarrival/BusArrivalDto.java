package com.ipseweb.traffic.dto.busarrival;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class BusArrivalInfoResponse{
        private String busStopId;
        private String busStopName;
        private String routeType;
        private String vehicleType;
        private Long arrivePrevStationCnt;
        private Long arriveSeconds;
        private String routeId;
        private String routeNo;
    }


}

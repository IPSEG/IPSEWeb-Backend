package com.ipseweb.traffic.dto.busroute;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

public class BusRouteDto {

    @Data
    @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class BusRouteBasicInfoResponse {
        private String endNodeName;
        private String startNodeName;
        private LocalTime endVehicleTime;
        private LocalTime startVehicleTime;
        private Long intervalTimeMinute;
        private Long intervalSaturdayTimeMinute;
        private Long intervalSundayTimeMinute;
        private String routeId;
        private Long routeNo;
        private String routeType;
    }
}

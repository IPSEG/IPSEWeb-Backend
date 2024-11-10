package com.ipseweb.traffic.dto.busarrival;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class OpenApiBusArrivalData {

    @JsonProperty("arrprevstationcnt")
    private Long arrivePrevStationCnt;

    @JsonProperty("arrtime")
    private Long arriveSeconds;

    @JsonProperty("nodeid")
    private String busStopId;

    @JsonProperty("nodenm")
    private String busStopName;

    @JsonProperty("routeid")
    private String routeId;

    @JsonProperty("routeno")
    private String routeNo;

    @JsonProperty("routetp")
    private String routeType;

    @JsonProperty("vehicletp")
    private String vehicleType;
}

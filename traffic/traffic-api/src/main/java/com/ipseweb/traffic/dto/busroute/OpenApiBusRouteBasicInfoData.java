package com.ipseweb.traffic.dto.busroute;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class OpenApiBusRouteBasicInfoData {
    @JsonProperty("endnodenm")
    private String endNodeName;

    @JsonProperty("endvehicletime")
    private String endVehicleTime;

    @JsonProperty("intervalsattime")
    private Long intervalSaturdayTimeMinute;

    @JsonProperty("intervalsuntime")
    private Long intervalSundayTimeMinute;

    @JsonProperty("intervaltime")
    private Long intervalTimeMinute;

    @JsonProperty("routeid")
    private String routeId;

    @JsonProperty("routeno")
    private Long routeNo;

    @JsonProperty("routetp")
    private String routeTyp;

    @JsonProperty("startnodenm")
    private String startNodeName;

    @JsonProperty("startvehicletime")
    private String startVehicleTime;


}

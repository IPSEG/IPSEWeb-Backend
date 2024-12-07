package com.ipseweb.traffic.dto.subway;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StationArrivalRequest {
    private String stationName;
    private String stationLineName;
}


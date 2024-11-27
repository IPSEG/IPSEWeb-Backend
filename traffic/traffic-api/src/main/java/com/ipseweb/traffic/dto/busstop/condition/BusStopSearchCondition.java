package com.ipseweb.traffic.dto.busstop.condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusStopSearchCondition {
    private String busStopName;
    private String cityCode;
    private String busStopId;
}

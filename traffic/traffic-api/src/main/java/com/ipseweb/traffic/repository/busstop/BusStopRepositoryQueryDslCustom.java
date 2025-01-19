package com.ipseweb.traffic.repository.busstop;

import com.ipseweb.traffic.domain.bus.BusStop;
import com.ipseweb.traffic.dto.busstop.condition.BusStopSearchCondition;

import java.util.Optional;

public interface BusStopRepositoryQueryDslCustom {

    Optional<BusStop> searchBusStop(BusStopSearchCondition condition);

}

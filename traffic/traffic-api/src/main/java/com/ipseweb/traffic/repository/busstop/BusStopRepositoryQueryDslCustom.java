package com.ipseweb.traffic.repository.busstop;

import com.ipseweb.traffic.domain.bus.BusStop;
import com.ipseweb.traffic.dto.busstop.condition.BusStopSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BusStopRepositoryQueryDslCustom {

    Optional<BusStop> searchBusStop(BusStopSearchCondition condition);

    List<BusStop> searchBusStopLikeName(String name);

    Page<BusStop> searchBusStopPagingLikeName(String name, Pageable Pageable);

}

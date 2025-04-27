package com.ipseweb.traffic.repository.busstop;


import com.ipseweb.traffic.domain.bus.entity.BusStop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusStopRepository extends JpaRepository<BusStop, String>, BusStopRepositoryQueryDslCustom {

    Optional<BusStop> findByBusStopNameAndCityCodeAndBusStopId(String busStopName, String cityCode, String busStopId);

    Optional<BusStop> findByBusStopName(String busStopName);

    Page<BusStop> findBusStopByBusStopNameContaining(String busStopName, Pageable pageable);

}

package com.ipseweb.traffic.repository.busstop;


import com.ipseweb.traffic.domain.BusStop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BusStopRepository extends JpaRepository<BusStop, String> {

    Optional<BusStop> findByBusStopNameAndCityCode(String busStopName, String cityCode);

    Optional<BusStop> findByBusStopNameAndCityCodeAndBusStopId(String busStopName, String cityCode, String busStopId);

    Optional<BusStop> findByBusStopName(String busStopName);
}

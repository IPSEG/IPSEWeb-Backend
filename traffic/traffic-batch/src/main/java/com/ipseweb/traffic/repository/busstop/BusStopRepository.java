package com.ipseweb.traffic.repository.busstop;


import com.ipseweb.traffic.domain.bus.entity.BusStop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusStopRepository extends JpaRepository<BusStop, String> {

}

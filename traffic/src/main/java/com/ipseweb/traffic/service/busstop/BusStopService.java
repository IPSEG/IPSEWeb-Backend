package com.ipseweb.traffic.service.busstop;


import com.ipseweb.traffic.domain.BusStop;
import com.ipseweb.traffic.repository.busstop.BusStopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BusStopService {

    private final BusStopRepository busStopRepository;


    public List<BusStop> findBusStops() {
        return busStopRepository.findAll();
    }
}

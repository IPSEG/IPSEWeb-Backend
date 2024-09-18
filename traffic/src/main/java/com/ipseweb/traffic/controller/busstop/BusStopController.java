package com.ipseweb.traffic.controller.busstop;

import com.ipseweb.traffic.domain.BusStop;
import com.ipseweb.traffic.service.busstop.BusStopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BusStopController {

    private final BusStopService busStopService;

    @GetMapping("/api/busstops")
    public List<BusStop> busStops() {
        return busStopService.findBusStops();
    }


}

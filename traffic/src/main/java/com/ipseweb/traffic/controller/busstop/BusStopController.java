package com.ipseweb.traffic.controller.busstop;

import com.ipseweb.traffic.domain.BusStop;
import com.ipseweb.traffic.dto.ErrorResponse;
import com.ipseweb.traffic.exception.ResponseEntityFactory;
import com.ipseweb.traffic.service.busstop.BusStopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BusStopController {

    private final BusStopService busStopService;

    @GetMapping("/api/busstops")
    public ResponseEntity<ErrorResponse<List<BusStop>>> busStops() {
        List<BusStop> busStops = busStopService.findBusStops();
        return ResponseEntityFactory.success(busStops);
    }


}

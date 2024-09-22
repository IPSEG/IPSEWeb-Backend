package com.ipseweb.traffic.controller.busstop;

import com.ipseweb.traffic.domain.BusStop;
import com.ipseweb.traffic.dto.busstop.OpenApiBusStopResponse;
import com.ipseweb.traffic.dto.error.ErrorResponse;
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


    /**
     * v1. db에서 버스 정류장 정보 조회
     *
     * @return
     */
    @GetMapping("/api/v1/busstops")
    public ResponseEntity<ErrorResponse<List<BusStop>>> busStopsV1() {
        List<BusStop> busStopsV1 = busStopService.findBusStopsV1();
        return ResponseEntityFactory.success(busStopsV1);
    }

    /**
     * v2. openAPI로 버스 정류장 정보 조회
     * @return
     */
    @GetMapping("/api/v2/busstops")
    public ResponseEntity<ErrorResponse<List<OpenApiBusStopResponse>>> busStopsV2() {
        List<OpenApiBusStopResponse> busStopsV2 = busStopService.findBusStopsV2();
        return ResponseEntityFactory.success(busStopsV2);
    }
}

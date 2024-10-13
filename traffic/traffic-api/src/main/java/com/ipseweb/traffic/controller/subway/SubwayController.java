package com.ipseweb.traffic.controller.subway;

import com.ipseweb.error.Response;
import com.ipseweb.traffic.dto.station.OpenApiStationArrivalResponse;
import com.ipseweb.traffic.service.subway.SubwayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subway")
public class SubwayController {

    SubwayService subwayService;

    public SubwayController(SubwayService subwayService) {
        this.subwayService = subwayService;
    }

    @GetMapping(value = "/arrivalInfo/{stationName}")
    public ResponseEntity<Response<OpenApiStationArrivalResponse>> getStationArrivalInfo(@PathVariable String stationName) {
        return subwayService.getStationArrivalInfo(stationName);
    }

}

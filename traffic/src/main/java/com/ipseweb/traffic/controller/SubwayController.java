package com.ipseweb.traffic.controller;

import com.ipseweb.traffic.dto.StationArrivalInfoResponse;
import com.ipseweb.traffic.dto.StationArrivalRequest;
import com.ipseweb.traffic.service.SubwayService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subway")
public class SubwayController {

    SubwayService subwayService;

    public SubwayController(SubwayService subwayService) {
        this.subwayService = subwayService;
    }

    @GetMapping(value = "/arrivalInfo/{stationName}")
    public void getStationArrivalInfo(@PathVariable String stationName) {
        subwayService.getStationArrivalInfo(stationName);
    }

}

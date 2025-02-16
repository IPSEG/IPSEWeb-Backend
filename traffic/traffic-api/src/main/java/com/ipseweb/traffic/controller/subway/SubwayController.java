package com.ipseweb.traffic.controller.subway;

import com.ipseweb.error.Response;
import com.ipseweb.traffic.dto.subway.OpenApiStationArrivalResponse;
import com.ipseweb.traffic.resource.subway.SubwayResource;
import com.ipseweb.traffic.service.subway.SubwayService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Subway Arrival", description = "Subway Arrival API")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(SubwayResource.SUBWAY_ARRIVAL)
public class SubwayController {

    private final SubwayService subwayService;

    @GetMapping(value = "/v1")
    public ResponseEntity<Response<OpenApiStationArrivalResponse>> getStationArrivalInfo(@RequestParam("name") String name) {
        return subwayService.getStationArrivalInfo(name);
    }

}

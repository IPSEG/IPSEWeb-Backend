package com.ipseweb.traffic.controller.busarrival;

import com.ipseweb.error.Response;
import com.ipseweb.traffic.dto.busarrival.BusArrivalDto;
import com.ipseweb.traffic.dto.busarrival.OpenApiBusArrivalResponse;
import com.ipseweb.traffic.dto.busstop.BusStopDto;
import com.ipseweb.traffic.service.busarrival.BusArrivalService;
import com.ipseweb.traffic.service.busstop.BusStopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "BusArrival", description = "BusArrival API")
@RestController
@RequiredArgsConstructor
@Slf4j
public class BusArrivalController {

    private final BusStopService busStopService;
    private final BusArrivalService busArrivalService;

    @Operation(summary = "버스 도착 정보 조회", description = "버스 정류장에서 버스 도착 정보를 조회합니다.",
            responses = {
                    @ApiResponse(description = "JPA 버스 도착 정보 조회",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BusArrivalDto.BusArrivalInfoResponse.class)
                            ))
            })
    @PostMapping("/api/v1/bus")
    public ResponseEntity<Response<List<BusArrivalDto.BusArrivalInfoResponse>>> busArrivalInfoV1(
            @RequestBody BusArrivalDto.BusArrivalInfoRequest busArrivalInfoRequest) {

//      1. 버스 정류장 이름으로 버스 정류장 정보 조회
        BusStopDto.BusStopResponse busStopResponse = busStopService.findBusStopByNameAndCityCodeAndBusStopId(
                busArrivalInfoRequest.getBusStopName(),
                busArrivalInfoRequest.getCityCode(),
                busArrivalInfoRequest.getBusStopId()
                );

//      2. 버스 정류장 정보로 버스 도착 정보 조회
        return busArrivalService.findBusArrivalInfo(busStopResponse.getBusStopId(), busStopResponse.getCityCode());
    }
}

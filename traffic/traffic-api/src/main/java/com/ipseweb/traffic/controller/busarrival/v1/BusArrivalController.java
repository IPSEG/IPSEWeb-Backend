package com.ipseweb.traffic.controller.busarrival.v1;

import com.ipseweb.error.Response;
import com.ipseweb.exception.ResponseEntityFactory;
import com.ipseweb.traffic.dto.busarrival.BusArrivalDto;
import com.ipseweb.traffic.dto.busstop.BusStopDto;
import com.ipseweb.traffic.resource.busarrival.BusArrivalResource;
import com.ipseweb.traffic.service.busarrival.BusArrivalCacheService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Tag(name = "BusArrival", description = "BusArrival API")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(BusArrivalResource.BUS_ARRIVAL_VERSION_1)
public class BusArrivalController {

    private final BusStopService busStopService;
    private final BusArrivalCacheService busArrivalCacheService;

    @Operation(summary = "버스 도착 정보 조회", description = "버스 정류장에서 버스 도착 정보를 조회합니다.",
            responses = {
                    @ApiResponse(description = "JPA 버스 도착 정보 조회",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BusArrivalDto.BusArrivalInfoResponse.class)
                            ))
            })
    @GetMapping
    public ResponseEntity<Response<List<BusArrivalDto.BusArrivalInfoResponse>>> busArrivalInfoV1(
            @RequestParam("busStopId") String busStopId) {

//      1. 버스 정류장 이름으로 버스 정류장 정보 조회
        BusStopDto.BusStopResponse busStopResponse = busStopService.findBusStopByBusStopId(
                busStopId
        );

//      2. 버스 정류장 정보로 버스 도착 정보 조회
        LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);
        String formattedTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return ResponseEntityFactory.success(
                busArrivalCacheService.findBusArrivalInfo(busStopResponse.getBusStopId(),
                        busStopResponse.getCityCode(), formattedTime)
        );
    }
}

package com.ipseweb.traffic.controller.busstop;

import com.ipseweb.traffic.domain.BusStop;
import com.ipseweb.error.OpenApiErrorCode;
import com.ipseweb.error.Response;
import com.ipseweb.exception.ResponseEntityFactory;
import com.ipseweb.exception.TrafficException;
import com.ipseweb.traffic.dto.busstop.BusStopDto;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ipseweb.traffic.dto.busstop.BusStopDto.*;

@Tag(name = "BusStop", description = "BusStop API")
@RestController
@RequiredArgsConstructor
@Slf4j
public class BusStopController {

    private final BusStopService busStopService;


    /**
     * v1. db에서 버스 정류장 정보 조회
     *
     * @return
     */
    @Operation(summary = "모든 버스정류장 목록 조회", description = "v1, 버스 정류장 목록을 조회합니다.",
        responses = {
            @ApiResponse(description = "JPA 버스 정류장 조회",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = BusStopResponse.class)
            ))
        })
    @GetMapping("/api/v1/busstops")
    public ResponseEntity<Response<List<BusStopResponse>>> busStopsV1() {
        List<BusStopResponse> busStops = busStopService.findBusStops();
        return ResponseEntityFactory.success(busStops);
    }

    @Operation(summary = "버스정류장 이름으로 조회", description = "v1, 버스 정류장 이름으로 버스 정류장을 조회합니다.",
            responses = {
                    @ApiResponse(description = "JPA 버스 정류장 검색",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BusStopResponse.class)
                            ))
            })
    @GetMapping("/api/v1/busstop/{busStopName}")
    public ResponseEntity<Response<BusStopResponse>> busStopV1(@PathVariable String busStopName) {
        BusStopResponse busStopByName = busStopService.findBusStopByName(busStopName);
        return ResponseEntityFactory.success(busStopByName);
    }

}

package com.ipseweb.traffic.controller.busstop;

import com.ipseweb.error.Response;
import com.ipseweb.exception.ResponseEntityFactory;
import com.ipseweb.traffic.resource.busstop.BusStopResource;
import com.ipseweb.traffic.service.busstop.BusStopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ipseweb.traffic.dto.busstop.BusStopDto.BusStopResponse;

@Tag(name = "BusStop", description = "BusStop API")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(BusStopResource.BUS_STOP)
public class BusStopController {

    private final BusStopService busStopService;

    @Operation(summary = "버스정류장 이름으로 조회", description = "v1, 버스 정류장 이름으로 버스 정류장을 조회합니다.",
            responses = {
                    @ApiResponse(description = "JPA 버스 정류장 검색",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BusStopResponse.class)
                            ))
            })
    @GetMapping("/v1")
    public ResponseEntity<Response<BusStopResponse>> busStopV1(
            @RequestParam("busStopName") String busStopName,
            @RequestParam("cityCode") String cityCode,
            @RequestParam("busStopId") String busStopId) {
        BusStopResponse busStopByName = busStopService.findBusStopByNameAndCityCodeAndBusStopId(busStopName, cityCode, busStopId);
        return ResponseEntityFactory.success(busStopByName);
    }



}

package com.ipseweb.traffic.controller.busroute.v1;

import com.ipseweb.error.Response;
import com.ipseweb.exception.ResponseEntityFactory;
import com.ipseweb.traffic.resource.busroute.BusRouteResource;
import com.ipseweb.traffic.service.busroute.BusRouteCacheService;
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

import static com.ipseweb.traffic.dto.busroute.BusRouteDto.*;

@Tag(name = "BusRoute", description = "BusRoute API")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(BusRouteResource.BUS_ROUTE_VERSION_1)
public class BusRouteController {
    private final BusRouteCacheService busRouteCacheService;


    @Operation(summary = "버스 노선 정보 조회", description = "버스 노선 기본 정보를 조회합니다.",
            responses = {
                    @ApiResponse(description = "JPA 버스 노선 기본 정보 조회",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BusRouteBasicInfoResponse.class)
                            ))
            })
    @GetMapping("/basic")
    public ResponseEntity<Response<BusRouteBasicInfoResponse>> busRouteBasicInfoV1(
            @RequestParam("cityCode") String cityCode, @RequestParam("routeId") String routeId) {
        LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);
        String formattedTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return ResponseEntityFactory.success(busRouteCacheService.findBusRouteBasicInfo(cityCode, routeId, formattedTime));
    }
}

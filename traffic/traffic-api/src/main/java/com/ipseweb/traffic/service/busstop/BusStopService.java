package com.ipseweb.traffic.service.busstop;


import com.ipseweb.traffic.domain.BusStop;
import com.ipseweb.traffic.dto.busstop.BusStopDto.BusStopResponse;
import com.ipseweb.traffic.repository.busstop.BusStopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BusStopService {

    private final BusStopRepository busStopRepository;

    /**
     * 모든 버스 정류장 조화
     * @return
     */
    public List<BusStopResponse> findBusStops() {

        List<BusStop> busStopList = busStopRepository.findAll();

        return busStopList.stream().map(busStop -> new BusStopResponse(
                busStop.getBusStopId(),
                busStop.getBusStopName(),
                busStop.getCity(),
                busStop.getDetailCity())).collect(Collectors.toList());

    }

    /**
     * 버스 정류장 이름으로 버스 정류장 조회
     */
    public BusStopResponse findBusStopByName(String busStopName) {
        Optional<BusStop> busStop = busStopRepository.findByBusStopName(busStopName);

        return new BusStopResponse(
                busStop.get().getBusStopId(),
                busStop.get().getBusStopName(),
                busStop.get().getCity(),
                busStop.get().getDetailCity()
        );
    }









}

package com.ipseweb.traffic.service.busstop;


import com.ipseweb.error.CommonErrorCode;
import com.ipseweb.exception.TrafficException;
import com.ipseweb.traffic.domain.bus.BusStop;
import com.ipseweb.traffic.dto.busstop.BusStopDto.BusStopResponse;
import com.ipseweb.traffic.repository.busstop.BusStopRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class BusStopService {

    private final BusStopRepository busStopRepository;

    /**
     * 모든 버스 정류장 조화
     *
     * @return
     */
    public List<BusStopResponse> findBusStops() {
        List<BusStop> busStopList = busStopRepository.findAll();

        return busStopList.stream().map(busStop -> new BusStopResponse(
                busStop.getBusStopId(),
                busStop.getBusStopName(),
                busStop.getCityCode(),
                busStop.getCity(),
                busStop.getDetailCity())).collect(Collectors.toList());
    }

    /**
     * 버스 정류장 이름으로 버스 정류장 조회
     */
    public BusStopResponse findBusStopByNameAndCityCodeAndBusStopId(String busStopName, String cityCode, String busStopId) {
        try {
            Optional<BusStop> busStop = busStopRepository.findByBusStopNameAndCityCodeAndBusStopId(busStopName, cityCode, busStopId);

            return new BusStopResponse(
                    busStop.get().getBusStopId(),
                    busStop.get().getBusStopName(),
                    busStop.get().getCityCode(),
                    busStop.get().getCity(),
                    busStop.get().getDetailCity()
            );
        } catch (NoSuchElementException e) {
            log.error(e.getMessage().toString());
            throw new TrafficException(e, CommonErrorCode.NO_SEARCH_DATA_ERROR);
        }

    }


}

package com.ipseweb.traffic.service.busstop;


import com.ipseweb.error.CommonErrorCode;
import com.ipseweb.exception.TrafficException;
import com.ipseweb.traffic.domain.bus.BusStop;
import com.ipseweb.traffic.dto.busstop.BusStopDto;
import com.ipseweb.traffic.dto.busstop.BusStopDto.BusStopResponse;
import com.ipseweb.traffic.repository.busstop.BusStopRepository;
import com.ipseweb.traffic.resource.busstop.BusStopResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public BusStopResponse findBusStopByNameAndCityCodeAndBusStopId(String busStopName, String cityCode, String id) {
        try {
            Optional<BusStop> busStop = busStopRepository.findByBusStopNameAndCityCodeAndBusStopId(busStopName, cityCode, id);

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

    /**
     * 버스 정류장 이름으로 조회
     * @param searchBusStopName
     * @return
     */
    public BusStopDto.BusStopPagingResponse findBusStopByLikeName(Integer pageNumber, String searchBusStopName) {
        PageRequest pageRequest = PageRequest.of(pageNumber, BusStopResource.BUS_STOP_PAGE_SIZE, Sort.by(Sort.Direction.DESC, "cityCode"));
        Page<BusStop> result = busStopRepository.searchBusStopPagingLikeName(searchBusStopName, pageRequest);
        List<BusStopResponse> busStopResponseList = Optional.ofNullable(result.getContent())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(busStop -> new BusStopResponse(
                        busStop.getBusStopId(),
                        busStop.getBusStopName(),
                        busStop.getCityCode(),
                        busStop.getCity(),
                        busStop.getDetailCity()
                )).collect(Collectors.toList());

        return new BusStopDto.BusStopPagingResponse(
                busStopResponseList,
                result.hasNext(),
                result.hasPrevious(),
                result.getSize(),
                result.getNumberOfElements(),
                result.getNumber()
        );
    }


}

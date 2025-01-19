package com.ipseweb.traffic.service.busstop;


import com.ipseweb.traffic.domain.BusStop;
import com.ipseweb.traffic.dto.busstop.OpenApiBusStopData;
import com.ipseweb.traffic.dto.busstop.OpenApiBusStopResponse;
import com.ipseweb.traffic.repository.busstop.BusStopRepository;
import com.ipseweb.util.Request;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.ast.tree.AbstractUpdateOrDeleteStatement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BusStopJobService {


    private final BusStopRepository busStopRepository;

    private final int chunkSize = 1_000;

    @Value("${spring.openapi.busStop.apiKey}")
    private String apiKey;

    @Value("${spring.openapi.busStop.url.busStopInfo}")
    private String busStopInfoUrl;



    /**
     * OpenAPI를 호출해서 버스 정류장 정보 리스트를 반환합니다.
     * @return
     */
    public List<OpenApiBusStopResponse> getOpenApiBusStopResponse() {

        List<OpenApiBusStopResponse> openApiBusStopDataList = new ArrayList<>();

        int page = 1;

        while (true) {

            String url = String.format(busStopInfoUrl, page, chunkSize, apiKey);
            OpenApiBusStopResponse openApiBusStopData = Request.requestGet(url, OpenApiBusStopResponse.class);

            if (openApiBusStopData.getCurrentCount() == 0) {
                break;
            }

            openApiBusStopDataList.add(openApiBusStopData);

            page++;
        }

        return openApiBusStopDataList;
    }

    /**
     * OpenApuBusStopResonse에서 BusStop 앤티티 리스트를 생성합니다.
     */
    public List<BusStop> getBusStopList(List<OpenApiBusStopResponse> responses) {

        List<BusStop> busStopList = new ArrayList<>();

        for (OpenApiBusStopResponse response : responses) {
            List<OpenApiBusStopData> data = response.getData();

            for (OpenApiBusStopData openApiBusStopData
                    : data) {

                BusStop busStop = new BusStop(
                        openApiBusStopData.getBusStopId(),
                        openApiBusStopData.getBusStopName(),
                        openApiBusStopData.getInformationCollectDate(),
                        openApiBusStopData.getCity(),
                        openApiBusStopData.getDetailCity(),
                        openApiBusStopData.getCityCode(),
                        openApiBusStopData.getMobileCode(),
                        openApiBusStopData.getLongitude(),
                        openApiBusStopData.getLatitude()
                );

                busStopList.add(busStop);
            }
        }

        return busStopList;
    }


    /**
     *
     */
    public List<BusStop> saveAll(List<BusStop> busStopList) {
        return busStopRepository.saveAll(busStopList);
    }

}

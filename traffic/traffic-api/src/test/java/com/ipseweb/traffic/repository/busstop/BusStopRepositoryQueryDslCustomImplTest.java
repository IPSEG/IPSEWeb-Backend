package com.ipseweb.traffic.repository.busstop;

import com.ipseweb.exception.TrafficException;
import com.ipseweb.traffic.domain.bus.BusStop;
import com.ipseweb.traffic.dto.busstop.condition.BusStopSearchCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class BusStopRepositoryQueryDslCustomImplTest {

    @Autowired
    BusStopRepository busStopRepository;

    @Test
    @DisplayName("BusStopQueryDslCustomRepository 정상 테스트")
    @Transactional
    public void searchBusStop() throws Exception {
        //given
        BusStopSearchCondition busStopSearchCondition = new BusStopSearchCondition(
                "신덕1리",
                "37040",
                "ADB354000076"
        );
        Optional<BusStop> busStop = busStopRepository.searchBusStop(busStopSearchCondition);

        //when


        //then
        assertThat(busStop.isPresent()).isTrue();
    }

    @Test
    @DisplayName("BusStopQueryDslCustomRepository 에외 테스트")
    @Transactional
    public void searchBusStopNoData() throws Exception {
        //given
        BusStopSearchCondition busStopSearchCondition = new BusStopSearchCondition(
                "테스트",
                "testCityCode",
                "ADB354000076"
        );

        //when


        //then
        assertThrows(TrafficException.class, () -> {
            busStopRepository.searchBusStop(busStopSearchCondition);
        });
    }
}
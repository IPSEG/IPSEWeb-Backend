package com.ipseweb.traffic.service.busstop;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ipseweb.traffic.dto.busstop.BusStopDto.BusStopResponse;

@SpringBootTest
@Transactional
class BusStopServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    BusStopService busStopService;

    @Test
    void findBusStops() {
        List<BusStopResponse> busStops = busStopService.findBusStops();

        Assertions.assertThat(busStops.size()).isGreaterThan(0);
    }
}
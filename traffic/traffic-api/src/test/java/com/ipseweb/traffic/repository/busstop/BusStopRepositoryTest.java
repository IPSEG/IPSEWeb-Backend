package com.ipseweb.traffic.repository.busstop;

import com.ipseweb.traffic.domain.BusStop;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@SpringBootTest
@Transactional
class BusStopRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    BusStopRepository busStopRepository;


    @Test
    void findById() {
        Optional<BusStop> busStop = busStopRepository.findById("ADB354000076");

        Assertions.assertThat(busStop.get()).isNotNull();
        Assertions.assertThat(busStop.get().getBusStopName()).isEqualTo("신덕1리");
    }

    @Test
    void findByBusStopName() {
        Optional<BusStop> busStop = busStopRepository.findByBusStopName("신덕1리");

        Assertions.assertThat(busStop.get()).isNotNull();
        Assertions.assertThat(busStop.get().getBusStopId()).isEqualTo("ADB354000076");
    }

    @Test
    public void findAll() throws Exception {
        List<BusStop> all = busStopRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(10);

    }



}
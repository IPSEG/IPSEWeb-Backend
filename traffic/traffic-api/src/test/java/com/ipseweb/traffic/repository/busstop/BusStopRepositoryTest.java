package com.ipseweb.traffic.repository.busstop;

import com.ipseweb.traffic.domain.bus.BusStop;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.Sort.*;


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

    @Test
    @DisplayName("%BusStopName% 쿼리 테스트")
    public void findBusStopByBusStopNameContainingTest() throws Exception {
        //given
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Direction.DESC, "cityCode"));
        Page<BusStop> result = busStopRepository.findBusStopByBusStopNameContaining("이천역", pageRequest);

        //when
        System.out.println("전체 페이지 : " + result.getTotalPages());
        System.out.println("현재 페이지 : " + result.getNumber());
        System.out.println("페이지 크기 : " + result.getSize());
        System.out.println("현재 페이지에 나올 데이터 수 : " + result.getNumberOfElements());
        System.out.println("전체 데이터 수 : " + result.getTotalElements());
        System.out.println("이전 페이지 여부 : " + result.hasPrevious());
        System.out.println("현재 페이지가 첫 페이지 인지 여부 : " + result.isFirst());
        System.out.println("다음 페이지 여부 : " + result.hasNext());
        System.out.println("현재 페이지가 마지막 페이지 인지 여부 : " + result.isLast());

        // 다음 페이지 객체, 다음 페이지가 없으면 null
        Pageable nextPageable = result.nextPageable();

        // 이전 페이지 객체, 이전 페이지가 없으면 null
        Pageable previusPageable = result.previousPageable();

        // 조회된 데이터
        List<BusStop> content = result.getContent();
        System.out.println("조회된 데이터 존재 여부 : " + result.hasContent());

        // 정렬 정보
        Sort sort = result.getSort();

    }



}

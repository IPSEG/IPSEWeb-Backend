package com.ipseweb.traffic.repository.busstop;

import com.ipseweb.error.CommonErrorCode;
import com.ipseweb.exception.TrafficException;
import com.ipseweb.traffic.domain.bus.entity.BusStop;
import com.ipseweb.traffic.dto.busstop.condition.BusStopSearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.ipseweb.traffic.domain.bus.entity.QBusStop.busStop;
import static org.springframework.util.StringUtils.hasLength;

@RequiredArgsConstructor
public class BusStopRepositoryQueryDslCustomImpl implements BusStopRepositoryQueryDslCustom{

    private final JPAQueryFactory queryFactory;


    @Override
    public Optional<BusStop> searchBusStop(BusStopSearchCondition condition) {
        return Optional.of(queryFactory
                .selectFrom(busStop)
                .where(
                        busStopIdEq(condition.getBusStopId()),
                        cityCodeEq(condition.getCityCode()),
                        busStopNameEq(condition.getBusStopName())
                )
                .fetch()
                .stream()
                .findFirst()
                .orElseThrow(
                        () -> new TrafficException(CommonErrorCode.NO_SEARCH_DATA_ERROR)
                ));
    }

    /**
     * %name% 조회
     * @param name
     * @return
     */
    @Override
    public List<BusStop> searchBusStopLikeName(String name) {
        return queryFactory
                .selectFrom(busStop)
                .where(busStop.busStopName.contains(name))
                .fetch();
    }

    /**
     * bus_stop_name like %name% 쿼리 결과에 대한 Paging 쿼리
     * @param name
     * @param pageable
     * @return
     */
    @Override
    public Page<BusStop> searchBusStopPagingLikeName(String name, Pageable pageable) {

        List<BusStop> contents = queryFactory
                .selectFrom(busStop)
                .where(busStop.busStopName.contains(name))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalCount = queryFactory
                .select(busStop.count())
                .from(busStop)
                .where(busStop.busStopName.contains(name))
                .fetchOne();


        return new PageImpl<>(contents, pageable, totalCount);
    }


    private BooleanExpression busStopNameEq(String busStopName) {
        return hasLength(busStopName) ?  busStop.busStopName.eq(busStopName) : null;
    }

    private BooleanExpression cityCodeEq(String cityCode) {
        return hasLength(cityCode) ? busStop.cityCode.eq(cityCode) : null;
    }

    private BooleanExpression busStopIdEq(String busStopId) {
        return hasLength(busStopId) ? busStop.busStopId.eq(busStopId) : null;
    }




}

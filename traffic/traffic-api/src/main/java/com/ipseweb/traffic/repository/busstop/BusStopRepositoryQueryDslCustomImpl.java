package com.ipseweb.traffic.repository.busstop;

import com.ipseweb.error.CommonErrorCode;
import com.ipseweb.exception.TrafficException;
import com.ipseweb.traffic.domain.bus.BusStop;
import com.ipseweb.traffic.dto.busstop.condition.BusStopSearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.Optional;

import static com.ipseweb.traffic.domain.bus.QBusStop.busStop;
import static org.springframework.util.StringUtils.hasLength;

public class BusStopRepositoryQueryDslCustomImpl implements BusStopRepositoryQueryDslCustom{

    private final JPAQueryFactory queryFactory;

    public BusStopRepositoryQueryDslCustomImpl(EntityManager em) {
        this.queryFactory  = new JPAQueryFactory(em);
    }

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
                .stream().findFirst().orElseThrow(
                        () -> new TrafficException(CommonErrorCode.NO_SEARCH_DATA_ERROR)
                ));
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

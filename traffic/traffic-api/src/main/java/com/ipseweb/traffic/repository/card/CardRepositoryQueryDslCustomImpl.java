package com.ipseweb.traffic.repository.card;

import com.ipseweb.error.CardErrorCode;
import com.ipseweb.error.CommonErrorCode;
import com.ipseweb.exception.CardException;
import com.ipseweb.exception.TrafficException;
import com.ipseweb.traffic.domain.card.Card;
import com.ipseweb.traffic.dto.card.condition.CardSearchCondition;
import com.ipseweb.traffic.resource.card.type.CardType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;

import static com.ipseweb.traffic.domain.card.QBusArrivalCard.busArrivalCard;
import static com.ipseweb.traffic.domain.card.QSubwayArrivalCard.subwayArrivalCard;
import static org.springframework.util.StringUtils.hasLength;

@Slf4j
public class CardRepositoryQueryDslCustomImpl implements CardRepositoryQueryDslCustom {

    private final JPAQueryFactory queryFactory;

    public CardRepositoryQueryDslCustomImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Card searchCard(CardSearchCondition condition) {

        if (condition.getCardType().equals(CardType.BUS)) {
            return queryFactory
                    .selectFrom(busArrivalCard)
                    .where(
                            busArrivalUserIdEq(condition.getUserId()),
                            busStopIdEq(condition.getBusStopId()),
                            busStopNameEq(condition.getBusStopName()),
                            cityCodeEq(condition.getCityCode())
                    )
                    .fetch()
                    .stream()
                    .findFirst()
                    .orElseThrow(
                            () -> new TrafficException(CommonErrorCode.NO_SEARCH_DATA_ERROR)
                    );
        } else if (condition.getCardType().equals(CardType.SUBWAY)) {
            return queryFactory
                    .selectFrom(subwayArrivalCard)
                    .where(
                            subwayStationUserIdEq(condition.getUserId()),
                            subwayStationNameEq(condition.getStationName())
                    )
                    .fetch()
                    .stream()
                    .findFirst()
                    .orElseThrow(
                            () -> new TrafficException(CommonErrorCode.NO_SEARCH_DATA_ERROR)
                    );
        } else if (condition.getCardType().equals(CardType.TRAFFIC)) {
            // TODO : Traffic 화면 설정시 추가 필요
            return null;
        } else {
            log.error("Undefined Card Type : " + condition.getCardType().toValue());
            throw new CardException(CardErrorCode.UNDEFINED_CARD_TYPE);
        }
    }

    /**
     * subwayArrivalCard에서 userId비교
     * @param userId
     * @return
     */
    private BooleanExpression subwayStationUserIdEq(String userId) {
        return hasLength(userId) ? subwayArrivalCard.userId.eq(userId) : null;
    }

    /**
     * BusArrivalCard에서 userId 비교
     * @param userId
     * @return
     */
    private BooleanExpression busArrivalUserIdEq(String userId) {
        return hasLength(userId) ? busArrivalCard.userId.eq(userId) : null;
    }

    /**
     * SubwayStationCard에서 지하철 정류장 이름 비교 함수
     * @param subwayStationName
     * @return
     */
    private BooleanExpression subwayStationNameEq(String subwayStationName) {
        return hasLength(subwayStationName) ? subwayArrivalCard.stationName.eq(subwayStationName) : null;
    }


    /**
     * BusArrivalCard에서 버스 정류장 이름 비교 함수
     * @param busStopName
     * @return
     */
    private BooleanExpression busStopNameEq(String busStopName) {
        return hasLength(busStopName) ? busArrivalCard.busStopName.eq(busStopName) : null;
    }

    /**
     * BusArrivalCard에서 버스 정류장 도시 정보 코드 비교 함수
     * @param cityCode
     * @return
     */
    private BooleanExpression cityCodeEq(String cityCode) {
        return hasLength(cityCode) ? busArrivalCard.busStopName.eq(cityCode) : null;
    }

    /**
     * BusArrivalCard에서 버스 정류장 ID 비교 함수
     * @param busStopId
     * @return
     */
    private BooleanExpression busStopIdEq(String busStopId) {
        return hasLength(busStopId) ? busArrivalCard.busStopName.eq(busStopId) : null;
    }



}

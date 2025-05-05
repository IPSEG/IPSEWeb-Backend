package com.ipseweb.traffic.repository.card;

import com.ipseweb.traffic.domain.card.entity.Card;
import com.ipseweb.traffic.dto.card.CardDto;
import com.ipseweb.traffic.dto.card.condition.CardSearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.ipseweb.traffic.domain.card.entity.QBusArrivalCard.busArrivalCard;
import static com.ipseweb.traffic.domain.card.entity.QCard.card;
import static com.ipseweb.traffic.domain.card.entity.QSubwayArrivalCard.subwayArrivalCard;
import static com.ipseweb.traffic.domain.cardgroup.entity.QCardGroup.cardGroup;
import static com.ipseweb.traffic.domain.user.entity.QUser.user;
import static org.springframework.util.StringUtils.hasLength;

@RequiredArgsConstructor
public class CardRepositoryQueryDslCustomImpl implements CardRepositoryQueryDslCustom {


    private final JPAQueryFactory queryFactory;


    @Override
    public Optional<Card> searchCard(CardDto.AddRequest add) {
        switch (add.getCardType()) {
            case SUBWAY -> {
                return Optional.ofNullable(
                        queryFactory
                                .selectFrom(subwayArrivalCard)
                                .join(subwayArrivalCard.cardGroup, cardGroup)
                                .join(subwayArrivalCard.user, user)
                                .fetchJoin()
                                .where(
                                        userIdEq(add.getUserId()),
                                        cardNameEq(add.getCardName()),
                                        cardGroupIdEq(add.getCardGroupId()),
                                        subwayStationNameEq(add.getStationName())
                                )
                                .fetchOne()
                );
            }
            case BUS -> {
                return Optional.ofNullable(
                        queryFactory
                                .selectFrom(busArrivalCard)
                                .join(busArrivalCard.cardGroup, cardGroup)
                                .join(busArrivalCard.user, user)
                                .fetchJoin()
                                .where(
                                        userIdEq(add.getUserId()),
                                        cardNameEq(add.getCardName()),
                                        cardGroupIdEq(add.getCardGroupId()),
                                        busStopIdEq(add.getBusStopId()),
                                        busStopNameEq(add.getBusStopName()),
                                        busStopCityCodeEq(add.getCityCode())

                                )
                                .fetchOne()


                );
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Card> searchCardAllByCondition(CardSearchCondition condition) {
        return queryFactory
                .selectFrom(card)
                .where(userIdEq(condition.getUserId()))
                .fetch();
    }

    private BooleanExpression busStopCityCodeEq(String busStopCityCode) {
        return hasLength(busStopCityCode) ? busArrivalCard.cityCode.eq(busStopCityCode) : null;
    }
    private BooleanExpression busStopNameEq(String busStopName) {
        return hasLength(busStopName) ? busArrivalCard.busStopName.eq(busStopName) : null;
    }
    private BooleanExpression busStopIdEq(String busStopId) {
        return hasLength(busStopId) ? busArrivalCard.busStopId.eq(busStopId) : null;
    }

    private BooleanExpression subwayStationNameEq(String subwayStationName) {
        return hasLength(subwayStationName) ? subwayArrivalCard.stationName.eq(subwayStationName) : null;
    }

    private BooleanExpression cardGroupIdEq(Long cardGroupId) {
        return cardGroupId == null ? null : card.cardGroup.id.eq(cardGroupId);
    }


    private BooleanExpression userIdEq(String userId) {
        return hasLength(userId) ? card.user.userId.eq(userId) : null;
    }

    private BooleanExpression cardNameEq(String cardName) {
        return hasLength(cardName) ? card.cardName.eq(cardName) : null;
    }
}

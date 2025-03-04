package com.ipseweb.traffic.repository.card;

import com.ipseweb.traffic.domain.card.SubwayArrivalCard;
import com.ipseweb.traffic.dto.card.CardDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

import static com.ipseweb.traffic.domain.card.QSubwayArrivalCard.subwayArrivalCard;

public class SubwayArrivalCardRepositoryQueryDslCustomImpl implements SubwayArrivalCardRepositoryQueryDslCustom {
    private final JPAQueryFactory queryFactory;
    public SubwayArrivalCardRepositoryQueryDslCustomImpl(JPAQueryFactory queryFactory) {this.queryFactory = queryFactory;}

    @Override
    public List<CardDto.GetResponse> findCards(CardDto.GetRequest getRequest) {
        JPAQuery<CardDto.GetResponse> query = queryFactory
                .select(Projections.constructor(CardDto.GetResponse.class, subwayArrivalCard))
                .from(subwayArrivalCard)
                .where(
                        isUserIdEqual(getRequest.getUserId()),
                        isStationNameEqual(getRequest.getStationName())
                );

        return query.fetch();
    }

    @Override
    public SubwayArrivalCard findCardByStationNameAndUserId(CardDto.AddRequest addRequest) {
        JPAQuery<SubwayArrivalCard> query = queryFactory
                .select(subwayArrivalCard)
                .from(subwayArrivalCard)
                .where(
                        isUserIdEqual(addRequest.getUserId()),
                        isStationNameEqual(addRequest.getStationName())
                );

        return query.fetchOne();
    }

    private BooleanExpression isUserIdEqual(String userId) {
        return userId != null ? subwayArrivalCard.userId.eq(userId) : null;
    }

    private BooleanExpression isStationNameEqual(String userId) {
        return userId != null ? subwayArrivalCard.stationName.eq(userId) : null;
    }
}

package com.ipseweb.traffic.repository.cardgroup;

import com.ipseweb.traffic.domain.card.QCard;
import com.ipseweb.traffic.domain.cardgroup.CardGroup;
import com.ipseweb.traffic.dto.cardgroup.condition.CardGroupSearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ipseweb.traffic.domain.card.QCard.*;
import static com.ipseweb.traffic.domain.cardgroup.QCardGroup.cardGroup;

public class CardGroupRepositoryQueryDslCustomImpl implements CardGroupRepositoryQueryDslCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public CardGroupRepositoryQueryDslCustomImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<CardGroup> searchCardGroup(CardGroupSearchCondition condition) {

        return jpaQueryFactory
                .selectFrom(cardGroup)
                .where(
                        cardGroupNameEq(condition.getCardGroupName())
                )
                .join(cardGroup.cardList, card)
                .fetchJoin()
                .stream()
                .collect(Collectors.toList());
    }

    private BooleanExpression cardGroupNameEq(String cardGroupName) {
        return StringUtils.hasLength(cardGroupName) ? cardGroup.name.eq(cardGroupName) : null;
    }
}

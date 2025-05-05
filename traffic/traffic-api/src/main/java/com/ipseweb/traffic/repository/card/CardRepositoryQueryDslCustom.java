package com.ipseweb.traffic.repository.card;

import com.ipseweb.traffic.domain.card.entity.Card;
import com.ipseweb.traffic.dto.card.CardDto;
import com.ipseweb.traffic.dto.card.condition.CardSearchCondition;

import java.util.List;
import java.util.Optional;

public interface CardRepositoryQueryDslCustom {

    Optional<Card> searchCard(CardDto.AddRequest add);

    List<Card> searchCardAllByCondition(CardSearchCondition condition);
}

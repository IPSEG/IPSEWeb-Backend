package com.ipseweb.traffic.repository.card;

import com.ipseweb.traffic.domain.card.Card;
import com.ipseweb.traffic.dto.card.condition.CardSearchCondition;

public interface CardRepositoryQueryDslCustom {

    Card searchCard(CardSearchCondition condition);
}

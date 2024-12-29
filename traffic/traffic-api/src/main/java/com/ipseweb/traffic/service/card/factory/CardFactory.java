package com.ipseweb.traffic.service.card.factory;

import com.ipseweb.traffic.domain.card.Card;
import com.ipseweb.traffic.dto.card.CardDto;

public interface CardFactory {
    Card createCard(CardDto.Add add);
}

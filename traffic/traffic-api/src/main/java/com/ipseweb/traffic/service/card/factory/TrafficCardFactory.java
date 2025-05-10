package com.ipseweb.traffic.service.card.factory;

import com.ipseweb.traffic.domain.card.entity.Card;
import com.ipseweb.traffic.domain.cardgroup.entity.CardGroup;
import com.ipseweb.traffic.domain.user.entity.User;
import com.ipseweb.traffic.dto.card.CardDto;

public class TrafficCardFactory implements CardFactory{
    @Override
    public Card createCard(CardDto.AddRequest getRequest, User user, CardGroup cardGroup) {
        return null;
    }
}

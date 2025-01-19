package com.ipseweb.traffic.service.card.factory;

import com.ipseweb.traffic.domain.card.Card;
import com.ipseweb.traffic.domain.card.SubwayArrivalCard;
import com.ipseweb.traffic.dto.card.CardDto;

public class SubwayArrivalCardFactory implements CardFactory{
    @Override
    public Card createCard(CardDto.Add add) {
        return new SubwayArrivalCard(
                add.getStationName()
        );
    }
}

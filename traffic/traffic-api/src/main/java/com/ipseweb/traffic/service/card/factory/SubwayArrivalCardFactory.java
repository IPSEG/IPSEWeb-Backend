package com.ipseweb.traffic.service.card.factory;

import com.ipseweb.traffic.domain.card.entity.Card;
import com.ipseweb.traffic.domain.card.entity.SubwayArrivalCard;
import com.ipseweb.traffic.dto.card.CardDto;

public class SubwayArrivalCardFactory implements CardFactory{
    @Override
    public Card createCard(CardDto.AddRequest addRequest) {
        return SubwayArrivalCard.builder()
                .userId(addRequest.getUserId())
                .stationName(addRequest.getStationName())
                .build();
    }
}

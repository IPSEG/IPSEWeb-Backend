package com.ipseweb.traffic.service.card.factory;

import com.ipseweb.traffic.domain.card.entity.BusArrivalCard;
import com.ipseweb.traffic.domain.card.entity.Card;
import com.ipseweb.traffic.dto.card.CardDto;


public class BusArrivalCardFactory implements CardFactory{
    @Override
    public Card createCard(CardDto.AddRequest getRequest) {
        return BusArrivalCard.builder()
                .cardName(getRequest.getCardName())
                .busStopName(getRequest.getBusStopName())
                .cityCode(getRequest.getCityCode())
                .busStopId(getRequest.getBusStopId())
                .build();
    }
}

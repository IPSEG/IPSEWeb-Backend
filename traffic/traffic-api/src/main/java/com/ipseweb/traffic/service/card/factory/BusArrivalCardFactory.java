package com.ipseweb.traffic.service.card.factory;

import com.ipseweb.traffic.domain.card.BusArrivalCard;
import com.ipseweb.traffic.domain.card.Card;
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

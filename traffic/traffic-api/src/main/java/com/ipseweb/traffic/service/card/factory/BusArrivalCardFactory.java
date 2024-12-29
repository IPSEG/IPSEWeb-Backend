package com.ipseweb.traffic.service.card.factory;

import com.ipseweb.traffic.domain.card.BusArrivalCard;
import com.ipseweb.traffic.domain.card.Card;
import com.ipseweb.traffic.dto.card.CardDto;


public class BusArrivalCardFactory implements CardFactory{
    @Override
    public Card createCard(CardDto.Add add) {
        return new BusArrivalCard(
                add.getCardName(),
                add.getBusStopName(),
                add.getCityCode(),
                add.getBusStopId()
        );
    }
}

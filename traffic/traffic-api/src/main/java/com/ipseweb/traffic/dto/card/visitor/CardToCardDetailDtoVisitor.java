package com.ipseweb.traffic.dto.card.visitor;

import com.ipseweb.traffic.domain.card.BusArrivalCard;
import com.ipseweb.traffic.domain.card.SubwayArrivalCard;
import com.ipseweb.traffic.dto.card.CardDto;

public class CardToCardDetailDtoVisitor implements CardVisitor {

    @Override
    public CardDto.BusArrivalCardDetail visitor(BusArrivalCard busArrivalCard) {
        return new CardDto.BusArrivalCardDetail(
                busArrivalCard.getCardId(),
                busArrivalCard.getCardName(),
                busArrivalCard.getBusStopName(),
                busArrivalCard.getCityCode(),
                busArrivalCard.getBusStopId()
        );
    }

    @Override
    public CardDto.SubwayArrivalCardDetail visitor(SubwayArrivalCard subwayArrivalCard) {
        return new CardDto.SubwayArrivalCardDetail(
                subwayArrivalCard.getCardId(),
                subwayArrivalCard.getCardName(),
                subwayArrivalCard.getStationName()
        );

    }
}

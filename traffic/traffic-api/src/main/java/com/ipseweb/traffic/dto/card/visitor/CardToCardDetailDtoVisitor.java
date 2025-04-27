package com.ipseweb.traffic.dto.card.visitor;

import com.ipseweb.traffic.domain.card.entity.BusArrivalCard;
import com.ipseweb.traffic.domain.card.entity.SubwayArrivalCard;
import com.ipseweb.traffic.domain.card.visitor.CardVisitor;
import com.ipseweb.traffic.dto.card.CardDto;

public class CardToCardDetailDtoVisitor implements CardVisitor<CardDto.CardDetail> {

    @Override
    public CardDto.BusArrivalCardDetail visit(BusArrivalCard busArrivalCard) {
        return new CardDto.BusArrivalCardDetail(
                busArrivalCard.getCardId(),
                busArrivalCard.getCardName(),
                busArrivalCard.getBusStopName(),
                busArrivalCard.getCityCode(),
                busArrivalCard.getBusStopId()
        );
    }

    @Override
    public CardDto.SubwayArrivalCardDetail visit(SubwayArrivalCard subwayArrivalCard) {
        return new CardDto.SubwayArrivalCardDetail(
                subwayArrivalCard.getCardId(),
                subwayArrivalCard.getCardName(),
                subwayArrivalCard.getStationName()
        );

    }

}

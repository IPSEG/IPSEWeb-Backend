package com.ipseweb.traffic.dto.card.visitor;

import com.ipseweb.traffic.domain.card.entity.BusArrivalCard;
import com.ipseweb.traffic.domain.card.entity.SubwayArrivalCard;
import com.ipseweb.traffic.domain.card.type.CardType;
import com.ipseweb.traffic.domain.card.visitor.CardVisitor;
import com.ipseweb.traffic.dto.card.CardDto;

public class CardToCardBasicDtoVisitor implements CardVisitor<CardDto.CardBasic> {


    @Override
    public CardDto.CardBasic visit(BusArrivalCard busArrivalCard) {
        return new CardDto.BusArrivalCardBasic(
                busArrivalCard.getCardId(),
                busArrivalCard.getCardName(),
                busArrivalCard.getUserId(),
                CardType.from(busArrivalCard.getCardType()),
                busArrivalCard.getBusStopName(),
                busArrivalCard.getDetailCity(),
                busArrivalCard.getCity(),
                busArrivalCard.getCityCode(),
                busArrivalCard.getBusStopId()
        );
    }

    @Override
    public CardDto.CardBasic visit(SubwayArrivalCard subwayArrivalCard) {
        return new CardDto.SubwayArrivalCardBasic(
                subwayArrivalCard.getCardId(),
                subwayArrivalCard.getCardName(),
                subwayArrivalCard.getUserId(),
                CardType.from(subwayArrivalCard.getCardType()),
                subwayArrivalCard.getStationName()
        );
    }
}

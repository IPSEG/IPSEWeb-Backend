package com.ipseweb.traffic.dto.card.visitor;

import com.ipseweb.traffic.domain.card.BusArrivalCard;
import com.ipseweb.traffic.domain.card.SubwayArrivalCard;
import com.ipseweb.traffic.dto.card.CardDto;

public interface CardVisitor {

    CardDto.BusArrivalCardDetail visitor(BusArrivalCard busArrivalCard);

    CardDto.SubwayArrivalCardDetail visitor(SubwayArrivalCard subwayArrivalCard);
}

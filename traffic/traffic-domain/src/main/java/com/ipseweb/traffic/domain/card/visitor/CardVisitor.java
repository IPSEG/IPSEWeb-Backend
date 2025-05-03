package com.ipseweb.traffic.domain.card.visitor;

import com.ipseweb.traffic.domain.card.entity.BusArrivalCard;
import com.ipseweb.traffic.domain.card.entity.SubwayArrivalCard;

public interface CardVisitor<R> {

    R visit(BusArrivalCard busArrivalCard);
    R visit(SubwayArrivalCard subwayArrivalCard);
}

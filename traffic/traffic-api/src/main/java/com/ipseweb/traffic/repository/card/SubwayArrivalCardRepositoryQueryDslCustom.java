package com.ipseweb.traffic.repository.card;

import com.ipseweb.traffic.domain.card.entity.SubwayArrivalCard;
import com.ipseweb.traffic.dto.card.CardDto;

import java.util.List;

public interface SubwayArrivalCardRepositoryQueryDslCustom {
    List<CardDto.GetResponse> findCards(CardDto.GetRequest getRequest);
    SubwayArrivalCard findCardByStationNameAndUserId(CardDto.AddRequest addRequest);
}

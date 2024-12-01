package com.ipseweb.traffic.domain.card;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("Subway")
public class SubwayArrivalCard extends Card {

    private String stationName;
}

package com.ipseweb.traffic.domain.card;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@DiscriminatorValue("Subway")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubwayArrivalCard extends Card {

    private String stationName;
}

package com.ipseweb.traffic.domain.card;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@DiscriminatorValue("Bus")
@Getter
public class BusArrivalCard extends Card{

    private String busStopName;

    private String cityCode;

    private String busStopId;
}

package com.ipseweb.traffic.domain.card;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@DiscriminatorValue("Bus")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BusArrivalCard extends Card{

    private String busStopName;

    private String cityCode;

    private String busStopId;

    public BusArrivalCard(String cardName, String busStopName, String cityCode, String busStopId) {
        super(cardName);
        this.busStopName = busStopName;
        this.cityCode = cityCode;
        this.busStopId = busStopId;
    }
}

package com.ipseweb.traffic.domain.card.entity;


import com.ipseweb.traffic.domain.card.type.CardType;
import com.ipseweb.traffic.domain.card.visitor.CardVisitor;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue(value = CardType.Values.BUS)
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class BusArrivalCard extends Card{

    private String busStopName;

    private String cityCode;

    private String busStopId;

    @Override
    public <R> R accept(CardVisitor<R> visitor) {
        return visitor.visit(this);
    }
}

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
@Getter
@DiscriminatorValue(value = CardType.Values.SUBWAY)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class SubwayArrivalCard extends Card {

    private String stationName;

    @Override
    public <R> R accept(CardVisitor<R> visitor) {
        return visitor.visit(this);
    }
}

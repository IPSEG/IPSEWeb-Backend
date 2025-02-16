package com.ipseweb.traffic.domain.card;

import com.ipseweb.traffic.dto.card.CardDto;
import com.ipseweb.traffic.dto.card.visitor.CardVisitor;
import com.ipseweb.traffic.resource.card.type.CardType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
//@Table(name = "subway_card_info")
@Getter
@DiscriminatorValue(value = CardType.Values.SUBWAY)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubwayArrivalCard extends Card {

    private String stationName;

    @Override
    public CardDto.CardDetail accept(CardVisitor visitor) {
        return visitor.visitor(this);
    }
}

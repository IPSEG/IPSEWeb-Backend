package com.ipseweb.traffic.domain.card;


import com.ipseweb.traffic.dto.card.CardDto;
import com.ipseweb.traffic.dto.card.visitor.CardVisitor;
import com.ipseweb.traffic.resource.card.type.CardType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
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
    public CardDto.CardDetail accept(CardVisitor visitor) {
        return visitor.visitor(this);
    }
}

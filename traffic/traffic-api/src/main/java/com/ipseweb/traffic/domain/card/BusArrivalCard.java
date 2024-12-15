package com.ipseweb.traffic.domain.card;


import com.ipseweb.traffic.dto.card.CardDto;
import com.ipseweb.traffic.dto.card.visitor.CardVisitor;
import com.ipseweb.traffic.resource.card.type.CardType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@DiscriminatorValue(value = CardType.Values.BUS)
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

    @Override
    public CardDto.CardDetail accept(CardVisitor visitor) {
        return visitor.visitor(this);
    }
}

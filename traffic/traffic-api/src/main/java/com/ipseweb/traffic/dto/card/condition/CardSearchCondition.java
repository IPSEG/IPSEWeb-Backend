package com.ipseweb.traffic.dto.card.condition;

import com.ipseweb.traffic.resource.card.type.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardSearchCondition {

    private CardType cardType;
    private String cardName;
    private String userId;
    private Long groupId;

    private String busStopId;
    private String busStopName;

    private String subwayId;
    private String stationName;
    private String cityCode;
}

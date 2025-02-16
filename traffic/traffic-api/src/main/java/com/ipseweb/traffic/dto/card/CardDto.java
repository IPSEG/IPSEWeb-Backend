package com.ipseweb.traffic.dto.card;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ipseweb.traffic.resource.card.type.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@Data
public class CardDto {


    public record CardIdAndNameResponse(Long id, String name) {

    }

    ;

    public record MultipleCardIdAndNameResponse(List<CardIdAndNameResponse> list) {

    }

    ;

    /**
     * TODO : 일단 간단하게 가자.
     */

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Add {
        private String userId;
        private String cardName;
        private Long cardGroupId;
        private CardType cardType;
        private String busStopId;
        private String busStopName;
        private String subwayId;
        private String stationName;
        private String cityCode;
    }


    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static abstract class CardDetail {
        private Long id;
        private String name;
    }

    @Data
    @NoArgsConstructor
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class BusArrivalCardDetail extends CardDetail {
        public BusArrivalCardDetail(Long id, String cardName, String busStopName, String cityCode, String busStopId) {
            super(id, cardName);
            this.busStopName = busStopName;
            this.cityCode = cityCode;
            this.busStopId = busStopId;
        }

        private String busStopName;

        private String cityCode;

        private String busStopId;

    }

    @Data
    @NoArgsConstructor
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class SubwayArrivalCardDetail extends CardDetail {
        private String stationName;

        public SubwayArrivalCardDetail(Long id, String cardName, String stationName) {
            super(id, cardName);
            this.stationName = stationName;
        }
    }


}


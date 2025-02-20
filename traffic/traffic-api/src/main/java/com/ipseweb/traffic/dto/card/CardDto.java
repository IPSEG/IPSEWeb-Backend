package com.ipseweb.traffic.dto.card;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ipseweb.traffic.domain.card.SubwayArrivalCard;
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



    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class AddRequest {
        private String userId;
        private String cardName;
        private Long cardGroupId;
        private String cardType;
        private String busStopId;
        private String busStopName;
        private String subwayId;
        private String stationName;
        private String cityCode;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class GetRequest {
        private String userId;
        private String cardName;
        private String stationName;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class GetResponse {
        private Long cardId;
        private String cardName;
        private String userId;
        private String stationName;

        public GetResponse(SubwayArrivalCard card) {
            this.cardId = card.getCardId();
            this.cardName = card.getCardName();
            this.userId = card.getUserId();
            this.stationName = card.getStationName();
        }
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


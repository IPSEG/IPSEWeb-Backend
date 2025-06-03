package com.ipseweb.traffic.dto.card;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ipseweb.traffic.domain.card.type.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@Data
public class CardDto {


    public record CardIdAndNameResponse(Long id, String name, CardType cardType) {
    }
    ;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class AddRequest {
        private String userId;
        private String cardName;
        private Long cardGroupId;
        private CardType cardType;

        // bus_stop
        private String busStopId;
        private String busStopName;
        private String cityCode;

        // subway
        private String subwayId;
        private String stationName;

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
    public static abstract class CardBasic {
        private Long cardId;
        private String cardName;
        private String userId;



    }

    @Data
    @NoArgsConstructor
    public static class SubwayArrivalCardBasic extends CardBasic {

        private String stationName;

        public SubwayArrivalCardBasic(Long cardId, String cardName, String userId, String subwayStationName) {
            super(cardId, cardName, userId);
            this.stationName = subwayStationName;
        }
    }

    @Data
    @NoArgsConstructor
    public static class BusArrivalCardBasic extends CardBasic {
        private String busStopName;
        private String cityCode;
        private String busStopId;

        public BusArrivalCardBasic(Long cardId, String cardName, String userId, String busStopId, String busStopName, String cityCode) {
            super(cardId, cardName, userId);
            this.busStopId = busStopId;
            this.busStopName = busStopName;
            this.cityCode = cityCode;
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


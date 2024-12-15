package com.ipseweb.traffic.dto.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class CardDto {

    public record CardDetailResponse(){

    };

    public record CardIdAndNameResponse(Long id, String name){

    };

    public record MultipleCardIdAndNameResponse(List<CardIdAndNameResponse> list){

    };



    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static abstract class CardDetail {
        private Long id;
        private String name;

    }

    @Data
    @NoArgsConstructor
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
    public static class SubwayArrivalCardDetail extends CardDetail {
        private String stationName;

        public SubwayArrivalCardDetail(Long id, String cardName, String stationName) {
            super(id, cardName);
            this.stationName = stationName;
        }
    }


}


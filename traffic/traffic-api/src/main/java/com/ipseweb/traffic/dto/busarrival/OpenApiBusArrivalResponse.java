package com.ipseweb.traffic.dto.busarrival;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ipseweb.error.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenApiBusArrivalResponse {
    @JsonProperty("response")
    private Response response;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        @JsonProperty("header")
        private OpenApiBusArrivalHeader header;

        @JsonProperty("body")
        private OpenApiBusArrivalBody body;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OpenApiBusArrivalHeader{
        @JsonProperty("resultCode")
        private String resultCode;
        @JsonProperty("resultMsg")
        private String resultMsg;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OpenApiBusArrivalBody{
        @JsonProperty("items")
        private OpenApiBusArrivalDatas items;

        @JsonProperty("numOfRows")
        private Integer numOfRows;

        @JsonProperty("pageNo")
        private Integer pageNo;

        @JsonProperty("totalCount")
        private Integer totalCount;

    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OpenApiBusArrivalDatas {
        @JsonProperty("item")
        private List<OpenApiBusArrivalData> item;
    }
}

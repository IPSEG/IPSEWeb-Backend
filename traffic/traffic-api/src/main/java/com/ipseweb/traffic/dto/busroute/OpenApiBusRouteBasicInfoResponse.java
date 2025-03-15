package com.ipseweb.traffic.dto.busroute;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenApiBusRouteBasicInfoResponse {
    @JsonProperty("response")
    private Response response;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        @JsonProperty("header")
        private OpenApiBusRouteBasicInfoHeader header;

        @JsonProperty("body")
        private OpenApiBusRouteBasicInfoBody body;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OpenApiBusRouteBasicInfoHeader {
        @JsonProperty("resultCode")
        private String resultCode;
        @JsonProperty("resultMsg")
        private String resultMsg;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OpenApiBusRouteBasicInfoBody {
        @JsonProperty("items")
        private OpenApiBusRouteBasicInfoDatas items;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class OpenApiBusRouteBasicInfoDatas {
        @JsonProperty("item")
        private OpenApiBusRouteBasicInfoData item;
    }
}

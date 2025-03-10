package com.ipseweb.traffic.dto.busarrival;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;
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
    public static class OpenApiBusArrivalHeader {
        @JsonProperty("resultCode")
        private String resultCode;
        @JsonProperty("resultMsg")
        private String resultMsg;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OpenApiBusArrivalBody {
        @JsonProperty("items")
        @JsonDeserialize(using = EmptyStringAsNullDatasDeserializer.class)
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class OpenApiBusArrivalDatas {
        @JsonProperty("item")
        @JsonDeserialize(using = EmptyStringAsNullListDeserializer.class)
        private List<OpenApiBusArrivalData> item;
    }


    /**
     * OpenApiBusArrivalDatas custom JsonDeserializer
     */
    public static class EmptyStringAsNullDatasDeserializer extends JsonDeserializer<OpenApiBusArrivalDatas> {
        @Override
        public OpenApiBusArrivalDatas deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            JsonNode node = p.readValueAsTree();

            if (node == null || node.isNull() || (node.isTextual() && node.asText().isEmpty())) {
                return new OpenApiBusArrivalDatas(new ArrayList<>());
            }

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.treeToValue(node, OpenApiBusArrivalDatas.class);
        }
    }

    /**
     * List<OpenApiBusArrivalData> Custom JsonDeserializer
     */
    public static class EmptyStringAsNullListDeserializer extends JsonDeserializer<List<OpenApiBusArrivalData>> {
        @Override
        public List<OpenApiBusArrivalData> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            JsonNode node = p.readValueAsTree();

            if (node == null || node.isNull() || (node.isTextual() && node.asText().isEmpty())) {
                return new ArrayList<>();
            }

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(node.toString(), objectMapper.getTypeFactory().constructCollectionType(List.class, OpenApiBusArrivalData.class));
        }
    }
}

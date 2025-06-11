package com.ipseweb.traffic.dto.cardgroup;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ipseweb.traffic.dto.card.CardDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CardGroupDto {
    public record CardGroupResponse(Long id, String name, List<CardDto.CardBasic> cards) {

    };

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Add {
        private String cardGroupName;
    }

}

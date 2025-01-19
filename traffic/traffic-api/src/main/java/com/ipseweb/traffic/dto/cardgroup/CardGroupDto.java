package com.ipseweb.traffic.dto.cardgroup;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ipseweb.traffic.dto.card.CardDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CardGroupDto {

    //  TODO : 초기 스펙에서는 카드그룹 조회시, 카드 상세 정보는 노출하지 않는다.
    public record CardGroupResponse(Long id, String name, CardDto.MultipleCardIdAndNameResponse multiple) {

    };

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Add {
        private String cardGroupName;
    }

}

package com.ipseweb.traffic.dto.cardgroup;

import com.ipseweb.traffic.dto.card.CardDto;
import com.ipseweb.traffic.dto.card.CardDto.CardIdAndNameResponse;

public class CardGroupDto {

    //  TODO : 초기 스펙에서는 카드그룹 조회시, 카드 상세 정보는 노출하지 않는다.
    public record CardGroupResponse(Long id, String name, CardDto.MultipleCardIdAndNameResponse multiple) {

    };
}

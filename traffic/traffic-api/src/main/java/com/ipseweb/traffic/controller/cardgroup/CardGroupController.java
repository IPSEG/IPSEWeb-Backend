package com.ipseweb.traffic.controller.cardgroup;


import com.ipseweb.traffic.dto.busstop.BusStopDto;
import com.ipseweb.traffic.dto.cardgroup.CardGroupDto;
import com.ipseweb.traffic.dto.cardgroup.condition.CardGroupSearchCondition;
import com.ipseweb.traffic.resource.cardgroup.CardGroupResources;
import com.ipseweb.traffic.service.cardgroup.CardGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(CardGroupResources.CARD_GROUP)
public class CardGroupController {

    private final CardGroupService cardGroupService;

    @Operation(summary = "카드 이름으로 카드 목록 조회", description = "v1, 카드 이름으로 카드 목록을 조회합니다.",
            responses = {
                    @ApiResponse(description = "JPA 버스 정류장 검색",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CardGroupDto.CardGroupResponse.class)
                            ))
            })
    @RequestBody( content = @Content(
            examples = {
                    @ExampleObject(name = "request", value = """
                                    {
                                        "name":"String"
                                    }
                                    """
                    )
            }
    ))
    @GetMapping("/v1")
    public List<CardGroupDto.CardGroupResponse> getCardGroupList(@Param("name") String name) {
//      TODO :  Jackson의 동작: 런타임 객체의 타입을 기준으로 직렬화하기 때문에 실제 클래스의 필드가 자동으로 포함됩니다.
//      이거 진짜인지 확인해야됨. 그리고 이걸 주제로 velog 작성하자.
        CardGroupSearchCondition condition = new CardGroupSearchCondition(name);
        return cardGroupService.getCardGroupList(condition);
    }

}

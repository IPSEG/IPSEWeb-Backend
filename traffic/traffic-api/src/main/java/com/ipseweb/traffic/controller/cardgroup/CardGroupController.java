package com.ipseweb.traffic.controller.cardgroup;


import com.ipseweb.error.Response;
import com.ipseweb.exception.ResponseEntityFactory;
import com.ipseweb.traffic.dto.cardgroup.CardGroupDto;
import com.ipseweb.traffic.dto.cardgroup.condition.CardGroupSearchCondition;
import com.ipseweb.traffic.resource.cardgroup.CardGroupResources;
import com.ipseweb.traffic.service.cardgroup.CardGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @GetMapping("/v1")
    public ResponseEntity<Response<List<CardGroupDto.CardGroupResponse>>> getCardGroupList(@Param("name") String name) {
        CardGroupSearchCondition condition = new CardGroupSearchCondition(name);
        return ResponseEntityFactory.success(cardGroupService.getCardGroupList(condition));
    }


    @Operation(summary = "카드 그룹 생성", description = "v1, 카드 그룹을 생성합니다.",
            responses = {
                    @ApiResponse(description = "카드 그룹 추가",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CardGroupDto.Add.class)
                            ))
            })
    @PostMapping("/v1/add")
    public ResponseEntity<Response> addCardGroup(@RequestBody CardGroupDto.Add add ) {
        cardGroupService.addCardGroup(add);
        return ResponseEntityFactory.empty();
    }





}

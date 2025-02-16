package com.ipseweb.traffic.controller.card;

import com.ipseweb.error.Response;
import com.ipseweb.exception.ResponseEntityFactory;
import com.ipseweb.traffic.dto.card.CardDto;
import com.ipseweb.traffic.resource.card.CardResource;
import com.ipseweb.traffic.service.card.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(CardResource.CARD)
public class CardController {

    private final CardService cardService;

    @Operation(summary = "카드 상세 정보 조회", description = "v1, 카드 ID로 카드 상세 정보를 조회합니다.",
            responses = {
                    @ApiResponse(description = "JPA 카드 상세 정보 조회",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CardDto.CardDetail.class)
                            ))
            })
    @GetMapping("/v1")
    public ResponseEntity<Response<CardDto.CardDetail>> getCard(@RequestParam("id") Long id) {
        return ResponseEntityFactory.success(cardService.getCard(id));
    }


    @Operation(summary = "카드 추가", description = "v1, 카드를 추가합니다.",
            responses = {
                    @ApiResponse(description = "JPA 카드 추가",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CardDto.Add.class)
                            )
                    )

            })
    @PostMapping("/v1/add")
    public ResponseEntity<Response> addCard(@RequestBody CardDto.Add add) {
        cardService.addCard(add);
        return ResponseEntityFactory.empty();
    }


}

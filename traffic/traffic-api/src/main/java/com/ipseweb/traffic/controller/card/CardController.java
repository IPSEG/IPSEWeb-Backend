package com.ipseweb.traffic.controller.card;

import com.ipseweb.error.Response;
import com.ipseweb.exception.ResponseEntityFactory;
import com.ipseweb.traffic.dto.busstop.BusStopDto;
import com.ipseweb.traffic.dto.card.CardDto;
import com.ipseweb.traffic.resource.card.CardResource;
import com.ipseweb.traffic.service.card.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(CardResource.CARD)
public class CardController {

    private final CardService cardService;
    @Operation(summary = "카드 ID로 카드 상세 정보 조회", description = "v1, 카드 ID로 카드 상세 정보를 조회합니다.",
            responses = {
                    @ApiResponse(description = "JPA 카드 상세 정보 조회",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CardDto.CardDetailResponse.class)
                            ))
            })
    @RequestBody( content = @Content(
            examples = {
                    @ExampleObject(name = "request", value = """
                                    {
                                        "id":"Long"
                                    }
                                    """
                    )
            }
    ))
    @GetMapping("/v1")
    public ResponseEntity<Response<CardDto.CardDetail>> getCard(@RequestParam("id") Long id) {
        return ResponseEntityFactory.success(cardService.getCard(id));
    }
}

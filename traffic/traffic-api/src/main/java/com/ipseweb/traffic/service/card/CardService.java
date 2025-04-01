package com.ipseweb.traffic.service.card;

import com.ipseweb.error.CardErrorCode;
import com.ipseweb.error.CardGroupErrorCode;
import com.ipseweb.exception.CardException;
import com.ipseweb.exception.CardGroupException;
import com.ipseweb.traffic.domain.card.Card;
import com.ipseweb.traffic.domain.cardgroup.CardGroup;
import com.ipseweb.traffic.dto.card.CardDto;
import com.ipseweb.traffic.dto.card.condition.CardSearchCondition;
import com.ipseweb.traffic.dto.card.visitor.CardToCardDetailDtoVisitor;
import com.ipseweb.traffic.repository.card.CardRepository;
import com.ipseweb.traffic.repository.cardgroup.CardGroupRepository;
import com.ipseweb.traffic.service.card.factory.CardFactoryProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardService {

    private final CardRepository cardRepository;
    private final CardGroupRepository cardGroupRepository;

    /**
     * 카드 상세 정보 조회
     * @param cardId
     * @return
     */
    public CardDto.CardDetail getCard(Long cardId) {
        return cardRepository.findById(cardId).orElseThrow(() -> {
            log.error("Card is not exist. id : {}", cardId);
            throw new CardException(CardErrorCode.CARD_IS_NOT_EXIST);
        }).accept(new CardToCardDetailDtoVisitor());
    }

    /**
     * 카드 목록 조회
     * @param getRequest
     * @return
     */
    public List<CardDto.CardDetail> getCardList(CardDto.GetRequest getRequest) {

        List<CardDto.CardDetail> collect = cardGroupRepository.findById(getRequest.getCardGroupId())
                .map(cardGroup -> cardRepository.findAllByCardGroupAndUserId(cardGroup, getRequest.getUserId()))
                .orElseGet(() -> Collections.emptyList())
                .stream()
                .map(c -> c.accept(new CardToCardDetailDtoVisitor()))
                .collect(Collectors.toList());

        return collect;


        /**
         * 카드그룹내에 등록된 카드가 없을 수 있다.
        if(result == null || result.isEmpty()) {
            log.error("Card is not exist. request Info : {}", getRequest);
            throw new CardException(CardErrorCode.CARD_IS_NOT_EXIST);
        }
         */
    }

    /**
     * 카드 추가
     * @param addRequest
     */
    public void addCard(CardDto.AddRequest addRequest) {
        CardSearchCondition condition = new CardSearchCondition(
                addRequest.getCardType(),
                addRequest.getCardName(),
                addRequest.getUserId(),
                addRequest.getCardGroupId(),
                addRequest.getBusStopId(),
                addRequest.getBusStopName(),
                addRequest.getSubwayId(),
                addRequest.getStationName(),
                addRequest.getCityCode()
        );

        // 동일한 카드는 여러개 존재할 수 있으나, 동일한 카드 그룹 내에는 존재할 수 없다.
        Card searchCard = cardRepository.searchCard(condition);

        if(searchCard != null) {
            log.error("Card is already exists.");
            throw new CardException(CardErrorCode.CARD_IS_ALREADY_EXIST);
        }

        Long cardGroupId = Optional.ofNullable(addRequest.getCardGroupId())
                .filter(id -> id != 0)
                .orElseGet(
                        () ->
                                cardGroupRepository.findCardGroupByName("default")
                                        .map(CardGroup::getId)
                                        .orElseThrow(() -> new CardGroupException(CardGroupErrorCode.CARD_GROUP_IS_NOT_EXIST))
                );
        log.info("CardGroup id is " + cardGroupId);

        // Card Entity 생성 및 저장
        Card card = CardFactoryProvider.getFactory(addRequest.getCardType()).createCard(addRequest);
        cardRepository.save(card);
    }
}

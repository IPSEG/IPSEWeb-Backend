package com.ipseweb.traffic.service.card;

import com.ipseweb.error.CardErrorCode;
import com.ipseweb.exception.CardException;
import com.ipseweb.traffic.domain.card.Card;
import com.ipseweb.traffic.domain.cardgroup.CardGroup;
import com.ipseweb.traffic.dto.card.CardDto;
import com.ipseweb.traffic.dto.card.visitor.CardToCardDetailDtoVisitor;
import com.ipseweb.traffic.repository.card.CardRepository;
import com.ipseweb.traffic.repository.card.SubwayArrivalCardRepository;
import com.ipseweb.traffic.repository.cardgroup.CardGroupRepository;
import com.ipseweb.traffic.resource.card.type.CardType;
import com.ipseweb.traffic.service.card.factory.CardFactoryProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardService {

    private final CardRepository cardRepository;
    private final CardGroupRepository cardGroupRepository;
    private final SubwayArrivalCardRepository subwayArrivalCardRepository;

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
    public List<CardDto.GetResponse> getCardList(CardDto.GetRequest getRequest) {
        // request 정보 기반으로 카드 목록 조회
        List<CardDto.GetResponse> result = subwayArrivalCardRepository.findCards(getRequest);

        if(result == null || result.isEmpty()) {
            log.error("Card is not exist. request Info : {}", getRequest);
            throw new CardException(CardErrorCode.CARD_IS_NOT_EXIST);
        }

        return result;
    }

    /**
     * 카드 추가
     * @param addRequest
     */
    public void addCard(CardDto.AddRequest addRequest) {
        // userId, stationName으로 이미 등록된 정보가 있는지 조회
        Card searchCard = subwayArrivalCardRepository.findCardByStationNameAndUserId(addRequest);

        if(searchCard != null) {
            throw new CardException(CardErrorCode.CARD_IS_ALREADY_EXIST);
        }

        // addRequest의 cardGroupId 정보 조회 cardGroup 정보를 전달 안했을 시 default 값 지정
        CardGroup cardGroup = cardGroupRepository.findById(addRequest.getCardGroupId()).orElse(null);

        // TODO : 임시로 1 설정하도록 지정 추후 카드 그룹 관리 서비스 추가 필요
        if(cardGroup == null) {
            addRequest.setCardGroupId(1L);
        }

        // Card Entity 생성 및 저장
        Card card = CardFactoryProvider.getFactory(CardType.from(addRequest.getCardType())).createCard(addRequest);
        cardRepository.save(card);
    }
}

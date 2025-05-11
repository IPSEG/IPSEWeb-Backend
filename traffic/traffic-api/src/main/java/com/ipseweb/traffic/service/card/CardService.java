package com.ipseweb.traffic.service.card;

import com.ipseweb.error.CardErrorCode;
import com.ipseweb.error.CardGroupErrorCode;
import com.ipseweb.exception.CardException;
import com.ipseweb.exception.CardGroupException;
import com.ipseweb.traffic.domain.card.entity.Card;
import com.ipseweb.traffic.domain.cardgroup.entity.CardGroup;
import com.ipseweb.traffic.dto.card.CardDto;
import com.ipseweb.traffic.dto.card.condition.CardSearchCondition;
import com.ipseweb.traffic.dto.card.visitor.CardToCardBasicDtoVisitor;
import com.ipseweb.traffic.dto.card.visitor.CardToCardDetailDtoVisitor;
import com.ipseweb.traffic.repository.card.CardRepository;
import com.ipseweb.traffic.repository.cardgroup.CardGroupRepository;
import com.ipseweb.traffic.repository.user.UserRepository;
import com.ipseweb.traffic.service.card.factory.CardFactoryProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardService {

    private final CardRepository cardRepository;
    private final CardGroupRepository cardGroupRepository;
    private final UserRepository userRepository;

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
    public List<CardDto.CardBasic> getCardList(CardDto.GetRequest getRequest) {
        // request 정보 기반으로 카드 목록 조회
        List<Card> findCardList = cardRepository.searchCardAllByCondition(new CardSearchCondition(getRequest.getUserId()));

        if(findCardList == null || findCardList.isEmpty()) {
            log.error("Card is not exist. request Info : {}", getRequest);
            throw new CardException(CardErrorCode.CARD_IS_NOT_EXIST);
        }

        return findCardList.stream().map( c -> c.accept(new CardToCardBasicDtoVisitor())).collect(Collectors.toList());
    }

    /**
     * 카드 추가
     * @param addRequest
     */
    public void addCard(CardDto.AddRequest addRequest) {
        cardRepository.searchCard(addRequest).ifPresent(card -> {
                    throw new CardException(CardErrorCode.CARD_IS_ALREADY_EXIST);
                }
        );

        // CardGroup 조회
        CardGroup findCardGroup = cardGroupRepository.findById(addRequest.getCardGroupId())
                .orElseThrow(() -> new CardGroupException(CardGroupErrorCode.CARD_GROUP_IS_NOT_EXIST));


        // Card Entity 생성 및 저장
        Card card = CardFactoryProvider.getFactory(addRequest.getCardType()).createCard(addRequest, findCardGroup);
        cardRepository.save(card);
    }
}

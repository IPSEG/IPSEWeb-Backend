package com.ipseweb.traffic.service.card;

import com.ipseweb.error.CardErrorCode;
import com.ipseweb.error.CardGroupErrorCode;
import com.ipseweb.exception.CardException;
import com.ipseweb.exception.CardGroupException;
import com.ipseweb.traffic.domain.card.Card;
import com.ipseweb.traffic.domain.cardgroup.CardGroup;
import com.ipseweb.traffic.dto.card.CardDto;
import com.ipseweb.traffic.dto.card.visitor.CardToCardDetailDtoVisitor;
import com.ipseweb.traffic.repository.card.CardRepository;
import com.ipseweb.traffic.repository.cardgroup.CardGroupRepository;
import com.ipseweb.traffic.service.card.factory.CardFactoryProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class CardService {

    private final CardRepository cardRepository;

    private final CardGroupRepository cardGroupRepository;

    public CardDto.CardDetail getCard(Long cardId) {

        return cardRepository.findById(cardId).orElseThrow(() -> {
            log.error("Card is not exist. id : {}", cardId);
            throw new CardException(CardErrorCode.CARD_IS_NOT_EXIST);
        }).accept(new CardToCardDetailDtoVisitor());
    }

    public void addCard(CardDto.Add add) {
        CardGroup cardGroup = cardGroupRepository.findById(add.getCardGroupId()).orElseThrow(() -> {
            log.error("CardGroup not exist.");
            throw new CardGroupException(CardGroupErrorCode.CARD_GROUP_IS_NOT_EXIST);
        });

        Card card = CardFactoryProvider.getFactory(add.getCardType()).createCard(add);

        card.setCardGroup(cardGroup);

        cardRepository.save(card);
    }
}

package com.ipseweb.traffic.service.card;

import com.ipseweb.error.CardErrorCode;
import com.ipseweb.exception.CardException;
import com.ipseweb.traffic.dto.card.CardDto;
import com.ipseweb.traffic.dto.card.visitor.CardToCardDetailDtoVisitor;
import com.ipseweb.traffic.repository.card.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardService {

    private final CardRepository cardRepository;

    public CardDto.CardDetail getCard(Long cardId) {

        return cardRepository.findById(cardId).orElseThrow(() -> {
            log.error("Card is not exist. id : {}", cardId);
            throw new CardException(CardErrorCode.CARD_IS_NOT_EXIST);
        }).accept(new CardToCardDetailDtoVisitor());
    }
}

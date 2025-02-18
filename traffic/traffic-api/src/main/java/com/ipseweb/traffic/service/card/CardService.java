package com.ipseweb.traffic.service.card;

import com.ipseweb.error.CardErrorCode;
import com.ipseweb.exception.CardException;
import com.ipseweb.traffic.domain.card.Card;
import com.ipseweb.traffic.dto.card.CardDto;
import com.ipseweb.traffic.repository.card.CardRepository;
import com.ipseweb.traffic.repository.card.SubwayArrivalCardRepository;
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
    private final SubwayArrivalCardRepository subwayArrivalCardRepository;

    public List<CardDto.GetResponse> getCard(CardDto.GetRequest getRequest) {

        List<CardDto.GetResponse> result = subwayArrivalCardRepository.findCards(getRequest);

        if(result == null || result.isEmpty()) {
            log.error("Card is not exist. request Info : {}", getRequest);
            throw new CardException(CardErrorCode.CARD_IS_NOT_EXIST);
        }

        return result;
    }

    public void addCard(CardDto.AddRequest addRequest) {
        Card searchCard = subwayArrivalCardRepository.findCardByStationNameAndUserId(addRequest);
        if(searchCard != null) {
            throw new CardException(CardErrorCode.CARD_IS_ALREADY_EXIST);
        }
        Card card = CardFactoryProvider.getFactory(CardType.SUBWAY).createCard(addRequest);

        cardRepository.save(card);
    }
}

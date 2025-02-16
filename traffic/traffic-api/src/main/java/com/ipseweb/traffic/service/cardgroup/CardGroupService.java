package com.ipseweb.traffic.service.cardgroup;

import com.ipseweb.error.CardGroupErrorCode;
import com.ipseweb.exception.CardGroupException;
import com.ipseweb.traffic.domain.cardgroup.CardGroup;
import com.ipseweb.traffic.dto.card.CardDto;
import com.ipseweb.traffic.dto.cardgroup.CardGroupDto;
import com.ipseweb.traffic.dto.cardgroup.condition.CardGroupSearchCondition;
import com.ipseweb.traffic.repository.cardgroup.CardGroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardGroupService {

    private final CardGroupRepository cardGroupRepository;


    public List<CardGroupDto.CardGroupResponse> getCardGroupList(CardGroupSearchCondition condition) {

        return cardGroupRepository.searchCardGroup(condition)
                .stream()
                .filter(cg -> cg != null)
                .map(
                        cg -> new CardGroupDto.CardGroupResponse(
                                cg.getId(),
                                cg.getName(),
                                new CardDto.MultipleCardIdAndNameResponse(
                                        cg.getCardList()
                                                .stream()
                                                .map(c ->
                                                        new CardDto.CardIdAndNameResponse(
                                                                c.getCardId(),
                                                                c.getCardName())
                                                ).collect(Collectors.toList())
                                )
                        )
                )
                .collect(Collectors.toList());

    }

    public void addCardGroup(CardGroupDto.Add add) {
        cardGroupRepository.findCardGroupByName(add.getCardGroupName()).ifPresent(cardGroup -> {
            log.error("A card group with the same name exists.");
            throw new CardGroupException(CardGroupErrorCode.CARD_GROUP_ALREADY_EXIST);
        });

        CardGroup cardGroup = new CardGroup(add.getCardGroupName());

        cardGroupRepository.save(cardGroup);
    }


}

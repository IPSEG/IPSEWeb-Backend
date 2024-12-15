package com.ipseweb.traffic.service.cardgroup;

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
                .filter( cg -> cg != null)
                .map(
                        cg -> new CardGroupDto.CardGroupResponse(
                                cg.getId(),
                                cg.getName(),
                                new CardDto.MultipleCardIdAndNameResponse(
                                        cg.getCardList()
                                                .stream()
                                                .map( c ->
                                                        new CardDto.CardIdAndNameResponse(
                                                                c.getId(),
                                                                c.getName())
                                                ).collect(Collectors.toList())
                                )
                        )
                )
                .collect(Collectors.toList());

    }


}

package com.ipseweb.traffic.repository.cardgroup;

import com.ipseweb.traffic.domain.cardgroup.CardGroup;
import com.ipseweb.traffic.dto.cardgroup.condition.CardGroupSearchCondition;

import java.util.List;
import java.util.Optional;

public interface CardGroupRepositoryQueryDslCustom {

    List<CardGroup> searchCardGroup(CardGroupSearchCondition condition);

}

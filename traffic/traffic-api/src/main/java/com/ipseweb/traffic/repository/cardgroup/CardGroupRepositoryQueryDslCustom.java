package com.ipseweb.traffic.repository.cardgroup;

import com.ipseweb.traffic.domain.cardgroup.entity.CardGroup;
import com.ipseweb.traffic.dto.cardgroup.condition.CardGroupSearchCondition;

import java.util.List;

public interface CardGroupRepositoryQueryDslCustom {

    List<CardGroup> searchCardGroup(CardGroupSearchCondition condition);

}

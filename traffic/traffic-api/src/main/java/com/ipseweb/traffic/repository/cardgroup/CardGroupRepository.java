package com.ipseweb.traffic.repository.cardgroup;

import com.ipseweb.traffic.domain.cardgroup.CardGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardGroupRepository extends JpaRepository<CardGroup, Long>,  CardGroupRepositoryQueryDslCustom {
    Optional<CardGroup> findCardGroupByName(String name);
}

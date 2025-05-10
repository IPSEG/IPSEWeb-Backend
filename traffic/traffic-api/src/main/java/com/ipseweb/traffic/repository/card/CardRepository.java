package com.ipseweb.traffic.repository.card;

import com.ipseweb.traffic.domain.card.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long>, CardRepositoryQueryDslCustom {
    List<Card> findAllByCardName(String name);
}

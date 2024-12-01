package com.ipseweb.traffic.repository.card;

import com.ipseweb.traffic.domain.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findAllByName(String name);
}

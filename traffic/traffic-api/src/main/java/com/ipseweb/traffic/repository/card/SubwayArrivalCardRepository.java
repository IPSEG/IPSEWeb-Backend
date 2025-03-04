package com.ipseweb.traffic.repository.card;

import com.ipseweb.traffic.domain.card.SubwayArrivalCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubwayArrivalCardRepository extends JpaRepository<SubwayArrivalCard, Long>, SubwayArrivalCardRepositoryQueryDslCustom {
}

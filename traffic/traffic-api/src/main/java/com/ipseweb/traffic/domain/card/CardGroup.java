package com.ipseweb.traffic.domain.card;


import com.ipseweb.traffic.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter
@Table(name = "tb_card_group")
public class CardGroup extends BaseEntity {

    @Id
    @Column(name = "card_group_id")
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "cardGroup", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Card> cardList;
}

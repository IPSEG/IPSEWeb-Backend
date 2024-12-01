package com.ipseweb.traffic.domain.cardgroup;


import com.ipseweb.traffic.domain.base.BaseEntity;
import com.ipseweb.traffic.domain.card.Card;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "tb_card_group")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardGroup extends BaseEntity {

    @Id
    @Column(name = "card_group_id")
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "cardGroup", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Card> cardList = new ArrayList<>();

    public CardGroup(String name) {
        this.name = name;
    }
}

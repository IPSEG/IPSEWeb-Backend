package com.ipseweb.traffic.domain.card;

import com.ipseweb.traffic.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Table(name = "tb_card")
public abstract class Card extends BaseEntity {

    @Id
    @Column(name = "card_id")
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "card_group_id")
    private CardGroup cardGroup;
}
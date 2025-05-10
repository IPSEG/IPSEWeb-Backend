package com.ipseweb.traffic.domain.card.entity;

import com.ipseweb.traffic.domain.base.entity.BaseEntity;
import com.ipseweb.traffic.domain.card.visitor.CardVisitor;
import com.ipseweb.traffic.domain.cardgroup.entity.CardGroup;
import com.ipseweb.traffic.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "card_type",
        discriminatorType = DiscriminatorType.STRING
)
@Table(name = "card")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public abstract class Card extends BaseEntity {

    @Id
    @Column(name = "card_id")
    @GeneratedValue
    private Long cardId;

    @Column(name = "card_name")
    private String cardName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "card_group_id")
    private CardGroup cardGroup;

    /**
     * 연관 관계 편의 메서드
     */
    public void setCardGroup(CardGroup cardGroup) {

        if (this.cardGroup != null) {
            this.cardGroup.getCardList().remove(this);
        }

        this.cardGroup = cardGroup;
        cardGroup.getCardList().add(this);
    }


    /**
     * visitor 위임 함수
     */

    public abstract <R> R accept(CardVisitor<R> visitor);

}
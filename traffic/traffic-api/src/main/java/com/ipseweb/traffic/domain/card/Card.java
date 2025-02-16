package com.ipseweb.traffic.domain.card;

import com.ipseweb.traffic.domain.base.BaseEntity;
import com.ipseweb.traffic.domain.cardgroup.CardGroup;
import com.ipseweb.traffic.dto.card.CardDto;
import com.ipseweb.traffic.dto.card.visitor.CardVisitor;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "card_type",
        discriminatorType = DiscriminatorType.STRING
)
@Table(name = "card_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Card extends BaseEntity {

    @Id
    @Column(name = "card_id")
    @GeneratedValue
    private Long cardId;

    @Column(name = "card_name")
    private String cardName;

    @Column(name = "user_id")
    private String userId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "card_group_id")
    private CardGroup cardGroup;

    public Card(String name) {
        this.cardName = name;
    }

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
    public abstract CardDto.CardDetail accept(CardVisitor visitor);
}
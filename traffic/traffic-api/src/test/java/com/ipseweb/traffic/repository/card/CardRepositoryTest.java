package com.ipseweb.traffic.repository.card;

import com.ipseweb.traffic.domain.card.BusArrivalCard;
import com.ipseweb.traffic.domain.card.Card;
import com.ipseweb.traffic.domain.cardgroup.CardGroup;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class CardRepositoryTest {

    @Autowired
    CardRepository cardRepository;

    @Test
    @DisplayName("카드 이름으로 카드 조회")
    public void findAllByNameTest() throws Exception {
        //given
        CardGroup cardGroup = new CardGroup("MyCardGroup1");
        BusArrivalCard busArrivalCard = BusArrivalCard.builder()
                .busStopName("MyyBusCard")
                .cityCode("신덕1리")
                .busStopId("37040")
                .build();
        cardRepository.save(busArrivalCard);

        //when
        /**
         * none
         */

        //then
////        List<Card> myBusCardList = cardRepository.findAllByName("MyyBusCard");
//
//        Assertions.assertThat(myBusCardList.size()).isEqualTo(1);
//
//        Card myBusCard = myBusCardList.get(0);
//        CardGroup myBusCardGroup = myBusCard.getCardGroup();
//
//        System.out.println("myBusCardGroup.getCreatedDate() = " + myBusCardGroup.getCreatedDate());
//        System.out.println("myBusCardGroup.getLastModifiedDate() = " + myBusCardGroup.getLastModifiedDate());
//        System.out.println("myBusCardGroup.getCreateBy() = " + myBusCardGroup.getCreateBy());
//        System.out.println("myBusCardGroup.getLastModifiedBy() = " + myBusCardGroup.getLastModifiedBy());

    }

}
package com.ipseweb.traffic.service.card.factory;

import com.ipseweb.traffic.resource.card.type.CardType;

import java.util.HashMap;
import java.util.Map;

public class CardFactoryProvider {

    private static final Map<CardType, CardFactory> factoryMap = new HashMap<>();

    static {
        factoryMap.put(CardType.BUS, new BusArrivalCardFactory());
        factoryMap.put(CardType.SUBWAY, new SubwayArrivalCardFactory());
        factoryMap.put(CardType.TRAFFIC, new TrafficCardFactory());
    }

    public static CardFactory getFactory(CardType cardType) {
        return factoryMap.get(cardType);
    }


}

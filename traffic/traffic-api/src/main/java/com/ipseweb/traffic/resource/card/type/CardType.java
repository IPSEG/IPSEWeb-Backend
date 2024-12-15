package com.ipseweb.traffic.resource.card.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CardType {

    BUS(Values.BUS),
    SUBWAY(Values.SUBWAY),
    TRAFFIC(Values.TRAFFIC);

    private final String value;

    public static class Values {
        public static final String BUS = "BUS";
        public static final String SUBWAY = "SUBWAY";
        public static final String TRAFFIC  = "TRAFFIC";
    }
}

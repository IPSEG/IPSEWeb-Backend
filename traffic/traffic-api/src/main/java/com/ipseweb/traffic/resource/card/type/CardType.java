package com.ipseweb.traffic.resource.card.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CardType {

    BUS(Values.BUS),
    SUBWAY(Values.SUBWAY),
    TRAFFIC(Values.TRAFFIC);


    private final String value;

    @JsonCreator
    public static CardType from(String value) {
        return CardType.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return name();
    }


    public static class Values {
        public static final String BUS = "BUS";
        public static final String SUBWAY = "SUBWAY";
        public static final String TRAFFIC  = "TRAFFIC";
    }
}

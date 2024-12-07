package com.ipseweb.traffic.dto.card;

import java.util.List;

public class CardDto {

    public record CardIdAndNameResponse(Long id, String name){

    };

    public record MultipleCardIdAndNameResponse(List<CardIdAndNameResponse> list){

    };
}

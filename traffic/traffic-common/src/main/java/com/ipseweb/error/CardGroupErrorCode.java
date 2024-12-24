package com.ipseweb.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum CardGroupErrorCode implements ErrorCode {

    CARD_GROUP_IS_NOT_EXIST(HttpStatus.NOT_FOUND, "CardGroup is not found."),
    CARD_GROUP_ALREADY_EXIST(HttpStatus.NOT_FOUND, "CardGroup Already exists.");

    private final HttpStatus httpStatus;
    private final String message;

}

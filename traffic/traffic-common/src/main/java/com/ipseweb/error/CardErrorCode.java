package com.ipseweb.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum CardErrorCode implements ErrorCode{
    CARD_IS_NOT_EXIST(HttpStatus.NOT_FOUND, "Card is not found.")
    ;

    private final HttpStatus httpStatus;
    private final String message;

}

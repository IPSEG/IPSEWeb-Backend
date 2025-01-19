package com.ipseweb.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessCode implements ErrorCode{
    SUCCESS(HttpStatus.OK, "success"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}

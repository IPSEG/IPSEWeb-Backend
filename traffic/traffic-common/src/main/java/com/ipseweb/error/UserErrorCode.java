package com.ipseweb.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum UserErrorCode implements ErrorCode{
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}

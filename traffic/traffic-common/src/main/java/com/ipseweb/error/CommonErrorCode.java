package com.ipseweb.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode{

    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter included"),

    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not exists"),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),

    NO_SEARCH_DATA_ERROR(HttpStatus.BAD_REQUEST, "No search data"),

    ;


    private final HttpStatus httpStatus;
    private final String message;

}

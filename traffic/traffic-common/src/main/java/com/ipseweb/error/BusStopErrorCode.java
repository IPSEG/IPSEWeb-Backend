package com.ipseweb.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@RequiredArgsConstructor
@Getter
public enum BusStopErrorCode implements ErrorCode{
    BUS_STOP_NOT_EXIST(HttpStatus.NOT_FOUND, "BusStop not exists.")
            ;

    private final HttpStatus httpStatus;
    private final String message;
}


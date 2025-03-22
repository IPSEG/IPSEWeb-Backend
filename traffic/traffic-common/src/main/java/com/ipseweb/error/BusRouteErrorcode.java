package com.ipseweb.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum BusRouteErrorcode implements ErrorCode{

    BUS_ROUTE_NOT_EXIST(HttpStatus.NOT_FOUND, "BusRoute wast not exist."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}

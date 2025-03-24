package com.ipseweb.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
public enum OpenApiErrorCode implements ErrorCode {

    BUSSTOP_EMPTY(HttpStatus.BAD_REQUEST, "BusStop List is Empty."),
    BUS_ROUTE_BASIC_INFO_EMPTY(HttpStatus.BAD_REQUEST, "BusRouteBasicInfo is Empty."),

    ;

    private final HttpStatus httpStatus;
    private final String message;
}

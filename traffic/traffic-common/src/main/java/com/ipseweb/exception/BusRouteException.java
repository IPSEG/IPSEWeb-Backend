package com.ipseweb.exception;

import com.ipseweb.error.ErrorCode;

public class BusRouteException extends TrafficException {
    public BusRouteException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BusRouteException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public BusRouteException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause, errorCode);
    }

    public BusRouteException(Throwable cause, ErrorCode errorCode) {
        super(cause, errorCode);
    }

    public BusRouteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorCode errorCode) {
        super(message, cause, enableSuppression, writableStackTrace, errorCode);
    }
}

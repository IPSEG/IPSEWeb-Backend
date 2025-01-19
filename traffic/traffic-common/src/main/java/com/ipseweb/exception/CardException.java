package com.ipseweb.exception;

import com.ipseweb.error.ErrorCode;

public class CardException extends TrafficException{
    public CardException(ErrorCode errorCode) {
        super(errorCode);
    }

    public CardException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public CardException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause, errorCode);
    }

    public CardException(Throwable cause, ErrorCode errorCode) {
        super(cause, errorCode);
    }

    public CardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorCode errorCode) {
        super(message, cause, enableSuppression, writableStackTrace, errorCode);
    }
}

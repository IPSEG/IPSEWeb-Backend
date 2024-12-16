package com.ipseweb.exception;

import com.ipseweb.error.ErrorCode;

public class CardGroupException extends TrafficException{
    public CardGroupException(ErrorCode errorCode) {
        super(errorCode);
    }

    public CardGroupException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public CardGroupException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause, errorCode);
    }

    public CardGroupException(Throwable cause, ErrorCode errorCode) {
        super(cause, errorCode);
    }

    public CardGroupException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorCode errorCode) {
        super(message, cause, enableSuppression, writableStackTrace, errorCode);
    }
}

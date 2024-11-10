package com.ipseweb.exception;

import com.ipseweb.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class TrafficException extends RuntimeException{
    private final ErrorCode errorCode;

    public TrafficException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public TrafficException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public TrafficException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public TrafficException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public TrafficException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorCode errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }
}

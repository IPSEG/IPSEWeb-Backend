package com.ipseweb.exception;

import com.ipseweb.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TrafficException extends RuntimeException{
    private final ErrorCode errorCode;
}

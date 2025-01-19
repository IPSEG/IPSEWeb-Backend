package com.ipseweb.traffic.exhandler;

import com.ipseweb.error.CommonErrorCode;
import com.ipseweb.error.Response;
import com.ipseweb.exception.ResponseEntityFactory;
import com.ipseweb.exception.TrafficException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class TrafficExceptionControllerAdvice {
    @ExceptionHandler(TrafficException.class)
    public ResponseEntity<Response<Object>> TrafficException(TrafficException e) {
        log.error("[ExceptionHandler] ex", e);
        return ResponseEntityFactory.error(e.getErrorCode());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<Object>> UnknownException(Exception e) {
        log.error("[ExceptionHandler] ex", e);
        return ResponseEntityFactory.error(CommonErrorCode.UNKNOWN_ERROR);
    }
}

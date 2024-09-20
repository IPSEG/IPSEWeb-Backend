package com.ipseweb.traffic.dto;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    HttpStatus getHttpStatus();

    String getMessage();
}

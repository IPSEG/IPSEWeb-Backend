package com.ipseweb.traffic.exception;

import com.ipseweb.traffic.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;

public class ResponseEntityFactory<T> {
    public static <T>ResponseEntity<ErrorResponse<T>> success(T data) {
        ErrorResponse<T> response = ErrorResponse.success(data);
        return new ResponseEntity<>(response, response.getStatus());
    }

}

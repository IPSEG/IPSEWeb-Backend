package com.ipseweb.exception;


import com.ipseweb.error.ErrorCode;
import com.ipseweb.error.Response;
import org.springframework.http.ResponseEntity;

public class ResponseEntityFactory<T> {
    public static <T> ResponseEntity<Response<T>> success(T data) {
        Response<T> response = Response.success(data);
        return new ResponseEntity<>(response, response.getStatus());
    }

    public static <T> ResponseEntity<Response<T>> error(ErrorCode errCode) {
        Response<T> response = Response.error(errCode);
        return new ResponseEntity<>(response, response.getStatus());

    }

}

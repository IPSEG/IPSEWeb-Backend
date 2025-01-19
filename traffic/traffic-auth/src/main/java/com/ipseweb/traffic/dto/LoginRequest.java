package com.ipseweb.traffic.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String userId;
    private String encryptedPassword;
    private String randomString;
}

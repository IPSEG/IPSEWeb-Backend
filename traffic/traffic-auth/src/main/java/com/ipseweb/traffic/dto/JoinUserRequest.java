package com.ipseweb.traffic.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinUserRequest {
    private String userId;
    private String userName;
    private String encryptedPassword;
    private String rsaKey;
    private String userEmail;
}

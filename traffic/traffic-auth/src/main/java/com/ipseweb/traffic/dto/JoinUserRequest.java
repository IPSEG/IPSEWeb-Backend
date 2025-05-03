package com.ipseweb.traffic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JoinUserRequest {
    private String userId;
    private String userName;
    private String encryptedPassword;
    private String randomString;
    private String userEmail;
}

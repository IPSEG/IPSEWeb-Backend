package com.ipseweb.traffic.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RsaResponse {
    private String modulus;
    private String exponent;
    private String randomString;
}

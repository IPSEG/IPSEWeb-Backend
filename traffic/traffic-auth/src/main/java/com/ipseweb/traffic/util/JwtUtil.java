package com.ipseweb.traffic.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secretkey}")
    public String secretKey;
    private final long expirationTimeMilliseconds = 3600 * 1000;

    public String generateToken(String username) {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        Key signKey = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeMilliseconds))
                .signWith(signKey, SignatureAlgorithm.HS256)
                .compact();
    }
}

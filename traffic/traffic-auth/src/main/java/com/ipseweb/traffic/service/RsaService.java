package com.ipseweb.traffic.service;

import com.ipseweb.traffic.dto.RsaResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

@Service
public class RsaService {
    private KeyPairGenerator keyPairGenerator;
    private KeyFactory keyFactory;
    private RedisTemplate redisTemplate;

    public RsaService() {
        try {
            this.keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            this.keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public RsaResponse getRsa() {
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        RsaResponse rsaResponse = new RsaResponse();

        try {
            RSAPublicKeySpec publicKeySpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
            String modulus = publicKeySpec.getModulus().toString();
            String exponent = publicKeySpec.getPublicExponent().toString();
            String randomString = getRandomString();

            rsaResponse.setModulus(modulus);
            rsaResponse.setExponent(exponent);
            rsaResponse.setRandomString(randomString);

            redisTemplate.opsForValue().set(randomString, modulus);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        return rsaResponse;
    }

    public String getRandomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 15;
        SecureRandom random = new SecureRandom();

        return random.ints(leftLimit,rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}

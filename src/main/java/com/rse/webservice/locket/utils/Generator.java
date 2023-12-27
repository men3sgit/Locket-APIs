package com.rse.webservice.locket.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class Generator {

    public static String generateRandomBase64WithoutNumberToken() {
        return generateRandomBase64WithoutNumberToken(32);
    }

    public static String generateRandomBase64WithoutNumberToken(int bytes) {
        SecureRandom secureRandom = new SecureRandom();

        // Generate a byte array of random values
        byte[] randomBytes = new byte[bytes];
        secureRandom.nextBytes(randomBytes);

        // Base64 encode the bytes without numbers
        return Base64.getEncoder()
                .encodeToString(randomBytes)
                .replaceAll("[0-9]", "");
    }


    public static void main(String[] args) {
        System.out.println(Generator.generateRandomBase64WithoutNumberToken());
    }
}

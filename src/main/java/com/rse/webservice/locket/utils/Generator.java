package com.rse.webservice.locket.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class Generator {
    private static final int DEFAULT_BYTES = 32;

    public static String generateRandomBase64Token() {
        return generateRandomBase64Token(DEFAULT_BYTES).replaceAll("[+]","");
    }
    public static String generateRandomBase64Token(boolean hasNumber) {
        return generateRandomBase64Token(DEFAULT_BYTES, hasNumber);
    }

    public static String generateRandomBase64Token(int bytes) {
        return generateRandomBase64Token(bytes, false);
    }

    public static String generateRandomBase64Token(int bytes, boolean hasNumber) {
        SecureRandom secureRandom = new SecureRandom();
        // Generate a byte array of random values
        byte[] randomBytes = new byte[bytes];
        secureRandom.nextBytes(randomBytes);
        // Base64 encode the bytes without numbers
        String base64Text = Base64.getEncoder().encodeToString(randomBytes);
        return hasNumber ? base64Text.replaceAll("[0-9]", "")
                : base64Text;
    }

}

package com.rse.webservice.locket.security.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    String extractUsername(String token);

    boolean validateToken(String token, UserDetails userDetails);

    boolean isTokenExpired(String token);

    String generateToken(Map<String, Object> extractClaims,
                         UserDetails userDetails);

    String generateToken(UserDetails userDetails);

    // TODO get All properties Claims
    <T> T extractClaims(String token, Function<Claims, T> claimResolver);
}

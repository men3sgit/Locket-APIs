package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.model.RefreshToken;
import com.rse.webservice.locket.payload.freshtoken.requests.RefreshTokenCreateRequest;
import com.rse.webservice.locket.payload.freshtoken.responses.RefreshTokenCreateResponse;
import com.rse.webservice.locket.repository.RefreshTokenRepository;
import com.rse.webservice.locket.repository.UserRepository;
import com.rse.webservice.locket.security.jwt.JwtService;
import com.rse.webservice.locket.service.RefreshTokenService;
import com.rse.webservice.locket.utils.Generator;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    @Value("${locket.app.jwt.refreshTokenExpirationTimeMs}")
    private long expirationMs;
    private final RefreshTokenRepository refreshTokenRepository;

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    public RefreshTokenCreateResponse create(RefreshTokenCreateRequest request) {
        var newRefreshToken = RefreshToken.builder()
                .userId(request.getUserId())
                .token(Generator.generateRandomBase64WithoutNumberToken(128))
                .expiredAt(new Timestamp(System.currentTimeMillis() + expirationMs))
                .build();

//        var response = RefreshTokenCreateResponse.builder()
//                .refreshToken(newRefreshToken.getToken())
//                .accessToken(jwtService.generateToken(userDetailsService.loadUserByUsername()))
//                .build();
        return null;
    }
}

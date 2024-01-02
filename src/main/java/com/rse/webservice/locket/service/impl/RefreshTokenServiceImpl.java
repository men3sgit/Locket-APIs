package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.exception.ApiRequestException;
import com.rse.webservice.locket.model.RefreshToken;
import com.rse.webservice.locket.payload.freshtoken.requests.RefreshTokenCreateRequest;
import com.rse.webservice.locket.payload.freshtoken.requests.RefreshTokenSelfRequest;
import com.rse.webservice.locket.payload.freshtoken.responses.RefreshTokenCreateResponse;
import com.rse.webservice.locket.payload.freshtoken.responses.RefreshTokenSelfResponse;
import com.rse.webservice.locket.repository.RefreshTokenRepository;
import com.rse.webservice.locket.repository.UserRepository;
import com.rse.webservice.locket.security.jwt.JwtService;
import com.rse.webservice.locket.service.RefreshTokenService;
import com.rse.webservice.locket.utils.Generator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    @Value("${locket.app.jwt.refresh-token.expiration-time-ms}")
    private long expirationMs;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;

    @Override
    public RefreshTokenCreateResponse create(RefreshTokenCreateRequest request) {
        var newRefreshToken = RefreshToken.builder()
                .userId(request.getUserId())
                .token(Generator.generateRandomBase64Token())
                .expiredAt(new Timestamp(System.currentTimeMillis() + expirationMs))
                .build();

        refreshTokenRepository.save(newRefreshToken);
        return RefreshTokenCreateResponse.of(newRefreshToken.getId());
    }

    @Override
    public RefreshTokenSelfResponse self(RefreshTokenSelfRequest request) {
        var refreshToken = refreshTokenRepository
                .findByToken(request.getToken())
                .orElseThrow(() -> new ApiRequestException("Refresh token can not found with %s".formatted(request.getToken())));

        var user = userRepository.findById(refreshToken.getUserId()).get();
        var userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String accessToken = jwtService.generateToken(userDetails);

        return RefreshTokenSelfResponse.builder()
                .accessToken(accessToken)
                .type("Bearer")
                .freshToken(refreshToken.getToken())
                .expiredAt(refreshToken.getExpiredAt())
                .userId(user.getId())
                .build();
    }


}

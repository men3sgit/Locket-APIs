package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.constants.Const;
import com.rse.webservice.locket.constants.ConstantKey;
import com.rse.webservice.locket.enums.TokenType;
import com.rse.webservice.locket.exception.ApiRequestException;
import com.rse.webservice.locket.model.Token;
import com.rse.webservice.locket.model.User;
import com.rse.webservice.locket.payload.token.requests.TokenCreateRequest;
import com.rse.webservice.locket.payload.token.requests.TokenUpdateRequest;
import com.rse.webservice.locket.payload.token.responses.TokenCreateResponse;
import com.rse.webservice.locket.payload.token.responses.TokenUpdateResponse;
import com.rse.webservice.locket.repository.TokenRepository;
import com.rse.webservice.locket.repository.UserRepository;
import com.rse.webservice.locket.service.EmailService;
import com.rse.webservice.locket.service.TokenService;
import com.rse.webservice.locket.utils.templates.TemplateConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Value("${locket.app.name}")
    private String title;
    @Value("${url.endpoint.verify.registration}")
    private String verifyRegistrationTokenUrl;

    @Value("${locket.app.token.registration.expiration-time-ms}")
    private long registrationTokenExpirationMs;


    @Override
    public TokenUpdateResponse verifyNewUser(TokenUpdateRequest request) {
        validateToken(request.getToken());

        var restrationToken = getRestrationToken(request.getToken());
        var user = getUserById(restrationToken.getUserId());
        user.setStatus(Const.GeneralStatus.ACTIVE);
        userRepository.save(user);

        restrationToken.revoke();
        tokenRepository.save(restrationToken);
        return TokenUpdateResponse.of(restrationToken.getId());
    }

    @Override
    public void sendMailToVerify(String to, String name, String token) {
        var url = verifyRegistrationTokenUrl + "?token=" + token;
        var verifyNewUserTemplate = "verification_new_user_email_template.html";
        var config = new TemplateConfig();
        config.setPath(verifyNewUserTemplate);
        config.setProperties(Map.of("title", title, "name", name, "url", url));
        emailService.sendHtmlMailMessage(to, "New User Account Verification", config);
    }

    @Override
    public TokenCreateResponse createRegistrationToken(TokenCreateRequest request) {
        var newToken = Token.builder()
                .userId(request.getUserId())
                .token(UUID.randomUUID().toString())
                .tokenType(TokenType.REGISTRATION)
                .expiredAt(new Timestamp(System.currentTimeMillis() + registrationTokenExpirationMs))
                .build();
        tokenRepository.save(newToken);
        // TODO: Refactoring send mail here
        return TokenCreateResponse.of(newToken.getId(), newToken.getToken());
    }


    private void validateToken(String token) {
        var tokenObj = getToken(token);

        if (tokenObj.isTokenUsed()) {
            throw new ApiRequestException(ConstantKey.MSG_USED_TOKEN);
        }

        if (tokenObj.isTokenExpired()) {
            // TODO: resend mail
            throw new ApiRequestException(ConstantKey.MSG_TOKEN_EXPIRED);
        }

        if (!tokenObj.isTokenValid()) {
            throw new ApiRequestException(ConstantKey.MSG_INVALID_TOKEN);
        }
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ApiRequestException(ConstantKey.MSG_USER_NOT_FOUND));
    }

    private Token getRestrationToken(String token) {
        return getTokenWithType(token, TokenType.REGISTRATION);
    }

    private Token getBearerToken(String token) {
        return getTokenWithType(token, TokenType.BEARER);
    }

    private Token getTokenWithType(String token, TokenType tokenType) {
        return tokenRepository.findByTokenAndTokenType(token, tokenType)
                .orElseThrow(() -> new ApiRequestException(ConstantKey.MSG_NOT_FOUND_TOKEN));
    }

    private Token getToken(String token) {
        return tokenRepository.findByToken(token)
                .orElseThrow(() -> new ApiRequestException(ConstantKey.MSG_NOT_FOUND_TOKEN));
    }
}

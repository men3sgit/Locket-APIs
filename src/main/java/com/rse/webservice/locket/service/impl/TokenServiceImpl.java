package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.constants.Const;
import com.rse.webservice.locket.constants.ConstantKey;
import com.rse.webservice.locket.enums.TokenType;
import com.rse.webservice.locket.exception.ApiRequestException;
import com.rse.webservice.locket.model.Token;
import com.rse.webservice.locket.model.User;
import com.rse.webservice.locket.payload.account.requests.AccountSelfRequest;
import com.rse.webservice.locket.payload.account.responses.AccountSelfResponse;
import com.rse.webservice.locket.payload.token.requests.TokenCreateRequest;
import com.rse.webservice.locket.payload.token.requests.TokenUpdateRequest;
import com.rse.webservice.locket.payload.token.responses.TokenCreateResponse;
import com.rse.webservice.locket.payload.token.responses.TokenUpdateResponse;
import com.rse.webservice.locket.repository.TokenRepository;
import com.rse.webservice.locket.repository.UserRepository;
import com.rse.webservice.locket.service.AccountService;
import com.rse.webservice.locket.service.EmailService;
import com.rse.webservice.locket.service.TokenService;
import com.rse.webservice.locket.utils.templates.TemplateConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    @Value("${locket.app.name}")
    private String title;

    @Value("${locket.app.token.registration.verify-endpoint}")
    private String verifyRegistrationTokenUrl;

    @Value("${locket.app.token.forgot-password.verify-endpoint}")
    private String verifyForgotPasswordTokenUrl;

    @Value("${locket.app.token.registration.expiration-time-ms}")
    private long registrationTokenExpirationMs;

    @Value("${locket.app.token.forgot-password.expiration-time-ms}")
    private long forgotPasswordTokenExpirationMs;

    @Override
    public TokenUpdateResponse verifyNewUser(TokenUpdateRequest request) {
        validateToken(request.getToken());

        Token restrationToken = getRestrationToken(request.getToken());
        User user = getUserById(restrationToken.getUserId());
        user.setStatus(Const.GeneralStatus.ACTIVE);
        userRepository.save(user);

        restrationToken.revoke();
        tokenRepository.save(restrationToken);
        return TokenUpdateResponse.of(ConstantKey.MSG_SUCCESS);
    }

    @Override
    public TokenUpdateResponse receiveNewPassword(TokenUpdateRequest request) {
        var storedForgotPasswordToken = getForgotPasswordToken(request.getToken());
        storedForgotPasswordToken.revoke();
        var storedUser = getUserById(storedForgotPasswordToken.getUserId());
        String newPassword = UUID.randomUUID().toString();
        storedUser.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(storedUser);

        return TokenUpdateResponse.of(newPassword);
    }

    @Override
    public void sendMailToVerify(String to, String name, String token) {
        var url = verifyRegistrationTokenUrl + "?token=" + token;
        final var verifyNewUserTemplate = "verification_new_user_email_template.html";
        sendVerificationEmail(to, name, url, verifyNewUserTemplate);
    }

    @Override
    public void sendMailToResetPassword(String to) {
        User storedUser = getUserByEmail(to);
        String name = getAccountNameByUserId(storedUser.getId());
        Token newToken = createAndSaveForgotPasswordToken(storedUser.getId());
        final var url = verifyForgotPasswordTokenUrl + "?token=" + newToken.getToken();
        final var verifyNewUserTemplate = "receive_new_password_email_template.html";
        sendVerificationEmail(to, name, url, verifyNewUserTemplate);
    }

    @Override
    public TokenCreateResponse createRegistrationToken(TokenCreateRequest request) {
        Token newToken = createAndSaveRegistrationToken(request.getUserId());
        return TokenCreateResponse.of(newToken.getId(), newToken.getToken());
    }

    private void sendVerificationEmail(String to, String name, String url, String template) {
        var config = new TemplateConfig();
        config.setPath(template);
        config.setProperties(Map.of("title", title, "name", name, "url", url));

        emailService.sendHtmlMailMessage(to, "Account Verification", config);
    }

    private Token createAndSaveForgotPasswordToken(Long userId) {
        Token newToken = Token.builder()
                .userId(userId)
                .token(UUID.randomUUID().toString())
                .tokenType(TokenType.FORGOT_PASSWORD)
                .expiredAt(new Timestamp(System.currentTimeMillis() + forgotPasswordTokenExpirationMs))
                .build();
        return tokenRepository.save(newToken);
    }

    private Token createAndSaveRegistrationToken(Long userId) {
        Token newToken = Token.builder()
                .userId(userId)
                .token(UUID.randomUUID().toString())
                .tokenType(TokenType.REGISTRATION)
                .expiredAt(new Timestamp(System.currentTimeMillis() + registrationTokenExpirationMs))
                .build();
        return tokenRepository.save(newToken);
    }

    private String getAccountNameByUserId(Long userId) {
        return Optional.ofNullable(accountService.self(AccountSelfRequest.of(userId)))
                .map(AccountSelfResponse::getFirstName)
                .orElseThrow(() -> new ApiRequestException(ConstantKey.MSG_USER_NOT_FOUND));
    }

    private void validateToken(String token) {
        Token tokenObj = getToken(token);

        if (tokenObj.isTokenUsed()) {
            throw new ApiRequestException(ConstantKey.MSG_USED_TOKEN);
        }

        if (tokenObj.isTokenExpired()) {
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

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ApiRequestException(ConstantKey.MSG_USER_NOT_FOUND));
    }

    private Token getRestrationToken(String token) {
        return getTokenWithType(token, TokenType.REGISTRATION);
    }

    private Token getBearerToken(String token) {
        return getTokenWithType(token, TokenType.BEARER);
    }

    private Token getForgotPasswordToken(String token) {
        return getTokenWithType(token, TokenType.FORGOT_PASSWORD);
    }

    private Token getTokenWithType(String token, TokenType tokenType) {
        validateToken(token);
        return tokenRepository.findByTokenAndTokenType(token, tokenType)
                .orElseThrow(() -> new ApiRequestException(ConstantKey.MSG_NOT_FOUND_TOKEN));
    }

    private Token getToken(String token) {
        return tokenRepository.findByToken(token)
                .orElseThrow(() -> new ApiRequestException(ConstantKey.MSG_NOT_FOUND_TOKEN));
    }
}

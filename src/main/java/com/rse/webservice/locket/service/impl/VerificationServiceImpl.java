package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.constants.ConstantKey;
import com.rse.webservice.locket.exception.ApiRequestException;
import com.rse.webservice.locket.model.User;
import com.rse.webservice.locket.model.VerificationToken;
import com.rse.webservice.locket.payload.request.verify.NewUserVerificationRequest;
import com.rse.webservice.locket.payload.response.verify.NewUserVerificationResponse;
import com.rse.webservice.locket.repository.UserRepository;
import com.rse.webservice.locket.repository.VerificationTokenRepository;
import com.rse.webservice.locket.service.EmailService;
import com.rse.webservice.locket.service.VerificationService;
import com.rse.webservice.locket.constants.Const;
import com.rse.webservice.locket.constants.URL;
import com.rse.webservice.locket.utils.templates.TemplateConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class VerificationServiceImpl implements VerificationService {
    private final VerificationTokenRepository verificationTokenRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Value("${locket.app.name}")
    private String title;

    @Override
    public NewUserVerificationResponse verifyNewUser(NewUserVerificationRequest request) {
        validateToken(request.getToken());

        var userId = verificationTokenRepository.getUserIdByToken(request.getToken());
        var user = getUserById(userId);
        user.setStatus(Const.GeneralStatus.ACTIVE);
        userRepository.save(user);

        var token = getVerificationToken(request.getToken());
        token.verify();
        verificationTokenRepository.save(token);

        return NewUserVerificationResponse.of(ConstantKey.MSG_SUCCESS);
    }

    @Override
    public void sendMailToVerify(String to, String name, String token) {
        var url = URL.NEW_USER_VERIFICATION_URL + "?token=" + token;
        var verifyNewUserTemplate = "verification_new_user_email_template.html";
        var config = new TemplateConfig();
        config.setPath(verifyNewUserTemplate);
        config.setProperties(Map.of("title", title, "name", name, "url", url));
        emailService.sendHtmlMailMessage(to, Const.MailTitle.NEW_USER_ACCOUNT_VERIFICATION, config);
    }

    private void validateToken(String token) {
        var verificationToken = getVerificationToken(token);

        if (verificationToken.isTokenUsed()) {
            throw new ApiRequestException(ConstantKey.MSG_USED_TOKEN);
        }

        if (verificationToken.isTokenExpired()) {
            // TODO: resend mail
            throw new ApiRequestException(ConstantKey.MSG_TOKEN_EXPIRED);
        }

        if (!verificationToken.isTokenValid()) {
            throw new ApiRequestException(ConstantKey.MSG_INVALID_TOKEN);
        }
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ApiRequestException(ConstantKey.MSG_USER_NOT_FOUND));
    }

    private VerificationToken getVerificationToken(String token) {
        return verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new ApiRequestException(ConstantKey.MSG_NOT_FOUND_TOKEN));
    }
}

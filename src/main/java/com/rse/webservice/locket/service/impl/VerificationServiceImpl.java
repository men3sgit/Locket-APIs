package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.payload.request.NewUserVerificationRequest;
import com.rse.webservice.locket.payload.response.NewUserVerificationResponse;
import com.rse.webservice.locket.utils.templates.TemplateConfig;
import com.rse.webservice.locket.repository.VerificationTokenRepository;
import com.rse.webservice.locket.service.EmailService;
import com.rse.webservice.locket.service.VerificationService;
import com.rse.webservice.locket.utils.Const;
import com.rse.webservice.locket.utils.URL;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class VerificationServiceImpl implements VerificationService {
    private final VerificationTokenRepository verificationTokenRepository;
    private final EmailService emailService;
    @Value("${locket.app.name}")
    private String title;

    @Override
    public NewUserVerificationResponse verifyNewUser(NewUserVerificationRequest request) {
        // TODO: active user and update token
        return NewUserVerificationResponse.of("Men dep trai");
    }

    @Override
    public void sendMailToVerify(String to, String token) {
        final String url = URL.NEW_USER_VERIFICATION_URL + "?token=" + token;
        final String verifyNewUserTemplate = "verification_new_user_email_template.html";
        TemplateConfig config = new TemplateConfig();
        config.setPath(verifyNewUserTemplate);
        config.setProperties(Map.of("title", title, "name", to, "url", url));
        emailService.sendHtmlMailMessage(to, Const.MailTitle.NEW_USER_ACCOUNT_VERIFICATION, config);
    }
}

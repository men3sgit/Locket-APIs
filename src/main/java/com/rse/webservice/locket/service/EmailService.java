package com.rse.webservice.locket.service;

import com.rse.webservice.locket.utils.templates.TemplateConfig;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);

    void sendHtmlMailMessage(String to, String subject, TemplateConfig config);
}

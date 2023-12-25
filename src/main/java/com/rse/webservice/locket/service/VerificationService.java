package com.rse.webservice.locket.service;

import com.rse.webservice.locket.payload.request.NewUserVerificationRequest;
import com.rse.webservice.locket.payload.response.NewUserVerificationResponse;

public interface VerificationService {

    NewUserVerificationResponse verifyNewUser(NewUserVerificationRequest request);

    void sendMailToVerify(String to, String token);
}

package com.rse.webservice.locket.service;

import com.rse.webservice.locket.payload.request.verify.NewUserVerificationRequest;
import com.rse.webservice.locket.payload.response.verify.NewUserVerificationResponse;

public interface VerificationService {

    NewUserVerificationResponse verifyNewUser(NewUserVerificationRequest request);

    void sendMailToVerify(String to, String token);
}

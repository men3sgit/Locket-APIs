package com.rse.webservice.locket.service;

import com.rse.webservice.locket.payload.verify.requests.NewUserVerificationRequest;
import com.rse.webservice.locket.payload.verify.responses.NewUserVerificationResponse;

public interface VerificationService {

    NewUserVerificationResponse verifyNewUser(NewUserVerificationRequest request);

    void sendMailToVerify(String to, String name, String token);
}

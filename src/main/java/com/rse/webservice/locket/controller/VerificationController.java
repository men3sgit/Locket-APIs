package com.rse.webservice.locket.controller;

import com.rse.webservice.locket.payload.verify.requests.NewUserVerificationRequest;
import com.rse.webservice.locket.payload.verify.requests.ResendTokenRequest;
import com.rse.webservice.locket.payload.ApiResponse;
import com.rse.webservice.locket.service.VerificationService;
import com.rse.webservice.locket.constants.URL;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = URL.API_V1_VERIFY_PATH)
public class VerificationController {
    private final VerificationService verificationService;

    @GetMapping(path = URL.REGISTRATION_CONFIRM)
    public ApiResponse<?> verify(NewUserVerificationRequest request) {
        System.err.println(request.getToken());
        return new ApiResponse<>(verificationService.verifyNewUser(request));
    }
    @GetMapping(path = URL.RESEND_TOKEN)
    public ApiResponse<?> resentRegistrationConfirmToken(ResendTokenRequest request){
        return null;
    }
}

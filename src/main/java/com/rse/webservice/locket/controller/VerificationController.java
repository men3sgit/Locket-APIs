package com.rse.webservice.locket.controller;

import com.rse.webservice.locket.payload.request.verify.NewUserVerificationRequest;
import com.rse.webservice.locket.payload.response.ApiResponse;
import com.rse.webservice.locket.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/verify")
public class VerificationController {
    private final VerificationService verificationService;

    @GetMapping("/create-new-user")
    public ApiResponse<?> verify(NewUserVerificationRequest request) {
        System.err.println(request.getToken());
        return new ApiResponse<>(verificationService.verifyNewUser(request));
    }
}

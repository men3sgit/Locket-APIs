package com.rse.webservice.locket.controller;

import com.rse.webservice.locket.payload.request.auth.AuthenticationRequest;
import com.rse.webservice.locket.payload.request.auth.RegistrationRequest;
import com.rse.webservice.locket.payload.response.ApiResponse;
import com.rse.webservice.locket.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping(path = "/register")
    public ApiResponse<?> register(@RequestBody @Valid RegistrationRequest request) {
        return new ApiResponse<>(authenticationService.addNewUser(request));
    }

    @PostMapping(path = "/login")
    public ApiResponse<?> login(@RequestBody AuthenticationRequest request) {

        return new ApiResponse<>(authenticationService.authenticate(request));
    }

    @PostMapping(path = "/logout")
    public ApiResponse<?> logout(@RequestBody AuthenticationRequest request) {
        return new ApiResponse<>(authenticationService.authenticate(request));
    }

    @PostMapping(path = "/confirm-password")
    public ApiResponse<?> confirmPassword(@RequestBody AuthenticationRequest request) {
        return new ApiResponse<>(authenticationService.authenticate(request));
    }


}

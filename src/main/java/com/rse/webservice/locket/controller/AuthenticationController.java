package com.rse.webservice.locket.controller;

import com.rse.webservice.locket.constants.HttpStatusCodes;
import com.rse.webservice.locket.payload.ApiResponse;
import com.rse.webservice.locket.payload.auth.requests.AuthenticationRequest;
import com.rse.webservice.locket.payload.auth.requests.ChangePasswordRequest;
import com.rse.webservice.locket.payload.auth.requests.ForgotPasswordRequest;
import com.rse.webservice.locket.payload.auth.requests.RegistrationRequest;
import com.rse.webservice.locket.payload.token.requests.TokenUpdateRequest;
import com.rse.webservice.locket.service.AuthenticationService;
import com.rse.webservice.locket.service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final TokenService tokenService;

    @PostMapping(path = "/register")
    public ApiResponse<?> register(@RequestBody @Valid RegistrationRequest request) {
        var response = authenticationService.addNewUser(request);
        return new ApiResponse<>(response, HttpStatusCodes.CREATED);
    }

    @PostMapping(path = "/login")
    public ApiResponse<?> login(@RequestBody AuthenticationRequest request) {

        return new ApiResponse<>(authenticationService.authenticate(request));
    }

    @PostMapping(path = "/logout")
    public ApiResponse<?> logout() {
        authenticationService.logout();
        return new ApiResponse<>("Logged off");
    }

    @PostMapping(path = "/confirm-password")
    public ApiResponse<?> confirmPassword(@RequestBody AuthenticationRequest request) {
        return new ApiResponse<>(authenticationService.authenticate(request));
    }

    @GetMapping(path = "/forgot-password")
    public ApiResponse<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        return new ApiResponse<>(authenticationService.resetPassword(request));
    }

    @GetMapping(path = "/verify-forgot-password")
    public ApiResponse<?> receiveNewPassword(TokenUpdateRequest request) {
        var response = tokenService.receiveNewPassword(request);
        return new ApiResponse<>("Your new password: " + response.getMessage(),HttpStatusCodes.NO_CONTENT);
    }

    @GetMapping("/verify-registration")
    public ApiResponse<?> verify(TokenUpdateRequest request) {
        var response = tokenService.verifyNewUser(request);
        return new ApiResponse<>(response);
    }

    @PostMapping(path = "/change-password")
    public ApiResponse<?> changePassword(@RequestBody ChangePasswordRequest request) {
        var response = authenticationService.changePassword(request);
        return new ApiResponse<>(response, HttpStatusCodes.NO_CONTENT);
    }


}

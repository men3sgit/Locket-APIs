package com.rse.webservice.locket.service;

import com.rse.webservice.locket.payload.auth.requests.AuthenticationRequest;
import com.rse.webservice.locket.payload.auth.requests.ChangePasswordRequest;
import com.rse.webservice.locket.payload.auth.requests.ForgotPasswordRequest;
import com.rse.webservice.locket.payload.auth.requests.RegistrationRequest;
import com.rse.webservice.locket.payload.auth.responses.*;

public interface AuthenticationService {
    RegistrationResponse addNewUser(RegistrationRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    LogoutResponse logout();

    ChangePasswordResponse changePassword(ChangePasswordRequest request);


    ForgotPasswordResponse resetPassword(ForgotPasswordRequest request);
}

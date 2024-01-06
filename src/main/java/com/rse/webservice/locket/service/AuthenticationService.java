package com.rse.webservice.locket.service;

import com.rse.webservice.locket.payload.auth.requests.AuthenticationRequest;
import com.rse.webservice.locket.payload.auth.requests.ChangePasswordRequest;
import com.rse.webservice.locket.payload.auth.requests.RegistrationRequest;
import com.rse.webservice.locket.payload.auth.responses.AuthenticationResponse;
import com.rse.webservice.locket.payload.auth.responses.ChangePasswordResponse;
import com.rse.webservice.locket.payload.auth.responses.LogoutResponse;
import com.rse.webservice.locket.payload.auth.responses.RegistrationResponse;

public interface AuthenticationService {
    RegistrationResponse addNewUser(RegistrationRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    LogoutResponse logout();

    ChangePasswordResponse changePassword(ChangePasswordRequest request);



}

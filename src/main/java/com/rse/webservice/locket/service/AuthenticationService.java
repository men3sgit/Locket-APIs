package com.rse.webservice.locket.service;

import com.rse.webservice.locket.payload.request.auth.AuthenticationRequest;
import com.rse.webservice.locket.payload.request.RegistrationRequest;
import com.rse.webservice.locket.payload.response.auth.AuthenticationResponse;
import com.rse.webservice.locket.payload.response.auth.RegistrationResponse;

public interface AuthenticationService {
    RegistrationResponse addNewUser(RegistrationRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

}

package com.rse.webservice.locket.service;

import com.rse.webservice.locket.payload.request.AuthenticationRequest;
import com.rse.webservice.locket.payload.request.RegistrationRequest;
import com.rse.webservice.locket.payload.response.AuthenticationResponse;
import com.rse.webservice.locket.payload.response.RegistrationResponse;

public interface AuthenticationService {
    RegistrationResponse addNewUser(RegistrationRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

}

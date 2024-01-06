package com.rse.webservice.locket.service;

import com.rse.webservice.locket.payload.token.requests.TokenCreateRequest;
import com.rse.webservice.locket.payload.token.requests.TokenUpdateRequest;
import com.rse.webservice.locket.payload.token.responses.TokenCreateResponse;
import com.rse.webservice.locket.payload.token.responses.TokenUpdateResponse;

public interface TokenService {

    TokenUpdateResponse verifyNewUser(TokenUpdateRequest request);
    TokenUpdateResponse receiveNewPassword(TokenUpdateRequest request);

    void sendMailToVerify(String to, String name, String token);

    void sendMailToResetPassword(String to);

    TokenCreateResponse createRegistrationToken(TokenCreateRequest request);


}

package com.rse.webservice.locket.service;

import com.rse.webservice.locket.payload.freshtoken.requests.RefreshTokenCreateRequest;
import com.rse.webservice.locket.payload.freshtoken.requests.RefreshTokenSelfRequest;
import com.rse.webservice.locket.payload.freshtoken.responses.RefreshTokenCreateResponse;
import com.rse.webservice.locket.payload.freshtoken.responses.RefreshTokenSelfResponse;

public interface RefreshTokenService {
    RefreshTokenCreateResponse create(RefreshTokenCreateRequest request);
    RefreshTokenSelfResponse self(RefreshTokenSelfRequest request);
}

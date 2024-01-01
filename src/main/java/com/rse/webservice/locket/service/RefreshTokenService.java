package com.rse.webservice.locket.service;

import com.rse.webservice.locket.payload.freshtoken.requests.RefreshTokenCreateRequest;
import com.rse.webservice.locket.payload.freshtoken.responses.RefreshTokenCreateResponse;

public interface RefreshTokenService {
    RefreshTokenCreateResponse create(RefreshTokenCreateRequest request);
}

package com.rse.webservice.locket.service;

import com.rse.webservice.locket.payload.pin.requests.PinCreateRequest;
import com.rse.webservice.locket.payload.pin.response.PinCreateResponse;

public interface PinService {
    PinCreateResponse create(PinCreateRequest request);

    void delete(Long id);
}

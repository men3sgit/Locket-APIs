package com.rse.webservice.locket.service;

import com.rse.webservice.locket.payload.view.requests.ViewCreateRequest;
import com.rse.webservice.locket.payload.view.requests.ViewSearchRequest;
import com.rse.webservice.locket.payload.view.requests.ViewUpdateRequest;
import com.rse.webservice.locket.payload.view.responses.ViewCreateResponse;
import com.rse.webservice.locket.payload.view.responses.ViewSearchResponse;
import com.rse.webservice.locket.payload.view.responses.ViewUpdateResponse;

import java.util.List;

public interface ViewService {
    ViewCreateResponse create(ViewCreateRequest request);
    ViewUpdateResponse update(ViewUpdateRequest request);
    List<ViewSearchResponse> search(ViewSearchRequest request);
}

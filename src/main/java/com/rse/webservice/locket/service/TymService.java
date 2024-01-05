package com.rse.webservice.locket.service;

import com.rse.webservice.locket.payload.tym.requests.TymCreateRequest;
import com.rse.webservice.locket.payload.tym.requests.TymSearchRequest;
import com.rse.webservice.locket.payload.tym.requests.TymUpdateRequest;
import com.rse.webservice.locket.payload.tym.responses.TymCreateResponse;
import com.rse.webservice.locket.payload.tym.responses.TymSearchResponse;
import com.rse.webservice.locket.payload.tym.responses.TymUpdateResponse;

import java.util.List;

public interface TymService {

    TymCreateResponse create(TymCreateRequest request);

    TymUpdateResponse update(TymUpdateRequest request);

    List<TymSearchResponse> search(TymSearchRequest request);
}

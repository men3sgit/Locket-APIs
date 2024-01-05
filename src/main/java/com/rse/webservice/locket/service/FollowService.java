package com.rse.webservice.locket.service;

import com.rse.webservice.locket.payload.follow.requests.FollowByMeSearchRequest;
import com.rse.webservice.locket.payload.follow.requests.FollowCreateRequest;
import com.rse.webservice.locket.payload.follow.requests.FollowSearchRequest;
import com.rse.webservice.locket.payload.follow.requests.FollowUpdateRequest;
import com.rse.webservice.locket.payload.follow.responses.FollowByMeSearchResponse;
import com.rse.webservice.locket.payload.follow.responses.FollowCreateResponse;
import com.rse.webservice.locket.payload.follow.responses.FollowSearchResponse;
import com.rse.webservice.locket.payload.follow.responses.FollowUpdateResponse;

import java.util.List;

public interface FollowService {
    FollowCreateResponse create(FollowCreateRequest request);

    FollowUpdateResponse update(FollowUpdateRequest request);

    List<FollowSearchResponse> search(FollowSearchRequest request);

    List<FollowByMeSearchResponse> search(FollowByMeSearchRequest request);
}

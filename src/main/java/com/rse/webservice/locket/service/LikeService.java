package com.rse.webservice.locket.service;

import com.rse.webservice.locket.payload.like.requests.LikeCreateRequest;
import com.rse.webservice.locket.payload.like.requests.LikeDeleteRequest;
import com.rse.webservice.locket.payload.like.requests.LikeSearchRequest;
import com.rse.webservice.locket.payload.like.requests.LikeSelfRequest;
import com.rse.webservice.locket.payload.like.responses.LikeCreateResponse;
import com.rse.webservice.locket.payload.like.responses.LikeDeleteResponse;
import com.rse.webservice.locket.payload.like.responses.LikeSearchResponse;
import com.rse.webservice.locket.payload.like.responses.LikeSelfResponse;

public interface LikeService {

    LikeCreateResponse create(LikeCreateRequest request);

    LikeDeleteResponse delete(LikeDeleteRequest request);

    LikeSearchResponse search(LikeSearchRequest request);

    LikeSelfResponse self(LikeSelfRequest request);
}

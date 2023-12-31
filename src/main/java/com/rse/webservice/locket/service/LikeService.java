package com.rse.webservice.locket.service;

import com.rse.webservice.locket.payload.request.like.LikeCreateRequest;
import com.rse.webservice.locket.payload.request.like.LikeDeleteRequest;
import com.rse.webservice.locket.payload.request.like.LikeSearchRequest;
import com.rse.webservice.locket.payload.request.like.LikeSelfRequest;
import com.rse.webservice.locket.payload.response.like.LikeCreateResponse;
import com.rse.webservice.locket.payload.response.like.LikeDeleteResponse;
import com.rse.webservice.locket.payload.response.like.LikeSearchResponse;
import com.rse.webservice.locket.payload.response.like.LikeSelfResponse;

public interface LikeService {

    LikeCreateResponse create(LikeCreateRequest request);

    LikeDeleteResponse delete(LikeDeleteRequest request);

    LikeSearchResponse search(LikeSearchRequest request);

    LikeSelfResponse self(LikeSelfRequest request);
}

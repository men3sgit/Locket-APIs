package com.rse.webservice.locket.service;

import com.rse.webservice.locket.payload.request.post.PostCreateRequest;
import com.rse.webservice.locket.payload.request.post.PostDeleteRequest;
import com.rse.webservice.locket.payload.request.post.PostSelfRequest;
import com.rse.webservice.locket.payload.response.post.PostCreateResponse;
import com.rse.webservice.locket.payload.response.post.PostSelfResponse;

public interface PostService {

    PostCreateResponse create(PostCreateRequest request);

    PostSelfResponse self(PostSelfRequest request);
    PostSelfResponse delete(PostDeleteRequest request);
}

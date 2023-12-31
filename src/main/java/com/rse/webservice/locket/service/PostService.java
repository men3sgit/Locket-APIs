package com.rse.webservice.locket.service;

import com.rse.webservice.locket.payload.request.post.*;
import com.rse.webservice.locket.payload.response.post.PostCreateResponse;
import com.rse.webservice.locket.payload.response.post.PostSearchResponse;
import com.rse.webservice.locket.payload.response.post.PostSelfResponse;
import com.rse.webservice.locket.payload.response.post.PostUpdateResponse;

public interface PostService {

    PostCreateResponse create(PostCreateRequest request);

    PostSelfResponse self(PostSelfRequest request);

    PostSelfResponse delete(PostDeleteRequest request);

    PostSearchResponse search(PostSearchRequest request);

    PostUpdateResponse update(PostUpdateRequest request);
}

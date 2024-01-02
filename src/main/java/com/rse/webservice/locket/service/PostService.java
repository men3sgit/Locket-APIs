package com.rse.webservice.locket.service;

import com.rse.webservice.locket.payload.post.requests.*;
import com.rse.webservice.locket.payload.post.responses.PostCreateResponse;
import com.rse.webservice.locket.payload.post.responses.PostSearchResponse;
import com.rse.webservice.locket.payload.post.responses.PostSelfResponse;
import com.rse.webservice.locket.payload.post.responses.PostUpdateResponse;

public interface PostService {

    PostCreateResponse create(PostCreateRequest request);

    PostSelfResponse self(PostSelfRequest request);

    PostSelfResponse delete(PostDeleteRequest request);

    PostSearchResponse search(PostSearchRequest request);

    PostUpdateResponse update(PostUpdateRequest request);
}

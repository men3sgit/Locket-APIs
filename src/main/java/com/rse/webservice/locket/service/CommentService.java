package com.rse.webservice.locket.service;

import com.rse.webservice.locket.payload.comment.requests.*;
import com.rse.webservice.locket.payload.comment.responses.*;
import com.rse.webservice.locket.payload.post.requests.*;
import com.rse.webservice.locket.payload.post.responses.PostCreateResponse;
import com.rse.webservice.locket.payload.post.responses.PostSearchResponse;
import com.rse.webservice.locket.payload.post.responses.PostSelfResponse;
import com.rse.webservice.locket.payload.post.responses.PostUpdateResponse;

import java.util.List;

public interface CommentService {
    CommentCreateResponse create(CommentCreateRequest request);

    CommentSelfResponse self(CommentSelfRequest request);

    CommentDeleteResponse delete(CommentDeleteRequest request);

   List<CommentSearchResponse> search(CommentSearchRequest request);

    CommentUpdateResponse update(CommentUpdateRequest request);
}

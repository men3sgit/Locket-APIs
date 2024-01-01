package com.rse.webservice.locket.payload.post.responses;

import lombok.Data;

import java.util.List;

@Data
public class PostSearchResponse {

    // TODO: Paging
    List<PostSelfResponse> data;
}

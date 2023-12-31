package com.rse.webservice.locket.payload.response.like;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LikeSelfResponse {
    private Long id;
    private Long postId;
    private Long accountId;
}

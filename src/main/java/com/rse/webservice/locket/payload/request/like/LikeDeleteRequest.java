package com.rse.webservice.locket.payload.request.like;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class LikeDeleteRequest {
    private Long postId;
    private Long accountId;
}

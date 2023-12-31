package com.rse.webservice.locket.payload.request.like;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeCreateRequest {
    private Long postId;
    private Long accountId;
}

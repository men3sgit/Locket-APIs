package com.rse.webservice.locket.payload.like.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeCreateRequest {
    private Long postId;
    private Long accountId;
}

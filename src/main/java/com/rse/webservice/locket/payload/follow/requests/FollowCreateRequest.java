package com.rse.webservice.locket.payload.follow.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class FollowCreateRequest {

    private Long accountId;

    private Long followingId;

}

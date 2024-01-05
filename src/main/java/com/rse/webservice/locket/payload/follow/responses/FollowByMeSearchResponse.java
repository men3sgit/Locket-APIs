package com.rse.webservice.locket.payload.follow.responses;

import com.rse.webservice.locket.payload.common.responses.AuditResponse;
import lombok.Data;

@Data
public class FollowByMeSearchResponse extends AuditResponse {

    private Long accountId;

    private Long followingId;


}

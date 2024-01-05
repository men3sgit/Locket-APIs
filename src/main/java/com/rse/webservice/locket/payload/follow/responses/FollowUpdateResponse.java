package com.rse.webservice.locket.payload.follow.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class FollowUpdateResponse {
    private Boolean success;
}

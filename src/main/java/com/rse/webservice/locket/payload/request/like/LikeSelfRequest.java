package com.rse.webservice.locket.payload.request.like;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class LikeSelfRequest {
    private Long id;
}

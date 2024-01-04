package com.rse.webservice.locket.payload.tym.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class TymCreateRequest {

    private Long postId;
    private Long accountId;

}

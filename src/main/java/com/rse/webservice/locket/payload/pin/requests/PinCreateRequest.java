package com.rse.webservice.locket.payload.pin.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class PinCreateRequest {
    private Long postId;
    private Long accountId;
}

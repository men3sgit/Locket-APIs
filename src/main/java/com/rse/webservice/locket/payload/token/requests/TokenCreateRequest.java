package com.rse.webservice.locket.payload.token.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class TokenCreateRequest {
    private Long userId;

}

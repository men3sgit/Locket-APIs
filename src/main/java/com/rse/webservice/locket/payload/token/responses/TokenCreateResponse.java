package com.rse.webservice.locket.payload.token.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class TokenCreateResponse {
    private Long id;
    private String token;
}

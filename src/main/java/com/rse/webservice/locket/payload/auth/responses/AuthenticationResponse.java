package com.rse.webservice.locket.payload.auth.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class AuthenticationResponse {
    private String token;
}

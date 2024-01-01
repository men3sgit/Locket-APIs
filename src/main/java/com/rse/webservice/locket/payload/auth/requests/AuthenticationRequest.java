package com.rse.webservice.locket.payload.auth.requests;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}

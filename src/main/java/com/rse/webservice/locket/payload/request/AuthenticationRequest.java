package com.rse.webservice.locket.payload.request;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}

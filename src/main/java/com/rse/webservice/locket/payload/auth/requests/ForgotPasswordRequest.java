package com.rse.webservice.locket.payload.auth.requests;

import lombok.Data;

@Data
public class ForgotPasswordRequest {
    private String email;
}

package com.rse.webservice.locket.payload.verify.requests;

import lombok.Data;

@Data
public class NewUserVerificationRequest {
    private String token;
}

package com.rse.webservice.locket.payload.verify.requests;

import lombok.Data;

@Data
public class ResendTokenRequest {
    private String email;
}

package com.rse.webservice.locket.payload.request.verify;

import lombok.Data;

@Data
public class ResendTokenRequest {
    private String email;
}

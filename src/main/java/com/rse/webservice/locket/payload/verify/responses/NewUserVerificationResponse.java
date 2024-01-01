package com.rse.webservice.locket.payload.verify.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class NewUserVerificationResponse {
    private String message;
}

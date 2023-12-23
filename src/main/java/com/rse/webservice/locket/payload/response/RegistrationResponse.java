package com.rse.webservice.locket.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class RegistrationResponse {
    private Long id;
}

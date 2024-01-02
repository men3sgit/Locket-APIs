package com.rse.webservice.locket.payload.freshtoken.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class RefreshTokenCreateResponse {
    private Long id;
}

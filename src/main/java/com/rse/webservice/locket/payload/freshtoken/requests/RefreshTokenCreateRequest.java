package com.rse.webservice.locket.payload.freshtoken.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class RefreshTokenCreateRequest {
    private Long userId;
}

package com.rse.webservice.locket.payload.freshtoken.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefreshTokenCreateResponse {
    private String accessToken;
    private String type;
    private String refreshToken;
    private Long userId;
}

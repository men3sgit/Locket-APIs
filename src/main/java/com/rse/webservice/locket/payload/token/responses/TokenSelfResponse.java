package com.rse.webservice.locket.payload.token.responses;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class TokenSelfResponse {
    private String accessToken;

    private String type;

    private String freshToken;

    private Long userId;

    private Timestamp expiredAt;
}

package com.rse.webservice.locket.payload.freshtoken.responses;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class RefreshTokenSelfResponse {
    private String accessToken;

    private String type;

    private String freshToken;

    private Long userId;

    private Timestamp expiredAt;
}

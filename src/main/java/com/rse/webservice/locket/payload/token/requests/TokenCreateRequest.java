package com.rse.webservice.locket.payload.token.requests;

import com.rse.webservice.locket.enums.TokenType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor(staticName = "of")
public class TokenCreateRequest {
    private Long userId;

}

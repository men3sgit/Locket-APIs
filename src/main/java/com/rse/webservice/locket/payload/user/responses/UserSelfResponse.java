package com.rse.webservice.locket.payload.user.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserSelfResponse {
    private Long id;
    private String username;
    private String roles;
    private Timestamp createdAt;
}

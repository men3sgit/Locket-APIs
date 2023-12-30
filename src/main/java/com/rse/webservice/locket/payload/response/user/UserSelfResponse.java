package com.rse.webservice.locket.payload.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserSelfResponse {
    private Long id;
    private String email;
    private String name;
    private String phone;
}

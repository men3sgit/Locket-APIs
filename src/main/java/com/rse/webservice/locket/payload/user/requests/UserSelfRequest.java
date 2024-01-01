package com.rse.webservice.locket.payload.user.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class UserSelfRequest {
    private Long id;
}

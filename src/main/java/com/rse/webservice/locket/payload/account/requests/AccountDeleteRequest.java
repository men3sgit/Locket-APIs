package com.rse.webservice.locket.payload.account.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class AccountDeleteRequest {
    private Long id;
}

package com.rse.webservice.locket.payload.request.account;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class AccountDeleteRequest {
    private Long id;
}

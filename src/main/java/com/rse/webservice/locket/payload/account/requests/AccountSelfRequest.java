package com.rse.webservice.locket.payload.account.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class AccountSelfRequest {
    private Long accountId;
}

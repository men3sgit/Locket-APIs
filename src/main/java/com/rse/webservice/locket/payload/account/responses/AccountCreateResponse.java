package com.rse.webservice.locket.payload.account.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class AccountCreateResponse {
    private Long id;
}

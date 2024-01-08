package com.rse.webservice.locket.service;

import com.rse.webservice.locket.payload.account.requests.*;
import com.rse.webservice.locket.payload.account.responses.*;

public interface AccountService {
    AccountCreateResponse create(AccountCreateRequest request);

    AccountUpdateResponse update(AccountUpdateRequest request);

    AccountDeleteResponse delete(AccountDeleteRequest request);

    AccountSearchResponse search(AccountSearchRequest request);

    AccountSelfResponse self(AccountSelfRequest request);

    Boolean existsAccount(Long accountId);
}

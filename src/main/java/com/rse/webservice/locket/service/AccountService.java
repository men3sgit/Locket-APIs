package com.rse.webservice.locket.service;

import com.rse.webservice.locket.payload.request.account.*;
import com.rse.webservice.locket.payload.response.account.*;

public interface AccountService {
    AccountCreateResponse create(AccountCreateRequest request);

    AccountUpdateResponse update(AccountUpdateRequest request);

    AccountDeleteResponse delete(AccountDeleteRequest request);

    AccountSearchResponse search(AccountSearchRequest request);

    AccountSelfResponse self(AccountSelfRequest request);
}

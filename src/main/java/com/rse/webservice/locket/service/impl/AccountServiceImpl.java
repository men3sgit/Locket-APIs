package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.payload.account.requests.*;
import com.rse.webservice.locket.payload.account.requests.responses.*;
import com.rse.webservice.locket.payload.account.responses.*;
import com.rse.webservice.locket.payload.request.account.*;
import com.rse.webservice.locket.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public AccountCreateResponse create(AccountCreateRequest request) {
        return null;
    }

    @Override
    public AccountUpdateResponse update(AccountUpdateRequest request) {
        return null;
    }

    @Override
    public AccountDeleteResponse delete(AccountDeleteRequest request) {
        return null;
    }

    @Override
    public AccountSearchResponse search(AccountSearchRequest request) {
        return null;
    }

    @Override
    public AccountSelfResponse self(AccountSelfRequest request) {

        return null;
    }
}

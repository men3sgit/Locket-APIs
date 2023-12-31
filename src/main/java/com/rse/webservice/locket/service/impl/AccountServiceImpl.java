package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.payload.request.account.AccountSelfRequest;
import com.rse.webservice.locket.payload.response.account.AccountSelfResponse;
import com.rse.webservice.locket.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public AccountSelfResponse self(AccountSelfRequest request) {

        return null;
    }
}

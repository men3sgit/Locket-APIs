package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.exception.ApiRequestException;
import com.rse.webservice.locket.model.Account;
import com.rse.webservice.locket.payload.account.requests.*;
import com.rse.webservice.locket.payload.account.responses.*;
import com.rse.webservice.locket.repository.AccountRepository;
import com.rse.webservice.locket.service.AccountService;
import com.rse.webservice.locket.utils.DataUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public AccountCreateResponse create(AccountCreateRequest request) {
        var newAccount = DataUtils.copyProperties(request, Account.class);
        accountRepository.save(newAccount);
        return AccountCreateResponse.of(newAccount.getId());
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
        var storedAccount = getAccount(request.getId());
        return DataUtils.copyProperties(storedAccount, AccountSelfResponse.class);
    }
    public Account getAccount(Long userId){
        return accountRepository.findByUserId(userId).orElseThrow(() -> new ApiRequestException(""));
    }
}

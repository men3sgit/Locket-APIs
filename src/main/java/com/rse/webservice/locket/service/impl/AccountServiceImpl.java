package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.exception.ApiRequestException;
import com.rse.webservice.locket.model.Account;
import com.rse.webservice.locket.payload.account.requests.*;
import com.rse.webservice.locket.payload.account.responses.*;
import com.rse.webservice.locket.payload.image.requests.ImageUpdateRequest;
import com.rse.webservice.locket.payload.image.requests.ImageUploadRequest;
import com.rse.webservice.locket.repository.AccountRepository;
import com.rse.webservice.locket.service.AccountService;
import com.rse.webservice.locket.service.CommonService;
import com.rse.webservice.locket.service.ImageService;
import com.rse.webservice.locket.utils.DataUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final ImageService imageService;
    private final CommonService commonService;

    @Override
    public AccountCreateResponse create(AccountCreateRequest request) {
        var newAccount = DataUtils.copyProperties(request, Account.class);
        accountRepository.save(newAccount);
        return AccountCreateResponse.of(newAccount.getId());
    }

    @Override
    public AccountUpdateResponse update(AccountUpdateRequest request) {
        var storedAccount = getAccount(request.getAccountId());
        var updateAccount = DataUtils.copyProperties(request, Account.class);

        if (Objects.isNull(request.getAvatar()) || request.getAvatar().isEmpty()) {
            clearAvatarPath(storedAccount);
            updateAccount.setId(storedAccount.getId());
            return AccountUpdateResponse.of(storedAccount.getId());
        }


        handleAvatarUpdate(request, storedAccount, updateAccount);
        updateAccount.setId(storedAccount.getId());
        accountRepository.save(updateAccount);
        return AccountUpdateResponse.of(storedAccount.getId());
    }

    private void handleAvatarUpdate(AccountUpdateRequest request, Account storedAccount, Account updateAccount) {
        String storedAvatarPath = storedAccount.getAvatarPath();
        updateAccount.setAvatarPath(storedAvatarPath);

        if (Objects.isNull(storedAvatarPath)) {
            handleNewAvatar(request, updateAccount);
        } else {
            handleExistingAvatar(request, storedAccount);
        }
    }

    private void handleNewAvatar(AccountUpdateRequest request, Account updateAccount) {
        var uploadResponse = imageService.upload(ImageUploadRequest.of(request.getAvatar()));
        String avatarPath = uploadResponse.getPath();
        updateAccount.setAvatarPath(avatarPath);
    }

    private void handleExistingAvatar(AccountUpdateRequest request, Account storedAccount) {
        String imageId = storedAccount.getAvatarPath().substring(storedAccount.getAvatarPath().lastIndexOf("/") + 1);
        var updateImageRequest = ImageUpdateRequest.of(Long.parseLong(imageId), request.getAvatar());
        imageService.update(updateImageRequest);

    }

    private void clearAvatarPath(Account storedAccount) {
        storedAccount.setAvatarPath(null);
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
        var storedAccount = getAccount(request.getAccountId());
        var accountResponse = DataUtils.copyProperties(storedAccount, AccountSelfResponse.class);
        var currentEmail = commonService.getLoginUsername();
        accountResponse.setEmail(currentEmail);
        return accountResponse;
    }

    @Override
    public Boolean existsAccount(Long accountId) {
        return accountRepository.existsById(accountId);
    }

    public Account getAccount(Long accountId) {
        return accountRepository.findByUserId(accountId).orElseThrow(() -> new ApiRequestException(""));
    }
}

package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.exception.ApiRequestException;
import com.rse.webservice.locket.model.Account;
import com.rse.webservice.locket.payload.account.requests.*;
import com.rse.webservice.locket.payload.account.responses.*;
import com.rse.webservice.locket.payload.image.requests.ImageUpdateRequest;
import com.rse.webservice.locket.payload.image.requests.ImageUploadRequest;
import com.rse.webservice.locket.repository.AccountRepository;
import com.rse.webservice.locket.service.AccountService;
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

    @Override
    public AccountCreateResponse create(AccountCreateRequest request) {
        var newAccount = DataUtils.copyProperties(request, Account.class);
        accountRepository.save(newAccount);
        return AccountCreateResponse.of(newAccount.getId());
    }

    @Override
    public AccountUpdateResponse update(AccountUpdateRequest request) {
        var storedAccount = getAccount(request.getUserId());
        var updateAccount = DataUtils.copyProperties(request, Account.class);
        String storedAvatarPath = storedAccount.getAvatarPath();
        if (Objects.isNull(storedAvatarPath)) {
            var uploadResponse = imageService.upload(ImageUploadRequest.of(request.getAvatar()));
            // formatted code
            String avatarPath = uploadResponse.getPath().replace("/api/v1/files/", "/api/v1/images/");
            updateAccount.setAvatarPath(avatarPath);
        } else {
            String fileIdStr = storedAvatarPath.substring(storedAvatarPath.lastIndexOf("/") + 1);
            var updateImageRequest = ImageUpdateRequest.of(Long.parseLong(fileIdStr), request.getAvatar());
            imageService.update(updateImageRequest);
            updateAccount.setAvatarPath(storedAvatarPath);
        }


        updateAccount.setId(storedAccount.getId());
        accountRepository.save(updateAccount);
        return AccountUpdateResponse.of(storedAccount.getId());
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
        var storedAccount = getAccount(request.getUserId());
        return DataUtils.copyProperties(storedAccount, AccountSelfResponse.class);
    }

    public Account getAccount(Long userId) {
        return accountRepository.findByUserId(userId).orElseThrow(() -> new ApiRequestException(""));
    }
}

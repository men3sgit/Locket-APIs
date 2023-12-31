package com.rse.webservice.locket.controller;

import com.rse.webservice.locket.exception.ApiRequestException;
import com.rse.webservice.locket.payload.ApiResponse;
import com.rse.webservice.locket.payload.account.requests.AccountDeleteRequest;
import com.rse.webservice.locket.payload.account.requests.AccountSelfRequest;
import com.rse.webservice.locket.payload.account.requests.AccountUpdateRequest;
import com.rse.webservice.locket.service.AccountService;
import com.rse.webservice.locket.service.CommonService;
import com.rse.webservice.locket.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountService accountService;
    private final CommonService commonService;

    private final PostService postService;

    @GetMapping("/{id}/posts")
    public ApiResponse<?> findPostsByAccountId(@PathVariable Long id) {
        if (!accountService.existsAccount(id)) {
            throw new ApiRequestException("Account doesn't exist");
        }

        var response = postService.getPostsByAccountId(id);

        return new ApiResponse<>(response);
    }

    @GetMapping("/{id}")
    public ApiResponse<?> self(@PathVariable Long id) {
        var response = accountService.self(AccountSelfRequest.of(id));
        return new ApiResponse<>(response);
    }

    // chua chay
    @GetMapping
    public ApiResponse<?> getAccountByToken() {
        var currentUserId = AccountSelfRequest.of(commonService.getLoginId());
        var response = accountService.self(currentUserId);
        return new ApiResponse<>(response);
    }

    @PutMapping
    public ApiResponse<?> update(AccountUpdateRequest request) {
        request.setAccountId(commonService.getCurrentAccountId());
        var response = accountService.update(request);
        return new ApiResponse<>(response);
    }

    @PutMapping("/{id}")
    public ApiResponse<?> update(@PathVariable Long id, AccountUpdateRequest request) {
        request.setAccountId(id);
        var response = accountService.update(request);
        return new ApiResponse<>(response);
    }


    @DeleteMapping("/{id}")
    public ApiResponse<?> delete(@PathVariable Long id) {
        var response = accountService.delete(AccountDeleteRequest.of(id));
        return new ApiResponse<>(response);
    }
}

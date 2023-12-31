package com.rse.webservice.locket.controller;

import com.rse.webservice.locket.payload.request.account.AccountCreateRequest;
import com.rse.webservice.locket.payload.request.account.AccountDeleteRequest;
import com.rse.webservice.locket.payload.request.account.AccountSelfRequest;
import com.rse.webservice.locket.payload.request.account.AccountUpdateRequest;
import com.rse.webservice.locket.payload.response.ApiResponse;
import com.rse.webservice.locket.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{id}")
    public ApiResponse<?> self(@PathVariable Long id) {
        var response = accountService.self(AccountSelfRequest.of(id));
        return new ApiResponse<>(response);
    }

    @PutMapping("/{id}")
    public ApiResponse<?> update(@PathVariable Long id, AccountUpdateRequest request) {
        request.setId(id);
        var response = accountService.update(request);
        return new ApiResponse<>(response);
    }

    @PostMapping(path = {"", "/", "/create"})
    public ApiResponse<?> create(@RequestBody AccountCreateRequest request) {
        var response = accountService.create(request);
        return new ApiResponse<>(response);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> delete(@PathVariable Long id){
        var response = accountService.delete(AccountDeleteRequest.of(id));
        return new ApiResponse<>(response);
    }
}

package com.rse.webservice.locket.controller;

import com.rse.webservice.locket.payload.ApiResponse;
import com.rse.webservice.locket.payload.freshtoken.requests.RefreshTokenCreateRequest;
import com.rse.webservice.locket.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/fresh-token")
public class RefreshTokenController {
    private final RefreshTokenService refreshTokenService;

    @GetMapping
    public ApiResponse<?> create(@RequestBody RefreshTokenCreateRequest request) {
        var response = refreshTokenService.create(request);
        return new ApiResponse<>(response);
    }
}

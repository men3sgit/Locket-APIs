package com.rse.webservice.locket.controller;

import com.rse.webservice.locket.payload.ApiResponse;
import com.rse.webservice.locket.payload.freshtoken.requests.RefreshTokenCreateRequest;
import com.rse.webservice.locket.payload.freshtoken.requests.RefreshTokenSelfRequest;
import com.rse.webservice.locket.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/refresh-tokens")
public class RefreshTokenController {
    private final RefreshTokenService refreshTokenService;

    @PostMapping
    public ApiResponse<?> create(@RequestBody RefreshTokenCreateRequest request) {
        var response = refreshTokenService.create(request);
        return new ApiResponse<>(response);
    }

    @GetMapping
    public ApiResponse<?> self(@RequestBody RefreshTokenSelfRequest request){
        var response = refreshTokenService.self(request);
        return new ApiResponse<>(response);
    }
}

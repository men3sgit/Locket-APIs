package com.rse.webservice.locket.controller;

import com.rse.webservice.locket.payload.request.user.UserSelfRequest;
import com.rse.webservice.locket.payload.response.ApiResponse;
import com.rse.webservice.locket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ApiResponse<?> getAllUsers() {
        return new ApiResponse<>(userService.search());
    }

    @GetMapping("/{id}")
    public ApiResponse<?> self(@PathVariable(value = "id") Long id) {
        return new ApiResponse<>(userService.self(UserSelfRequest.of(id)));
    }

    @GetMapping("/principal")
    public Object principal(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) {
        return principal;
    }


}

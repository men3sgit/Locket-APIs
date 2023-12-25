package com.rse.webservice.locket.controller;

import com.rse.webservice.locket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok("Nguyen Thi Tu Linh");
    }

    @GetMapping("/principal")
    public Principal principal(@AuthenticationPrincipal Principal principal) {
        return principal;
    }

}

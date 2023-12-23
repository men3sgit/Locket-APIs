package com.rse.webservice.locket.controller;

import com.rse.webservice.locket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok("Hello world");
    }

}

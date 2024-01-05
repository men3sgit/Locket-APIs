package com.rse.webservice.locket.controller;

import com.rse.webservice.locket.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tokens")
public class TokenController {
    private final TokenService tokenService;

}

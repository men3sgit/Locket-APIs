package com.rse.webservice.locket.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            // Access user details from authentication.getPrincipal()
            // Example: OAuth2User user = (OAuth2User) authentication.getPrincipal();
            // Then, you can access user.getName()
            return "Hello, " + authentication.getName();
        } else {
            return "User not authenticated";
        }
    }
}

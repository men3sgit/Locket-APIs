package com.rse.webservice.locket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping
    public String greeting(){
        return "Hello World";
    }
}

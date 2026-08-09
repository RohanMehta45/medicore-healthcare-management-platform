package com.example.SecurityLearning.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class SampleApi {

    @GetMapping
    public String hello() {
        return "Security Working";
    }

    @GetMapping("/authorities")
    public Object authorities(Authentication authentication) {

        return authentication.getAuthorities();
    }
}
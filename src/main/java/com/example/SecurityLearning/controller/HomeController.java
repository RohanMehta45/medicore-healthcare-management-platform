package com.example.SecurityLearning.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public Object home(Authentication authentication) {

        OAuth2User user = (OAuth2User) authentication.getPrincipal();

        return user.getAttributes();

    }

}
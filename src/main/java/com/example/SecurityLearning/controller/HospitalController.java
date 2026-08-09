package com.example.SecurityLearning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HospitalController {

    @GetMapping("/")
    public String home() {
        return "Hospital Management API Running";
    }

}

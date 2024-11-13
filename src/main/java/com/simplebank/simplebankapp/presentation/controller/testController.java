package com.simplebank.simplebankapp.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {
    @GetMapping("/test")
    public String testSecured() {
        return "Secured EndPoint";
    }
}

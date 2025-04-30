package com.code.SimpleBlogAPI.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.SimpleBlogAPI.dto.RegistrationRequest;
import com.code.SimpleBlogAPI.service.AuthService;

@RestController
@RequestMapping("/blog")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // registering user
    @PostMapping("/register")
    public String registeringUser(@RequestBody RegistrationRequest request) {
        return authService.registerUser(request);
    }

    // custom login not created because spring security handles it
    // automatically
    @GetMapping("/login")
    public String login() {
        return "login succesfull!!!";
    }
}
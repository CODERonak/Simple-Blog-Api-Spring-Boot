package com.code.SimpleBlogAPI.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
public class AuthController {
    @GetMapping("/register")
    public String viewAllPosts() {
        return "register page";
    }

    @GetMapping("/login")
    public String viewSinglePosts() {
        return "login page";
    }
}
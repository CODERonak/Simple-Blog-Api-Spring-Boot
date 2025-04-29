package com.code.SimpleBlogAPI.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
public class PublicController {
    
    @GetMapping("/posts")
    public String viewAllPosts() {
        return "All posts";
    }

    @GetMapping("/posts/{id}")
    public String viewSinglePosts() {
        return "single posts";
    }
}
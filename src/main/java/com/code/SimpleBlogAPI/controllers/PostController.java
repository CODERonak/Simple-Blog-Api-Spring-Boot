package com.code.SimpleBlogAPI.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
public class PostController {
    @GetMapping("/dashboard")
    public String viewAllPosts() {
        return "dashboard page";
    }

    @GetMapping("/posts/create")
    public String createPost() {
        return "creating posts page";
    }

    @GetMapping("/posts/edit/{id}")
    public String editPosts() {
        return "creating posts page";
    }

    @GetMapping("/posts/delete/{id}")
    public String deletePosts() {
        return "creating posts page";
    }
}
package com.code.SimpleBlogAPI.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.SimpleBlogAPI.entity.Post;
import com.code.SimpleBlogAPI.service.PostService;

@RestController
@RequestMapping("/blog")
public class PublicController {

    private final PostService postService;

    public PublicController(PostService postService) {
        this.postService = postService;
    }
    
    @GetMapping("/posts")
    public List<Post> viewAllPosts() {
        return postService.viewAllPosts();
    }

    @GetMapping("/posts/{id}")
    public String viewSinglePosts() {
        return "single posts";
    }
}
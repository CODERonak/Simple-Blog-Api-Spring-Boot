package com.code.SimpleBlogAPI.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.SimpleBlogAPI.entity.Post;
import com.code.SimpleBlogAPI.service.PostService;

@RestController
@RequestMapping("/blog")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // created it for the user to post
    @PostMapping("/posts/create")
    public String createPost(@RequestBody Post post, Authentication authentication) {
        postService.createPost(post, authentication);
        return "Created the post";
    }

    // created it for the user to edit
    @PutMapping("/posts/edit/{id}")
    public String editPost(@PathVariable int id, @RequestBody Post post) {
        postService.editPost(id, post);
        return "Edited the post";
    }

    // created it for the user to delete
    @DeleteMapping("/posts/delete/{id}")
    public String deletePosts(@PathVariable int id) {
        postService.deletePost(id);
        return "deleted the post";
    }

    // created it for the user to view the posts he has created
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/dashboard")
    public List<Post> viewDashboardPosts(Authentication authentication) {
        String username = authentication.getName();
        return postService.viewDashboardPosts(username);
    }
}
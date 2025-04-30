package com.code.SimpleBlogAPI.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.code.SimpleBlogAPI.entity.Post;
import com.code.SimpleBlogAPI.repository.PostRepository;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // for public viewing
    public List<Post> viewAllPosts() {
        return postRepository.findAll();
    }

    // for public viewing
    public Post viewSinglePost(int id) {
        return postRepository.findById(id).orElse(null);
    }

    // for creating posts
    public Post createPost(Post post, Authentication authentication) {
        String username = authentication.getName();
        post.setCreatedBy(username);
        return postRepository.save(post);
    }

    // for editing posts
    public Post editPost(int id, Post post) {
        post.setId(id);
        return postRepository.save(post);
    }

    // for deleting posts
    public void deletePost(int id) {
        postRepository.deleteById(id);
    }

    // for dashboard to see the posts created
    public List<Post> viewDashboardPosts(String username) {
        return postRepository.findByCreatedBy(username);
    }

}
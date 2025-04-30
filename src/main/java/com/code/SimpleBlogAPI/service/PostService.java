package com.code.SimpleBlogAPI.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.code.SimpleBlogAPI.entity.Post;
import com.code.SimpleBlogAPI.repository.PostRepository;

@Service
public class PostService {

    private final PostRepository postRepository;   

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> viewAllPosts() {
        return postRepository.findAll();
    }

    public Post viewSinglePost(int id) {
        return postRepository.findById(id).orElse(null);
    }

}
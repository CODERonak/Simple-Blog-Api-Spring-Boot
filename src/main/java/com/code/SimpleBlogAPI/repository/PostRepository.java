package com.code.SimpleBlogAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.code.SimpleBlogAPI.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

}
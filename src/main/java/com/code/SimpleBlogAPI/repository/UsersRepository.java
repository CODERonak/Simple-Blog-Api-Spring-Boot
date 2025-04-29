package com.code.SimpleBlogAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.SimpleBlogAPI.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>{

}
package com.code.SimpleBlogAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import com.code.SimpleBlogAPI.dto.RegistrationRequest;

@Service
public class AuthService {
    // created the instance of JdbcUserDetailsManager and BCryptPasswordEncoder
    private final JdbcUserDetailsManager manager;
    private final BCryptPasswordEncoder passwordEncoder;

    // created the constructor for the auth service
    @Autowired
    public AuthService(JdbcUserDetailsManager manager, BCryptPasswordEncoder passwordEncoder) {
        this.manager = manager;
        this.passwordEncoder = passwordEncoder;
    }

    // method to register the user
    public String registerUser(RegistrationRequest request) {
        // checking if the user already exists
        if (manager.userExists(request.getUsername())) {
            return "User already Exists!!!";
        }

        // creating the user
        User.UserBuilder user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles("USER");

                // save's the user
        manager.createUser(user.build());
        return "User registered successfully!!!";
    }

}
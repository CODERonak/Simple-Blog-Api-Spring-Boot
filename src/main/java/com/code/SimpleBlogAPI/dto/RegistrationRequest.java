package com.code.SimpleBlogAPI.dto;

import lombok.Getter;
import lombok.Setter;

// DTO for registration request
@Getter
@Setter
public class RegistrationRequest {
    private String username;
    private String password;
}
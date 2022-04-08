package com.example.ssrfapp.dto;

import org.springframework.stereotype.Component;

public class LoginRequestDTO {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}

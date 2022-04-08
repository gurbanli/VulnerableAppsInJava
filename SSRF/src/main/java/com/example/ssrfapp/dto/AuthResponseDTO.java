package com.example.ssrfapp.dto;

import org.springframework.stereotype.Component;

@Component
public class AuthResponseDTO {
    private String message;
    private Integer statusCode;

    public String getMessage() {
        return message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}

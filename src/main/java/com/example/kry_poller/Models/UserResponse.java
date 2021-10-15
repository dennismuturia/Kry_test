package com.example.kry_poller.Models;

import lombok.Data;

@Data
public class UserResponse {
    private Long userId;
    private String email;
    private String message;
    private String token;
    public UserResponse(Long userId,String email, String message, String token){
        this.userId = userId;
        this.email = email;
        this.message = message;
        this.token = token;
    }
}

package com.example.kry_poller.Validation;

import com.example.kry_poller.Models.UserResponse;
import com.example.kry_poller.Models.Users;

public interface Validation {
    public UserResponse loginValidation(Users usr);
    public Long userId();
}

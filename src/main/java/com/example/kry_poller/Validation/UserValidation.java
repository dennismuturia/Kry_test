package com.example.kry_poller.Validation;

import com.example.kry_poller.Models.UserResponse;
import com.example.kry_poller.Models.Users;


public interface UserValidation extends Validation{

    public String createToken(Users users);
    public boolean tokenValidation(String token, long user_id);
    public String hashPassword(Users usr);

}

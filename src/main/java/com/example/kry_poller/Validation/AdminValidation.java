package com.example.kry_poller.Validation;

import com.example.kry_poller.Models.Users;

import java.util.Date;

public interface AdminValidation extends Validation{
    private String createToken(Users users){
        return new Date().getTime() + users.getEmail() ;
    }
}

package com.example.kry_poller;

import com.example.kry_poller.Models.UserResponse;
import com.example.kry_poller.Models.Users;
import com.example.kry_poller.Repositories.UserRepository;
import com.example.kry_poller.Validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;
@Service
public class ValidationClass extends Encrypt implements UserValidation {
    @Autowired
    private UserRepository userRepository;


    @Override
    public String createToken(Users users) {
        return new Date().getTime() + users.getEmail() ;
    }

    @Override
    public boolean tokenValidation(String token, long user_id) {
        String x = userRepository.findUsersByUserId(user_id).getPrivateToken();
        return userRepository.findUsersByUserId(user_id).getPrivateToken().equals(token);
    }

    @Override
    public String hashPassword(Users usr) {
        return null;
    }


    @Override
    public UserResponse loginValidation(Users usr) {
        return null;
    }

    @Override
    public Long userId() {
        return new Date().getTime()+new Random().nextLong();
    }

    @Override
    public String getSHA(String password) throws NoSuchAlgorithmException {
        return super.getSHA(password);
    }
}

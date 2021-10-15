package com.example.kry_poller.Services;

import com.example.kry_poller.Models.UserResponse;
import com.example.kry_poller.Models.Users;
import com.example.kry_poller.Repositories.UserRepository;
import com.example.kry_poller.UserNotFoundException;
import com.example.kry_poller.ValidationClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


@Service
public class UserService extends ValidationClass{
    static Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    ValidationClass validationClass;
    @Autowired
    UserRepository userRepository;



    public UserResponse checkLogin(Users users) throws UserNotFoundException, NoSuchAlgorithmException {
        Users users1 = userRepository.findUsersByEmail(users.getEmail());
        users1.setPrivateToken(validationClass.createToken(users));
        userRepository.save(users1);
        return users1.getPassword().equals(getSHA(users.getPassword())) ? new UserResponse(users1.getUserId(),users1.getEmail(), "SUCCESS", users1.getPrivateToken()) : new UserResponse(null,users.getEmail(), "FAILURE", null);
    }

    public UserResponse registerUser(Users usr) throws NoSuchAlgorithmException {
        if(!userRepository.findByEmail(usr.getEmail()).isPresent()){
            Users newUser = new Users(null,
                    usr.getEmail(),
                    getSHA(usr.getPassword()),
                    validationClass.createToken(usr),
                    new ArrayList<>());
            userRepository.save(newUser);
            log.info("received register request from: "+ usr.getEmail());
            return new UserResponse(newUser.getUserId(),newUser.getEmail(), "SUCCESS", newUser.getPrivateToken());
        }
        log.info("rejected register request from: "+ usr.getEmail());
        return new UserResponse(null,usr.getEmail(), "EXISTS", null);
    }

    @Override
    public boolean tokenValidation(String token, long user_id) {
        return super.tokenValidation(token, user_id);
    }
}

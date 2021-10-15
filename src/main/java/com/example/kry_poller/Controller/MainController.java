package com.example.kry_poller.Controller;

import com.example.kry_poller.Models.ServiceResponse;
import com.example.kry_poller.Models.Services;
import com.example.kry_poller.Models.UserResponse;
import com.example.kry_poller.Models.Users;
import com.example.kry_poller.Repositories.UserRepository;
import com.example.kry_poller.Services.ServicesService;
import com.example.kry_poller.Services.UserService;
import com.example.kry_poller.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MainController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServicesService servicesService;
    @PostMapping("/login")
    public UserResponse login(@Valid @RequestBody Users user) throws UserNotFoundException, NoSuchAlgorithmException {
        return userService.checkLogin(user);
    }

    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody Users user) throws NoSuchAlgorithmException {
        return userService.registerUser(user);
    }

    @GetMapping("/{userId}/services/{token}")
    public List<Services> allServices(@PathVariable("userId")Long userId, @PathVariable("token")String userToken){
        if(userService.tokenValidation(userToken, userId)){
            return servicesService.getAllServices(userId);
        }else return new ArrayList<>();

    }
    @PostMapping("/{userId}/createservice/{token}")
    public ServiceResponse createService(@PathVariable("userId") Long userId, @Valid @RequestBody Services services, @PathVariable("token")String userToken){
        if(userService.tokenValidation(userToken, userId)){
            return servicesService.registerService(services, userId);
        }else return new ServiceResponse(0L, null, null, 0, "FALSE");

    }

    //update service
    @PostMapping("/{userId}/updateservice/{token}")
    public ServiceResponse updateService(@PathVariable("userId") Long userId, @Valid @RequestBody Services services, @PathVariable("token")String userToken){
        if(userService.tokenValidation(userToken, userId))
            return servicesService.registerService(services, userId);
       else return new ServiceResponse(0L, null, null, 0, "FALSE");
    }

    //delete service
    @PostMapping("/{userId}/deleteService/{token}")
    public HttpStatus deleteService(@PathVariable("userId") Long userId, @Valid @RequestBody Services services, @PathVariable("token")String userToken){
        if(servicesService.deleteService(services))
            return HttpStatus.OK;
        else return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @GetMapping("/test/{userId}")
    public HttpStatus myRegister(@PathVariable("userId") Long userId, @RequestBody Map<String, Object> requestBody){
        String token = userRepository.findUsersByUserId(userId).getPrivateToken();
        if(token.equals(requestBody.get("token"))){
            return HttpStatus.OK;
        }else{
            return HttpStatus.FORBIDDEN;
        }
    }
}

package com.example.kry_poller;

import com.example.kry_poller.Models.Users;
import com.example.kry_poller.Repositories.UserRepository;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
class ValidationClassTest {
    @MockBean
    public UserRepository userRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void createToken() {

        when(userRepository.findUsersByUserId(1L)).thenReturn(
                new Users(1L, "xyz@gmail.com", "123", new Date() + "xyz@gmail.com", new ArrayList<>()));
        Long now = new Date().getTime();
        assertNotEquals(userRepository.findUsersByUserId(1L).getPrivateToken(),
                now + "xyz@gmail.com");
    }

    @Test
    void tokenValidation() {

    }
}
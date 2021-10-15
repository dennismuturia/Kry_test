package com.example.kry_poller;

import com.example.kry_poller.Models.Services;
import com.example.kry_poller.Models.Users;
import com.example.kry_poller.Repositories.ServicesRepository;
import com.example.kry_poller.Repositories.UserRepository;
import com.example.kry_poller.Services.ServicesService;
import com.example.kry_poller.Services.UserService;
import org.apache.catalina.User;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class KryPollerApplicationTests {
    @MockBean
    public UserRepository userRepository;

    @MockBean
    public ServicesRepository servicesRepository;

    @Autowired
    public UserService userService;


    @Test
    void contextLoads() {
    }

    @Test
    void createToken() {

        when(userRepository.findUsersByUserId(1L)).thenReturn(
                new Users(1L, "xyz@gmail.com", "123", String.valueOf
                            (new Date()) + "xyz@gmail.com", new ArrayList<>()));
        Long now = new Date().getTime();
        assertNotEquals(userRepository.findUsersByUserId(1L).getPrivateToken(),
                now + "xyz@gmail.com");
    }

    @Test
    void checkUserLogin() throws NoSuchAlgorithmException {
        when(userRepository.findUsersByUserId(1L)).thenReturn(
                new Users(1L, "xyz@gmail.com", new Encrypt().getSHA("123"), String.valueOf
                        (new Date()) + "xyz@gmail.com", new ArrayList<>()));

        Users usrr = new Users(null, "xyz@gmail.com", "123", null, new ArrayList<>());
        Assertions.assertEquals(new Encrypt().getSHA(usrr.getPassword()), userRepository.findUsersByUserId(1L).getPassword());

    }

    @Test
    void getAllServices(){
        when(servicesRepository.findAll()).thenReturn(
                Stream.of(new Services(1L, "google", "google.com"),
                        new Services(2L, "wiki", "wikipedia.com")).collect(Collectors.toList()));
        Assertions.assertEquals(2, servicesRepository.findAll().size());
    }

    @Test
    void updateService(){
        when(servicesRepository.getById(1L)).thenReturn(new Services(1L, "google", "google.com"));
        Services first = servicesRepository.getById(1L);
        first.setServiceName("new Google");
        new ServicesService(servicesRepository, userRepository).updateService(first, 1L);
        Services new2 = servicesRepository.getById(1L);
        Assertions.assertTrue(first.getServiceName().equals(new2.getServiceName()));
    }

    @Test
    void createService() throws NoSuchAlgorithmException {
        when(userRepository.findUsersByUserId(1L)).thenReturn(
                new Users(1L, "xyz@gmail.com", new Encrypt().getSHA("123"), String.valueOf
                        (new Date()) + "xyz@gmail.com", new ArrayList<>()));
        new ServicesService(servicesRepository, userRepository).registerService(new Services(1L, "gg", "google.com"), 1L);
        Services ss = userRepository.findUsersByUserId(1L).getServices().get(0);
        Assertions.assertTrue(ss.getServiceName().equals("gg"));
    }

    @Test
    void deleteService(){
        when(servicesRepository.getById(1L)).thenReturn(new Services(1L, "google", "google.com"));
        Services s = servicesRepository.getById(1L);

        Assertions.assertEquals(true, new ServicesService(servicesRepository, userRepository).deleteService(s));
    }

}

package com.example.kry_poller;

import com.example.kry_poller.Models.Users;
import com.example.kry_poller.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;

@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
@EnableScheduling
public class KryPollerApplication implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(KryPollerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Users users = new Users(1L, "dmuturia2922@gmail.com", new Encrypt().getSHA("hello"), null, new ArrayList<>());
        users.setPrivateToken(new ValidationClass().createToken(users));
        userRepository.save(users);
    }
}

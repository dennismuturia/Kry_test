package com.example.kry_poller.Configs;

import com.example.kry_poller.Encrypt;
import com.example.kry_poller.ValidationClass;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CustomValidation {
    @Bean
    public ValidationClass validateToken(){
        return new ValidationClass();
    }

    @Bean
    public Encrypt encrypt(){return  new Encrypt();}
}

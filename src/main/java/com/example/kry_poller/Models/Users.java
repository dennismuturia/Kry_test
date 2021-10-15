package com.example.kry_poller.Models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String email;
    private String password;
    @OneToMany
    private List<Services> services;
    @Column(columnDefinition="TEXT")
    private String privateToken;

    public Users(Long userId, String email, String password, String privateToken, List<Services>servicesList){
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.privateToken = privateToken;
        this.services = servicesList;
    }

    public  Users(){}
}

package com.example.kry_poller.Models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long serviceId;
    private String serviceName;
    private String url;
    private int servicePort;
    private String isActive;

    public Services(Long serviceId, String serviceName, String url){
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.url = url;
    }
    public Services(){}

}

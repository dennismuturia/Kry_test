package com.example.kry_poller.Models;

import lombok.Data;

@Data
public class ServiceResponse {
    private Long serviceId;
    private String serviceName;
    private String serviceIP;
    private int servicePort;
    private String isActive;
    public ServiceResponse(Long serviceId, String serviceName, String serviceIp, int servicePort, String isActive){
        this.isActive = isActive;
        this.serviceId = serviceId;
        this.serviceIP = serviceIp;
        this.serviceName = serviceName;
        this.servicePort = servicePort;
    }
}

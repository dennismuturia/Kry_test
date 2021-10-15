package com.example.kry_poller;

import com.example.kry_poller.Models.Services;
import com.example.kry_poller.Repositories.ServicesRepository;
import com.example.kry_poller.Services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
public class PingService implements ServiceObject{
    private ServicesRepository servicesRepository;

    static Logger log = LoggerFactory.getLogger(PingService.class);
    List<Services> servicesList;
    public PingService(List<Services>servicesList, ServicesRepository servicesRepository){
        this.servicesList = servicesList;
        this.servicesRepository = servicesRepository;
    }

    public boolean checkPortExists(Services s) {

        return false;
    }

    @Override
    public void sendHttpPing() {
        servicesList.forEach(services -> {
            HttpURLConnection connection = null;
            String targetUrl = "http://"+(checkPortExists(services) ? services.getUrl()+":"+ services.getServicePort() : services.getUrl());
            try {
                java.net.URL url = new java.net.URL(targetUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                int code = connection.getResponseCode();
                if (code==200)
                {
                    services.setIsActive("OK");
                    updateServices(services);
                }else{
                    services.setIsActive("FALSE");
                    updateServices(services);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    void updateServices(Services services){
        servicesRepository.save(services);
    }
}

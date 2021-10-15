package com.example.kry_poller.Configs;

import com.example.kry_poller.PingService;
import com.example.kry_poller.Repositories.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTasks {
    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private PingService pingService;
    @Scheduled(fixedDelay = 1000, initialDelay = 4000)
    public void scheduleTask() {
        pingService = new PingService(servicesRepository.findAll(), servicesRepository);
        pingService.sendHttpPing();
        //System.out.println("Updating the status of servers " + System.currentTimeMillis() / 1000);
    }
}

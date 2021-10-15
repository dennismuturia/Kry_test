package com.example.kry_poller.Services;

import com.example.kry_poller.Models.ServiceResponse;
import com.example.kry_poller.Models.Services;
import com.example.kry_poller.Models.Users;
import com.example.kry_poller.Repositories.ServicesRepository;
import com.example.kry_poller.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesService {
    private ServicesRepository repository;

    private UserRepository userRepository;

    public ServicesService(ServicesRepository repository, UserRepository userRepository){
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public List<Services>getAllServices(Long userId){
        return userRepository.findServicesByUser(userId);
    }

    public ServiceResponse registerService(Services services, Long userId){
        if(repository.findServicesByServiceName(services.getServiceName()).isEmpty()){
            Users users = userRepository.findUsersByUserId(userId);

            List<Services> allServices = users.getServices();
            allServices.add(services);
            users.setServices(allServices);
            repository.save(services);
            userRepository.save(users);
            return new ServiceResponse(services.getServiceId(), services.getServiceName(), services.getUrl(),services.getServicePort(), services.getIsActive() == null? "FALSE":"OK");
        }else{
            return new ServiceResponse(null, services.getServiceName(), services.getUrl(),services.getServicePort(), "FALSE");
        }
    }
    public ServiceResponse updateService(Services services, Long userId){
        Services services1 = repository.getById(services.getServiceId());
        services1.setServiceName(services.getServiceName());
        services1.setServicePort(services.getServicePort());
        services1.setUrl(services.getUrl());
        repository.save(services1);

        return new ServiceResponse(services1.getServiceId(), services1.getServiceName(), services1.getUrl(), services.getServicePort(), services1.getIsActive());
    }

    public boolean deleteService(Services services){
        Services services1 = repository.getById(services.getServiceId());
        repository.delete(services1);
        return !repository.findServicesByServiceName(services.getServiceName()).isPresent();
    }
}

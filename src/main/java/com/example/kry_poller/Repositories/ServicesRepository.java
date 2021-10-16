package com.example.kry_poller.Repositories;

import com.example.kry_poller.Models.Services;
import com.example.kry_poller.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ServicesRepository extends JpaRepository<Services, Long> {
    Optional<Services> findServicesByServiceName(String serviceName);

    @Query(value = "delete from users_services WHERE  services_service_id=:id", nativeQuery = true)
    void deleteRelationship(@Param("id")Long service_id);
}

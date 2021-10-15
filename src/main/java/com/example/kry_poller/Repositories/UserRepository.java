package com.example.kry_poller.Repositories;

import com.example.kry_poller.Models.Services;
import com.example.kry_poller.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findUsersByEmail(String email);

    Optional<Users> findByEmail(String email);

    Users  findUsersByPrivateToken(String privateToken);
    Users findUsersByUserId(Long userId);

    @Query("SELECT e.services  from Users e where e.userId=:userId")
    List<Services>findServicesByUser(@Param("userId")Long userId);
}

package com.springsecurity.authorizationserverwithdb.repositories;

import com.springsecurity.authorizationserverwithdb.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findClientByClientId(String clientId);
}

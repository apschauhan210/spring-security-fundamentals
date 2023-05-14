package com.springsecurity.authorizationserverwithdb.repositories;

import com.springsecurity.authorizationserverwithdb.entities.GrantType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GrantTypeRepository extends JpaRepository<GrantType, Integer> {

    Optional<GrantType> findGrantTypeByName(String name);
}

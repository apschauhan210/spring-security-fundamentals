package com.springsecurity.authorizationserverwithdb.repositories;

import com.springsecurity.authorizationserverwithdb.entities.Scope;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScopeRepository extends JpaRepository<Scope, Integer> {

    Optional<Scope> findScopeByName(String name);
}

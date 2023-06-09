package com.springsecurity.userdetailsserviceimplementation.repositories;

import com.springsecurity.userdetailsserviceimplementation.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByUserName(String name);
}

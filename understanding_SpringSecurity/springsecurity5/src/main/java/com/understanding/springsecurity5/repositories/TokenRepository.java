package com.understanding.springsecurity5.repositories;

import com.understanding.springsecurity5.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    Optional<Token> findTokenByToken(String token);

    Optional<Token> findTokenByUsername(String username);

}

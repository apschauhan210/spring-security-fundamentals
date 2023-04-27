package com.understanding.springsecurity5.services;

import com.understanding.springsecurity5.entities.Token;
import com.understanding.springsecurity5.repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class TokenService {

    @Autowired
    private TokenRepository repository;

    public Token getToken(String token){
        return repository.findTokenByToken(token).orElseThrow(()->new BadCredentialsException("Token does not exists"));
    }

    @Transactional
    public void updateToken(String username, String userToken){
        Optional<Token> token = repository.findTokenByUsername(username);
        if(token.isPresent()){
            token.get().setToken(userToken);
        } else {
            Token token1 = new Token(username, userToken);
            repository.save(token1);
        }
    }
}

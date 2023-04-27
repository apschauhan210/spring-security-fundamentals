package com.understanding.springsecurity5.security.providers;

import com.understanding.springsecurity5.entities.Token;
import com.understanding.springsecurity5.security.authentications.TokenAuthentication;
import com.understanding.springsecurity5.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private TokenService tokenService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getName();
        Token token1 = tokenService.getToken(token); //If the token is not correct getToken() will throw an exception.
        Authentication tokenAuthentication = new TokenAuthentication(token1.getUsername(), token, List.of(()->"read"));
        return tokenAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}

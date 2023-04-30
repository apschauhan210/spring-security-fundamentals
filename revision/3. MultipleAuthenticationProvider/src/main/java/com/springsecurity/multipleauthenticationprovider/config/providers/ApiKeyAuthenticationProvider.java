package com.springsecurity.multipleauthenticationprovider.config.providers;

import com.springsecurity.multipleauthenticationprovider.config.authentications.ApiKeyAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class ApiKeyAuthenticationProvider implements AuthenticationProvider {

    @Value("${secret.key}")
    private String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ApiKeyAuthentication customAuthentication = (ApiKeyAuthentication) authentication;
        String requestKey = customAuthentication.getKey();

        if(requestKey != null && customAuthentication.getKey().equals(key)) {
            customAuthentication.setAuthenticated(true);
            customAuthentication.setKey(null);
            return customAuthentication;
        }

        throw new BadCredentialsException("Invalid Key");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(ApiKeyAuthentication.class);
    }
}

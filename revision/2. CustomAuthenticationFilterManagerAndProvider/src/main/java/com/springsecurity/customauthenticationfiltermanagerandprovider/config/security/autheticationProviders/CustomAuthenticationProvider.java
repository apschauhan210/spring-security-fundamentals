package com.springsecurity.customauthenticationfiltermanagerandprovider.config.security.autheticationProviders;

import com.springsecurity.customauthenticationfiltermanagerandprovider.config.security.authentications.CustomAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Value("${secret.key}")
    private String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomAuthentication ca = (CustomAuthentication) authentication;
        String requestKey = ca.getKey();

        if(requestKey != null && requestKey.equals(key)) {
            return new CustomAuthentication(null, true);
        }

        throw new BadCredentialsException("Wrong Key!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthentication.class.equals(authentication);
    }
}

package com.springsecurity.customauthenticationfiltermanagerandprovider.config.security.filters;

import com.springsecurity.customauthenticationfiltermanagerandprovider.config.security.authentications.CustomAuthentication;
import com.springsecurity.customauthenticationfiltermanagerandprovider.config.security.autheticationManagers.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final CustomAuthenticationManager manager;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 1. create an authentication object which is not yet authenticated
        // 2. delegate the authentication object to the manager
        // 3. get back the authentication from the manager
        // 4. if the object is authenticated then send request to the next filter in the chain

        String key = request.getHeader("key");

        CustomAuthentication authentication = new CustomAuthentication(key, false);

        try {
            var newAuthentication = manager.authenticate(authentication);

            if(newAuthentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(newAuthentication);
                filterChain.doFilter(request, response);
            }
        } catch (AuthenticationException e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }
}

package com.springsecurity.multipleauthenticationprovider.config.filters;

import com.springsecurity.multipleauthenticationprovider.config.authentications.ApiKeyAuthentication;
import com.springsecurity.multipleauthenticationprovider.config.managers.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
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

        String headerKey = request.getHeader("key");

        if(headerKey == null || "null".equals(headerKey)) {
            filterChain.doFilter(request, response);
        }

        ApiKeyAuthentication authentication = new ApiKeyAuthentication(headerKey, false);

        try {
            Authentication newAuthentication =  manager.authenticate(authentication);
            if(newAuthentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(newAuthentication);
                filterChain.doFilter(request, response);
            }
        } catch (AuthenticationException e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
}

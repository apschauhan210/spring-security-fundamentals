package com.understanding.springsecurity4.security.filters;

import com.understanding.springsecurity4.security.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {   //Instead of extending OncePerRequestFilter we can also implement Filter interface by javax.servlet. In that case we need to implement the doFilter method which takes ServletRequest and ServletResponse as the parameter. So we need to cast them to HttpServletRequest and HttpServletResponse in order to use getHeaders method.

    @Autowired
    private AuthenticationManager manager;

    @Override
    public void doFilterInternal(HttpServletRequest request,
                         HttpServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String authorization = request.getHeader("Authorization");

        Authentication authentication = new CustomAuthentication(authorization, null);

        try {
            Authentication result = manager.authenticate(authentication);

            if (result.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(result);
                chain.doFilter(request,response);
            }
            else
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        } catch (AuthenticationException exception){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

    }
}

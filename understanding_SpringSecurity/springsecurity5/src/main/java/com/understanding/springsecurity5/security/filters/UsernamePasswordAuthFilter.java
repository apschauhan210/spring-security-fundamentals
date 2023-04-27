package com.understanding.springsecurity5.security.filters;

import com.understanding.springsecurity5.entities.Otp;
import com.understanding.springsecurity5.repositories.OtpRepository;
import com.understanding.springsecurity5.security.authentications.UsernameOtpAuthentication;
import com.understanding.springsecurity5.security.authentications.UsernamePasswordAuthentication;
import com.understanding.springsecurity5.services.OtpService;
import com.understanding.springsecurity5.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private OtpService otpService;

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
            String username = request.getHeader("username");
            String password = request.getHeader("password");
            String otp = request.getHeader("otp");

            if (otp == null){
                Authentication authentication = new UsernamePasswordAuthentication(username, password);
                authentication = manager.authenticate(authentication);
                otpService.updateOtp(username);
            } else{
                Authentication authentication = new UsernameOtpAuthentication(username, otp);
                authentication = manager.authenticate(authentication);
                String token = UUID.randomUUID().toString();
                tokenService.updateToken(authentication.getName(), token);
                response.setHeader("Authorization", token);
            }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }

}

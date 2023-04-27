package com.understanding.springsecurity5.security.providers;

import com.understanding.springsecurity5.entities.Otp;
import com.understanding.springsecurity5.repositories.OtpRepository;
import com.understanding.springsecurity5.security.authentications.UsernameOtpAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OtpAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private OtpRepository otpRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String otp = (String) authentication.getCredentials();

        Otp otp1 = otpRepository.findOtpByUsername(username).orElseThrow(()-> new BadCredentialsException("User does not exist!"));
        if(otp.equals(otp1.getOtp())){
            return new UsernameOtpAuthentication(username, otp, List.of(()->"read"));
        }

        throw new BadCredentialsException("Incorrect Otp");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernameOtpAuthentication.class.equals(authentication);
    }
}

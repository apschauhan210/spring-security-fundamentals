package com.understanding.springsecurity5.services;

import com.understanding.springsecurity5.entities.Otp;
import com.understanding.springsecurity5.repositories.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class OtpService {

    @Autowired
    private OtpRepository repository;

    @Transactional
    public void updateOtp(String username){
        Optional<Otp> otpByUsername = repository.findOtpByUsername(username);
        String code = String.valueOf(1000 + ((int)(Math.random()*(9999 - 1000 + 1))));
        if(otpByUsername.isPresent()){
            otpByUsername.get().setOtp(code);
        } else {
            Otp otpEntity = new Otp(username, code);
            repository.save(otpEntity);
        }
    }

}

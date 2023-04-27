package com.understanding.springSecurity1.config;

import com.understanding.springSecurity1.entities.User;
import com.understanding.springSecurity1.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ApplicationConfig {

    @Bean
    CommandLineRunner userCommandLineRunner(UserRepository repository){
        return args -> {
            User anuj = new User("aps", "apssexy");
            User ankita = new User("ankita", "anki");
            User sudha = new User("sudha", "sudha");
            repository.saveAll(List.of(anuj, ankita, sudha));
        };
    }
}

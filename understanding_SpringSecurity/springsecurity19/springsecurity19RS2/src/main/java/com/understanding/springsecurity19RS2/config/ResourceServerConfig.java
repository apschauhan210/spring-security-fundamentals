package com.understanding.springsecurity19RS2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                        .mvcMatchers("/hello/**").hasAuthority("SCOPE_read")
                        .anyRequest().authenticated();

        http.oauth2ResourceServer().jwt();

        return http.build();
    }
}

package com.springsecurity.methodauthorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity // @EnableGlobalMethodSecurity is deprecated. In this new @EnableMethodSecurity prePostEnabled is true by default
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic()
                .and()
                .authorizeHttpRequests().anyRequest().authenticated()
                .and()
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var u1 = User.withUsername("sam")
                .password(passwordEncoder().encode("sam"))
                .authorities("read")
                .build();
        var u2 = User.withUsername("emily")
                .password(passwordEncoder().encode("emily"))
                .authorities("write")
                .build();
        var u3 = User.withUsername("rita")
                .password(passwordEncoder().encode("rita"))
                .authorities("read", "write")
                .build();

        var uds = new InMemoryUserDetailsManager(u1, u2, u3);

        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

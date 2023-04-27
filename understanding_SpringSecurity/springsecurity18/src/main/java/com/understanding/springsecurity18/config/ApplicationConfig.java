package com.understanding.springsecurity18.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ApplicationConfig {

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails anuj = User.builder()
                .username("anuj")
                .password("anuj@123")
//                .authorities("ROLE_MANAGER","read") // authorities("ROLE_MANAGER") => roles("MANAGER")
                .roles("MANAGER")
                .build();
        UserDetails ankita = User.builder()
                .username("ankita")
                .password("ankita@123")
//                .authorities("write", "read")
                .roles("ADMIN")
                .build();
        UserDetails sudha = User.builder()
                .username("sudha")
                .password("sudha@123")
                .authorities("delete")
                .build();

        return new InMemoryUserDetailsManager(anuj, ankita, sudha);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic();

        http.csrf().disable();

        http.authorizeRequests()          // Requests are matched in the order in which they are specified
//                .mvcMatchers(HttpMethod.GET,"/hello").hasAuthority("write")
//                .mvcMatchers("/ciao").hasAuthority("read")
                .mvcMatchers(HttpMethod.GET, "/hello").hasRole("MANAGER")
                .mvcMatchers(HttpMethod.POST, "/hola").hasRole("ADMIN")
                .anyRequest().authenticated();

        return http.build();
    }
}

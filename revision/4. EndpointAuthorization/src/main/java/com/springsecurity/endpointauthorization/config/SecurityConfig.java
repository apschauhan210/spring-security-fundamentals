package com.springsecurity.endpointauthorization.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.httpBasic()
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/demo/**").hasAuthority("read")
                .requestMatchers("/test/**").hasAuthority("write")
                .anyRequest().authenticated()
                .and().csrf().disable()
                .build();

        // matcher method + authorization rule
        // 1. which matcher methods should you use and how  ( anyRequest(), old(mvcMatchers(), antMatchers(), regexMatchers()) )
        // 2. how to apply different authorization rules (hasAuthority(), hasRole(), authenticated(), permitAll(), denyAll()
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var uds = new InMemoryUserDetailsManager();

        UserDetails anuj = User.withUsername("anuj")
                        .password(passwordEncoder().encode("anuj"))
                        .authorities("read")
                        .build();
        UserDetails sam = User.withUsername("ankita")
                            .password(passwordEncoder().encode("ankita"))
                            .authorities("write")
                            .build();
        UserDetails admin = User.withUsername("admin")
                                .password(passwordEncoder().encode("admin"))
                                .authorities("read", "write")
                                .build();

        uds.createUser(anuj);
        uds.createUser(sam);
        uds.createUser(admin);

        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}

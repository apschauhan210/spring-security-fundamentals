package com.springsecurity.resourceserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final CorsCustomizer corsCustomizer;

    public SecurityConfig(CorsCustomizer corsCustomizer) {
        this.corsCustomizer = corsCustomizer;
    }

    @Value("${jwks.uri}")
    private String jwksUri;

    @Bean
    public SecurityFilterChain resourceServerSecurityFilterChain(HttpSecurity http) throws Exception {
        http.oauth2ResourceServer(
                r -> r.jwt().jwkSetUri(jwksUri)
                        .jwtAuthenticationConverter(new CustomJwtAuthenticationTokenConverter())
        );

        http.authorizeHttpRequests().anyRequest().authenticated();

        corsCustomizer.corsCustomizer(http);

        return http.build();
    }
}

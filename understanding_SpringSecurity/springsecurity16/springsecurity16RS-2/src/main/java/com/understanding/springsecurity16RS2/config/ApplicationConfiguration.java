package com.understanding.springsecurity16RS2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Configuration
public class ApplicationConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${key}")
    private String keyValue;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();

        http.oauth2ResourceServer(
                c -> c.jwt(
                        jwtConfigurer -> {
                            jwtConfigurer.decoder(decoder());
                        }
                )
        );

    }

    @Bean
    public JwtDecoder decoder() {
        SecretKey key =
                new SecretKeySpec(keyValue.getBytes(), 0, keyValue.getBytes().length, "AES");

        return NimbusJwtDecoder.withSecretKey(key).build();
    }
}

package com.understanding.springsecurity16RS1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();

        http.oauth2ResourceServer(
                c -> c.opaqueToken(
                        t -> {
                            t.introspectionUri("http://localhost:8080/oauth/check_token");
                            t.introspectionClientCredentials("resourceserver", "resource123");
                        }
                )
        );
    }
}

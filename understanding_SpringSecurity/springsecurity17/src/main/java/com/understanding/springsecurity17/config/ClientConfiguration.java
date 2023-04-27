package com.understanding.springsecurity17.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

@Configuration
public class ClientConfiguration {

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(){
        return new InMemoryClientRegistrationRepository(gitHubClient());
    }

    private ClientRegistration gitHubClient(){
        return CommonOAuth2Provider.GITHUB.getBuilder("github")
                .clientId("547520d9d35699b92279")
                .clientSecret("3e0129831503644a6999c3994a9224c2f681c42f")
                .build();
    }
}

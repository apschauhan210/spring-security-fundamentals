package com.understanding.springsecurity12.security;

import com.understanding.springsecurity12.entities.Client;
import com.understanding.springsecurity12.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaClientDetailsService implements ClientDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Client client = clientRepository.findClientByClientId(clientId).orElseThrow(()-> new ClientRegistrationException("Client not found"));
        return new SecurityClient(client);
    }
}

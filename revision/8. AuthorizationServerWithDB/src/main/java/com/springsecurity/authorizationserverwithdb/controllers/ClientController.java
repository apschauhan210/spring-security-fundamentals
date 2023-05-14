package com.springsecurity.authorizationserverwithdb.controllers;

import com.springsecurity.authorizationserverwithdb.dtos.ClientDto;
import com.springsecurity.authorizationserverwithdb.entities.Client;
import com.springsecurity.authorizationserverwithdb.security.SecurityClient;
import com.springsecurity.authorizationserverwithdb.services.JpaRegisteredClientRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientController {

    private final Logger logger = LoggerFactory.getLogger(ClientController.class);

    private final JpaRegisteredClientRepository registeredClientRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity registerClient(@RequestBody Client client) {
        client.setSecret(passwordEncoder.encode(client.getSecret()));
        SecurityClient securityClient = new SecurityClient(client);
        try {
            registeredClientRepository.save(securityClient);
            return new ResponseEntity(HttpStatus.OK);
        } catch (IllegalStateException e) {
            logger.warn(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("get")
    public ResponseEntity<ClientDto> getClient(@RequestParam String clientId) {
        try {
            SecurityClient registeredClient = (SecurityClient) registeredClientRepository.findByClientId(clientId);
            Client client = registeredClient.getClient();
            ClientDto clientDto = new ClientDto(client);
            return new ResponseEntity<>(clientDto, HttpStatus.OK);
        } catch (IllegalStateException e) {
            logger.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

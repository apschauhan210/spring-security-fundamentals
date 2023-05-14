package com.springsecurity.authorizationserverwithdb.security;

import com.springsecurity.authorizationserverwithdb.entities.Client;
import com.springsecurity.authorizationserverwithdb.entities.Scope;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
public class SecurityClient extends RegisteredClient {

    private final Client client;

    @Override
    public String getId() {
        return String.valueOf(client.getId());
    }

    @Override
    public String getClientId() {
        return client.getClientId();
    }

    @Override
    public String getClientSecret() {
        return client.getSecret();
    }

    @Override
    public Set<ClientAuthenticationMethod> getClientAuthenticationMethods() {
        return new HashSet<>(List.of(new ClientAuthenticationMethod(client.getAuthenticationMethod())));
    }

    @Override
    public Set<AuthorizationGrantType> getAuthorizationGrantTypes() {
        return client.getGrantTypes()
                .stream()
                .map((e) -> new AuthorizationGrantType(e.getName()))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getRedirectUris() {
        return client.getRedirectUris();
    }

    @Override
    public Set<String> getScopes() {
        return client.getScopes()
                .stream()
                .map(Scope::getName)
                .collect(Collectors.toSet());
    }
}

package com.springsecurity.authorizationserverwithdb.dtos;

import com.springsecurity.authorizationserverwithdb.entities.Client;
import com.springsecurity.authorizationserverwithdb.entities.GrantType;
import com.springsecurity.authorizationserverwithdb.entities.Scope;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@ToString
@Getter
@Setter
public class ClientDto {

    private int id;
    private String clientId;
    private String authenticationMethod;
    private Set<String> scopes;
    private Set<String> redirectUris;
    private Set<String> grantTypes;

    public ClientDto(Client client) {
        this.id = client.getId();
        this.clientId = client.getClientId();
        this.authenticationMethod = client.getAuthenticationMethod();

        this.redirectUris = new HashSet<>();
        for(String uri: client.getRedirectUris()) {
            this.redirectUris.add(uri);
        }

        this.scopes = new HashSet<>();
        for(Scope scope: client.getScopes()) {
            this.scopes.add(scope.getName());
        }

        this.grantTypes = new HashSet<>();
        for(GrantType grantType: client.getGrantTypes()) {
            this.grantTypes.add(grantType.getName());
        }
    }
}

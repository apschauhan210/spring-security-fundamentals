package com.springsecurity.authorizationserverwithdb.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @SequenceGenerator(name = "client_id_generator", sequenceName = "client_id_generator", allocationSize = 1)
    @GeneratedValue(generator = "client_id_generator")
    private int id;

    @Column(unique = true)
    private String clientId;

    private String secret;

    @ElementCollection
    private Set<String> redirectUris;

    private String authenticationMethod;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    @JoinTable(
            name = "client_scopes",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "scope_id")
    )
    private Set<Scope> scopes;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    @JoinTable(
            name = "client_grant-types",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "grant_type_id")
    )
    private Set<GrantType> grantTypes;
}

package com.springsecurity.authorizationserverwithdb.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Scope {

    @Id
    @SequenceGenerator(name = "scope_id_generator", sequenceName = "scope_id_generator", allocationSize = 1)
    @GeneratedValue(generator = "scope_id_generator")
    private int id;

    private String name;

    @ManyToMany(mappedBy = "scopes")
    private Set<Client> clients;

    public Scope(String name) {
        this.name = name;
    }
}

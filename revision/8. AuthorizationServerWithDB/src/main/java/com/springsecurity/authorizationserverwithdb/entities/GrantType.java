package com.springsecurity.authorizationserverwithdb.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GrantType {

    @Id
    @SequenceGenerator(name = "gt_id_generator", sequenceName = "gt_id_generator", allocationSize = 1)
    @GeneratedValue(generator = "gt_id_generator")
    private int id;

    private String name;

    @ManyToMany(mappedBy = "grantTypes")
    private Set<Client> clients;

    public GrantType(String name) {
        this.name = name;
    }
}

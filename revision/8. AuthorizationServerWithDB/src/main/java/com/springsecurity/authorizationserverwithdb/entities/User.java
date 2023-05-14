package com.springsecurity.authorizationserverwithdb.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Cascade;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_generator", allocationSize = 1)
    @GeneratedValue(generator = "user_id_generator")
    private int id;

    @Column(unique = true)
    private String username;

    private String password;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "user_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = {
//                    @JoinColumn(name = "authority_id", referencedColumnName = "id"),
//                    @JoinColumn(name = "authority_name", referencedColumnName = "name")
//            }
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Set<Authority> authorities;

}

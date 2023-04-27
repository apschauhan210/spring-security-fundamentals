package com.springsecurity.userdetailsserviceimplementation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

    @Id
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_generator", allocationSize = 1)
    @GeneratedValue(generator = "user_id_generator")
    private int id;

    private String userName;

    private String password;
}

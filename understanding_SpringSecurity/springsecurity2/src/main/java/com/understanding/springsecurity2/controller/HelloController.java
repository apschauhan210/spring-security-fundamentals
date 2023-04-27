package com.understanding.springsecurity2.controller;

import com.understanding.springsecurity2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;

    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @PostMapping("/user")
    public String addUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        jdbcUserDetailsManager.createUser(user);

        return "Registered Successfully";
    }
}

package com.springsecurity.authorizationserverwithdb.controllers;

import com.springsecurity.authorizationserverwithdb.entities.User;
import com.springsecurity.authorizationserverwithdb.security.SecurityUser;
import com.springsecurity.authorizationserverwithdb.services.JpaUserDetailsManager;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final JpaUserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        SecurityUser newUser = new SecurityUser(user);
        try {
            userDetailsManager.createUser(newUser);
            return new ResponseEntity(HttpStatus.OK);
        } catch (IllegalStateException e) {
//            e.printStackTrace();
            logger.warn(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/change_password")
    public ResponseEntity changePassword(@RequestParam String oldPassword, @RequestParam String newPassword, Authentication authentication) {
        userDetailsManager.changePassword(oldPassword, newPassword);
        return new ResponseEntity("Password updated successfully!", HttpStatus.OK);
    }
}

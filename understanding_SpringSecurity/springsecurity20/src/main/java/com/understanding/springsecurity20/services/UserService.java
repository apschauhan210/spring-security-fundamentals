package com.understanding.springsecurity20.services;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    //    @PreAuthorize("hasAuthority('write')")
    @PreAuthorize("#username == authentication.getName()")
    public List<String> findProductsForUser(String username) {
        return List.of("Peanuts", "Almonds");
    }

    @PreAuthorize("hasAuthority('read')")
    public String test1(Authentication authentication) {
        System.out.println("TEST1 called by " + authentication.getName());
        return "TEST1";
    }

    @PostAuthorize("hasAuthority('read')")
    public String test2(Authentication authentication) {
        System.out.println("TEST2 called by " + authentication.getName());
        return "TEST2";
    }

    @PostAuthorize("returnObject == authentication.name")
    // Apply it only when the object to be returned can be obtained only after executing the method
    public String test3() {
        System.out.println("TEST3 called ");

        // Eg. do some procedure and return object only if authorization rule holds.
        // Don't do any mutating operations.

        return "ankita";
    }

    @PreFilter("filterObject == authentication.name") // works only when the parameter, returned object is a collection or an array
    public List<String> test4(List<String> list) {
        System.out.println(list);
        return list;
    }

    @PostFilter("filterObject == authentication.name") // works only when the returned object is a collection or an array
    public List<String> test5(List<String> list) {
        System.out.println(list);
        return list;
    }

}

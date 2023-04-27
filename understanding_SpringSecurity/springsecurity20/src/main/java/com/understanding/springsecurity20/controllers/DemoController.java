package com.understanding.springsecurity20.controllers;

import com.understanding.springsecurity20.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DemoController {

    private final UserService userService;

    public DemoController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/find/{username}")
    public List<String> findProductForUser(@PathVariable String username) {
        return userService.findProductsForUser(username);
    }

    @GetMapping("/test1")
    public String test1(Authentication authentication) {
        return userService.test1(authentication);
    }

    @GetMapping("/test2")
    public String test2(Authentication authentication) {
        return userService.test2(authentication);
    }

    @GetMapping("/test3")
    public String test3() {
        return userService.test3();
    }

    @GetMapping("/test4")
    public List<String> test4(){
        List<String> list = new ArrayList<>();
        list.add("anuj");
        list.add("ankita");
        list.add("sudha");
        return userService.test4(list);
    }

    @GetMapping("/test5")
    public List<String> test5(){
        List<String> list = new ArrayList<>();
        list.add("anuj");
        list.add("ankita");
        list.add("sudha");
        return userService.test5(list);
    }
}

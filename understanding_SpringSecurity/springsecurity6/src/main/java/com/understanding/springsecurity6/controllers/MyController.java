package com.understanding.springsecurity6.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MyController {

    @GetMapping
    public String main(){
        return "main.html";
    }

    @PostMapping("/change")
    public String change(){
        System.out.println(";)");
        return "main.html";
    }
}

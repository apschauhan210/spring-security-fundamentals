package com.understanding.springSecurity1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/springsecurity1")
public class ApplicationController {

    @GetMapping("hello")
    public String getHello(){
        return "Hello";
    }
}

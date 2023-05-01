package com.springsecurity.endpointauthorization.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/t1")
    public String demo1() {
        return "Test1";
    }

    @GetMapping("/t2")
    public String demo2() {
        return "Test2";
    }
}

package com.springsecurity.endpointauthorization.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/d1")
    public String demo1() {
        return "Demo1";
    }

    @GetMapping("/d2")
    public String demo2() {
        return "Demo2";
    }

    @PostMapping("/d3")
    public String demo3() {
        return "Demo3";
    }
}

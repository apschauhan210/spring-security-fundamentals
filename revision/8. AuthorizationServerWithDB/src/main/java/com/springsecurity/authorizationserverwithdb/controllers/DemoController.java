package com.springsecurity.authorizationserverwithdb.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class DemoController {

    @GetMapping
    public String demo() {
        return "demo.html";
    }

    @ResponseBody
    @GetMapping("/secured")
    public String securedEndPoint() {
        return "Secured Demo";
    }
}

package com.springsecurity.methodauthorization.controllers;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DemoController {

    @GetMapping("/demo1")
    @PreAuthorize("hasAuthority('read')")
    public String demo1() {
        return "Demo1";
    }

    @GetMapping("/demo2")
    @PostAuthorize("hasAnyAuthority('read', 'write')")
    public String demo2() {
        return "Demo2";
    }

    @GetMapping("/demo3/{name}")
    @PreAuthorize("#name == authentication.name and hasAuthority('write')") // can access the path variable through # symbol and can also access the authentication instance
    public String demo3(@PathVariable("name") String name) {
        return "Demo3";
    }

    @GetMapping("/demo4")
    @PreAuthorize("@controllerConditionEvaluator.evaluateCondition()")  // can use any function to evaluate using SPEL(spring expression language): Used in case of complex condition
    public String demo4() {
        return "Demo4";
    }

    // @PostAuthorize

    @GetMapping("/demo5")
    @PostAuthorize("returnObject != 'Demo 5'")  // is mainly used when we want to restrict the access to some returned value
    public String demo5() {
        System.out.println(":)");  // never use @PostAuthorize with methods that change data
        return "Demo 5";
    }

    // @PreFilter  => works with either array or Collection

    @GetMapping("/demo6")
    @PreFilter("filterObject.contains('a')")
    public String demo6(@RequestBody List<String> values) {
        System.out.println("Values: " + values);
        return "Demo 6";
    }

    // @PostFilter  => the returned type must be either a Collection or an array

    @GetMapping("/demo7")
    @PostFilter("filterObject.contains('a')")
    public List<String> demo6() {
        var list = new ArrayList<String>();
        list.add("abcd");
        list.add("wert");
        list.add("qaaz");
        list.add("wrty");

        // List.of(...) // List.of creates and immutable collection
        return list;
    }
}

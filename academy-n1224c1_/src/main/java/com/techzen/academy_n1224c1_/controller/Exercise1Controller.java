package com.techzen.academy_n1224c1_.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Exercise1Controller {
    @GetMapping("/greeting")
    public String greeting(@RequestParam(defaultValue = "") String name) {
        return "Hello "+name+"!!!";
    }
}

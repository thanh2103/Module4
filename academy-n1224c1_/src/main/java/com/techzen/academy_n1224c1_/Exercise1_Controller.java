package com.techzen.academy_n1224c1_;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Exercise1_Controller {
    @GetMapping("/greeting")
    public String greeting(@RequestParam(defaultValue = "") String name) {
        return "Hello "+name+"!!!";
    }
}

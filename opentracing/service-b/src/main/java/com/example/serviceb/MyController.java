package com.example.serviceb;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello from service-b";
    }
}

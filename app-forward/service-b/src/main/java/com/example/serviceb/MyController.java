package com.example.servicea;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @RequestMapping("/hello")
    public String hello(@RequestHeader Map<String, String> headers) {
        headers.entrySet().stream().forEach( e -> {
            System.out.println("header: " + e.getKey() + " -> " + e.getValue());
        });
        return "hello from service-b";
    }
}

package com.example.servicea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        String serviceUrl = System.getenv("SERVICE_URL");
        if (serviceUrl == null) {
            serviceUrl = "http://localhost:8088/hello";
        }
        String response = restTemplate.getForObject(serviceUrl, String.class);
        return "hello " + name + " from service-a, " + response;
    }

}

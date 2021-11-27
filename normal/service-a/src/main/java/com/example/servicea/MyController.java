package com.example.servicea;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyController {

    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public MyController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

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

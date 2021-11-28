package com.example.servicea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RemoteService {

    @Autowired
    private RestTemplate restTemplate;
    
    public String callRemote() {
        String serviceUrl = System.getenv("SERVICE_URL");
        if (serviceUrl == null) {
            serviceUrl = "http://localhost:8088/hello";
        }
        return restTemplate.getForObject(serviceUrl, String.class);
    }
}

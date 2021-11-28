package com.example.servicea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private RemoteService remoteService;

    @Autowired
    private LocalService localService;

    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        localService.dummyFunction();
        String response = remoteService.callRemote();
        return "hello " + name + " from service-a, " + response;
    }

}

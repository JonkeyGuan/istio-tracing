package com.example.servicea;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyController {

    @Autowired
    private RestTemplate restTemplate;

    private final static String[] HEADERS_to_PROPAGATE = {
            "x-request-id",
            "x-ot-span-context",
            "x-datadog-trace-id", "x-datadog-parent-id", "x-datadog-sampling-priority",
            "traceparent", "tracestate",
            "x-cloud-trace-context",
            "grpc-trace-bin",
            "x-b3-traceid", "x-b3-spanid", "x-b3-parentspanid", "x-b3-sampled", "x-b3-flags",
            "end-user", "user-agent",
    };

    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable String name, @RequestHeader Map<String, String> headers) {
        String serviceUrl = System.getenv("SERVICE_URL");
        if (serviceUrl == null) {
            serviceUrl = "http://localhost:8088/hello";
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        Arrays.stream(HEADERS_to_PROPAGATE).forEach(header -> {
            String value = headers.get(header);
            if (value != null) {
                httpHeaders.set(header, value);
                System.out.println("header: " + header + " -> " + value);
            }
        });
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> responseEntity = restTemplate.exchange(serviceUrl, HttpMethod.GET, httpEntity,
                String.class);
        return "hello " + name + " from service-a, " + responseEntity.getBody();
    }

}

package com.example.servicea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.jaegertracing.Configuration;
import io.opentracing.Tracer;

@SpringBootApplication
public class Application {

	@Bean
	public Tracer jaegerTracer() {
		String jaegerServiceName = System.getenv("JAEGER_SERVICE_NAME");
		if (jaegerServiceName == null) {
			jaegerServiceName = "myself";
		}
		return Configuration.fromEnv(jaegerServiceName).getTracer();
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

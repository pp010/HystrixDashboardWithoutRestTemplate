package com.example.demo;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrix
@EnableHystrixDashboard
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	DemoHystrix demoHystrix;

	@Bean
	@LoadBalanced
	RestTemplate rest() {
		return new RestTemplate();
	}

	@GetMapping("/checkzuul")
	public String checkzuul() throws ClientProtocolException, IOException {
		return demoHystrix.checkzuul();
	}

	@GetMapping("/checkerror")
	public String checkerror() throws ClientProtocolException, IOException {
		return demoHystrix.checkerror();
	}
}

package com.example.demo;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class DemoHystrix {
	
	/*@Autowired
	RestTemplate restTemplate;*/
	
	@Autowired
	HttpCaller httpCaller;

	@HystrixCommand(fallbackMethod = "fallback")
	public String checkzuul() throws ClientProtocolException, IOException {
		//return restTemplate.getForObject("http://localhost:8080/checkzuul", String.class);
		return httpCaller.getCall("http://helloapp.u1.app.cloud.comcast.net/checkzuul");
	}
	
	@HystrixCommand(fallbackMethod = "fallback")
	public String checkerror() throws ClientProtocolException, IOException {
		//return restTemplate.getForObject("http://localhost:8080/checkerror", String.class);
		return httpCaller.getCall("http://localhost:8080/checkerror");
	}
	
	public String fallback() {
		return "fallback";
	}

}

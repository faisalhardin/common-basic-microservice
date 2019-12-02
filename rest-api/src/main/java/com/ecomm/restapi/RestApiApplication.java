package com.ecomm.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@EnableEurekaClient
@EnableSwagger2
@SpringBootApplication
@EnableCircuitBreaker
public class RestApiApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}

}

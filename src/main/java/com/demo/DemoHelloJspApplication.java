package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class DemoHelloJspApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoHelloJspApplication.class, args);
		
	}

}

package com.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
@MapperScan(basePackages = "com.demo.repository")
public class DemoHelloJspApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoHelloJspApplication.class, args);
		
	}

}

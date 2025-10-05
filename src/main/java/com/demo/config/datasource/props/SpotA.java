package com.demo.config.datasource.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix="a")
@Getter
@Setter
public class SpotA {
	private String url; 
	private String username;
	private String password;
}

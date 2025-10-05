package com.demo.config.datasource.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix="b")
@Getter
@Setter
public class SpotB {
	private String url; 
	private String username;
	private String password;
}

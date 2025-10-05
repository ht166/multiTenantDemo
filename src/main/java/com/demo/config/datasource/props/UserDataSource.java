package com.demo.config.datasource.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix="user")
@Getter
@Setter
public class UserDataSource {
	private String url; 
	private String username;
	private String password;
}

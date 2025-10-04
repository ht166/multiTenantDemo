package com.demo.config.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class DataSourceProperties {
	
	private Map<String, DataSourceProps> datasource = new HashMap<>();
	
	@PostConstruct
	public void print() {
		datasource.forEach((k, v) -> {
			System.out.println("TenantKey = " + k);
			System.out.println("-> url" + v.getUrl());
		});
	}

	@Getter
	@Setter
	public static class DataSourceProps {
		private String url;
		private String username;
		private String password;

	}

}

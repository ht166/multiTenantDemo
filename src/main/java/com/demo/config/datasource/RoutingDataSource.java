package com.demo.config.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		String tenant = DataSourceContextHolder.getDataSourceKey();
			if(tenant==null) {
				tenant = "user";
			}
			System.out.println("Lookup from context:" + tenant);
			
		return tenant;
	}
}
package com.demo.config.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.demo.config.datasource.props.SpotA;
import com.demo.config.datasource.props.SpotB;
import com.demo.config.datasource.props.UserDataSource;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableConfigurationProperties({
    SpotA.class,
    SpotB.class,
    UserDataSource.class
})
@PropertySource("classpath:/datasources.properties")
public class DataSourceConfig {
	
	/** ÉçÉKÅ[ */
	private static final Logger log = LoggerFactory.getLogger(DataSourceConfig.class);
	
	@Autowired
	private SpotA spotA;
	
	@Autowired
	private SpotB spotB;
	
	@Autowired
	private UserDataSource userDataSource;
	
    @Bean("A")
    HikariDataSource dataSourceA() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(spotA.getUrl());
        ds.setUsername(spotA.getUsername());
        ds.setPassword(spotA.getPassword());
        return ds;
    }

    @Bean("B")
    HikariDataSource dataSourceB() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(spotB.getUrl());
        ds.setUsername(spotB.getUsername());
        ds.setPassword(spotB.getPassword());
        return ds;
    }

    @Bean("user")
    HikariDataSource dataSourceUser() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(userDataSource.getUrl());
        ds.setUsername(userDataSource.getUsername());
        ds.setPassword(userDataSource.getPassword());
        
        return ds;
    }

    @Bean
    RoutingDataSource routingDataSource(
            @Qualifier("A") DataSource dsA,
            @Qualifier("B") DataSource dsB,
            @Qualifier("user") DataSource dsUser
    ) {
        RoutingDataSource routing = new RoutingDataSource();
        Map<Object, Object> targetMap = new HashMap<>();
        targetMap.put("A", dsA);
        targetMap.put("B", dsB);
        targetMap.put("user", dsUser);
        
        log.info("targetMap.keySet() : {}", targetMap.keySet());
        
        routing.setTargetDataSources(targetMap);
        routing.setDefaultTargetDataSource(dsUser);
        routing.afterPropertiesSet();
        return routing;
    }
}

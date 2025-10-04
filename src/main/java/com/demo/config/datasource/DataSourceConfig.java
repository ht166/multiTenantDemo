package com.demo.config.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration

public class DataSourceConfig {

    @Bean("A")
    HikariDataSource dataSourceA() {
        HikariDataSource ds = new HikariDataSource();
//        ds.setJdbcUrl("jdbc:postgresql://localhost:5432/demo_db");
//        ds.setUsername("demo_user");
//        ds.setPassword("demo_pass");
        return ds;
    }

    @Bean("B")
    HikariDataSource dataSourceB() {
        HikariDataSource ds = new HikariDataSource();
//        ds.setJdbcUrl("jdbc:postgresql://localhost:5432/demo_db");
//        ds.setUsername("demo_user");
//        ds.setPassword("demo_pass");
        return ds;
    }

    @Bean("user")
    HikariDataSource dataSourceUser() {
        HikariDataSource ds = new HikariDataSource();
//        ds.setJdbcUrl("jdbc:postgresql://localhost:5432/demo_db");
//        ds.setUsername("demo_user");
//        ds.setPassword("demo_pass");
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
        routing.setTargetDataSources(targetMap);
        routing.setDefaultTargetDataSource(dsUser);
        routing.afterPropertiesSet();
        return routing;
    }
}

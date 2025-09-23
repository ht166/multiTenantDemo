package com.demo.config.datasource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.zaxxer.hikari.HikariDataSource;
@Configuration
public class DataSourceConfig {

    @Bean("A")
    @ConfigurationProperties("spring.datasource.A")
    public HikariDataSource dataSourceA() {
        return new HikariDataSource();
    }

    @Bean("B")
    @ConfigurationProperties("spring.datasource.B")
    public HikariDataSource dataSourceB() {
        return new HikariDataSource();
    }

    @Bean("user")
    @ConfigurationProperties("spring.datasource.user")
    public HikariDataSource dataSourceUser() {
        return new HikariDataSource();
    }

    @Bean
    public DataSourceRegistry dataSourceRegistry(
            @Qualifier("A") DataSource dsA,
            @Qualifier("B") DataSource dsB,
            @Qualifier("user") DataSource dsUser
    ) {
        DataSourceRegistry registry = new DataSourceRegistry();
        registry.add("A", dsA);
        registry.add("B", dsB);
        registry.add("user", dsUser);
        return registry;
    }

    @Bean
    @DependsOn("dataSourceRegistry")
    public RoutingDataSource routingDataSource(DataSourceRegistry registry) {
        RoutingDataSource routing = new RoutingDataSource();
        routing.setTargetDataSources(registry.getAll());
        routing.setDefaultTargetDataSource(registry.get("user"));
        routing.afterPropertiesSet();
        return routing;
    }
}

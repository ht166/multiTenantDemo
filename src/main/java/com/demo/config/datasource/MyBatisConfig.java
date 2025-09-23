package com.demo.config.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@MapperScan(basePackages = "com.demo.repository")
public class MyBatisConfig {

    private final RoutingDataSource routingDataSource;

    public MyBatisConfig(RoutingDataSource routingDataSource) {
        this.routingDataSource = routingDataSource;
    }

    @Bean
    @Lazy
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(routingDataSource);
        return factory.getObject();
    }

    @Bean
    @Lazy
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}

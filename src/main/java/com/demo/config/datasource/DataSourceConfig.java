package com.demo.config.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.demo.config.datasource.props.AbstractDataSourceSpot;
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
	
	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(DataSourceConfig.class);
	
	/** 
	 * データソースプロパティ
	 *  */
	/** SpotAのデータソースプロパティ*/
	private final SpotA spotA;
	
	/** SpotBのデータソースプロパティ*/
	private final SpotB spotB;
	
	/** ユーザーのデータソースプロパティ */
	private final UserDataSource userDataSource;
	
	/** postgreSQL用のドライバー名 */
	private static final String POSTGRESQL_DRIVER_CLASS_NAME = "org.postgresql.Driver";
	/**
	 * <pre>
	 * DataSourceConfigのコンストラクタ
	 * Springによって引数に記載のBeanが注入される。
	 * </pre>
	 */
	public DataSourceConfig(SpotA spotA, SpotB spotB, UserDataSource userDataSource) {
		this.spotA = spotA;
		this.spotB = spotB;
		this.userDataSource = userDataSource;
	}
	
    @Bean("A")
    HikariDataSource dataSourceA() {
        return setDataSourceProperties(spotA);
    }

    @Bean("B")
    HikariDataSource dataSourceB() {
        return setDataSourceProperties(spotB);
    }

    @Bean("user")
    HikariDataSource dataSourceUser() {
        return setDataSourceProperties(userDataSource);
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
    
    /**
     * データソースプロパティをHikariDataSourceに設定する処理
     * @param dsProp データソースプロパティ
     * @return
     */
    private HikariDataSource setDataSourceProperties(AbstractDataSourceSpot dsProp) {
    	HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(dsProp.getUrl());
        ds.setUsername(dsProp.getUsername());
        ds.setPassword(dsProp.getPassword());
        ds.setDriverClassName(POSTGRESQL_DRIVER_CLASS_NAME);
        return ds;
    }
}

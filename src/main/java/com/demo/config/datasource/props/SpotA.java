package com.demo.config.datasource.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 * SpotA用ののデータソースプロパティクラス
 */
@ConfigurationProperties(prefix="a")
public class SpotA extends AbstractDataSourceSpot {
}

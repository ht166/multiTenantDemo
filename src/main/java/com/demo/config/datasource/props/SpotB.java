package com.demo.config.datasource.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 * SpotB用のデータソースプロパティクラス
 * @author ht166
 *
 */
@ConfigurationProperties(prefix="b")
public class SpotB extends AbstractDataSourceSpot {
}

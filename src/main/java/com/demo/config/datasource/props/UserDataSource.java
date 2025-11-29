package com.demo.config.datasource.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 * ユーザーデータベース用のデータソースプロパティクラス
 * @author ht166
 *
 */
@ConfigurationProperties(prefix="user")
public class UserDataSource extends AbstractDataSourceSpot {
}

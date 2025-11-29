package com.demo.config.datasource.props;

import lombok.Getter;
import lombok.Setter;
/**
 * <pre>
 * データソースプロパティの抽象クラス
 * 全てのデータソースプロパティクラスはこのクラスを継承する。
 * </pre>
 * @author ht166
 *
 */
@Getter
@Setter
public abstract class AbstractDataSourceSpot {
	private String url; 
	private String username;
	private String password;
}

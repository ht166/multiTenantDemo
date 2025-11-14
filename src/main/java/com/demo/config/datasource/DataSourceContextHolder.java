package com.demo.config.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DataSourceContextHolder {
	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(DataSourceContextHolder.class);
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

	public static void setDataSourceKey(String key) {
		contextHolder.set(key);
	}

	public static String getDataSourceKey() {
		return contextHolder.get();
	}

	public static void clear() {
		log.info("データソースクリア");
		contextHolder.remove();
	}
	
    public static void setIfChanged(String dsKey) {
    	log.info("setIfChanged(dsKey)実行");
    	log.info("getDataSourceKey(){}",getDataSourceKey());
    	
        if (!dsKey.equals(getDataSourceKey())) {
        	log.info("データソースキーが異なるのでデータソースを変更");
            setDataSourceKey(dsKey);
        }else {
        	log.info("データソースキーが同じなので変更しない");
        }
    }
}

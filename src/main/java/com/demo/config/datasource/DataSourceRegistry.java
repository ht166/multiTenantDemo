package com.demo.config.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

@Component
public class DataSourceRegistry {

    private final Map<String, DataSource> dataSources = new HashMap<>();

    // データソースを追加
    public void add(String name, DataSource dataSource) {
        dataSources.put(name, dataSource);
    }

    // データソースを取得
    public DataSource get(String name) {
        return dataSources.get(name);
    }

    // 全部取得
    @Deprecated
    public Map<Object, Object> getAll() {
        // AbstractRoutingDataSource で使える Map<Object,Object> に変換
        Map<Object, Object> map = new HashMap<>();
        dataSources.forEach((k,v) -> map.put(k, v));
        return map;
    }

    // データソース名を解決（必要なら変換用）
    public String resolveDataSourceName(String key) {
        return dataSources.containsKey(key) ? key : "user";
    }
}


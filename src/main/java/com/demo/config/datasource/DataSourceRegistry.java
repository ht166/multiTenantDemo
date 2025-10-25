package com.demo.config.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

@Component
public class DataSourceRegistry {

    private final Map<String, DataSource> dataSources = new HashMap<>();

    // �f�[�^�\�[�X��ǉ�
    public void add(String name, DataSource dataSource) {
        dataSources.put(name, dataSource);
    }

    // �f�[�^�\�[�X���擾
    public DataSource get(String name) {
        return dataSources.get(name);
    }

    // �S���擾
    public Map<Object, Object> getAll() {
        // AbstractRoutingDataSource �Ŏg���� Map<Object,Object> �ɕϊ�
        Map<Object, Object> map = new HashMap<>();
        dataSources.forEach((k,v) -> map.put(k, v));
        return map;
    }

    // �f�[�^�\�[�X���������i�K�v�Ȃ�ϊ��p�j
    public String resolveDataSourceName(String key) {
        return dataSources.containsKey(key) ? key : "user";
    }
}


package com.demo.config.datasource;

public class DataSourceContext {

    public static void setIfChanged(String dsKey) {
        if (!dsKey.equals(DataSourceContextHolder.getDataSourceKey())) {
            DataSourceContextHolder.setDataSourceKey(dsKey);
        }
    }

    public static void clear() {
        DataSourceContextHolder.clear();
    }

    public static void with(String dsKey, Runnable task) {
        try {
            setIfChanged(dsKey);
            task.run();
        } finally {
            clear();
        }
    }
}


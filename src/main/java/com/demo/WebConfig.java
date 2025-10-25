package com.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.demo.utill.interceptor.SelectDataSourceInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SelectDataSourceInterceptor selectDataSourceInterceptor;
    

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(selectDataSourceInterceptor)
                .addPathPatterns("/**"); // 全リクエスト対象
    }
}

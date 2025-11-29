package com.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.demo.utill.interceptor.SelectDataSourceInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final SelectDataSourceInterceptor selectDataSourceInterceptor;
    
    /**
     * コンストラクタ
     */
    public WebConfig(SelectDataSourceInterceptor selectDataSourceInterceptor) {
    	this.selectDataSourceInterceptor = selectDataSourceInterceptor;
	}
    
    /**
     * インターセプターの登録
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	String[] excludePath = {
    			"/css/**"
    			,"/js/**"
    			,"/images/**"
    			,"/webjars/**"
    	};
        registry.addInterceptor(selectDataSourceInterceptor)
                .addPathPatterns("/**").excludePathPatterns(excludePath); //静的リソース以外すべてのリクエストを対象
    }
}

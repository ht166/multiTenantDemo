package com.demo.utill.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.demo.config.datasource.DataSourceContextHolder;
import com.demo.dto.UserInfDto;


@Component
public class SelectDataSourceInterceptor implements HandlerInterceptor {
	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(SelectDataSourceInterceptor.class);
	
	@Autowired
	private UserInfDto sessionUser;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("データソース切り替え処理開始");
    	String dsKey = "user";

    	if (sessionUser != null && sessionUser.getCode() != null && !sessionUser.getCode().isBlank()) {
            dsKey = sessionUser.getCode();
            log.info("dsKey = " +dsKey);
            DataSourceContextHolder.setDataSourceKey(dsKey);
        }else if(sessionUser != null) {
        	log.info("セッションユーザーがnullのためデータソース切り替えなし");
        }else {
        	log.info("セッションユーザーのcodeが不明のためデータソース切り替えなし");
        }

       
        
        log.info("データソース切り替え処理終了");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        DataSourceContextHolder.clear();
    }
}

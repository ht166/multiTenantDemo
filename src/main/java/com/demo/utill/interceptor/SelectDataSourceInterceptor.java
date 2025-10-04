package com.demo.utill.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.demo.config.datasource.DataSourceContextHolder;
import com.demo.dto.UserInfDto;


@Component
public class SelectDataSourceInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String dsKey = "user";
        UserInfDto user = (UserInfDto) request.getSession().getAttribute("userInfDto");
        if (user != null && user.getCode() != null && !user.getCode().isBlank()) {
            dsKey = user.getCode();
        }

        // ここで現在のキーと比較して変更があればのみセット
        String currentKey = DataSourceContextHolder.getDataSourceKey();
        if (!dsKey.equals(currentKey)) {
            DataSourceContextHolder.setDataSourceKey(dsKey);
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        DataSourceContextHolder.clear();
    }
}

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
	/** ���K�[ */
	private static final Logger log = LoggerFactory.getLogger(SelectDataSourceInterceptor.class);
	
	@Autowired
	private UserInfDto sessionUser;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("�f�[�^�\�[�X�؂�ւ������J�n");
    	String dsKey = "user";

    	if (sessionUser != null && sessionUser.getCode() != null && !sessionUser.getCode().isBlank()) {
            dsKey = sessionUser.getCode();
            log.info("dsKey = " +dsKey);
            DataSourceContextHolder.setDataSourceKey(dsKey);
        }else if(sessionUser != null) {
        	log.info("�Z�b�V�������[�U�[��null�̂��߃f�[�^�\�[�X�؂�ւ��Ȃ�");
        }else {
        	log.info("�Z�b�V�������[�U�[��code���s���̂��߃f�[�^�\�[�X�؂�ւ��Ȃ�");
        }

       
        
        log.info("�f�[�^�\�[�X�؂�ւ������I��");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        DataSourceContextHolder.clear();
    }
}

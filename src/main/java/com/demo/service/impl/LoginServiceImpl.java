package com.demo.service.impl;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.UserInfDto;
import com.demo.entity.UserInf;
import com.demo.repository.UserInfMapper;
import com.demo.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private UserInfMapper userInfMapper;

	public boolean countByIdAndPassword(String userId, String password) {
		logger.info("countByIdAndPasswordを実行");
		int count = userInfMapper.countByIdAndPassword(userId, password);
		logger.info("countByIdAndPasswordが成功");
		
		return (count >= 0);
	}
	
	public UserInfDto getUserInf(String userId) {
		logger.info("getUserInfを実行");
		UserInf user = userInfMapper.getUserInf(userId);

		// DTO に変換
		UserInfDto dto = new UserInfDto();
		dto.setId(user.getId());
		dto.setUserName(user.getUserName());
		dto.setLevel(user.getLevel());
		dto.setCode(user.getCode());
		logger.info("getUserInfが成功　DTOに変換");
		return dto;
	}

}

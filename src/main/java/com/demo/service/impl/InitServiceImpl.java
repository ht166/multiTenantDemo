package com.demo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.demo.config.datasource.DataSourceContext;
import com.demo.entity.UserInf;
import com.demo.repository.UserInfMapper;
import com.demo.service.InitService;

import jakarta.annotation.Resource;

public class InitServiceImpl implements InitService {
	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(InitServiceImpl.class);

	@Resource
	private UserInfMapper mapper;

	@EventListener(ApplicationReadyEvent.class)
	public void init() {
		DataSourceContext.with("user", () -> {
			List<UserInf> userList = mapper.getAllUser();
			if (userList == null || userList.isEmpty()) {
				log.info("ユーザーが1件も存在しません。");
			} else {
				for (UserInf user : userList) {
					log.info("ユーザーID={}, ユーザー名={}, レベル={}, コード={}",
							user.getId(), user.getUserName(), user.getLevel(), user.getCode());
				}
			}
		});
	}

}

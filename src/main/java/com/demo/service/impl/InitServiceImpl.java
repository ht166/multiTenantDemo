package com.demo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.demo.config.datasource.DataSourceContext;
import com.demo.entity.UserInf;
import com.demo.repository.UserInfMapper;
import com.demo.service.InitService;
@Service
public class InitServiceImpl implements InitService {
	/** ���K�[ */
	private static final Logger log = LoggerFactory.getLogger(InitServiceImpl.class);

	@Resource
	private UserInfMapper mapper;

	@EventListener(ApplicationReadyEvent.class)
	public void init() {
		DataSourceContext.with("user", () -> {
			List<UserInf> userList = mapper.getAllUser();
			if (userList == null || userList.isEmpty()) {
				log.info("���[�U�[��1�������݂��܂���B");
			} else {
				for (UserInf user : userList) {
					log.info("���[�U�[ID={}, ���[�U�[��={}, ���x��={}, �R�[�h={}",
							user.getId(), user.getUserName(), user.getLevel(), user.getCode());
				}
			}
		});
	}

}

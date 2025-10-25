package com.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.UserInfDto;
import com.demo.entity.UserInf;
import com.demo.repository.UserInfMapper;
import com.demo.service.AdminService;

/**
 * 管理者アカウントのサービスを実装したクラス
 * @author ht166
 *
 */
@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private UserInfMapper userInfMapper;

	/**
	 * ユーザーの全件一覧を取得
	 */
	@Override
	public List<UserInfDto> getAllUsers() {
		// DBからEntityのリストを取得
		List<UserInf> userList = userInfMapper.getAllUser();

		// Entity → DTO に変換
		List<UserInfDto> dtoList = new ArrayList<>();
		for (UserInf user : userList) {
			UserInfDto dto = new UserInfDto();
			dto.setId(user.getId());
			dto.setUserName(user.getUserName());
			dto.setLevel(user.getLevel());
			dto.setCode(user.getCode());
			dtoList.add(dto);
		}

		return dtoList;
	}

}

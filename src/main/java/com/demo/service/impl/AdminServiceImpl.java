package com.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dto.UserInfDto;
import com.demo.entity.UserInf;
import com.demo.form.AdminForm;
import com.demo.repository.AdminMapper;
import com.demo.repository.UserInfMapper;
import com.demo.service.AdminService;

/**
 * 管理者アカウントのサービスを実装したクラス
 * @author ht166
 *
 */
@Service
public class AdminServiceImpl implements AdminService {
	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	private final UserInfMapper userInfMapper;
	
	private final AdminMapper adminMapper;
	
	/**
	 * AdminServiceImplのコンストラクタ
	 * @param userInfMapper
	 * @param adminMapper
	 */
	public AdminServiceImpl(UserInfMapper userInfMapper,AdminMapper adminMapper) {
		this.userInfMapper = userInfMapper;
		this.adminMapper = adminMapper;
	}

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

	@Override
	public int createUser(Map<String, Object> info) {
		
		
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}
	@Transactional
	@Override
	public int deleteUser(String targetUserId) {
		log.info("ユーザーID：{}削除処理　開始",targetUserId);
		int result = adminMapper.deleteUser(targetUserId);
		
		log.info("{}件削除成功",result);
		
		return result;
	}

	@Override
	public int updateUserInfo(AdminForm updateUserInfo) {
		Map<String,Object> editInfo = new HashMap<>();
		editInfo.put("id", updateUserInfo.getId());
		editInfo.put("password", updateUserInfo.getPassword());
		editInfo.put("userName", updateUserInfo.getUserName());
		editInfo.put("created_at", updateUserInfo.getCreated_at());
		editInfo.put("level", updateUserInfo.getLevel());
		editInfo.put("code", updateUserInfo.getCode());
		int result = adminMapper.updateUserInf(editInfo);
		return result;
	}
}

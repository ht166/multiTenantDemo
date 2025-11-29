package com.demo.service;

import java.util.List;
import java.util.Map;

import com.demo.dto.UserInfDto;
import com.demo.form.AdminForm;
/**
 * 管理者アカウントのサービス
 * @author ht166
 *
 */
public interface AdminService {
	/**
	 * ユーザー一覧を取得
	 * @return ユーザー一覧
	 */
	public  List<UserInfDto> getAllUsers();
	
	public int createUser(Map<String,Object> info) ;
	public int updateUserInfo(AdminForm updateInfo) ;

	public int deleteUser(String targetId);
	
	
		
}

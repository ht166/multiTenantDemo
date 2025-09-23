package com.demo.service;

import java.util.List;

import com.demo.dto.UserInfDto;
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
}

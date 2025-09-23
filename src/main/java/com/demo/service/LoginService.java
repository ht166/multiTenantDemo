package com.demo.service;

import com.demo.dto.UserInfDto;

public interface LoginService {
	/**
	 * ログインチェック
	 * @param userId ユーザーID
	 * @param password パスワード
	 * @return ログインチェック結果
	 */
	boolean countByIdAndPassword(String userId,String password);
	
	public UserInfDto getUserInf(String userId) ;
}

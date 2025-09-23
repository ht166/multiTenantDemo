package com.demo.dto;

import java.security.Timestamp;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Getter;
import lombok.Setter;
/**
 * ユーザー情報のDTO
 * @author ht166
 *
 */
@Component
@Getter
@Setter
@SessionScope
public class UserInfDto {
	/*
	 * ユーザーID
	 */
	private String id;
	
	/*
	 * パスワード
	 */
	private String password;
	
	/*
	 * ユーザー名
	 */
	private String userName;
	
	/*
	 * 登録時間
	 */
	private Timestamp created_at;
	
	/*
	 * メニューレベル
	 */
	
	private int level;
	
	/**
	 * 所属コード
	 */
	private String code;
}

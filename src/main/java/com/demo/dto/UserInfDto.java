package com.demo.dto;

import java.security.Timestamp;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

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
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
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

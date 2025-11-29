package com.demo.form;

import java.security.Timestamp;

import lombok.Getter;
import lombok.Setter;
/**
 * ユーザー管理画面フォーム
 * @author ht166
 *
 */
@Getter
@Setter
public class AdminForm {
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

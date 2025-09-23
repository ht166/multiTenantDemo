package com.demo.entity;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInf {

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


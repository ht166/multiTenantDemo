package com.demo.entity;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInf {

		/*
		 * ���[�U�[ID
		 */
		private String id;
		
		/*
		 * �p�X���[�h
		 */
		private String password;
		
		/*
		 * ���[�U�[��
		 */
		private String userName;
		
		/*
		 * �o�^����
		 */
		private Timestamp created_at;
		
		/*
		 * ���j���[���x��
		 */
		
		private int level;
		
		/**
		 * �����R�[�h
		 */
		private String code;
	}


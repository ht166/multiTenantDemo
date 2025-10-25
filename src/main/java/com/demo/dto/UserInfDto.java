package com.demo.dto;

import java.security.Timestamp;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
/**
 * ���[�U�[����DTO
 * @author ht166
 *
 */
@Component
@Getter
@Setter
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserInfDto {
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

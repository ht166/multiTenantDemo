package com.demo.service;

import com.demo.dto.UserInfDto;

public interface LoginService {
	/**
	 * ���O�C���`�F�b�N
	 * @param userId ���[�U�[ID
	 * @param password �p�X���[�h
	 * @return ���O�C���`�F�b�N����
	 */
	boolean countByIdAndPassword(String userId,String password);
	
	public UserInfDto getUserInf(String userId) ;
}

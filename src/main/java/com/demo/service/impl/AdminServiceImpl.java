package com.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.UserInfDto;
import com.demo.entity.UserInf;
import com.demo.repository.UserInfMapper;
import com.demo.service.AdminService;

/**
 * �Ǘ��҃A�J�E���g�̃T�[�r�X�����������N���X
 * @author ht166
 *
 */
@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private UserInfMapper userInfMapper;

	/**
	 * ���[�U�[�̑S���ꗗ���擾
	 */
	@Override
	public List<UserInfDto> getAllUsers() {
		// DB����Entity�̃��X�g���擾
		List<UserInf> userList = userInfMapper.getAllUser();

		// Entity �� DTO �ɕϊ�
		List<UserInfDto> dtoList = new ArrayList<>();
		for (UserInf user : userList) {
			UserInfDto dto = new UserInfDto();
			dto.setId(user.getId());
			dto.setUserName(user.getUserName());
			dto.setLevel(user.getLevel());
			dto.setCode(user.getCode());
			dtoList.add(dto);
		}

		return dtoList;
	}

}

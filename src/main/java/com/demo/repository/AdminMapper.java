package com.demo.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
	public int deleteUser(String targetUserId);
	
	public int updateUserInf(Map<String,Object> condition);
}

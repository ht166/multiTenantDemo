package com.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.demo.entity.UserInf;

@Mapper
public interface UserInfMapper {
	
	int countByIdAndPassword(@Param(value= "userId") String userId,@Param(value= "password") String password);
	
	UserInf getUserInf(String userId);
	
	List<UserInf> getAllUser();
}

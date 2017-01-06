package com.teardowall.mapper;

import com.teardowall.mapper.mybatis.TearMapper;
import com.teardowall.models.User;

@TearMapper
public interface UserMapper {
	User findUserByEmail(String email);
	User findUserById(String id);
	void addUser(User user);
	void updateUser(User user);
}

package com.teardowall.mapper;

import com.teardowall.mapper.mybatis.TearMapper;
import com.teardowall.models.User;

@TearMapper
public interface UserMapper {
	User findUserByEmail(String email);
}

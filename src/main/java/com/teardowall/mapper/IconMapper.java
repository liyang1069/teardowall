package com.teardowall.mapper;

import java.util.List;

import com.teardowall.mapper.mybatis.TearMapper;
import com.teardowall.models.Icon;

@TearMapper
public interface IconMapper {
	List<Icon> getIconByKeyWord(String keyword);
}

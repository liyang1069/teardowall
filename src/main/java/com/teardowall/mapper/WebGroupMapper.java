package com.teardowall.mapper;

import java.util.List;

import com.teardowall.mapper.mybatis.TearMapper;
import com.teardowall.models.WebGroup;

@TearMapper
public interface WebGroupMapper {
	//WebGroup[] getDefaultGroups();
	List<WebGroup> getDefaultGroups();
	List<WebGroup> getGroupsByUserId(String userId);
	WebGroup getGroupById(String groupId);
	WebGroup getGroupByIdAndUserId(String groupId, String userId);
	long insertGroup(WebGroup group);
	void updateGroup(WebGroup group);
	void deleteGroup(String groupId);
}

package com.teardowall.mapper;

import java.util.List;

import com.teardowall.mapper.mybatis.TearMapper;
import com.teardowall.models.Icon;
import com.teardowall.models.WebSite;

@TearMapper
public interface WebSiteMapper {
	int getTest(int id);
	void insertIconBatch(List<Icon> list);
	void insertIcon(Icon icon);
	void insertWeb(WebSite site);
	WebSite[] getSitesByGroupId(String groupId);
}

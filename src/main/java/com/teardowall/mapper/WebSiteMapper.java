package com.teardowall.mapper;

import java.util.List;

import com.teardowall.mapper.mybatis.TearMapper;
import com.teardowall.models.GroupsSites;
import com.teardowall.models.Icon;
import com.teardowall.models.WebSite;

@TearMapper
public interface WebSiteMapper {
	int getTest(int id);
	void insertIconBatch(List<Icon> list);
	void insertIcon(Icon icon);
	long insertWeb(WebSite site);
	List<WebSite> getSitesByGroupId(String groupId);
	String iconPathById(String iconId);
	void insertGroupSiteRelation(GroupsSites gs);
	void deleteSitesByGroupId(String groupId);
	void deleteGroupSiteRelationByGroupId(String groupId);
	List<WebSite> getDefaultIconSites(String iconId);
}

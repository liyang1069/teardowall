package com.teardowall.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.teardowall.mapper.WebGroupMapper;
import com.teardowall.mapper.WebSiteMapper;
import com.teardowall.models.WebGroup;
import com.teardowall.models.WebSite;

@Component
@Transactional
public class WebGroupService extends BaseService {
	
	@Resource
	private WebGroupMapper webGroupMapper;
	
	@Resource
	private WebSiteMapper webSiteMapper;
	
	public Map<WebGroup, WebSite[]> getDefaultGroups(){
		//WebGroup[] groups = webGroupMapper.getDefaultGroups();
		List<WebGroup> groups = webGroupMapper.getDefaultGroups();
		//List<Map<WebGroup, WebSite[]>> list = new ArrayList<Map<WebGroup, WebSite[]>>();
		Map<WebGroup, WebSite[]> hash = new HashMap<>();
		for(Iterator<WebGroup> it = groups.iterator(); it.hasNext();){
			WebGroup group = it.next();
			WebSite[] sites = webSiteMapper.getSitesByGroupId(group.getId());
			hash.put(group, sites);
		}
		return hash;
	}
}

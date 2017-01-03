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
	
	@Resource
	private WebSiteService webSiteService;
	
	public List<WebGroup> getGroupsByUserId(String userId){
		List<WebGroup> groups = webGroupMapper.getGroupsByUserId(userId);
		if(groups == null || groups.size() == 0){
			groups = getDefaultGroups();
		}
		return groups;
	}
	
	public List<WebGroup> getDefaultGroups(){
		//WebGroup[] groups = webGroupMapper.getDefaultGroups();
		List<WebGroup> groups = webGroupMapper.getDefaultGroups();
		//List<Map<WebGroup, WebSite[]>> list = new ArrayList<Map<WebGroup, WebSite[]>>();
		//Map<WebGroup, WebSite[]> hash = new HashMap<>();
		return groups;
		//return hash;
	}
	
	public List<List<WebSite>> getSitesByGroups(List<WebGroup> groups){
		List<List<WebSite>> sites = new ArrayList<>();
		for(Iterator<WebGroup> it = groups.iterator(); it.hasNext();){
			WebGroup group = it.next();
			List<WebSite> ws = webSiteMapper.getSitesByGroupId(group.getId());
			for(Iterator<WebSite> its = ws.iterator(); its.hasNext(); ){
				WebSite site = its.next();
				site.setIconPath(webSiteService.iconPathById(site.getIconId()));
			}
			int addNum = 0;
			if(ws.size() < 4){
				addNum = 4 - ws.size();
			}
			else if(ws.size() < 8){
				addNum = 8 - ws.size();
			}
			for(int i = 0; i < addNum; i++){
				ws.add(getNullSite());
			}
			sites.add(ws);
		}
		return sites;
	}
	
	private WebSite getNullSite(){
		WebSite site = new WebSite();
		site.setId("0");
		site.setName("");
		site.setIconId("");
		site.setWebUrl("");
		return site;
	}
}
